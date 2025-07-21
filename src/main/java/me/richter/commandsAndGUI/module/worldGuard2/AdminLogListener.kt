package me.richter.commandsAndGUI.module.worldGuard2

import me.richter.commandsAndGUI.Main
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent

class AdminLogListener : Listener {
	@EventHandler
	fun clickEvent(event: PlayerInteractEvent) {
		val player = event.player
		if (!Main.playersInAdminMode.contains(player)) return
		if (event.item?.type != Material.WOODEN_SWORD) return
		player.sendMessage("logs: ${event.clickedBlock?.location}")

		event.isCancelled = true
	}

	@EventHandler
	fun placeEvent (event: BlockPlaceEvent) {
		val player = event.player
		if (!Main.playersInAdminMode.contains(player)) return
		player.sendMessage("logs: ${event.blockPlaced.location}")

		event.isCancelled = true
	}

}