package me.richter.commandsAndGUI.files

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.MessagesFile.Message
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Messages2File {
	private val configFile: File = File(Main.instance.dataFolder, "messages2.yml")
	private val messages2: YamlConfiguration

	init {
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("messages2.yml", false)
		}
		messages2 = YamlConfiguration.loadConfiguration(configFile)
	}

	fun loadConfig() {
		val configFile = File(Main.instance.dataFolder, "messages2.yml")
		if (!configFile.exists()) {
			configFile.parentFile.mkdirs()
			Main.instance.saveResource("messages2.yml", false)
		}
	}

	fun saveConfig() {
		messages2.save(configFile)
	}

	object Message {
		private val dir = File("./plugins/CommandsAndGUI")
		private val file = File(dir, "messages2.yml")
		private val config = YamlConfiguration.loadConfiguration(file)

		private val PREFIX: String = config.getString("PREFIX") ?: run {
			logMessageNotFound("PREFIX")
			"§8[§aRichtigerStuff§8]§r"
		}

		private fun getMessage(path: String, default: String): String {
			return config.getString(path)?.replace("%PREFIX%", PREFIX) ?: run {
				logMessageNotFound(path)
				default
			}
		}

		private fun logMessageNotFound(path: String) {
			// Sendet eine Fehlermeldung an die Konsole
			Bukkit.getLogger().warning("Message path '$path' not found in messages.yml.")
		}

		val flyingDisabled = getMessage("flyingDisabled", "$PREFIX Flying disabled")
		val flyingEnabled = getMessage("flyingEnabled", "$PREFIX Flying enabled")

		fun flyingDisabledFor(targetPlayer: String): String {
			return getMessage("flyingDisabledFor", "$PREFIX Flying disabled for %PLAYER%")
				.replace("%PLAYER%", targetPlayer)
		}

		fun flyingEnabledFor(targetPlayer: String): String {
			return getMessage("flyingEnabledFor", "$PREFIX Flying enabled for %PLAYER%")
				.replace("%PLAYER%", targetPlayer)
		}

		val flySpeed0to10 = getMessage("flySpeed0to10", "$PREFIX Fly speed has to be a number from 0 to 10")

		fun setFlySpeed(flySpeed: String): String {
			return getMessage("setFlySpeed", "$PREFIX Set your fly speed to %FlySpeed% (default is 1)")
				.replace("%FlySpeed%", flySpeed)
		}

		fun getFlySpeed(flySpeed: String): String {
			return getMessage("getFlySpeed", "$PREFIX Your current fly speed is %FlySpeed% (default is 1)")
				.replace("%FlySpeed%", flySpeed)
		}

		fun setFlySpeedFor(flySpeed: String, targetPlayer: String): String {
			return getMessage("setFlySpeedFor", "$PREFIX Set fly speed of %PLAYER% to %FlySpeed% (default is 1)")
				.replace("%FlySpeed%", flySpeed)
				.replace("%PLAYER%", targetPlayer)
		}

		fun getFlySpeedFor(flySpeed: String, targetPlayer: String): String {
			return getMessage("getFlySpeedFor", "$PREFIX The current fly speed of %PLAYER% is %FlySpeed% (default is 1)")
				.replace("%FlySpeed%", flySpeed)
				.replace("%PLAYER%", targetPlayer)
		}

		val godDisabled = getMessage("godDisabled", "$PREFIX Godmode disabled")
		val godEnabled = getMessage("godEnabled", "$PREFIX Godmode enabled")

		val youGotHealed = getMessage("youGotHealed", "$PREFIX You got Healed")

		val vanishDisabled = getMessage("vanishDisabled", "$PREFIX Vanish disabled")
		val vanishEnabled = getMessage("vanishEnabled", "$PREFIX Vanish enabled")

		val playerDoesNotExist = getMessage("playerDoesNotExist", "$PREFIX Player does not exist")

		val backpackAlreadyExists = getMessage("backpackAlredyExists", "$PREFIX A Backpack with this name already Exists")
		val backpackCreated = getMessage("backpackCreated", "$PREFIX Backpack Created")
		val backpackDeleted = getMessage("backpackDeleted", "$PREFIX Backpack Deleted")
		val backpackDoesntExists = getMessage("backpackDontExists", "$PREFIX A Backpack with this name does not Exist")
		val youAreNotAllowedToOpenThisBackpack = getMessage("youAreNotAllowedToOpenThisBackpack", "$PREFIX You are not allowed to open this Backpack")
		val youAreNotTheOwner = getMessage("youAreNotTheOwner", "$PREFIX You are not the owner of this Backpack")
		val moduleNotEnabled = getMessage("moduleNotEnabled", "$PREFIX This module is not enabled!")

		val initWorldManager = getMessage("initWorldManager", "$PREFIX Initialized world creation")
		val creatingWorld = getMessage("cratingWorld", "$PREFIX Creating world...")
		val finCreatingWorld = getMessage("finCratingWorld", "$PREFIX Finished world creation")

		val cannotInvSeeYourSelf = getMessage("cannotInvSeeYourSelf", "$PREFIX You can not InvSee yourself (mainly because im to lazy to fix some bugs)§r")

	}

	fun autoUtilsOn(player: String) {
		messages2.set(player, true)
		saveConfig()
	}

	fun autoUtilsOff(player: String) {
		messages2.set(player, false)
		saveConfig()
	}

	fun getUtilsAuto(player: String): Boolean {
		return messages2.getBoolean(player, false)
	}

	fun save() {
		messages2.save(configFile)
	}
}
