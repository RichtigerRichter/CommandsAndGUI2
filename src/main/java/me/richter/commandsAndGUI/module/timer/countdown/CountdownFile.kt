package me.richter.commandsAndGUI.module.timer.countdown

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class CountdownFile {
	private val file: File
	private var config: YamlConfiguration

	init {
		val dir = File("./plugins/CommandsAndGUI")
		if (!dir.exists()) {
			dir.mkdirs()
		}

		file = File(dir, "countdown.yml")
		if (!file.exists()) {
			file.createNewFile()

			config = YamlConfiguration.loadConfiguration(this.file)

			config.save(file)
		}


		config = YamlConfiguration.loadConfiguration(this.file)
	}

	fun getAllTopGroupsExceptFlySoup(): MutableSet<String> {
		val list: MutableSet<String> = mutableSetOf()
		for (entry in config.getKeys(false)) {
			if (entry == "flySoup") continue
			list.add(entry)
		}
		return list
	}

	fun setTime(name: String, time: Int) {
		config.set("$name.time", time)
		saveCountdownFile()
	}

	fun getTime(name: String): Int {
		return config.getInt("$name.time")
	}

	fun setRunning(name: String, isRunning: Boolean) {
		config.set("$name.isRunning", isRunning)
		saveCountdownFile()
	}

	fun getRunning(name: String): Boolean {
		return config.getBoolean("$name.isRunning")
	}

	fun getConfig(): YamlConfiguration {
		return config
	}

	fun remove(path: String) {
		config.set(path, null)
		saveCountdownFile()
	}

	fun saveCountdownFile() {
		config.save(file)
	}
}