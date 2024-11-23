package me.richter.commandsAndGUI.modules.guiNew.mainGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.MainItems
import me.richter.commandsAndGUI.modules.guiNew.GUIMiscFuns
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class MainGUI {
    fun open(player: Player) {
        val inventory = Bukkit.createInventory(player, 6 * 9, Component.text("Main GUI"))
        Main.guiMainMap[player.uniqueId] = inventory

        inventory.setItem(19, MainItems().itemGuiBackpackLogo())
        if (!ConfigFile.IsModuleEnabled.backpack) inventory.setItem(19, GeneralItems().itemGuiUnavailable())

        inventory.setItem(28, MainItems().itemGuiOpenWorkstation())
        if (!ConfigFile.IsModuleEnabled.allWorkstations) inventory.setItem(19, GeneralItems().itemGuiUnavailable())


        inventory.setItem(21, MainItems().itemGuiFlightLogo())
        if (player.allowFlight) {
            inventory.setItem(30, MainItems().itemGuiFlightOn())
        } else {
            inventory.setItem(30, MainItems().itemGuiFlightOff())
        }
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(21, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(30, GeneralItems().itemGuiUnavailable())


        inventory.setItem(23, MainItems().itemGuiGodmodeLogo())
        if (player.isInvulnerable) {
            inventory.setItem(32, MainItems().itemGuiGodmodeOn())
        } else {
            inventory.setItem(32, MainItems().itemGuiGodmodeOff())
        }
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(23, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(32, GeneralItems().itemGuiUnavailable())

        inventory.setItem(25, MainItems().itemGuiVanishLogo())
        if (Main.vanishedPlayersMap.containsKey(player.uniqueId) && Main.vanishedPlayersMap[player.uniqueId] == true) {
            inventory.setItem(34, MainItems().itemGuiVanishOn())
        } else {
            inventory.setItem(34, MainItems().itemGuiVanishOff())
        }
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(25, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(34, GeneralItems().itemGuiUnavailable())




        inventory.setItem(49, GeneralItems().itemGuiClose())

        GUIMiscFuns().fillBG(inventory)

        player.openInventory(inventory)
    }
}