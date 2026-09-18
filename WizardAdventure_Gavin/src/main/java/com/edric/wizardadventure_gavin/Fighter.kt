package com.edric.wizardadventure_gavin

import kotlin.math.max

abstract class Fighter {

    var name=""
    var damage=0
    var HP=0
    var maxHP=0

    fun attack(target: Fighter,type: Types?){
        target.takeDamage(this,type)
    }

    open fun takeDamage(attacker:Fighter,type:Types?){}


}