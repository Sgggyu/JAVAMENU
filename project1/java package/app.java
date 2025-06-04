import java.util.Random;
import java.util.Random; 




//1

class Calc{
    public int add(int a,int b){
        return a+b;
    }
    public int subb(int a, int b){
        return a-b;
    }
    public int fact(int a, int b){
        return a*b;
    }
    public double divi(int a,int b){
        return (double)a/(double)b;
    }
}


//2


class ScoreTest {
    public double[] scoreList = new double[50]; 

    public void init() { //  初始化 
        Random random = new Random(); 
        for (int i = 0; i < 50; i++) {
            scoreList[i] = random.nextDouble() * 100; 
        }
    }

    public double get_Sum() {
        double sum = 0; 
        for (int i = 0; i < 50; i++) {
            sum += scoreList[i]; 
        }
        return sum;
    }

    public double get_Avg() {
        return get_Sum() / 50; 
    }

    // 打印所有成绩
    public void showScores() {
        System.out.println("所有成绩如下：");
        for (int i = 0; i < 50; i++) {
            System.out.printf("%.2f  ", scoreList[i]); 
            if ((i + 1) % 10 == 0) { 
                System.out.println();
            }
        }
    }
}

//3 

class Elephant{
    public int flag = 0;// 代表冰箱状态0是关闭 1是打开
    public void open(){
        if(flag == 0){
            System.out.println("冰箱打开成功了");
            flag = 1;
        }
        else{
            System.out.println("已经打开了！");
        }
    }
    public void put_in(){
        if (flag == 0){
            System.out.println("冰箱是关的，请先打开冰箱");
        }
        else{
            System.out.println("成功装入大象！");
        }
    }
    public void close(){
        if (flag == 0){
            System.out.println("已经关闭了!");
        }
        else{
            System.out.println("关闭成功了！");
            flag = 0;
        }
    }
    public void fridge_status(){
        if (flag == 0){
            System.out.println("冰箱是关闭状态");
        }
        else{
            System.out.println("冰箱是打开状态");
        }
    }
}


class Cubx {
    public double length;
    public double width;
    public double height;


    public Cubx() {
        this.length = 1;
        this.width = 2;
        this.height = 3;
    }

    public double getVolume() {
        return length * width * height;
    }

}

class Cubx_in{
    public double length;
    public double width ;
    public double height;
    public Cubx_in(double a, double b, double c){
        length = a;
        width = b;
        height = c;
    }
    public double get_volum(){
        return length * width * height;
    }
}

public class app {
    public static void main(String[] args) {
        //测试 计算器
        System.out.println("计算器测试\n");
        Calc calulate = new Calc();
        int a = 100,b = 33;
        System.out.printf("the result of add: %d\n",calulate.add(a, b));
        System.out.printf("the result of subb: %d\n",calulate.subb(a, b));
        System.out.printf("the result of fact: %d\n",calulate.fact(a, b));
        System.out.printf("the result of calulate: %.2f\n",calulate.divi(a, b));

        System.out.println("\n");


        //测试 随机数组
        System.out.println("随机数组测试\n");
        ScoreTest test = new ScoreTest();
        test.init(); 
        test.showScores();

        // 输出总成绩和平均成绩
        
        System.out.printf("\n总成绩：%.2f\n", test.get_Sum());
        System.out.printf("平均成绩：%.2f\n", test.get_Avg());
        System.out.println("\n");



        //测试大象冰箱
        Elephant elep = new Elephant();
        System.out.println("人：冰箱状态为:");
        elep.fridge_status();
        System.out.println("人：关闭冰箱！");
        elep.close();
        System.out.println("人：打开冰箱!");
        elep.open();
        System.out.println("人：把大象放进去!");
        elep.put_in();
        System.out.println("人：关闭冰箱!");
        elep.close();
        System.out.println();
        //测试立方体
        Cubx cubx1 = new Cubx();
        System.out.println("长宽高为1，2，3，立方体体积为:");
        System.out.println(cubx1.getVolume()); //答案为6
        System.out.println("\n");

        //有参数版本
        Cubx_in cubx2 = new Cubx_in(1,2,3); //构造函数参数初始化
        System.out.println("长宽高为1，2，3，立方体体积为:");
        System.out.println(cubx2.get_volum());
        System.out.println("\n");

    }
}
