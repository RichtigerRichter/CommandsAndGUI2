package me.richter.commandsAndGUI.module.invSee

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.UUID

class InvSeeGUI {
    val helmetPlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Helmet Slot", "", "CAG.gui.item", "placeholder")
    val chesplatePlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Chestplate Slot", "", "CAG.gui.item", "placeholder")
    val leggingsPlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Leggings Slot", "", "CAG.gui.item", "placeholder")
    val bootsPlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Boots Slot", "", "CAG.gui.item", "placeholder")
    val offhandPlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Offhand Slot", "", "CAG.gui.item", "placeholder")
    val cursorItemPlaceholder = ItemBuilder().itemBuilder(Material.ORANGE_STAINED_GLASS_PANE, "Item in Cursor Slot", "", "CAG.gui.item", "placeholder")


    fun open(player: Player, target: Player) {
        val inventory: Inventory

        if (Main.guiInvSeeMap.contains(target.uniqueId)) {
            inventory = Main.guiInvSeeMap[target.uniqueId]!!
        } else {
            inventory = Bukkit.createInventory(player, 6*9, Component.text("${target.name}'s Inventory"))
            Main.guiInvSeeMap[target.uniqueId] = inventory
        }

        updateInvSeeGUI(target, inventory)

        player.openInventory(inventory)
    }

    fun updateInvSeeGUI(target: Player, inventory: Inventory) {
        val tInv = target.inventory
        val tOInv = target.openInventory
        val playerList = inventory.viewers

        //hotbar
        for (index in 0..8) {
            val item = tInv.getItem(index)
            inventory.setItem(index+4*9, item)
        }

        //inv
        for (index in 9..35) {
            val item = tInv.getItem(index)
            inventory.setItem(index, item)
        }

        inventory.setItem(0, tInv.helmet ?: helmetPlaceholder)
        inventory.setItem(1, tInv.chestplate ?: chesplatePlaceholder)
        inventory.setItem(2, tInv.leggings ?: leggingsPlaceholder)
        inventory.setItem(3, tInv.boots ?: bootsPlaceholder)
        inventory.setItem(4, GeneralItems().itemGUIFillerGray())
        if (tInv.itemInOffHand.type != Material.AIR) { inventory.setItem(5, tInv.itemInOffHand) } else { inventory.setItem(5, offhandPlaceholder) }
        inventory.setItem(6, GeneralItems().itemGUIFillerGray())
        if (tOInv.cursor.type != Material.AIR) { inventory.setItem(7, tOInv.cursor) } else { inventory.setItem(7, cursorItemPlaceholder) }
        inventory.setItem(8, GeneralItems().itemGUIFillerGray())

        for (player in playerList) {
            if (ItemBuilder().getCustomTagValue(player.itemOnCursor, "CAG.gui.item") == "placeholder") {
                player.setItemOnCursor(null)
            }
        }

        for (index in 45..53) {
            inventory.setItem(index, GeneralItems().itemGUIFillerGray())
            if (index == 49) inventory.setItem(49, GeneralItems().itemGuiClose())
        }
    }

    fun updateTargetInv(target: Player, inventory: Inventory) {
        //hotbar
        for (index in 0..8) {
            val item = inventory.getItem(index+4*9)
            target.inventory.setItem(index, item)
        }

        //inv
        for (index in 9..35) {
            val item = inventory.getItem(index)
            target.inventory.setItem(index, item)
        }


        if (ItemBuilder().getCustomTagValue(inventory.getItem(0) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.inventory.helmet = inventory.getItem(0)
        }
        if (ItemBuilder().getCustomTagValue(inventory.getItem(1) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.inventory.chestplate = inventory.getItem(1)
        }
        if (ItemBuilder().getCustomTagValue(inventory.getItem(2) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.inventory.leggings = inventory.getItem(2)
        }
        if (ItemBuilder().getCustomTagValue(inventory.getItem(3) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.inventory.boots = inventory.getItem(3)
        }
        if (ItemBuilder().getCustomTagValue(inventory.getItem(5) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.inventory.setItemInOffHand(inventory.getItem(5))
        }
        if (ItemBuilder().getCustomTagValue(inventory.getItem(7) ?: ItemStack(Material.AIR), "CAG.gui.item") != "placeholder") {
            target.openInventory.setCursor(inventory.getItem(7))
        }
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