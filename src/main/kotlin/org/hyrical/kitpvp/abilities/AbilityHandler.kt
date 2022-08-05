package org.hyrical.kitpvp.abilities

import org.hyrical.kitpvp.abilities.impl.DoubleJumpAbility
import org.hyrical.kitpvp.abilities.impl.HookAbility

object AbilityHandler {

    val abilities: MutableMap<String, AbstractAbility> = mutableMapOf()

    fun load() {
        val temp = mutableListOf<AbstractAbility>()
        temp.add(DoubleJumpAbility())
        temp.add(HookAbility())

        temp.forEach {
            abilities[it.getIdentifier().lowercase()] = it
        }
    }
}