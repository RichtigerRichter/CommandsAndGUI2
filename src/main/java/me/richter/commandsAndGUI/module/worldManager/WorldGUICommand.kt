package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.Funs
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldGUICommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.gui) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
        if (sender !is Player) return false
        if (args.isEmpty()) return false
        val player = Bukkit.getPlayer(sender.uniqueId)!!

        if (args[0] == "gui") {
            WorldGUI().open(sender, plugin)
        }
        if (args[0] == "create") {
            WorldManager(plugin).createWorld(args[1])
        }
        if (args[0] == "join") {
            WorldManager(plugin).joinWorld(player ,args[1])
        }


        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("worldmanager", ignoreCase = true)) {
            //if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val player = Bukkit.getPlayer(sender.name)!!
            val completions = mutableListOf<String>()

            if (args.size == 1) {
                completions.add("gui")
                completions.add("createTempDevWorld")
                completions.add("join")


                completions.add("new")
                completions.add("edit")
                completions.add("saveAsPreset")
                completions.add("create")


                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 2 && args[0] == "createTempDevWorld") {
                completions.add("[WorldName]")

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 2 && args[0] == "new") {
                completions.add("[WorldName]")

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "join") {
                completions.addAll(WorldManager(plugin).getAllWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "edit") {
                completions.addAll(WorldManager(plugin).getAllIntitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "saveAsPreset") {
                completions.addAll(WorldManager(plugin).getAllIntitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "create") {
                completions.addAll(WorldManager(plugin).getAllIntitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 3 && args[0] == "edit") {
                completions.add("seed")
                completions.add("type")
                if (/*TODO type == FLAT*/true) { completions.add("flatLayers") }
                completions.add("generateStructures")
                completions.add("hardcore")
                completions.add("keepSpawnChunksLoaded")

                return completions.filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 4 && args[2] == "type") {
                completions.add("AMPLIFIED")
                completions.add("FLAT")
                completions.add("LARGE_BIOMES")
                completions.add("NORMAL")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 4 && args[2] == "generateStructures") {
                completions.add("true")
                completions.add("false")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 4 && args[2] == "hardcore") {
                completions.add("true")
                completions.add("false")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 4 && args[2] == "keepSpawnChunksLoaded") {
                completions.add("true")
                completions.add("false")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 4 && args[2] == "seed") {
                completions.add("[seed]")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 4 && args[2] == "flatLayers"/*TODO && type == FLAT*/) {
                //TODO layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains 1 stone 5 grass_block
                completions.add("[biome]")
                completions.addAll(Funs().getAllBiomeNames())
                completions.remove("CUSTOM")


                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size > 4 && Funs().isEven(args.size) && args[2] == "flatLayers"/*TODO && type == FLAT*/) {
                //TODO layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains stone 5 grass_block 7
                completions.add("[height]")


                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }
            if (args.size > 4 && Funs().isOdd(args.size) && args[2] == "flatLayers"/*TODO && type == FLAT*/) {
                //TODO layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains stone 5 grass_block 7
                completions.add("[block]")
                completions.addAll(Funs().getAllBlockNames())

                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }

        }
        return mutableListOf()
    }
}