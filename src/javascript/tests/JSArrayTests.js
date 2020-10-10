class JSArrayTests {
    
    static run() {
        let start = this.getTimestamp();

        this.testFrozen();
        this.testLength();
        this.testConcat();
        this.testCopyWithin();
        this.testEvery();
        this.testFill();
        this.testFilter();
        this.testFind();
        this.testForeach();
        this.testIncludes();
        this.testIndexOf();
        this.testSort();
        this.testToString();

        console.log(`\nAll tests passed in ${this.getTimestamp() - start}ms`);
    }

    /**
     * Tests immutability with Object.freeze
     */
    static testFrozen() {
        let testArray = new Array();

        testArray.push("Some", Math.PI, "data");

        testArray = Object.freeze(testArray);

        try { testArray.push("moreData"); }
        catch (err) {}

        this.assertEquals(3, testArray.length);
        try { this.assertEquals(false, delete testArray[0]) }
        catch(err) {}

        console.log("Object.freeze passed");
    }

    /**
     * Tests array length with Array.length
     */
    static testLength() {
        let testArray = new Array();

        this.assertEquals(0, testArray.length);
        
        testArray.push("some data");
        
        this.assertEquals(1, testArray.length);
        
        testArray.push("Lots", "of", "data");
        
        this.assertEquals(4, testArray.length);
        
        console.log("Array.length passed");
    }

    static testConcat() {
        let testArray = new Array();
        
        testArray.push(1232, 392); // add some data
        
        let concat = testArray.concat("More data");
        
        this.assertEquals("More data", concat[2]);
        
        // what about adding another array?
        let other = new Array("Even more", "data");
        let otherConcat = testArray.concat(other);
        
        this.assertEquals("data", otherConcat[3]);
        
        console.log("Array.concat() passed");
    }

    static testCopyWithin() {
        let testArray = new Array();

        testArray.push(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        testArray.copyWithin(2, 1);

        this.assertEquals(0, testArray[0]);
        this.assertEquals(1, testArray[1]);
        this.assertEquals(1, testArray[2]);
        this.assertEquals(2, testArray[3]);
        this.assertEquals(3, testArray[4]);
        this.assertEquals(4, testArray[5]);
        this.assertEquals(5, testArray[6]);
        this.assertEquals(6, testArray[7]);
        this.assertEquals(7, testArray[8]);
        this.assertEquals(8, testArray[9]);


        testArray = new Array();
        testArray.push(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        testArray.copyWithin(0, 7, 8);

        this.assertEquals(7, testArray[0]);
        this.assertEquals(1, testArray[1]);
        this.assertEquals(2, testArray[2]);
        this.assertEquals(3, testArray[3]);
        this.assertEquals(4, testArray[4]);
        this.assertEquals(5, testArray[5]);
        this.assertEquals(6, testArray[6]);
        this.assertEquals(7, testArray[7]);
        this.assertEquals(8, testArray[8]);
        this.assertEquals(9, testArray[9]);
        
        console.log("Array.copyWithin() passed");
    }

    static testEvery() {
        let testArray = new Array();

        testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        this.assertEquals(false, testArray.every(item => item < 10));
        this.assertEquals(true, testArray.every(item => item  < 11));

        console.log("Array.every() passed");
    }

    static testFill() {
        let testArray = new Array();

        testArray.push("apple", true, 69);

        // fill with "pear" starting at index of 1
        testArray.fill("pear", 1);

        this.assertEquals("apple", testArray[0]);
        this.assertEquals("pear", testArray[1]);
        this.assertEquals("pear", testArray[2]);

        console.log("Array.fill() passed");
    }

    static testFilter() {
        let testArray = new Array();
        
        testArray.push("John", "Bob", "Mary", "Linda");

        this.assertEquals(true, testArray.filter(item => item.length > 2).length == testArray.length);
        
        console.log("Array.filter() passed");
    }

    static testFind() {
        let testArray = new Array();
		
		testArray.push("John", "Bob", "Mary", "Linda");

        this.assertEquals("Bob", testArray.find(item => item.length < 4));
		
		console.log("Array.find() passed");
    }

    static testForeach() {
        let testArray = new Array();
		
		testArray.push("John", "Bob", "Mary", "Linda");
		
        let length = 0;
        
        testArray.forEach(() => length++);
		
		this.assertEquals(true, length == testArray.length);
		
		console.log("Array.foreach() passed");
    }

    static testIncludes() {
		let testArray = new Array();
		
		testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 28120);
		
		this.assertEquals(true, testArray.includes(28120));
		
		console.log("Array.includes() passed");
	}

    static testIndexOf() {
        let testArray = new Array();
        
        testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 28120);
        
        this.assertEquals(10, testArray.indexOf(28120));
        
        console.log("Array.indexOf() passed");
    }

    static testSort() {
        let testArray = new Array();
        testArray.push(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        testArray.sort((a, b) => b - a);
        
        this.assertEquals(9, testArray[0]);
        this.assertEquals(8, testArray[1]);
        this.assertEquals(7, testArray[2]);
        this.assertEquals(6, testArray[3]);
        this.assertEquals(5, testArray[4]);
        this.assertEquals(4, testArray[5]);
        this.assertEquals(3, testArray[6]);
        this.assertEquals(2, testArray[7]);
        this.assertEquals(1, testArray[8]);
        
        // inverse works, but what about inversing the inverse
        testArray.sort((a, b) => a - b);

        this.assertEquals(1, testArray[0]);
        this.assertEquals(2, testArray[1]);
        this.assertEquals(3, testArray[2]);
        this.assertEquals(4, testArray[3]);
        this.assertEquals(5, testArray[4]);
        this.assertEquals(6, testArray[5]);
        this.assertEquals(7, testArray[6]);
        this.assertEquals(8, testArray[7]);
        this.assertEquals(9, testArray[8]);
        
        // make sure calling the same sort doesn't change the array
        testArray.sort((a, b) => a - b);

        this.assertEquals(1, testArray[0]);
        this.assertEquals(2, testArray[1]);
        this.assertEquals(3, testArray[2]);
        this.assertEquals(4, testArray[3]);
        this.assertEquals(5, testArray[4]);
        this.assertEquals(6, testArray[5]);
        this.assertEquals(7, testArray[6]);
        this.assertEquals(8, testArray[7]);
        this.assertEquals(9, testArray[8]);
        
        console.log("Array.sort() passed");
    }

    static testToString() {
        let testArray = new Array();
        testArray.push("nice", 1, true);
        
        let string = testArray.toString();
        
        // Are multiple types concatenated properly?
        this.assertEquals("nice,1,true", string);
        
        // What about undefined?
        testArray.push(undefined);
        string = testArray.toString();
        
        this.assertEquals("nice,1,true,", string);
        
        // What about null?
        testArray.push(null);
        string = testArray.toString();
        
        this.assertEquals("nice,1,true,,", string);
        
        console.log("Array.toString() passed");
    }

    static assertEquals(expected, result, epsilon) {
        if (typeof expected === "number") {
            if (Math.abs(expected - result) > epsilon) {
                throw new Error(`Assertion failed: Got '${result}', expected '${expected}'`);
            }
        }
        else if (expected !== result) throw new Error(`Assertion failed: Got '${result}', expected '${expected}'`);
    }

    static getTimestamp() {
        return Date.now();
    }
}

JSArrayTests.run();