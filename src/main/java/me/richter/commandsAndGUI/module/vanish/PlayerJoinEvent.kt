// If player is vanished don't send join message & hide vanished players

package me.richter.commandsAndGUI.module.vanish

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class PlayerJoinEvent(private val plugin: JavaPlugin) : Listener {
    @EventHandler
    fun playerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        if (VanishManager(plugin).isVanished(player)) {
            VanishManager(plugin).set(player, true)
            event.joinMessage(Component.text(""))
        } else {
            VanishManager(plugin).set(player, false)
        }


        for (onlinePlayer in Bukkit.getOnlinePlayers()) {
            if (VanishManager(plugin).isVanished(onlinePlayer)) {
                player.hidePlayer(plugin, onlinePlayer)
            }
        }
    }
}