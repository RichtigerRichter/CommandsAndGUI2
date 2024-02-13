//TODO is'n bissel scheiße
package me.richter.commandsAndGUI.module.sit

import me.richter.commandsAndGUI.Main
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Marker
import org.bukkit.entity.Player
import org.bukkit.entity.Turtle

class SitManager {
    fun sit (player: Player) {
        if (Main.sitMap.containsKey(player)) return
        if (!player.isOnGround) return
        val position = player.location.add(0.0, 0.0, 0.0)
        val armorStand = position.world.spawnEntity(position, EntityType.TURTLE) as Turtle
        armorStand.setAI(false)
        armorStand.ageLock = true
        armorStand.setBaby()
        armorStand.isInvisible = true
        armorStand.isInvulnerable = true

        armorStand.teleport(position.add(0.0, 0.0, 0.0))

        armorStand.addPassenger(player)
        Main.sitMap[player] = armorStand
    }
}