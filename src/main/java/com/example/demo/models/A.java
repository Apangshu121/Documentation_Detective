package com.example.demo.models;

import com.example.demo.annotations.ClassDocumentation;
import com.example.demo.annotations.MethodDocumentation;

/**
 * This is a class A.
 *
 * @ClassDocumentation "This is a class A"
 */
@ClassDocumentation("This is a class A")
public class A {

    /**
     * This is a method AA inside A.
     *
     * @MethodDocumentation "This is a method AA inside A"
     */
    @MethodDocumentation("This is a method AA inside A")
    public void AA() {
        System.out.println("AA");
    }

    /**
     * This is a method AB inside A.
     *
     * @MethodDocumentation "This is a method AB inside A"
     */
    @MethodDocumentation("This is a method AB inside A")
    public void AB() {
        System.out.println("AB");
    }

    @MethodDocumentation("This is a method AC inside A")
    public void AC() {
        System.out.println("AC");
    }

    /**
     * This is a method AD inside A.
     *
     * @MethodDocumentation "This is a method AD inside A"
     */
    public void AD() {
        System.out.println("AD");
    }
}
