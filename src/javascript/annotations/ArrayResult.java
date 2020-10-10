package javascript.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Represents that a method returns an array result
 * @author Josh
 * @version 9th October, 2020
 */
@Retention(CLASS)
@Target(METHOD)
public @interface ArrayResult
{
	/**
	 * The class of the type of array the method
	 * returns
	 * @return The class of the type of array the
	 * method returns
	 */
	Class<?> type();
}