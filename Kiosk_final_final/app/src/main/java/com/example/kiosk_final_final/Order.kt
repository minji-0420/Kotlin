package com.example.kiosk_final_final

import kotlin.system.exitProcess

class OrderManager {
    private val previousOrderList: MutableList<Menu> = mutableListOf()
    private val currentOrderList: MutableList<Menu> = mutableListOf()

    fun addToOrder(menuItem: Menu) {
        currentOrderList.add(menuItem)
        println("주문 내역에 ${menuItem.name}가 추가 되었습니다.")
        order()
    }

    private fun order() {
        if (currentOrderList.isEmpty()) {
            println("주문 내역이 비어있습니다.")
        } else {
            println("[ 주문 내역 ]")
            for ((index, menuItem) in currentOrderList.withIndex()) {
                println("[${index + 1}] ${menuItem.name}  |  W ${menuItem.price}  |  ${menuItem.description}")
            }
        }
        println("[ ORDER MENU ]")
        println("[5] 결제")
        println("[6] 주문 취소")
        println("[7] 메뉴 더 담기")

        when (readLine()?.toIntOrNull()) {
            5 -> payment()
            6 -> clearOrder()
            7 -> inputMyInfo(this)
            else -> {
                println("올바른 메뉴 번호를 선택하세요.")
                order()
            }
        }
    }

    private fun sum(orderList: List<Menu>): Int {
        var sumPrice = 0
        for (menuItem in orderList) {
            sumPrice += menuItem.price
        }
        return sumPrice
    }

    private fun payment() {
        val sumPrice = sum(currentOrderList)
        println("[ 주문 내역 ]")
        for ((index, menuItem) in currentOrderList.withIndex()) {
            println("[${index + 1}] ${menuItem.name}  |  W ${menuItem.price}  |  ${menuItem.description}")
        }
        println("[ 합계 금액 ]")
        println("W $sumPrice")

        previousOrderList.addAll(currentOrderList)
        currentOrderList.clear()

        println("결제하시겠습니까?")
        println("[1] 결제 [2] 취소")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                val range = (0..100000)
                val money = range.random()
                val plus = money - sumPrice
                val minus = sumPrice - money
                if (money > sumPrice) {
                    println("현재 잔액 W ${money}, 결제 금액 W ${sumPrice}로 결제가 완료되었습니다. 남은 잔액 : W ${plus}")
                    exitProcess(0)
                } else {
                    println("현재 잔액은 W ${money}, 결제 금액 W ${sumPrice}으로 W ${minus}이 부족해서 주문할 수 없습니다.")
                    exitProcess(0)
                }
            }
            2 -> {
                println("프로그램을 종료합니다.")
                exitProcess(0)
            }
            else -> {
                println("올바른 메뉴 번호를 선택하세요.")
            }
        }
    }


    private fun clearOrder() {
        currentOrderList.clear()
        println("주문 내역이 초기화 되었습니다.")
        return inputMyInfo(this)
    }
}
