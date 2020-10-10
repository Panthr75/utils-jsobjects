package javascript.interfaces;

import javascript.JSArray;

/**
 * Represents that an object can split another object
 * @author Josh
 * @version 9th October, 2020
 * @param <T> The type that this splitter splits
 */
public interface Splitter<T>
{
	boolean canSplit(Object obj);
	
	JSArray split(T objToSplit, int limit);
}