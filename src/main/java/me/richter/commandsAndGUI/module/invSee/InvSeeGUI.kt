package me.richter.commandsAndGUI.module.invSee

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

class InvSeeGUI {
    fun open(player: Player, target: Player) {
        val inventory: Inventory

        if (Main.guiInvSeeMap.contains(target.uniqueId)) {
            inventory = Main.guiInvSeeMap[target.uniqueId]!!
        } else {
            inventory = Bukkit.createInventory(player, 6 * 9, Component.text("${target.name}'s Inventory"))
            Main.guiInvSeeMap[target.uniqueId] = inventory
        }

        updateInvSeeGUI(target, inventory)

        player.openInventory(inventory)
    }

    fun updateInvSeeGUI(target: Player, inventory: Inventory) {
        //hotbar
        for (index in 0..8) {
            val item = target.inventory.getItem(index)
            inventory.setItem(index + 4 * 9, item)
        }

        //inv
        for (index in 9..35) {
            val item = target.inventory.getItem(index)
            inventory.setItem(index, item)
        }

        inventory.setItem(0, target.inventory.helmet)
        inventory.setItem(1, target.inventory.chestplate)
        inventory.setItem(2, target.inventory.leggings)
        inventory.setItem(3, target.inventory.boots)
        inventory.setItem(4, GeneralItems().itemGUIFillerGray())
        inventory.setItem(5, target.inventory.itemInOffHand)
        inventory.setItem(6, GeneralItems().itemGUIFillerGray())
        inventory.setItem(7, target.openInventory.cursor)
        inventory.setItem(8, GeneralItems().itemGUIFillerGray())

        inventory.setItem(49, GeneralItems().itemGuiClose())
    }

    fun updateTargetInv(target: Player, inventory: Inventory) {
        //hotbar
        for (index in 0..8) {
            val item = inventory.getItem(index + 4 * 9)
            target.inventory.setItem(index, item)
        }

        //inv
        for (index in 9..35) {
            val item = inventory.getItem(index)
            target.inventory.setItem(index, item)
        }

        target.inventory.helmet = inventory.getItem(0)
        target.inventory.chestplate = inventory.getItem(1)
        target.inventory.leggings = inventory.getItem(2)
        target.inventory.boots = inventory.getItem(3)
        target.inventory.setItemInOffHand(inventory.getItem(5))
        target.openInventory.setCursor(inventory.getItem(7))

    }

    fun getTargetFromInv(inventory: Inventory): UUID? {
        if (!Main.guiInvSeeMap.containsValue(inventory)) return null
        val keys = Main.guiInvSeeMap.keys
        for (key in keys) {
            if (Main.guiInvSeeMap[key] == inventory) {
                return key
            }
        }
        return null
    }

    fun updateAllInvSeeGUIs() {
        val allTargetsUUIDs = Main.guiInvSeeMap.keys
        for (targetUUID in allTargetsUUIDs) {
            val target = Bukkit.getPlayer(targetUUID) ?: return
            InvSeeGUI().updateInvSeeGUI(target, Main.guiInvSeeMap[targetUUID]!!)

        }
    }
}