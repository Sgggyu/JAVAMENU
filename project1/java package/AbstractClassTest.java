

abstract class Vehicle {
    public abstract void move();
}

// 汽车
class Car extends Vehicle {
    @Override
    public void move() {
        System.out.println("汽车正在移动");
    }
}

// 自行车
class Bicycle extends Vehicle {
    @Override
    public void move() {
        System.out.println("自行车正在骑行");
    }
}

// 飞机
class Airplane extends Vehicle {
    @Override
    public void move() {
        System.out.println("飞机正在飞行");
    }
}

// 测试
public class AbstractClassTest {
    public static void main(String[] args) {
        // 多态
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();
        Vehicle airplane = new Airplane();

        // 调用 move 方法
        car.move();
        bicycle.move();
        airplane.move();
    }
}
