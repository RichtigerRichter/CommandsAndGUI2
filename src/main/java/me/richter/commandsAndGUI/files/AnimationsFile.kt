package me.richter.commandsAndGUI.files

import me.richter.commandsAndGUI.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class AnimationsFile {
	private val configFile: File = File(Main.instance.dataFolder, "animations.yml")
	private val messages2: YamlConfiguration
	private val dir = File("./plugins/CommandsAndGUI")
	private val file = File(dir, "animations.yml")
	private val config = YamlConfiguration.loadConfiguration(file)
	val aniStringMap: MutableMap<String, MutableList<String>> = mutableMapOf()
	val aniIntervalMap: MutableMap<String, Int> = mutableMapOf() // delay in millisekunden





	init {
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("animations.yml", false)
		}
		messages2 = YamlConfiguration.loadConfiguration(configFile)



		val names = config.getKeys(false)

		for (name in names) {
			val aniStringList: MutableList<String> = mutableListOf()

			val textList = config["$name.texts"] as? MutableList<*>
			if (textList != null) {
				for (text in textList) {
					aniStringList.add(text.toString())
				}
			}

			aniStringMap[name] = aniStringList

			val changeInterval = config["$name.change-interval"] as? Int
			if (changeInterval != null) {
				aniIntervalMap[name] = changeInterval
			}
		}





	}

	fun loadConfig() {
		val configFile = File(Main.instance.dataFolder, "animations.yml")
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("animations.yml", false)
		}
	}

	fun saveConfig() {
		messages2.save(configFile)
	}

	fun getAnimationList(name: String?): MutableList<String> {
		val maybeList = config["$name.texts"] as? MutableList<*>
		val list: MutableList<String> = mutableListOf()
		if (maybeList == null) {
			list.add("${name ?: "Unnamed"} not found")
			return list
		}
		for (text in maybeList) {
			list.add(text as String)
		}
		return list
	}



	object Animations {





		private fun logMessageNotFound(path: String) {
			// Sendet eine Fehlermeldung an die Konsole
			Main().logger.warning("Message path '$path' not found in animations.yml.")
			//Bukkit.getLogger().warning("Message path '$path' not found in messages.yml.")
		}
	}

	fun save() {
		messages2.save(configFile)
	}
}
