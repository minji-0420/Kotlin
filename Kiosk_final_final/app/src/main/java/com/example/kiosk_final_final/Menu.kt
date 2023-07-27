package com.example.kiosk_final_final

open class Menu(val name: String, val price: Int, val description: String)

class Burger(name: String, price: Int, description: String) : Menu(name, price, description)
class FrozenCustard(name: String, price: Int, description: String) : Menu(name, price, description)
class Drinks(name: String, price: Int, description: String) : Menu(name, price, description)
class Beers(name: String, price: Int, description: String) : Menu(name, price, description)

fun burgerList(orderManager: OrderManager) {

    val burgerList = ArrayList<Burger>()

    val shakeShackBurger = Burger("Shake Shack Burger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
    val smokeShack = Burger("Smoke Shack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거")
    val shroomBurger = Burger("Shroom Burger", 9400, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거")
    val cheeseBurger = Burger("Cheese Burger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거")
    val hamBurger = Burger("HamBurger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거")
    burgerList.add(shakeShackBurger)
    burgerList.add(smokeShack)
    burgerList.add(shroomBurger)
    burgerList.add(cheeseBurger)
    burgerList.add(hamBurger)

    println("[ Burgers Menu ]")
    for ((index, burger) in burgerList.withIndex()) {
        println("[${index + 1}] ${burger.name}|W ${burger.price}|${burger.description}")
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

    val frozenCustardList = ArrayList<FrozenCustard>()

    val shakes = FrozenCustard("Shakes", 5900, "바닐라 쉐이크")
    val shakeOfTheWeek = FrozenCustard("Shake Of The Week", 6500, "특별한 커스터드 플레이버")
    val redBeanShake = FrozenCustard("Red Bean Shake", 6500, "신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크")
    val floats = FrozenCustard("Floats", 5900, "루트 비어")
    val cupsAndCones = FrozenCustard("Cups and Cones", 4900, "바닐라 아이스크림")
    val concretes = FrozenCustard("Concretes", 5900, "쉐이크쉑의 쫀득한 커스터드와 다양한 믹스-인의 조합")
    frozenCustardList.add(shakes)
    frozenCustardList.add(shakeOfTheWeek)
    frozenCustardList.add(redBeanShake)
    frozenCustardList.add(floats)
    frozenCustardList.add(cupsAndCones)
    frozenCustardList.add(concretes)

    println("[ FrozenCustard Menu ]")
    for ((index, frozenCustard) in frozenCustardList.withIndex()) {
        println("[${index + 1}] ${frozenCustard.name}|W ${frozenCustard.price}|${frozenCustard.description}")
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

    val drinksList = ArrayList<Drinks>()

    val shackMadeLemonade = Drinks("Shack Made Lemonade", 3900, "상큼한 레모네이드")
    val icedTea = Drinks("Iced Tea", 3400, "직접 유기농 홍차를 우려낸 아이스티")
    val fifty = Drinks("Fifty Fifty", 3500, "레모네이드와 아이스티의 만남")
    val fountainSoda = Drinks("Fountain Soda", 2700, "코카콜라")
    val abitaRootBeer = Drinks("Abita Root Beer", 4400, "청량감 있는 독특한 미국식 무알콜 탄산음료")
    val bottledWater = Drinks("Water", 1000, "지리산 암반대수층으로 만든 생수")
    drinksList.add(shackMadeLemonade)
    drinksList.add(icedTea)
    drinksList.add(fifty)
    drinksList.add(fountainSoda)
    drinksList.add(abitaRootBeer)
    drinksList.add(bottledWater)

    println("[ Drinks Menu ]")
    for ((index, drinks) in drinksList.withIndex()) {
        println("[${index + 1}] ${drinks.name}|W ${drinks.price}|${drinks.description}")
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

    val beersList = ArrayList<Beers>()

    val shackMeisterAle = Beers("Shack Meister Ale", 9800, "뉴욕 브루클린 브루어리에서 양조한 에일 맥주")
    val magpieBrewingCo = Beers("Magpie Brewing Co.", 6800, "Pale Ale, Draft")

    beersList.add(shackMeisterAle)
    beersList.add(magpieBrewingCo)

    println("[ Beers Menu ]")
    for ((index, beers) in beersList.withIndex()) {
        println("[${index + 1}] ${beers.name}|W ${beers.price}|${beers.description}")
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