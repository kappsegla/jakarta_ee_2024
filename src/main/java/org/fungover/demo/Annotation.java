package org.fungover.demo;


public class Annotation {

    @MyAnnotation
    public static void main(String[] args) {
        //Get Class object from classname
        var classInfo = HelloResource.class;
        //Get Class object from object ref
        var objectClassInfo = new HelloResource().getClass();

        System.out.println(classInfo.getMethods().length);
        for (var method : classInfo.getMethods()) {
            System.out.println(method.getName());
           for(var annotation : method.getAnnotations()) {
               System.out.println(annotation);
           }
        }

    }
}
