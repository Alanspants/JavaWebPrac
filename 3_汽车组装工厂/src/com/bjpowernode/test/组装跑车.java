package com.bjpowernode.test;

import com.bjpowernode.Car;
import com.bjpowernode.service.Engine;
import com.bjpowernode.service.SportEngine;

public class 组装跑车 {
    public static void main(String[] args) {
        // 1. 获得一个跑车发动机
        Engine engine = new SportEngine();
        // 2. 将发动机添加到汽车
        Car car = new Car(engine);
        // 3. 发动汽车
        car.run();
    }
}
