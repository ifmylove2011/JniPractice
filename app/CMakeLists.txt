#设置最小 cmake 版本
cmake_minimum_required(VERSION 3.4.1)

project (start)

#命名start动态库，最终生成libstart.so
#add_library(start SHARED src/main/cpp/start.cpp)
file(GLOB_RECURSE SRC_C "*.cpp")
add_library(start SHARED ${SRC_C})
#查找本地 库
#find_library(xter start)
#find_library(log-lib log)

#原生库及log日志相关库
target_link_libraries(start ${log-lib})