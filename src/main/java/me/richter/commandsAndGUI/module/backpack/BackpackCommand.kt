package me.richter.commandsAndGUI.module.backpack

import me.richter.commandsAndGUI.files.ConfigFile.IsModuleEnabled
import me.richter.commandsAndGUI.files.MessagesFile.Message
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class BackpackCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        if (!IsModuleEnabled.backpack) {
            sender.sendMessage(Message.moduleNotEnabled); return true
        }

        if (sender !is Player) {
            return false
        }
        if (args.size >= 2) {

            if (args[0] == "open") {
                if (!BackpackManager().isValidBackpack(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDoesntExists))
                    return false
                }
                if (!BackpackManager().isAllowedPlayer(args[1], sender)) {
                    sender.sendMessage(Component.text(Message.youAreNotAllowedToOpenThisBackpack))
                    return false
                }
                BackpackManager().open(args[1], sender)
            }

            if (args[0] == "create") {
                if (args.size <= 2) {
                    BackpackManager().create(args[1].filter { it.isLetterOrDigit() }, sender)
                }
            }

            if (args[0] == "delete") {
                if (!BackpackManager().isValidBackpack(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDoesntExists))
                    return false
                }

                if (!BackpackManager().isOwner(args[1], sender)) {
                    sender.sendMessage(Component.text(Message.youAreNotTheOwner))
                    return false
                }

                if (BackpackManager().delete(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDeleted))
                } else {
                    sender.sendMessage("did not delete (bidde bei richter melden, weil das eigentlich nicht passieren sollte -_- )")
                }

            }

            if (args[0] == "playerAdd") {
                if (!BackpackManager().isValidBackpack(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDoesntExists))
                    return false
                }
                if (!BackpackManager().isOwner(args[1], sender)) {
                    sender.sendMessage(Component.text(Message.youAreNotTheOwner))
                    return false
                }
                val allowedPlayers: MutableList<Player> = mutableListOf()

                val maxSize = args.size
                var size = 2
                while (size < maxSize) {
                    val player = Bukkit.getPlayer(args[size]) ?: return true
                    if (BackpackManager().isAllowedPlayer(args[1], player)) {
                        return true
                    }

                    allowedPlayers.add(player)

                    size += 1
                }
                BackpackManager().addPlayer(args[1], allowedPlayers)
                sender.sendMessage(Component.text("The following players where added to this Backpack:\n$allowedPlayers"))
            }

            if (args[0] == "playerRemove") {
                if (!BackpackManager().isValidBackpack(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDoesntExists))
                    return false
                }
                if (!BackpackManager().isOwner(args[1], sender)) {
                    sender.sendMessage(Component.text(Message.youAreNotTheOwner))
                    return false
                }

                val removePlayers: MutableList<Player> = mutableListOf()

                val maxSize = args.size
                var size = 2
                while (size < maxSize) {
                    removePlayers.add(Bukkit.getPlayer(args[size])!!)
                    size += 1
                }
                BackpackManager().removePlayer(args[1], removePlayers)
                sender.sendMessage(Component.text("removed $removePlayers"))
            }

            if (args[0] == "playersList") {
                if (!BackpackManager().isValidBackpack(args[1])) {
                    sender.sendMessage(Component.text(Message.backpackDoesntExists))
                    return false
                }
                if (!BackpackManager().isOwner(args[1], sender)) {
                    sender.sendMessage(Component.text(Message.youAreNotAllowedToOpenThisBackpack))
                    return false
                }

                val allowedPlayers: MutableList<String> = BackpackManager().allPlayersAllowedToOpenBackpack(args[1])

                sender.sendMessage(Component.text("The following players may access this Backpack:\n$allowedPlayers"))
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
        if (command.name.equals("backpack", ignoreCase = true)) {
            if (!IsModuleEnabled.backpack) {
                sender.sendMessage(Message.moduleNotEnabled); return mutableListOf()
            }

            val player = Bukkit.getPlayer(sender.name)!!
            val completions = mutableListOf<String>()
            if (args.size == 1) {
                completions.add("open")
                completions.add("create")
                completions.add("delete")
                completions.add("playerAdd")
                completions.add("playerRemove")
                completions.add("playersList")


                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "open") {
                completions.addAll(BackpackManager().allBackpacksAllowedToOpen(player))

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "create") {
                completions.add("<name>")


                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "delete") {
                completions.addAll(BackpackManager().allBackpacksOwned(player))

                //alle backpacks anzeigen

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "playerAdd") {
                completions.addAll(BackpackManager().allBackpacksOwned(player))
                //alle player anzeigen

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size > 2 && args[0] == "playerAdd") {

                val onlinePlayers = Bukkit.getOnlinePlayers()
                val playerNames = onlinePlayers.map { it.name }
                completions.addAll(playerNames)

                //alle player anzeigen

                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "playerRemove") {
                completions.addAll(BackpackManager().allBackpacksOwned(player))
                //alle player anzeigen

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

            if (args.size > 2 && args[0] == "playerRemove") {

                val onlinePlayers = Bukkit.getOnlinePlayers()
                val playerNames = onlinePlayers.map { it.name }
                completions.addAll(playerNames)

                //alle player anzeigen

                return completions.filter { it.startsWith(args[args.size - 1], ignoreCase = true) }.toMutableList()
            }

            if (args.size == 2 && args[0] == "playersList") {
                completions.addAll(BackpackManager().allBackpacksOwned(player))
                //alle player anzeigen

                return completions.filter { it.startsWith(args[1], ignoreCase = true) }.toMutableList()
            }

        }
        return mutableListOf()
    }
}