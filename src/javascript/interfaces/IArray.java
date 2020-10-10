package javascript.interfaces;


import java.util.Iterator;
import java.util.function.*;

import javascript.*;
import javascript.annotations.*;

public interface IArray<A extends IArray<A>> extends ArrayLike, Iterable<Object>, IReadonlyArray<A>
{
	/**
	 * Copies an item at a specific index within this array to the given range.
	 * <br>
	 * If no arguments are passed, this method fills every item in this array with the first element.
	 * <br>
	 * Behaves like <code>copyWithin(0, 0, length())</code>
	 * @return this
	 */
	@Mutator
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
	@Mutator
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
	@Mutator
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
	@Mutator
	A copyWithin(int target, int start, int end);
	
	/**
	 * Returns the entry iterator for this array
	 * @return The entry iterator for this array
	 */
	default ArrayEntryIterator entries()
	{
		return new ArrayEntryIterator(this);
	}
	
	/**
	 * Fills the entire array with <code>value</code>.
	 * @param value The value to fill this array with
	 * @return this
	 */
	@Mutator
	A fill(Object value);
	
	/**
	 * Fills the entire array with <code>value</code>, starting at <code>start</code>.
	 * @param value The value to fill the array with
	 * @param start The index to start filling <code>value</code>. If <code>start</code>
	 * is negative, it's treated as <code>length - start</code>.
	 * @return this
	 */
	@Mutator
	A fill(Object value, int start);
	
	/**
	 * Fills the entire array with <code>value</code>, starting at <code>start</code>
	 * and ending at <code>end - 1</code>
	 * @param value The value to fill the array with
	 * @param start The index to start filling <code>value</code>. If <code>start</code>
	 * is negative, it's treated as <code>length - start</code>.
	 * @param end The exclusive index to end filling <code>value</code>. If
	 * <code>end</code> is negative, it's treated as <code>length - end</code>.
	 * @return this
	 */
	@Mutator
	A fill(Object value, int start, int end);
	
	/**
	 * Returns an iterator for iterating through the keys of this array
	 * @return An iterator for iterating through the keys of this array
	 */
	default ArrayKeyIterator keys()
	{
		return new ArrayKeyIterator(this);
	}
	
	/**
	 * Removes the last element from the array and returns it.
	 * @return The element that was removed.
	 */
	@Mutator
	@Undefinable
	Object pop();
	
	/**
	 * Adds <code>items</code> to the back of this array, returning the new length
	 * of this array.
	 * @param items The items to push
	 * @return The new length of this array.
	 */
	@Mutator
	int push(Object ...items);
	
	/**
	 * Reverses the elements in this array.
	 * @return this
	 */
	@Mutator
	A reverse();

	/**
	 * Removes the first item in this array, and returns it.
	 * @return The element that was removed.
	 */
	@Undefinable
	Object shift();
	
	/**
	 * Sorts an array.
	 * @return this
	 */
	@Mutator
	A sort();
	
	/**
	 * Sorts an array.
	 * @param compareFn Function used to determine the order of the elements. 
	 * It is expected to return a negative value if first argument is less than 
	 * second argument, zero if they're equal and a positive value otherwise. 
	 * If omitted, the elements are sorted in ascending, ASCII character order.
	 * @return this
	 */
	@Mutator
	A sort(ToIntFunction<CompareInfo> compareFn);
	
	/**
	 * Removes elements from an array and, if necessary, inserts new elements
	 * in their place, returning the deleted elements.
	 * @return
	 */
	@Mutator
	A splice();
	
	/**
	 * Removes elements from an array and, if necessary, inserts new elements
	 * in their place, returning the deleted elements.
	 * @param start
	 * @return The deleted elements.
	 */
	@Mutator
	A splice(int start);
	
	/**
	 * Removes elements from an array and, if necessary, inserts new elements
	 * in their place, returning the deleted elements.
	 * @param start The zero-based location in the array from which to start
	 * removing elements.
	 * @param deleteCount The number of elements to remove.
	 * @return The deleted elements.
	 */
	@Mutator
	A splice(int start, int deleteCount);
	
	/**
	 * Removes elements from an array and, if necessary, inserts new elements
	 * in their place, returning the deleted elements.
	 * @param start The zero-based location in the array from which to start
	 * removing elements.
	 * @param deleteCount The number of elements to remove.
	 * @param items Elements to insert into the array in place of the deleted
	 * elements.
	 * @return The deleted elements.
	 */
	@Mutator
	A splice(int start, int deleteCount, Object ...items);
	
	/**
	 * Adds <code>items</code> to the start of this array.
	 * @param items The items to add the the start of this array
	 * @return The new length of this array
	 */
	@Mutator
	int unshift(Object ...items);
	
	/**
	 * Returns the value iterator for this array.
	 * @return The value iterator for this array.
	 */
	default ArrayValueIterator values()
	{
		return new ArrayValueIterator(this);
	}
	
	/**
	 * Returns an iterator for iterating through this array.
	 * @return An iterator for iterating through this array.
	 */
	default Iterator<Object> iterator()
	{
		return this.values();
	}
	
	
	/**
	 * Sets the item at <code>index</code> in this array.
	 * @param index The index to set <code>value</code>
	 * @param value The value to set at <code>index</code>
	 */
	@Mutator
	void set(int index, Object value);
	
	/**
	 * Deletes the item at <code>index</code> from the array.
	 * @param index The index of the item to delete
	 * @return Whether or not the item was deleted successful.
	 */
	@Mutator
	boolean delete(int index);
}