package com.example.kiosk_final

abstract class MenuItem {
    abstract val name: String
    abstract val price: Int
    abstract val introduce: String
}

open class Burger : MenuItem() {
    override val name: String = ""
    override val price: Int = 0
    override val introduce: String = ""
}

class ShackBurger : Burger() {
    override val name = "ShackBurger"
    override val price = 6900
    override val introduce = "토마토, 양상추, 쉑소스가 토핑된 치즈버거"
}

class SmokeShack : Burger() {
    override val name = "SmokeShack"
    override val price = 8900
    override val introduce = "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
}

class ShroomBurger : Burger() {
    override val name = "ShroomBurger"
    override val price = 9400
    override val introduce = "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"
}

class CheeseBurger : Burger() {
    override val name = "CheeseBurger"
    override val price = 6900
    override val introduce = "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"
}

class HamBurger : Burger() {
    override val name = "HamBurger"
    override val price = 5400
    override val introduce = "비프패티를 기반으로 야채가 들어간 기본버거"
}

open class FrozenCustard : MenuItem() {
    override val name: String = ""
    override val price: Int = 0
    override val introduce: String = ""
}

class Shakes() : FrozenCustard() {
    override val name = "Shakes"
    override val price = 5900
    override val introduce = "매일 직접 만드는 부드럽고 진한 맛의 쫀득한 아이스크림"
}

class Cones() : FrozenCustard() {
    override val name = "Cones"
    override val price = 4900
    override val introduce = "초콜릿 아이스크림"
}

class Concretes() : FrozenCustard() {
    override val name = "Shack Attack"
    override val price = 5900
    override val introduce = "쉐이크쉑의 쫀득한 커스터드와 다양한 믹스-인의 조합"
}

open class Drinks : MenuItem() {
    override val name: String = ""
    override val price: Int = 0
    override val introduce: String = ""
}

class ShackMadeLemonade() : Drinks() {
    override val name = "Shack-made Lemonade"
    override val price = 3900
    override val introduce = "매장에서 직접 만드는 상큼한 레몬에이드"
}

class FreshBrewedIcedTea() : Drinks() {
    override val name = "Fresh Brewed Iced Tea"
    override val price = 3400
    override val introduce = "유기농 홍차를 직접 우려낸 아이스티"
}

class Fifty() : Drinks() {
    override val name = "Fifty / Fifty"
    override val price = 3500
    override val introduce = "레모네이드와 아이스 티의 만남"
}

class FountainSoda() : Drinks() {
    override val name = "Fountain Soda"
    override val price = 2700
    override val introduce = "코카콜라"
}

class AbitaRootBeer() : Drinks() {
    override val name = "Abita Root Beer"
    override val price = 4400
    override val introduce = "청량감 있는 독특한 미국식 무알콜 탄산음료"
}

class BottledWater() : Drinks() {
    override val name = "Bottled Water"
    override val price = 1000
    override val introduce = "지리산 암반대수층으로 만든 프리미엄 생수"
}

open class Beers : MenuItem() {
    override val name: String = ""
    override val price: Int = 0
    override val introduce: String = ""
}

class ShackMeisterAle() : Beers() {
    override val name = "ShackMeister Ale"
    override val price = 9800
    override val introduce = "쉐이크쉑 버거를 위해 뉴욕 브루클린 브루어리에서 특별히 양조한 에일 맥주"
}

class MagpieBrewingCo() : Beers() {
    override val name = "Magpie Brewing Co."
    override val price = 6800
    override val introduce = "Pale Ale, Draft"
}

fun burgerList(orderManager: OrderManager) {
    val burgerList = listOf(
        ShackBurger(),
        SmokeShack(),
        ShroomBurger(),
        CheeseBurger(),
        HamBurger()
    )
    println("[ Burgers Menu ]")
    for ((index, burger) in burgerList.withIndex()) {
        println("[${index + 1}] ${burger.name}       |       W ${burger.price}       |       ${burger.introduce}")
    }
    println("[0] 뒤로 가기")

    while (true) {
        try {
            when (val selectNumber = readLine()?.toIntOrNull()) {
                in 1..burgerList.size -> {
                    val selectedBurger = burgerList[selectNumber!! - 1]
                    orderManager.addToOrder(selectedBurger)
                }

                0 -> {
                    inputMyInfo(orderManager)
                }

                else -> {
                    println("올바른 메뉴 번호를 선택하세요.")
                }
            }
        } catch (e: Exception) {
            println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}


fun frozenCustardList(orderManager: OrderManager) {
    val frozenCustardList = listOf(
        Shakes(),
        Cones(),
        Concretes()
    )
    println("[ FrozenCustard Menu ]")
    for ((index, frozenCustard) in frozenCustardList.withIndex()) {
        println("[${index + 1}] ${frozenCustard.name}       |       W ${frozenCustard.price}       |       ${frozenCustard.introduce}")
    }
    println("[0] 뒤로 가기")
    while (true) {
        try {
            when (val selectNumber = readLine()?.toIntOrNull()) {
                in 1..frozenCustardList.size -> {
                    val selectedFrozenCustard = frozenCustardList[selectNumber!! - 1]
                    orderManager.addToOrder(selectedFrozenCustard)
                }

                0 -> {
                    inputMyInfo(orderManager)
                }

                else -> {
                    println("올바른 메뉴 번호를 선택하세요.")
                }
            }
        } catch (e: Exception) {
            println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}

fun drinksList(orderManager: OrderManager) {
    val drinksList = listOf(
        ShackMadeLemonade(),
        FreshBrewedIcedTea(),
        Fifty(),
        FountainSoda(),
        AbitaRootBeer(),
        BottledWater()
    )
    println("[ Drinks Menu ]")
    for ((index, drinks) in drinksList.withIndex()) {
        println("[${index + 1}] ${drinks.name}       |       W ${drinks.price}       |       ${drinks.introduce}")
    }
    println("[0] 뒤로 가기")

    while (true) {
        try {
            when (val selectNumber = readLine()?.toIntOrNull()) {
                in 1..drinksList.size -> {
                    val selectedDrinks = drinksList[selectNumber!! - 1]
                    orderManager.addToOrder(selectedDrinks)
                }

                0 -> {
                    inputMyInfo(orderManager)
                }

                else -> {
                    println("올바른 메뉴 번호를 선택하세요.")
                }
            }
        } catch (e: Exception) {
            println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}

fun beersList(orderManager: OrderManager) {
    val beersList = listOf(
        ShackMeisterAle(),
        MagpieBrewingCo()
    )
    println("[ Beers Menu ]")
    for ((index, beers) in beersList.withIndex()) {
        println("[${index + 1}] ${beers.name}       |       W ${beers.price}       |       ${beers.introduce}")
    }
    println("[0] 뒤로 가기")

    while (true) {
        try {
            when (val selectNumber = readLine()?.toIntOrNull()) {
                in 1..beersList.size -> {
                    val selectedBeers = beersList[selectNumber!! - 1]
                    orderManager.addToOrder(selectedBeers)
                }

                0 -> {
                    inputMyInfo(orderManager)
                }

                else -> {
                    println("올바른 메뉴 번호를 선택하세요.")
                }
            }
        } catch (e: Exception) {
            println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}