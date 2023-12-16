package com.example.demo.models;

import com.example.demo.annotations.ClassDocumentation;
import com.example.demo.annotations.MethodDocumentation;

/**
 * This is a class C.
 *
 * @ClassDocumentation "This is a class C"
 */
@ClassDocumentation("This is a class C")
public class C {

    /**
     * This is a method CA inside C.
     *
     * @MethodDocumentation "This is a method CA inside C"
     */
    @MethodDocumentation("This is a method CA inside C")
    public void CA() {
        System.out.println("CA");
    }

    /**
     * This is a method CB inside C.
     *
     * @MethodDocumentation "This is a method CB inside C"
     */
    @MethodDocumentation("This is a method CB inside C")
    public void CB() {
        System.out.println("CB");
    }

    /**
     * This is a method CC inside C.
     *
     * @MethodDocumentation "This is a method CC inside C"
     */
    @MethodDocumentation("This is a method CC inside C")
    public void CC() {
        System.out.println("CC");
    }

    /**
     * This is a method CD inside C.
     *
     * @MethodDocumentation "This is a method CD inside C"
     */
    @MethodDocumentation("This is a method CD inside C")
    public void CD() {
        System.out.println("CD");
    }
}
