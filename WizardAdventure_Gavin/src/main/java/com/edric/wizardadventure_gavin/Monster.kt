package com.edric.wizardadventure_gavin

class Monster(
    val type: Types
    ): Fighter(){

    init{
        name=type.name+"Mon"
        HP=30
        maxHP=30
    }

    fun show(){
        println(name)
        println("HP: $HP / $maxHP")
        println("Type: ${type.name}")
    }

    override fun takeDamage(attacker: Fighter, attackerType: Types) {
        super.takeDamage(attacker, attackerType)
        var dmg=attacker.damage
        if(
            attackerType==Types.GRASS && this.type==Types.WATER||
            attackerType==Types.WATER && this.type==Types.FIRE||
            attackerType==Types.FIRE && this.type==Types.GRASS
            )
        {HP-=dmg*2}else{HP-=dmg}

        if(HP<=0){
            isAlive=false
        }

        println("$name took $dmg amount of damage of ${attacker.name}")

    }


}