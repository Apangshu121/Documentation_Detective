package com.example.demo.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Creating custom annotation for class

// @Target specifies where the annotation can be used. ElementType.TYPE means it can be used on classes, interfaces, or enums.

// @Retention specifies how long the annotation is retained. RetentionPolicy.RUNTIME means the annotation is retained at runtime,
// which allows it to be read via reflection.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// Below line is used to declare a new annotation type in Java
public @interface ClassDocumentation {
    //value() is a member of the annotation.
    //Members can be of any primitive type, String, Class, enum, another annotation, or an array of these types.
    //In this case, value() is a String that defaults to an empty string. You can use it to store the documentation for the class or method.
    String value() default "";
}

