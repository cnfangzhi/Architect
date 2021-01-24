//
// Created by fangzhi on 2021/1/16.
//

#include <iostream>

using namespace std;

class Person
{
public:
    string name;
    int age;

public:
    Person(string name, int age) {
        this->name = name;
        this->age = age;
    }
};

class Student : public Person {
public:
    Student(string name, int age) : Person(name, age) {

    }
};

class Worker : public Person {

};

// static_cast 静态转换  用于基本数据类型之间的转换，如把int转换成char
void test() {
    double num1 = 20.02;
    // 直接转换
    int num2 = num1;
    // 用于基本数据类型之间的转换
    int num3 = static_cast<int>(num1);
    cout << "num2 = " << num2 << ", num3 = " << num3 << endl;
    // 2. 把类型转换成另一种类型，用于类层次结构中基类和派生类之间指针或引用的转换
    Student *stu = new Student("haha", 12);
    Person *person1 = stu;
    Person *person2 = static_cast<Person *>(stu);

    cout << person1->name.c_str() << " , " << person1->age << endl;
    cout << person2->name.c_str() << " , " << person2->age << endl;

    Person *person = new Person("Darren", 24);
    // 转成Student
     Student *stu2 = static_cast<Student *>(person);
    cout << stu2->name.c_str() << " , " << stu2->age << endl;
}

// const_cast 常量转换 用于修改常量的值
void test2() {
    const Person *person = new Person("haha", 12);
    cout << person->name.c_str() << ", " << person->age << endl;
//    person->age = 24;
    Person *person1 = const_cast<Person *>(person);
    person1->age = 22;
    person1->name = "aaa";
    // person的值被改名了
    cout << person->name.c_str() << ", " << person->age << endl;
}

// reinterpret_cast 强制类型转换 ，用于转换任意类型，很少使用
void test3() {
    Person *person = new Person("haha", 12);

    long mPtr = (long) person;
// 与 static_cast 区别在于 static_cast 一般用于转换有继承关系的类型 reinterpret_cast 也能转换继承关系的类型
    Person *stu = reinterpret_cast<Person *>(mPtr);
    cout << stu->name.c_str() << ", " << stu->age << endl;
}

// dynamic_cast 动态转换 ，更安全，运行时转换，转换成功返回类型，失败返回空 ，
//      必须要包含多态类型和 static_cast 很类似，但是更安全
void test4() {
    Student *stu = new Student("aaa", 11);
    Person *worker = dynamic_cast<Person *>(stu);
    cout << worker->name.c_str() << ", " << worker->age << endl;
}

class Exception {
public:
    string msg;
public:
    Exception(string msg) {
        this->msg = msg;
    }

    const char *what() {
        return this->msg.c_str();
    }
};

void test5() {
    // c++ 中可以抛任何数据类型，try {} catch(数据类型 变量名) {}
    // throw 抛异常

    try {
        int i = 0;

        if (i == 0) {
            throw Exception("出异常了");
        }

        if (i < 0) {
            throw 12;
        }
    } catch (int number) {
        cout << "捕捉到异常" << number << endl;
    } catch (Exception exception) {
        cout << "捕捉到异常" << exception.what() << endl;
    } catch (...) {
        cout << "捕捉到其他异常" << endl;
    }
}

void test6() {
    string str1 = "123";
    string str2("123");
    string str3(5, 'A');
    string *str4 = new string("123");

    cout << str1.c_str() << endl;
    cout << str2.c_str() << endl;
    cout << str3.c_str() << endl;
    cout << str4->c_str() << endl;

    // string -> char*
    const char* c_str1 = str1.c_str();
    cout << c_str1 << endl;

    // char* -> string
    char* c_str = "Darren";
    string str(c_str);
    cout << str.c_str() << endl;

}

int main(){
    test3();
    return 0;
}

