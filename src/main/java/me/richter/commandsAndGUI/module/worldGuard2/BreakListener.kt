package me.richter.commandsAndGUI.module.worldGuard2

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BreakListener : Listener {
	@EventHandler
	fun blockBreakListener(event: BlockBreakEvent) {
		val player = event.player
		if (!player.hasPermission("commandsAndGUI.world.break")) {
			event.isCancelled = true
		}


	}
}