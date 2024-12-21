package com.example.basic

fun main() {
    myFunc({
        println("함수 호출")
    })

    myFunc {
        println("함수 호출2")
    }


}

fun myFunc(cb: () -> Unit) {
    println("시작")
    cb()
    println("끝")
}

// 코루틴
suspend fun myFunc2(a: Int, cb: () -> Unit) {
    println("시작 $a")
    cb()
    println("끝")
}

