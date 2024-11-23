package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.ConfigFile
import me.richter.commandsAndGUI.files.MessagesFile.Message
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.MainItems
import me.richter.commandsAndGUI.modules.backpack.BackpackManager
import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.utils.Text
import me.richter.commandsAndGUI.modules.vanish.VanishManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class PlayerGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Player: ${guiData.getSelectedPlayer().name}"
    }

    override fun getSlots(): Int {
        return 6*9
    }

    override fun setGUIItems() {
        inventory.setItem(19, MainItems().itemGuiBackpackLogo())
        if (!ConfigFile.IsModuleEnabled.backpack) inventory.setItem(19, GeneralItems().itemGuiUnavailable())

        inventory.setItem(28, MainItems().itemGuiOpenWorkstation())
        if (!ConfigFile.IsModuleEnabled.allWorkstations) inventory.setItem(19, GeneralItems().itemGuiUnavailable())


        inventory.setItem(21, MainItems().itemGuiFlightLogo())
        if (guiData.getSelectedPlayer().allowFlight) {
            inventory.setItem(30, MainItems().itemGuiFlightOn())
        } else {
            inventory.setItem(30, MainItems().itemGuiFlightOff())
        }
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(21, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.fly) inventory.setItem(30, GeneralItems().itemGuiUnavailable())

        inventory.setItem(23, MainItems().itemGuiGodmodeLogo())
        if (guiData.getSelectedPlayer().isInvulnerable) {
            inventory.setItem(32, MainItems().itemGuiGodmodeOn())
        } else {
            inventory.setItem(32, MainItems().itemGuiGodmodeOff())
        }
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(23, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.godmode) inventory.setItem(32, GeneralItems().itemGuiUnavailable())

        inventory.setItem(25, MainItems().itemGuiVanishLogo())
        if (Main.vanishedPlayersMap.containsKey(guiData.getSelectedPlayer().uniqueId) && Main.vanishedPlayersMap[guiData.getSelectedPlayer().uniqueId] == true) {
            inventory.setItem(34, MainItems().itemGuiVanishOn())
        } else {
            inventory.setItem(34, MainItems().itemGuiVanishOff())
        }
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(25, GeneralItems().itemGuiUnavailable())
        if (!ConfigFile.IsModuleEnabled.vanish) inventory.setItem(34, GeneralItems().itemGuiUnavailable())

        val goBackItem = ItemStack(Material.SPECTRAL_ARROW, 1)
        val goBackMeta = goBackItem.itemMeta
        goBackMeta.displayName(Text.miniMessage("<italic:false><white>Go Back"))
        goBackItem.setItemMeta(goBackMeta)
        inventory.setItem(getSlots() - 8, goBackItem)

        setFillerGlass()

    }

    override fun handleGUI(event: InventoryClickEvent) {
        when (event.currentItem) {
            MainItems().itemGuiBackpackLogo() -> {
                BackpackManager().openGUI(guiData.getOwner())
                return
            }

            MainItems().itemGuiOpenWorkstation() -> {
                WorkstationsGUI(guiData).open()
                return
            }

            MainItems().itemGuiFlightLogo() -> {
                FlySettingsGUI(guiData).open()
                return
            }

            MainItems().itemGuiFlightOn() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = false
                guiData.getOwner().sendMessage(Component.text(Message.flyingDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOff())
            }

            MainItems().itemGuiFlightOff() -> {
                Bukkit.getPlayer(event.whoClicked.uniqueId)!!.allowFlight = true
                guiData.getOwner().sendMessage(Component.text(Message.flyingEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiFlightOn())
            }

            MainItems().itemGuiGodmodeLogo() -> {
                guiData.getSelectedPlayer().health = guiData.getSelectedPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                guiData.getSelectedPlayer().foodLevel = 20
                guiData.getSelectedPlayer().saturation = 20F

                if (guiData.getOwner() == guiData.getSelectedPlayer()) {
                    guiData.getOwner().sendMessage(Component.text(Message.healed))
                } else {
                    guiData.getOwner().sendMessage(Component.text(Message.healedFor(guiData.getSelectedPlayer().name)))
                }
            }

            MainItems().itemGuiGodmodeOn() -> {
                guiData.getSelectedPlayer().isInvulnerable = false
                guiData.getOwner().sendMessage(Component.text(Message.godDisabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOff())
            }

            MainItems().itemGuiGodmodeOff() -> {
                guiData.getSelectedPlayer().isInvulnerable = true
                guiData.getOwner().sendMessage(Component.text(Message.godEnabled))
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiGodmodeOn())
            }

            MainItems().itemGuiVanishLogo() -> {}
            MainItems().itemGuiVanishOn() -> {
                VanishManager().set(guiData.getSelectedPlayer(), false)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOff())
                guiData.getOwner().sendMessage(Component.text(Message.vanishDisabled))
            }

            MainItems().itemGuiVanishOff() -> {
                VanishManager().set(guiData.getSelectedPlayer(), true)
                event.clickedInventory!!.setItem(event.slot, MainItems().itemGuiVanishOn())
                guiData.getOwner().sendMessage(Component.text(Message.vanishEnabled))
            }
        }

        if (event.slot == getSlots() - 8) {
            PlayerSelectGUI(guiData).open()
        }
    }
}