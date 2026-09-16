package com.edric.orderingfood_feli

class Item (
    var name:String,
    var description:String,
    var price:Double
    ){

    fun show(){
        println("Name: $name")
        println("Desc: $description")
        println("Price: $price")
    }

}