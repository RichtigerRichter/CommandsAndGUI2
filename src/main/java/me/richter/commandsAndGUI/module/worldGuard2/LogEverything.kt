package me.richter.commandsAndGUI.module.worldGuard2

import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityPlaceEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.inventory.InventoryType

class LogEverything: Listener {
	@EventHandler
	fun blockBrakeEvent(event: BlockBreakEvent) {
        val blockLocation = event.block.location
        val blockType = event.block.type
        val player = event.player

    }

	@EventHandler
	fun blockPlaceEvent(event: BlockPlaceEvent) {
        val blockLocation = event.block.location
        val blockType = event.block.type
        val player = event.player

	}

	@EventHandler
	fun entityPlaceEvent(event: EntityPlaceEvent) {
        val uuid = event.entity.uniqueId
        val player = event.player
        val entityType = event.entityType
        val spawnReason = event.entity.entitySpawnReason

	}

    @EventHandler
    fun entitySpawnEvent(event: EntitySpawnEvent) {
        val uuid = event.entity.uniqueId
        val spawnLocation = event.location
        val spawnReason = event.entity.entitySpawnReason

    }

    @EventHandler
    fun entityDamageEvent(event: EntityDamageEvent) {
        val uuid = event.entity.uniqueId
        val damageSource = event.damageSource
        val damage = event.damage
        val cause = event.cause

    }

    @EventHandler
    fun entityDeathEvent(event: EntityDeathEvent) {
        val uuid = event.entity.uniqueId
        val damageSource = event.damageSource

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