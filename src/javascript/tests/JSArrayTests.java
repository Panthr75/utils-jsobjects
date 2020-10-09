package javascript.tests;

import java.util.Date;

import javascript.*;

class JSArrayTests 
{
	public static void main(String[] args)
	{
		long start = new Date().getTime();
		
		testFrozen();
		testLength();
		testConcat();
		//testSort();
		testToString();
		
		System.out.println("\nAll tests passed in " + String.valueOf(new Date().getTime() - start) + "ms");
	}
	
	public static void testFrozen()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("Some", Math.PI, "data");
		
		testArray = JSObject.freeze(testArray);
		
		testArray.push("more data");
		
		assertEquals(3, testArray.length());
		assertEquals(false, testArray.delete(0));
		
		testArray.set(1, "I wanna eat the pi");
		
		assertEquals(Math.PI, testArray.get(1), 0.0001);
		
		System.out.println("JSObject.freeze(JSArray) passed");
	}
	
	public static void testLength()
	{
		JSArray testArray = new JSArray();
		
		assertEquals(0, testArray.length());
		
		testArray.push("some data");
		
		assertEquals(1, testArray.length());
		
		testArray.push("Lots", "of", "data");
		
		assertEquals(4, testArray.length());
		
		System.out.println("JSArray.length passed");
	}
	
	public static void testConcat()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(1232, 392); // add some data
		
		JSArray concat = testArray.concat("More data");
		
		assertEquals("More data", (String)concat.get(2));
		
		// what about adding another array?
		JSArray other = new JSArray("Even more", "data");
		JSArray otherConcat = testArray.concat(other);
		
		assertEquals("data", (String)otherConcat.get(3));
		
		System.out.println("JSArray.concat() passed");
	}
	
	public static void testSort()
	{
		JSArray testArray = new JSArray();
		testArray.push(1, 2, 3);
		
		testArray.sort(info -> (Integer)info.b - (Integer)info.a);
		
		assertEquals(3, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(1, testArray.get(2));
		
		// inverse works, but what about inversing the inverse
		testArray.sort(info -> (Integer)info.a - (Integer)info.b);

		assertEquals(1, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(3, testArray.get(2));
		
		// make sure calling the same sort doesn't change the array
		testArray.sort(info -> (Integer)info.a - (Integer)info.b);

		assertEquals(1, testArray.get(0));
		assertEquals(2, testArray.get(1));
		assertEquals(3, testArray.get(2));
		
		System.out.println("JSArray.sort() passed");
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
		
		// What about null?
		testArray.push((Object)null);
		string = testArray.toString();
		
		assertEquals("nice,1,true,,", string);
		
		System.out.println("JSArray.toString() passed");
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
	
	public static void assertEquals(boolean expected, Object result)
	{Class<?> objClass = result.getClass();
	if ((objClass == boolean.class && objClass != Boolean.class) || expected != (Boolean)result)
		throw new AssertionError("Got '" + String.valueOf(result) + "', expected '" + String.valueOf(expected) + "'");
	}
	
	public static void assertEquals(double expected, Object result, double epsilon)
	{
		Class<?> objClass = result.getClass();
		if ((objClass == double.class && objClass != Double.class) || Math.abs(expected - (Double)result) > epsilon)
			throw new AssertionError("Got '" + String.valueOf(result) + "', expected '" + String.valueOf(expected) + "'");
	}
}