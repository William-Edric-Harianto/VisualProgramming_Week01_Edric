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
            2-> viewOrders()
            3-> viewMenu()
            4-> addMenu()
            5-> editMenu()
            6-> deleteMenu()
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

    fun takeInput(text:String,first:Double,last:Double,error_message:String):Double{
        /**
         * first and last = 0 for no limit
         */

        val noLimit = first==0.0 && last==0.0
        while(true){
            println(text)
            val str = readlnOrNull()?.toDoubleOrNull()
            if(str!=null){
                val inLimit = str>=first&&str<=last
                if(noLimit||inLimit){
                    return str
                }
            }
            print(error_message)
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
        if(orderLists.size==0){
            println("No orders yet...")
        }else{
            println("----ORDERS----")
            var i=1
            for(orderList in orderLists){
                println("$i.")
                orderList.show()
                i++
            }
        }
        home()
    }

    fun viewMenu(){
        menu.show()
        home()
    }

    fun addMenu(){
        println("---ADD MENU---")
        print("Name: ");val name= readLine()?:""
        print("Description: ");val desc= readLine()?:""
        val price= takeInput("Price: ",0.0,10_000.0,"Only (0-10000) is allowed")
        menu.itemList.add(Item(name,desc,price))
        println("successfully added $name | $desc | $$price")
        home()
    }

    fun editMenu(){
        if(menu.itemList.size==0){
            println("no menu added yet...")
        }else{
            println("---Show Menu---")
            menu.show()
            val select = takeInput("Select: ",1,menu.itemList.size)
            val item = menu.itemList.get(select-1)
            item.show()
            println()
            println("---EDIT MENU---")
            print("New Name: ");val name= readLine()?:""
            print("New Description: ");val desc= readLine()?:""
            val price= takeInput("New Price: ",0.0,10_000.0,"Only (0-10000) is allowed")
            val oldName=item.name;val oldDesc=item.description;val oldPrice=item.price
            item.name=name;item.description=desc;item.price=price
            println("Succesfully Changed:")
            println("Name from $oldName to $name")
            println("Name from $oldDesc to $desc")
            println("Name from $oldPrice to $price")
            println()
        }
        home()
    }

    fun deleteMenu(){
        if(menu.itemList.size==0){
            println("No menu added yet...")
        }else{
            println("---DELETE MENU---")
            menu.show()
            val select = takeInput("Select: ",1,menu.itemList.size)
            val item = menu.itemList.get(select-1)
            println("Selection: ")
            item.show()
            println("ARE YOU SURE YOU WANT TO DELETE")
            println("y to delete");val input=readLine()?:""
            if(input.equals("y",true)){
                menu.itemList.remove(item)
                println("Successfully deleted ${item.name} from the menu ")
            }else{
                println("Failed to remove ${item.name} from the menu")
            }
        }
        home()
    }

}