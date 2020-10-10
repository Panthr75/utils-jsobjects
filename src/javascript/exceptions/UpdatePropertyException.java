package javascript.exceptions;

import javascript.*;

public class UpdatePropertyException extends RuntimeException
{
	public enum UpdateType
	{
		ADD,
		SET,
		DELETE
	}
	
	private static final long serialVersionUID = 1L;

	public UpdatePropertyException(String property, JSObject object, UpdateType type)
	{
		super(genMessage(property, object, type));
	}
	
	private static String genMessage(String property, JSObject object, UpdateType type)
	{
		if (type == UpdateType.ADD)
		{
			return "Cannot add property '" + property + "', object is not extensible";
		}
		else if (type == UpdateType.DELETE)
		{
			return "Cannot delete property '" + property + "' of " + getObjectString(object);
		}
		else if (type == UpdateType.SET)
		{
			return "Cannot set property '" + property + "' of " + getObjectString(object);
		}
		else
			return null;
	}
	
	private static String getObjectString(JSObject obj)
	{
		Class<?> clazz = obj.getClass();
		String name = clazz == JSObject.class ? "object" : clazz.getSimpleName();
		return "[Object " + name + "]";
	}
}
