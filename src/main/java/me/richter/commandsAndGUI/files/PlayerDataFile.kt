package me.richter.commandsAndGUI.files

import me.richter.commandsAndGUI.Main
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

class PlayerDataFile {
	private val file: File
	private var config: YamlConfiguration

	init {
		val dir = File("./plugins/CommandsAndGUI")
		if (!dir.exists()) {
			dir.mkdirs()
		}

		file = File(dir, "playerData.yml")
		if (!file.exists()) {
			file.createNewFile()

			config = YamlConfiguration.loadConfiguration(this.file)



			config.save(file)
		}


		config = YamlConfiguration.loadConfiguration(this.file)
	}

	fun loadConfig() {
		val configFile = File(Main.instance.dataFolder, "playerData.yml")
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("playerData.yml", false)
		}
	}

	fun setAllowElytralessElytraFlight(player: Player, allow: Boolean) {
		config.set("${player.name}.fly.allow-elytraless-elytra-flight", allow)
		savePlayerDataFile()
	}

	fun getAllowElytralessElytraFlight(player: Player): Boolean {
		return config.getBoolean("${player.name}.fly.allow-elytraless-elytra-flight")
	}




	fun savePlayerDataFile() {
		config.save(file)
	}
}