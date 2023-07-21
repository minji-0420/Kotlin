package com.example.calculator_lv04

fun main() {
    println("연산 할 숫자를 입력하세요.")
    val number3 = readLine()!!.toInt()

    println("연산 할 숫자를 입력하세요.")
    val number4 = readLine()!!.toInt()

    // 이게 왜 되는거지?! 클래스 안 클래스가 가능하다니!!! - 상속받았기 때문에 가능!! operate 이라는 함수 이름 통일!
    val add = Calculator2(AddOperationLv4())
    val addResult = add.operate(number3, number4)

    val sub = Calculator2(SubOperationLv4())
    val subResult = sub.operate(number3, number4)

    val mul = Calculator2(MulOperationLv4())
    val mulResult = mul.operate(number3, number4)

    val div = Calculator2(DivOperationLv4())
    val divResult = div.operate(number3, number4)

    println("연산자를 선택 하세요. " +
            "[1] 덧셈, [2] 뺄셈, [3] 곱셈, [4] 나눗셈, [5] 종료")

    when(readLine()!!.toInt()) {
        1 -> print(addResult)
        2 -> print(subResult)
        3 -> print(mulResult)
        4 -> print(divResult)
        else -> print("종료하겠습니다.")

    }
}

class Calculator2(private val abstractOperation : AbstractOperation) {
    fun operate(number3: Int, number4: Int): Double {
        return abstractOperation.operate(number3, number4)
    }
}

abstract class AbstractOperation {
    abstract fun operate(number3: Int, number4: Int): Double }

class AddOperationLv4 : AbstractOperation() {
    override fun operate(number3: Int, number4: Int): Double = (number3 + number4).toDouble()}

class SubOperationLv4 : AbstractOperation() {
    override fun operate(number3: Int, number4: Int): Double = (number3 - number4).toDouble()}

class MulOperationLv4 : AbstractOperation() {
    override fun operate(number3: Int, number4: Int): Double = (number3 * number4).toDouble()}

class DivOperationLv4 : AbstractOperation() {
    override fun operate(number3: Int, number4: Int): Double = (number3 / number4).toDouble()}



