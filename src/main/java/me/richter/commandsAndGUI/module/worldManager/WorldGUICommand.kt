package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import net.kyori.adventure.util.TriState
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldGUICommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.gui) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return true }
        if (sender !is Player) return false
        if (args.isEmpty()) return false
        val player = Bukkit.getPlayer(sender.uniqueId)!!

        if (args[0] == "gui") {
            WorldGUI().open(sender, plugin)
        }

        if (args.size == 2 && args[0] == "createTempDevWorld") {

        }

        if (args[0] == "list") {
            sender.sendMessage(Component.text(WorldManager(plugin).getAllInitWorldNames().toString()))
        }

        if (args.size == 2 && args[0] == "initialize") {
           WorldManager(plugin).initWorld(args[1])
            sender.sendMessage(Component.text(MessagesFile.Message.initWorldManager))
        }

        if (args.size == 2 && args[0] == "join") {
            WorldManager(plugin).joinWorld(player ,args[1])
        }

        if (args.size == 2 && args[0] == "saveAsPreset") {
            //Todo saveAsPreset
        }

        if (args.size == 2 && args[0] == "create") {
            sender.sendMessage(Component.text(MessagesFile.Message.cratingWorld))
            WorldManager(plugin).createWorld(args[1])
            sender.sendMessage(Component.text(MessagesFile.Message.finCratingWorld))
        }


        if (args[0] == "edit" && args[2] == "type") {
            WorldManager(plugin).setType(args[1], args[3])
        }
        if (args[0] == "edit" && args[2] == "generateStructures") {
            WorldManager(plugin).setGenerateStructures(args[1], args[3].toBoolean())
        }
        if (args[0] == "edit" && args[2] == "hardcore") {
            WorldManager(plugin).setHardcore(args[1], args[3] == "true")
        }
        if (args[0] == "edit" && args[2] == "keepSpawnChunksLoaded") {
            val triState: TriState = TriState.byBoolean(args[3] == "true")
            WorldManager(plugin).setKeepSpawnChunksLoaded(args[1], triState)
        }
        if (args[0] == "edit" && args[2] == "seed") {
            WorldManager(plugin).setSeed(args[1], args[3])
        }
        /*Todo
        if (args[0] == "edit" && args[2] == "flatMapLayers"/*Todo && type == FLAT*/) {
            val biome = args[3]
            var layers = ""
            var layer: String
            var argIndex = 5
            while (argIndex in 5..args.size) {
                if (Funs().isEven(argIndex)) {
                    layer = "{\"block\": \"" + args[argIndex] + "\", \"height\": " + args[argIndex - 1] + "}"
                    layers = if (layers.isBlank()) {
                        layer
                    } else {
                        "$layers, $layer"
                    }
                }
                argIndex++
            }
            sender.sendMessage(Component.text(WorldManager(plugin).setFlatLayers(args[1], layers, biome)))
        }
         */

        //worldmanager edit t flatMapLayers PLAINS BEDROCK 1 STONE 5 GRASS_BLOCK 1

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("worldmanager", ignoreCase = true)) {
            //if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val completions = mutableListOf<String>()

            if (args.size == 1) {
                completions.add("gui")
                completions.add("join")


                completions.add("initialize")
                completions.add("edit")
                completions.add("saveAsPreset")
                completions.add("create")


                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 2 && args[0] == "createTempDevWorld") {
                completions.add("<WorldName>")

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 2 && args[0] == "initialize") {
                completions.add("<WorldName>")

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "join") {
                completions.addAll(WorldManager(plugin).getAllWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "edit") {
                completions.addAll(WorldManager(plugin).getAllInitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "saveAsPreset") {
                completions.addAll(WorldManager(plugin).getAllInitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "create") {
                completions.addAll(WorldManager(plugin).getAllInitWorldNames())

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 3 && args[0] == "edit") {
                completions.add("seed")
                completions.add("type")
                //Todo if (/*Todo type == FLAT*/true) { completions.add("flatMapLayers") }
                completions.add("generateStructures")
                completions.add("hardcore")
                completions.add("keepSpawnChunksLoaded")

                return completions.filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
            }



            if (args.size == 4 && args[2] == "type") {
                completions.add("AMPLIFIED")
                completions.add("FLAT")
                completions.add("LARGE_BIOMES")
                completions.add("DEFAULT")

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
                completions.add("<seed>")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            /*
            if (args.size == 4 && args[2] == "flatMapLayers"/*Todo && type == FLAT*/) {
                //Todo layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains 1 stone 5 grass_block
                completions.add("<biome>")
                completions.addAll(Funs().getAllBiomeNames())
                completions.remove("CUSTOM")


                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }
            if (args.size > 4 && Funs().isEven(args.size) && args[2] == "flatMapLayers"/*Todo && type == FLAT*/) {
                //Todo layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains stone 5 grass_block 7
                completions.add("<height>")


                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }
            if (args.size > 4 && Funs().isOdd(args.size) && args[2] == "flatMapLayers"/*Todo && type == FLAT*/) {
                //Todo layer system ausdenken
                //{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}
                //z.B. => plains stone 5 grass_block 7
                completions.add("<block>")
                completions.addAll(Funs().getAllBlockNames())

                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }

             */

        }
        return mutableListOf()
    }
}