package me.richter.commandsAndGUI.module.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryClickEventListener : Listener {

    @EventHandler
    fun onEvent(event: InventoryClickEvent) {
        val holder = event.inventory.holder

        if (holder is GUI) {
            val gui: GUI = holder

            if (event.currentItem != null) {
                if (event.isShiftClick) {
                    event.isCancelled = true
                }

                if (event.clickedInventory!!.holder === gui.inventory.holder) {
                    event.isCancelled = true
                    gui.handleGUI(event)
                }
            }
        }

    }

}