package javascript.interfaces;

import java.util.function.*;

import javascript.*;
import javascript.annotations.*;

/**
 * Represents a read-only array
 * @author Josh
 * @version 10th October, 2020
 */
public interface IReadonlyArray<A extends IReadonlyArray<A>> extends ArrayLike
{
	/**
	 * Returns a new array containing all items in this array, plus the given items.
	 * @param items The items
	 * @return A new array containing all items in this array, plus the given items.
	 */
	A concat(Object ...items);
	
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
	
	/**
	 * Returns a new array, mapping the values from this array to the values returned
	 * from <code>callbackfn</code>.
	 * @param callbackfn The function to map the values
	 * @return A new array of mapped values
	 */
	A map(Function<ArrayEntry, Object> callbackfn);
	
	/**
	 * Calls the specified callback function for all the elements in an array.
	 * The return value of the callback function is the accumulated result,
	 * and is provided as an argument in the next call to the callback function.
	 * @param callbackfn The reduce method calls the <code>callbackfn</code> function one 
	 * time for each element in the array.
	 * @return The accumulated value
	 */
	Object reduce(Function<ArrayReduceEntry<Object>, Object> callbackfn);
	
	/**
	 * Calls the specified callback function for all the elements in an array.
	 * The return value of the callback function is the accumulated result,
	 * and is provided as an argument in the next call to the callback function.
	 * @param callbackfn The reduce method calls the <code>callbackfn</code> function one 
	 * time for each element in the array.
	 * @param initialValue The initial accumulated value
	 * @return The accumulated value
	 */
	<T> T reduce(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue);
	
	/**
	 * Calls the specified callback function for all the elements in an array, in
	 * descending order. The return value of the callback function is the accumulated result,
	 * and is provided as an argument in the next call to the callback function.
	 * @param callbackfn The reduceRight method calls the <code>callbackfn</code> function one time for
	 * each element in the array.
	 * @return The accumulated value
	 */
	Object reduceRight(Function<ArrayReduceEntry<Object>, Object> callbackfn);
	
	/**
	 * Calls the specified callback function for all the elements in an array, in
	 * descending order. The return value of the callback function is the accumulated result,
	 * and is provided as an argument in the next call to the callback function.
	 * @param callbackfn The reduceRight method calls the <code>callbackfn</code> function one time for
	 * each element in the array.
	 * @param initialValue The initial accumulated value
	 * @return The accumulated value
	 */
	<T> T reduceRight(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue);
	
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
	
	/**
	 * Determines whether the specified callback function returns true for any
	 * element of an array.
	 * @param callbackfn The some method calls the predicate function for each
	 * element in the array until the predicate returns true, or until the end 
	 * of the array.
	 * @return Whether or not the callbackfn returned true.
	 */
	boolean some(Predicate<ArrayEntry> callbackfn);
	
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
}