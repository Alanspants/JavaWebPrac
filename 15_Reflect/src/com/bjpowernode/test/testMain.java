package com.bjpowernode.test;

import com.bjpowernode.entity.Student;

public class testMain {
    public static void main(String[] args) {

        // 有一个mike学员
        Student mike = new Student();

        // mike没有好好复习，考55
        mike.setScore(55);

        // 回家检查成绩
        int score = mike.getScore();
        if(score < 60){
            System.out.println("打");
        }
    }
}
