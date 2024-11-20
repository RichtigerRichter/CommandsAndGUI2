package me.richter.commandsAndGUI.module.godmode

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player


class GodCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.godmode) { sender.sendMessage(Message.moduleNotEnabled); return true }

        if (args.isEmpty()) {
            if (sender !is Player) return true
            // Code wenn ohne argumente
            if (sender.isInvulnerable) {
                sender.isInvulnerable = false
                sender.sendMessage(Component.text(Message.godDisabled))
            } else {
                sender.isInvulnerable = true
                sender.sendMessage(Component.text(Message.godEnabled))
            }
            return true
        }

        val targetPlayer = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage(
                Component.text(Message.playerDoesNotExist))
            return true
        }
        // Code mit targetPlayer
        val targetPlayerC = args[0] //Name vom TARGET

        if (targetPlayer.isInvulnerable) {
            targetPlayer.isInvulnerable = false
            sender.sendMessage(Component.text("${Message.godDisabled} for §6$targetPlayerC"))
        } else {
            targetPlayer.isInvulnerable = true
            sender.sendMessage(Component.text("${Message.godEnabled} for §6$targetPlayerC"))
        }
        return true
    }


    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("god", ignoreCase = true)) {
            if (!ConfigFile.IsModuleEnabled.godmode) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val completions = mutableListOf<String>()
            if (args.isEmpty()) {return completions}


            if (args.size == 1) {
                // Get list of online players
                val players = Bukkit.getOnlinePlayers().map { it.name }
                completions.addAll(players)
                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

        }
        return mutableListOf()
    }
}