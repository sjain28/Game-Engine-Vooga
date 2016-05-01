package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import events.Cause;
import events.KeyCause;
import events.VoogaEvent;

public class EventTest {

	@Test
	public void testAddingCauses(){
		VoogaEvent e = new VoogaEvent();
		ArrayList<Cause> causes = new ArrayList<>();

		KeyCause tester = new KeyCause("A", null, e);
		causes.add(tester);

		e.addCause(tester);

		assertEquals("Adding causes", 1, e.getCauses().size());
	}

	@Test
	public void testAddingEffects(){
		VoogaEvent e = new VoogaEvent();
		//ArrayList<Effect> effects = new ArrayList<>();


		//		VariableEffect tester = new VariableEffect(e);
		//		effects.add(tester);
		//		
		//		e.addEffect(tester);

		assertEquals("Adding causes", 1, e.getEffects().size());
	}

	@Test
	public void testKeyCause(){
		VoogaEvent v = new VoogaEvent();
		KeyCause temp = new KeyCause("A",null, v);

		temp.setValue(true);

		//assertEquals(true, temp.check(data));
	}
}