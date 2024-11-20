package me.richter.commandsAndGUI.module.customCrafting.InvisibleItemFrames

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class InvisibleItemFrameManager {
	fun invisibleItemFrameItem(): ItemStack {
		return ItemBuilder().itemBuilder(Material.ITEM_FRAME, "Invisible ItemFrame", "", "cag.item", "invisibleItemFrame")
	}


}