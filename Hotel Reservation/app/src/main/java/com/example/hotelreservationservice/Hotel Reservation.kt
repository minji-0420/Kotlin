package com.example.hotelreservationservice

import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Reservation {
    var name:String = ""
    var roomNumber:Int = 0
    var checkIn:LocalDate = LocalDate.now()
    var checkOut:LocalDate = LocalDate.now()
    var money = 0
    var realMoney = 0

    constructor(name: String, roomNumber: Int, checkIn: LocalDate, checkOut : LocalDate, money: Int, realMoney: Int) {
        this.name = name
        this.roomNumber = roomNumber
        this.checkIn = checkIn
        this.checkOut = checkOut
        this.money = money
        this.realMoney = money - 100000
    }

    override fun toString(): String {
        return "Reservation(name=$name, roomNumber=$roomNumber, checkIn=$checkIn, checkOut=$checkOut, money=$money, realmoney=$realMoney)"
    }
}

fun main() {

    val reservationList = mutableListOf<Reservation>()

    println("호텔 예약 서비스입니다.")

    while (true) {
        println("메뉴를 선택 하세요.")
        when (inputInfo("selectNumber")) {
            1 -> {
                val myName = inputMyInfo("name", null).toString()
                val myRoomNumber = inputMyInfo("roomNumber", null).toString().toInt()
                val myCheckIn = inputMyInfo("checkIn", null) as LocalDate
                val myCheckOut = inputMyInfo("checkOut", myCheckIn) as LocalDate
                val myMoney = inputMyInfo("money", null).toString().toInt()
                val myRealMoney = myMoney - 100000
                // 예약금 10만원으로 고정

                val existingReservation = reservationList.find {
                    it.roomNumber == myRoomNumber &&
                            (it.checkIn.isBefore(myCheckOut) || it.checkIn == myCheckOut) &&
                            (it.checkOut.isAfter(myCheckIn) || it.checkOut == myCheckIn)
                }

                if (existingReservation != null) {
                    println("해당 객실은 ${existingReservation.checkIn}부터 ${existingReservation.checkOut}까지 예약이 불가능합니다.")
                    println("체크인과 체크아웃 날짜를 변경하여 다시 예약해 주세요.")
                } else {
                    println("예약이 완료되었습니다.")
                    println("이름 : $myName")
                    println("객실 번호 : $myRoomNumber")
                    println("체크인 날짜 : $myCheckIn")
                    println("체크아웃 날짜 : $myCheckOut")
                    println("잔액 : $myRealMoney")

                    val reservation = Reservation(myName, myRoomNumber, myCheckIn, myCheckOut, myMoney, myRealMoney)
                    reservationList.add(reservation)

                }
            }

            2 -> println(reservationList)

            3 -> {
                val sortedList = reservationList.sortedBy { it.checkIn }
                printReservationList (sortedList)}

            4 -> {
                println("종료합니다.")
                break
            }

            5 -> {
                println("예약자 성함을 입력 하세요.")
                while(true) {
                    try {
                        val nameToSearch = readLine()?.trim()
                        val foundReservation = reservationList.filter {it.name == nameToSearch}
                        if(nameToSearch?.first() != '_' && nameToSearch?.first() != '!') {
                            if (foundReservation.isEmpty()) {
                                println("예약 내역이 없습니다. 예약자 성함을 다시 입력 하세요.")
                            } else {
                                println("입금 내역 : ${foundReservation[0].money}")
                                println("출금 내역 : 100000원")
                                println("잔액 : ${foundReservation[0].realMoney}")
                                break
                            }
                        } else {
                            println("성함을 다시 입력 하세요.")
                        }
                    } catch(e:Exception) {
                        println("성함을 다시 입력 하세요.")

                    }
                }
            }
            else -> println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}

fun inputInfo(type:String): Int {
    while (true) {
        try {
            println("[1] 객실 예약, [2] 예약 목록 출력, [3] 예약 목록 정렬 출력, [4] 시스템 종료, [5] 입금 - 출금 내역 목록 출력, [6] 예약 변경 / 취소")
            val selectNumber = readLine()!!.toInt()
            if (selectNumber in 1..6) {
                return selectNumber
            } else  {
                println("올바른 메뉴 번호를 선택 하세요.")
            }
        } catch (e:Exception) {
            println("올바른 메뉴 번호를 선택 하세요.")
        }
    }
}

fun inputMyInfo(type: String, checkInDate : LocalDate?): Any? {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return when(type) {
        "name" -> {
            println("이름을 입력 하세요.")
            while(true) {
                try {
                    val originName = readLine()
                    if(originName?.first() != '_' && originName?.first() != '!') {
                        return originName

                    } else {
                        println("이름을 다시 입력 하세요.")
                    }
                } catch(e:Exception) {
                    println("이름을 다시 입력 하세요.")
                }
            }
        }
        "roomNumber" -> {
            println("예약 할 객실 번호를 입력 하세요. 객실 번호는 100 ~ 999 영역 이내 입니다.")
            while(true) {
                try {
                    val originRoomNumber:Int? = readLine()!!.toInt()
                    if(originRoomNumber in 100..999) {
                        return originRoomNumber ?: -1
                    } else {
                        println("객실 번호를 다시 입력 하세요. 객실 번호는 100 ~ 999 영역 이내 입니다.")
                    }

                } catch(e:Exception) {
                    println("객실 번호를 다시 입력 하세요. 객실 번호는 100 ~ 999 영역 이내 입니다.")
                }
            }
        }
        "checkIn" -> {
            println("체크인 날짜를 입력 하세요. 표기 형식 : 2023-07-20")
            while(true) {
                try {
                    val originCheckIn = LocalDate.parse(readLine(), formatter)
                    val localDate:LocalDate = LocalDate.now()
                    if (originCheckIn != null) {
                        if(originCheckIn > localDate) {
                            return originCheckIn
                        } else {
                            println("체크인은 지난 날은 선택할 수 없습니다. 다시 입력 하세요.")
                        }
                    }
                } catch(e:Exception) {
                    println("체크인 날짜를 다시 입력 하세요. 표기 형식 : 2023-07-20")
                }
            }
        }
        "checkOut" -> {
            println("체크아웃 날짜를 입력 하세요. 표기 형식 : 2023-07-20")
            while (true) {
                try {
                    val originCheckOut = LocalDate.parse(readLine(), formatter)
                    if (originCheckOut > checkInDate) {
                        return originCheckOut
                    } else {
                        println("체크인 날짜보다 이전이거나 같을 수 없습니다. 다시 입력 하세요.")
                    }
                }
                catch(e:Exception) {
                    println("체크 아웃 날짜를 다시 입력 하세요. 표기 형식 : 2023-07-20")
                }
            }
        }
        "money" -> {
            val range = (500000..10000000)
            return range.random()
        }

        else -> {
            return "no"
        }
    }
}

fun printReservationList(reservations: List<Reservation>) {
    for ((index, reservation) in reservations.withIndex()) {
        println("Reservation ${index +1}")
        println("이름 : ${reservation.name}")
        println("객실 번호 : ${reservation.roomNumber}")
        println("체크인 날짜 : ${reservation.checkIn}")
        println("체크아웃 날짜 : ${reservation.checkOut}")
        println()
    }
}
