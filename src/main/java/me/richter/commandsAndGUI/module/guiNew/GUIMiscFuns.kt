package me.richter.commandsAndGUI.module.guiNew

import me.richter.commandsAndGUI.items.GeneralItems
import org.bukkit.Material
import org.bukkit.inventory.Inventory

class GUIMiscFuns {

    fun fillBG (inventory:Inventory) {

        for (slot in 0 until inventory.size) {
            val item = inventory.getItem(slot)

            when(slot) {
                in 0..8 -> if (item == null || item.type == Material.AIR) {inventory.setItem(slot, GeneralItems().itemGUIFillerBlack())}
                in 9..17 -> if (item == null || item.type == Material.AIR) {inventory.setItem(slot, GeneralItems().itemGUIFillerGray())}
                in 18..35 -> if (item == null || item.type == Material.AIR) {inventory.setItem(slot, GeneralItems().itemGUIFillerLightGray())}
                in 36..44 -> if (item == null || item.type == Material.AIR) {inventory.setItem(slot, GeneralItems().itemGUIFillerGray())}
                in 45..53 -> if (item == null || item.type == Material.AIR) {inventory.setItem(slot, GeneralItems().itemGUIFillerBlack())}
            }
        }
    }
}
