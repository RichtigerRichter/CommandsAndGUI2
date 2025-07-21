package me.richter.commandsAndGUI.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class MainItems {
    fun itemGuiBackpackLogo(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.CHEST,
            "Backpack",
            "Click the Chest to open the Backpack",
            100
        )
    }
    fun itemGuiOpenWorkstation(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.CRAFTING_TABLE,
            "Open Workstation Menu",
            "Click to open the Workstation selection Menu",
            100
        )
    }
    fun itemGuiFlightLogo(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.ELYTRA,
            "Flight",
            "Click the dye below to toggle flying\nClick the Elytra to open the Settings",
            100
        )
    }
    fun itemGuiFlightOn(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.LIME_DYE,
            "Flying is§l§a enabled",
            "Click to disable flying",
            100
        )
    }
    fun itemGuiFlightOff(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GRAY_DYE,
            "Flying is§l§7 disabled",
            "Click to enable flying",
            100
        )
    }
    fun itemGuiGodmodeLogo(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.ENCHANTED_GOLDEN_APPLE,
            "Godmode",
            "Click the dye below to toggle Godmode\nClick this Enchanted Golden Apple to heal your self",
            100
        )
    }
    fun itemGuiGodmodeOn(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.LIME_DYE,
            "Godmode is§l§a enabled",
            "Click to disable Godmode",
            100
        )
    }
    fun itemGuiGodmodeOff(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GRAY_DYE,
            "Godmode is§l§7 disabled",
            "Click to enable Godmode",
            100
        )
    }
    fun itemGuiVanishLogo(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GLASS,
            "Vanish",
            "Click the dye below to toggle Vanish",
            100
        )
    }
    fun itemGuiVanishOn(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.LIME_DYE,
            "Vanish is§l§a enabled",
            "Click to disable Vanish",
            100
        )
    }
    fun itemGuiVanishOff(): ItemStack {
        return ItemBuilder().itemBuilder(
            Material.GRAY_DYE,
            "Vanish is§l§7 disabled",
            "Click to enable Vanish",
            100
        )
    }
}