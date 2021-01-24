//
// Created by fangzhi on 2020/9/29.
//

#include "com_fz_jni_JniSimple.h"
#include <stdlib.h>

// JNIEXPORT JNI 一个关键字，不能少（编译能通过），标记为该方法可以被外部调用
// jstring : 代表 java 中的 String
// JNICALL: 也是一个关键字，可以少的 jni call
// JNIEnv: 这个是 c 和 java 相互调用的桥梁，所有 function 搞清
// jobject: java传递下来的对象，就是本项目中 JniSimple java 对象
// jclass: java传递下来的 class 对象，就是本项目中的 JniSimple.class
jobject globalStr;

JNIEXPORT jstring JNICALL Java_com_fz_jni_JniSimple_getSingnaturePassword
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env, "123");
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_changeName
(JNIEnv *env, jobject obj) {
    // 获取jclass
    jclass j_cls = (*env)->GetObjectClass(env, obj);
    // sig 属性的签名， javap -p -s 具体class
    jfieldID j_fieldID = (*env)->GetFieldID(env, j_cls, "name", "Ljava/lang/String;");
    jstring jstr = (*env)->GetObjectField(env, obj, j_fieldID);

    char* c_str = (*env)->GetStringUTFChars(env, jstr, NULL);
    printf("name is %s.", c_str);
    jstring name = (*env)->NewStringUTF(env, "Jack");
    (*env)->SetObjectField(env, obj, j_fieldID, name);
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_callAddMathod
        (JNIEnv *env, jobject jobj)
{
    jclass jcls = (*env)->GetObjectClass(env, jobj);
    jmethodID mid = (*env)->GetMethodID(env, jcls, "add", "(II)I");
    jint sum = (*env)->CallIntMethod(env, jobj, mid, 2, 3);
    printf("sum = %d", sum);
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_changeAge
        (JNIEnv *env, jclass jcls)
{
    jfieldID fieldid_age = (*env)->GetStaticFieldID(env, jcls, "age", "I");
    jint old_age = (*env)->GetStaticIntField(env, jcls, fieldid_age);
    old_age += 12; // jint 无需转 int
    (*env)->SetStaticIntField(env, jcls, fieldid_age, old_age);

}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_callStaticMethod
        (JNIEnv *env, jclass jcls)
{
    jmethodID mid = (*env)->GetStaticMethodID(env, jcls, "getUUID", "()Ljava/lang/String;");
    jstring uuid = (*env)->CallStaticObjectMethod(env, jcls, mid);
    char* c_uuid = (*env)->GetStringUTFChars(env, uuid, NULL); //jstring -> c str
    printf("\nuuid = %s\n", c_uuid);
    (*env)->ReleaseStringChars(env, uuid, c_uuid);
    printf("\nuuid = %s\n", c_uuid);
}

JNIEXPORT jobject JNICALL Java_com_fz_jni_JniSimple_createPoint
        (JNIEnv *env, jclass jcls)
{
    // jclz -> Simple1

    // 获取 Point 的 class ,name = "全类名"
    jclass point_clz = (*env)->FindClass(env,"com/fz/jni/Point");
    // 构建 java 层的 Point 对象，构造函数的id , 构造方法（百度） <init>
    jmethodID j_mid = (*env)->GetMethodID(env,point_clz,"<init>","(II)V");

    jobject point = (*env)->NewObject(env, point_clz, j_mid,11,22);

    // 练习一下 y 重新付个值 ？调用 set 方法
    j_mid = (*env)->GetMethodID(env, point_clz,"setY","(I)V");
    /* va_list 集合
    void (JNICALL *CallVoidMethodV)
        (JNIEnv *env, jobject obj, jmethodID methodID, va_list args);
    // jvalue
    void (JNICALL *CallVoidMethodA)
        (JNIEnv *env, jobject obj, jmethodID methodID, const jvalue * args);
    */

    (*env)->CallVoidMethod(env, point,j_mid,33);
    return point;
}

int compare(const jint *number1, const jint *number2){
    return *number1 - *number2;
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_sort
        (JNIEnv *env, jclass jcls, jintArray jarray)
{
    jint *intArray = (*env)->GetIntArrayElements(env, jarray, NULL);
    int length = (*env)->GetArrayLength(env, jarray);
    // 第一个参数：void* 数组的首地址
    // 第二个参数：数组的大小长度
    // 第三个参数：数组元素数据类型的大小
    // 第四个参数：数组的一个比较方法指针（Comparable）
    qsort(intArray, length, sizeof(int), compare);

    // 同步数组的数据给 java 数组 intArray 并不是 jarray ，可以简单的理解为 copy
    // 0 : 既要同步数据给 jarray ,又要释放 intArray
    // JNI_COMMIT:  会同步数据给 jarray ，但是不会释放 intArray
    // JNI_ABORT: 不同步数据给 jarray ，但是会释放 intArray
    (*env)->ReleaseIntArrayElements(env,jarray,intArray,0);
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_localRef
        (JNIEnv *env, jclass jcls)
{
    // 在 native 层构建的 Java 对象，你不用了该怎么管理？
    // native 层开辟的内存由谁管理，你能开辟多大

    // 字符串截取，String 对象
    jclass str_clz = (*env)->FindClass(env,"java/lang/String");
    jmethodID init_mid = (*env)->GetMethodID(env,str_clz,"<init>","()V");
    jobject j_str = (*env)->NewObject(env, str_clz, init_mid);

    // 还有 100 行代码

    // jobject 不要再使用了，要回收 javaGC 的源码
    (*env)->DeleteLocalRef(env,j_str);

    // 删除了就不能再使用了，C 和 C++ 都需要自己释放内存（静态开辟的不需要，动态开辟的需要）
}

JNIEXPORT void JNICALL Java_com_fz_jni_JniSimple_saveGlobalRef
        (JNIEnv *env, jclass jcls, jstring str)
{
    // 保存全局变量，其他方法需要用到
    globalStr = (*env)->NewGlobalRef(env, str);

    // NewWeakGlobalRef (java 中的软引用很像) 无法保证对象不为空
}

JNIEXPORT jstring JNICALL Java_com_darren_day15_Simple1_getGlobalRef
        (JNIEnv *env, jclass jclz){
    return globalStr;
}

JNIEXPORT void JNICALL Java_com_darren_day15_Simple1_delteGlobalRef
        (JNIEnv *env, jclass jclz){
    (*env)->DeleteGlobalRef(env,globalStr);
}

/*JNIEXPORT void JNICALL Java_com_darren_day15_Simple1_staticLocalCache
(JNIEnv *env, jclass jclz, jstring name){
	// name属性 赋值操作
	// static jfieldID f_id = NULL;// 局部缓存，这个方法会被多次调用，不需要反复的去获取 jfieldID  OpenCV WebRtc
	if (f_id == NULL){
		f_id = (*env)->GetStaticFieldID(env, jclz, "name", "Ljava/lang/String;");
	}else{
		printf("fieldID is not null\n");
	}
	(*env)->SetStaticObjectField(env, jclz, f_id, name);
}*/

// 全局静态缓存，在构造函数中初始化的时候会去缓存
static jfieldID f_name_id = NULL;
static jfieldID f_name1_id = NULL;
//static jfieldID f_name2_id = NULL;



JNIEXPORT void JNICALL Java_com_darren_day15_Simple1_staticLocalCache
        (JNIEnv *env, jclass jclz, jstring name){
    // 如果这个方法会反复的被调用，那么不会反复的去获取 jfieldID
    (*env)->SetStaticObjectField(env, jclz, f_name1_id, name);
}

JNIEXPORT void JNICALL Java_com_darren_day15_Simple1_initStaticCache
        (JNIEnv *env, jclass jclz){
    // 初始化全局静态缓存
//    f_name_id = (*env)->GetStaticFieldID(env, jclz, "name", "Ljava/lang/String;");
    f_name1_id = (*env)->GetStaticFieldID(env, jclz, "name1", "Ljava/lang/String;");
//    f_name2_id = (*env)->GetStaticFieldID(env, jclz, "name2", "Ljava/lang/String;");
}

JNIEXPORT void JNICALL Java_com_darren_day15_Simple1_exception
        (JNIEnv *env, jclass jclz){

    // 假设现在想给 ，name 赋值 name3
    jfieldID f_id = (*env)->GetStaticFieldID(env, jclz, "name3", "Ljava/lang/String;");

    // 好几种方式
    // 1. 补救措施 ，name3 我拿 name
    // 1.1 有没有异常
    jthrowable throwable = (*env)->ExceptionOccurred(env);
    /*if (throwable){
        // 补救措施，先把异常清除
        printf("有异常");
        // 清除异常
        (*env)->ExceptionClear(env);
        // 重新获取 name 属性
        f_id = (*env)->GetStaticFieldID(env, jclz, "name", "Ljava/lang/String;");
    }*/

    // 2. 想给 java 层抛一个异常
    if (throwable){
        // 清除异常
        (*env)->ExceptionClear(env);
        // Throw 抛一个 java 的 Throwable 对象
        jclass no_such_clz = (*env)->FindClass(env,"java/lang/NoSuchFieldException");
        (*env)->ThrowNew(env, no_such_clz,"NoSuchFieldException name3");

        return;// 记得 return
    }

    jstring name = (*env)->NewStringUTF(env, "Darren");
    (*env)->SetStaticObjectField(env, jclz, f_id, name);
}


