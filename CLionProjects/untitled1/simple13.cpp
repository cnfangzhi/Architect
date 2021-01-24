//
// Created by fangzhi on 2020/12/23.
//

#include <iostream>

using namespace std;

class Vector
{
public:
    Vector(int x, int y) {
        this->x = x;
        this->y = y;
    }

    Vector(const Vector &vector) {
        this->x = vector.x;
        this->y = vector.y;
    }

private:
    int x;
    int y;

public:
    void setX(int x) {
        this->x = x;
    }

    void setY(int y) {
        this->y = y;
    }

    int getX() {
        return this->x;
    }

    int getY() {
        return this->y;
    }

    // 重载减号运算符
    // 为什么要用引用，为了防止重复创建对象
    // const 关键常量，为了防止去修改值
    Vector operator - (const Vector &vector) {
        cout << "operator -" << endl;
        int x = this->x - vector.x;
        int y = this->y - vector.y;
        Vector res(x, y);
        return res;
    }

    // 自增运算符
    void operator ++ () { // ++X
        cout << "operator ++ ()" << endl;
        this->x = this->x++;
        this->y = this->y++;
    }

    void operator ++ (int) { // X++
        cout << "operator ++ (int)" << endl;
        this->x = this->x++;
        this->y = this->y++;
    }

    bool operator == (const Vector &vector) {
        cout << "operator ==" << endl;
        return (this->x == vector.x && this->y == vector.y);
    }

    friend ostream& operator << (ostream &_Ostr, const Vector &vector) {
        cout << "operator <<" << endl;
        _Ostr << vector.x << "," << vector.y << endl;
        return _Ostr;
    }

    ostream& operator << (ostream &_Ostr) {
        cout << "operator << 2" << endl;
        _Ostr << this->x << "," << this->y << endl;
        return _Ostr;
    }
};

Vector operator + (Vector &vector1, Vector &vector2) {
    int x = vector1.getX() + vector2.getX();
    int y = vector1.getY() + vector2.getY();
    Vector res(x, y);
    return res;
}

void test1() {
    Vector vector1(2, 3);
    Vector vector2(2, 3);
    Vector vector = vector1 - vector2;
    cout << vector.getX() << ", " << vector.getY() << endl;
    vector++;
    ++vector;
//    cout << vector.getX() << ", " << vector.getY() << endl;
    vector << cout;
    cout << vector << "haha" << endl;

    bool isEqual = vector1 == vector2;
    cout << isEqual << endl;
}

// 括号运算符
class Array
{
public:
    Array(int size) {
        this->size = size;
        this->array = (int*)malloc(sizeof(int) * size);
    }
    ~Array() {
        if (this->array) {
            free(this->array);
            this->array = NULL;
        }
    }
    Array(const Array &array1) {
        this->size = size;
        this->array = (int*)malloc(sizeof(int) * size);
        for (int i = 0; i < array1.size; i++) {
            this -> array[i] = array1.array[i];
        }
    }
private:
    int size;
    int* array;
public:
    void set(int index, int value) {
        array[index] = value;
    }

    int get(int index) {
        return this->array[index];
    }

    int getSize() {
        return this->size;
    }

    int operator [] (int index) {
        cout << "operator []" << endl;
        return this->array[index];
    }
};

void printfArray(Array *array) {
    for (int i = 0; i < array->getSize(); i++) {
        cout << (*array)[i] << endl;
    }
}

void test2() {
    Array *array = new Array(5);
    array->set(0, 0);
    array->set(1, 1);
    array->set(2, 2);

    printfArray(array);

    delete(array);
}

class Person
{
    // 变量修饰符
    // private 本类中使用
    // protected 子类中能使用 （默认值）
    // public 公开，任何地方都可以
public:
    char* name;
    int age;
public:
    Person(char* name, int age) {
        this->name = name;
        this->age = age;
        cout << "Person 构造函数" << endl;
    }

public:
    void print() {
        cout << this->name << ", " << this->age << endl;
    }
};

class Teacher: public Person
{
public:
    Teacher(char* name, int age) : Person(name, age) {
        cout << "Teacher 构造函数" << endl;
    }
};

class Student : public Person
{
private:
    char *course;
    Teacher teacher;
public:
    // : Person(name,age) 调用构造函数初始化父类的属性
    // 不光可以给父类初始化属性，还可以给本类的属性进行初始化，用 , 隔开即可
    Student(char* name, int age, char* course) : Person(name, age), course(course), teacher(name, age) {
        cout << "Student 构造函数" << endl;
    }
    void print() {
        cout << "course:" << course << endl;
        cout << teacher.name << "," << teacher.age << endl;
    }
};



void test3() {
    Student stu("ff", 11, "语文");
    stu.print();
}

int main() {
    test3();
    return 0;
}



