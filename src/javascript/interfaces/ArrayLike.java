package javascript.interfaces;

import javascript.annotations.*;

import javascript.Global;

public interface ArrayLike
{
	ArrayLike slice(int start, int end);
	
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
}