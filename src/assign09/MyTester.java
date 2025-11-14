package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTester {
	private static BetterDynamicArray threeEvents;
	private static AudioEvent e1, e2, e3, e4, e5, e6;
	
	private static AudioEvent makeEvent(int time) {
		return new VolumeEvent(time, 1, 1);
	}
	
	@BeforeEach
	public void setup(){
		e1 = makeEvent(1);
		e2 = makeEvent(2);
		e3 = makeEvent(3);
		e4 = makeEvent(4);
		e5 = makeEvent(5);
		e6 = makeEvent(6);
		threeEvents = new BetterDynamicArray();
		threeEvents.add(e1);
		threeEvents.add(e2);
		threeEvents.add(e3);
		System.out.println(threeEvents.size());
	}

	@Test
	void testA() {
		System.out.println(threeEvents.size());
		//assertEquals(3, threeEvents.size(), "Calling getElement changed the size of dynamic array");
	}

}
