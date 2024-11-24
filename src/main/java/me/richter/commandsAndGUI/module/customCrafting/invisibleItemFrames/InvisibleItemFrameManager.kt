package me.richter.commandsAndGUI.module.customCrafting.invisibleItemFrames

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class InvisibleItemFrameManager {
	fun invisibleItemFrameItem(): ItemStack {
		return ItemBuilder().itemBuilder(Material.ITEM_FRAME, "Invisible GlowItemFrame", "If you hold this Item, placed Invisible Item frames will be marked with a particle", "cag.item", "invisibleItemFrame")
	}

	fun invisibleGlowItemFrameItem(): ItemStack {
		return ItemBuilder().itemBuilder(Material.GLOW_ITEM_FRAME, "Invisible GlowItemFrame", "If you hold this Item, placed Invisible Item frames will be marked with a particle", "cag.item", "invisibleItemFrame")
	}
}