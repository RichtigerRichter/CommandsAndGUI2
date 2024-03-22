//TODO is'n bissel scheiße
package me.richter.commandsAndGUI.module.sit

import me.richter.commandsAndGUI.Main
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Turtle

class SitManager {
    fun sit (player: Player) {
        if (Main.sitMap.containsKey(player)) return
        if (!player.isOnGround) return
        val position = player.location.add(0.0, 0.0, 0.0)
        val turtle = position.world.spawnEntity(position, EntityType.TURTLE) as Turtle
        turtle.setAI(false)
        turtle.ageLock = true
        turtle.setBaby()
        turtle.isInvisible = true
        turtle.isInvulnerable = true

        turtle.teleport(position.add(0.0, 0.0, 0.0))

        turtle.addPassenger(player)
        Main.sitMap[player] = turtle
    }
}