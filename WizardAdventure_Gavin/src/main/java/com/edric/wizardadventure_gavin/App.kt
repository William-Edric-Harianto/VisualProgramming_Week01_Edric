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

    companion object {
        //region takeInput

        fun takeInput(text: String, first: Int, last: Int): Int {
            /**
             * first and last = 0 for no limit
             */

            val noLimit = first == 0 && last == 0
            while (true) {
                print(text)
                val str = readlnOrNull()?.toIntOrNull()
                if (str != null) {
                    val inLimit = str >= first && str <= last
                    if (noLimit || inLimit) {
                        return str
                    }
                }
                print("Wrong input, only integer between ($first - $last) is allowed!")
            }
        }

        fun takeInput(text: String, first: Int, last: Int, error_message: String): Int {
            /**
             * first and last = 0 for no limit
             */

            val noLimit = first == 0 && last == 0
            while (true) {
                print(text)
                val str = readlnOrNull()?.toIntOrNull()
                if (str != null) {
                    val inLimit = str >= first && str <= last
                    if (noLimit || inLimit) {
                        return str
                    }
                }
                print(error_message)
            }
        }

        //endregion
    }

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
        if(wizard.HP<=0){
            println()
            println("${wizard.name} has been slain!")
            println()
            println("Restarting game...")
            println()
        }else{
            home()
        }

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
            print("Selection: ");val select=readLine()?:""
            when{
                select.equals("a",true)->wizard.drinkManaPotion()
                select.equals("b",true)->wizard.drinkHealthPotion()
                select.equals("c",true)->rename()
                select.equals("d",true)->break
                else->println("Invalid Input!")
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
        var end=false
        println("${wizard.name} entered into battle and encountered ${monster.name}!")
        do{
            end=battle(monster)
        }while(!end)
        println("battle ended!")
    }

    fun battle(monster: Monster):Boolean{
        println("——— BATTLE ———\n")
        wizard.stats()
        println()
        monster.show()
        var end = wizardATK(monster)
        if(!end) end=monsterATK(monster)
        return end
    }

    fun wizardATK(monster: Monster):Boolean{
//        preset
        var end=false
//        input
        while(true){
            println("""
                ——————————
                a. Fire Attack
                b. Water Attack
                c. Grass Attack
                d. Drink potion
                e. Run
            """.trimIndent())

            print("Selection: ");val select = readLine()

            when{
                select.equals("a",true)->{
                    if(wizard.mana<10){
                        println("Not enough Mana!")
                    }else{
                        wizard.attack(monster,Types.FIRE)
                        break
                    }
                }
                select.equals("b",true)->{
                    if(wizard.mana<10){
                        println("Not enough Mana!")
                    }else {
                        wizard.attack(monster, Types.WATER)
                        break
                    }
                }
                select.equals("c",true)->{
                    if(wizard.mana<10){
                        println("Not enough Mana!")
                    }else {
                        wizard.attack(monster, Types.GRASS)
                        break
                    }
                }
                select.equals("d",true)->{
                    if(//there is at least a usable potion
                        (wizard.manaPotion>0 && wizard.HP<wizard.maxHP) ||
                        (wizard.HPPotion>0 && wizard.mana<wizard.maxMana)
                        ){
                        wizard.drinkPotion();break
                    }else{
                        if(wizard.manaPotion<=0)println("out of Mana Potion")
                        if(wizard.HPPotion<=0)println("out of Health Potion")
                        if(wizard.HP>=wizard.maxHP)println("Health is full already")
                        if(wizard.mana>=wizard.maxMana)println("Mana is full already")
                    }
                }
                select.equals("e",true)->{
                    end = true
                    println("${wizard.name} ran from battle")
                    break}
                else->println("Invalid Input")
            }
        }

        if(!end&&monster.HP>0){
            wizard.lifesteal(monster)
        }

        return (end||monster.HP<=0)
    }

    fun monsterATK(monster: Monster):Boolean{
//        preset
        val end:Boolean = monster.attack(wizard,null)
        return (end||wizard.HP==0)
    }

    //endregion

}