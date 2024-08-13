package me.richter.commandsAndGUI.module.guiNew.flySettingsGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.FlySettingsItems
import me.richter.commandsAndGUI.items.GeneralItems
import me.richter.commandsAndGUI.module.guiNew.mainGUI.MainGUI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class FlySettingsGUIClickListener: Listener {
    @EventHandler
    fun inventoryClickEvent(event: InventoryClickEvent) {
        if(event.currentItem == null) return
        val player = Bukkit.getPlayer(event.whoClicked.uniqueId) ?: return
        val inventory = Main.guiFlySettingsMap[player.uniqueId]
        if(event.clickedInventory != inventory) { return }

        when (event.currentItem) {
            FlySettingsItems().skala0off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala0on())
                player.flySpeed = 0.000f
                event.isCancelled = true
            }
            FlySettingsItems().skala0on() -> {}

            FlySettingsItems().skala25off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala25on())
                player.flySpeed = 0.025f
                event.isCancelled = true
            }
            FlySettingsItems().skala25on() -> {}

            FlySettingsItems().skala50off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala50on())
                player.flySpeed = 0.050F
                event.isCancelled = true
            }
            FlySettingsItems().skala50on() -> {}

            FlySettingsItems().skala75off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala75on())
                player.flySpeed = 0.075F
                event.isCancelled = true
            }
            FlySettingsItems().skala75on() -> {}

            FlySettingsItems().skala100off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala100on())
                player.flySpeed = 0.100F
                event.isCancelled = true
            }
            FlySettingsItems().skala100on() -> {}

            FlySettingsItems().skala250off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala250on())
                player.flySpeed = 0.250F
                event.isCancelled = true
            }
            FlySettingsItems().skala250on() -> {}

            FlySettingsItems().skala500off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala500on())
                player.flySpeed = 0.500F
                event.isCancelled = true
            }
            FlySettingsItems().skala500on() -> {}

            FlySettingsItems().skala750off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala750on())
                player.flySpeed = 0.750F
                event.isCancelled = true
            }
            FlySettingsItems().skala750on() -> {}

            FlySettingsItems().skala1000off() -> {
                FlySettingsGUI().setSkalaToOff(player)
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala1000on())
                player.flySpeed = 1.000F
                event.isCancelled = true
            }
            FlySettingsItems().skala1000on() -> {}

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