package me.richter.commandsAndGUI

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.module.backpack.BackpackCommand
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.fly.FlyCommand
import me.richter.commandsAndGUI.module.godmode.GodCommand
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.GUICloseListener
import me.richter.commandsAndGUI.module.guiNew.GUICommand
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUIClickListener
import me.richter.commandsAndGUI.module.heal.HealCommand
import me.richter.commandsAndGUI.module.jump.JumpCommand
import me.richter.commandsAndGUI.module.sit.SitCommand
import me.richter.commandsAndGUI.module.sit.SitListener
import me.richter.commandsAndGUI.module.utils.HeadCommand
import me.richter.commandsAndGUI.module.utils.JoinQuitEvent
import me.richter.commandsAndGUI.module.utils.SetupCommand
import me.richter.commandsAndGUI.module.utils.Utils
import me.richter.commandsAndGUI.module.vanish.PlayerJoinEvent
import me.richter.commandsAndGUI.module.vanish.PlayerQuitEvent
import me.richter.commandsAndGUI.module.vanish.VanishCommand
import me.richter.commandsAndGUI.module.vanish.VanishManager
import me.richter.commandsAndGUI.module.workstations.OpenCommand
import me.richter.commandsAndGUI.module.worldGuard2.BreakListener
import me.richter.commandsAndGUI.module.worldManager.WorldGUICommand
import org.bukkit.WorldCreator
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Main : JavaPlugin() {

    companion object {


        val guiMainMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiFlySettingsMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiWorkstationMap: MutableMap<UUID, Inventory> = mutableMapOf()

        val sitMap: MutableMap<Player, Entity> = mutableMapOf()

        val initWorldCreator: MutableMap<String, WorldCreator> = mutableMapOf()

        val BackpackMap: MutableMap<String, Inventory> = mutableMapOf()
        val vanishedPlayersMap: MutableMap<UUID, Boolean> = mutableMapOf()
    }


    override fun onEnable() {
        // Plugin startup logic

        registerCommands()
        registerListeners()

        ConfigFile().save()
        MessagesFile().save()
        VanishManager(this).create()
        BackpackManager().load()

        printPluginInfo()
        logger.info("CommandsAndGUI has been Loaded")

        if (!server.minecraftVersion.startsWith("1")) {
            logger.warning("THIS PLUGIN IS ONLY MADE FOR MINECRAFT VERSIONS 1.x.x IF THE SERVER IS RUNNING ON A NEWER VERSION PLEASE CONTACT THE PLUGIN DEV SINCE THE VERSION CHECKS WON'T WORK")
        }
    }

    private fun printPluginInfo() {
        val r = "\u001B[0m"

        val s = "\u001B[90m" //schatten => grau
        val b = "\u001B[34m" //border => blau
        val f = "\u001B[31m" //font => rot
        val t = "\u001B[97m" //text => weiß

        println("${b}####################################################################################################$r")
        println("${b}#                                                                                                  #$r")
        println("${b}#  ${f}██████${s}╗ ${f}██${s}╗ ${f}██████${s}╗${f}██${s}╗  ${f}██${s}╗${f}████████${s}╗${f}███████${s}╗${f}██████${s}╗ ${f}██${s}╗${f}███████${s}╗    ${f}███████${s}╗${f}██${s}╗  ${f}██${s}╗${f}██${s}╗${f}████████${s}╗ ${b}#$r")
        println("${b}#  ${f}██${s}╔══${f}██${s}╗${f}██${s}║${f}██${s}╔════╝${f}██${s}║  ${f}██${s}║╚══${f}██${s}╔══╝${f}██${s}╔════╝${f}██${s}╔══${f}██${s}╗${f}██${s}║${f}██${s}╔════╝    ${f}██${s}╔════╝${f}██${s}║  ${f}██${s}║${f}██${s}║╚══${f}██${s}╔══╝ ${b}#$r")
        println("${b}#  ${f}██████${s}╔╝${f}██${s}║${f}██${s}║     ${f}███████${s}║   ${f}██${s}║   ${f}█████${s}╗  ${f}██████${s}╔╝╚═╝${f}███████${s}╗    ${f}███████${s}╗${f}███████${s}║${f}██${s}║   ${f}██${s}║    ${b}#$r")
        println("${b}#  ${f}██${s}╔══${f}██${s}╗${f}██${s}║${f}██${s}║     ${f}██${s}╔══${f}██${s}║   ${f}██${s}║   ${f}██${s}╔══╝  ${f}██${s}╔══${f}██${s}╗   ╚════${f}██${s}║    ╚════${f}██${s}║${f}██${s}╔══${f}██${s}║${f}██${s}║   ${f}██${s}║    ${b}#$r")
        println("${b}#  ${f}██${s}║  ${f}██${s}║${f}██${s}║╚${f}██████${s}╗${f}██${s}║  ${f}██${s}║   ${f}██${s}║   ${f}███████${s}╗${f}██${s}║  ${f}██${s}║   ${f}███████${s}║    ${f}███████${s}║${f}██${s}║  ${f}██${s}║${f}██${s}║   ${f}██${s}║    ${b}#$r")
        println("${b}#  ${s}╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝    ${b}#$r")
        println("${b}####################################################################################################$r")
        println("${b}#      ${t}Plugin: CommandsAndGUI      ${b}#       ${t}Version: 1.0       ${b}#      ${t}Release: 16.11.2023 20:00     ${b}#$r")
        println("${b}####################################################################################################$r")
        println("${b}# ${t}Download: https://github.com/RichtigerRichter/CommandsAndGUI/releases                            ${b}#$r")
        println("${b}####################################################################################################$r")

    }


    private fun registerCommands() {
        getCommand("fly")!!.setExecutor(FlyCommand())
        getCommand("jump")!!.setExecutor(JumpCommand())
        getCommand("GUI")!!.setExecutor(GUICommand())
        getCommand("worldManager")!!.setExecutor(WorldGUICommand(this))
        getCommand("setup")!!.setExecutor(SetupCommand())
        getCommand("god")!!.setExecutor(GodCommand())
        getCommand("vanish")!!.setExecutor(VanishCommand(this))
        getCommand("workstation")!!.setExecutor(OpenCommand())
        getCommand("backpack")!!.setExecutor(BackpackCommand())
        getCommand("sit")!!.setExecutor(SitCommand())
        getCommand("heal")!!.setExecutor(HealCommand(this))
        getCommand("head")!!.setExecutor(HeadCommand())
        //getCommand("disguise")!!.setExecutor(DisguiseCommand(this))

    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(GUICloseListener(), this)
        server.pluginManager.registerEvents(MainGUIClickListener(this), this)
        server.pluginManager.registerEvents(WorkstationGUIClickListener(), this)
        server.pluginManager.registerEvents(FlySettingsGUIClickListener(), this)
        server.pluginManager.registerEvents(PlayerJoinEvent(this), this)
        server.pluginManager.registerEvents(PlayerQuitEvent(this), this)
        server.pluginManager.registerEvents(JoinQuitEvent(), this)
        server.pluginManager.registerEvents(Utils(this), this)
        server.pluginManager.registerEvents(SitListener(), this)
        server.pluginManager.registerEvents(BreakListener(), this)
        //server.pluginManager.registerEvents(DisguiseListener(this), this)

    }

    override fun onDisable() {
        // Plugin shutdown logic
        ConfigFile().save()
        MessagesFile().save()
        VanishManager(this).create()
        BackpackManager().save()

        logger.info("RichtigesPlugin has been Unloaded")
    }
}
