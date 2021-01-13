package com.bjpowernode;

import com.bjpowernode.test.Student;

public class Test {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.sayHello("mike");
        System.out.println(stu.add(10, 20));
    }
}
