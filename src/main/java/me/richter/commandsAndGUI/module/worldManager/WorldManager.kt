package me.richter.commandsAndGUI.module.worldManager

import org.bukkit.WorldCreator
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldManager : JavaPlugin() {
    fun createWorld(name: String) {
        val worldCreator = WorldCreator(name)

        val world = server.createWorld(worldCreator)
    }

    fun deleteWorld(name: String) {
        TODO("chatGPT sagt unloaden und folder löschen")
    }

    fun joinWorld(name: String, player: Player) {
        if (player.teleport(server.getWorld(name)!!.spawnLocation)) {
            player.teleport(server.getWorld(name)!!.spawnLocation)
        }
    }
}