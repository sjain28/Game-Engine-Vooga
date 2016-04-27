package authoring.gui;



/**
This applet allows the user to draw and edit a 2D bezier spline
of any degree. Editing has three modes: <p>
<ul>
<li>Extending - adding point to the control polygon of the bezier.
<li>Editing - moving existing control point by picking and dragging.
<li>Deleting - removing any existing control point by picking it.
</ul>
There is a clear button and a button to view the de-Casteljau 
algorithm in action.<p>
The additional view mode is for watching de-Casteljau algorithm
steps in finding a point on the bezier curve for a given parameter
value t. The value of t can be changed using a scroll bar.
*/

import java.awt.*;
import java.applet.*;
import java.util.Vector;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class DrawBezier extends Applet {
/** main applet class */
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static CoordLable coordLabel = new CoordLable();
	public static DrawPanel dp = new DrawPanel();
	public static Controls theControls = new Controls(dp);

    public void init() {
		setLayout(new BorderLayout());

		add("North", coordLabel);
		add("Center", new FramedArea(dp));
		add("South",theControls);

			// force loading of Geometry class
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		Point p3 = Geometry.interpolate(p1,p2,0.5);
    }

    public boolean handleEvent(Event e) {
		switch (e.id) {
		  case Event.WINDOW_DESTROY:
			System.exit(0);
			return true;
		  default:
			return false;
		}
    }

    public static void main(String args[]) {
		Frame f = new Frame("DrawBezier");
		DrawBezier drawBez = new DrawBezier();
		drawBez.init();
		drawBez.start();

		f.add("Center", drawBez);
		f.resize(400, 400);
		f.show();
    }
}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class Geometry {
// A class for some geometric global functions
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// returns the euclidian distance between two points
	public static double dist(Point p0, Point p1) {
		int dx = p1.x - p0.x;
		int dy = p1.y - p0.y;
		int sum = dx*dx + dy*dy;
		return Math.sqrt((double)sum);
	}

	// returns the linear interpolation of two points
	public static Point interpolate(Point p0, Point p1,double t) {
		double x = t * p1.x + (1-t) * p0.x;
		double y = t * p1.y + (1-t) * p0.y;
		return new Point((int)(x+0.5),(int)(y+0.5));
	}

	// evaluates a bezier defined by the control polygon
	// which points are given in the array at the value t
	public static Point evalBezier(Point arr[],double t) {
		for (int iter = arr.length ; iter > 0 ; iter--) {
			for (int i = 1 ; i < iter ; i++) {
				arr[i-1] = interpolate(arr[i-1],arr[i],t);
			}
		}
		return arr[0];
	}

	// evaluates a bezier defined by the control polygon
	// which points are given in the array at the value t
	// Note: this function is recursive
	public static Point evalBezierRec(Point arr[],double t,int iter) {
		if (iter == 1)
			return arr[0];
		for (int i = 1 ; i < iter ; i++) {
			arr[i-1] = interpolate(arr[i-1],arr[i],t);
		}
		return evalBezierRec(arr,t,iter-1);
	}

}


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class CoordLable extends Panel {
// This class exists solely to put a title showing where the mouse is
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	Label coords;

	public CoordLable() {
		setLayout(new GridLayout(1,0));
		coords = new Label("(?,?)");
		add(new Label("Mouse at ",Label.RIGHT));
		add(coords);
		validate();
	}

	public void setCoords(int x, int y) {
		coords.setText("(" + x + "," + y + ")");
	}

}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class FramedArea extends Panel {
// This class exists solely to put a frame around the drawing area. 
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public FramedArea(DrawPanel target) {
		super();

		//Set layout to one that makes its contents as big as possible.
		setLayout(new GridLayout(1,0));

		add(target);
		validate();
    }

    public Insets insets() {
		return new Insets(4,4,5,5);
    }

    public void paint(Graphics g) {
        Dimension d = size();
        Color bg = getBackground();
 
        g.setColor(bg);
        g.draw3DRect(0, 0, d.width - 1, d.height - 1, true);
        g.draw3DRect(3, 3, d.width - 7, d.height - 7, false);
    }
}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class DrawPanel extends Panel {
// The drawing panel class
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		// edit modes
    public static final int EXTEND = 0;
    public static final int EDIT = 1;
    public static final int DELETE = 2;
    int	   mode = EXTEND;
		// DeCasteljau algorithm show 
	boolean bShow = false;
	int		step = 0;
	Point	dePnts[][];
	public double tVal = 0.5;

		// vector to hold the control points
    Vector points = new Vector();

		// helper variables for mouse dragging
	Point mPoint;
	int index;

		// constractor
	//----------------------------------------------------------
    public DrawPanel() {
	//----------------------------------------------------------
		setBackground(Color.white);
		mPoint = new Point(-1,-1);
	}

		// drawing modes setting
	//----------------------------------------------------------
    public void setDrawMode(int mode) {
	//----------------------------------------------------------
		switch (mode) {
		case EXTEND:
		case EDIT:
		case DELETE:
			this.mode = mode;
			break;
		default:
			throw new IllegalArgumentException();
		}
    }

		// initialize De-Castlejau algorithm show
	//----------------------------------------------------------
	public void initShow() {
	//----------------------------------------------------------
		bShow = true;
		step = 0;
		// allocate space for points
		int i,j;
		int np = points.size();
		if (np <= 0)
			return;
		dePnts = new Point[np][];
		for (i = 0 ; i < np ; i++)
			dePnts[i] = new Point[np-i];
		// copy original polygon
		points.copyInto(dePnts[0]);
		// fill the points of all algorithm steps
		for (i = 1 ; i < np ; i++)
			for (j = 0 ; j < np-i ; j++)
				dePnts[i][j] = Geometry.interpolate(dePnts[i-1][j],dePnts[i-1][j+1],tVal);
		repaint();
	}

		// advance one step in De-Casteljau algorithm
	//----------------------------------------------------------
	public void stepShow() {
	//----------------------------------------------------------
		step++;
		if (step >= points.size())
			step = 0;		// loop around again
		repaint();
	}

		// end De-Castlejau algorithm show
	//----------------------------------------------------------
	public void endShow() {
	//----------------------------------------------------------
		bShow = false;
		step = 0;
		repaint();
	}

		// clearing all control points
	//----------------------------------------------------------
	public void clearAll() {
	//----------------------------------------------------------
		points.removeAllElements();
		repaint();
	}

		// helper function to draw the de-casteljau algorithm
	//----------------------------------------------------------
	protected void drawDeCasteljau(Graphics g) {
	//----------------------------------------------------------
		int np = points.size();
		if (np <= 0)
			return;
		Point p0,p1;
		for (int i=0 ; i <= step ; i++) {
				// draw first point
			p0 = dePnts[i][0];
			g.setColor(Color.red);
			g.fillRect(p0.x - 2, p0.y - 2, 4, 4);
				// draw polygon & other points
			for (int j = 1 ; j < np-i ; j++) {
				p1 = dePnts[i][j];
				g.setColor(Color.blue);
				g.drawLine(p0.x, p0.y, p1.x, p1.y);
				g.setColor(Color.red);
				g.fillRect(p1.x - 2, p1.y - 2, 4, 4);
				p0 = p1;
			}
		}
		if (step == np-1) {	// i.e. last step
			drawBezier(g);
				// show last point
			g.setColor(Color.black);	
			p0 = dePnts[step][0];
			g.fillRect(p0.x - 2, p0.y - 2, 4, 4);
		}
	}

		// helper function to draw the bezier spline
	//----------------------------------------------------------
	protected void drawBezier(Graphics g) {
	//----------------------------------------------------------
		int np = points.size();
		if (np < 3)
			return;

		Point ptArray[] = new Point[np];

		Point p0,p1;
		p0 = (Point)points.elementAt(0);
		for (int i = 1 ; i <= 50 ; i++) {
			double t = (double)i/50.0;
			points.copyInto(ptArray);
			//p1 = Geometry.evalBezierRec(ptArray,t,np);
			p1 = Geometry.evalBezier(ptArray,t);
			g.setColor(Color.green);
			g.drawLine(p0.x, p0.y, p1.x, p1.y);
			p0 = p1;
		}
	}
		// helper function: finds nearest control point
		// to the input argument point op
	//----------------------------------------------------------
	protected int getNearestPointIndex(Point op) {
	//----------------------------------------------------------
		int np = points.size();
		if (np == 0)
			return -1;

		int index = -1;
		double dist = Geometry.dist(op,(Point)points.elementAt(0));
		if (dist < 10.0)
			index = 0;

		for (int i=1 ; i < np; i++) {
			Point p = (Point)points.elementAt(i);
			double newDist = Geometry.dist(op,p);
			if (newDist < dist && newDist < 10.0)
				index = i;
		}
		return index;
	}

		// handle events according to edit mode
	//----------------------------------------------------------
    public boolean handleEvent(Event e) {
	//----------------------------------------------------------
		if (bShow) {
			if (e.id == Event.MOUSE_MOVE || e.id == Event.MOUSE_DRAG) {
				DrawBezier.coordLabel.setCoords(e.x,e.y);
				return true;
			}
			return false;
		}

		switch (e.id) {
		  case Event.MOUSE_MOVE:
			DrawBezier.coordLabel.setCoords(e.x,e.y);
			return true;
		  case Event.MOUSE_DOWN:
			mPoint.x = e.x;
			mPoint.y = e.y;
			switch (mode) {
			case EXTEND:
				//if (points.size() > 3)	// no more than 4 points
				//	break;
				points.addElement(new Point(e.x, e.y));
				index = points.size()-1;
				break;
			case EDIT:
			case DELETE:
			default:
				index = getNearestPointIndex(mPoint);
			}
			//repaint();
			return true;
		  case Event.MOUSE_UP:
			switch (mode) {
			case DELETE:
				if (index >=0) {
					points.removeElementAt(index);
				}
				break;
			case EXTEND:
			case EDIT:
			default:
				if (index >= 0) {
						// take care of clipping here (e.x and e.y out of window)
					((Point)points.elementAt(index)).x = e.x;
					((Point)points.elementAt(index)).y = e.y;

				}
			}
			index = -1;
			repaint();
			return true;
		  case Event.MOUSE_DRAG:
			DrawBezier.coordLabel.setCoords(e.x,e.y);
			if (index >= 0) {
				((Point)points.elementAt(index)).x = mPoint.x;
				((Point)points.elementAt(index)).y = mPoint.y;
			}
			mPoint.x = e.x;
			mPoint.y = e.y;
			repaint();
			return true;
		  case Event.WINDOW_DESTROY:
			System.exit(0);
			return true;
		  default:
			return false;
		}
    }

		// draw the bezier spline and control polygon
	//----------------------------------------------------------
	public void paint(Graphics g) {
	//----------------------------------------------------------
		g.setPaintMode();

			// draw only DeCasteljau algorithm when needed
		if (bShow) {
			drawDeCasteljau(g);
			return;
		}

			// draw the bezier spline + polygon
		int np = points.size();
		if (np == 0)
			// draw nothing
			return;

			// draw polygon
		Point p0,p1;
		p0 = (Point)points.elementAt(0);
		g.setColor(Color.red);
		g.fillRect(p0.x - 2, p0.y - 2, 4, 4);

		for (int i=1 ; i < np; i++) {
			p0 = (Point)points.elementAt(i-1);
			p1 = (Point)points.elementAt(i);
			g.setColor(Color.red);
			g.fillRect(p1.x - 2, p1.y - 2, 4, 4);
			g.setColor(Color.blue);
			g.drawLine(p0.x, p0.y, p1.x, p1.y);
		}
		
			// draw bezier
		drawBezier(g);

	/*-**
	// SOMEHOW THERE IS NO NEED FOR THIS...
			// take care of dragging actions
		if (index != -1) {
			Point oldp = (Point)points.elementAt(index);
			Point prevp,nextp;
			
			if (index < np-1) {
				nextp = (Point)points.elementAt(index+1);
				// erase the next line
				g.setXORMode(getBackground());
				g.drawLine(oldp.x, oldp.y, nextp.x, nextp.y);
				g.setColor(getForeground());
				g.setPaintMode();
				// draw new next line
				g.drawLine(mPoint.x, mPoint.y, nextp.x, nextp.y);
			}
			if (index > 0) {
				prevp = (Point)points.elementAt(index-1);
				// erase the previous line
				g.setXORMode(getBackground());
				g.drawLine(prevp.x, prevp.y, oldp.x, oldp.y);
				g.setColor(getForeground());
				g.setPaintMode();
				// draw new previous line
				g.drawLine(prevp.x, prevp.y, mPoint.x, mPoint.y);
			}
		}
	**-*/
    }
}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class Controls extends Panel {
// the Card layout control for the two modes: drawing and viewing de-casteljau
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Controls(DrawPanel target) {
		setLayout(new CardLayout());
		add("Draw", new DrawControls(target));
		add("View", new ViewControls(target));
	}
}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class ViewControls extends Panel {
// The view de-Casteljau controls
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    DrawPanel target;
	Label tValLabel;
	final int INTERVAL = 10;
	final int INITIAL_VAL = INTERVAL/2;

    public ViewControls(DrawPanel target) {
		this.target = target;
		setLayout(new FlowLayout());
		setBackground(Color.lightGray);
		
		tValLabel = new Label("t="+target.tVal+" ");
		add(tValLabel);

		Scrollbar sb = new Scrollbar(Scrollbar.HORIZONTAL,INITIAL_VAL,INTERVAL/10,0,INTERVAL+1);
		add(sb);
		
		Button stepB = new Button("Step");
		add(stepB);
		Button okB = new Button("OK");
		add(okB);

    }

    public void paint(Graphics g) {
		Rectangle r = bounds();
		g.setColor(Color.lightGray);
		g.draw3DRect(0, 0, r.width, r.height, false);
    }

    public boolean action(Event e, Object arg) {
		if (e.target instanceof Button) {
			String choice = (String)arg;

			if (choice.equals("Step")) {
				target.stepShow();
			} else if (choice.equals("OK")) {
				((CardLayout)DrawBezier.theControls.getLayout()).first(DrawBezier.theControls);
				target.endShow();
			}
		}
		return true;
    }

	public boolean handleEvent(Event e) {
		switch (e.id) {
		case Event.SCROLL_LINE_UP:
		case Event.SCROLL_LINE_DOWN:
		case Event.SCROLL_PAGE_UP:
		case Event.SCROLL_PAGE_DOWN:
		case Event.SCROLL_ABSOLUTE:
			if (e.target instanceof Scrollbar) {
				int val = ((Scrollbar)e.target).getValue();
				target.tVal = (double)val/(double)INTERVAL;
				tValLabel.setText("t="+target.tVal+" ");
				target.initShow();
			}
		}
		return super.handleEvent(e);
	}
}


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class DrawControls extends Panel {
// The drawing controls
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    DrawPanel target;

    public DrawControls(DrawPanel target) {
		this.target = target;
		setLayout(new FlowLayout());
		setBackground(Color.lightGray);
		
		Choice modes = new Choice();
		modes.addItem("Extend");
		modes.addItem("Edit");
		modes.addItem("Delete");
		modes.setBackground(Color.lightGray);
		add(modes);

		Button b = new Button("Clear");
		add(b);
		b = new Button("Show De-Casteljau");
		add(b);
    }

    public void paint(Graphics g) {
		Rectangle r = bounds();
		g.setColor(Color.lightGray);
		g.draw3DRect(0, 0, r.width, r.height, false);
    }

    public boolean action(Event e, Object arg) {
		if (e.target instanceof Choice) {
			String choice = (String)arg;

			if (choice.equals("Extend")) {
				target.setDrawMode(DrawPanel.EXTEND);
			} else if (choice.equals("Edit")) {
				target.setDrawMode(DrawPanel.EDIT);
			} else if (choice.equals("Delete")) {
				target.setDrawMode(DrawPanel.DELETE);
			}
		} else if (e.target instanceof Button) {
			String choice = (String)arg;

			if (choice.equals("Clear")) {
				target.clearAll();
			} else if (choice.equals("Show De-Casteljau")) {
				((CardLayout)DrawBezier.theControls.getLayout()).last(DrawBezier.theControls);
				target.initShow();
			}
		}
		return true;
    }                
}
	


    
