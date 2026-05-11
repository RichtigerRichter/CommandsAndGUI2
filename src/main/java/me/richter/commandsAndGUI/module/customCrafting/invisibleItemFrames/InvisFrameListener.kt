package me.richter.commandsAndGUI.module.customCrafting.invisibleItemFrames

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.hanging.HangingPlaceEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.inventory.ItemStack

class InvisFrameListener: Listener {
	fun runEveryTick() {
		for (player in Main.showInvisibleItemFrameParticleMap.keys) {
			for (entity in player.getNearbyEntities(50.0, 50.0, 50.0)) {
				if (entity.type != EntityType.ITEM_FRAME && entity.type != EntityType.GLOW_ITEM_FRAME) continue
				if (!entity.isInvisible) continue

                val item = if (entity.type == EntityType.ITEM_FRAME) {
                    ItemStack(Material.ITEM_FRAME)
                } else {
                    ItemStack(Material.GLOW_ITEM_FRAME)
                }

                player.spawnParticle(Particle.ITEM, entity.location, 1, 0.0, 0.0, 0.0, 0.0, item)
                //player.spawnParticle(Particle.COMPOSTER, entity.location, 1)

                if (ItemBuilder().getCustomTagValue(player.inventory.itemInMainHand, "cag.item") != "invisibleItemFrame") {
                    Main.showInvisibleItemFrameParticleMap.remove(player)
                }
            }
		}
	}

	@EventHandler
	fun itemFramePlaceEvent(event: HangingPlaceEvent) {
		if (event.entity.type == EntityType.ITEM_FRAME) {
            val item = event.itemStack ?: return

            if (ItemBuilder().getCustomTagValue(item, "cag.item") == "invisibleItemFrame") {
                val entity = event.entity as ItemFrame
                entity.isVisible = false
            }
        }

        if (event.entity.type == EntityType.GLOW_ITEM_FRAME) {
            val item = event.itemStack ?: return

            if (ItemBuilder().getCustomTagValue(item, "cag.item") == "invisibleItemFrame") {
                val entity = event.entity as ItemFrame
                entity.isVisible = false
            }
        }

	}

	@EventHandler
	fun itemFrameRemoveEvent(event: HangingBreakEvent) {
		// Check if the entity is an Item Frame or Glow Item Frame
		if (event.entity.type != EntityType.ITEM_FRAME && event.entity.type != EntityType.GLOW_ITEM_FRAME) return

		val entity = event.entity as ItemFrame

		// Check if the item frame was invisible
		if (!entity.isVisible) {
			event.isCancelled = true

            // Cancel the default behavior
            val itemFrameItem: ItemStack = if (event.entity.type == EntityType.ITEM_FRAME) {
                InvisibleItemFrameManager().invisibleItemFrameItem()
            } else {
                InvisibleItemFrameManager().invisibleGlowItemFrameItem()
            }

			entity.world.dropItem(entity.location, itemFrameItem)
			//entity.world.dropItem(entity.location, (event.entity as ItemFrame).item)

			entity.remove()
		}


    }

	@EventHandler
	fun onSlotChange(event: PlayerItemHeldEvent) {
		val player = event.player
		val newItem = player.inventory.getItem(event.newSlot) ?: ItemStack(Material.AIR)

		if (ItemBuilder().getCustomTagValue(newItem, "cag.item") == "invisibleItemFrame") {
			Main.showInvisibleItemFrameParticleMap[player] = true

		} else {
			Main.showInvisibleItemFrameParticleMap.remove(player)
			for (entity in player.world.entities) {
				if (entity.type != EntityType.ITEM_FRAME && entity.type != EntityType.GLOW_ITEM_FRAME) continue
				if (!entity.isInvisible) continue
				Main.glowingBlocksAPI.unsetGlowing(entity.location, player)
				Main.glowingEntitiesAPI.unsetGlowing(entity, player)

			}
		}
	}

}