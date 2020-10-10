package javascript.interfaces;

/**
 * Represents an array that can be spread in the IArray.concat method
 * @author Josh
 * @version 10th October, 2020.
 */
public interface ConcatArray<A extends ConcatArray<A>> extends ArrayLike
{
	/**
	 * Converts each item in this array to a string, and these strings are concatenated,
	 * separated by the default separator <code>","</code>.
	 * @return Each item in this array converted to a string, concatenated, and separated
	 * by the default separator <code>","</code>.
	 */
	String join();
	
	/**
	 * Converts each item in this array to a string, and these strings are concatenated,
	 * separated by <code>separator</code>.
	 * @param separator The separator to use.
	 * @return Each item in this array converted to a string, concatenated, and separated
	 * by <code>separator</code>.
	 */
	String join(String seperator);
	
	/**
	 * Returns a section of an array.
	 * @return A shallow copy of this array
	 */
	A slice();
	
	/**
	 * Returns a section of an array.
	 * @param start The start index of the section to return
	 * @return A shallow copy of this array, starting at <code>start</code>.
	 */
	A slice(int start);
	
	/**
	 * Returns a section of an array.
	 * @param start The start index of the section to return
	 * @param end The exclusive end index of the section to return
	 * @return A shallow copy of this array, starting at <code>start</code>, and
	 * ending at <code>end - 1</code>.
	 */
	A slice(int start, int end);
}