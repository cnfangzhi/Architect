//
// Created by fangzhi on 2020/12/13.
//

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void test1() {
    char* name = "fangzhi";
    int len = strlen(name);
    printf("strlen=%d\n", len);

    char* temp = name;
    for (int i = 0; i < len; i++) {
        printf("%c", *temp);
        temp++;
    }
    printf("\nname=%s", name);
}

void malloc2(char** str) {
    *str = "fangzhi";
}

void test2() {
    char* name = NULL; // name为指针变量，NULL为指针指向的内存地址
    malloc2(&name);
    printf("name = %s", name);
}

// const 关键字在c 和 c++ 会有区别
void test3() {
    // 常量
    const int number = 100;
//    number = 200;
    int* p1 = &number;
    *p1 = 200;
    printf("num = %d", number);

    // 常量指针 （常量指针指向的值不能被修改）
    const int* numberP = &number;
//    *numberP = 300;
    numberP = NULL;

    // 指针常量 （指针常量的指针不能被修改）
    int number2 = 20;
    int* const numP = &number2;
    *numP = 300;
//    numP = NULL;

    // 值不能改，指针也不能改
    const int* const p2 = &number;
}

// 二级指针的内存模式
// 指针数组：

typedef struct File2
{
    char* fileName;
    int length;
} File;

void test4() {
    char* name = "fangzhi";
    // 二级指针，看出二维数组
    char** nameP = &name;


   File files[2] = {{"in.mp4"},  {"out.mp4"}};
}

void print2(char** strs, int len) {
    for (int i = 0; i<len; i++) {
//        printf("%s\n", strs[i]); // [] 也相当于逻辑指针
        printf("%s\n", *(strs+i));
    }
}

void test5(){
    char* names[2] = { "abc", "111" };
//    char name[10][30] = { "abc", "111" }; //数据从静态常量区copy到栈的buffer里面
//    print2(name, 2); // 打印出错，因为指针++的步长不一样， 步长为30*sizeof(char)
    print2(names, 2);

    int num = 4;
    char** params = (malloc(sizeof(char *) * num));
    for (int i=0; i<num; i++) {
        params[i] = (malloc(sizeof(char) * 100));
        sprintf(params[i], "i = %d", i);
//        strcpy(params[i], "aaaa");
    }
    print2(params, num);

    for (int i = 0; i<num; i++) {
        if (params[i] != NULL) {
            free(params[i]);
            params[i] = NULL;
        }
    }
    if (params != NULL) {
        free(params);
        params = NULL;
    }
}

void initParams(char ***p, int num) {
    char** params = (malloc(sizeof(char *) * num));
    for (int i=0; i<num; i++) {
        params[i] = (malloc(sizeof(char) * 100));
        sprintf(params[i], "i = %d", i);
//        strcpy(params[i], "aaaa");
    }
    *p = params;

}

void test6() {
    char** params = NULL;
    int num = 8;
    initParams(&params, num);
    print2(params, num);
    for (int i = 0; i<num; i++) {
        if (params[i] != NULL) {
            free(params[i]);
            params[i] = NULL;
        }
    }
    if (params != NULL) {
        free(params);
        params = NULL;
    }
}

int main(){
    test6();
    return 0;
}

