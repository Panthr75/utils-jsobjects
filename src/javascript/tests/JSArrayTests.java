package javascript.tests;

import javascript.Global;
import javascript.JSArray;

class JSArrayTests 
{
	public static void main(String[] args)
	{
		testToString();
		testSort();
	}
	
	public static void testToString()
	{
		JSArray testArray = new JSArray();
		testArray.push("nice", 1, true);
		
		String string = testArray.toString();
		
		// Are multiple types concatenated properly?
		assertEquals("nice,1,true", string);
		
		// What about undefined?
		testArray.push(Global.undefined);
		string = testArray.toString();
		
		assertEquals("nice,1,true,", string);
		System.out.println(string);
	}
	
	public static void testSort()
	{
		JSArray testArray = new JSArray();
		testArray.push(1, 2, 3);
		
		testArray.sort((info) -> (Integer)info.b - (Integer)info.a);
		
		assertEquals(3, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(1, testArray.get(2));
		
		// inverse works, but what about inversing the inverse
		testArray.sort((info) -> (Integer)info.a - (Integer)info.b);

		assertEquals(1, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(3, testArray.get(2));
		
		// make sure calling the same sort doesn't change the array
		testArray.sort((info) -> (Integer)info.a - (Integer)info.b);

		assertEquals(1, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(3, testArray.get(2));
	}
	
	private static void assertEquals(String expected, String result)
	{
		if (!expected.contentEquals(result))
			throw new AssertionError("Got \"" + result + "\", expected \"" + expected + "\"");
	}
	
	public static void assertEquals(int expected, Object result)
	{
		Class<?> objClass = result.getClass();
		if ((objClass == int.class && objClass != Integer.class) || expected != (Integer)result)
			throw new AssertionError("Got '" + String.valueOf(result) + "', expected '" + String.valueOf(expected) + "'");
	}
}