package com.example.kiosk_final

import kotlin.system.exitProcess

fun main() {
    println("어서오세요, SHAKE SHACK BURGER 입니다.")

    val orderManager = OrderManager()

    inputMyInfo(orderManager)
}

fun inputInfo(): Int {
    println("메뉴를 선택 하세요.")
    println("[ SHAKE SHACK MENU ]")
    println("[1] Burgers         | 앵거스 비프 통살을 다져 만든 버거")
    println("[2] Frozen Custard  | 매장에서 신선하게 만드는 아이스크림")
    println("[3] Drinks          | 매장에서 직접 만드는 음료")
    println("[4] Beers           | 뉴욕 브루클린 브루어리에서 양조한 맥주")
    println("[0] 종료             | 프로그램 종료")

    return readLine()?.toIntOrNull() ?: 0
}

fun inputMyInfo(orderManager: OrderManager) {

    when (inputInfo()) {

        1 -> burgerList(orderManager)
        2 -> frozenCustardList(orderManager)
        3 -> drinksList(orderManager)
        4 -> beersList(orderManager)
        0 -> {
            println("종료합니다.")
            exitProcess(0)
        }

        else -> println("올바른 메뉴 번호를 선택 하세요.")
    }
}