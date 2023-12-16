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
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java/com/example/demo/models"))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(AnnotationFinder::processFile);
        } catch (IOException e) {
            System.out.println("Error reading files");
        }
    }

    private static void processFile(Path path) {
        Path outputPath = Paths.get("javadoc.txt");

        try {
            CompilationUnit cu = JavaParser.parse(path.toFile());

            for (TypeDeclaration type : cu.getTypes()) {

                // Check if class is annotated
                if (type.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(ClassDocumentation.class.getSimpleName()))) {
                    System.out.println("Class "+type.getName()+" is annotated with @ClassDocumentation");

                    Comment commentOpt = type.getComment();
                    if (commentOpt instanceof JavadocComment) {
                        JavadocComment comment = (JavadocComment) commentOpt;
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
                        String javadoc = "Class " + type.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + type.getName() + " has no JavaDoc comment");
                    }
                }

                for (BodyDeclaration member : type.getMembers()) {
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) member;
                        if (method.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(MethodDocumentation.class.getSimpleName()))) {
                            System.out.println("Method "+method.getName()+ " in class "+type.getName()+" is annotated with @MethodDocumentation");

                            Comment commentOpt = method.getComment();
                            if (commentOpt instanceof JavadocComment) {
                                JavadocComment comment = (JavadocComment) commentOpt;
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
