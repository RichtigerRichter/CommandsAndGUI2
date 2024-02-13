// If player is vanished don't send leave message

package me.richter.commandsAndGUI.module.vanish

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class PlayerQuitEvent(private val plugin: JavaPlugin): Listener {
    @EventHandler
    fun playerLeaveEvent(event: PlayerQuitEvent){
        val player = event.player
        if (VanishManager(plugin).get(player)) {
            VanishManager(plugin).set(player, true)
            event.quitMessage(Component.text(""))
        }else{
            VanishManager(plugin).set(player, false)
        }
    }

}