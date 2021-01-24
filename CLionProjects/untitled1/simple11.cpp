//
// Created by fangzhi on 2020/12/20.
//

#include <iostream>

using namespace std;

class Student
{
public:
    Student() {
        cout << "空参构造函数" << endl;
    }

    Student(char* name) :Student(name, 0){
        cout << "一个参构造函数" << endl;
//        setName(name);
    }

    Student(char* name, int age) {
        cout << "两个参构造函数" << endl;
        this->name = (char*)malloc(sizeof(char) * 100);
        strcpy(this->name, name);
//        setName(name);
        setAge(age);
    }

    ~Student() {
        cout << "析构函数" << endl;
        if (this->name) {
            free(this->name);
        }
        this->name = NULL;
    }

    // 拷贝构造函数，有一个默认的拷贝构造函数，用来对象之间的赋值
    Student(const Student &stu) {
        cout << "拷贝构造函数" << endl;

        this->name = (char*)malloc(sizeof(char)* 100);
        strcpy(this->name, stu.name);
        this->age = stu.age;
    }

private:
    char* name = NULL;
    int age = 0;

public:
    char* getName() {
        return this -> name;
    }

    void setName(char* name) {
        this->name = name;
    }

    int getAge() {
        return this->age;
    }

    void setAge(int age) {
        this->age = age;
    }

};

void test1() {
//    Student stu;
//    stu.setAge(24);
//    stu.setName("fz");

//    Student stu("fz", 22);

//    cout << stu.getName() << ", " << stu.getAge() << endl;

//    Student *stuP = new Student("fa", 11);
    Student *stuP = new Student("fa");

//    Student *stuP = static_cast<Student *>(malloc(sizeof(Student)));
//    stuP->setAge(14);
//    stuP->setName("eeee");

    cout << stuP->getName() << ", " << stuP->getAge() << endl;
}

void test2() {
    // malloc/free, new/delete
    Student *stu = new Student();
    delete(stu);

    Student *stu2 = (Student*)malloc(sizeof(Student));
    free(stu2);
}

typedef struct {
    int age;
    char* name;
} Person;

void test3() {
    Person person1 = {22, "aaaa"};
    Person person2 = person1; // = 是赋值，把里面所有定义的属性赋值，c/c++编译器帮我们做的
    cout << &person1 << ", " << &person2 << endl;

    cout << person1.name << ", " << person2.age << endl;
}

void test4() {
    Student *stu1 = new Student("jljl", 11);
    Student stu2 = *stu1;
    stu2.setAge(22);

    cout << stu1->getAge() << ", " << stu1->getName() << endl;
    cout << stu2.getAge() << ", " << stu2.getName() << endl;
}


Student getStudent(char* name){
    Student stu(name);// 栈 ，方法执行完，这个对象会被回收，但是发现调用了拷贝构造函数
    cout << &stu << endl;
    return stu;// 会返回一个新的 Student 对象，而栈内开辟的 stu 是会被回收
}


void printStudent(Student stu){// stu 是该方法栈中一个新的对象，拷贝构造函数赋值，方法执行完会调用析构函数
    cout << stu.getName() << " , " << stu.getAge() << endl;
}

void test5(){
    // 1. = 会调用拷贝构造函数
//     Student stu1("Darren", 24);
//     Student stu2 = stu1; // = 是赋值，把里面所有定义的属性赋值，c/c++ 编译器帮我们做的，其实会调用对象的拷贝构造

//     Student stu2;// 声明变量，开辟变量内存
//     stu2 = stu1; // 这个不会去调用拷贝构造函数，但是会赋值 c 的类似

    // 2. 第二种场景 作为参数返回的时候会调用拷贝构造函数
//     Student stu = getStudent("Jack");
//     cout << &stu << endl;

    // 3. 第三种场景 作为参数传递的时候会调用拷贝构造函数
//     Student stu("Darren", 24);
//     printStudent(stu);

    // 知识的补充
    Student stu = getStudent("Jack");

     cout << stu.getName() << " , " << stu.getAge() << endl;

    printStudent(stu);

}

int main() {
    test5();

    return 0;
}

