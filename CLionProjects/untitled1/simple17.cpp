//
// Created by fangzhi on 2021/1/17.
//

#include <iostream>
#include <algorithm>// STL 算法包
#include <cctype> // 用到一个函数指针，回调函数

using namespace std;

void test1() {
    string str("1234456");

    // 字符串的遍历
    for (int i = 0; i < str.length(); i++) {
        cout << str[i] << endl;
    }

    // 迭代器遍历
    for (string::iterator it = str.begin(); it < str.end(); it++) {
        cout << *it << endl;
    }

    try {
        for (int i = 0; i < str.length() + 2; i++) {
            cout << str.at(i) << endl; // 如果越界会抛异常
        }

        for (int i = 0; i < str.length() + 2; i++) {
            cout << str[i] << endl; // 会导致程序宕机
        }
    } catch (...) {
        cout << "异常了" << endl;
    }
}

void test2() {
    // 添加
    string str1 = "123";
    string str2 = "456";
    str1 = str1 + str2;
    str2.append(str1);

    cout << str1.c_str() << endl;
    cout << str2.c_str() << endl;
}

void test3() {
    // 删除
    string str1 = "123 abc 123 abc 123";
//    str1.erase(0, 3); // 第一个参数：从哪里开始 ； 第二个参数：删除几个（默认值，字符串的结尾）

    // 迭代器删除  2 bc 123 abc 123 解释
    for (string::iterator it = str1.begin(); it<str1.begin()+3; it++)// 删除一个字后都会从头开始计算
    {
        str1.erase(it);
    }
    cout << str1.c_str() << endl;
}

// 替换
void test4(){

    string str1 = "123 abc 123 abc 123";
    // 第一个参数：从哪里开始
    // 第二个参数：替换几个
    // 第三个参数：替换成谁
    str1.replace(0,6,"1234");

    cout << str1.c_str() << endl;

}

// 查找
void test5(){

    string str1 = "123 abc 123 abc 123";
    // 查找谁，从哪里开始
     int position = str1.find("123",0);
    // 从后面往前面查
    int position2 = str1.rfind("123");

    cout << position << endl;
    cout << position2 << endl;
    cout << str1.length() << endl;

}

// 大小写转换
void test6(){

    string str1 = "AAA abc BBB abc 123";
    // 转换成大写
     transform(str1.begin(), str1.end(), str1.begin(), toupper);

//    transform(str1.begin(), str1.end(), str1.begin(), tolower);

    cout << str1.c_str() << endl;

}

void test(){
    // 寄存器上面的变量不能取地址，register 快速
    register int a = 10;
    // c++ 很多默认情况下就会有 register 增强
    printf("%d", &a);

    for (register int i = 0; i < 10; i++)
    {

    }

}

// 2.2 c / c++ 之间的区别
// c 里面 const 是一个伪命题，可以通过指针去操作，编译时期的检测
// c++ 里面不光在编译时期做处理，还在运行时做了处理
void test7(){
    const int number = 12;
//     number = 23;
    int *number_p = const_cast<int*>(&number);// 计算通过 const_cast 转换已经不能修改值
    *number_p = 20;
    cout << number << endl;
    cout << *number_p << endl;

}

// 3. 引用加强
// 3.1 引用的使用场景(回顾，好处)
// 3.2 引用的本质剖析
void change(int& number1,int& number2){
    int temp = number1;
    number1 = number2;
    number2 = temp;
}
// 其实引用本质就是 指针
void change(int& number1){// 相当于 change(int* number1)
    number1 = 12;// 相当于 *number1 = 12;
}

void test8(){

    int number1 = 10;
    int number2 = 20;

    change(number1);

    cout << number1 << endl;

}

int get(){
    int number = 10;
    return number;
}

int& get1(){
    int number = 10;
    return number;
}


// 引用做左值和右值的问题
void test9(){

    int number1 = get(); // 10
    cout << number1 << endl;

    int number2 = get1(); // 10
    cout << number2 << endl;

    int& number3 = get1(); //? 野指针 12478
    cout << number3 << endl;

}

class Sudent
{
private:
    string name;
public:
    Sudent(string name){
        this->name = name;
    }

public:
    string getName(){
        return this->name;
    }

    string& getName2(){
        return this->name;
    }
};

void test11(){

    Sudent stu = Sudent("Darren");

    // 改不动
     stu.getName() = "Jack";
    cout << stu.getName().c_str() << endl;

    // 加引用后可以修改
    stu.getName2() = "Jack";
    cout << stu.getName().c_str() << endl;

    // vector front back

}

class Exception : public out_of_range
{
public:
    Exception(string msg) :out_of_range(msg){
        cout << "构造函数" << endl;
    }

    ~Exception(){
        cout << "析构函数" << endl;
    }
};

void c_method(){// 如果是自己的写 ，1000 多行
    cout << "抛异常" << endl;
    throw Exception("异常了");
//    throw new Exception("异常了");
}

void test12(){
    try{
        c_method();
    }
    catch (Exception& e){
        // Exception Exception&：多次析构函数
//        抛异常
//        构造函数
//        析构函数
//        try异常:异常了
//        析构函数
//        析构函数

        // Exception&
//        抛异常
//        构造函数
//        析构函数
//        try异常:异常了
//        析构函数
        cout << "try异常:"<< e.what() << endl;



        // Exception*
//        抛异常
//        构造函数
//        try异常:异常了
//        析构函数
//        cout << "try异常:"<< e->what() << endl;
//        delete e;
    }
    catch (...){
        cout << "其他异常" << endl;
    }

}

int main() {
    test12();
    return 0;
}

