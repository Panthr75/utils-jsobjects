package javascript;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JSObject
{
	/**
	 * Whether or not this object is frozen
	 */
	boolean frozen;
	
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