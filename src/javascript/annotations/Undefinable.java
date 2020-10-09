package javascript.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Represents something that can return/can be {@link Global.undefined}. This should only
 * be added if the method returns explicitly {@link Global.undefined}, or the field
 * is explicitly set to {@link Global.undefined}. 
 * @author Josh
 * @version 8th October, 2020
 */
@Retention(CLASS)
@Target({ FIELD, METHOD, TYPE_PARAMETER })
public @interface Undefinable 
{

}