package javascript.tests;

import java.util.function.Consumer;

import javascript.*;
import javascript.exceptions.UpdatePropertyException;

class JSArrayTests extends Tester
{
	@Test(testName = "JSObject.freeze(JSArray)")
	public void testFrozen()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("Some", Math.PI, "data");
		
		testArray = JSObject.freeze(testArray);
		
		try { testArray.push("more data"); }
		catch (UpdatePropertyException e) {}
		
		this.assertEquals(3, testArray.length());
		try { this.assertEquals(false, testArray.delete(0)); }
		catch (UpdatePropertyException e) {}
		
		try { testArray.set(1, "I wanna eat the pi"); }
		catch (UpdatePropertyException e) {}
		
		this.assertEquals(Math.PI, testArray.get(1), 0.0001);
	}

	@Test(testName = "JSArray.length")
	public void testLength()
	{
		JSArray testArray = new JSArray();
		
		this.assertEquals(0, testArray.length());
		
		testArray.push("some data");
		
		this.assertEquals(1, testArray.length());
		
		testArray.push("Lots", "of", "data");
		
		this.assertEquals(4, testArray.length());
		
		testArray.setLength(1);
		
		this.assertEquals(testArray, "some data");
		
		testArray.setLength(2);
		
		this.assertEquals(testArray, "some data", Global.undefined);
	}

	@Test(testName = "JSArray.concat()")
	public void testConcat()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(1232, 392); // add some data
		
		JSArray concat = testArray.concat("More data");
		
		this.assertEquals("More data", concat.get(2));
		
		// what about adding another array?
		JSArray other = new JSArray("Even more", "data");
		JSArray otherConcat = testArray.concat(other);
		
		this.assertEquals("data", otherConcat.get(3));
	}

	@Test(testName = "JSArray.copyWithin()")
	public void testCopyWithin()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        testArray.copyWithin(2, 1);
        
        this.assertEquals(0, testArray.get(0));
        this.assertEquals(1, testArray.get(1));
        this.assertEquals(1, testArray.get(2));
        this.assertEquals(2, testArray.get(3));
        this.assertEquals(3, testArray.get(4));
        this.assertEquals(4, testArray.get(5));
        this.assertEquals(5, testArray.get(6));
        this.assertEquals(6, testArray.get(7));
        this.assertEquals(7, testArray.get(8));
        this.assertEquals(8, testArray.get(9));
        
        testArray = new JSArray();
		testArray.push(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		testArray.copyWithin(0, 7, 8);
        
		this.assertEquals(7, testArray.get(0));
		this.assertEquals(1, testArray.get(1));
		this.assertEquals(2, testArray.get(2));
		this.assertEquals(3, testArray.get(3));
		this.assertEquals(4, testArray.get(4));
		this.assertEquals(5, testArray.get(5));
		this.assertEquals(6, testArray.get(6));
		this.assertEquals(7, testArray.get(7));
		this.assertEquals(8, testArray.get(8));
		this.assertEquals(9, testArray.get(9));
	}

	@Test(testName = "JSArray.every()")
	public void testEvery()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		this.assertEquals(false, testArray.every(entry -> (int)entry.item < 10));
		this.assertEquals(true, testArray.every(entry -> (int)entry.item < 11));
	}

	@Test(testName = "JSArray.fill()")
	public void testFill()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("apple", true, 69);

        // fill with "pear" starting at index of 1
		testArray.fill("pear", 1);
		
		this.assertEquals("apple", testArray.get(0));
		this.assertEquals("pear", testArray.get(1));
		this.assertEquals("pear", testArray.get(2));
	}

	@Test(testName = "JSArray.filter()")
	public void testFilter()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("John", "Bob", "Mary", "Linda");

		this.assertEquals(true, testArray.filter(info -> ((String)info.item).length() > 2).length() == testArray.length());
	}

	@Test(testName = "JSArray.find()")
	public void testFind()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("John", "Bob", "Mary", "Linda");

		this.assertEquals("Bob", testArray.find(info -> ((String)info.item).length() < 4));
	}

	@Test(testName = "JSArray.foreach()")
	public void testForeach()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("John", "Bob", "Mary", "Linda");
		
		JSArrayTests.ForEachTest test = new ForEachTest(4);
		testArray.foreach(test);
		
		this.assertEquals(true, test.passed());
	}
	
	private class ForEachTest implements Consumer<ArrayEntry>
	{
		private final int expectedLength;
		private int length;
		
		public ForEachTest(int expectedLength)
		{
			this.expectedLength = expectedLength;
		}

		public void accept(ArrayEntry info) 
		{
			this.length++;
		}
		
		public boolean passed()
		{
			return this.expectedLength == this.length;
		}
	}

	@Test(testName = "JSArray.includes()")
	public void testIncludes()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 28120);
		
		assertEquals(true, testArray.includes(28120));
	}

	@Test(testName = "JSArray.indexOf()")
	public void testIndexOf()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 28120);
		
		this.assertEquals(10, testArray.indexOf(28120));
	}

	@Test(testName = "JSArray.join()")
	public void testJoin()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("Item", true, "bob", 3.14D);
		
		this.assertEquals("Item,true,bob,3.14", testArray.join());
		
		this.assertEquals("\"Item\", \"true\", \"bob\", \"3.14\"", "\"" + testArray.join("\", \"") + "\"");
	}

	@Test(testName = "JSArray.lastIndexOf()")
	public void testLastIndexOf()
	{
		JSArray testArray = new JSArray();
		
		testArray.push(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
		
		this.assertEquals(2, testArray.lastIndexOf(1));
		
		testArray.push(1);
		
		this.assertEquals(10, testArray.lastIndexOf(1));
	}

	@Test(testName = "JSArray.map()")
	public void testMap()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("banana", "grape", "orange", "test");
		
		// map to a JSString
		JSArray mappedArray = testArray.map(info -> new JSString(info.item));
		
		this.assertEquals(true, mappedArray.get(0) instanceof JSString);
		this.assertEquals(true, mappedArray.get(1) instanceof JSString);
		this.assertEquals(true, mappedArray.get(2) instanceof JSString);
		this.assertEquals(true, mappedArray.get(3) instanceof JSString);
		
		// make sure original array was not mutated
		
		this.assertEquals(true, testArray.get(0) instanceof String);
		this.assertEquals(true, testArray.get(1) instanceof String);
		this.assertEquals(true, testArray.get(2) instanceof String);
		this.assertEquals(true, testArray.get(3) instanceof String);
	}
	
	@Test(testName = "JSArray.pop()")
	public void testPop()
	{
		JSArray testArray = new JSArray();
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		this.assertEquals(9, testArray.pop());
		this.assertEquals(8, testArray.length());
	}
	
	@Test(testName = "JSArray.push()")
	public void testPush()
	{
		JSArray testArray = new JSArray();
		testArray.push(2, 4, 6, 8, 10, 12, 14, 16, 18);
		
		this.assertEquals(9, testArray.length());
		
		testArray.push(20);
		
		this.assertEquals(10, testArray.length());
		
		this.assertEquals(testArray, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20);
	}
	
	@Test(testName = "JSArray.reduce()")
	public void testReduce()
	{
		JSArray testArray = new JSArray();
		testArray.push(2, 4, 6, 8, 10, 12, 14, 16, 18);
		
		int total = testArray.reduce(info -> info.accumulator + ((int)info.value), 0);
		
		this.assertEquals(90, total);
	}

	@Test(testName = "JSArray.reduceRight()")
	public void testReduceRight()
	{
		JSArray testArray = new JSArray();
		testArray.push("z", "y", "x", "w", "v", "u", "t", "s", "r", "q", "p", "o", "n", "m",
				"l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
		
		String alphabet = testArray.reduceRight(info -> info.accumulator + ((String)info.value), "");
		
		this.assertEquals("abcdefghijklmnopqrstuvwxyz", alphabet);
	}
	
	@Test(testName = "JSArray.reverse()")
	public void testReverse()
	{
		JSArray testArray = new JSArray();
		testArray.push("z", "y", "x", "w", "v", "u", "t", "s", "r", "q", "p", "o", "n", "m",
				"l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
		testArray.reverse();
		
		String alphabet = testArray.join("");
		
		this.assertEquals("abcdefghijklmnopqrstuvwxyz", alphabet);
	}
	
	@Test(testName = "JSArray.shift()")
	public void testShift()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("King of Hearts", "King of Spades", "King of Diamonds", "King of Clubs");
		
		this.assertEquals("King of Hearts", testArray.shift());
		this.assertEquals(3, testArray.length());
	}
	
	@Test(testName = "JSArray.slice()")
	public void testSlice()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("King of Hearts", "King of Spades", "King of Diamonds", "King of Clubs");
		
		JSArray slicedArray = testArray.slice();
		
		// make sure object references aren't the same
		this.assertEquals(false, testArray == slicedArray);
		
		// make sure slicing a section works
		slicedArray = testArray.slice(1);
		
		this.assertEquals(3, slicedArray.length());
		this.assertEquals(slicedArray, "King of Spades", "King of Diamonds", "King of Clubs");
		
		// remove elements at index 1 and 2
		slicedArray = testArray.slice(1, 3);
		
		this.assertEquals(2, slicedArray.length());
		this.assertEquals(slicedArray, "King of Spades", "King of Diamonds");
	}
	
	@Test(testName = "JSArray.some()")
	public void testSome()
	{
		JSArray testArray = new JSArray();
		
		testArray.push("King of Hearts", "King of Spades", "King of Diamonds", "King of Clubs");
		
		this.assertEquals(true, testArray.some(info -> ((String)info.item).contains("Clubs")));
	}

	@Test(testName = "JSArray.sort()")
	public void testSort()
	{
		JSArray testArray = new JSArray();
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		testArray.sort(info -> (Integer)info.b - (Integer)info.a);

		this.assertEquals(9, testArray.get(0));
		this.assertEquals(8, testArray.get(1));
		this.assertEquals(7, testArray.get(2));
		this.assertEquals(6, testArray.get(3));
		this.assertEquals(5, testArray.get(4));
		this.assertEquals(4, testArray.get(5));
		this.assertEquals(3, testArray.get(6));
		this.assertEquals(2, testArray.get(7));
		this.assertEquals(1, testArray.get(8));
		
		// inverse works, but what about inversing the inverse
		testArray.sort(info -> (Integer)info.a - (Integer)info.b);

		this.assertEquals(1, testArray.get(0));
		this.assertEquals(2, testArray.get(1));
		this.assertEquals(3, testArray.get(2));
		this.assertEquals(4, testArray.get(3));
		this.assertEquals(5, testArray.get(4));
		this.assertEquals(6, testArray.get(5));
		this.assertEquals(7, testArray.get(6));
		this.assertEquals(8, testArray.get(7));
		this.assertEquals(9, testArray.get(8));
		
		// make sure calling the same sort doesn't change the array
		testArray.sort(info -> (Integer)info.a - (Integer)info.b);

		this.assertEquals(1, testArray.get(0));
		this.assertEquals(2, testArray.get(1));
		this.assertEquals(3, testArray.get(2));
		this.assertEquals(4, testArray.get(3));
		this.assertEquals(5, testArray.get(4));
		this.assertEquals(6, testArray.get(5));
		this.assertEquals(7, testArray.get(6));
		this.assertEquals(8, testArray.get(7));
		this.assertEquals(9, testArray.get(8));
	}
	
	@Test(testName = "JSArray.splice()")
	public void testSplice()
	{
		JSArray testArray = new JSArray();
		testArray.push(1, 2, 3, 4, 5);
		
		JSArray splicedArray = testArray.splice();
		
		this.assertEquals(0, splicedArray.length());
		this.assertEquals(testArray, 1, 2, 3, 4, 5);
		
		// should remove all items from testArray
		// splicedArray should contain all items in testArray
		splicedArray = testArray.splice(0);
		
		this.assertEquals(0, testArray.length());
		this.assertEquals(splicedArray, 1, 2, 3, 4, 5);

		testArray.push(1, 2, 3, 4, 5);

		splicedArray = testArray.splice(0, 1);
		
		this.assertEquals(testArray, 2, 3, 4, 5);
		this.assertEquals(splicedArray, 1);
		
		testArray = splicedArray.concat(testArray);
		
		splicedArray = testArray.splice(0, 1, 0, 1);
		
		this.assertEquals(testArray, 0, 1, 2, 3, 4, 5);
		this.assertEquals(splicedArray, 1);
	}

	@Test(testName = "JSArray.toString()")
	public void testToString()
	{
		JSArray testArray = new JSArray();
		testArray.push("nice", 1, true);
		
		String string = testArray.toString();
		
		// Are multiple types concatenated properly?
		this.assertEquals("nice,1,true", string);
		
		// What about undefined?
		testArray.push(Global.undefined);
		string = testArray.toString();
		
		this.assertEquals("nice,1,true,", string);
		
		// What about null?
		testArray.push((Object)null);
		string = testArray.toString();
		
		this.assertEquals("nice,1,true,,", string);
	}
	
	@Test(testName = "JSArray.unshift()")
	public void testUnshift()
	{
		JSArray testArray = new JSArray();
		testArray.push("is", "cool");
		
		// insert "Java" at front
		testArray.unshift("Java");
		
		this.assertEquals("Java is cool", testArray.join(" "));
	}
	
	public void assertEquals(JSArray result, Object ...expected)
	{
		int len = expected.length;
		this.assertEquals(len, result.length());
		for (int index = 0; index < len; index++)
			this.assertEquals(expected[index], result.get(index));
	}
}