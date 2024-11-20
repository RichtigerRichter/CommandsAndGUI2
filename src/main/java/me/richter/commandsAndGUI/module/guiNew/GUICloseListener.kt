package me.richter.commandsAndGUI.module.guiNew

import me.richter.commandsAndGUI.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

class GUICloseListener : Listener {
    @EventHandler
    fun inventoryCloseEvent(event: InventoryCloseEvent) {
        val uuid = event.player.uniqueId

        if (event.inventory == Main.guiMainMap[uuid]) Main.guiMainMap.remove(uuid)
        if (event.inventory == Main.guiFlySettingsMap[uuid]) Main.guiFlySettingsMap.remove(uuid)
        if (event.inventory == Main.guiWorkstationMap[uuid]) Main.guiWorkstationMap.remove(uuid)


    }
}