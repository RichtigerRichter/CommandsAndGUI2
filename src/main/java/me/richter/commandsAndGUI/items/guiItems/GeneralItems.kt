package me.richter.commandsAndGUI.items.guiItems

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class GeneralItems {
    fun itemGuiUnavailable(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.BEDROCK,
            "Unavailable",
            "this feature was disabled in the config",
            100
        )
    }

    fun itemGuiClose(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.BARRIER,
            "Close",
            "",
            100
        )
    }

    fun itemGuiBack(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.ARROW,
            "Back",
            "",
            100
        )
    }

    fun itemGUIFillerLightGray(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.LIGHT_GRAY_STAINED_GLASS_PANE,
            "",
            "",
            100
        )
    }

    fun itemGUIFillerGray(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GRAY_STAINED_GLASS_PANE,
            "",
            "",
            100
        )
    }

    fun itemGUIFillerBlack(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.BLACK_STAINED_GLASS_PANE,
            "",
            "",
            100
        )
    }
}