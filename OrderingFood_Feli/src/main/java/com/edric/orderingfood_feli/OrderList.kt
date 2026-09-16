package com.edric.orderingfood_feli

class OrderList(
    var name:String
    ) {

    val orderList = ArrayList<Order>()
    //second adalah amount
    var total:Double = 0.0
        get()=countTotal()
        private set//ga isa diset
    fun countTotal():Double{
        var hold:Double=0.0
        for(itemOrder in orderList){
            hold+=itemOrder.item.price * itemOrder.amount
        }
        return hold
    }

    fun getIndex(item:Item): Order?{
        for(itemOrder in orderList)
            if (item==itemOrder)
                return itemOrder
        return null
    }

    fun show (){
        if(orderList.size==0){
            println("No items yet...")
        }else{
            println("----$name's Order----")
            var i=1
            for(itemOrder in orderList){
                val name=itemOrder.item.name
                val count=itemOrder.amount
                val price=itemOrder.item.price
                println("$i. ${name} x ${count}    $${count*price}")
            }
            println("------------------------")
            println("TOTAL:           $$total")
        }
    }

    fun addOrder(newOrder:Order){
        for(order in orderList){
            if(order.item==newOrder.item){
                order.amount+=newOrder.amount
                return
            }
        }
//        if none
        orderList.add(newOrder)
    }

    fun removeOrder(index:Int,amount:Int){
        val order = orderList.get(index)
        if(order.amount==amount) {
            orderList.remove(order)
        }else{
            order.amount-=amount
        }
    }
}