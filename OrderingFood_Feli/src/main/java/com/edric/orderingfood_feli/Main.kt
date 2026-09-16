package com.edric.orderingfood_feli

fun main(){
    val menu=Menu()
    val orderLists= ArrayList<OrderList>()
    val restaurant = Restaurant(menu,orderLists)
    restaurant.home()
}