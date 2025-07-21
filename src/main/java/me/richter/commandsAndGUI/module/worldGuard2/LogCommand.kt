package me.richter.commandsAndGUI.module.worldGuard2

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.module.utils.Text
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class LogCommand: CommandExecutor, TabCompleter {
	override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
		//if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return true }

		if (sender !is Player) { return true }
		if (args.size == 1) {

			if (args[0] == "on") {
				Main.playersInAdminMode.add(sender)

				sender.sendMessage(Text.miniPapi(sender, "admin mode was turned on"))
			}

			if (args[0] == "off") {
				Main.playersInAdminMode.remove(sender)

				sender.sendMessage(Text.miniPapi(sender, "admin mode was turned off"))
			}


		}
		return true
	}

	override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
		if (command.name.equals("adminMode", ignoreCase = true)) {
			//if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

			val player = Bukkit.getPlayer(sender.name)!!
			val completions = mutableListOf<String>()

			//
			if (args.size == 1) {
				completions.add("on")
				completions.add("off")


				return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
			}

			/*
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