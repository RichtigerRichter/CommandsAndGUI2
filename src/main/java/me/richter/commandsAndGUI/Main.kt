package me.richter.commandsAndGUI

import me.richter.commandsAndGUI.files.Config2File
import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.Messages2File
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.items.GetItemCommand
import me.richter.commandsAndGUI.module.backpack.BackpackCommand
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.bdLul.BdLulEvent
import me.richter.commandsAndGUI.module.bdLul.BdLul
import me.richter.commandsAndGUI.module.fly.FlyCommand
import me.richter.commandsAndGUI.module.fly.FlyManager
import me.richter.commandsAndGUI.module.fly.soup.SoupEatListener
import me.richter.commandsAndGUI.module.godmode.GodCommand
import me.richter.commandsAndGUI.module.guiNew.GUICloseListener
import me.richter.commandsAndGUI.module.guiNew.GUICommand
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUIClickListener
import me.richter.commandsAndGUI.module.heal.HealCommand
import me.richter.commandsAndGUI.module.invView.InvSeeClickListener
import me.richter.commandsAndGUI.module.invView.InvSeeCommand
import me.richter.commandsAndGUI.module.invView.InvSeeGUI
import me.richter.commandsAndGUI.module.jump.JumpCommand
import me.richter.commandsAndGUI.module.playerTracker.*
import me.richter.commandsAndGUI.module.scoreboard.JoinLeaveListener
import me.richter.commandsAndGUI.module.scoreboard.TestScoreboardCommand
import me.richter.commandsAndGUI.module.sit.SitCommand
import me.richter.commandsAndGUI.module.sit.SitListener
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import me.richter.commandsAndGUI.module.timer.countdown.CountdownCommand
import me.richter.commandsAndGUI.module.timer.timer.Timer
import me.richter.commandsAndGUI.module.timer.timer.TimerCommand
import me.richter.commandsAndGUI.module.utils.*
import me.richter.commandsAndGUI.module.vanish.PlayerJoinEvent
import me.richter.commandsAndGUI.module.vanish.PlayerQuitEvent
import me.richter.commandsAndGUI.module.vanish.VanishCommand
import me.richter.commandsAndGUI.module.vanish.VanishManager
import me.richter.commandsAndGUI.module.workstations.OpenCommand
import me.richter.commandsAndGUI.module.worldGuard2.BreakListener
import me.richter.commandsAndGUI.module.worldManager.WorldGUICommand
import net.kyori.adventure.bossbar.BossBar
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.WorldCreator
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*


class Main : JavaPlugin() {

    private lateinit var taskEveryTick: BukkitRunnable
    private lateinit var taskEverySec: BukkitRunnable


    companion object {
        //get Javaplugin with Main.instance
        lateinit var instance: Main
            private set

        val guiMainMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiFlySettingsMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiWorkstationMap: MutableMap<UUID, Inventory> = mutableMapOf()
        val guiInvSeeMap: MutableMap<UUID, Inventory> = mutableMapOf()

        val trackMap: MutableMap<UUID, MutableMap<String, MutableList<Any>>> = mutableMapOf()

        val sitMap: MutableMap<Player, Entity> = mutableMapOf()

        val initWorldCreator: MutableMap<String, WorldCreator> = mutableMapOf()
        val BackpackMap: MutableMap<String, Inventory> = mutableMapOf()

        val vanishedPlayersMap: MutableMap<UUID, Boolean> = mutableMapOf()
    }



    override fun onEnable() {
        instance = this

        registerCommands()
        registerListeners()

        //config stuff
        saveDefaultConfig()
        Config2File().loadConfig()
        Messages2File().loadConfig()

        ConfigFile().save()
        MessagesFile().save()
        VanishManager(this).create()
        BackpackManager().load()

        //start loops
        runEveryTick()
        runEverySec()

        PluginLogo().printPluginInfo()
        logger.info("CommandsAndGUI has been Loaded")

        if (!server.minecraftVersion.startsWith("1")) {
            logger.warning("THIS PLUGIN IS ONLY MADE FOR MINECRAFT VERSIONS 1.x.x IF THE SERVER IS RUNNING ON A NEWER VERSION PLEASE CONTACT THE PLUGIN DEV SINCE THE VERSION CHECKS WON'T WORK")
        }

    }


    private fun registerCommands() {
        getCommand("fly")!!.setExecutor(FlyCommand())
        getCommand("jump")!!.setExecutor(JumpCommand())
        getCommand("GUI")!!.setExecutor(GUICommand())
        getCommand("invSee")!!.setExecutor(InvSeeCommand())
        getCommand("worldManager")!!.setExecutor(WorldGUICommand(this))
        getCommand("setup")!!.setExecutor(SetupCommand())
        getCommand("god")!!.setExecutor(GodCommand())
        getCommand("vanish")!!.setExecutor(VanishCommand(this))
        getCommand("workstation")!!.setExecutor(OpenCommand())
        getCommand("backpack")!!.setExecutor(BackpackCommand())
        getCommand("sit")!!.setExecutor(SitCommand())
        getCommand("heal")!!.setExecutor(HealCommand(this))
        getCommand("getHead")!!.setExecutor(HeadCommand())
        getCommand("getItem")!!.setExecutor(GetItemCommand())
        getCommand("timer")!!.setExecutor(TimerCommand())
        getCommand("countdown")!!.setExecutor(CountdownCommand())
        getCommand("testScoreboard")!!.setExecutor(TestScoreboardCommand())
        getCommand("track")!!.setExecutor(TrackCommand())




    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(GUICloseListener(), this)
        server.pluginManager.registerEvents(MainGUIClickListener(this), this)
        server.pluginManager.registerEvents(WorkstationGUIClickListener(), this)
        server.pluginManager.registerEvents(FlySettingsGUIClickListener(), this)
        server.pluginManager.registerEvents(PlayerJoinEvent(this), this)
        server.pluginManager.registerEvents(PlayerQuitEvent(this), this)
        server.pluginManager.registerEvents(BdLulEvent(), this)
        server.pluginManager.registerEvents(BdLul(this), this)
        server.pluginManager.registerEvents(SitListener(), this)
        server.pluginManager.registerEvents(BreakListener(), this)
        server.pluginManager.registerEvents(InvSeeClickListener(), this)
        server.pluginManager.registerEvents(SoupEatListener(), this)
        server.pluginManager.registerEvents(JoinLeaveListener(), this)



    }

    private fun runEverySec() {
        taskEverySec = object : BukkitRunnable() {
            override fun run() {
                FlyManager().countdownTick()
                
                Countdown().runningEverySec()
                Timer().runningEverySec()

            }
        }
        taskEverySec.runTaskTimer(this, 0L, 20L)
    }

    private fun stopEverySecTask() { taskEverySec.cancel() }

    private fun runEveryTick() {
        taskEveryTick = object : BukkitRunnable() {
            override fun run() {
                //TODO noch nicht so geil
                InvSeeGUI().updateAllInvSeeGUIs()

                TrackManager().updateBossbarTick()

            }
        }
        taskEveryTick.runTaskTimer(this, 0L, 1L)
    }

    private fun stopEveryTickTask() { taskEveryTick.cancel() }



    override fun onDisable() {
        // Plugin shutdown logic
        ConfigFile().save()
        MessagesFile().save()
        VanishManager(this).create()
        BackpackManager().save()

        stopEveryTickTask()
        stopEverySecTask()

        logger.info("RichtigesPlugin has been Unloaded")
    }
}
