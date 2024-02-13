package me.richter.commandsAndGUI.module.sit

import me.richter.commandsAndGUI.Main
import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.vehicle.VehicleExitEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class SitListener : Listener {
    @EventHandler
    fun unsit(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (!Main.sitMap.containsKey(player)) return
        //val position = player.location.add(0.0, 1.0, 0.0)
        //player.teleport(position)
        val chair = Main.sitMap[player] as Entity
        chair.remove()
        Main.sitMap.remove(player)
    }
}