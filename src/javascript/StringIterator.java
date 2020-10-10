package javascript;

import javascript.interfaces.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for iterating through characters in an IString
 * @author Josh
 * @version 9th October, 2020
 *
 * @param <S> The string this iterator is for
 */
public class StringIterator<S extends IString<S>> implements Iterator<S>
{
	private final IString<S> string;
	private int index;
	private final int length;
	
	public StringIterator(IString<S> string)
	{
		this.string = string;
		this.index = 0;
		this.length = this.string.length();
	}
	
	public boolean hasNext()
	{
		return this.index < this.length;
	}
	
	public S next()
	{
		if (this.hasNext())
		{
			int i = this.index;
			this.index++;
			return this.string.charAt(i);
		}
		else
			throw new NoSuchElementException("No such character exists in the string");
	}
}