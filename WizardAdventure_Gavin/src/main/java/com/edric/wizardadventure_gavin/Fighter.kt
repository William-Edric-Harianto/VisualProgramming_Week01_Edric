package com.edric.wizardadventure_gavin

import kotlin.math.max

abstract class Fighter {

    var name=""
    var damage=0
    var HP=0
    var maxHP=0

    var isAlive=true

    fun attack(target: Fighter){
        target.takeDamage(this)
    }
    fun attack(target: Fighter,type: Types){
        target.takeDamage(this,type)
    }
    fun takeDamage(attacker:Fighter){
        val dmg= attacker.damage
        if (dmg<HP){
            HP-=dmg
        }else{
            isAlive=false
        }
    }
    open fun takeDamage(attacker:Fighter,type:Types){}


}