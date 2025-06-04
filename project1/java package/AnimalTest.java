// 抽象类方式
abstract class Animal {
    public abstract void makeSound();
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("喵喵叫");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("汪汪叫");
    }
}

class Bird extends Animal {
    @Override
    public void makeSound() {
        System.out.println("叽叽喳喳");
    }
}

public class AnimalTest {
    public static void main(String[] args) {
        Animal cat = new Cat();
        Animal dog = new Dog();
        Animal bird = new Bird();
        
        cat.makeSound();  // 输出：喵喵叫
        dog.makeSound();  // 输出：汪汪叫
        bird.makeSound(); // 输出：叽叽喳喳
    }
}