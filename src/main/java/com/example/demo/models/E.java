package com.example.demo.models;


import com.example.demo.annotations.ClassDocumentation;
import com.example.demo.annotations.MethodDocumentation;

@ClassDocumentation("This is a class E")
public class E {

    @MethodDocumentation("This is a method EA inside E")
    public void EA() {
        System.out.println("EA");
    }

    @MethodDocumentation("This is a method EB inside E")
    public void EB() {
        System.out.println("EB");
    }

    @MethodDocumentation("This is a method EC inside E")
    public void EC() {
        System.out.println("EC");
    }

    @MethodDocumentation("This is a method ED inside E")
    public void ED() {
        System.out.println("ED");
    }
}
