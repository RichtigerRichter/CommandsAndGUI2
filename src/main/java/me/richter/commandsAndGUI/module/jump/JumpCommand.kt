package me.richter.commandsAndGUI.module.jump

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import me.richter.commandsAndGUI.files.MessagesFile.Message.PREFIX
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class JumpCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.jump) {
            sender.sendMessage(Message.moduleNotEnabled); return true
        }

        if (args.isEmpty()) {
            if (sender !is Player) return true
            // Code wenn ohne argumente
            sender.velocity = sender.velocity.add(Vector(0.0, 1.0, 0.0))
            sender.sendMessage(Component.text("$PREFIX You Jumped"))
            return true
        }

        val targetPlayer = Bukkit.getPlayer(args[0])
        if (targetPlayer == null) {
            sender.sendMessage(
                Component.text("$PREFIX ${Message.playerDoesNotExist}")
            )
            return false
        }
        // Code mit targetPlayer
        targetPlayer.velocity = targetPlayer.velocity.add(Vector(0.0, 10.0, 0.0))
        if (targetPlayer == sender) {
            sender.sendMessage(Component.text("$PREFIX You Jumped")); return false
        }

        val targetPlayerC = args[0] //Name vom TARGET

        sender.sendMessage(Component.text("$PREFIX Made §6$targetPlayerC§r Jump"))


        return true
    }

}
