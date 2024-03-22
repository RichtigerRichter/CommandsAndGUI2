package me.richter.commandsAndGUI.module.vanish

import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

class VanishManager(private val plugin: JavaPlugin) {
    private val pluginFolder = File("./plugins/CommandsAndGUI")
    private val vanishedFile = File(pluginFolder, "vanishedPlayers.yml")


    init {
        // Ensure the plugin folder exists
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs()
        }

        // Ensure the backpack file exists
        if (!vanishedFile.exists()) {
            try {
                vanishedFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun set(player: Player, enabled: Boolean) {
        save(player, enabled)

    }


    fun toggle(player: Player) {
        val config = YamlConfiguration.loadConfiguration(vanishedFile)

        if (!config.contains(player.name) || !config.getBoolean(player.name)) {
            save(player, true)
            player.sendMessage(Component.text(MessagesFile.Message.vanishEnabled))
        }
        else {
            save(player, false)
            player.sendMessage(Component.text(MessagesFile.Message.vanishDisabled))
        }
    }


    fun get(player: Player): Boolean {
        val config = YamlConfiguration.loadConfiguration(vanishedFile)

        return !(!config.contains(player.name) || !config.getBoolean(player.name))
    }


    private fun save(player: Player, enabled: Boolean) {
        val config = YamlConfiguration.loadConfiguration(vanishedFile)
        if (enabled) {
            for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                onlinePlayer.hidePlayer(plugin, player)
            }
        }
        else {
            for (onlinePlayer in Bukkit.getOnlinePlayers()) {

                onlinePlayer.showPlayer(plugin, player)

            }
        }

        config.set(player.name, enabled)
        try {
            config.save(vanishedFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun create() {
        // Ensure the plugin folder exists
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs()
        }

        // Ensure the backpack file exists
        if (!vanishedFile.exists()) {
            try {
                vanishedFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}