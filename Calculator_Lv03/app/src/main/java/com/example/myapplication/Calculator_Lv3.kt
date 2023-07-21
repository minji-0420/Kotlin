package com.example.myapplication

// 같은 단어 선택 : shift + F6
// 왜 interface가 아닌 abstract class를 사용 하라고 했을까?
// 아직 자료형 변환 익숙치 않음 / double 변환 생각해 봐야 함

fun main() {
    println("연산 할 숫자를 입력하세요.")
    val number1 = readLine()!!.toInt()

    println("연산 할 숫자를 입력하세요.")
    val number2 = readLine()!!.toInt()

    val addOperation = AddOperationLv3(number1, number2)
    val subtractOperation = SubtractOperationLv3(number1, number2)
    val multiplyOperation = MultiplyOperationLv3(number1, number2)
    val divideOperation = DivideOperationLv3(number1, number2)


    println("연산자를 선택 하세요. " +
            "[1] 덧셈, [2] 뺄셈, [3] 곱셈, [4] 나눗셈, [5] 종료")

    // Calculator().result()
    when(readLine()!!.toInt()) {
        1 -> addOperation.result()
        2 -> subtractOperation.result()
        3 -> multiplyOperation.result()
        4 -> divideOperation.result()
        else -> println("종료하겠습니다.")
    }

}

open class Calculator() {

    open fun result() {
        println("연산 결과 입니다.")
    }

}

class AddOperationLv3(val number1: Int, val number2: Int) : Calculator() {
    override fun result() {
        val addResult = number1 + number2
        println("덧셈 결과 : $addResult")
    }
}

class SubtractOperationLv3(val number1: Int, val number2: Int) : Calculator() {
    override fun result() {
        val subtractResult = number1 - number2
        println("뺄셈 결과 : $subtractResult")
    }
}

class MultiplyOperationLv3(val number1: Int, val number2: Int) : Calculator() {
    override fun result() {
        val multiplyResult = number1 * number2
        println("곱셈 결과 : $multiplyResult")
    }
}

class DivideOperationLv3(val number1: Int, val number2: Int) : Calculator() {
    override fun result() {
        val divideResult = number1 / number2
        println("나눗셈 결과 : $divideResult")
    }
}

