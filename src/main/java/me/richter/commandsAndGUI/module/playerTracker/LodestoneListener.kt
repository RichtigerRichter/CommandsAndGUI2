package me.richter.commandsAndGUI.module.playerTracker

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class LodestoneListener: Listener {
	@EventHandler
	fun playerInteractEvent(event: PlayerInteractEvent) {
		val player = event.player
		val item = event.item ?: return
		if ((event.clickedBlock?.type ?: return) == Material.LODESTONE) {
			if (ItemBuilder().getCustomTagValue(item, "CAG.item.trackingCompass.entity") != null || ItemBuilder().getCustomTagValue(item, "CAG.item.trackingCompass.location") != null) {
				event.isCancelled = true
			}
		}
	}
}