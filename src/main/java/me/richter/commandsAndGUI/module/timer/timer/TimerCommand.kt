package me.richter.commandsAndGUI.module.timer.timer

import me.richter.commandsAndGUI.module.utils.RegexStrings
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class TimerCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, lable: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false

        when (args[0].lowercase()) {
            "resume" -> {
                if (TimerFile().getRunning()) {
                    sender.sendMessage(Component.text("§ctimer is already running"))
                    return true
                }
                if (!TimerFile().getRunning()) {
                    TimerFile().setRunning(true)
                    Timer().sendActionBar()
                    sender.sendMessage(Component.text("§7timer resumed"))
                    return true
                }
            }

            "pause" -> {
                if (!TimerFile().getRunning()) {
                    sender.sendMessage(Component.text("§ctimer is already paused"))
                    return true
                }
                if (TimerFile().getRunning()) {
                    TimerFile().setRunning(false)
                    sender.sendMessage(Component.text("§7timer was paused"))
                    return true
                }
            }

            "set" -> {
                if (args.size != 2) return false
                if (!Regex(RegexStrings.TIME).matches(args[1])) {
                    sender.sendMessage(Component.text("§7the time needs a pattern like this: 18:23:31"))
                    return true
                }

                val time = args[1].split(":")
                val s = time[2].toInt()
                val m = time[1].toInt()
                val h = time[0].toInt()

                TimerFile().setTime(s + 60 * m + 60 * 60 * h)

                val seconds = Timer().timeList()["s"] ?: return false
                val minutes = Timer().timeList()["m"] ?: return false
                val hours = Timer().timeList()["h"] ?: return false

                val formatedSec = if (seconds < 10) "0$seconds" else "$seconds"
                val formatedMin = if (minutes < 10) "0$minutes" else "$minutes"
                val formatedHor = if (hours < 10) "0$hours" else "$hours"

                Timer().sendActionBar()
                sender.sendMessage(Component.text("§7timer was set to $formatedHor:$formatedMin:$formatedSec"))
                return true
            }

            "reset" -> {
                TimerFile().setRunning(false)
                TimerFile().setTime(0)

                sender.sendMessage(Component.text("§7timer was reset"))
                return true
            }

            "start" -> {
                TimerFile().setRunning(true)

                sender.sendMessage(Component.text("§7timer was started"))
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
            completions.addLast("resume")
            completions.addLast("pause")
            completions.addLast("set")
            completions.addLast("reset")
            completions.addLast("start")

            return completions
        }
        if (args.size == 2 && args[0] == "set") {
            val completions: MutableList<String> = mutableListOf()
            completions.addLast("<hh:mm:ss>")

            return completions
        }

        return emptyList
    }
}