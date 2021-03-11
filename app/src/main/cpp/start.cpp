//
// Created by XTER on 2021/3/5_
//

#include <jni.h>

extern "C" {

JNIEXPORT jint JNICALL
Java_com_xter_jnipractice_SomeFunc_getVersion(JNIEnv *env, jobject o) {
    return env->GetVersion();
}

JNIEXPORT jintArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getIntArray(JNIEnv *env, jobject o, jint size) {
    jintArray array;
    array = env->NewIntArray(size);
    for (int i = 0; i < size; i++) {
        env->SetIntArrayRegion(array, i, 1, &i);
    }
    return array;
}

JNIEXPORT jobjectArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getStringArray(JNIEnv *env, jobject o, jint size) {
    jclass jc = env->FindClass("java/lang/String");
    jobjectArray array;
    array = env->NewObjectArray(size, jc, NULL);
    char *s[] = {"我", "你", "他", "它", "她"};
    for (int i = 0; i < size; i++) {
        jstring ss = env->NewStringUTF(s[i % 5]);
        env->SetObjectArrayElement(array, i, ss);
    }
    return array;
}

JNIEXPORT jintArray JNICALL
Java_com_xter_jnipractice_SomeFunc_getRectValue(JNIEnv *env, jobject o, jobject rect) {
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
Java_com_xter_jnipractice_SomeFunc_callMethodFunc(JNIEnv *env, jobject o,jobject activity) {
    jclass jc = env->FindClass("com/xter/jnipractice/MainActivity");
    jmethodID m1 = env->GetMethodID(jc,"testFunc","()V");
    env->CallVoidMethod(activity,m1);
}

JNIEXPORT jobject JNICALL
Java_com_xter_jnipractice_SomeFunc_genUser(JNIEnv *env, jobject o) {
    jclass jc = env->FindClass("com/xter/jnipractice/User");
    jfieldID field_name = env->GetFieldID(jc, "name", "java/lang/String");
    jfieldID field_age = env->GetFieldID(jc, "age", "I");
    jfieldID field_female = env->GetFieldID(jc, "female", "Z");
    jobject user = env->AllocObject(jc);
    jstring name = env->NewStringUTF("Mike");
    env->SetObjectField(user,field_name,name);
    env->SetIntField(user,field_age,22);
    env->SetBooleanField(user, field_female, static_cast<jboolean>(true));
    return user;
}
}
