package me.richter.commandsAndGUI.modules.fly.soup

import me.richter.commandsAndGUI.items.ItemBuilder
import me.richter.commandsAndGUI.modules.fly.creative.FlyManager
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class SoupEatListener : Listener {
    @EventHandler
    fun soupEatEvent(event: PlayerInteractEvent) {
        val player = event.player

        if (!event.action.isRightClick) return

        val item = event.item ?: return

        if (ItemBuilder().getCustomTagValue(item, "CAG.item") != "flySoup") return

        player.playSound(player, Sound.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1f, 1f)

        FlyManager().addTime(player, 120)



        player.allowFlight = true
        player.isFlying = true
        event.player.sendMessage("snack!")

        if (event.player.gameMode != GameMode.CREATIVE) {
            event.item!!.amount -= 1
        }

        event.isCancelled = true
    }
}