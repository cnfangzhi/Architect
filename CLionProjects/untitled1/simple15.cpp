//
// Created by fangzhi on 2021/1/16.
//

#include <iostream>

using namespace std;

template <typename T>
T add(T num1, T num2) {
    cout << "模板函数被调用" << endl;
    return num1 + num2;
}

// 当普通函数和模板函数同时存在的时候，优先会调用普通函数
//普通函数
int add(int number1 , int number2){
    cout << "int add 被调用" << endl;
    return number1 + number2;
}

// 打算生成 3 个方法 ， 发现有一个 int 函数就不会额外生成，发现 double float 没有，就会生成两个
void test() {
    int sum1 = add(1,2);

    double sum2 = add(1.0 , 2.3);

    float sum3 = add(1.0f, 2.4f);

    cout << sum1 << " , " << sum2 << endl;
}

// 模板类
template <typename T>
class Callback
{
public:
    void onError() {

    }

    void onSucceed(T result) {
        cout << result << endl;
    }
};

class HttpCallback : public Callback<int> {

};

void test2() {
    HttpCallback *callback = new HttpCallback();
    callback->onSucceed(12);
}

int main(){
    test2();
    return 0;
}

