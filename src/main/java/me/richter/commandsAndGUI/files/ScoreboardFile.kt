package me.richter.commandsAndGUI.files

import me.richter.commandsAndGUI.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.StringReader

class ScoreboardFile {
	private val configFile: File = File(Main.instance.dataFolder, "scoreboard.yml")
	private val messages2: YamlConfiguration
	private val dir = File("./plugins/CommandsAndGUI")
	private val file = File(dir, "scoreboard.yml")
	private val config = YamlConfiguration.loadConfiguration(file)
	val scoreboardListMap: MutableMap<String,MutableList<String>> = mutableMapOf()
	var scoreboardDisplayNameMap: MutableMap<String, String> = mutableMapOf()



	init {
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("scoreboard.yml", false)
		}
		messages2 = YamlConfiguration.loadConfiguration(configFile)


		val nameSet = config.getKeys(false)
		for (name in nameSet) {
			scoreboardDisplayNameMap[name] = config["$name.name"] as String

			val textMaybeList = config["$name.lines"] as? MutableList<*>
			val textList: MutableList<String> = mutableListOf()

			if (textMaybeList != null) {
				for (text in textMaybeList) {
					textList.addFirst(text as String)
				}
			}

			scoreboardListMap[name] = textList
		}
	}

	fun loadConfig() {
		val configFile = File(Main.instance.dataFolder, "scoreboard.yml")
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("scoreboard.yml", false)
		}
	}

	fun saveConfig() {
		messages2.save(configFile)
	}

	fun save() {
		messages2.save(configFile)
	}
}
