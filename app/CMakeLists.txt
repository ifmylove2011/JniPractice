#设置编译 native library 需要最小的 cmake 版本
cmake_minimum_required(VERSION 3.4.1)
project (start)
#将指定的源文件编译为名为 libnative-lib.so 的动态库
add_library(start SHARED src/main/cpp/start.cpp)
#查找本地 库
find_library(xter start)
find_library(log-lib log)

#将预构建的库添加到自己的原生库
target_link_libraries(start ${log-lib})