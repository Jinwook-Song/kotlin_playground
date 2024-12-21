package com.example.basic

// compile-time 상수 (top level)
const val key = 20

fun main() {
    println("hello kotlin")

    val john = Person("John", 13)
    val john2 = Person("John", 13)

    println(john)
    println(john2)
    println(john == john2)


//    nullSafety()
//    exception()
//    array()
//    list()
//    variables()
//    loop()
//    condition()
}

class Person(
    private val name: String,
    var age: Int,
) {
    private var hobby = "축구"

    init {
        println("init")
    }

    fun some() {
        hobby = "농구"

    }
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun nullSafety() {
    var name: String?
    name = "jw"

//    name = null

    var name2: String = "name2"
    if (name != null) {
        name2 = name
    }

    name?.let {
        name2 = name
    }

    println(name2)

}

fun exception() {
    val items = listOf(1, 2, 3)
    try {
        val item = items[4]
    } catch (e: Exception) {
        println(e.message)
    }

}

fun array() {
    val items = arrayOf(1, 2, 3)
    items[0] = 10
    for (i in items) {
        println(i)
    }
}

fun list() {
    val items = mutableListOf<Int>(1, 2, 3, 4)
    items.add(5)
    items.remove(3)
}

fun loop() {
    val items = listOf(1, 2, 3, 4, 5)

    for (item in items) {
        println(item)
    }

    items.forEach { item ->
        println(item)
    }

    for (i in items.indices) {
        println(items[i])
    }

}

fun condition() {
    var i = 5
    var result = if (i > 10) {
        "10보다 크다"
    } else if (i > 5) {
        "5보다 크고, 10보다 작거나 같다"
    } else {
        "나머지"
    }
    var result2 = when {
        i > 10 -> {
            "10보다 크다"
        }

        i > 5 -> {
            "5보다 크고, 10보다 작거나 같다"
        }

        else -> {
            "나머지"
        }
    }

    println(result)
    println(result2)
}

fun variables() {
    //    변수
    var i: Int = 10
    var name: String = "jw"

//    상수
    val num = 20

//    형변환
    var x = 10
    var y = 20L

    y = x.toLong()
    x = y.toInt()


}
