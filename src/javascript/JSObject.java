package javascript;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JSObject
{
	/**
	 * Whether or not this object is frozen
	 */
	boolean frozen;
	
	/**
	 * The default way of turning an object to a string.
	 * <br/>
	 * Returns <code>[Object ClassName]</code>.
	 */
	@Override
	public String toString()
	{
		Class<?> clazz = this.getClass();
		String name = clazz == JSObject.class ? "object" : clazz.getName();
		return "[Object " + name + "]";
	}
	
	/**
	 * Gets the raw primitive value this object
	 * @return The raw primitive value of this object
	 */
	public Object valueOf()
	{
		return this;
	}
	
	public static Object getPrototypeOf(JSObject obj)
	{
		if (obj == null) throw new IllegalArgumentException("'null' has no prototype");
		Class<?> clazz = obj.getClass().getSuperclass();
		if (clazz == null) return Global.undefined;
		else return clazz.cast(obj);
	}
	
	public static <T extends JSObject> T freeze(T obj)
	{
		obj.frozen = true;
		return obj;
	}
	
	public static boolean isFrozen(JSObject obj)
	{
		return obj.frozen;
	}
	
	public static JSArray keys(JSObject obj)
	{
		if (obj == null) throw new IllegalArgumentException("'null' has no keys");
		Class<?> clazz = obj.getClass();
		JSArray fields = new JSArray((Object[])clazz.getFields());
		fields = fields.filter((entry) -> !Modifier.isStatic(((Field)entry.item).getModifiers()));
		
		JSArray keys = fields.map((entry) -> ((Field)entry.item).getName());
		
		keys.sort();
		
		return keys;
	}
}