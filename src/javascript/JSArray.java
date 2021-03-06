package javascript;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

import javascript.interfaces.*;
import javascript.exceptions.*;

/**
 * An <a href="https://tc39.es/ecma262/#sec-array-objects">ECMA-262 Array</a> implementation
 * @author Josh
 * @version 8th October, 2020
 */
public class JSArray extends JSObject implements IArray<JSArray>
{
	private Object[] _values;
	
	public int length()
	{
		return this._values.length;
	}
	
	public void setLength(int newLength)
	{
		if (JSObject.isFrozen(this)) return;
		int oldLength = this.length();
		
		this._values = Arrays.copyOf(this._values, newLength);
		
		for (int i = oldLength; i < newLength; i++)
		{
			this._values[i] = Global.undefined;
		}
	}
	
	/**
	 * Instantiates a new Array with a length of 0
	 */
	public JSArray()
	{
		super();
		
		this._values = new Object[0];
	}
	
	/**
	 * Instantiates a new Array with the given length, filling all values
	 * will <code>Undefined</code>
	 * @param length The length of the array
	 */
	public JSArray(int length)
	{
		super();
		
		this._values = new Object[length];
		for (int index = 0; index < length; index++)
		{
			this._values[index] = Global.undefined;
		}
	}
	
	/**
	 * Instantiates a new Array with the given items
	 * @param items The items to put in this array
	 */
	public JSArray(Object ...items)
	{
		super();
		
		int length = items.length;
		this._values = Arrays.copyOf(items, length);
	}
	
	public JSArray concat(Object ...items)
	{
		JSArray A = new JSArray();
		for (int index = 0, length = this.length(); index < length; index++)
		{
			A.push(this.get(index));
		}
		
		for (int index = 0, length = items.length; index < length; index++)
		{
			Object E = items[index];
			if (isConcatSpreadable(E))
			{
				ArrayLike Earr = (ArrayLike)E;
				for (int k = 0, len = Earr.length(); k < len; k++)
				{
					A.push(Earr.get(k));
				}
			}
			else
				A.push(items[index]);
		}
		
		return A;
	}
	
	static boolean isConcatSpreadable(Object o)
	{
		if (o instanceof ConcatArray) return true;
		return JSArray.isArray(o);
	}
	
	
	public JSArray copyWithin()
	{
		int len = this.length();
		
		int to = 0;
		int from = 0;
		int fin = len;
		
		int count = Math.min(fin - from, len - to);
		
		int direction = 1;
		
		while (count > 0)
		{
			if (from >= 0 && from < len)
			{
				Object fromVal = this.get(from);
				this.set(to, fromVal);
			}
			else
			{
				this.delete(to);
			}
			
			from += direction;
			to += direction;
			count--;
		}
		
		return this;
	}
	
	public JSArray copyWithin(int target)
	{
		int len = this.length();
		
		int to = target < 0 ? Math.max(len + target, 0) : Math.min(target, len);
		int from = 0;
		int fin = len;
		
		int count = Math.min(fin - from, len - to);
		
		int direction;
		if (from < to && to < from + count)
		{
			direction = -1;
			from += count - 1;
			to += count - 1;
		}
		else
			direction = 1;
		
		while (count > 0)
		{
			if (from >= 0 && from < len)
			{
				Object fromVal = this.get(from);
				this.set(to, fromVal);
			}
			else
			{
				this.delete(to);
			}
			
			from += direction;
			to += direction;
			count--;
		}
		
		return this;
	}
	
	public JSArray copyWithin(int target, int start)
	{
		int len = this.length();
		
		int to = target < 0 ? Math.max(len + target, 0) : Math.min(target, len);
		int from = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = len;
		
		int count = Math.min(fin - from, len - to);
		
		int direction;
		if (from < to && to < from + count)
		{
			direction = -1;
			from += count - 1;
			to += count - 1;
		}
		else
			direction = 1;
		
		while (count > 0)
		{
			if (from >= 0 && from < len)
			{
				Object fromVal = this.get(from);
				this.set(to, fromVal);
			}
			else
			{
				this.delete(to);
			}
			
			from += direction;
			to += direction;
			count--;
		}
		
		return this;
	}
	
	public JSArray copyWithin(int target, int start, int end)
	{
		int len = this.length();
		
		int to = target < 0 ? Math.max(len + target, 0) : Math.min(target, len);
		int from = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = end < 0 ? Math.max(len + end, 0) : Math.min(end, len);
		
		int count = Math.min(fin - from, len - to);
		
		int direction;
		if (from < to && to < from + count)
		{
			direction = -1;
			from += count - 1;
			to += count - 1;
		}
		else
			direction = 1;
		
		while (count > 0)
		{
			if (from >= 0 && from < len)
			{
				Object fromVal = this.get(from);
				this.set(to, fromVal);
			}
			else
			{
				this.delete(to);
			}
			
			from += direction;
			to += direction;
			count--;
		}
		
		return this;
	}
	
	public boolean every(Predicate<ArrayEntry> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		for (int k = 0, len = this.length(); k < len; k++)
		{
			if (!callbackfn.test(new ArrayEntry(k, this.get(k))))
				return false;
		}
		return true;
	}
	
	public JSArray fill(Object value)
	{
		int len = this.length();
		int k = 0;
		int fin = len;
		
		for (; k < fin; k++)
		{
			this.set(k, value);
		}
		
		return this;
	}
	
	public JSArray fill(Object value, int start)
	{
		int len = this.length();
		int k = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = len;
		
		for (; k < fin; k++)
		{
			this.set(k, value);
		}
		
		return this;
	}
	
	public JSArray fill(Object value, int start, int end)
	{
		int len = this.length();
		int k = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = end < 0 ? Math.max(len + end, 0) : Math.min(end, len);
		
		for (; k < fin; k++)
		{
			this.set(k, value);
		}
		
		return this;
	}
	
	public JSArray filter(Predicate<ArrayEntry> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		JSArray A = new JSArray();
		int k = 0;
		
		for (; k < len; k++)
		{
			Object kValue = this.get(k);
			if (callbackfn.test(new ArrayEntry(k, kValue)))
				A.push(kValue);
		}
		
		return A;
	}
	
	public Object find(Predicate<ArrayEntry> predicate)
	{
		if (predicate == null) throw new IllegalArgumentException("'predicate' may not be 'null'");
		
		int len = this.length();
		int k = 0;
		
		for (; k < len; k++)
		{
			Object kValue = this.get(k);
			if (predicate.test(new ArrayEntry(k, kValue)))
				return kValue;
		}
		
		return Global.undefined;
	}
	
	public int findIndex(Predicate<ArrayEntry> predicate)
	{
		if (predicate == null) throw new IllegalArgumentException("'predicate' may not be 'null'");
		
		int len = this.length();
		int k = 0;
		
		for (; k < len; k++)
		{
			Object kValue = this.get(k);
			if (predicate.test(new ArrayEntry(k, kValue)))
				return k;
		}
		
		return -1;
	}
	
	public void foreach(Consumer<ArrayEntry> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		int k = 0;
		
		for (; k < len; k++)
		{
			callbackfn.accept(new ArrayEntry(k, this.get(k)));
		}
	}
	
	public boolean includes(Object searchElement)
	{
		int len = this.length();
		
		int fromIndex = 0;
		int k = fromIndex;
		
		for (; k < len; k++)
		{
			Object elementK = this.get(k);
			if (Utils.sameValueZero(searchElement, elementK))
				return true;
		}
		
		return false;
	}
	
	public boolean includes(Object searchElement, int fromIndex)
	{
		int len = this.length();
		
		int k;
		if (fromIndex >= 0)
			k = fromIndex;
		else
		{
			k = len + fromIndex;
			if (k < 0) k = 0;
		}
		
		for (; k < len; k++)
		{
			Object elementK = this.get(k);
			if (Utils.sameValueZero(searchElement, elementK))
				return true;
		}
		
		return false;
	}
	
	public int indexOf(Object searchElement)
	{
		int len = this.length();
		
		int fromIndex = 0;
		int k = fromIndex;
		
		for (; k < len; k++)
		{
			Object elementK = this.get(k);
			if (Utils.strictEqualityComparison(searchElement, elementK))
				return k;
		}
		
		return -1;
	}
	
	public int indexOf(Object searchElement, int fromIndex)
	{
		int len = this.length();
		
		int k;
		if (fromIndex >= 0)
			k = fromIndex;
		else
		{
			k = len + fromIndex;
			if (k < 0) k = 0;
		}
		
		for (; k < len; k++)
		{
			Object elementK = this.get(k);
			if (Utils.strictEqualityComparison(searchElement, elementK))
				return k;
		}
		
		return -1;
	}
	
	public String join()
	{
		int len = this.length();
		String sep = ",";
		
		StringBuilder R = new StringBuilder();
		for (int k = 0; k < len; k++)
		{
			if (k > 0) R.append(sep);
			Object element = this.get(k);
			String next = element == null || element instanceof JSUndefined ? "" : element.toString();
			R.append(next);
		}
		return R.toString();
	}
	
	public String join(String separator)
	{
		int len = this.length();
		String sep = separator == null ? "null" : separator;
		
		StringBuilder R = new StringBuilder();
		for (int k = 0; k < len; k++)
		{
			if (k > 0) R.append(sep);
			Object element = this.get(k);
			String next = element == null || element instanceof JSUndefined ? "" : element.toString();
			R.append(next);
		}
		return R.toString();
	}
	
	public int lastIndexOf(Object searchElement)
	{
		int len = this.length();
		int fromIndex = len - 1;
		
		for (int k = fromIndex; k >= 0; k--)
		{
			if (k >= 0 && k < len)
			{
				Object elementK = this.get(k);
				if (Utils.strictEqualityComparison(searchElement, elementK))
					return k;
			}
		}
		
		return -1;
	}
	
	public int lastIndexOf(Object searchElement, int toIndex)
	{
		int len = this.length();
		int k = toIndex >= 0 ? Math.min(toIndex, len - 1) : len + toIndex;
		
		for (; k >= 0; k--)
		{
			if (k >= 0 && k < len)
			{
				Object elementK = this.get(k);
				if (Utils.strictEqualityComparison(searchElement, elementK))
					return k;
			}
		}
		
		return -1;
	}
	
	public JSArray map(Function<ArrayEntry, Object> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			A.set(k, callbackfn.apply(new ArrayEntry(k, this.get(k))));
		}
		
		return A;
	}
	
	public Object pop()
	{
		int len = this.length();
		
		if (len == 0)
			return Global.undefined;
		else
		{
			int index = len - 1;
			Object element = this.get(index);
			
			this.delete(index);
			return element;
		}
	}
	
	public int push(Object ...items)
	{
		int len = this.length();
		if (JSObject.isFrozen(this)) return len;
		
		int argCount = items.length;
		if (argCount > 0)
		{
			for (int index = 0; index < argCount; index++)
			{
				this._add(len, items[index]);
				len++;
			}
		}
		
		return len;
	}
	
	public Object reduce(Function<ArrayReduceEntry<Object>, Object> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		
		if (len == 0) throw new IllegalArgumentException("Cannot reduce an array of length 0.");
		
		int k = 0;
		Object accumulator = this.get(0);
		k++;
		
		for (; k < len; k++)
		{
			Object kValue = this.get(k);
			accumulator = callbackfn.apply(new ArrayReduceEntry<Object>(accumulator, kValue, k));
		}
		
		return accumulator;
	}
	
	public <T> T reduce(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		
		int k = 0;
		T accumulator = initialValue;
		
		for (; k < len; k++)
		{
			Object kValue = this.get(k);
			accumulator = callbackfn.apply(new ArrayReduceEntry<T>(accumulator, kValue, k));
		}
		
		return accumulator;
	}
	
	public Object reduceRight(Function<ArrayReduceEntry<Object>, Object> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		
		if (len == 0) throw new IllegalArgumentException("Cannot reduce an array of length 0.");
		
		int k = len - 1;
		Object accumulator = this.get(0);
		k--;
		
		for (; k >= 0; k--)
		{
			Object kValue = this.get(k);
			accumulator = callbackfn.apply(new ArrayReduceEntry<Object>(accumulator, kValue, k));
		}
		
		return accumulator;
	}
	
	public <T> T reduceRight(Function<ArrayReduceEntry<T>, T> callbackfn, T initialValue)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		
		int k = len - 1;
		T accumulator = initialValue;
		
		for (; k >= 0; k--)
		{
			Object kValue = this.get(k);
			accumulator = callbackfn.apply(new ArrayReduceEntry<T>(accumulator, kValue, k));
		}
		
		return accumulator;
	}
	
	public JSArray reverse()
	{
		int len = this.length();
		
		int middle = len / 2;
		int lower = 0;
		
		for (; lower != middle; lower++)
		{
			int upper = len - lower - 1;
			Object lowerValue = this.get(lower);
			Object upperValue = this.get(upper);
			
			this.set(lower, upperValue);
			this.set(upper, lowerValue);
		}
		
		return this;
	}
	
	public Object shift()
	{
		int len = this.length();
		
		if (len == 0)
			return Global.undefined;
		else
		{
			Object first = this.get(0);
			
			for (int k = 1; k < len; k++)
			{
				Object val = this.get(k);
				this.set(k - 1, val);
			}
			
			this.delete(len - 1);
			return first;
		}
	}
	
	public JSArray slice()
	{
		int len = this.length();
		int k = 0;
		int fin = len;
		
		int count = Math.max(fin - k, 0);
		
		JSArray A = new JSArray(count);
		
		for (int j = 0; j < count; j++)
		{
			Object kValue = this.get(k);
			A.set(j, kValue);
			k++;
		}
		
		return A;
	}
	
	public JSArray slice(int start)
	{
		int len = this.length();
		int k = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = len;
		
		int count = Math.max(fin - k, 0);
		
		JSArray A = new JSArray(count);
		
		for (int j = 0; j < count; j++)
		{
			Object kValue = this.get(k);
			A.set(j, kValue);
			k++;
		}
		
		return A;
	}
	
	public JSArray slice(int start, int end)
	{
		int len = this.length();
		int k = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int fin = end < 0 ? Math.max(len + end, 0) : Math.min(end, len);
		
		int count = Math.max(fin - k, 0);
		
		JSArray A = new JSArray(count);
		
		for (int j = 0; j < count; j++)
		{
			Object kValue = this.get(k);
			A.set(j, kValue);
			k++;
		}
		
		return A;
	}
	
	public boolean some(Predicate<ArrayEntry> callbackfn)
	{
		if (callbackfn == null) throw new IllegalArgumentException("'callbackfn' may not be 'null'");
		
		int len = this.length();
		
		for (int k = 0; k < len; k++)
		{
			Object kValue = this.get(k);
			if (callbackfn.test(new ArrayEntry(k, kValue)))
				return true;
		}
		return false;
	}
	
	//TODO: refactor .sort() SO ITS WAY FASTER LIKE JS RUNS 10X FASTER THAN IT
	
	public JSArray sort()
	{
		return sort(null);
	}
	
	public JSArray sort(ToIntFunction<CompareInfo> compareFn)
	{
		int len = this.length();
		
		// I need to implement IntroSort, but for now this will do
		boolean modified = len > 1;
		while (modified)
		{
			modified = false;
			Object prev = this.get(0);
			for (int k = 1; k < len; k++)
			{
				Object current = this.get(k);
				int c = sortCompare(prev, current, compareFn);
				if (c > 0)
				{
					this.set(k, prev);
					this.set(k - 1, current);
					modified = true;
				}
				else prev = current;
			}
		}
		
		return this;
	}
	
	private int sortCompare(Object x, Object y, ToIntFunction<CompareInfo> compareFn)
	{
		if (x instanceof JSUndefined) return y instanceof JSUndefined ? 0 : -1;
		else if (y instanceof JSUndefined) return 1;
		
		if (compareFn != null)
		{
			int v = compareFn.applyAsInt(new CompareInfo(x, y));
			return v;
		}
		
		String xString = String.valueOf(x);
		String yString = String.valueOf(y);
		
		if (xString.compareTo(yString) < 0)
		{
			return -1;
		}
		else if (yString.compareTo(xString) < 0)
		{
			return 1;
		}
		return 0;
	}
	
	public JSArray splice()
	{
		int len = this.length();

		int actualStart = 0;
		int deleteCount = 0;
		
		int actualDeleteCount = Math.min(Math.max(deleteCount, 0), len - actualStart);
		
		JSArray A = new JSArray(actualDeleteCount);
		int k = 0;
		
		for (; k < actualDeleteCount; k++)
		{
			int from = actualStart + k;
			if (k >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				A.set(k, fromValue);
			}
		}
		
		k = actualStart;
		for (; k < len - actualDeleteCount; k++)
		{
			int from = k + actualDeleteCount;
			int to = k;
			if (from >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				this.set(to, fromValue);
			}
			else
			{
				this.delete(to);
			}
		}
		k = len;
		for (; k > len - actualDeleteCount; k--)
		{
			this.delete(k - 1);
		}
		
		return A;
	}
	
	public JSArray splice(int start)
	{
		int len = this.length();

		int actualStart = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int deleteCount = len - actualStart;
		
		int actualDeleteCount = Math.min(Math.max(deleteCount, 0), len - actualStart);
		
		JSArray A = new JSArray(actualDeleteCount);
		int k = 0;
		
		for (; k < actualDeleteCount; k++)
		{
			int from = actualStart + k;
			if (k >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				A.set(k, fromValue);
			}
		}
		
		k = actualStart;
		for (; k < len - actualDeleteCount; k++)
		{
			int from = k + actualDeleteCount;
			int to = k;
			if (from >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				this.set(to, fromValue);
			}
			else
			{
				this.delete(to);
			}
		}
		k = len;
		for (; k > len - actualDeleteCount; k--)
		{
			this.delete(k - 1);
		}
		
		return A;
	}
	
	public JSArray splice(int start, int deleteCount)
	{
		int len = this.length();

		int actualStart = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		
		int actualDeleteCount = Math.min(Math.max(deleteCount, 0), len - actualStart);
		
		JSArray A = new JSArray(actualDeleteCount);
		int k = 0;
		
		for (; k < actualDeleteCount; k++)
		{
			int from = actualStart + k;
			if (k >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				A.set(k, fromValue);
			}
		}
		
		k = actualStart;
		for (; k < len - actualDeleteCount; k++)
		{
			int from = k + actualDeleteCount;
			int to = k;
			if (from >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				this.set(to, fromValue);
			}
			else
			{
				this.delete(to);
			}
		}
		k = len;
		for (; k > len - actualDeleteCount; k--)
		{
			this.delete(k - 1);
		}
		
		return A;
	}
	
	public JSArray splice(int start, int deleteCount, Object ...items)
	{
		int len = this.length();

		int actualStart = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		
		int actualDeleteCount = Math.min(Math.max(deleteCount, 0), len - actualStart);
		
		JSArray A = new JSArray(actualDeleteCount);
		int k = 0;
		
		for (; k < actualDeleteCount; k++)
		{
			int from = actualStart + k;
			if (k >= 0 && k < len)
			{
				Object fromValue = this.get(from);
				A.set(k, fromValue);
			}
		}
		
		int itemCount = items.length;
		if (itemCount < actualDeleteCount)
		{
			k = actualStart;
			for (; k < len - actualDeleteCount; k++)
			{
				int from = k + actualDeleteCount;
				int to = k + itemCount;
				if (from >= 0 && k < len)
				{
					Object fromValue = this.get(from);
					this.set(to, fromValue);
				}
				else
				{
					this.delete(to);
				}
			}
			k = len;
			for (; k > len - actualDeleteCount + itemCount; k--)
			{
				this.delete(k - 1);
			}
		}
		else if (itemCount > actualDeleteCount)
		{
			k = len - actualDeleteCount;
			for (; k > actualStart; k--)
			{
				int from = k + actualDeleteCount - 1;
				int to = k + itemCount - 1;
				if (from >= 0 && k < len)
				{
					Object fromValue = this.get(from);
					this.set(to, fromValue);
				}
				else
				{
					this.delete(to);
				}
			}
		}
		
		k = actualStart;
		for (int i = 0; i < itemCount; i++)
		{
			this.set(k, items[i]);
			k++;
		}
		
		this.setLength(len - actualDeleteCount + itemCount);
		
		return A;
	}
	
	/**
	 * Converts this array to a locale string.
	 * <br/>
	 * <b>Will be implemented later, for now, returns <code>toString()</code><b>
	 * @return This array as a localized string
	 */
	public String toLocaleString()
	{
		// maybe implement ECMA-402 api, but for now, will just return this.toString()
		return this.toString();
	}
	
	@Override
	public String toString()
	{
		return this.join();
	}
	
	public int unshift(Object ...items)
	{
		int len = this.length();
		
		int argCount = items.length;
		if (argCount > 0)
		{
			Object[] newValues = Arrays.copyOf(items, len + argCount);
			int k = argCount;
			
			for (int j = 0; j < len; j++)
			{
				newValues[k + j] = this._values[j];
			}
			
			this._update(newValues);
		}
		
		return len + argCount;
	}
	
	private void _update(Object[] values)
	{
		int valLength = values.length;
		int len = this.length();
		
		while (valLength < len)
		{
			this.delete(valLength);
			len--;
		}
		
		if (valLength > len)
		{
			if (JSObject.isFrozen(this))
				throw new UpdatePropertyException(String.valueOf(valLength - 1), this, 
						UpdatePropertyException.UpdateType.ADD);
			
			int k = 0;
			while (valLength > len)
			{
				this._add(k, values[k]);
				len++;
				k++;
			}
		}
	}
	
	private void _add(int index, Object value)
	{
		if (JSObject.isFrozen(this)) 
			throw new UpdatePropertyException(String.valueOf(index), this, UpdatePropertyException.UpdateType.ADD);
		
		int len = this.length();
		
		this.setLength(len + 1);
		
		for (int k = len - 1; k >= index; k--)
		{
			this._set(k + 1, this._values[k]);
		}

		this._set(index, value);
	}
	
	public Object get(int index)
	{
		if (index < 0 || index >= this.length()) return Global.undefined;
		return this._values[index];
	}
	
	private void _set(int index, Object value)
	{
		if (JSObject.isFrozen(this))
			throw new UpdatePropertyException(String.valueOf(index), this, 
					UpdatePropertyException.UpdateType.SET);
		
		this._values[index] = value;
	}
	
	public void set(int index, Object value)
	{
		if (index >= 0)
		{
			int len = this.length();
			if (index >= len)
				this._add(index, value);
			else
				this._set(index, value);
		}
	}
	
	public boolean delete(int index)
	{
		if (JSObject.isFrozen(this))
		{
			throw new UpdatePropertyException(String.valueOf(index), this, 
					UpdatePropertyException.UpdateType.DELETE);
		}
			
		if (index < 0) return false;
		
		int len = this.length();
		
		if (index >= len) return false;
		
		Object[] newArray = new Object[len - 1];
		int n = 0;
		for (int k = 0; k < len; k++)
		{
			if (k != index)
			{
				newArray[n] = this._values[k];
				n++;
			}
		}
		
		this._values = newArray;
		return true;
	}
	
	
	
	/**
	 * Creates an Array from the given item array
	 * @param items The items
	 * @return An Array comprising of <code>items</code>
	 */
	public static JSArray from(Object[] items)
	{
		int len = items.length;
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			Object kValue = items[k];
			A.set(k, kValue);
		}
		return A;
	}
	
	/**
	 * Creates an Array from the given item array, using the <code>mapFn</code>
	 * to map each item to a new one
	 * @param <U> The original type
	 * @param <T> The type to map to
	 * @param items The items
	 * @param mapFn The function to map an item <code>U</code> to item <code>T</code>.
	 * @return An Array comprising of <code>items</code> mapped via <code>mapFn</code>.
	 */
	public static <U, T> JSArray from(U[] items, Function<U, T> mapFn)
	{
		if (mapFn == null) throw new IllegalArgumentException("'mapFn' may not be 'null'");
		
		int len = items.length;
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			T kValue = mapFn.apply(items[k]);
			A.set(k, kValue);
		}
		return A;
	}
	
	/**
	 * Creates an Array from the given iterator
	 * @param <T> The type this iterator holds
	 * @param iterator The iterator
	 * @return An Array comprising of the items in <code>iterator</code>
	 */
	public static <T> JSArray from(Iterator<T> iterator)
	{
		JSArray A = new JSArray();
		int k = 0;
		
		while (iterator.hasNext())
		{
			T nextValue = iterator.next();
			A.set(k, nextValue);
			
			k++;
		}
		
		return A;
	}
	
	/**
	 * Creates an Array from the given iterator, using the <code>mapFn</code>
	 * to map each item in the iterator to a new one.
	 * @param <U> The original type
	 * @param <T> The type to map to
	 * @param iterator The iterator
	 * @param mapFn The function to map an item <code>U</code> to item <code>T</code>.
	 * @return An Array comprising of the items in <code>iterator</code> mapped via <code>mapFn</code>.
	 */
	public static <U, T> JSArray from(Iterator<U> iterator, Function<U, T> mapFn)
	{
		if (mapFn == null) throw new IllegalArgumentException("'mapFn' may not be 'null'");
		
		JSArray A = new JSArray();
		int k = 0;
		
		while (iterator.hasNext())
		{
			U nextValue = iterator.next();
			A.set(k, mapFn.apply(nextValue));
			
			k++;
		}
		
		return A;
	}
	
	/**
	 * Creates an Array from the given iterable object.
	 * <br/>
	 * <b>This is short for writing <code>JSArray.from(iterable.iterator())</code>.</b>
	 * @param <T> The type this iterable holds
	 * @param iterable The iterable object
	 * @return An Array comprising of the items in <code>iterable</code>
	 */
	public static <T> JSArray from(Iterable<T> iterable)
	{
		return JSArray.from(iterable.iterator());
	}
	
	/**
	 * Creates an Array from the given iterable object.
	 * <br/>
	 * <b>This is short for writing <code>JSArray.from(iterable.iterator(), mapFn)</code>.</b>
	 * @param <U> The original type
	 * @param <T> The type to map to
	 * @param iterable The iterable object
	 * @param mapFn The function to map an item <code>U</code> to item <code>T</code>.
	 * @return An Array comprising of the items in <code>iterable</code> mapped via <code>mapFn</code>.
	 */
	public static <U, T> JSArray from(Iterable<U> iterable, Function<U, T> mapFn) 
	{
		return JSArray.from(iterable.iterator(), mapFn);
	}
	
	/**
	 * Creates an Array from the given ArrayLike object
	 * @param array The array-like object
	 * @return An Array from the ArrayLike object
	 */
	public static JSArray from(ArrayLike array)
	{
		int len = array.length();
		
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			A.set(k, array.get(k));
		}
		
		return A;
	}
	
	/**
	 * Creates an Array from the given ArrayLike object, using <code>mapFn</code>
	 * to map each item in the array-like object to a new one.
	 * @param <T> The type to map to
	 * @param array The array-like object
	 * @param mapFn The function to map an item to an item of type <code>T</code>.
	 * @return An Array from the ArrayLike object mapped via <code>mapFn</code>.
	 */
	public static <T> JSArray from(ArrayLike array, Function<Object, T> mapFn)
	{
		if (mapFn == null) throw new IllegalArgumentException("'mapFn' may not be 'null'");
		
		int len = array.length();
		
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			Object kValue = array.get(k);
			A.set(k, mapFn.apply(kValue));
		}
		
		return A;
	}
	
	/**
	 * Creates an array comprising of <code>items</code>.
	 * @param items The items to put in the array
	 * @return An array comprising of <code>items</code>.
	 */
	public static JSArray of(Object ...items)
	{
		int len = items.length;
		JSArray A = new JSArray(len);
		
		for (int k = 0; k < len; k++)
		{
			Object kValue = items[k];
			A.set(k, kValue);
		}
		
		return A;
	}
	
	/**
	 * Returns whether <code>obj</code> is an array.
	 * @param obj The obj to check
	 * @return Whether <code>obj</code> is an array.
	 */
	public static boolean isArray(Object obj)
	{
		return obj instanceof ArrayLike;
	}
}