package me.richter.commandsAndGUI.items

import me.richter.commandsAndGUI.module.fly.soup.FlySoup
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class GetItemCommand: CommandExecutor, TabCompleter {
	override fun onCommand(sender: CommandSender, command: Command, p2: String, args: Array<out String>?): Boolean {
		if (sender !is Player) return false
		if (args.isNullOrEmpty()) return false
		val count = if (args.size == 2) args[1].toInt() else 1

		if (args[0] == "FlySoup") sender.inventory.addItem(FlySoup().flySoupItem(count))

		return true
	}

	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		p2: String,
		p3: Array<out String>?
	): MutableList<String> {
		val completions: MutableList<String> = mutableListOf()
		completions.addLast("FlySoup")
		return completions
	}
}