package javascript.interfaces;


import java.util.Iterator;
import java.util.function.*;

import javascript.*;
import javascript.annotations.*;

public interface IArray<A extends IArray<A>> extends ArrayLike, Iterable<Object>
{
	//TODO: Finish documentation
	
	/**
	 * Returns a new array containing all items in this array, plus the given items.
	 * @param items The items
	 * @return A new array containing all items in this array, plus the given items.
	 */
	A concat(Object ...items);
	
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
	 * Returns whether <code>callback</code> returns <code>true</code> for
	 * every item in this array.
	 * @param callbackfn The callback function/interface
	 * @return whether <code>callback</code> returns <code>true</code> for
	 * every item in this array.
	 * @throws IllegalArgumentException if <code>callbackfn</code> is null.
	 */
	boolean every(Predicate<ArrayEntry> callbackfn);
	
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
	 * Returns a new array, filtered by <code>callbackfn</code>.
	 * @param callbackfn The callback function/interface
	 * @return A new array, filtered by <code>callbackfn</code>.
	 */
	A filter(Predicate<ArrayEntry> callbackfn);
	
	/**
	 * Returns the item in this array where <code>predicate</code> returned
	 * <code>true</code>. If <code>predicate</code> never returned <code>true</code>,
	 * then {@link Global.undefined} is returned.
	 * @param predicate The predicate to find the item in the array.
	 * @return The item in this array where <code>predicate</code> returned
	 * <code>true</code>. If <code>predicate</code> never returned <code>true</code>,
	 * then {@link Global.undefined} is returned.
	 */
	@Undefinable
	Object find(Predicate<ArrayEntry> predicate);
	
	/**
	 * Returns the index of the item in this array where <code>predicate</code>
	 * returned <code>true</code>. If <code>predicate</code> never returned
	 * <code>true</code>, then <code>-1</code> is returned.
	 * @param predicate The predicate to find the item in the array.
	 * @return The index of the item in this array where <code>predicate</code>
	 * returned <code>true</code>. If <code>predicate</code> never returned
	 * <code>true</code>, then <code>-1</code> is returned.
	 */
	int findIndex(Predicate<ArrayEntry> predicate);
	
	/**
	 * Loops through each item in this array, invoking <code>callbackfn</code>.
	 * @param callbackfn The callback function/interface
	 */
	// had to be foreach because forEach is specified in iterator
	void foreach(Consumer<ArrayEntry> callbackfn);
	
	/**
	 * Returns whether <code>searchElement</code> is inside this array. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-samevaluezero">SameValueZero</a> comparison.
	 * @param searchElement The element to search for
	 * @return Whether <code>searchElement</code> is inside this array.
	 */
	boolean includes(Object searchElement);
	
	/**
	 * Returns whether <code>searchElement</code> is inside this array, starting
	 * the search at <code>fromIndex</code>. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-samevaluezero">SameValueZero</a> comparison.
	 * @param searchElement The element to search for
	 * @param fromIndex The index to begin the search at
	 * @return Whether <code>searchElement</code> is inside this array.
	 */
	boolean includes(Object searchElement, int fromIndex);
	
	/**
	 * Returns the index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-strict-equality-comparison">Strict equality</a>
	 * comparison.
	 * @param searchElement The element to search for
	 * @return The index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>.
	 */
	int indexOf(Object searchElement);
	
	/**
	 * Returns the index of <code>searchElement</code> inside this array, starting
	 * the search at <code>fromIndex</code>. If <code>searchElement</code> isn't contained
	 * inside this array, this method returns <code>-1</code>. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-strict-equality-comparison">Strict equality</a>
	 * comparison.
	 * @param searchElement The element to search for
	 * @param fromIndex The index to begin the search at
	 * @return The index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>.
	 */
	int indexOf(Object searchElement, int fromIndex);
	
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
	 * Returns an iterator for iterating through the keys of this array
	 * @return An iterator for iterating through the keys of this array
	 */
	default ArrayKeyIterator keys()
	{
		return new ArrayKeyIterator(this);
	}
	
	/**
	 * Returns the last index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-strict-equality-comparison">Strict equality</a>
	 * comparison.
	 * @param searchElement The element to search for
	 * @return The las index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>.
	 */
	int lastIndexOf(Object searchElement);
	
	/**
	 * Returns the last index of <code>searchElement</code> inside this array, ending
	 * the search at <code>toIndex</code>. If <code>searchElement</code> isn't contained
	 * inside this array, this method returns <code>-1</code>. Items are compared using the
	 * <a href="https://tc39.es/ecma262/#sec-strict-equality-comparison">Strict equality</a>
	 * comparison.
	 * @param searchElement The element to search for
	 * @param toIndex The index to end the search at (exclusive)
	 * @return The last index of <code>searchElement</code> inside this array. If
	 * <code>searchElement</code> isn't contained inside this array, this method
	 * returns <code>-1</code>.
	 */
	int lastIndexOf(Object searchElement, int toIndex);
	
	A map(Function<ArrayEntry, Object> callbackfn);
	
	@Undefinable
	Object pop();
	
	int push(Object ...items);
	
	Object reduce(Function<ArrayReduceEntry<Object>, Object> callbackfn);
	
	<T> T reduce(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue);
	
	Object reduceRight(Function<ArrayReduceEntry<Object>, Object> callbackfn);
	
	<T> T reduceRight(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue);
	
	@Mutator
	A reverse();

	@Undefinable
	Object shift();
	
	A slice();
	
	A slice(int start);
	
	A slice(int start, int end);
	
	boolean some(Predicate<ArrayEntry> callbackfn);
	
	@Mutator
	void sort();
	
	@Mutator
	void sort(ToIntFunction<CompareInfo> compareFn);
	
	@Mutator
	A splice();
	
	@Mutator
	A splice(int start);
	
	@Mutator
	A splice(int start, int deleteCount);
	
	@Mutator
	A splice(int start, int deleteCount, Object[] items);
	
	/**
	 * Converts this array to a locale string.
	 * @return This array as a localized string
	 */
	String toLocaleString();
	
	/**
	 * Converts this array to a string
	 * @return This array as a string
	 */
	String toString();
	
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