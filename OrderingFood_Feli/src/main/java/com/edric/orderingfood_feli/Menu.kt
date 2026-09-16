package com.edric.orderingfood_feli

class Menu(){
    val itemList = ArrayList<Item>()

    fun show(){
        if(itemList.size==0){
            println("no items yet...")
        }else{
            println("<-MENU->")
            var i=0
            for(item in itemList) {
                println("$i. ${item.name} | ${item.price}")
                i++
            }
        }
    }
}