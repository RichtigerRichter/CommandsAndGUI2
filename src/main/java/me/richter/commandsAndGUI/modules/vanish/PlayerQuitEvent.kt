// If player is vanished don't send leave message

package me.richter.commandsAndGUI.modules.vanish

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitEvent : Listener {
    @EventHandler
    fun playerLeaveEvent(event: PlayerQuitEvent) {
        val player = event.player
        if (VanishManager().isVanished(player)) {
            VanishManager().set(player, true)
            event.quitMessage(Component.text(""))
        } else {
            VanishManager().set(player, false)
        }
    }

}