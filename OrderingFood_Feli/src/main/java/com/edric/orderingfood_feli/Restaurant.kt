package com.edric.orderingfood_feli

class Restaurant(
    val menu:Menu,
    val orderLists:ArrayList<OrderList>
    ) {

    fun home(){
        println("ORDER SYSTEM")
        println("1. Make Order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        val input = takeInput("Selection: ",1,7)

        when(input){
            1-> makeOrder()
            2-> viewOrder()
            3-> makeOrder()
            4-> makeOrder()
            5-> makeOrder()
            6-> makeOrder()
            7->return
            else->home()
        }
    }

    //region takeInput

    fun takeInput(text:String,first:Int,last:Int):Int{
        /**
         * first and last = 0 for no limit
         */

        val noLimit = first==0 && last==0
        while(true){
            println(text)
            val str = readlnOrNull()?.toIntOrNull()
            if(str!=null){
                val inLimit = str>=first&&str<=last
                if(noLimit||inLimit){
                    return str
                }
            }
            print("Wrong input, only integer between ($first - $last) is allowed!")
        }
    }

    fun takeInput(text:String,first:Int,last:Int,error_message:String):Int{
        /**
         * first and last = 0 for no limit
         */

        val noLimit = first==0 && last==0
        while(true){
            println(text)
            val str = readlnOrNull()?.toIntOrNull()
            if(str!=null){
                val inLimit = str>=first&&str<=last
                if(noLimit||inLimit){
                    return str
                }
            }
            print(error_message)
        }
    }

    fun takeInput(text:String,first:Double,last:Double):Double{
        /**
         * first and last = 0.0 for no limit
         */
        val noLimit = first==0.0 && last==0.0
        while(true){
            print(text)
            val str = readlnOrNull()?.toDoubleOrNull()
            if(str!=null){
                val inLimit = str>=first&&str<=last
                if(noLimit||inLimit){
                    return str
                }
            }
            print("Wrong input, only integer between ($first - $last) is allowed!")
        }
    }

    //endregion

    fun makeOrder(){
        if(menu.itemList.size==0){
            println("No item in menu yet")
        }else{
            println("---Make Order---")
            print("Name: ");val name=readLine()?:""
            val currentOrderList=OrderList(name)
            orderLists.add(currentOrderList)
            while(true){
                menu.show()
                println("0. Exit")
                val select=takeInput("Select: ",0,menu.itemList.size)
                val item=menu.itemList.get(select-1)
                if (select==0)break
                val amount=takeInput("Amount",1,1000,"Only (1-1000) is allowed")
                currentOrderList.addOrder(Order(item,amount))
                println("Successfully added to $item x $amount order")
            }
            println()
        }
        home()
    }

    fun viewOrders(){
        println("----ORDERS----")
        var i=1
        for(orderList in orderLists){
            println("$i.")
            orderList.show()
            i++
        }
    }

}