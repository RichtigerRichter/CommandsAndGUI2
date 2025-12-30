//TODO is'n bissel scheiße
package me.richter.commandsAndGUI.module.sit

import me.richter.commandsAndGUI.Main
import org.bukkit.Location
import org.bukkit.entity.*
import org.bukkit.metadata.FixedMetadataValue

class SitManager {
    fun sit (player: Player) {
        sitArrow(player)
        //sitTurtle(player)
        //layPlayer(player)
    }
    fun sitTurtle (player: Player) {
        if (Main.sitMap.containsKey(player)) return
        if (!(player as Entity).isOnGround) return
        val position = player.location.add(0.0, 0.0, 0.0)
        val tempSpawnLoc = Location(player.world, -10000.0, -10000.0, -10000.0)
        val turtle = position.world.spawnEntity(tempSpawnLoc, EntityType.TURTLE) as Turtle
        turtle.setAI(false)
        turtle.ageLock = true
        turtle.setBaby()
        turtle.isInvisible = true
        turtle.isInvulnerable = true

        turtle.teleport(position.add(0.0, 0.0, 0.0))

        turtle.addPassenger(player)
        Main.sitMap[player] = turtle
    }
    fun sitArrow (player: Player) {
        if (Main.sitMap.containsKey(player)) return
        //if (!player.isOnGround) return
        //if (player.fallDistance != 0f) return
        val position = player.location.add(0.0, 0.0, 0.0)
        val arrow = position.world.spawnEntity(position, EntityType.ARROW) as Arrow
        //arrow.isInvisible = true
        //arrow.isInvulnerable = true

        arrow.teleport(position.add(0.0, 0.5, 0.0))

        arrow.addPassenger(player)
        Main.sitMap[player] = arrow
    }
    fun sitArmorStand (player: Player) {

    }

    //TODO lay wir sofort gecanceled
    fun layPlayer(player: Player) {
        Main.layMap.add(player)
        player.pose = Pose.SLEEPING
    }



}