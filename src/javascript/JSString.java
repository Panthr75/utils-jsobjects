package javascript;

import java.util.Arrays;
import java.text.Normalizer;

import javascript.interfaces.*;
import javascript.annotations.*;

public class JSString extends JSObject implements IString<JSString>
{
	private char[] _values;
	
	public int length()
	{
		return this._values.length;
	}
	
	public JSString()
	{
		this._values = new char[0];
	}
	
	public JSString(char value)
	{
		this._values = new char[1];
		this._values[0] = value;
	}
	
	public JSString(boolean value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(byte value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(short value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(int value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(long value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(float value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(double value)
	{
		this(String.valueOf(value));
	}
	
	public JSString(Character value)
	{
		if (value == null)
		{
			this._values = new char[4];
			this._values[0] = 'n';
			this._values[1] = 'u';
			this._values[2] = 'l';
			this._values[3] = 'l';
		}
		else
		{
			this._values = new char[1];
			this._values[0] = value;
		}
	}
	
	public JSString(Boolean value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Byte value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Short value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Integer value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Long value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Float value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(Double value)
	{
		this(value == null ? null : String.valueOf(value));
	}
	
	public JSString(String value)
	{
		if (value == null)
		{
			this._values = new char[4];
			this._values[0] = 'n';
			this._values[1] = 'u';
			this._values[2] = 'l';
			this._values[3] = 'l';
		}
		else
		{
			int size = value.length();
			
			this._values = new char[size];
			for (int k = 0; k < size; k++)
			{
				this._values[k] = value.charAt(k);
			}
		}
	}
	
	public JSString(JSUndefined value)
	{
		if (value == null)
		{
			this._values = new char[4];
			this._values[0] = 'n';
			this._values[1] = 'u';
			this._values[2] = 'l';
			this._values[3] = 'l';
		}
		else
		{
			this._values = new char[9];
			this._values[0] = 'u';
			this._values[1] = 'n';
			this._values[2] = 'd';
			this._values[3] = 'e';
			this._values[4] = 'f';
			this._values[5] = 'i';
			this._values[6] = 'n';
			this._values[7] = 'e';
			this._values[8] = 'd';
		}
	}
	
	public JSString(Object value)
	{
		this(value == null ? null : value.toString());
	}
	
	public JSString charAt(int index)
	{
		int size = this.length();
		if (index < 0 || index >= size) new JSString();
		return new JSString(this._values[index]);
	}
	
	public Integer charCodeAt(int pos)
	{
		int size = this.length();
		if (pos < 0 || pos >= size) return null;
		return Character.getNumericValue(this._values[pos]);
	}
	
	public Integer charPointAt(int pos)
	{
		int size = this.length();
		if (pos < 0 || pos >= size) return null;
		return Character.codePointAt(this._values, pos);
	}
	
	public JSString concat(Object ...args)
	{
		JSString R = this;
		for (int k = 0, len = args.length; k < len; k++)
		{
			JSString nextString = new JSString(args[k]);
			R = JSString.add(R, nextString);
		}
		return R;
	}
	
	public boolean endsWith(Object searchString)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int end = len;
		
		int searchLength = searchStr.length();
		if (searchLength == 0) return true;

		int start = end - searchLength;
		if (start < 0) return false;
		
		char[] substringChars = new char[end - start];
		for (int k = start, j = 0; k < end; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		return Utils.sameValueNonNumeric(substring, searchStr);
	}
	
	public boolean endsWith(Object searchString, int endPosition)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int end = Math.min(Math.max(endPosition, 0), len);
		
		int searchLength = searchStr.length();
		if (searchLength == 0) return true;

		int start = end - searchLength;
		if (start < 0) return false;
		
		char[] substringChars = new char[end - start];
		for (int k = start, j = 0; k < end; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		return Utils.sameValueNonNumeric(substring, searchStr);
	}
	
	public boolean includes(Object searchString)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = 0;
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return true;
		
		int j = 0;
		for (int k = start; k < len; k++)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j++;
				if (j == searchLen) return true;
			}
			else
			{
				j = 0;
			}
		}
		
		return false;
	}
	
	public boolean includes(Object searchString, int position)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = Math.min(Math.max(position, 0), len);
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return true;

		int j = 0;
		for (int k = start; k < len; k++)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j++;
				if (j == searchLen) return true;
			}
			else
			{
				j = 0;
			}
		}
		
		return false;
	}
	
	public int indexOf(Object searchString)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = 0;
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return start;

		int j = 0;
		for (int k = start; k < len; k++)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j++;
				if (j == searchLen) return k - j + 1;
			}
			else
			{
				j = 0;
			}
		}
		
		return -1;
	}
	
	public int indexOf(Object searchString, int position)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = Math.min(Math.max(position, 0), len);
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return start;

		int j = 0;
		for (int k = start; k < len; k++)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j++;
				if (j == searchLen) return k - j + 1;
			}
			else
			{
				j = 0;
			}
		}
		
		return -1;
	}
	
	public int lastIndexOf(Object searchString)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = 0;
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return start;

		int j = searchLen - 1;
		for (int k = len - 1; k > start; k--)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j--;
				if (j == -1) return k;
			}
			else
			{
				j = searchLen - 1;
			}
		}
		
		return -1;
	}
	
	public int lastIndexOf(Object searchString, int position)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = Math.min(Math.max(position, 0), len);
		int searchLen = searchStr.length();
		
		if (searchLen == 0 && start < len) return start;

		int j = searchLen - 1;
		for (int k = len - 1; k > start; k--)
		{
			if (this._values[k] == searchStr._values[j])
			{
				j--;
				if (j == -1) return k;
			}
			else
			{
				j = searchLen - 1;
			}
		}
		
		return -1;
	}
	
	public int localeCompare(Object that)
	{
		JSString That = new JSString(that);
		
		return this.toString().compareTo(That.toString());
	}
	
	// public RegexpMatchArray match(Regexp regexp);
	
	// public Iterator<JSString> matchAll(Regexp regexp);
	
	public JSString normalize()
	{
		String S = this.toString();
		return new JSString(Normalizer.normalize(S, Normalizer.Form.NFC));
	}
	
	public JSString normalize(String form)
	{
		form = form == null ? "NFC" : form;
		String S = this.toString();
		
		Normalizer.Form f;
		if (form.contentEquals("NFC")) f = Normalizer.Form.NFC;
		else if (form.contentEquals("NFD")) f = Normalizer.Form.NFD;
		else if (form.contentEquals("NFKC")) f = Normalizer.Form.NFKC;
		else if (form.contentEquals("NFKD")) f = Normalizer.Form.NFKD;
		else
			throw new IllegalArgumentException("Normalization form must be 'NFC', 'NFD', 'NFKC', or 'NFKD'");
		
		return new JSString(Normalizer.normalize(S, f));
	}
	
	public JSString normalize(JSString form)
	{
		form = form == null ? new JSString("NFC") : form;
		String S = this.toString();
		
		Normalizer.Form f;
		if (form.contentEquals("NFC")) f = Normalizer.Form.NFC;
		else if (form.contentEquals("NFD")) f = Normalizer.Form.NFD;
		else if (form.contentEquals("NFKC")) f = Normalizer.Form.NFKC;
		else if (form.contentEquals("NFKD")) f = Normalizer.Form.NFKD;
		else
			throw new IllegalArgumentException("Normalization form must be 'NFC', 'NFD', 'NFKC', or 'NFKD'");
		
		return new JSString(Normalizer.normalize(S, f));
	}
	
	public JSString padEnd(int maxLength)
	{
		return JSString.stringPad(this, maxLength, Global.undefined, 1);
	}
	
	public JSString padEnd(int maxLength, Object fillString)
	{
		return JSString.stringPad(this, maxLength, fillString, 1);
	}
	
	public JSString padStart(int maxLength)
	{
		return JSString.stringPad(this, maxLength, Global.undefined, -1);
	}
	
	public JSString padStart(int maxLength, Object fillString)
	{
		return JSString.stringPad(this, maxLength, fillString, 1);
	}
	
	static JSString stringPad(Object O, int maxLength, Object fillString, int placement)
	{
		JSString S = new JSString(O);
		int stringLength = S.length();
		if (maxLength <= stringLength) return S;
		
		String filler = fillString instanceof JSUndefined ? " " : new JSString(fillString).toString();
		int fillerLength = filler.length();
		if (fillerLength == 0) return S;
		
		int fillLen = maxLength - stringLength;
		
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < fillLen; k += fillerLength)
		{
			int newLen = k + fillerLength;
			if (newLen > fillLen)
			{
				sb.append(filler.substring(0, newLen - fillLen));
			}
			else
			{
				sb.append(filler);
			}
		}
		JSString truncatedStringFiller = new JSString(sb.toString());
		if (placement == -1)
			return JSString.add(truncatedStringFiller, S);
		else
			return JSString.add(S, truncatedStringFiller);
	}
	
	public JSString repeat(int count)
	{
		if (count < 0) throw new IllegalArgumentException("'count' may not be less than 0");
		else
		{
			JSString result = new JSString();
			while (count > 0)
			{
				result = JSString.add(result, this);
				count--;
			}
			return result;
		}
	}

	// public JSString replace(Object searchValue, Object replacer);
	
	// public JSString replace(Object searchValue, Function<ReplaceInfo, Object> replacer);
	
	// public JSString replace(Regexp searchValue, Object replacer);
	
	// public JSString replace(Regexp searchValue, Function<ReplaceInfo, Object> replacer);
	
	// replaceAll
	
	// search
	
	public JSString slice()
	{
		int len = this.length();
		
		int from = 0;
		int to = len;
		
		if (from >= to) return new JSString();
		
		char[] resultChars = new char[to - from];
		
		int j = 0;
		for (int k = from; k < to; k++)
		{
			resultChars[j] = this._values[k];
			j++;
		}
		
		JSString result = new JSString();
		result._values = resultChars;
		
		return result;
	}
	
	public JSString slice(int start)
	{
		int len = this.length();
		
		int from = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int to = len;
		
		if (from >= to) return new JSString();
		
		char[] resultChars = new char[to - from];
		
		int j = 0;
		for (int k = from; k < to; k++)
		{
			resultChars[j] = this._values[k];
			j++;
		}
		
		JSString result = new JSString();
		result._values = resultChars;
		
		return result;
	}
	
	public JSString slice(int start, int end)
	{
		int len = this.length();
		
		int from = start < 0 ? Math.max(len + start, 0) : Math.min(start, len);
		int to = end < 0 ? Math.max(len + end, 0) : Math.min(end, len);
		
		if (from >= to) return new JSString();
		
		char[] resultChars = new char[to - from];
		
		int j = 0;
		for (int k = from; k < to; k++)
		{
			resultChars[j] = this._values[k];
			j++;
		}
		
		JSString result = new JSString();
		result._values = resultChars;
		
		return result;
	}
	
	@ArrayResult(type = JSString.class)
	public JSArray split()
	{
		return new JSArray(this);
	}
	
	// up to implementor implementing the splitter interface
	// to validate types in .canSplit
	@SuppressWarnings("unchecked")
	@ArrayResult(type = JSString.class)
	public JSArray split(Object seperator)
	{
		int lim = Integer.MAX_VALUE;
		
		if (seperator instanceof Splitter<?>)
		{
			Splitter<?> splitter = (Splitter<?>)seperator;
			if (splitter.canSplit(this))
			{
				return ((Splitter<JSString>)splitter).split(this, lim);
			}
		}
		
		JSArray A = new JSArray();
		int lengthA = 0;
		
		JSString R = new JSString(seperator);
		
		int s = this.length();
		
		if (s == 0)
		{
			if (R.length() != 0)
				A.set(0, this);
			return A;
		}
		
		int p = 0;
		int q = p;
		
		while (q != s)
		{
			Object e = JSString.splitMatch(this, q, R);
			if (e.getClass() == boolean.class && !((boolean)e)) q++;
			else
			{
				int eInt = (int)e;
				if (eInt == p) q++;
				else
				{
					char[] substringChars = new char[q - p];
					int j = 0;
					for (int k = p; k < q; k++)
					{
						substringChars[j] = this._values[k];
					}
					JSString T = new JSString();
					T._values = substringChars;
					
					A.set(lengthA, T);
					
					lengthA++;
					if (lengthA == lim) return A;
					p = eInt;
					q = p;
				}
			}
		}
		
		char[] substringChars = new char[s - p];
		int j = 0;
		for (int k = p; k < s; k++)
		{
			substringChars[j] = this._values[k];
		}
		
		JSString T = new JSString();
		T._values = substringChars;
		
		A.set(lengthA, T);
		return A;
	}
	
	// up to implementor implementing the splitter interface
	// to validate types in .canSplit
	@SuppressWarnings("unchecked")
	@ArrayResult(type = JSString.class)
	public JSArray split(Object seperator, int limit)
	{
		int lim = Math.abs(limit);
		
		if (seperator instanceof Splitter<?>)
		{
			Splitter<?> splitter = (Splitter<?>)seperator;
			if (splitter.canSplit(this))
			{
				return ((Splitter<JSString>)splitter).split(this, lim);
			}
		}
		
		JSArray A = new JSArray();
		int lengthA = 0;
		
		JSString R = new JSString(seperator);
		
		int s = this.length();
		
		if (s == 0)
		{
			if (R.length() != 0)
				A.set(0, this);
			return A;
		}
		
		int p = 0;
		int q = p;
		
		while (q != s)
		{
			Object e = JSString.splitMatch(this, q, R);
			if (e.getClass() == boolean.class && !((boolean)e)) q++;
			else
			{
				int eInt = (int)e;
				if (eInt == p) q++;
				else
				{
					char[] substringChars = new char[q - p];
					int j = 0;
					for (int k = p; k < q; k++)
					{
						substringChars[j] = this._values[k];
					}
					JSString T = new JSString();
					T._values = substringChars;
					
					A.set(lengthA, T);
					
					lengthA++;
					if (lengthA == lim) return A;
					p = eInt;
					q = p;
				}
			}
		}
		
		char[] substringChars = new char[s - p];
		int j = 0;
		for (int k = p; k < s; k++)
		{
			substringChars[j] = this._values[k];
		}
		
		JSString T = new JSString();
		T._values = substringChars;
		
		A.set(lengthA, T);
		return A;
	}
	
	static Object splitMatch(JSString S, int q, JSString R)
	{
		int r = R.length();
		int s = S.length();
		
		if (q + r > s) return false;
		
		for (int i = 0; i < r; i++)
		{
			if (!S.charAt(q + i).contentEquals(R.charAt(i))) return false;
		}
		
		return q + r;
	}
	
	public boolean startsWith(Object searchString)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = 0;
		int searchLength = searchStr.length();
		
		if (searchLength == 0) return true;
		int end = start + searchLength;
		if (end > len) return false;
		
		char[] substringChars = new char[end - start];
		for (int k = start, j = 0; k < end; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		
		return Utils.sameValueNonNumeric(substring, searchStr);
	}
	
	public boolean startsWith(Object searchString, int position)
	{
		JSString searchStr = new JSString(searchString);
		int len = this.length();
		int start = Math.min(Math.max(position, 0), len);
		int searchLength = searchStr.length();
		
		if (searchLength == 0) return true;
		int end = start + searchLength;
		if (end > len) return false;
		
		char[] substringChars = new char[end - start];
		for (int k = start, j = 0; k < end; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		
		return Utils.sameValueNonNumeric(substring, searchStr);
	}
	
	public JSString substring()
	{
		int len = this.length();
		
		int from = 0;
		int to = len;
		
		char[] substringChars = new char[to - from];
		for (int k = from, j = 0; k < to; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		
		return substring;
	}
	
	public JSString substring(int start)
	{
		int len = this.length();
		
		int from = Math.min(Math.max(start, 0), len);
		int to = len;
		
		char[] substringChars = new char[to - from];
		for (int k = from, j = 0; k < to; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		
		return substring;
	}
	
	public JSString substring(int start, int end)
	{
		int len = this.length();
		
		int finalStart = Math.min(Math.max(start, 0), len);
		int finalEnd = Math.min(Math.max(end, 0), len);
		
		int from = Math.min(finalStart, finalEnd);
		int to = Math.max(finalStart, finalEnd);
		
		char[] substringChars = new char[to - from];
		for (int k = from, j = 0; k < to; k++)
		{
			substringChars[j] = this._values[k];
			j++;
		}
		JSString substring = new JSString();
		substring._values = substringChars;
		
		return substring;
	}
	
	public JSString toLowerCase()
	{
		int len = this.length();
		char[] newChars = new char[len];
		
		for (int k = 0; k < len; k++)
		{
			newChars[k] = Character.toLowerCase(this._values[k]);
		}
		
		JSString result = new JSString();
		result._values = newChars;
		
		return result;
	}
	
	/**
	 * Converts this JSString to a Java String
	 * @return This JSString as a Java String
	 */
	@Override
	public String toString()
	{
		return this.valueOf();
	}
	
	public JSString toUpperCase()
	{
		int len = this.length();
		char[] newChars = new char[len];
		
		for (int k = 0; k < len; k++)
		{
			newChars[k] = Character.toUpperCase(this._values[k]);
		}
		
		JSString result = new JSString();
		result._values = newChars;
		
		return result;
	}
	
	public JSString trim()
	{
		int start = 0;
		int len = this.length();
		int end = len;
		
		for (; start < len; start++)
		{
			if (this._values[start] != ' ')
				break;
		}
		
		for (; end > 0; end--)
		{
			if (this._values[end - 1] != ' ')
				break;
		}
		
		return this.substring(start, end);
	}
	
	public JSString trimStart()
	{
		for (int k = 0, len = this.length(); k < len; k++)
		{
			if (this._values[k] != ' ')
				return this.substring(k);
		}
		
		return new JSString();
	}
	
	public JSString trimEnd()
	{
		for (int k = this.length(); k > 0; k--)
		{
			if (this._values[k - 1] != ' ')
				return this.substring(0, k);
		}
		
		return new JSString();
	}
	
	@Override
	public String valueOf()
	{
		StringBuilder sb = new StringBuilder();
		for (int index = 0, len = this.length(); index < len; index++)
		{
			sb.append(this._values[index]);
		}
		return sb.toString();
	}
	
	// because no operator overloading, will make static
	// methods for operators (except for equals)
	
	public boolean contentEquals(JSString string)
	{
		if (string == null) return false;
		int stringSize = string.length();
		int size = this.length();
		
		if (stringSize != size) return false;
		
		for (int k = 0; k < size; k++)
		{
			if (this._values[k] != string._values[k]) return false;
		}
		return true;
	}
	
	public boolean contentEquals(String string)
	{
		if (string == null) return false;
		int stringSize = string.length();
		int size = this.length();
		
		if (stringSize != size) return false;
		
		for (int k = 0; k < size; k++)
		{
			if (this._values[k] != string.charAt(k)) return false;
		}
		return true;
	}
	
	public static JSString add(JSString a, JSString b)
	{
		int aSize = a.length();
		int bSize = b.length();
		char[] resultChars = Arrays.copyOf(a._values, aSize + bSize);
		for (int k = 0; k < bSize; k++)
		{
			resultChars[k + aSize] = b._values[k];
		}
		
		JSString result = new JSString();
		result._values = resultChars;
		
		return result;
	}
}