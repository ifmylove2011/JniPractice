//
// Created by XTER on 2021/3/5_
//

#include <jni.h>
#include <android/log.h>
#include <malloc.h>
#include <string.h>

#ifndef LOG_TAG
#define LOG_TAG "JNI_PRACTICE"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG ,__VA_ARGS__)
#endif

extern "C" {
unsigned int byte2int(jbyte *src, int size) {
    unsigned int result = 0;
    for (int i = 0; i < size; i++) {
        result += (src[i] & 0xff) << ((size - 1 - i) * 8);
    }
    return result;
}

JNIEXPORT jint JNICALL
Java_com_xter_jnipractice_SomeFunc_getVersion(JNIEnv *env, jclass o) {
    return env->GetVersion();
}

JNIEXPORT jintArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getIntArray(JNIEnv *env, jclass o, jint size) {
    jintArray array;
    array = env->NewIntArray(size);
    for (int i = 0; i < size; i++) {
        env->SetIntArrayRegion(array, i, 1, &i);
    }
    return array;
}

JNIEXPORT jobjectArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getStringArray(JNIEnv *env, jclass o, jint size) {
    jclass jc = env->FindClass("java/lang/String");
    jobjectArray array;
    array = env->NewObjectArray(size, jc, NULL);
    const char *s[] = {"我", "你", "他", "它", "她"};
    for (int i = 0; i < size; i++) {
        jstring ss = env->NewStringUTF(s[i % 5]);
        env->SetObjectArrayElement(array, i, ss);
    }
    return array;
}

JNIEXPORT jintArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getRectValue(JNIEnv *env, jclass o, jobject rect) {
    jclass jc = env->FindClass("android/graphics/Rect");
    jfieldID field_left = env->GetFieldID(jc, "left", "I");
    jfieldID field_right = env->GetFieldID(jc, "right", "I");
    jfieldID field_top = env->GetFieldID(jc, "top", "I");
    jfieldID field_bottom = env->GetFieldID(jc, "bottom", "I");
    jint left = env->GetIntField(rect, field_left);
    jint right = env->GetIntField(rect, field_right);
    jint top = env->GetIntField(rect, field_top);
    jint bottom = env->GetIntField(rect, field_bottom);
    jintArray array = env->NewIntArray(4);
    env->SetIntArrayRegion(array, 0, 1, &left);
    env->SetIntArrayRegion(array, 1, 1, &right);
    env->SetIntArrayRegion(array, 2, 1, &top);
    env->SetIntArrayRegion(array, 3, 1, &bottom);
    return array;
}

JNIEXPORT void JNICALL
Java_com_xter_jnipractice_SomeFunc_callMethodFunc(JNIEnv *env, jclass o, jobject activity) {
    jclass jc = env->FindClass("com/xter/jnipractice/MainActivity");
    jmethodID m1 = env->GetMethodID(jc, "testFunc", "()V");
    env->CallVoidMethod(activity, m1);
}

JNIEXPORT jobject JNICALL
Java_com_xter_jnipractice_SomeFunc_genUser(JNIEnv *env, jclass o) {
    jclass jc = env->FindClass("com/xter/jnipractice/entity/User");
    jfieldID field_name = env->GetFieldID(jc, "name", "java/lang/String");
    jfieldID field_age = env->GetFieldID(jc, "age", "I");
    jfieldID field_female = env->GetFieldID(jc, "female", "Z");
    jobject user = env->AllocObject(jc);
    jstring name = env->NewStringUTF("Mike");
    env->SetObjectField(user, field_name, name);
    env->SetIntField(user, field_age, 22);
    env->SetBooleanField(user, field_female, static_cast<jboolean>(true));
    return user;
}

JNIEXPORT jobject JNICALL
Java_com_xter_jnipractice_SomeFunc_genProfile(JNIEnv *env, jclass o) {
    jclass jc = env->FindClass("com/xter/jnipractice/entity/Profile");
    jfieldID field_address = env->GetFieldID(jc, "address", "java/lang/String");
    jfieldID field_school = env->GetFieldID(jc, "school", "java/lang/String");
    jfieldID field_degree = env->GetFieldID(jc, "degree", "java/lang/String");
    jfieldID field_phoneNumber = env->GetFieldID(jc, "phoneNumber", "java/lang/String");
    jfieldID field_salary = env->GetFieldID(jc, "salary", "I");
    jfieldID field_experience = env->GetFieldID(jc, "experience", "java/util/ArrayList");

    //创建List对象集合
    jclass cls_ArrayList = env->FindClass("java/util/ArrayList");
    jmethodID construct = env->GetMethodID(cls_ArrayList, "<init>", "()V");
    jobject obj_ArrayList = env->NewObject(cls_ArrayList, construct, "");
    jmethodID arrayList_add = env->GetMethodID(cls_ArrayList, "add", "(Ljava/lang/Object;)Z");
    env->CallBooleanMethod(obj_ArrayList, arrayList_add, env->NewStringUTF("长城贴砖"));
    env->CallBooleanMethod(obj_ArrayList, arrayList_add, env->NewStringUTF("氢弹抛光"));
    env->CallBooleanMethod(obj_ArrayList, arrayList_add, env->NewStringUTF("太空电梯"));

    //构建最终实体
    jobject profile = env->AllocObject(jc);
    jstring address = env->NewStringUTF("问就是在故宫");
    env->SetObjectField(profile, field_address, address);
    env->SetObjectField(profile, field_experience, obj_ArrayList);
    return profile;
}

JNIEXPORT jobject JNICALL
Java_com_xter_jnipractice_SomeFunc_genSecret(JNIEnv *env, jclass o, jbyteArray buffer) {
    jclass jc = env->FindClass("com/xter/jnipractice/entity/Secret");
    jfieldID field_order = env->GetFieldID(jc, "order", "[B");
    jfieldID field_len = env->GetFieldID(jc, "len", "[B");
    jfieldID field_plan = env->GetFieldID(jc, "plan", "[B");

    jbyte *bufOrder = (jbyte *) malloc(sizeof(jbyte) * 1);
    jbyte *bufLen = (jbyte *) malloc(sizeof(jbyte) * 4);

    memset(bufOrder, 0, sizeof(jbyte) * 1);
    memset(bufLen, 0, sizeof(jbyte) * 4);

    env->GetByteArrayRegion(buffer, 0, 1, bufOrder);
    env->GetByteArrayRegion(buffer, 1, 4, bufLen);

    //获得具体内容
    unsigned int len = byte2int(bufLen, 4);
    jbyte *bufPlan = (jbyte *) malloc(sizeof(jbyte) * len);
    LOGD("content len = %d",len);
    memset(bufPlan, 0, sizeof(jbyte) * len);
    env->GetByteArrayRegion(buffer, 5, len, bufPlan);

    jbyteArray arrayOrder = env->NewByteArray(1);
    jbyteArray arrayLen = env->NewByteArray(4);
    jbyteArray arrayPlan = env->NewByteArray(len);

    env->SetByteArrayRegion(arrayOrder, 0, 1, bufOrder);
    env->SetByteArrayRegion(arrayLen, 0, 4, bufLen);
    env->SetByteArrayRegion(arrayPlan, 0, len, bufPlan);

    //构建最后实体
    jobject secret = env->AllocObject(jc);
    env->SetObjectField(secret, field_order, arrayOrder);
    env->SetObjectField(secret, field_len, arrayLen);
    env->SetObjectField(secret, field_plan, arrayPlan);
    return secret;
}

}
