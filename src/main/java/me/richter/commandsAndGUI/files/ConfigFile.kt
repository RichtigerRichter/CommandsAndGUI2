package me.richter.commandsAndGUI.files

import me.richter.commandsAndGUI.Main
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigFile {
    private val configFile: File = File(Main.instance.dataFolder, "config.yml")
    private val config2: YamlConfiguration

    init {
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            Main.instance.saveResource("config.yml", false)
        }
        config2 = YamlConfiguration.loadConfiguration(configFile)
    }

    fun loadConfig() {
        val configFile = File(Main.instance.dataFolder, "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            Main.instance.saveResource("config.yml", false)
        }
    }

    fun getConfig(): YamlConfiguration {
        val configFile = File(Main.instance.dataFolder, "config.yml")
        return YamlConfiguration.loadConfiguration(configFile)
    }

    fun saveConfig() {
        val configFile = File(Main.instance.dataFolder, "config.yml")
        config2.save(configFile)
    }

    fun getValue(path: String): Any? {
        return config2.get(path)
    }

    object IsModuleEnabled {
        private val dir = File("./plugins/CommandsAndGUI")
        private val file = File(dir, "config.yml")
        private val config = YamlConfiguration.loadConfiguration(file)

        private fun getBoolean(path: String, default: Boolean = false): Boolean {
            return config.getBoolean(path, default).also {
                if (!config.contains(path)) {
                    logValueNotFound(path, default)
                }
            }
        }

        private fun logValueNotFound(path: String, default: Boolean) {
            // Logs a warning to the console if the configuration path is not found
            Bukkit.getLogger().warning("Configuration value '$path' not found in config.yml. Using default: $default")
        }

        val backpack: Boolean = getBoolean("Modules.BackpackModule", true)
        val fly: Boolean = getBoolean("Modules.FlyModule", true)
        val godmode: Boolean = getBoolean("Modules.GodmodeModule", true)
        val gui: Boolean = getBoolean("Modules.GuiModule", true)
        val invView: Boolean = getBoolean("Modules.InvViewModule", true)
        val worldManager: Boolean = getBoolean("Modules.WorldManager", true)
        val vanish: Boolean = getBoolean("Modules.VanishModule", true)
        val jump: Boolean = getBoolean("Modules.JumpModule", true)
        val heal: Boolean = getBoolean("Modules.HealModule", true)
        val setup: Boolean = getBoolean("Modules.SetupCommand", true)
        val scoreboard: Boolean = getBoolean("Modules.Scoreboard", false)

        // Workstations
        val all: Boolean = getBoolean("Workstations.All", true)
        val workbench: Boolean = getBoolean("Workstations.Workbench", true)
        val enchanting: Boolean = getBoolean("Workstations.Enchanting", true)
        val stonecutter: Boolean = getBoolean("Workstations.Stonecutter", true)
        val cartographyTable: Boolean = getBoolean("Workstations.CartographyTable", true)
        val anvil: Boolean = getBoolean("Workstations.Anvil", true)
        val smithingTable: Boolean = getBoolean("Workstations.SmithingTable", true)
        val grindstone: Boolean = getBoolean("Workstations.Grindstone", true)
        val loom: Boolean = getBoolean("Workstations.Loom", true)
        val furnace: Boolean = getBoolean("Workstations.Furnace", true)
        val blastFurnace: Boolean = getBoolean("Workstations.BlastFurnace", true)
        val creative: Boolean = getBoolean("Workstations.Creative", true)
        val brewing: Boolean = getBoolean("Workstations.Brewing", true)
        val crafting: Boolean = getBoolean("Workstations.Crafting", true)
        val smoker: Boolean = getBoolean("Workstations.Smoker", true)
    }
}
