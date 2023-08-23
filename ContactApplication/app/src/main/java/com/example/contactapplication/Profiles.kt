package com.example.contactapplication

data class Profiles(val image: Int, val name: String, val phone: String, val viewType : Int){
    companion object {
        const val VIEW_TYPE_LEFT = 0
        const val VIEW_TYPE_RIGHT = 1
    }
}