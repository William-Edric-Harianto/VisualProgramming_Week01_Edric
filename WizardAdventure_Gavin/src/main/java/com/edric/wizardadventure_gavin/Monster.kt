package com.edric.wizardadventure_gavin

class Monster(
    val type: Types
    ): Fighter(){

    init{
        name=type.name+"Mon"
        HP=30
        maxHP=30
        damage=10
    }

    fun show(){
        println(name)
        println("HP: $HP / $maxHP")
        println("Type: ${type.name}")
    }

    override fun takeDamage(attacker: Fighter, attackerType:Types?):Boolean {
        var dmg=attacker.damage
        var end=false
        if(
            attackerType==Types.GRASS && this.type==Types.WATER||
            attackerType==Types.WATER && this.type==Types.FIRE||
            attackerType==Types.FIRE && this.type==Types.GRASS
            )
        {dmg*=2}//type bener
        //if too much yaach
        if(dmg>=HP){dmg=HP;HP=0}else{HP-=dmg}
        //take damage
        println("$name took $dmg amount of damage from ${attacker.name}")
        if(HP<=0) {
            println("${attacker.name} Successfully defeated ${this.name}")
            if(attacker is Wizard){attacker.evolve()}
            end=true
        }
        return end
    }




}