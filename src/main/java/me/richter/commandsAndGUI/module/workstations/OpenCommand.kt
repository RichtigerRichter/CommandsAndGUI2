package me.richter.commandsAndGUI.module.workstations

import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class OpenCommand: CommandExecutor, TabCompleter{
    @Override
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!ConfigFile.IsModuleEnabled.workstation) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }

        if (sender !is Player) return false
        if (args.isEmpty()) {return false}
        if (args[0].isEmpty()) {return false}

        if (args[0] == "Workbench") {
            if (!ConfigFile.IsModuleEnabled.workbench) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openWorkbench(sender) }

        if (args[0] == "CartographyTable") {
            if (!ConfigFile.IsModuleEnabled.cartographyTable) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openCartographyTable(sender) }

        if (args[0] == "Grindstone") {
            if (!ConfigFile.IsModuleEnabled.grindstone) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openGrindstone(sender) }

        if (args[0] == "Loom") {
            if (!ConfigFile.IsModuleEnabled.loom) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openLoom(sender) }

        if (args[0] == "SmithingTable") {
            if (!ConfigFile.IsModuleEnabled.smithingTable) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openSmithingTable(sender) }

        if (args[0] == "Stonecutter") {
            if (!ConfigFile.IsModuleEnabled.stonecutter) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            OpenWorkFun().openStonecutter(sender) }

        if (args[0] == "Anvil") {
            if (!ConfigFile.IsModuleEnabled.anvil) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            if (args.size == 1){
                OpenWorkFun().openAnvil(sender)
                return false
            }
            if (args[1] == "op"){
                OpenWorkFun().openAnvil(sender) }
        }

        if (args[0] == "Enchanting") {
            if (!ConfigFile.IsModuleEnabled.enchanting) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return false }
            if (args.size == 1) {
                OpenWorkFun().openEnchanting(sender)
                return false
            }
            if (args[1] == "op") {
                OpenWorkFun().openEnchanting(sender) }
        }


        return false
    }


    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name.equals("workstation", ignoreCase = true)) {
            if (!ConfigFile.IsModuleEnabled.workstation) { sender.sendMessage(MessagesFile.Message.moduleNotEnabled); return mutableListOf() }
            val completions = mutableListOf<String>()

            if (args.size == 1) {
                if (ConfigFile.IsModuleEnabled.workbench) { completions.add("Workbench") }
                if (ConfigFile.IsModuleEnabled.cartographyTable) { completions.add("CartographyTable") }
                if (ConfigFile.IsModuleEnabled.grindstone) { completions.add("Grindstone") }
                if (ConfigFile.IsModuleEnabled.loom) { completions.add("Loom") }
                if (ConfigFile.IsModuleEnabled.smithingTable) { completions.add("SmithingTable") }
                if (ConfigFile.IsModuleEnabled.stonecutter) { completions.add("Stonecutter") }
                if (ConfigFile.IsModuleEnabled.anvil) { completions.add("Anvil") }
                if (ConfigFile.IsModuleEnabled.enchanting) { completions.add("Enchanting") }
                return completions.filter { it.startsWith(args[0], ignoreCase = true) }.toMutableList()
            }
        }
        return mutableListOf()
    }

}