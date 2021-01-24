#define _CRT_SECURE_NO_WARNINGS
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// 写一个打印数组的方法
void print(int* arr,int length){
    // 获取数组的大小
    for (int i = 0; i < length; i++)
    {
        printf("%d  ",arr[i]);
    }
}

struct MyStruct
{
    char* name;
};

typedef struct MyStruct Student;// 给 MyStruct 取一个别名叫做 Student

// int32_t ，可以对基本数据类型取别名
typedef int int32;


void test(){
    int a;// 告诉c和c++编译器开辟一块连续大小的 4 字节的内存空间
    int arr[] = {1,2,3,4,5,6};

    // 求数组的大小？
    int size = sizeof(arr) / sizeof(int);

    printf("size = %d\n",size);

    print(arr,size);

    getchar();
}

void test2(){
    int a;// 告诉c和c++编译器开辟一块连续大小的 4 字节的内存空间
    int arr[] = { 1, 2, 3, 4, 5, 6 };// arr 数据类型的内存大小空间 24

    a = 10;

    // 12 , 16 , 12 , 36   36？
    printf("%d, %d, %d ,%d", arr, arr+1, &arr, &arr+1);


    getchar();
}

void test3(){
    Student stu = { "Darren" };

    int32 number1 = 100;

    printf("number1 = %d",number1);

    // 获取 Bitmap ，锁定画布 void**
    // void number;// 错误

    // memcpy();// 拷贝内存，什么用 void*

    getchar();
}

char* getStr1(){
    char* str = "12345";
    return str;
}

char* getStr2(){
    char* str = "12345";
    return str;
}

char* getStr3(){
    char buff[128];

    strcpy(buff,"12345");

    return buff;
}

char* getStr4(){
    char* buff = malloc(128); // char* buff char* 是四字节的数据类型，存的是堆区开辟的 128 个字节的首地址
    strcpy(buff, "12345");

    return buff;
}

void test4(){

	char* str1 = NULL;

	char* str2 = NULL;

	str1 = getStr1();

	str2 = getStr2();

	char* str3 = getStr3();

	char* str4 = getStr4();

	printf("%d , %d, %s, %s",str1,str2,str3,str4);// str3 = 12345

	getchar();
}



void test5(){
    int a = 10;

    int b = 10;
    // 0508 , 0496  a 的地址要大于 b 的地址 （debug）
    // 016  , 020   a 的地址要小于 b 的地址 （release）
    // buffer 数据类型跟栈的开口方向无关（都是向上）

    char buff[10];
    // buff+1 > buff
    printf("%p , %p, %p, %p",&a,&b,buff,buff+1);

//    int a = 100;
    int* ff = &a;// 虽说是 a 的地址，但是我也可以把 ff 看做一个数组

    // char** 二维数组 char** 二级指针

    ff += 1;

    getchar();
}
// 理解指针的关键还是在于内存 ，指针的大小是 4 字节 int* double* 指针存放的是一块内存的地址 4 字节 ，但是也会有数据类型


void change1(int number){// 不能修改
    number = 300;
    printf("%p\n",number);
}

void change2(int* number){// 可以
    *number = 300;
}

void test6(){

    int a = 100;

    // 修改 a 的值

    a = 200;
    printf("%p\n", &a);

    change1(a);// a变量的值复制给了 number 变量 ，number是在另一个函数中，number 是一个新的变量
    printf("a = %d", a);

    change2(&a);// a 的地址复制给了 number 变量 ，number是在另一个函数中，number 是一个新的变量

    printf("a = %d",a);// a = 200 ?


    getchar();
}

void changeNumber(int* number){
    *number = 300;
}

// 2. 指针间接赋值的意义
void test7(){

    // 通过指针去修改值

    int number = 100;

    // 在同一个方法里面，直接复制
    number = 200;

    // 通过指针去赋值，其实就是为了在其他方法中去间接赋值
    // int* p = &number;
    // *p = 300;
    changeNumber(&number);

    // 在做开发的时候，离不开指针，指针出现的意义有很多，其中一种就是通过方法去修改值
    // 1.获取Bitmap信息
    // AndroidBitmapInfo android_bitmap_info; // 结构体
    // AndroidBitmap_getInfo(env, bitmap, &android_bitmap_info); // 通过 getInfo 方法去获取 Bitmap 的属性
    // 获取bitmap的 宽，高，format
    // int bitmap_width = android_bitmap_info.width;
    // int bitmap_height = android_bitmap_info.height;

    // c 和 java 的方法不同所在，c 可以传对象的地址在方法里面去赋值，java 一般都是通过返回值

    // 模拟一下指针方法赋值的写法（很常用）
    // java 写法
    // AndroidBitmapInfo android_bitmap_info = AndroidBitmap_getInfo(env, bitmap);
    // c 的写法
    printf("%d",number);

    // 试用期的分数 100  60分业务逻辑 15分框架维护 10分小游戏框架的维护 15架构的性能优化 50fps 60fps 优化包的体积

    getchar();
}


typedef struct AndroidBitmapInfo
{
    int width;
    int height;
    int format;
} AndroidBitmapInfo;// 定义了一个结构体取了一个别名 AndroidBitmapInfo ，结构体的名字是 匿名

void AndroidBitmap_getInfo(AndroidBitmapInfo* bitmapInfo){
    // 通过指针在方法里面赋值
    bitmapInfo->width = 200;
    bitmapInfo->height = 200;
}

// 模拟 AndroidBitmap_getInfo

void test8(){
    // 通过指针间接赋值很常见
    AndroidBitmapInfo androidBitmapInfo;
    AndroidBitmap_getInfo(&androidBitmapInfo);

    printf("width = %d, height = %d",androidBitmapInfo.width,androidBitmapInfo.height);

    getchar();
}

// 模拟 AndroidBitmap_getInfo
void test9(){
    // 通过指针间接赋值很常见 (堆内存上开闭)
    AndroidBitmapInfo *androidBitmapInfo = (AndroidBitmapInfo*)malloc(sizeof(AndroidBitmapInfo));
    AndroidBitmap_getInfo(androidBitmapInfo);

    printf("width = %d, height = %d", androidBitmapInfo -> width, androidBitmapInfo -> height);

    // 释放
    if (androidBitmapInfo != NULL){
        free(androidBitmapInfo);
        // 避免出现野指针的情况，减少程序代码的出错
        androidBitmapInfo = NULL;
    }

    // 这个地方写了 n 行代码，省略 n 行
    // 再次释放,会有问题
    if (androidBitmapInfo){
        free(androidBitmapInfo);
    }

    getchar();
}

void test10(){
    // 写入位置 0x00000000 时发生访问冲突
    char* p1 = NULL;// p1 = NULL ，NULL 也是地方 ，指针指向 NULL 相当于指向 0x00000000

    // 但是我们并不能对 0x00000000 去操作，这块地方是 c 和 c++ 编译器所持有的

    // Student student = null; 没有去纠结 null 是啥，跟 c 和 c++ 有点类似

    strcpy(p1,"1122");

    getchar();
}

// 做字符串的强化
void test11(){
    // char buff[100] = { 'd','a', 'r', 'r', 'e', 'n' };// 后面 6 - 99 都是默认值 0
     char buff[5] = { 'd', 'r', 'r', 'e', 'n' };
    // char buff[] = {'d','r','r','e','n'}; // 长度是 12（'\0'） ，size 是 5 （默认统计里面的个数）
    // char buff[2] = { 'd', 'r', 'r', 'e', 'n' };// 编译不通过，长度超出
    // char buff[100] = { 0 };// 把数组初始化为 0
    // char buff[100] 数据都是默认值 -52

    // char buff[] = "123456";// len 是 6 （'\0'），size 是 7 ？
    // 相当于 char buff[] = {1，2，3，4，5，6，\0}
//    char* buff = "123456";

    // 纠结一下 char buff[] = "123456" 和 char* buff = "123456"; malloc 的方式  啥区别 ？
    // 字符串可以在任何地方开辟内存，栈区，堆区，常量区

    // 大小 size 100
    int len = strlen(buff);// len 5  碰到 '\0' 就结束了
    int size = sizeof(buff);

    printf("len = %d, size = %d\n",len,size);

    // 内容
    printf("%d,%d,%d,%d", buff[0], buff[66], buff[99],buff[77]);

    getchar();
}

int AndroidBitmap_getInfo2(AndroidBitmapInfo* bitmapInfo){

    // 对 bitmapInfo 进行再次赋值

    //  2. 一定要考虑健壮性 , 自己的方法会报错
    if (bitmapInfo == NULL){
        // 3. 要异常错误进行抛出说明 音视频 ffmpeg
        printf("bitmapInfo 是 NULL 无法进行操作，错误码 %d",-1);
        return -1;
    }
    // 可能还会有 -xx

    // 通过指针在方法里面赋值
    bitmapInfo->width = 200;
    bitmapInfo->height = 200;
    return 0;// 一般 0 代表正常情况
}

// 模拟 AndroidBitmap_getInfo
void test12(){
    // 通过指针间接赋值很常见 (堆内存上开闭)
    struct AndroidBitmapInfo *androidBitmapInfo = (AndroidBitmapInfo*)malloc(sizeof(AndroidBitmapInfo));

    // 释放后再调用
    int res = AndroidBitmap_getInfo2(androidBitmapInfo);

    if (res == 0){
        // ...
        // 会出错 ， 调用者也会报错，但我并不知道错误所在
        printf("width = %d, height = %d", androidBitmapInfo->width, androidBitmapInfo->height);
    }

    // 释放
    if (androidBitmapInfo != NULL){
        free(androidBitmapInfo);
        // 避免出现野指针的情况，减少程序代码的出错
        androidBitmapInfo = NULL;
    }

    getchar();
}

// 4. 不要直接轻易的去改变调用传递给你的指针 打个比方
int strlen2( char* const str){
    // 临时变量,套路
    char* countStr = str;

    int count = 0;
    while (*countStr)// != '\0'
    {
        countStr++;
        count++;
    }

    // 自己想看看结果对不对
    printf("str = %s,len = %d\n",str,count);

    return count;
}

void test13(){
    char* str = "123456";

    int len = strlen2(str);

    printf("len = %d",len);
    getchar();
}

void main(){
    test11();
}
