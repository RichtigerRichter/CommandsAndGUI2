package me.richter.commandsAndGUI.module.worldGuard2

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerTeleportEvent

class PreventPlayerActionesListener : Listener {
	@EventHandler
	fun onInteract(event: PlayerInteractEvent) {
		if (event.action.isLeftClick) return
		if ((event.clickedBlock ?: return).type.isInteractable && event.player.isSneaking) return //https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html#isInteractable() is garnet depri
		if (!(event.clickedBlock ?: return).type.isInteractable) return
		if (!event.player.hasPermission("commandsAndGUI.world.interact")) { event.isCancelled = true }
	}

	@EventHandler
	fun onAttack(event: EntityDamageEvent) {
		val causingEntity = event.damageSource.causingEntity ?: return
		println(causingEntity)
		if ((causingEntity.type) == EntityType.PLAYER) {
			println("player")
			if (!(causingEntity as Player).hasPermission("commandsAndGUI.world.attack")) {
				event.isCancelled = true
			}
		}
	}

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (!event.player.hasPermission("commandsAndGUI.world.break")) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (!event.player.hasPermission("commandsAndGUI.world.place")) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onMoveCam(event: PlayerMoveEvent) {
        if (!event.player.hasPermission("commandsAndGUI.world.moveCam") && event.hasChangedOrientation()) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (!event.player.hasPermission("commandsAndGUI.world.move") && event.hasChangedPosition()) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onTeleport(event: PlayerTeleportEvent) {
        if (!event.player.hasPermission("commandsAndGUI.world.teleport") && event.hasChangedPosition()) {
            event.isCancelled = true
        }
    }
}
