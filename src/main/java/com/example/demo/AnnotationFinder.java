package com.example.demo;
import com.example.demo.annotations.ClassDocumentation;
import com.example.demo.annotations.MethodDocumentation;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class AnnotationFinder {
    static List<String> javaDocs = new ArrayList<String>();
    public static void main(String[] args) {
        findAnnotatedClassesAndMethods();
    }

    public static void findAnnotatedClassesAndMethods() {
        // The entire operation is wrapped in a try-with-resources statement, which ensures that the Stream<Path> is closed after it's used, even if an exception is thrown.
        // This line is used to create a Stream<Path> of all files and directories in the specified path, including subdirectories.
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java/com/example/demo/models"))) {
            paths
                    .filter(Files::isRegularFile) //This line is used to filter out all directories and keep only files.
                    .filter(path -> path.toString().endsWith(".java")) //This line is used to filter out all files that do not end with .java.
                    .forEach(AnnotationFinder::processFile); //This line is used to process each file.
        } catch (IOException e) {
            System.out.println("Error reading files");
        }
    }

    private static void processFile(Path path) {
        // Creates a Path instance that represents the path to a file named "javadoc.txt" in the current directory.
        Path outputPath = Paths.get("javadoc.txt");

        try {
            // This line parses the Java source file at the given path into a CompilationUnit.
            CompilationUnit cu = JavaParser.parse(path.toFile());

            // This loop iterates over each type declaration (class, interface, enum, etc.) in the source file.
            for (TypeDeclaration type : cu.getTypes()) {

                // Check if class is annotated with @ClassDocumentation
                if (type.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(ClassDocumentation.class.getSimpleName()))) {
                    System.out.println("Class "+type.getName()+" is annotated with @ClassDocumentation");

                    Comment commentOpt = type.getComment();
                    if (commentOpt instanceof JavadocComment) {
                        JavadocComment comment = (JavadocComment) commentOpt;
                        System.out.println("Class " + type.getName() + " has JavaDoc comment");
                        String javadoc = "Class " + type.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + type.getName() + " has no JavaDoc comment");
                    }

                }else{
                    System.out.println("Class "+type.getName()+" is not annotated with @ClassDocumentation");

                    Comment commentOpt = type.getComment();
                    if (commentOpt instanceof JavadocComment) {
                        JavadocComment comment = (JavadocComment) commentOpt;
                        System.out.println("Class " + type.getName() + " has JavaDoc comment");
                        String javadoc = "Class " + type.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + type.getName() + " has no JavaDoc comment");
                    }
                }

                // This loop iterates over each member of the type. Here the member could be a field (variable), method, constructor, inner class, etc.
                for (BodyDeclaration member : type.getMembers()) {

                    // This line checks if the member is a method or not.
                    if (member instanceof MethodDeclaration) {

                        //  If the member is a method, this line casts it to MethodDeclaration, which allows you to access method-specific information and functionality.
                        MethodDeclaration method = (MethodDeclaration) member;
                        if (method.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(MethodDocumentation.class.getSimpleName()))) {
                            System.out.println("Method "+method.getName()+ " in class "+type.getName()+" is annotated with @MethodDocumentation");

                            Comment commentOpt = method.getComment();
                            if (commentOpt instanceof JavadocComment) {
                                JavadocComment comment = (JavadocComment) commentOpt;
                                System.out.println("Method " + method.getName() + " in class " + type.getName() + " has JavaDoc comment");
                                String javadoc = "Method " + method.getName() + " in class " + type.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + method.getName() + " in class " + type.getName() + " has no JavaDoc comment");
                            }
                        }else{
                            System.out.println("Method "+method.getName()+ " in class "+type.getName()+" is not annotated with @MethodDocumentation");

                            Comment commentOpt = method.getComment();
                            if (commentOpt instanceof JavadocComment) {
                                JavadocComment comment = (JavadocComment) commentOpt;
                                System.out.println("Method " + method.getName() + " in class " + type.getName() + " has JavaDoc comment");
                                String javadoc = "Method " + method.getName() + " in class " + type.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + method.getName() + " in class " + type.getName() + " has no JavaDoc comment");
                            }
                        }
                    }
                }
                System.out.println();
            }

            // StandardCharsets.UTF_8:- This specifies the character encoding to use when writing the data. UTF-8 is a common encoding that can represent any character in the Unicode standard.
            // StandardOpenOption.CREATE:- This specifies that the file should be created if it doesn't already exist.
            // StandardOpenOption.TRUNCATE_EXISTING:- This specifies that the file should be truncated (emptied) if it already exists before writing to it.
            try {
                Files.write(outputPath, javaDocs, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + path);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
