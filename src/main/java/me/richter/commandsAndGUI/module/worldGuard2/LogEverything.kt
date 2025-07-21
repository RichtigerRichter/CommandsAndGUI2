package me.richter.commandsAndGUI.module.worldGuard2

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityPlaceEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.inventory.InventoryType

class LogEverything: Listener {
	@EventHandler
	fun blockBrakeEvent(event: BlockBreakEvent) {

	}

	@EventHandler
	fun blockPlaceEvent(event: BlockPlaceEvent) {

	}

	@EventHandler
	fun entityPlaceEvent(event: EntityPlaceEvent) {

	}

	@EventHandler
	fun t2(event: InventoryInteractEvent) {
		when (event.inventory.type) {
			InventoryType.CHEST -> {}
			InventoryType.BARREL -> {}
			InventoryType.BLAST_FURNACE -> {}
			InventoryType.BREWING -> {}
			InventoryType.CHISELED_BOOKSHELF -> {}
			InventoryType.CRAFTER -> {}
			InventoryType.DECORATED_POT -> {}
			InventoryType.DISPENSER -> {}
			InventoryType.DROPPER -> {}
			InventoryType.FURNACE -> {}
			InventoryType.HOPPER -> {}
			InventoryType.JUKEBOX -> {}
			InventoryType.SHULKER_BOX -> {}
			InventoryType.SMOKER -> {}
			else -> return
		}
 
		event




	}


}