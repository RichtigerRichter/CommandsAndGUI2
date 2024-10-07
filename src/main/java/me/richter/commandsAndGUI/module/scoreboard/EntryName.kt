package me.richter.commandsAndGUI.module.scoreboard

import org.bukkit.ChatColor


enum class EntryName(val entry: Int, val entryName: String) {
	ENTRY_0(0, ChatColor.DARK_PURPLE.toString()),
	ENTRY_1(1, ChatColor.DARK_GRAY.toString()),
	ENTRY_2(2, ChatColor.BOLD.toString()),
	ENTRY_3(3, ChatColor.DARK_RED.toString()),
	ENTRY_4(4, ChatColor.GRAY.toString()),
	ENTRY_5(5, ChatColor.DARK_GREEN.toString()),
	ENTRY_6(6, ChatColor.LIGHT_PURPLE.toString()),
	ENTRY_7(7, ChatColor.UNDERLINE.toString())
}