package javascript.interfaces;

/**
 * Represents a generic IArray
 * @author Josh
 * @version 8th October, 2020
 *
 * @param <A> The inheriting array
 * @param <T> The type this array holds
 */
public interface GenericIArray<A extends GenericIArray<A, T>, T> 
{	
	/**
	 * Returns a new array containing all items in this array, plus the given items.
	 * @param items The items
	 * @return A new array containing all items in this array, plus the given items.
	 */
	@SuppressWarnings("unchecked") // handled by implementor
	A concat(T ...items);
	
	/**
	 * Copies an item at a specific index within this array to the given range.
	 * <br>
	 * If no arguments are passed, this method fills every item in this array with the first element.
	 * <br>
	 * Behaves like <code>copyWithin(0, 0, length())</code>
	 * @return this
	 */
	A copyWithin();
	
	/**
	 * Copies an item at a specific index within this array to the given range.
	 * <br>
	 * If only one argument is passed, this method fills every item in this array with the 
	 * element at the index of <code>target</code>
	 * <br>
	 * Behaves like <code>copyWithin(target, 0, length())</code>
	 * @param target The target index to fill the array with. If <code>target</code> is negative,
	 * it's treated as <code>length - target</code>.
	 * @return this
	 */
	A copyWithin(int target);
	
	/**
	 * Copies an item at a specific index within this array to the given range.
	 * <br>
	 * If only one argument is passed, this method fills every item in this array with the 
	 * element at the index of <code>target</code>
	 * <br>
	 * Behaves like <code>copyWithin(target, start, length())</code>
	 * @param target The target index to fill the array with. If <code>target</code> is negative,
	 * it's treated as <code>length - target</code>.
	 * @param start The index to start copying the item at index <code>target</code> to. If
	 * <code>start</code> is negative, it's treated as <code>length - start</code>.
	 * @return this
	 */
	A copyWithin(int target, int start);
	
	/**
	 * Copies an item at a specific index within this array to the given range.
	 * <br>
	 * If only one argument is passed, this method fills every item in this array with the 
	 * element at the index of <code>target</code>
	 * @param target The target index to fill the array with. If <code>target</code> is negative,
	 * it's treated as <code>length - target</code>.
	 * @param start The index to start copying the item at index <code>target</code> to. If
	 * <code>start</code> is negative, it's treated as <code>length - start</code>.
	 * @param end The exclusive index to end copying the item at index <code>target</code> to.
	 * If <code>end</code> is negative, it's treated as <code>length - end</code>.
	 * @return this
	 */
	A copyWithin(int target, int start, int end);
	
//	/**
//	 * Returns the entry iterator for this array
//	 * @return The entry iterator for this array
//	 */
//	Iterator<GenericArrayEntry<T>> entries();
	
	A splice();
	A splice(int start);
	A splice(int start, int deleteCount);
	@SuppressWarnings("unchecked") // handled by implementor
	A splice(int start, int deleteCount, T ...items);
}