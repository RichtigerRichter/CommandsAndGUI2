package me.richter.commandsAndGUI.module.customCrafting.InvisibleItemFrames

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe

class InvisbleItemFrameRecipe {
	fun invisibleItemframeRecipe() {
		//TODO fixen das invis geht
		//TODO und das wenn man invis itemfram in der hand hat die anderen gowing bekommen
		//TODO und beschriftung in die lore

		// Create the resulting item (e.g., a diamond sword)
		val invisibleItemFrame = InvisibleItemFrameManager().invisibleItemFrameItem()


		// Define a NamespacedKey for the recipe (used to uniquely identify it)
		val key = NamespacedKey(Main.instance, "cag.invisible_itemFrame")

		// Create the shaped recipe with the resulting item
		val itemFrameRecipe = ShapedRecipe(key, invisibleItemFrame)

		// Define the shape of the recipe (3x3 crafting grid)
		itemFrameRecipe.shape(
			"GGG",  // Top row
			"GIG",  // Middle row
			"GGG"   // Bottom row
		)

		// Assign ingredients (D = DIAMOND, S = STICK)
		itemFrameRecipe.setIngredient('I', Material.ITEM_FRAME)
		itemFrameRecipe.setIngredient('G', Material.GLASS_PANE)

		// Register the recipe with the server
		Bukkit.addRecipe(itemFrameRecipe)
	}

	fun invisibleGlowItemframeRecipe() {
		//TODO fixen das invis geht
		//TODO und das wenn man invis itemfram in der hand hat die anderen gowing bekommen
		//TODO und beschriftung in die lore

		// Create the resulting item (e.g., a diamond sword)
		val invisibleGlowItemFrame = ItemBuilder().itemBuilder(Material.ITEM_FRAME, "Invisible GlowItemFrame", "", "cag.item", "invisibleItemFrame")



		// Define a NamespacedKey for the recipe (used to uniquely identify it)
		val key = NamespacedKey(Main.instance, "cag.invisible_glowItemFrame")

		// Create the shaped recipe with the resulting item
		val glowItemFrameRecipe = ShapedRecipe(key, invisibleGlowItemFrame)

		// Define the shape of the recipe (3x3 crafting grid)
		glowItemFrameRecipe.shape(
			"GGG",  // Top row
			"GIG",  // Middle row
			"GGG"   // Bottom row
		)

		// Assign ingredients (D = DIAMOND, S = STICK)
		glowItemFrameRecipe.setIngredient('I', Material.GLOW_ITEM_FRAME)
		glowItemFrameRecipe.setIngredient('G', Material.GLASS_PANE)

		// Register the recipe with the server
		Bukkit.addRecipe(glowItemFrameRecipe)
	}
}