package me.richter.commandsAndGUI.module.gui.mainGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.MessagesFile
import me.richter.commandsAndGUI.items.GeneralItems
import me.richter.commandsAndGUI.items.MainItems
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUI
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUI
import me.richter.commandsAndGUI.module.vanish.VanishManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.plugin.java.JavaPlugin

class MainGUIClickListener(private val plugin: JavaPlugin): Listener {
    @EventHandler
    fun mainGUIClickListener(event: InventoryClickEvent) {
        if(event.currentItem == null) return
        val player = Bukkit.getPlayer(event.whoClicked.uniqueId) ?: return
        val inventory = Main.guiMainMap[player.uniqueId]
        if(event.clickedInventory != inventory) { return }

        when (event.currentItem) {
            MainItems().itemGuiBackpackLogo() -> {
                BackpackManager().openGUI(player)
                event.isCancelled = true
                return}
            MainItems().itemGuiOpenWorkstation() -> {
                WorkstationGUI().open(player)
                event.isCancelled = true
                return }

            MainItems().itemGuiFlightLogo() -> {
                FlySettingsGUI().open(player)
                event.isCancelled = true
                return }
            MainItems().itemGuiFlightOn() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = false
                player.sendMessage(Component.text(MessagesFile.Message.flyingDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOff()) }
            MainItems().itemGuiFlightOff() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = true
                player.sendMessage(Component.text(MessagesFile.Message.flyingEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOn()) }

            MainItems().itemGuiGodmodeLogo() -> {
                player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                player.foodLevel = 20
                player.saturation = 20F
            }
            MainItems().itemGuiGodmodeOn() -> {
                player.isInvulnerable = false
                player.sendMessage(Component.text(MessagesFile.Message.godDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOff()) }
            MainItems().itemGuiGodmodeOff() -> {
                player.isInvulnerable = true
                player.sendMessage(Component.text(MessagesFile.Message.godEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOn()) }

            MainItems().itemGuiVanishLogo() -> {}
            MainItems().itemGuiVanishOn() -> {
                VanishManager(plugin).set(player, false)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOff())
                player.sendMessage(Component.text(MessagesFile.Message.vanishDisabled))}
            MainItems().itemGuiVanishOff() -> {
                VanishManager(plugin).set(player, true)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOn())
                player.sendMessage(Component.text(MessagesFile.Message.vanishEnabled))}

            GeneralItems().itemGuiClose() -> inventory?.close()
        }

        event.isCancelled = true
    }
}