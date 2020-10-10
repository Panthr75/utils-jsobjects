package javascript;

class Utils 
{
	private Utils() {}
	
	public static boolean sameValueZero(Object a, Object b)
	{
		if (a == null) return b == null;
		else if (b == null) return false;
		else if (a instanceof JSUndefined) return b instanceof JSUndefined;
		else if (a instanceof String) return b instanceof String && ((String)a).contentEquals((String)b);
		
		Class<?> aClass = a.getClass();
		if (aClass == byte.class || aClass == Byte.class) return ((Byte)a).equals(b);
		else if (aClass == short.class || aClass == Short.class) return ((Short)a).equals(b);
		else if (aClass == int.class || aClass == Integer.class) return ((Integer)a).equals(b);
		else if (aClass == long.class || aClass == Long.class) return ((Long)a).equals(b);
		else if (aClass == float.class || aClass == Float.class) return ((Float)a).equals(b);
		else if (aClass == double.class || aClass == Double.class) return ((Double)a).equals(b);
		else if (aClass == boolean.class || aClass == Boolean.class) return ((Boolean)a).equals(b);
		else return a == b;
	}
	
	public static boolean sameValueNonNumeric(Object a, Object b)
	{
		if (a == null) return b == null;
		else if (b == null) return false;
		else if (a instanceof JSUndefined) return b instanceof JSUndefined;
		else if (a instanceof String) return b instanceof String && ((String)a).contentEquals((String)b);
		else if (a instanceof JSString)
		{
			JSString aString = (JSString)a;
			if (b instanceof JSString) return aString.contentEquals((JSString)b);
		}
		
		Class<?> aClass = a.getClass();
		if (aClass == boolean.class || aClass == Boolean.class) return ((Boolean)a).equals(b);
		else return a == b;
	}
	
	public static boolean strictEqualityComparison(Object a, Object b)
	{
		if (a == null) return b == null;
		else if (b == null) return false;
		else if (a instanceof JSUndefined) return b instanceof JSUndefined;
		else if (a instanceof String) return b instanceof String && ((String)a).contentEquals((String)b);
		
		Class<?> aClass = a.getClass();
		if (aClass == byte.class || aClass == Byte.class) return ((Byte)a).equals(b);
		else if (aClass == short.class || aClass == Short.class) return ((Short)a).equals(b);
		else if (aClass == int.class || aClass == Integer.class) return ((Integer)a).equals(b);
		else if (aClass == long.class || aClass == Long.class) return ((Long)a).equals(b);
		else if (aClass == float.class || aClass == Float.class) return ((Float)a).equals(b);
		else if (aClass == double.class || aClass == Double.class) return ((Double)a).equals(b);
		else if (aClass == boolean.class || aClass == Boolean.class) return ((Boolean)a).equals(b);
		else return a == b;
	}
}