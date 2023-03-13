cmake_minimum_required(VERSION 3.21)
project(DSA_PW3_map_slla)

set(CMAKE_CXX_STANDARD 17)

add_executable(DSA_PW3_map_slla MapIterator.cpp MapIterator.h ShortTest.cpp ShortTest.h ExtendedTest.cpp ExtendedTest.h App.cpp Map.h Map.cpp)
