package javascript.tests;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * An annotation for a Test method, used by the {@link Tester}
 * class.
 * @author Josh
 * @version 10th October, 2020
 */
@Retention(RUNTIME)
@Target(METHOD)
@interface Test
{
	/**
	 * The name of this test
	 * @return The name of this test
	 */
	String testName();
}