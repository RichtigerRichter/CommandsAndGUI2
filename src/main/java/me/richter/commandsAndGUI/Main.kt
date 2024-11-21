package me.richter.commandsAndGUI

import fr.skytasul.glowingentities.GlowingEntities
import me.richter.commandsAndGUI.files.*
import me.richter.commandsAndGUI.items.GetItemCommand
import me.richter.commandsAndGUI.module.backpack.BackpackCommand
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.bdLul.BdLul
import me.richter.commandsAndGUI.module.bdLul.BdLulEvent
import me.richter.commandsAndGUI.module.customCrafting.CustomCrafting
import me.richter.commandsAndGUI.module.customCrafting.invisibleItemFrames.EntityPlaceListener
import me.richter.commandsAndGUI.module.fly.FlyCommand
import me.richter.commandsAndGUI.module.fly.creative.FlyManager
import me.richter.commandsAndGUI.module.fly.elytra.ElytraFlyListener
import me.richter.commandsAndGUI.module.fly.soup.SoupEatListener
import me.richter.commandsAndGUI.module.godmode.GodCommand
import me.richter.commandsAndGUI.module.gui.CommandsAndGUICommand
import me.richter.commandsAndGUI.module.gui.GUIData
import me.richter.commandsAndGUI.module.gui.InventoryClickEventListener
import me.richter.commandsAndGUI.module.gui.InventoryDragEventListener
import me.richter.commandsAndGUI.module.guiNew.GUICloseListener
import me.richter.commandsAndGUI.module.guiNew.GUICommand
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUIClickListener
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUIClickListener
import me.richter.commandsAndGUI.module.heal.HealCommand
import me.richter.commandsAndGUI.module.invSee.InvSeeClickListener
import me.richter.commandsAndGUI.module.invSee.InvSeeCommand
import me.richter.commandsAndGUI.module.invSee.InvSeeGUI
import me.richter.commandsAndGUI.module.jump.JumpCommand
import me.richter.commandsAndGUI.module.placeholders.PAPI
import me.richter.commandsAndGUI.module.placeholders.Placeholder
import me.richter.commandsAndGUI.module.playerTracker.LodestoneListener
import me.richter.commandsAndGUI.module.playerTracker.TrackCommand
import me.richter.commandsAndGUI.module.playerTracker.TrackManager
import me.richter.commandsAndGUI.module.scoreboard.JoinLeaveListener
import me.richter.commandsAndGUI.module.scoreboard.newTest.TestScoreboardCommand
import me.richter.commandsAndGUI.module.sit.SitCommand
import me.richter.commandsAndGUI.module.sit.SitListener
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import me.richter.commandsAndGUI.module.timer.countdown.CountdownCommand
import me.richter.commandsAndGUI.module.timer.timer.Timer
import me.richter.commandsAndGUI.module.timer.timer.TimerCommand
import me.richter.commandsAndGUI.module.utils.HeadCommand
import me.richter.commandsAndGUI.module.utils.PluginLogo
import me.richter.commandsAndGUI.module.utils.SetupCommand
import me.richter.commandsAndGUI.module.vanish.PlayerJoinEvent
import me.richter.commandsAndGUI.module.vanish.PlayerQuitEvent
import me.richter.commandsAndGUI.module.vanish.VanishCommand
import me.richter.commandsAndGUI.module.vanish.VanishManager
import me.richter.commandsAndGUI.module.workstations.OpenCommand
import me.richter.commandsAndGUI.module.worldGuard2.BreakListener
import me.richter.commandsAndGUI.module.worldManager.WorldGUICommand
import org.bukkit.Bukkit
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

        val animationsCount: MutableMap<String, Int> = mutableMapOf()

        lateinit var glowingEntitiesAPI: GlowingEntities

        private var guiDataMap: HashMap<Player, GUIData> = HashMap<Player, GUIData>()
    }


    override fun onEnable() {
        instance = this
        glowingEntitiesAPI = GlowingEntities(instance)

        registerCommands()
        registerListeners()

        CustomCrafting().registerCustomRecipes()

        //config stuff
        saveDefaultConfig()
        ConfigFile().loadConfig()
        MessagesFile().loadConfig()
        ScoreboardFile().loadConfig()
        AnimationsFile().loadConfig()
        PlayerDataFile().loadConfig()

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

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //

            PAPI().register() //
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
        getCommand("commandsandgui")!!.setExecutor(CommandsAndGUICommand())


    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(GUICloseListener(), this)
        server.pluginManager.registerEvents(MainGUIClickListener(this), this)
        server.pluginManager.registerEvents(WorkstationGUIClickListener(), this)
        server.pluginManager.registerEvents(FlySettingsGUIClickListener(), this)
        server.pluginManager.registerEvents(PlayerJoinEvent(this), this)
        server.pluginManager.registerEvents(PlayerQuitEvent(this), this)
        server.pluginManager.registerEvents(BdLulEvent(), this)
        server.pluginManager.registerEvents(BdLul(), this)
        server.pluginManager.registerEvents(SitListener(), this)
        server.pluginManager.registerEvents(BreakListener(), this)
        server.pluginManager.registerEvents(InvSeeClickListener(), this)
        server.pluginManager.registerEvents(SoupEatListener(), this)
        server.pluginManager.registerEvents(JoinLeaveListener(), this)
        server.pluginManager.registerEvents(LodestoneListener(), this)
        server.pluginManager.registerEvents(ElytraFlyListener(), this)
        server.pluginManager.registerEvents(EntityPlaceListener(), this)
        server.pluginManager.registerEvents(InventoryClickEventListener(), this)
        server.pluginManager.registerEvents(InventoryDragEventListener(), this)
    }

    private fun runEverySec() {
        taskEverySec = object : BukkitRunnable() {
            override fun run() {
                FlyManager().flytimeSec()

                Countdown().runningEverySec()
                Timer().runningEverySec()

            }
        }
        taskEverySec.runTaskTimer(this, 0L, 20L)
    }

    private fun stopEverySecTask() {
        taskEverySec.cancel()
    }

    private fun runEveryTick() {
        taskEveryTick = object : BukkitRunnable() {
            override fun run() {
                FlyManager().flytimeTick()

                InvSeeGUI().updateAllInvSeeGUIs()

                TrackManager().updateBossbarTick()
                TrackManager().updateCompassTick()

                Placeholder().animationUpdateTick()

            }
        }
        taskEveryTick.runTaskTimer(this, 0L, 1L)
    }

    private fun stopEveryTickTask() {
        taskEveryTick.cancel()
    }

    fun getGUIData(player: Player): GUIData {
        val guiData: GUIData
        if (!(guiDataMap.containsKey(player))) {
            guiData = GUIData(player)
            guiDataMap[player] = guiData
            return guiData

        } else {
            return guiDataMap[player]!!
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic

        VanishManager(this).create()
        BackpackManager().save()

        stopEveryTickTask()
        stopEverySecTask()

        logger.info("RichtigesPlugin has been Unloaded")
    }
}
