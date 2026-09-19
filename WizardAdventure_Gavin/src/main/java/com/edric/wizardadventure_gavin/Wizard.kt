package com.edric.wizardadventure_gavin

import kotlin.math.max
import kotlin.math.min

class Wizard(): Fighter(){

    init{
        damage=10
        HP=50
        maxHP=50
    }
    var mana=30
    var maxMana=30
    var kills=0
    var evolved=false
    var HPPotion=5
    var manaPotion=5

    fun stats(){
        println("""
            HP: $HP / $maxHP
            Mana: $mana / $maxMana
        """.trimIndent())
        if(evolved){
            println("Kills : $kills")
        }else{
            println("Kills needed to evolve: $kills / 5")
        }
        println("""
            Mana Potions held: $manaPotion
            Health Potions held: $HPPotion
        """.trimIndent())
    }

    fun evolve(){
        kills++
        if(!evolved && kills==5){// hasn't evolved and can evolve
            maxHP=75
            HP=maxHP
            maxMana=45
            mana=maxMana
            damage=15
            evolved=true
            println()
            println("Successfully evolved to strong wizard!")
            println("Increased damage, health, and mana")
            println()
        }
    }

    fun lifesteal(monster:Monster):Boolean{
        var end=false
        var life_stolen=kills-4
        if(evolved){
            if(life_stolen>=monster.HP){
                end=true
                life_stolen=monster.HP
                monster.HP=0
                println("${name} successfully defeated ${monster.name}")

            }
            println("Stole ${life_stolen} HP from ${monster.name}")
            life_stolen = min(life_stolen+HP,maxHP) - HP
            println("healed ${life_stolen}HP")
            HP+=life_stolen
            if(monster.HP<=0)evolve()
        }
        return end
    }

//    region drinkPotion

    fun drinkManaPotion(){
        if(mana>=maxMana){
            println("Mana is already full")
        }else{
            if(manaPotion>0){
                manaPotion--
                val oldMana=mana
                mana= min(maxMana,mana+15)
                println("Drank Mana Potion restored ${mana-oldMana}")
            }else{
                println("No Mana Potion in inventory")
            }
        }
    }

    fun drinkHealthPotion(){
        if(HP>=maxHP){
            println("Health is already full")
        }else{
            if(HPPotion>0){
                HPPotion--
                val oldHP=HP
                HP= min(maxHP,HP+25)
                println("Drank Health Potion restored ${HP-oldHP}")
            }else{
                println("No Health Potion in inventory")
            }
        }
    }

    fun drinkPotion(){
        println("---DRINK POTION---")
        println("1. Drink Mana Potion")
        println("2. Drink Health Potion")
        while (true){
            val select=App.takeInput("Select Action: ",1,2)
            if(select==1){
                if(manaPotion>0&&mana<maxMana){//if usable
                    drinkManaPotion()
                    break
                }else {
                    if (manaPotion <= 0)println("Out of Mana Potions")
                    if (mana>=maxMana)println("Mana is full")
                }
            }else{
                if(HPPotion>0&&HP<maxHP){//if usable
                    drinkHealthPotion()
                    break
                }else {
                    if (HPPotion <= 0)println("Out of Health Potions")
                    if (HP>=maxHP)println("Health is full")
                }
            }
        }

    }

//  endregion

    override fun takeDamage(attacker: Fighter, type: Types?):Boolean {
        var dmg=attacker.damage
        if(dmg>HP){dmg=HP;HP=0}else{HP-=dmg}
        println("${attacker.name} attacked ${name} -${dmg}HP")
        if(HP==0){println("${attacker.name} successfully defeated ${name}")}
        return (HP<=0)
    }

    override fun attack(target: Fighter,type: Types?):Boolean{
        mana-=10
        return super.attack(target, type)
    }

}