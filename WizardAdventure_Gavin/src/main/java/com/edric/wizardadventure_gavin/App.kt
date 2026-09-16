package com.edric.wizardadventure_gavin

class App {

    var name=""
    var wizard= Wizard()

    fun mainLoop(){
        while(true){
            begin()
            home()
        }
    }

    //region takeInput

    fun takeInput(text:String,first:Int,last:Int):Int{
        /**
         * first and last = 0 for no limit
         */

        val noLimit = first==0 && last==0
        while(true){
            print(text)
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
            print(text)
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

    //endregion

    fun begin(){
        println("What's your name?")
        name=readLine()?:name
        wizard= Wizard()
        wizard.name=name
        println()
        println("Good Luck, $name! You're gonna need it!")
        println()
    }

    fun home(){
        println("""
            What’re you going to do?
            1. View Stats
            2. Enter battle

        """.trimIndent())
        val select = takeInput("Select: ",1,2)
        when(select){
            1->viewStats()
            2->enterBattle()
        }
        home()
        //no need to point to home
    }

    //region viewStats

    fun viewStats(){
        while (true){
            println("——— ${name}’s STATS ———")
            wizard.stats()
            println("————————————————————————")
            println("""
            a. Drink Mana Potion
            b. Drink Health Potion
            c. Rename self
            d. Back
        """.trimIndent())
            val select=readLine()?:""
            when{
                select.equals("a",true)->wizard.drinkManaPotion()
                select.equals("b",true)->wizard.drinkHealthPotion()
                select.equals("c",true)->rename()
                select.equals("d",true)->break

            }
        }
    }

    fun rename(){
        val oldName=name
        println("Old Name: $oldName")
        print("New name: ");name=readLine()?:""
        println("Successfully changed name from $oldName to $name")
    }

//            endregion

    //region enterBattle

    fun enterBattle(){
        val monster= Monster(Types.entries.random())

    }

    fun battle(monster: Monster){
        println("——— BATTLE ———\n")
        wizard.stats()
        println()
        monster.show()
        println("""
            ——————————
            a. Fire Attack
            b. Water Attack
            c. Grass Attack
            d. Drink potion
            e. Run
        """.trimIndent())
    }

    //endregion

}