package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WorldGUICommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>?): Boolean {
        if (!ConfigFile.IsModuleEnabled.gui) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }

        if(sender !is Player) return false
        WorldGUI().open(sender)

        return true
    }
}