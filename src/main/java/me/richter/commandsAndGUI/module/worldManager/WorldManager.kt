package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.Main.Companion.initWorldCreator
import net.kyori.adventure.util.TriState
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldManager(plugin: JavaPlugin) {

    private val server = plugin.server

    fun initWorld(name: String) {
        initWorldCreator[name] = WorldCreator(name)
        server.logger.info(initWorldCreator[name].toString())
    }

    fun setSeed(name: String, seed: String) {
        initWorldCreator[name] = initWorldCreator[name]?.seed(seed.toLong())!!
    }

    fun setKeepSpawnChunksLoaded(name: String, set: TriState) {
        initWorldCreator[name] = initWorldCreator[name]?.keepSpawnLoaded(set)!!

    }

    fun setHardcore(name: String, set: Boolean) {
        initWorldCreator[name] = initWorldCreator[name]?.hardcore(set)!!
    }

    fun setGenerateStructures(name: String, set: Boolean) {
        initWorldCreator[name] = initWorldCreator[name]?.generateStructures(set)!!
    }

    fun setType(name: String, type: String) {
        val wType = WorldType.getByName(type)!!
        initWorldCreator[name] = initWorldCreator[name]?.type(wType)!!
    }

    fun setFlatLayers(name: String, layers: String, biome: String): String {
        val generatorSettings = "{\"layers\": [$layers], \"biome\":\"$biome\"}"
        initWorldCreator[name] = initWorldCreator[name]?.generatorSettings(generatorSettings)!!
        return generatorSettings
    }

    fun createWorld(name: String): String {
        val creator = initWorldCreator[name]!!
        server.createWorld(creator)
        initWorldCreator.remove(name)
        return creator.toString()
        /*
        val worldCreator = WorldCreator(name)
        worldCreator.generatorSettings("{\"layers\": [{\"block\": \"stone\", \"height\": 1}, {\"block\": \"grass_block\", \"height\": 1}], \"biome\":\"plains\"}")
        val world = server.createWorld(worldCreator)
         */
    }

    fun joinWorld(player: Player, name: String) {
        val world = server.getWorld(name)
        if (world != null) {
            player.teleport(world.spawnLocation)
        } else {
            player.sendMessage("World $name does not exist.")
        }
    }

    fun deleteWorld(name: String) {
        // Todo: Implement world deletion logic
    }

    fun isFlatType(worldName: String): Boolean {
        val worldGenerator = initWorldCreator[worldName] ?: return false
        return worldGenerator.type().toString() == "FLAT"
    }

    fun getChangedGameRules(worldName: String): String {
        val world: World = Bukkit.getWorld(worldName) ?: return "there is no world with that name"
        val allGameRules = world.gameRules
        var changedRules = ""
        for (gameRule in allGameRules) {
            if (world.getGameRuleValue(GameRule.getByName(gameRule) as GameRule<*>) != world.getGameRuleDefault(
                    GameRule.getByName(
                        gameRule
                    ) as GameRule<*>
                )
            ) {
                changedRules += "\n§e$gameRule: §2${world.getGameRuleValue(GameRule.getByName(gameRule) as GameRule<*>)}"
            }
        }
        return changedRules
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

    fun getAllInitWorldNames(): MutableList<String> {
        val stringWorlds = mutableListOf<String>()
        //TODO getAllIntitWorldNames adden

        for (world in initWorldCreator.keys) {
            stringWorlds.add(world)
        }
        return stringWorlds
    }
}
