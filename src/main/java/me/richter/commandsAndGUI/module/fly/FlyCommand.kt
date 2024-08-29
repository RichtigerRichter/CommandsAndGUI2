package me.richter.commandsAndGUI.module.fly

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player


class FlyCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.fly) { sender.sendMessage(Message.moduleNotEnabled); return true }


        if (args.isEmpty()) {
            if (sender !is Player) return true
            // Code wenn ohne argumente
            if (sender.allowFlight) {
                sender.allowFlight = false
                sender.sendMessage(Component.text(Message.flyingDisabled))
                return true
            } else {
                sender.allowFlight = true
                sender.sendMessage(Component.text(Message.flyingEnabled))
                return true
            }
        }


        val targetPlayerName = args[0] //Name vom TARGET
        val targetPlayer = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage(
                Component.text(Message.playerDoesNotExist))
            return false
        }

        // Code mit targetPlayer
        if (args.size == 1) {
            if (targetPlayer.allowFlight) {
                targetPlayer.allowFlight = false
                sender.sendMessage(Component.text(Message.flyingDisabledFor(targetPlayerName)))
                return true
            } else {
                targetPlayer.allowFlight = true
                sender.sendMessage(Component.text(Message.flyingEnabledFor(targetPlayerName)))
                return true
            }
        }

        if (args.size == 2 && args[1] == "on") {
            targetPlayer.allowFlight = true
            sender.sendMessage(Component.text(Message.flyingEnabledFor(targetPlayerName)))
            return true
        }
        if (args.size == 2 && args[1] == "off") {
            targetPlayer.allowFlight = false
            sender.sendMessage(Component.text(Message.flyingDisabledFor(targetPlayerName)))
            return true
        }

        if (args.size > 1) {
            if (args[1] == "speed") {
                val targetFlySpeed = targetPlayer.flySpeed.toDouble()
                if (args.size == 2 && args[1] == "speed") {
                    val targetFlySpeedArg: Double = targetFlySpeed * 10
                    if (targetPlayer == sender) {
                        sender.sendMessage(Component.text(Message.getFlySpeed(targetFlySpeedArg.toString())))
                        return true
                    } else {
                        sender.sendMessage(Component.text(Message.getFlySpeedFor(targetFlySpeedArg.toString(), targetPlayerName)))
                        return true
                    }
                }

                var targetFlySpeedArg = args[2].toDouble()
                if (targetFlySpeedArg in 0.0..10.0) {
                    targetFlySpeedArg /= 10
                    targetPlayer.flySpeed = targetFlySpeedArg.toFloat()
                    targetFlySpeedArg *= 10
                    if (targetPlayer == sender) {
                        sender.sendMessage(Component.text(Message.setFlySpeed(targetFlySpeedArg.toString())))
                    } else {
                        sender.sendMessage(Component.text(Message.setFlySpeedFor(targetFlySpeedArg.toString(), targetPlayerName)))
                    }
                } else {
                    sender.sendMessage(Component.text(Message.flySpeed0to10))
                    return true
                }

            }
        }
        return false
    }


    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("fly", ignoreCase = true)) {
            if (!ConfigFile.IsModuleEnabled.fly) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val completions = mutableListOf<String>()

            if (args.size == 1) {
                // Get list of online players
                val players = Bukkit.getOnlinePlayers().map { it.name }
                completions.addAll(players)
                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2) {
                completions.add("speed")
                completions.add("on")
                completions.add("off")
                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 3 && args[1] == "speed") {
                completions.add("01")
                completions.add("02")
                completions.add("03")
                completions.add("04")
                completions.add("05")
                completions.add("06")
                completions.add("07")
                completions.add("08")
                completions.add("09")
                completions.add("10")
                return completions.filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
            }

            /*
            if (args.size == 1) {
                // Provide tab-completion for first argument
                val options = mutableListOf("speed")
                return options.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }
            if (args.size == 2) {
                // Provide tab-completion for first argument
                val options = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                return options.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

             */
        }
        return mutableListOf()
    }
}