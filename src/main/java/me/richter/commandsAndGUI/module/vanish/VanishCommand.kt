//TODO [TODO] Vanish other players & get info if players are vanished


package me.richter.commandsAndGUI.module.vanish

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class VanishCommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.vanish) { sender.sendMessage(Message.moduleNotEnabled); return true }

        if (sender !is Player) return true
        VanishManager(plugin).toggle(sender)

        if (VanishManager(plugin).isVanished(sender)) {
            if (args.size > 1) {return false}
            if (args.isEmpty()) {return false}
            if (args[0] != "fakeDisconnect") {return true}
            for (player in Bukkit.getOnlinePlayers()) {

                val language = player.locale().language
                when (language) {
                    "en" -> {player.sendMessage(Component.text("§e${sender.name} left the game"))}
                    "de" -> {player.sendMessage(Component.text("§e${sender.name} hat das Spiel verlassen"))}
                }
            }
        } else {
            if (args.size > 1) {return false}
            if (args.isEmpty()) {return false}
            if (args[0] != "fakeDisconnect") {return true}
            for (player in Bukkit.getOnlinePlayers()) {
                val language = player.locale().language
                when (language) {
                    "en" -> {player.sendMessage(Component.text("§e${sender.name} joined the game"))}
                    "de" -> {player.sendMessage(Component.text("§e${sender.name} hat das Spiel betreten"))}
                }
            }
        }



        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, lable: String, args: Array<out String>
    ): MutableList<String> {
        if (command.name.equals("vanish", ignoreCase = true)) {
            if (!ConfigFile.IsModuleEnabled.vanish) { sender.sendMessage(Message.moduleNotEnabled); return mutableListOf() }

            val completions = mutableListOf<String>()
            if (args.isEmpty()) {return completions}

            if (args.size == 1) {
                // Get list of online players
                completions.add("fakeDisconnect")
                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }
        }
        return mutableListOf()
    }


}

