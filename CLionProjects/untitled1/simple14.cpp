//
// Created by fangzhi on 2020/12/24.
//

#include <iostream>

using namespace std;

// 属性初始化

class Person
{
protected:
    char* name;
    int age;
public:
    Person(char* name, int age) {
        this->name = name;
        this->age = age;
    }
};

class Course
{
private:
    string name;
public:
    Course(string name) {
        this->name = name;
    }

public:
    string _name() {
        return this->name;
    }
};

class Student : public Person
{
private:
    Course course;
public:
    Student(char* name, int age, string courseName) : Person(name, age), course(courseName) {
//        this->course = Course(courseName);
    }

    void print() {
        cout << name << "," << age << "," << course._name().c_str() << endl;
    }
};

void test1() {
    Student stu("aa", 12, "22222");
    stu.print();
}

// 多继承， 在Java 里面不允许多继承， c++ 是可以的，但是也不能有二义性（歧义）

class Person2
{
private:
    char* name;

public:
    Person2(char* name) {
        this->name = name;
    }
    char* _name() {
        return this->name;
    };
};

class Child
{
    int age;
public:
    Child(int age) {
        this->age = age;
    }

    int _age() {
        return this->age;
    }
};

class Student2 : public Person2, public Child {
public:
    Student2(char* name, int age) : Person2(name), Child(age) {

    }
};

void test2() {
    Student2 *stu = new Student2("aaa", 12);
    cout << stu->_name() << "," << stu->_age() << endl;
    delete(stu);
}

// 虚函数 virtual （解决二义性问题）
// 确保继承过来的相同属性或者函数，只存在一份拷贝

class A {
public:
    char *name;
};

class B : virtual public A {

};

class C : virtual public A {

};

class D : public B, public C {

};

void test3() {
    D d;
    d.name = "aa";
}

// c++ 中的多态
class Activity {
public:
    virtual void onCreate() {// 支持多态，需要使用虚函数
        cout << "Activity 中的 onCreate" << endl;
    }
};

class MainActivity : public Activity {
public:
    void onCreate() {
        cout << "MainActivity 中的 onCreate" << endl;
    }
};

class WelcomeActivity : public Activity {
public:
    void onCreate() {
        cout << "WelcomeActivity 中的 onCreate" << endl;
    }
};

void startActivity(Activity* activity) {
    activity->onCreate();
}

void test4() {
    // c++ 默认情况下没有多态 需要使用虚函数支持多态
    // c++ 多态：动态多态（子父类），静态多态（函数的重载）

    // java 中的多态
    // 父类指向子类的引用，重写 ，里氏替换原则
    // 程序在编译期间并不知晓运行的状态（我需要运行那个函数），只要在真正运行的过程中才会去找需要运行的方法
    Activity* activity1 = new MainActivity();
    Activity* activity2 = new WelcomeActivity();
    startActivity(activity1);
    startActivity(activity2);
}

// 纯虚函数 类似Java中的抽象类，接口
class BaseActivity {
public:
    void onCreate() {
        initView();
        initData();
    }

    // 子类必须要实现
    virtual void initView() = 0;

    virtual void initData() = 0;
};

// 如果不实现父类的纯虚函数，那么 MainActivity 也会变成抽象类，抽象类不允许实例化
class MainActivity2 : public BaseActivity {
public:
    void initView() {
        cout << "initView" << endl;
    }

    void initData() {
        cout << "initData" << endl;
    }

};

void test5() {
    BaseActivity *activity = new MainActivity2();
    activity->onCreate();
}

// 所有的函数都是虚函数，那么可以认为是接口
class ClickListener {
public:
    virtual void click() = 0;
};

class ImageClickListener : public ClickListener {
public:
    void click() {
        cout << "图片点击" << endl;
    }
};

// 函数指针
void click(void(*c)()) {
    c();
}

void click() {
    cout << "click" << endl;
}

void test6() {
    ClickListener *listener = new ImageClickListener();
    listener->click();

    click(click);
}

// 构造函数和析构函数
class Personf {
protected:
    char* name;
    int age;
public:
    Personf(char* name, int age) {
        this->name = name;
        this->age = age;
        cout << "Person 的构造函数" << endl;
    }

    ~Personf() {
        cout << "Person 的析构函数" << endl;
    }
};

class Studentf : public Personf {
public:
    Studentf(char* name, int age) : Personf(name, age) {
        cout<< "Student 的构造函数" << endl;
    }
    ~Studentf() {
        cout << "Student 的析构函数" << endl;
    }
};

void test7() {
    // 构造函数：先父类 -> 再子类
    // 析构函数：先子类 -> 再父类
    Studentf *stuf = new Studentf("ff121", 12);
    delete stuf;
}

// 模板函数 （Java 中的泛型）
template <typename T>
T add(T number1, T number2) {
    return number1 + number2;
}

void test8() {
    int sum1 = add(1,2);

    cout << sum1 << endl;

    int sum2 = add(1.0, 2.0);

    cout << sum2 << endl;

    int sum3 = add(1.0f, 2.0f);

    cout << sum3 << endl;
}


int main() {
    test7();

    return 0;
}

