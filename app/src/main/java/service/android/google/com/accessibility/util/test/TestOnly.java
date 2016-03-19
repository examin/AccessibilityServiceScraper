package service.android.google.com.accessibility.util.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by tim on 19.03.16.
 * Denotes that a parameter, field or method return value can only be used for testing purposes.
 * <p/>
 * This is a marker annotation and it has no specific attributes.
 */
@Documented
@Retention(CLASS)
@Target(ElementType.CONSTRUCTOR)
public @interface TestOnly {
}