package com.bjpowernode.test;

import com.bjpowernode.Car;
import com.bjpowernode.service.Engine;
import com.bjpowernode.service.SuvEngine;

public class 组装越野车 {
    public static void main(String[] args) {
        //1. 获得越野车发动机
        Engine engine = new SuvEngine();
        //2. 将发动机装车
        Car car = new Car(engine);
        //3. 发动汽车
        car.run();
    }
}
