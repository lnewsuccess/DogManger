package ConstructionMethod;
import java.util.Scanner;
import java.util.Arrays;
class Dog{
    private String name ;
    private int age ;
    private int id;
    public Dog(){

    }//默认构造方法一般要列出来
    public Dog(int id,String name,int age){  //以我目前学的只能够一个属性设置一个Set
        this.name=name;
        this.id=id;
        this.age=age;
    }
    public void SetName(String name){
        this.name=name;
    }
    public void SetAge(int age){
        this.age=age;
    }
    public int GetId(){
        return id;
    }
    public void GetDog(){
        System.out.println("狗的编号是"+id+"狗的名字叫"+name+"年龄为"+age);
    }
}
/*
    这个倒是没做什么就有一百多行代码
    输入id和名字就可以显示对应的年龄和没有输入的
    我现在可以调用创建的对象数组
    这种可以使用吗
    对应的方法传入的参数还必须要一样
    1：先输出现有鸡舍的内容
    2：然后判断要对狗舍进行的操作
    3：相应操作对应的输出

    我想要的功能是什么
    输入输出
     */
//狗的管理程序  其实还可以做个增删改查的循环来判断相对现有的狗舍来做些什么修改
class DogManger{ //包括增删，改，查
    Dog[] cs=new Dog[5];
    int count=0;
    //增加
    public void add(Dog dog){//传递进来的时候就得new一个对象

        if (cs.length<count+1){
            int newLength= cs.length*2;
            cs = Arrays.copyOf(cs,newLength);
        }
        cs[count]=dog;
        count++;
    }
    //修改
    public void modify(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入要修改的编号");
        int i = input.nextInt();
        int nums = find(i);
        if(nums==0){
            System.out.println("修改不了");
        }
        else{
            System.out.println("请输入变化过后的名称");
            String name = input.next();
            System.out.println("请输入变化过后的年龄");
            int age = input.nextInt();
            cs[nums].SetAge(age);
            cs[nums].SetName(name);
            cs[nums].GetDog();
        }
    }
    //删除
    public void delete(int id){
        int nums = find(id);
        if(nums==0){
            System.out.println("删除不了");
        }
        else{
            count=count-1;
            for(int i =nums;i<count;i++){
                cs[i]=cs[i+1];
            }
            print();
        }
    }
    //查找

    public int find(int id){
        int i;
        for(i=0;i<count;i++){
            if(cs[i].GetId()==id){
                break;
            }
        }
        if(i==count)
        {
            System.out.print("未查找到相应的编号");
            i = 0;
        }
        return i;
    }
    public void print(){
        for(int i=0;i<count;i++){
            cs[i].GetDog();
        }

    }
}
public class TestOne {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        DogManger man =new DogManger();
        man.add(new Dog(1,"hello",18));
        man.add(new Dog(2,"no",9));
        man.add(new Dog(3,"yes",20));
        man.add(new Dog(4,"why",5));
        man.add(new Dog(5,"guild",6));
        man.add(new Dog(6,"guilt",7));
        man.print();
//        man.modify();
        boolean flag=true;
        while(flag){
            System.out.println("请输入对现有鸡舍的操作（1：增加，2删除，3修改，4查找,结束输入除以上的任何按键）");
            int i = input.nextInt();
            switch (i){
                case 1 -> {
                    System.out.println("请输入要增加鸡的id，name，age");
                    int id = input.nextInt();
                    String name = input.next();
                    int age = input.nextInt();
                    man.add(new Dog(id,name,age));
                    man.print();
                }
                case 2 -> {
                    System.out.println("请输入要删除鸡的编号");
                    int id = input.nextInt();
                    man.delete(id);
                }
                case 3 -> {
                    man.modify();
                }
                case 4 -> {
                    System.out.println("请输入要查找鸡的编号");
                    int id = input.nextInt();
                    int nums=man.find(id);
                    man.cs[nums].GetDog();
                }
                default -> flag=false;
            }
        }

    }

}
