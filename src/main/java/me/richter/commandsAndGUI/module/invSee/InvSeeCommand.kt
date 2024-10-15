package me.richter.commandsAndGUI.module.invSee

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.Messages2File
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InvSeeCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>?): Boolean {
        if (!ConfigFile.IsModuleEnabled.worldManager) { sender.sendMessage(Messages2File.Message.moduleNotEnabled); return true }
        if (sender !is Player) return false
        if (args.isNullOrEmpty()) return false
        if (args.size != 1) return false

        val target: Player = Bukkit.getPlayer(args[0]) ?: return false

        if (target == sender) {
            sender.sendMessage(Messages2File.Message.cannotInvSeeYourSelf)
            return true
        }

        InvSeeGUI().open(sender, target)

        return true
    }
}