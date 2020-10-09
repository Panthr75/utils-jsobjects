package javascript;

public class JSObject
{
	/**
	 * Whether or not this object is frozen
	 */
	boolean frozen;
	
	public static <T extends JSObject> T freeze(T obj)
	{
		obj.frozen = true;
		return obj;
	}
	
	public static boolean isFrozen(JSObject obj)
	{
		return obj.frozen;
	}
}