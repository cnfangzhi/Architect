//
// Created by fangzhi on 2020/12/21.
//

#include <iostream>
#include <stdarg.h>
#include "student.h"

using namespace std;


// 可变参数
/*void sum(int count, ...) {
    va_list vp;
    va_start(vp, count);

    int number = va_arg(vp, int);
    cout << number << endl;

    number = va_arg(vp, int);
    cout << number << endl;

    number = va_arg(vp, int);
    cout << number << endl;
}*/

int sum(int count, ...) {
    va_list vp;
    // 可变参数开始方法
    va_start(vp, count);

    int sum = 0;
    for (int i = 0; i < count; i++) {
        sum += va_arg(vp, int);
    }
    va_end(vp);
    return sum;
}

void test1() {
    int number = sum(3, 5,6,7);
    cout << number << endl;
}

class Student1
{
public:
    char* name;
    int age;
    static int tag;

    Student1() {
        tag = 12;
    }

public:
    static void change() {
        tag += 12;
    }

    void change1() {
        this -> change();
    }
};
// 静态属性在c++ 中必须要初始化，初始化必须这么写
int Student1::tag = 12; // 静态 可以直接用类名去操作 ::
// 静态的属性必须要初始化
// 静态的方法只能去操作静态的属性或方法

void test2() {
    Student1 stu;

    stu.change1();

    cout << Student::tag << endl;
}

// 对象的大小
class A
{
public:
    double b;
    int a;
    char c;
};

class B
{
public:
    double b;
    int a;
    char c;
    static double d;
};

class C
{
public:
    double b;
    int a;
    char c;
    C(int a){
        this->a = a;
    }
public:
    int getA(){
        return this->a;
    }
};

// 对象的大小和结构体大小计算方式类似
// static静态变量和方法不会算到类的大小中
// 栈、堆、全局（静态、常量、字符串）、代码区，类的大小只与普通属性有关系
void test3() {
    cout << "A 的大小：" << sizeof(A) << endl;
    cout << "B 的大小：" << sizeof(B) << endl;
    cout << "C 的大小：" << sizeof(C) << endl;
}

// this指针：代表当前的对象
class Student2
{
public:
    char* name;
    int age;

    // const修饰函数
public:
    // this = const Student2* const this; 不能被修改
    // const 在()之后，不能对对象的属性进行修改
    void change() const{
//        this->age += 12;
//        this = (Student2*) 0x0011;
    }
};

// 友元函数
class Person
{
private:
    int age = 0;

public:
    Person(int age) {
        this->age = age;
    }

    int getAge() {
        return this->age;
    }

    // 友元函数的声明
    friend void friend_change(Person *person, int age);
};

void friend_change(Person *person, int age) {
    person->age = age;
}

void test4() {
    Person person = Person(24);
    friend_change(&person, 36);
    cout << person.getAge() << endl;
}

void test5() {
    Student *stu = new Student("aaa", 11);
    Student::changeTag(22);
    changeAge(stu, 23);
    stu->print();
    delete(stu);
}

// 友元类
class ImageView
{
public:
    friend class Class; // Class 是 ImageView的友元类

private:
    int a;

};

class Class
{
public:
    ImageView aObj;

    void changeA(int number) {
        aObj.a = number;
    }

    int getA() {
        return aObj.a;
    }

};

void test6() {
    Class b;
    b.changeA(24);
    cout << b.getA() << endl;
}

int main() {
    test6();
    return 0;
}


