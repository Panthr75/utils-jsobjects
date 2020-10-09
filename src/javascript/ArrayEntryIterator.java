package javascript;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javascript.interfaces.ArrayLike;

/**
 * An iterator for iterating through the entries of an array
 * @author Josh
 * @version 8th October, 2020
 */
public class ArrayEntryIterator implements Iterator<ArrayEntry>
{
	private final ArrayLike array;
	private int index;
	private final int length;
	
	public ArrayEntryIterator(ArrayLike array)
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
	public ArrayEntry next() 
	{
		if (this.hasNext())
		{
			ArrayEntry entry = new ArrayEntry(this.index, this.array.get(index));
			this.index++;
			return entry;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}
}