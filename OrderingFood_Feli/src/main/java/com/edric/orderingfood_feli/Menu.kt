package com.edric.orderingfood_feli

class Menu(){
    val itemList = ArrayList<Item>()

    fun show(){
        println("<-MENU->")
        var i=0
        for(item in itemList) {
            println("$i. ${item.name} | ${item.price}")
            i++
        }
    }
}