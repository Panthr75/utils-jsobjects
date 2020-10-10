package javascript.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javascript.interfaces.ArrayLike;

/**
 * An iterator for iterating through an array's keys
 * @author Josh
 * @version 8th October, 2020
 */
public class ArrayKeyIterator implements Iterator<Integer>
{
	private int index;
	private final int length;
	
	public ArrayKeyIterator(ArrayLike array)
	{
		this.length = array.length();
		this.index = 0;
	}

	@Override
	public boolean hasNext() 
	{
		return this.index < this.length;
	}

	@Override
	public Integer next() 
	{
		if (this.hasNext())
		{
			int result = this.index;
			this.index++;
			return result;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
}