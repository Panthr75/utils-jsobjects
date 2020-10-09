package javascript.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specified that this method is mutatable (it changes the original
 * object). This is useful because of {@link JSObject.#freeze(JSObject)}.
 * <br/>
 * Furthermore, methods implementing this should check if <code>this</code>
 * is frozen via {@link JSObject.#isFrozen(JSObject)} before mutating
 * themselves.
 * @author Josh
 * @version 8th October, 2020
 */
@Retention(CLASS)
@Target(METHOD)
public @interface Mutator 
{

}
