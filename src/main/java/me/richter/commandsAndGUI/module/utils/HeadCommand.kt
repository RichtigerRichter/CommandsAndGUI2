package me.richter.commandsAndGUI.module.utils

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class HeadCommand: CommandExecutor {
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		val head = if (args.isNullOrEmpty()) {
			Head().getHead("http://textures.minecraft.net/texture/5c8817ee8e9c2c2bf767487737e0a60a5e09b725138e14daa57480a03f1766d8") //missing texture head
		} else {
			Head().getHead(args[0])
		}
		if (sender !is Player) return false
		sender.inventory.addItem(head)
		return true
	}

}