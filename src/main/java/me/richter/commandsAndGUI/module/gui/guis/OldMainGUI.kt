package me.richter.commandsAndGUI.module.gui.guis

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.MainItems
import me.richter.commandsAndGUI.module.backpack.BackpackManager
import me.richter.commandsAndGUI.module.gui.GUI
import me.richter.commandsAndGUI.module.gui.GUIData
import me.richter.commandsAndGUI.module.guiNew.flySettingsGUI.FlySettingsGUI
import me.richter.commandsAndGUI.module.guiNew.workstationGUI.WorkstationGUI
import me.richter.commandsAndGUI.module.vanish.VanishManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class OldMainGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Old Main GUI"
    }

    override fun getSlots(): Int {
        return 54
    }

    override fun setGUIItems() {
        inventory.setItem(19, MainItems().itemGuiBackpackLogo())
        if (!ConfigFile.IsModuleEnabled.backpack) inventory.setItem(19, GeneralItems().itemGuiUnavailable())

        inventory.setItem(28, MainItems().itemGuiOpenWorkstation())
        if (!ConfigFile.IsModuleEnabled.allWorkstations) inventory.setItem(19, GeneralItems().itemGuiUnavailable())


        inventory.setItem(21, MainItems().itemGuiFlightLogo())
        if (guiData.getOwner().allowFlight) {
            inventory.setItem(30, MainItems().itemGuiFlightOn())
        } else {
            inventory.setItem(30, MainItems().itemGuiFlightOff())
        }
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(21, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(30, GeneralItems().itemGuiUnavailable())

        inventory.setItem(23, MainItems().itemGuiGodmodeLogo())
        if (guiData.getOwner().isInvulnerable) {
            inventory.setItem(32, MainItems().itemGuiGodmodeOn())
        } else {
            inventory.setItem(32, MainItems().itemGuiGodmodeOff())
        }
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(23, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(32, GeneralItems().itemGuiUnavailable())

        inventory.setItem(25, MainItems().itemGuiVanishLogo())
        if (Main.vanishedPlayersMap.containsKey(guiData.getOwner().uniqueId) && Main.vanishedPlayersMap[guiData.getOwner().uniqueId] == true) {
            inventory.setItem(34, MainItems().itemGuiVanishOn())
        } else {
            inventory.setItem(34, MainItems().itemGuiVanishOff())
        }
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(25, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(34, GeneralItems().itemGuiUnavailable())

        setFillerGlass()

    }

    override fun handleGUI(event: InventoryClickEvent) {
        val player = event.whoClicked as Player

        when (event.currentItem) {
            MainItems().itemGuiBackpackLogo() -> {
                BackpackManager().openGUI(player)
                return
            }

            MainItems().itemGuiOpenWorkstation() -> {
                WorkstationGUI().open(player)
                return
            }

            MainItems().itemGuiFlightLogo() -> {
                FlySettingsGUI().open(player)
                return
            }

            MainItems().itemGuiFlightOn() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = false
                player.sendMessage(Component.text(Message.flyingDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOff())
            }

            MainItems().itemGuiFlightOff() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = true
                player.sendMessage(Component.text(Message.flyingEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOn())
            }

            MainItems().itemGuiGodmodeLogo() -> {
                player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                player.foodLevel = 20
                player.saturation = 20F
            }

            MainItems().itemGuiGodmodeOn() -> {
                player.isInvulnerable = false
                player.sendMessage(Component.text(Message.godDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOff())
            }

            MainItems().itemGuiGodmodeOff() -> {
                player.isInvulnerable = true
                player.sendMessage(Component.text(Message.godEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOn())
            }

            MainItems().itemGuiVanishLogo() -> {}
            MainItems().itemGuiVanishOn() -> {
                VanishManager().set(player, false)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOff())
                player.sendMessage(Component.text(Message.vanishDisabled))
            }

            MainItems().itemGuiVanishOff() -> {
                VanishManager().set(player, true)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOn())
                player.sendMessage(Component.text(Message.vanishEnabled))
            }

            GeneralItems().itemGuiClose() -> inventory.close()
        }
    }
}