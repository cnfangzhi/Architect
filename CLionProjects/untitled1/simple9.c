//
// Created by fangzhi on 2020/12/15.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char name[50];
    int age;
} Student;

void test() {
    Student stu1 = {"haha", 22};
    Student stu2 = stu1;

    printf("%p, %p\n", &stu1, &stu2);
    printf("%s, %d\n", stu2.name, stu2.age);
}

typedef struct {
    char* name;
    int age;
} Student2;

void test2() {
    Student2 stu;
    stu.name = "hahaah";
    stu.age = 12;

    Student2 stu2 = stu;

    printf("%p, %p\n", stu.name, stu2.name);
    printf("%p, %p\n", &(stu.age), &(stu2.age));
    printf("%s, %d\n", stu2.name, stu2.age);

    printf("size = %d\n", sizeof(Student2));
    printf("size = %d\n", sizeof(Student));
}

typedef struct {
    int year;
    int month;
    int day;
//    char min;
} Date;

typedef struct {
//    double size;
    double number;
    char sex;
    int age;
    char name[10];
    Date birthday;

} Student3;

void test3() {
    Student3* stu = NULL;

    // 结构体内部的偏移量问题
    printf("%d\n", &(stu->number) - (int)stu); // 0
    printf("%d\n", &(stu->sex) - (int)stu);  // 4
    printf("%d\n", &(stu->age) - (int)stu); // 8
    printf("%d\n", &(stu->name) - (int)stu); // 12
    printf("%d\n", &(stu->birthday) - (int)stu); // 24
    printf("%d\n", sizeof(Student3)); // 36
    printf("%d\n", sizeof(Date)); // 36

}

void main() {
    test3();
}
