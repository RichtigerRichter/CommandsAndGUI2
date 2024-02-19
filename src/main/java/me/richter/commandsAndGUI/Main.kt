package me.richter.commandsAndGUI

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.module.backpack.BackpackCommand
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.fly.FlyCommand
import me.richter.commandsAndGUI.module.godmode.GodCommand
import me.richter.commandsAndGUI.module.guiNew.GUICloseListener
import me.richter.commandsAndGUI.module.guiNew.GUICommand
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUIClickListener
import me.richter.commandsAndGUI.module.jump.JumpCommand
import me.richter.commandsAndGUI.module.sit.SitCommand
import me.richter.commandsAndGUI.module.sit.SitListener
import me.richter.commandsAndGUI.module.utils.JoinQuitEvent
import me.richter.commandsAndGUI.module.utils.SetupCommand
import me.richter.commandsAndGUI.module.utils.Utils
import me.richter.commandsAndGUI.module.vanish.PlayerJoinEvent
import me.richter.commandsAndGUI.module.vanish.PlayerQuitEvent
import me.richter.commandsAndGUI.module.vanish.VanishCommand
import me.richter.commandsAndGUI.module.vanish.VanishManager
import me.richter.commandsAndGUI.module.workstations.OpenCommand
import me.richter.commandsAndGUI.module.worldManager.WorldGUICommand
import org.bukkit.Material
import org.bukkit.Registry
import org.bukkit.Registry.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Main : JavaPlugin() {

    companion object {
        val plugin = this

        val guiMainMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiFlySettingsMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiWorkstationMap: MutableMap<UUID, Inventory> = mutableMapOf()

        val sitMap: MutableMap<Player, Entity> = mutableMapOf()


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

        logger.info("CommandsAndGUI has been Loaded")

        val reset = "\u001B[0m"

        val shadow = "\u001B[90m"
        val frame = "\u001B[34m"
        val font = "\u001B[31m"
        val test = "\u001B[97m"

        println("${frame}####################################################################################################$reset")
        println("${frame}#                                                                                                  #$reset")
        println("${frame}#  ${font}██████${shadow}╗ ${font}██${shadow}╗ ${font}██████${shadow}╗${font}██${shadow}╗  ${font}██${shadow}╗${font}████████${shadow}╗${font}███████${shadow}╗${font}██████${shadow}╗ ${font}██${shadow}╗${font}███████${shadow}╗    ${font}███████${shadow}╗${font}██${shadow}╗  ${font}██${shadow}╗${font}██${shadow}╗${font}████████${shadow}╗ ${frame}#$reset")
        println("${frame}#  ${font}██${shadow}╔══${font}██${shadow}╗${font}██${shadow}║${font}██${shadow}╔════╝${font}██${shadow}║  ${font}██${shadow}║╚══${font}██${shadow}╔══╝${font}██${shadow}╔════╝${font}██${shadow}╔══${font}██${shadow}╗${font}██${shadow}║${font}██${shadow}╔════╝    ${font}██${shadow}╔════╝${font}██${shadow}║  ${font}██${shadow}║${font}██${shadow}║╚══${font}██${shadow}╔══╝ ${frame}#$reset")
        println("${frame}#  ${font}██████${shadow}╔╝${font}██${shadow}║${font}██${shadow}║     ${font}███████${shadow}║   ${font}██${shadow}║   ${font}█████${shadow}╗  ${font}██████${shadow}╔╝╚═╝${font}███████${shadow}╗    ${font}███████${shadow}╗${font}███████${shadow}║${font}██${shadow}║   ${font}██${shadow}║    ${frame}#$reset")
        println("${frame}#  ${font}██${shadow}╔══${font}██${shadow}╗${font}██${shadow}║${font}██${shadow}║     ${font}██${shadow}╔══${font}██${shadow}║   ${font}██${shadow}║   ${font}██${shadow}╔══╝  ${font}██${shadow}╔══${font}██${shadow}╗   ╚════${font}██${shadow}║    ╚════${font}██${shadow}║${font}██${shadow}╔══${font}██${shadow}║${font}██${shadow}║   ${font}██${shadow}║    ${frame}#$reset")
        println("${frame}#  ${font}██${shadow}║  ${font}██${shadow}║${font}██${shadow}║╚${font}██████${shadow}╗${font}██${shadow}║  ${font}██${shadow}║   ${font}██${shadow}║   ${font}███████${shadow}╗${font}██${shadow}║  ${font}██${shadow}║   ${font}███████${shadow}║    ${font}███████${shadow}║${font}██${shadow}║  ${font}██${shadow}║${font}██${shadow}║   ${font}██${shadow}║    ${frame}#$reset")
        println("${frame}#  ${shadow}╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝    ${frame}#$reset")
        println("${frame}####################################################################################################$reset")
        println("${frame}#      ${test}Plugin: CommandsAndGUI      ${frame}#       ${test}Version: 1.0       ${frame}#      ${test}Release: 16.11.2023 20:00     ${frame}#$reset")
        println("${frame}####################################################################################################$reset")
        println("${frame}# ${test}Download: https://github.com/RichtigerRichter/CommandsAndGUI/releases                            ${frame}#$reset")
        println("${frame}####################################################################################################$reset")

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
