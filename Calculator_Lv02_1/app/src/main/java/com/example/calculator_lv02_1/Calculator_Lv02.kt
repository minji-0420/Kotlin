package com.example.calculator_lv02_1

fun main () {

    println("연산 할 숫자를 입력하세요.")
    val num5 = readLine()!!.toInt()

    println("연산 할 숫자를 입력하세요.")
    val num6 = readLine()!!.toInt()

    println("[1] 덧셈, [2] 뺄셈, [3] 곱셈, [4] 나눗셈, [5] 나머지, [6] 종료")

    val cal = Calc()

    when(readLine()!!.toInt()) {
        1 -> cal.add(num5, num6)
        2 -> cal.minus(num5, num6)
        3 -> cal.multiply(num5, num6)
        4 -> cal.division(num5, num6)
        5 -> cal.modulo(num5, num6)
        6 -> println("종료하겠습니다.")
    }
}

class Calc() {
    fun add(num5: Int, num6: Int) {
        val resultAdd = num5 + num6
        println("덧셈 결과 $resultAdd 입니다.")
    }

    fun minus(num5: Int, num6: Int) {
        val resultMinus = num5 - num6
        println("뺄셈 결과 $resultMinus 입니다.")
    }

    fun multiply(num5: Int, num6: Int) {
        val resultMultiply = num5 * num6
        println("곱셈 결과 $resultMultiply 입니다.")
    }

    fun division(num5: Int, num6: Int) {
        val resultDivision = num5 / num6
        if (num5 == 0) {
            println("0으로 나눌 수 없습니다.")
        }
        else println("나눗셈 결과 $resultDivision 입니다.")
    }

    fun modulo(num5: Int, num6: Int) {
        val resultModulo = num5 % num6
        println("나머지 결과 $resultModulo 입니다.")
    }
}