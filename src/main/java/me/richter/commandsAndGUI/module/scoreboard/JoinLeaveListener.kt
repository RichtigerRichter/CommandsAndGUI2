package me.richter.commandsAndGUI.module.scoreboard

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class JoinLeaveListener : Listener {
    // Map zum Speichern der TestScoreboard-Instanzen für jeden Spieler
    private val playerScoreboards = ConcurrentHashMap<UUID, TestScoreboard>()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        // Scoreboard erstellen und initialisieren
        val scoreboard = TestScoreboard(player).also {
            it.init()
        }

        // Scoreboard in der Map speichern
        playerScoreboards[player.uniqueId] = scoreboard
    }

    @EventHandler
    fun onLeave(event: PlayerQuitEvent) {
        val player = event.player
        val scoreboard = playerScoreboards.remove(player.uniqueId)

        // Tasks sicher beenden
        scoreboard?.apply {
            stopRunEverySec()
            stopRunEveryTick()
        }
    }
}
