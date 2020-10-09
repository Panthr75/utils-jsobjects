package javascript;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javascript.interfaces.ArrayLike;

/**
 * An iterator for iterating through the values of an array.
 * @author Josh
 * @version 8th October, 2020
 */
public class ArrayValueIterator implements Iterator<Object>
{
	private final ArrayLike array;
	private int index;
	private final int length;
	
	public ArrayValueIterator(ArrayLike array)
	{
		this.length = array.length();
		this.array = array.slice(0, this.length);
		this.index = 0;
	}

	@Override
	public boolean hasNext() 
	{
		return this.index < this.length;
	}

	@Override
	public Object next() 
	{
		if (this.hasNext())
		{
			Object result = this.array.get(this.index);
			this.index++;
			return result;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
}