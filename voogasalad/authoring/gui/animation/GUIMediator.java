// This entire file is part of my masterpiece.
// Aditya Srinivasan

package authoring.gui.animation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import authoring.Command;
import tools.Pair;

/**
 * A specific implementation of the mediator, for GUI elements. It allows trigger/command pairings to be registered, and returns
 * a list of all Detailable elements. It is composed by commands such that execution can be informed by fields which have details.
 * 
 * @author Aditya Srinivasan
 *
 */
public class GUIMediator implements Mediator {
	
	private List<Pair<? extends Trigger, Command>> triggerActionPairings;
	
	public GUIMediator() {
		this.triggerActionPairings = new ArrayList<>();
	}

	@Override
	public void register(Pair<? extends Trigger, Command> triggerActionPair) {
		triggerActionPairings.add(triggerActionPair);
		triggerActionPair.getFirst().onActivation(e -> {
			triggerActionPair.getLast().execute();
		});
	}

	@Override
	public Collection<Detailable> getDetailables() {
		List<Detailable> detailables = new ArrayList<Detailable>();
		triggerActionPairings.stream().filter(p -> containsDetailable(p))
									  .map(d -> getDetailable(d))
									  .forEach(detailables::add);
		return detailables;
	}
	
	private boolean containsDetailable(Pair<? extends Trigger, Command> pair) {
		return pair.getFirst() instanceof Detailable || pair.getLast() instanceof Detailable;
	}
	
	private Detailable getDetailable(Pair<? extends Trigger, Command> detailablePair) {
		return (Detailable) ((detailablePair.getFirst() instanceof Detailable) ? detailablePair.getFirst() : detailablePair.getLast());
	}

}
