package me.richter.commandsAndGUI.module.heal

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.ConfigFile.IsModuleEnabled
import me.richter.commandsAndGUI.files.MessagesFile.Message
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class HealCommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
        if (IsModuleEnabled.heal) { sender.sendMessage(Message.moduleNotEnabled); return true }

        if (sender is ConsoleCommandSender) {
            println("\u001Bc")
            println("YOU IMBECILE, WHY THE HELL WOULD YOU THINK YOU COULD HEAL THE CONSOLE")
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                println("\u001Bc")
                println("actually did you know healing is harmful for consoles")
            }, 100)
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                println("\u001Bc")
                println("hmmmm, funny just like zombies in minecraft")
            }, 200)
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                println("\u001Bc")
                println("Ohhhh nooo its starting to have an effect on the console ಠ_ಠ")
            }, 300)
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                println("\u001Bc")
                println("i think the console is dying, schade kann man nix machn ¯\\_(ツ)_/¯ bye \uD83D\uDC4B")
            }, 400)
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                for (player in plugin.server.onlinePlayers) {
                    player.kick(Component.text("some buffoon tried to use the heal command in the console.\nturns out it kills the server :|"))
                }
                plugin.server.shutdown()
            }, 500)
        }
        if (sender !is Player) { return true }


        sender.health = sender.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        sender.foodLevel = 20
        sender.saturation = 20F
        //todo nachicht adden

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("heal", ignoreCase = true)) {
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

