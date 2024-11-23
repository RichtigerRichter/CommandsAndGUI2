package me.richter.commandsAndGUI.modules.fly

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import me.richter.commandsAndGUI.modules.fly.creative.FlyManager
import me.richter.commandsAndGUI.modules.fly.elytra.ElytraFlyManager
import me.richter.commandsAndGUI.modules.utils.TimeFormat
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player


class FlyCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.fly) {
            sender.sendMessage(Message.moduleNotEnabled); return true
        }

        // wenn ohne argumente
        if (args.isEmpty()) {
            if (sender !is Player) return true
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

        val targetPlayerName = args[0]
        val targetPlayer = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage(Component.text(Message.playerDoesNotExist)); return false
        }

        // wenn mit targetPlayer
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


        if (args[1] == "speed") {
            if (args.size == 2) {
                val targetFlySpeedArg: Double = targetPlayer.flySpeed * 10.0
                sender.sendMessage(
                    if (targetPlayer == sender) {
                        Component.text(Message.getFlySpeed(targetFlySpeedArg.toString()))
                    } else {
                        Component.text(Message.getFlySpeedFor(targetFlySpeedArg.toString(), targetPlayerName))
                    }
                )
                return true
            }

            val targetFlySpeedArg = args[2].toDoubleOrNull()
            if (targetFlySpeedArg == null || targetFlySpeedArg !in 0.0..10.0) {
                sender.sendMessage(Component.text(Message.flySpeed0to10))
                return true
            }

            targetPlayer.flySpeed = (targetFlySpeedArg / 10).toFloat()
            sender.sendMessage(
                if (targetPlayer == sender) {
                    Component.text(Message.setFlySpeed(targetFlySpeedArg.toString()))
                } else {
                    Component.text(Message.setFlySpeedFor(targetFlySpeedArg.toString(), targetPlayerName))
                }
            )
            return true
        }

        if (args[1] == "time" && args.size == 2) {
            sender.sendMessage(
                Component.text(
                    Message.flyTimeLeft(
                        TimeFormat().timeToString(
                            FlyManager().getTime(
                                targetPlayer
                            )
                        )
                    )
                )
            )
            return true
        }

        if (args[1] == "elytra" && args.size == 2) {
            ElytraFlyManager().startElytraFly(targetPlayer)
            return true
        }

        when (args[2].lowercase()) {
            "set" -> {
                if (args.size != 4) return false
                val time = args[3].toIntOrNull()
                if (time == null) {
                    sender.sendMessage(Component.text(Message.timeNeedsToBeNumber))
                    return true
                }

                FlyManager().setTime(targetPlayer, time)
                sender.sendMessage(Component.text(Message.flyTimeSet(TimeFormat().timeToString(time))))
                return true
            }

            "add" -> {
                if (args.size != 4) return false
                val time = args[3].toIntOrNull()
                if (time == null) {
                    sender.sendMessage(Component.text(Message.timeNeedsToBeNumber))
                    return true
                }

                FlyManager().addTime(targetPlayer, time)
                sender.sendMessage(
                    Component.text(
                        Message.flyTimeAdded(
                            time.toString(), TimeFormat().timeToString(
                                FlyManager().getTime(targetPlayer) + time
                            )
                        )
                    )
                )
                return true
            }
        }


        return true
    }


    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (command.name.equals("fly", ignoreCase = true)) {
            if (!ConfigFile.IsModuleEnabled.fly) {
                sender.sendMessage(Message.moduleNotEnabled); return mutableListOf()
            }

            val completions = mutableListOf<String>()

            if (args.size == 1) {
                // Get list of online players
                val players = Bukkit.getOnlinePlayers().map { it.name }
                completions.addAll(players)
                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2) {
                completions.add("time")
                completions.add("speed")
                completions.add("on")
                completions.add("off")
                completions.add("elytra")
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

            if (args.size == 3 && args[1] == "time") {
                completions.add("add")
                completions.add("set")

                return completions.filter { it.startsWith(args[2], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 4 && args[1] == "time") {
                completions.add("[Seconds]")

                return completions.filter { it.startsWith(args[3], ignoreCase = true) }.toMutableList()
            }

        }
        return mutableListOf()
    }
}