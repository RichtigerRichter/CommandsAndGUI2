package me.richter.commandsAndGUI.files

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class MessagesFile {
    private val file: File
    private var config: YamlConfiguration

    init {
        val dir = File("./plugins/CommandsAndGUI")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        file = File(dir, "messages.yml")
        if (!file.exists()) {
            file.createNewFile()

            config = YamlConfiguration.loadConfiguration(this.file)

            config.set("R", true)
            config.set("B", false)

            config.set("PREFIX", "§8[§aRichtigerStuff§8]§r")

            config.set("flyingDisabled", "%PREFIX% Flying§7 disabled§r")
            config.set("flyingEnabled", "%PREFIX% Flying§a enabled§r")
            config.set("flyingDisabledFor", "%PREFIX% Flying§7 disabled§r for §6%PLAYER%§r")
            config.set("flyingEnabledFor", "%PREFIX% Flying§a enabled§r for §6%PLAYER%§r")
            config.set("flySpeed0to10", "%PREFIX% §4Fly speed has to be a number from §r0 §4to §r10§r")
            config.set("setFlySpeed", "%PREFIX% Set your fly speed to §a%FlySpeed%§r (default is 1)")
            config.set("getFlySpeed", "%PREFIX% Your current fly speed is §a%FlySpeed%§r (default is 1)")
            config.set("setFlySpeedFor", "%PREFIX% Set fly speed of §6%PLAYER%§r to §a%FlySpeed%§r (default is 1)")
            config.set("getFlySpeedFor", "%PREFIX% The current fly speed of §6%PLAYER%§r is §a%FlySpeed%§r (default is 1)")

            config.set("godDisabled", "%PREFIX% Godmode§7 disabled§r")
            config.set("godEnabled", "%PREFIX% Godmode§a enabled§r")

            config.set("youGotHealed", "%PREFIX% You got Healed§r")

            config.set("vanishDisabled", "%PREFIX% Vanish§7 disabled§r")
            config.set("vanishEnabled", "%PREFIX% Vanish§a enabled§r")

            config.set("playerDoesNotExist", "%PREFIX% §4player does not exist§r")

            config.set("backpackAlredyExists", "%PREFIX% A Backpack with this name already Exists§r")
            config.set("backpackCreated", "%PREFIX% Backpack Created§r")
            config.set("backpackDeleted", "%PREFIX% Backpack Deleted§r")
            config.set("backpackDontExists", "%PREFIX% A Backpack with this name does not Exist§r")
            config.set("youAreNotAllowedToOpenThisBackpack", "%PREFIX% You are not allowed to open this Backpack§r")
            config.set("youAreNotTheOwner", "%PREFIX% You are not the owner of this Backpack§r")

            config.set("moduleNotEnabled", "%PREFIX% §cThis module is not enabled!§r")

            config.set("initWorldManager", "%PREFIX% Initialized world creation§r")
            config.set("cratingWorld", "%PREFIX% Creating world...§r")
            config.set("finCratingWorld", "%PREFIX% Finished world creation§r")

            config.save(this.file)
        }



        config = YamlConfiguration.loadConfiguration(this.file)
    }


    object Message {
        private val dir = File("./plugins/CommandsAndGUI")
        private val file = File(dir, "messages.yml")
        private val config = YamlConfiguration.loadConfiguration(this.file)

        val PREFIX: String = config.getString("PREFIX") as String

        val flyingDisabled = config.getString("flyingDisabled")!!.replace("%PREFIX%", PREFIX)
        val flyingEnabled = config.getString("flyingEnabled")!!.replace("%PREFIX%", PREFIX)
        fun flyingDisabledFor(targetPlayer: String):String {
            val flyingDisabledFor = config.getString("flyingDisabledFor")!!.replace("%PREFIX%", PREFIX)
            return flyingDisabledFor.replace("%PLAYER%", targetPlayer)
        }
        fun flyingEnabledFor(targetPlayer: String):String {
            val flyingEnabledFor = config.getString("flyingEnabledFor")!!.replace("%PREFIX%", PREFIX)
            return flyingEnabledFor.replace("%PLAYER%", targetPlayer)
        }
        val flySpeed0to10 = config.getString("flySpeed0to10")!!.replace("%PREFIX%", PREFIX)
        fun setFlySpeed(flySpeed: String):String {
            val setFlySpeed = config.getString("setFlySpeed")!!.replace("%PREFIX%", PREFIX)

            return setFlySpeed.replace("%FlySpeed%", flySpeed)
        }
        fun getFlySpeed(flySpeed: String):String {
            val getFlySpeed = config.getString("getFlySpeed")!!.replace("%PREFIX%", PREFIX)

            return getFlySpeed.replace("%FlySpeed%", flySpeed)
        }
        fun setFlySpeedFor(flySpeed: String, targetPlayer: String):String {
            val setFlySpeed = config.getString("setFlySpeedFor")!!.replace("%PREFIX%", PREFIX)

            return setFlySpeed.replace("%FlySpeed%", flySpeed).replace("%PLAYER%", targetPlayer)
        }
        fun getFlySpeedFor(flySpeed: String, targetPlayer: String):String {
            val getFlySpeed = config.getString("getFlySpeedFor")!!.replace("%PREFIX%", PREFIX)

            return getFlySpeed.replace("%FlySpeed%", flySpeed).replace("%PLAYER%", targetPlayer)
        }

        val godDisabled = config.getString("godDisabled")!!.replace("%PREFIX%", PREFIX)
        val godEnabled = config.getString("godEnabled")!!.replace("%PREFIX%", PREFIX)

        val youGotHealed = config.getString("youGotHealed")!!.replace("%PREFIX%", PREFIX)

        val vanishDisabled = config.getString("vanishDisabled")!!.replace("%PREFIX%", PREFIX)
        val vanishEnabled = config.getString("vanishEnabled")!!.replace("%PREFIX%", PREFIX)

        val playerDoesNotExist = config.getString("playerDoesNotExist")!!.replace("%PREFIX%", PREFIX)

        val backpackAlredyExists = config.getString("backpackAlredyExists")!!.replace("%PREFIX%", PREFIX)
        val backpackCreated = config.getString("backpackCreated")!!.replace("%PREFIX%", PREFIX)
        val backpackDeleted = config.getString("backpackDeleted")!!.replace("%PREFIX%", PREFIX)
        val backpackDoesntExists = config.getString("backpackDontExists")!!.replace("%PREFIX%", PREFIX)
        val youAreNotAllowedToOpenThisBackpack = config.getString("youAreNotAllowedToOpenThisBackpack")!!.replace("%PREFIX%", PREFIX)
        val youAreNotTheOwner = config.getString("youAreNotTheOwner")!!.replace("%PREFIX%", PREFIX)
        val moduleNotEnabled = config.getString("moduleNotEnabled")!!.replace("%PREFIX%", PREFIX)

        val initWorldManager = config.getString("initWorldManager")!!.replace("%PREFIX%", PREFIX)
        val cratingWorld = config.getString("cratingWorld")!!.replace("%PREFIX%", PREFIX)
        val finCratingWorld = config.getString("finCratingWorld")!!.replace("%PREFIX%", PREFIX)


    }

    fun autoSetupOn(player: String) {
        val config = YamlConfiguration.loadConfiguration(file)

        if (!config.contains(player)) { return }
        config.set(player, true)
        config.save(file)
    }
    fun autoSetupOff(player: String) {
        val config = YamlConfiguration.loadConfiguration(file)

        if (!config.contains(player)) { return }
        config.set(player, false)
        config.save(file)
    }
    fun getSetupAuto(player: String): Boolean {
        val config = YamlConfiguration.loadConfiguration(this.file)

        return !(!config.contains(player) || !config.getBoolean(player))
    }

    fun save() {
        config.save(this.file)
    }
}
