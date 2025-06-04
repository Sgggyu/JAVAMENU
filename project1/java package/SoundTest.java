// 接口方式
interface Soundable {
    void makeSound();
}

class Lion implements Soundable {
    @Override
    public void makeSound() {
        System.out.println("狮子吼叫");
    }
}

class Frog implements Soundable {
    @Override
    public void makeSound() {
        System.out.println("青蛙呱呱叫");
    }
}

class Duck implements Soundable {
    @Override
    public void makeSound() {
        System.out.println("鸭子嘎嘎叫");
    }
}

public class SoundTest {
    public static void main(String[] args) {
        Soundable lion = new Lion();
        Soundable frog = new Frog();
        Soundable duck = new Duck();
        
        lion.makeSound();  // 输出：狮子吼叫
        frog.makeSound();  // 输出：青蛙呱呱叫
        duck.makeSound();  // 输出：鸭子嘎嘎叫
        // 使用Lambda表达式实现接口
        System.out.println("——————————————————————：");
        System.out.println("使用Lambda表达式赋值实现接口：");
        Soundable cow = () -> System.out.println("牛哞哞叫");
        Soundable sheep = () -> System.out.println("羊咩咩叫");
        Soundable pig = () -> System.out.println("猪哼哼叫");
        
        cow.makeSound();    // 输出：牛哞哞叫
        sheep.makeSound();  // 输出：羊咩咩叫
        pig.makeSound();    // 输出：猪哼哼叫
    }
}