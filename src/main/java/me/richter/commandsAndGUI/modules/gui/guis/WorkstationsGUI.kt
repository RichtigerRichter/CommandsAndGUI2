package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.files.ConfigFile.IsModuleEnabled
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.guiItems.OpenWorkstationsItems
import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.workstations.OpenWorkFun
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class WorkstationsGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Workstations"
    }

    override fun getSlots(): Int {
        return 6*9
    }

    override fun setGUIItems() {
        inventory.setItem(19, OpenWorkstationsItems().itemGuiOpenWorkbench())
        if (!IsModuleEnabled.workbench) inventory.setItem(19, GeneralItems().itemGuiUnavailable())

        inventory.setItem(21, OpenWorkstationsItems().itemGuiOpenEnchanting())
        if (!IsModuleEnabled.enchanting) inventory.setItem(21, GeneralItems().itemGuiUnavailable())

        inventory.setItem(23, OpenWorkstationsItems().itemGuiOpenStonecutter())
        if (!IsModuleEnabled.stonecutter) inventory.setItem(23, GeneralItems().itemGuiUnavailable())

        inventory.setItem(25, OpenWorkstationsItems().itemGuiOpenCartographyTable())
        if (!IsModuleEnabled.cartographyTable) inventory.setItem(25, GeneralItems().itemGuiUnavailable())

        inventory.setItem(28, OpenWorkstationsItems().itemGuiOpenAnvil())
        if (!IsModuleEnabled.anvil) inventory.setItem(28, GeneralItems().itemGuiUnavailable())

        inventory.setItem(30, OpenWorkstationsItems().itemGuiOpenSmithingTable())
        if (!IsModuleEnabled.smithingTable) inventory.setItem(30, GeneralItems().itemGuiUnavailable())

        inventory.setItem(32, OpenWorkstationsItems().itemGuiOpenGrindstone())
        if (!IsModuleEnabled.grindstone) inventory.setItem(32, GeneralItems().itemGuiUnavailable())

        inventory.setItem(34, OpenWorkstationsItems().itemGuiOpenLoom())
        if (!IsModuleEnabled.loom) inventory.setItem(34, GeneralItems().itemGuiUnavailable())


        inventory.setItem(48, GeneralItems().itemGuiBack())
        inventory.setItem(49, GeneralItems().itemGuiClose())

        setFillerGlass()
    }

    override fun handleGUI(event: InventoryClickEvent) {
        when (event.currentItem) {
            OpenWorkstationsItems().itemGuiOpenAnvil() -> {
                OpenWorkFun().openAnvil(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenCartographyTable() -> {
                OpenWorkFun().openCartographyTable(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenEnchanting() -> {
                OpenWorkFun().openEnchanting(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenGrindstone() -> {
                OpenWorkFun().openGrindstone(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenLoom() -> {
                OpenWorkFun().openLoom(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenSmithingTable() -> {
                OpenWorkFun().openSmithingTable(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenStonecutter() -> {
                OpenWorkFun().openStonecutter(guiData.getOwner())
                return
            }

            OpenWorkstationsItems().itemGuiOpenWorkbench() -> {
                OpenWorkFun().openWorkbench(guiData.getOwner())
                return
            }

            GeneralItems().itemGuiBack() -> {
                MainGUI(guiData).open()
            }

            GeneralItems().itemGuiClose() -> {
                guiData.getOwner().closeInventory()
            }
        }
    }
}