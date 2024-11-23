package me.richter.commandsAndGUI.modules.guiNew.workstationGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile.IsModuleEnabled
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.OpenWorkstationsItems
import me.richter.commandsAndGUI.modules.guiNew.GUIMiscFuns
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class WorkstationGUI {
    fun open(player: Player) {
        val inventory = Bukkit.createInventory(player, 6 * 9, Component.text("Workstation GUI"))
        Main.guiWorkstationMap[player.uniqueId] = inventory

        inventory.setItem(19, OpenWorkstationsItems().itemGuiOpenWorkbench())
        if (!IsModuleEnabled.workbench) inventory.setItem(19, GeneralItems().itemGuiUnavailable())

        inventory.setItem(21, OpenWorkstationsItems().itemGuiOpenEnchanting())
        if (!IsModuleEnabled.enchanting) inventory.setItem(21, GeneralItems().itemGuiUnavailable())

        inventory.setItem(23, OpenWorkstationsItems().itemGuiOpenStonecutter())
        if (!IsModuleEnabled.stonecutter) inventory.setItem(23, GeneralItems().itemGuiUnavailable())

        inventory.setItem(25, OpenWorkstationsItems().itemGuiOpenCartographyTable())
        if (!IsModuleEnabled.cartographyTable) inventory.setItem(25, GeneralItems().itemGuiUnavailable())

        inventory.setItem(28, OpenWorkstationsItems().itemGuiOpenAnvil())
        if (!IsModuleEnabled.anvil) inventory.setItem(28, GeneralItems().itemGuiUnavailable())

        inventory.setItem(30, OpenWorkstationsItems().itemGuiOpenSmithingTable())
        if (!IsModuleEnabled.smithingTable) inventory.setItem(30, GeneralItems().itemGuiUnavailable())

        inventory.setItem(32, OpenWorkstationsItems().itemGuiOpenGrindstone())
        if (!IsModuleEnabled.grindstone) inventory.setItem(32, GeneralItems().itemGuiUnavailable())

        inventory.setItem(34, OpenWorkstationsItems().itemGuiOpenLoom())
        if (!IsModuleEnabled.loom) inventory.setItem(34, GeneralItems().itemGuiUnavailable())


        inventory.setItem(48, GeneralItems().itemGuiBack())
        inventory.setItem(49, GeneralItems().itemGuiClose())

        GUIMiscFuns().fillBG(inventory)

        player.openInventory(inventory)
    }
}