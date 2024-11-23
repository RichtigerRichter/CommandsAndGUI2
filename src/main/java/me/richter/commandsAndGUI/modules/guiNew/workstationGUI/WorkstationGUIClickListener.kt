package me.richter.commandsAndGUI.modules.guiNew.workstationGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.OpenWorkstationsItems
import me.richter.commandsAndGUI.modules.guiNew.mainGUI.MainGUI
import me.richter.commandsAndGUI.modules.workstations.OpenWorkFun
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class WorkstationGUIClickListener : Listener {
    @EventHandler
    fun inventoryClickEvent(event: InventoryClickEvent) {
        if (event.currentItem == null) return
        val player = Bukkit.getPlayer(event.whoClicked.uniqueId) ?: return
        val inventory = Main.guiWorkstationMap[player.uniqueId]

        if (event.clickedInventory != inventory) {
            return
        }

        when (event.currentItem) {
            OpenWorkstationsItems().itemGuiOpenAnvil() -> {

                OpenWorkFun().openAnvil(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenCartographyTable() -> {
                OpenWorkFun().openCartographyTable(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenEnchanting() -> {
                OpenWorkFun().openEnchanting(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenGrindstone() -> {
                OpenWorkFun().openGrindstone(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenLoom() -> {
                OpenWorkFun().openLoom(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenSmithingTable() -> {
                OpenWorkFun().openSmithingTable(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenStonecutter() -> {
                OpenWorkFun().openStonecutter(player)
                event.isCancelled = true
                return
            }

            OpenWorkstationsItems().itemGuiOpenWorkbench() -> {
                OpenWorkFun().openWorkbench(player)
                event.isCancelled = true
                return
            }

            GeneralItems().itemGuiBack() -> {
                MainGUI().open(player)
                event.isCancelled = true
            }

            GeneralItems().itemGuiClose() -> {
                player.closeInventory()
                event.isCancelled = true
            }
        }
        event.isCancelled = true
    }
}