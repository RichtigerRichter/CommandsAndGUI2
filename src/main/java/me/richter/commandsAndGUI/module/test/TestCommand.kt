package me.richter.commandsAndGUI.module.test

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class TestCommand: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        //if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return false }

        if (sender !is Player) { return true }
        if (args.size >= 2) {

            if (args[0] == "on") {

            }

            if (args[0] == "off") {

            }

        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("noClip", ignoreCase = true)) {
            //if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val player = Bukkit.getPlayer(sender.name)!!
            val completions = mutableListOf<String>()

            //
            if (args.size == 1) {
                completions.add("on")
                completions.add("off")


                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

        }
        return mutableListOf()
    }
}