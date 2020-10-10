package javascript.interfaces;

import java.util.Iterator;

import javascript.*;

/**
 * The base interface for something implementing an ECMAScript 262
 * string.
 * @author Josh
 * @version 9th October, 2020
 * @param <S> The inheriting class
 */
public interface IString<S extends IString<S>> extends Iterable<S>
{
	/**
	 * Gets the length of this string
	 * @return The length of this string
	 */
	int length();
	
	/**
	 * Gets the character at <code>index</code>
	 * @param index The index to get the character at
	 * @return The character at <code>index</code>.
	 */
	S charAt(int index);
	
	/**
	 * Gets the char code of the character at <code>pos</code>. Returns
	 * <code>null</code> if <code>pos</code> is outside the range of this string.
	 * @param pos The position of the character code to get
	 * @return The char code of the character at <code>pos</code>, or
	 * <code>null</code> if <code>pos</code> is outside the range of this string.
	 */
	Integer charCodeAt(int pos);
	
	/**
	 * Gets the code point of the character at <code>pos</code>. Returns
	 * <code>null</code> if <code>pos</code> is outside the range of this string.
	 * @param pos The position of the code point to get
	 * @return The code point of the character at <code>pos</code>, or
	 * <code>null</code> if <code>pos</code> is outside the range of this string.
	 */
	Integer charPointAt(int pos);
	
	/**
	 * Returns the result of concatenating this string with <code>args</code>
	 * (converts each item to a string).
	 * @param args The strings/objects to concatenate to this string
	 * @return The result of concatenating this string with <code>args</code>
	 * (converts each item to a string).
	 */
	S concat(Object ...args);
	
	/**
	 * Returns whether this string ends with the string representation
	 * of <code>searchString</code>
	 * @param searchString The search string to check if this string ends with
	 * @return Whether this string ends with the string representation
	 * of <code>searchString</code>
	 */
	boolean endsWith(Object searchString);
	
	/**
	 * Returns whether this string ends with the string representation
	 * of <code>searchString</code>, specifying the "end" of the string
	 * in <code>endPosition</code>
	 * @param searchString The search string to check if this string ends with
	 * @param endPosition The exclusive position in this string where the "end"
	 * of it is.
	 * @return Whether this string ends with the string representation
	 * of <code>searchString</code>
	 */
	boolean endsWith(Object searchString, int endPosition);
	
	/**
	 * Returns whether this string contains the string representation
	 * of <code>searchString</code>
	 * @param searchString The searchString
	 * @return Whether this string contains the string representation
	 * of <code>searchString</code>
	 */
	boolean includes(Object searchString);
	
	/**
	 * Returns whether this string contains the string representation
	 * of <code>searchString</code>, starting the search at
	 * <code>position</code>
	 * @param searchString The searchString
	 * @param position The position to begin the search at.
	 * @return Whether this string contains the string representation
	 * of <code>searchString</code>
	 */
	boolean includes(Object searchString, int position);
	
	/**
	 * Returns the index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 * @param searchString The searchString
	 * @return The index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 */
	int indexOf(Object searchString);
	
	/**
	 * Returns the index at with the string representation of
	 * <code>searchString</code> occurs, starting at <code>position</code>.
	 * If <code>searchString</code> does not occur in this string, this 
	 * method returns <code>-1</code>.
	 * @param searchString The searchString
	 * @param position The position to begin the search at
	 * @return The index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 */
	int indexOf(Object searchString, int position);
	
	/**
	 * Returns the last index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 * @param searchString The searchString
	 * @return The last index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 */
	int lastIndexOf(Object searchString);
	
	/**
	 * Returns the last index at with the string representation of
	 * <code>searchString</code> occurs, ending at <code>position</code>.
	 * If <code>searchString</code> does not occur in this string, this 
	 * method returns <code>-1</code>.
	 * @param searchString The searchString
	 * @param position The position to end the search at
	 * @return The last index at with the string representation of
	 * <code>searchString</code> occurs. If <code>searchString</code>
	 * does not occur in this string, this method returns <code>-1</code>.
	 */
	int lastIndexOf(Object searchString, int position);
	
	/**
	 * Locally compares this string to the string representation
	 * of <code>that</code>
	 * @param that The object to compare to
	 * @return The comparison of <code>this</code> to <code>that</code>.
	 */
	int localeCompare(Object that);
	
	S normalize();
	
	S normalize(String form);
	
	S normalize(S form);
	
	S padEnd(int maxLength);
	
	S padEnd(int maxLength, Object fillString);
	
	S padStart(int maxLength);
	
	S padStart(int maxLength, Object fillString);
	
	S repeat(int count);
	
	S slice();
	
	S slice(int start);
	
	S slice(int start, int end);
	
	JSArray split();
	
	JSArray split(Object seperator);
	
	JSArray split(Object seperator, int limit);
	
	boolean startsWith(Object searchString);
	
	boolean startsWith(Object searchString, int position);
	
	S substring();
	
	S substring(int start);
	
	S substring(int start, int end);
	
	default S toLocaleLowerCase()
	{
		return this.toLowerCase();
	}
	
	default S toLocaleUpperCase()
	{
		return this.toUpperCase();
	}
	
	S toLowerCase();
	
	S toUpperCase();
	
	S trim();
	
	S trimStart();
	
	S trimEnd();
	
	String valueOf();
	
	default Iterator<S> iterator()
	{
		return new StringIterator<S>(this);
	}
	
	boolean contentEquals(S string);
	boolean contentEquals(String string);
}