package me.richter.commandsAndGUI.modules.gui

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile.IsModuleEnabled
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.modules.gui.guis.MainGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class CommandsAndGUICommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        if (!IsModuleEnabled.gui) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return true }

        if (sender !is Player) {
            return true
        }

        val guiData: GUIData = Main.instance.getGUIData(sender)
        guiData.lastGUIListAdd(MainGUI(guiData))
        guiData.lastGUIListGetLast().update()

        /*
        if (args.size >= 2) {

            if (args[0] == "template") {

            }

            if (args[0] == "1") {

            }

            if (args[0] == "2") {

            }

        }
        */
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (command.name.equals("commandsandgui", ignoreCase = true)) {
            if (!IsModuleEnabled.gui) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return mutableListOf() }

            /*
            val player = Bukkit.getPlayer(sender.name)!!
            val completions = mutableListOf<String>()

            if (args.size == 1) {
                completions.add("template")
                completions.add("1")
                completions.add("2")


                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "1") {
                completions.add("1.1")
                completions.add("1.2")


                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "2") {
                completions.add("2.1")
                completions.add("2.2")


                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }
            */
        }
        return mutableListOf()
    }
}