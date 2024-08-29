package me.richter.commandsAndGUI.items.guiItems

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class OpenWorkstationsItems {
    fun itemGuiOpenWorkbench(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.CRAFTING_TABLE,
            "Open Crafting Table",
            "Click to open the Crafting menu",
            100
        )
    }
    fun itemGuiOpenCartographyTable(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.CARTOGRAPHY_TABLE,
            "Open Cartography Table",
            "Click to open the Cartography menu",
            100
        )
    }
    fun itemGuiOpenGrindstone(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GRINDSTONE,
            "Open Grindstone",
            "Click to open the Grindstone menu",
            100
        )
    }
    fun itemGuiOpenLoom(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.LOOM,
            "Open Loom",
            "Click to open the Loom menu",
            100
        )
    }
    fun itemGuiOpenSmithingTable(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.SMITHING_TABLE,
            "Open Smithing Table",
            "Click to open the Smithing menu",
            100
        )
    }
    fun itemGuiOpenStonecutter(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.STONECUTTER,
            "Open Stonecutter",
            "Click to open the Stonecutter menu",
            100
        )
    }
    fun itemGuiOpenAnvil(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.ANVIL,
            "Open Anvil",
            "Click to open the Anvil menu",
            100
        )
    }
    fun itemGuiOpenEnchanting(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.ENCHANTING_TABLE,
            "Open Enchanting Table",
            "Click to open the Enchanting menu",
            100
        )
    }
}