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
    var HPPotion=50
    var manaPotion=50

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
        maxHP=75
        HP=maxHP
        maxMana=45
        mana=maxMana
        damage=15
        evolved=true
        println()
        println("Successfully evolved to strong wizard!")
        println()
    }

    fun lifesteal(monster:Monster){
        if(evolved){
            println("Stole ${kills-4} HP from ${monster.name}")
        }
    }

    fun drinkManaPotion(){
        if(manaPotion<=0){
            manaPotion--
            val oldMana=mana
            mana= min(maxMana,mana+15)
            println("Drank Mana Potion restored ${mana-oldMana}")
        }
    }

    fun drinkHealthPotion(){
        if(HPPotion<=0){
            HPPotion--
            val oldHP=HP
            HP= min(maxHP,HP+25)
            println("Drank Health Potion restored ${HP-oldHP}")
        }
    }



}