package me.richter.commandsAndGUI.modules.backpack

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File
import java.io.IOException

class BackpackManager {

    fun openGUI(player: Player): Boolean {
        val backpackName = "${player.name}'s Backpack"
        if (!Main.BackpackMap.containsKey(backpackName)) {
            val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
            val backpackFile = File(pluginFolder, "$backpackName.yml")
            val config = YamlConfiguration.loadConfiguration(backpackFile)


            val allowedPlayers = mutableListOf<Player>()
            allowedPlayers.add(player)

            if (!backpackFile.exists()) {
                backpackFile.createNewFile()
                config.set("owner", player)
                config.set("allowedPlayers", allowedPlayers)

                // Erstelle ein neues Inventar, wenn es in der Datei nicht gefunden wird
                val inventory = Bukkit.createInventory(null, 54, Component.text(backpackName))
                Main.BackpackMap[backpackName] = inventory
                val itemsWithPositions = mutableListOf<Map<String, Any?>>()

                for (slot in 0 until inventory.size) {
                    val item = inventory.getItem(slot)
                    val itemData = mapOf("position" to slot, "item" to item)
                    itemsWithPositions.add(itemData)
                }
                // Speichere die Items und ihre Positionen in der Konfiguration
                config.set(backpackName, itemsWithPositions)

                try {
                    config.save(backpackFile)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        player.openInventory(Main.BackpackMap[backpackName]!!)
        return true
    }

    fun open(backpackName: String, player: Player): Boolean {
        if (!Main.BackpackMap.containsKey(backpackName)) {
            return false
        }
        player.openInventory(Main.BackpackMap[backpackName]!!)
        return true
    }

    fun create(backpackName: String, owner: Player) {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)

        val allowedPlayers = mutableListOf<Player>()
        allowedPlayers.add(owner)

        config.set("owner", owner)
        config.set("allowedPlayers", allowedPlayers)

        if (backpackFile.exists()) {
            owner.sendMessage(Component.text(MessagesFile.Message.backpackAlreadyExists))
        } else {
            // Erstelle ein neues Inventar, wenn es in der Datei nicht gefunden wird
            val inventory = Bukkit.createInventory(null, 54, Component.text(backpackName))
            Main.BackpackMap[backpackName] = inventory
            val itemsWithPositions = mutableListOf<Map<String, Any?>>()

            for (slot in 0 until inventory.size) {
                val item = inventory.getItem(slot)
                val itemData = mapOf("position" to slot, "item" to item)
                itemsWithPositions.add(itemData)
            }
            // Speichere die Items und ihre Positionen in der Konfiguration
            config.set(backpackName, itemsWithPositions)


            try {
                config.save(backpackFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            owner.sendMessage(Component.text(MessagesFile.Message.backpackCreated))
        }


    }

    fun delete(backpackName: String): Boolean {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")

        return backpackFile.delete()
    }

    fun addPlayer(backpackName: String, allowedPlayers: MutableList<Player>) {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)

        @Suppress("UNCHECKED_CAST")
        val newAllowedPlayers = config.getList("allowedPlayers", allowedPlayers) as MutableList<Player>
        newAllowedPlayers.addAll(allowedPlayers)



        config.set("allowedPlayers", newAllowedPlayers)

        config.save(backpackFile)
    }

    fun removePlayer(backpackName: String, removedPlayers: MutableList<Player>) {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)

        @Suppress("UNCHECKED_CAST")
        val allowedPlayers = config.getList("allowedPlayers") as MutableList<Player>
        allowedPlayers.removeAll(removedPlayers.toSet())

        config.save(backpackFile)
    }

    fun isAllowedPlayer(backpackName: String, player: Player): Boolean {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)


        @Suppress("UNCHECKED_CAST")
        val allowedPlayers = config.getList("allowedPlayers") as List<Player>

        return allowedPlayers.contains(player)
    }

    fun isOwner(backpackName: String, player: Player): Boolean {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)

        val owner = config.get("owner") as? Player

        return owner == player
    }

    fun isValidBackpack(backpackName: String): Boolean {
        val allBackpacks = allBackpacks()
        return allBackpacks.contains(backpackName)
    }

    fun allBackpacks(): MutableList<String> {
        val allBackpacks = mutableListOf<String>()
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")

        for (file in pluginFolder.listFiles()!!) {
            val backpackName = file.name.replace(".yml", "")
            //val backpackFile = File(pluginFolder, "$backpackName.yml")
            //val config = YamlConfiguration.loadConfiguration(backpackFile)

            allBackpacks.add(backpackName)
        }
        return allBackpacks
    }

    fun allBackpacksAllowedToOpen(player: Player?): MutableList<String> {
        val allBackpacks = mutableListOf<String>()
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")

        for (file in pluginFolder.listFiles()!!) {
            val backpackName = file.name.replace(".yml", "")
            val backpackFile = File(pluginFolder, "$backpackName.yml")
            val config = YamlConfiguration.loadConfiguration(backpackFile)

            @Suppress("UNCHECKED_CAST")
            val allowedPlayers = config["allowedPlayers"] as? MutableList<Player>
            if (allowedPlayers != null && allowedPlayers.contains(player) && !backpackName.contains(" ")) {
                allBackpacks.add(backpackName)
            }
        }
        return allBackpacks
    }

    fun allBackpacksOwned(player: Player?): MutableList<String> {
        val allBackpacks = mutableListOf<String>()
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")

        for (file in pluginFolder.listFiles()!!) {
            val backpackName = file.name.replace(".yml", "")
            val backpackFile = File(pluginFolder, "$backpackName.yml")
            val config = YamlConfiguration.loadConfiguration(backpackFile)

            val owner = config["owner"] as? Player
            if (owner == player && !backpackName.contains(" ")) {
                allBackpacks.add(backpackName)
            }
        }
        return allBackpacks
    }

    fun allPlayersAllowedToOpenBackpack(backpackName: String): MutableList<String> {
        val allPlayersAllowedToOpenBackpack: MutableList<String> = mutableListOf()

        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        val backpackFile = File(pluginFolder, "$backpackName.yml")
        val config = YamlConfiguration.loadConfiguration(backpackFile)

        @Suppress("UNCHECKED_CAST")
        val allowedPlayers = config.getList("allowedPlayers") as List<Player>
        for (player in allowedPlayers) {
            allPlayersAllowedToOpenBackpack.add(player.name)
        }

        return allPlayersAllowedToOpenBackpack
    }

    fun save() {
        for (backpackMap in Main.BackpackMap.entries) {
            val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
            val backpackName = backpackMap.key
            val inventory = backpackMap.value


            val backpackFile = File(pluginFolder, "$backpackName.yml")
            if (!backpackFile.exists()) {
                return
            }
            val config = YamlConfiguration.loadConfiguration(backpackFile)
            val itemsWithPositions = mutableListOf<Map<String, Any?>>()


            for (slot in 0 until inventory.size) {
                val item = inventory.getItem(slot)

                val itemData = mapOf("position" to slot, "item" to item)
                itemsWithPositions.add(itemData)

            }

            // Speichere die Items und ihre Positionen in der Konfiguration
            config.set(backpackName, itemsWithPositions)
            try {
                config.save(backpackFile)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun load() {
        val pluginFolder = File("./plugins/CommandsAndGUI/Backpacks")
        if (!pluginFolder.exists()) {
            pluginFolder.mkdir()
        }

        for (file in pluginFolder.listFiles()!!) {
            val backpackName = file.name.replace(".yml", "")

            val backpackFile = File(pluginFolder, "$backpackName.yml")
            val config = YamlConfiguration.loadConfiguration(backpackFile)

            @Suppress("UNCHECKED_CAST")
            val itemsWithPositions = config.getList(backpackName) as List<Map<String, Any?>>?

            if (itemsWithPositions != null) {
                val inventory = Bukkit.createInventory(null, 54, Component.text(backpackName))

                for (itemData in itemsWithPositions) {
                    val position = itemData["position"] as Int
                    val item = itemData["item"] as ItemStack?
                    inventory.setItem(position, item)
                }

                Main.BackpackMap[backpackName] = inventory
            }
        }
    }
}
