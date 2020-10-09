class JSArrayTests {
    
    static run() {
        let start = Date.now();

        this.testFrozen();
        this.testLength();
        this.testConcat();
        //this.testSort();
        this.testToString();

        console.log(`\nAll tests passed in ${Date.now() - start}ms`);
    }

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

    static testSort() {
        let testArray = new Array();
		testArray.push(1, 2, 3);
		
		testArray.sort((a, b) => b - a);
		
		this.assertEquals(3, testArray[0]);
		this.assertEquals(2, testArray[1]);
		this.assertEquals(1, testArray[2]);
		
		// inverse works, but what about inversing the inverse
		testArray.sort((a, b) => a - b);

		this.assertEquals(1, testArray[0]);
		this.assertEquals(2, testArray[1]);
		this.assertEquals(3, testArray[2]);
		
		// make sure calling the same sort doesn't change the array
		testArray.sort((a, b) => a - b);

		this.assertEquals(1, testArray[0]);
		this.assertEquals(2, testArray[1]);
		this.assertEquals(3, testArray[2]);
		
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
}

JSArrayTests.run();