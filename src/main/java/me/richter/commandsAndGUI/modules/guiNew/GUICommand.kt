package me.richter.commandsAndGUI.modules.guiNew

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.modules.guiNew.mainGUI.MainGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GUICommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>?): Boolean {
        if (!ConfigFile.IsModuleEnabled.worldManager) {
            sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return true
        }

        if (sender !is Player) return false
        MainGUI().open(sender)

        return true
    }
}