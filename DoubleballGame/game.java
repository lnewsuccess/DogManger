package DoubleballGame;

import java.util.Arrays;
import java.util.Random;//随机数的产生是前面的最大值，加的是最小值扩大参数倍 ，随机数产生默认是[o,输入值）
import java.util.Scanner;

class redBall{      //红球随机数产生
    int[] redBall = new int[6];
    Random ran = new Random();
    int[] assignment(){
        for(int i=0;i<6;i++){
            redBall[i]=ran.nextInt(33)+1;
            for(int k=0;k<i;k++){
                if(redBall[k]==redBall[i]){
                    redBall[i]=ran.nextInt(33)+1;
                }

            }
        }
        return redBall;
    }
}

class bluBall{              //篮球随机数产生
    int blueBall ;   //属性
    Random ran = new Random();//属性
    int assignment(){
        blueBall=ran.nextInt(16)+1; //语句一定包含在方法里我懂了
        return blueBall;
    }
}

class machineNumbers{
    int blueB;
    int[] sun;
    int[] make(){
        redBall red = new redBall();
        int[] redB = red.assignment();
        bluBall blue = new bluBall();
        blueB = blue.assignment();
        sun =new int[7];
        sun[0] = blueB;
        for(int i =1;i<=redB.length;i++){
            sun[i]=redB[i-1];
        }
        return sun;
    }

}
class humanScanner{
    int[] nums;
    int[] make(){
        nums =new int[7];
        Scanner input= new Scanner(System.in);
        System.out.println("请输入蓝球的值");
        nums[0]=input.nextInt();
        for(int i=1;i<7;i++){
            boolean flag =true;
            System.out.println("请输入第"+i+"个红球的值");
            nums[i]= input.nextInt();
            while (flag){
                flag=false;
                if (nums[i]>33||nums[i]<1){
                    System.out.println("请重新输入第"+i+"个红球的值（值在1~33之间）");
                    nums[i]=input.nextInt();
                    flag =true;
                }
            }
        }
        return nums;
    }
}
//接收返回值
//奖励评判    有篮球就为blu 没有就为no 友谊个红球就为o,t,th,f,fi,six
class Award{
    int count=0;
    String a ;
    String b;
    public void deterMine(int[] man ,int[] machine){
        if(man[0]==machine[0]){
            a="blu";
        }
        else{
            a="no";
        }
        for (int i=1;i<7;i++){
            for(int k=1;k<7;k++){
                if(man[i]==machine[k]){
                    count+=1;
                }
            }
        }
        b=a+count;
        System.out.println(b);
        switch (b) {
            case "blu6" -> System.out.println("这是一等奖"); //这种可以不用break来跳出循环 ->来代替：
            case "no6" -> System.out.println("这是二等奖");
            case "blu5" -> System.out.println("这是三等奖");
            case "blu4", "no5" -> System.out.println("这是四等奖");
            case "no4","blu3" -> System.out.println("这是五等奖");
            case "blu2","blu1","blu0" -> System.out.println("这是六等奖");
            default -> System.out.println("脚踏实地吧，哒");
        }
    }
}
public class game {
    public static void main(String[] args) {
        int other;
        int[] scope =new int[7];
        int[] nor = new int[7];
        boolean flag=true;
        Scanner input= new Scanner(System.in);
        while (flag){
            System.out.println("请选择要如何生成号码，1为机器生成，2为手动输入");
            other = input.nextInt();
            switch (other) {
                case 1 -> {
                    //机器随机生成号码
                    machineNumbers nums = new machineNumbers();
                    int[] num = nums.make();
                    System.out.println("机器生成的号码是：" + Arrays.toString(num));
                    flag = false;
                    nor = num;
                }
                case 2 -> {
                    //人工输入号码
                    humanScanner scan = new humanScanner();
                    int[] number = scan.make();
                    System.out.println("您输入的篮球数字是" + number[0] + "你输入的红球数字是" + Arrays.toString(Arrays.copyOfRange(number, 1, 7)));
                    flag=false;
                    nor=number;
                }
                default -> {
                    System.out.println("您输入了非法数字，请重新输入");
                }
            }
        }

        bluBall b = new bluBall();
        int blue = b.assignment(); //篮球随机数
        System.out.println("这一期的篮球号码是"+blue);
        redBall r =new redBall();
        int[] red=r.assignment(); //红球随机数
        System.out.println("这一期的红球号码是"+Arrays.toString(red));//bingo
        scope[0]=blue;
        System.arraycopy(red, 0, scope, 1, 6);
        System.out.println(Arrays.toString(nor));
        Award award=new Award();
        award.deterMine(nor,scope);
    }
}
