package me.richter.commandsAndGUI.module.fly.soup

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class SoupEatListener: Listener {
	@EventHandler
	fun soupEatEvent(event: PlayerInteractEvent) {
		val item = event.item ?: return
		if (ItemBuilder().getCustomTagValue(item, "CAG.item") != "flySoup") return
		//if (item != FlySoup().flySoupItem()) return
		if (!event.action.isRightClick) return
		
		event.player.playSound(event.player, Sound.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1f, 1f)

		event.player.sendMessage("snack!")

		if (event.player.gameMode != GameMode.CREATIVE) {
			event.item!!.amount -= 1
		}

		event.isCancelled = true
	}
}