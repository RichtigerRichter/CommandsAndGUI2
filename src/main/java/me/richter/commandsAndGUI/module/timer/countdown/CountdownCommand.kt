package me.richter.commandsAndGUI.module.timer.countdown

import me.richter.commandsAndGUI.module.utils.RegexStrings
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class CountdownCommand: CommandExecutor, TabCompleter {
	override fun onCommand(sender: CommandSender, command: Command, lable: String, args: Array<out String>): Boolean {
		if (args.isEmpty()) return false
		val name = args[0]

		when (args[1].lowercase()) {
			"resume" -> {
				if (CountdownFile().getRunning(name)) {
					sender.sendMessage(Component.text("§ccountdown is already running"))
					return true
				}
				if (!CountdownFile().getRunning(name)) {
					CountdownFile().setRunning(name, true)
					//Countdown().sendActionBar(name)
					sender.sendMessage(Component.text("§7countdown resumed"))
					return true
				}
			}
			"pause" -> {
				if (!CountdownFile().getRunning(name)) {
					sender.sendMessage(Component.text("§ccountdown is already paused"))
					return true
				}
				if (CountdownFile().getRunning(name)) {
					CountdownFile().setRunning(name, false)
					sender.sendMessage(Component.text("§7countdown was paused"))
					return true
				}
			}
			"set" -> {
				if (args.size != 3) return false
				if (!Regex(RegexStrings.TIME).matches(args[2])) {
					sender.sendMessage(Component.text("§7the time needs a pattern like this: 18:23:31"))
					return true
				}

				val time = args[2].split(":")
				val s = time[2].toInt()
				val m = time[1].toInt()
				val h = time[0].toInt()

				CountdownFile().setTime(name, s + 60*m + 60*60*h)

				//Countdown().sendActionBar(name)
				sender.sendMessage(Component.text("§7countdown was set to ${Countdown().formatTime(name)}"))
				return true
			}
			"reset" -> {
				CountdownFile().setRunning(name, false)
				CountdownFile().setTime(name, 0)

				sender.sendMessage(Component.text("§7countdown was reset"))
				return true
			}
			"start" -> {
				CountdownFile().setRunning(name, true)
				CountdownFile().setTime(name, 0)

				sender.sendMessage(Component.text("§7countdown was started"))
				return true
			}
		}


		return true
	}

	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		p2: String,
		args: Array<out String>?
	): MutableList<String> {
		val emptyList: MutableList<String> = mutableListOf()
		if (args.isNullOrEmpty()) return emptyList
		if (args.size == 1) {
			val completions: MutableList<String> = mutableListOf()
			completions.addLast("<name>")
			completions.addAll(CountdownFile().getAllTopGroupsExceptFlySoup())

			return completions
		}
		if (args.size == 2) {
			val completions: MutableList<String> = mutableListOf()
			completions.addLast("resume")
			completions.addLast("pause")
			completions.addLast("set")
			completions.addLast("reset")
			completions.addLast("start")

			return completions
		}
		if (args.size == 3 && args[1] == "set") {
			val completions: MutableList<String> = mutableListOf()
			completions.addLast("<hh:mm:ss>")

			return completions
		}

		return emptyList
	}
}