package me.richter.commandsAndGUI.module.scoreboard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class JoinLeaveListener : Listener {

	// Map zum Speichern der TestScoreboard-Instanzen für jeden Spieler
	private val playerScoreboards = ConcurrentHashMap<UUID, TestScoreboard>()

	@EventHandler
	fun onJoin(event: PlayerJoinEvent) {
		//todo debug
		return
		val player = event.player

		// Setze die Join-Nachricht mit Adventure-API
		event.joinMessage(Component.text("${player.name} hat den Server betreten.", NamedTextColor.GREEN))

		// Sende eine Willkommensnachricht
		player.sendMessage(
			"""
            ${ChatColor.GOLD}Willkommen auf dem Server! 
            Viel Spaß und viel Vergnügen (^:
            """.trimIndent()
		)

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

		// Setze die Quit-Nachricht
		event.quitMessage(Component.text("${player.name} hat den Server verlassen.", NamedTextColor.RED))
	}
}
