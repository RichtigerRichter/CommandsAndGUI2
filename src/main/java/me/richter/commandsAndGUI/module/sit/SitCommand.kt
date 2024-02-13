package me.richter.commandsAndGUI.module.sit

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SitCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>?): Boolean {
        val player:Player = Bukkit.getPlayer(sender.name)!!
        sender.sendMessage(Component.text("command"))
        SitManager().sit(player)

        return true
    }
}