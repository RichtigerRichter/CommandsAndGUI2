package me.richter.commandsAndGUI.module.utils

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.Messages2File
import me.richter.commandsAndGUI.files.Messages2File.Message.PREFIX
import net.kyori.adventure.text.Component
import org.bukkit.GameRule
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetupCommand : CommandExecutor {


    override fun onCommand(sender: CommandSender, command: Command, label: String, arg: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.setup) { sender.sendMessage(Messages2File.Message.moduleNotEnabled); return true }

        if(sender !is Player) return true
        val world = sender.world

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
        val doDaylightCycle = world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)
        sender.sendMessage(Component.text("$PREFIX DO_DAYLIGHT_CYCLE set to $doDaylightCycle"))

        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
        val announceAdvancements = world.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS)
        sender.sendMessage(Component.text("$PREFIX ANNOUNCE_ADVANCEMENTS set to $announceAdvancements"))

        world.setGameRule(GameRule.DO_FIRE_TICK, false)
        val doFireTick = world.getGameRuleValue(GameRule.DO_FIRE_TICK)
        sender.sendMessage(Component.text("$PREFIX DO_FIRE_TICK set to $doFireTick"))

        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
        val doImmediateRespawn = world.getGameRuleValue(GameRule.DO_IMMEDIATE_RESPAWN)
        sender.sendMessage(Component.text("$PREFIX DO_IMMEDIATE_RESPAWN set to $doImmediateRespawn"))

        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false)
        val doPatrolSpawning = world.getGameRuleValue(GameRule.DO_PATROL_SPAWNING)
        sender.sendMessage(Component.text("$PREFIX DO_PATROL_SPAWNING set to $doPatrolSpawning"))

        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false)
        val doTraderSpawning = world.getGameRuleValue(GameRule.DO_TRADER_SPAWNING)
        sender.sendMessage(Component.text("$PREFIX DO_TRADER_SPAWNING set to $doTraderSpawning"))

        if (MinecraftVersion().newerAndIncluding("1.19.4")){
        world.setGameRule(GameRule.DO_VINES_SPREAD, false)
        val doVinesSpread = world.getGameRuleValue(GameRule.DO_VINES_SPREAD)
        sender.sendMessage(Component.text("$PREFIX DO_VINES_SPREAD set to $doVinesSpread"))
        }

        world.setGameRule(GameRule.KEEP_INVENTORY, true)
        val keepInventory = world.getGameRuleValue(GameRule.KEEP_INVENTORY)
        sender.sendMessage(Component.text("$PREFIX KEEP_INVENTORY set to $keepInventory"))

        world.setGameRule(GameRule.MOB_GRIEFING, false)
        val mobGriefing = world.getGameRuleValue(GameRule.MOB_GRIEFING)
        sender.sendMessage(Component.text("$PREFIX MOB_GRIEFING set to $mobGriefing"))

        world.setGameRule(GameRule.SPAWN_RADIUS, 0)
        val spawnRadius = world.getGameRuleValue(GameRule.SPAWN_RADIUS)
        sender.sendMessage(Component.text("$PREFIX SPAWN_RADIUS set to $spawnRadius"))

        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false)
        val doWeatherCycle = world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE)
        sender.sendMessage(Component.text("$PREFIX DO_WEATHER_CYCLE set to $doWeatherCycle"))

        world.time = 6000
        val time = world.time
        sender.sendMessage(Component.text("$PREFIX Time set to $time"))

        world.setStorm(false)
        sender.sendMessage(Component.text("$PREFIX Weather Cleared"))

        return true
    }
}
