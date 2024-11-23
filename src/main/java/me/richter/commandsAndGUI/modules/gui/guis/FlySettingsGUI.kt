package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.guiItems.FlySettingsItems
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class FlySettingsGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Fly Settings"
    }

    override fun getSlots(): Int {
        return 6*9
    }

    override fun setGUIItems() {
        inventory.setItem(9 * 2 + 0, FlySettingsItems().skala0Logo())
        inventory.setItem(9 * 2 + 1, FlySettingsItems().skala25Logo())
        inventory.setItem(9 * 2 + 2, FlySettingsItems().skala50Logo())
        inventory.setItem(9 * 2 + 3, FlySettingsItems().skala75Logo())
        inventory.setItem(9 * 2 + 4, FlySettingsItems().skala100Logo())
        inventory.setItem(9 * 2 + 5, FlySettingsItems().skala250Logo())
        inventory.setItem(9 * 2 + 6, FlySettingsItems().skala500Logo())
        inventory.setItem(9 * 2 + 7, FlySettingsItems().skala750Logo())
        inventory.setItem(9 * 2 + 8, FlySettingsItems().skala1000Logo())

        setSkalaToOff(guiData.getSelectedPlayer())

        when (guiData.getSelectedPlayer().flySpeed) {
            0.000F -> {inventory.setItem(9 * 3 + 0, FlySettingsItems().skala0on())}
            0.025F -> {inventory.setItem(9 * 3 + 1, FlySettingsItems().skala25on())}
            0.050F -> {inventory.setItem(9 * 3 + 2, FlySettingsItems().skala50on())}
            0.075F -> {inventory.setItem(9 * 3 + 3, FlySettingsItems().skala75on())}
            0.100F -> {inventory.setItem(9 * 3 + 4, FlySettingsItems().skala100on())}
            0.250F -> {inventory.setItem(9 * 3 + 5, FlySettingsItems().skala250on())}
            0.500F -> {inventory.setItem(9 * 3 + 6, FlySettingsItems().skala500on())}
            0.750F -> {inventory.setItem(9 * 3 + 7, FlySettingsItems().skala750on())}
            1.000F -> {inventory.setItem(9 * 3 + 8, FlySettingsItems().skala1000on())}
            else -> {
                val customSpeedItem = ItemStack(Material.PLAYER_HEAD, 1)
                val customSpeedMeta = customSpeedItem.itemMeta
                customSpeedMeta.displayName(Text.miniMessage("<italic:false><white>${guiData.getSelectedPlayer().flySpeed * 1000}% Speed"))
                val customSpeedLore: MutableList<Component> = mutableListOf()
                customSpeedLore.add(Text.miniMessage("<italic:false><gray>Select a player to change their properties"))
                customSpeedMeta.lore(customSpeedLore)
                customSpeedItem.setItemMeta(customSpeedMeta)
                inventory.setItem(9 * 1 + 4, customSpeedItem)
            }
        }


        setFillerGlass()
    }

    override fun handleGUI(event: InventoryClickEvent) {
        when (event.currentItem) {
            FlySettingsItems().skala0off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala0on())
                guiData.getSelectedPlayer().flySpeed = 0.000f
            }

            FlySettingsItems().skala0on() -> {}

            FlySettingsItems().skala25off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala25on())
                guiData.getSelectedPlayer().flySpeed = 0.025f
            }

            FlySettingsItems().skala25on() -> {}

            FlySettingsItems().skala50off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala50on())
                guiData.getSelectedPlayer().flySpeed = 0.050F
            }

            FlySettingsItems().skala50on() -> {}

            FlySettingsItems().skala75off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala75on())
                guiData.getSelectedPlayer().flySpeed = 0.075F
            }

            FlySettingsItems().skala75on() -> {}

            FlySettingsItems().skala100off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala100on())
                guiData.getSelectedPlayer().flySpeed = 0.100F
            }

            FlySettingsItems().skala100on() -> {}

            FlySettingsItems().skala250off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala250on())
                guiData.getSelectedPlayer().flySpeed = 0.250F
            }

            FlySettingsItems().skala250on() -> {}

            FlySettingsItems().skala500off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala500on())
                guiData.getSelectedPlayer().flySpeed = 0.500F
            }

            FlySettingsItems().skala500on() -> {}

            FlySettingsItems().skala750off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala750on())
                guiData.getSelectedPlayer().flySpeed = 0.750F
            }

            FlySettingsItems().skala750on() -> {}

            FlySettingsItems().skala1000off() -> {
                setSkalaToOff(guiData.getSelectedPlayer())
                event.clickedInventory!!.setItem(event.slot, FlySettingsItems().skala1000on())
                guiData.getSelectedPlayer().flySpeed = 1.000F
            }

            FlySettingsItems().skala1000on() -> {}

            GeneralItems().itemGuiBack() -> {
                MainGUI(guiData).open()
            }

            GeneralItems().itemGuiClose() -> {
                guiData.getOwner().closeInventory()
            }

        }
    }

    fun setSkalaToOff(player: Player) {
        val inventory: Inventory = Main.guiFlySettingsMap[player.uniqueId] ?: return

        inventory.setItem(9 * 3 + 0, FlySettingsItems().skala0off())
        inventory.setItem(9 * 3 + 1, FlySettingsItems().skala25off())
        inventory.setItem(9 * 3 + 2, FlySettingsItems().skala50off())
        inventory.setItem(9 * 3 + 3, FlySettingsItems().skala75off())
        inventory.setItem(9 * 3 + 4, FlySettingsItems().skala100off())
        inventory.setItem(9 * 3 + 5, FlySettingsItems().skala250off())
        inventory.setItem(9 * 3 + 6, FlySettingsItems().skala500off())
        inventory.setItem(9 * 3 + 7, FlySettingsItems().skala750off())
        inventory.setItem(9 * 3 + 8, FlySettingsItems().skala1000off())
    }

}