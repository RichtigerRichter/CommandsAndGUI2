package me.richter.commandsAndGUI.module.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryDragEvent

class InventoryDragEventListener : Listener {

    @EventHandler
    fun onEvent(event: InventoryDragEvent) {
        val holder = event.inventory.holder

        if (holder is GUI) {
            val menu: GUI = holder as GUI
            for (slot in 0 until menu.getSlots()) {
                if (event.rawSlots.contains(slot)) {
                    event.isCancelled = true
                }
            }
        }
    }
}