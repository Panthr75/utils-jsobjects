package javascript;

public class ArrayReduceEntry<T> 
{
	public final T accumulator;
	public final Object value;
	public final int index;
	
	public ArrayReduceEntry(T accumulator, Object value, int index)
	{
		this.accumulator = accumulator;
		this.value = value;
		this.index = index;
	}
}