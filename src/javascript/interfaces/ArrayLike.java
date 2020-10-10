package javascript.interfaces;

import javascript.annotations.*;

import javascript.Global;

public interface ArrayLike
{
	/**
	 * Gets the item at <code>index</code> in this array.
	 * @param index The index of the element to get.
	 * @return {@link Global.undefined} if <code>index</code> is outside
	 * the range of this array.
	 */
	@Undefinable
	Object get(int index);
	
	/**
	 * Gets the length of this array
	 * @return The length of this array
	 */
	int length();
	
	/**
	 * Returns a section of an array.
	 * @param start The start index of the section to return
	 * @param end The exclusive end index of the section to return
	 * @return A shallow copy of this array, starting at <code>start</code>, and
	 * ending at <code>end - 1</code>.
	 */
	ArrayLike slice(int start, int end);
}