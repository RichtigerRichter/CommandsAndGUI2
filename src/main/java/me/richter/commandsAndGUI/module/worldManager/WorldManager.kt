package me.richter.commandsAndGUI.module.worldManager

import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldManager(plugin: JavaPlugin) {

    private val server = plugin.server

    fun createWorld(name: String) {
        val worldCreator = WorldCreator(name)
        worldCreator.generatorSettings("{\"layers\": [{\"block\": \"stone\", \"height\": 1}, {\"block\": \"grass_block\", \"height\": 1}], \"biome\":\"plains\"}")
        val world = server.createWorld(worldCreator)
    }

    fun deleteWorld(name: String) {
        // TODO: Implement world deletion logic
    }

    fun joinWorld(player: Player, name: String) {
        val world = server.getWorld(name)
        if (world != null) {
            player.teleport(world.spawnLocation)
        } else {
            player.sendMessage("World $name does not exist.")
        }
    }

    fun getAllWorlds(): List<World> {
        return server.worlds
    }

    fun getAllWorldNames(): MutableList<String> {
        val stringWorlds = mutableListOf<String>()
        for (world in server.worlds) {
            stringWorlds.add(world.name)
        }
        return stringWorlds
    }

    fun getAllIntitWorld(): MutableList<World> {
        //TODO getAllIntitWorld adden
        return server.worlds
    }

    fun getAllIntitWorldNames(): MutableList<String> {
        val stringWorlds = mutableListOf<String>()
        //TODO getAllIntitWorldNames adden
        for (world in server.worlds) {
            stringWorlds.add(world.name)
        }
        return stringWorlds
    }

    fun getChangedGameRules(worldName: String): String {
        val world: World = Bukkit.getWorld(worldName) ?: return "there is no world with that name"
        val allGameRules = world.gameRules
        var changedRules = ""
        for (gameRule in allGameRules) {
            if (world.getGameRuleValue(GameRule.getByName(gameRule) as GameRule<*>) != world.getGameRuleDefault(GameRule.getByName(gameRule) as GameRule<*>)) {
                changedRules += "\n§e$gameRule: §2${world.getGameRuleValue(GameRule.getByName(gameRule) as GameRule<*>)}"
            }
        }
        return changedRules
    }
}
