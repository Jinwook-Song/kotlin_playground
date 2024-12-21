package com.example.basic

fun main() {
    val dog = Dog()
    val cat = Cat()

    // type check
    if (cat is Animal) {
        cat.draw()
        println("고양이")
    }

    val box = Box(10)
    val box2 = Box("test")
    println(box.value)


}

interface Drawable {
    fun draw()
}

abstract class Animal {
    abstract fun move()

    open fun eat() {
        println("eat")
    }
}

class Dog : Animal(), Drawable {
    override fun move() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        super.eat()
        println("dog eat")
    }

    override fun draw() {
        TODO("Not yet implemented")
    }

}

class Cat : Animal(), Drawable {
    override fun move() {

    }

    override fun eat() {
        println("cat eat")
    }

    override fun draw() {
        println("Draw")
    }
}

class Box<T>(var value: T)
