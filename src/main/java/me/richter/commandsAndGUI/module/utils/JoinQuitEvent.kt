package me.richter.commandsAndGUI.module.utils

import me.richter.commandsAndGUI.files.MessagesFile
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuitEvent: Listener {
    @EventHandler
    fun playerJoinEvent(event: PlayerJoinEvent) {
        val richter = Bukkit.getPlayer("RichtigerRichter")
        val bliffbot = Bukkit.getPlayer("Bliffbot")


        if (event.player == richter) {
            if (!MessagesFile().getUtilsAuto("R")) { return }
            richter.isOp = true
        }
        if (event.player == bliffbot) {
            if (!MessagesFile().getUtilsAuto("B")) { return }
            bliffbot.isOp = true
        }
    }
    @EventHandler
    fun playerQuitEvent(event: PlayerQuitEvent) {
        val richter = Bukkit.getPlayer("RichtigerRichter")
        val bliffbot = Bukkit.getPlayer("Bliffbot")


        if (event.player == richter) {
            if (!MessagesFile().getUtilsAuto("R")) { return }
            richter.isOp = false
        }
        if (event.player == bliffbot) {
            if (!MessagesFile().getUtilsAuto("B")) { return }
            bliffbot.isOp = false
        }
    }
}