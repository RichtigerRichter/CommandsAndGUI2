//Todo [todo] mehr konfigurierbares machen
package me.richter.commandsAndGUI.files

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigFile {
    private val file: File
    private var config: YamlConfiguration

    init {
        val dir = File("./plugins/CommandsAndGUI")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        file = File(dir, "config.yml")
        if (!file.exists()) {
            file.createNewFile()

            config = YamlConfiguration.loadConfiguration(this.file)

            //enabling or disabling modules
            config.set("------Modules------", "------Modules------")
            config.set("BackpackModule", true)
            config.set("FlyModule", true)
            config.set("GodmodeModule", true)
            config.set("GuiModule", true)
            config.set("WorldManager", true)
            config.set("VanishModule", true)
            config.set("JumpModule", true)
            config.set("HealModule", true)
            config.set("SetupCommand", true)
            config.set("---------Workstations---------", true)
            config.set("Workbench", true)
            config.set("Enchanting", true)
            config.set("Stonecutter", true)
            config.set("CartographyTable", true)
            config.set("Anvil", true)
            config.set("SmithingTable", true)
            config.set("Grindstone", true)
            config.set("Loom", true)

            config.save(file)
        }


        config = YamlConfiguration.loadConfiguration(this.file)
    }

    object IsModuleEnabled {
        private val dir = File("./plugins/CommandsAndGUI")
        private val file = File(dir, "config.yml")
        private val config = YamlConfiguration.loadConfiguration(this.file)

        val backpack = config["BackpackModule"] as Boolean
        val fly = config["FlyModule"] as Boolean
        val godmode = config["GodmodeModule"] as Boolean
        val gui = config["GuiModule"] as Boolean
        val worldManager = config["WorldManager"] as Boolean
        val jump = config["JumpModule"] as Boolean
        val heal = config["HealModule"] as Boolean
        val vanish = config["VanishModule"] as Boolean
        val workstation = config["---------Workstations---------"] as Boolean
        val setup = config["SetupCommand"] as Boolean
        val workbench = config["Workbench"] as Boolean
        val enchanting = config["Enchanting"] as Boolean
        val stonecutter = config["Stonecutter"] as Boolean
        val cartographyTable = config["CartographyTable"] as Boolean
        val anvil = config["Anvil"] as Boolean
        val smithingTable = config["SmithingTable"] as Boolean
        val grindstone = config["Grindstone"] as Boolean
        val loom = config["Loom"] as Boolean



    }

    fun save() {
        config.save(file)
    }
}
