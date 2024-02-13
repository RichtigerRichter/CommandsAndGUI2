package me.richter.commandsAndGUI.items

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemBuilder {

    fun itemBuilder(
        itemMaterial: Material,
        name: String,
        lore: String,
        customModelData: Int
    ): ItemStack {
        val item = ItemStack(itemMaterial)
        val itemMeta = item.itemMeta
        itemMeta.displayName(Component.text("§r§f$name"))
        if (lore != "") {
            itemMeta.lore(formatStringToComponents(lore))
        }
        itemMeta.setCustomModelData(customModelData)
        item.itemMeta = itemMeta
        return item
    }

    private fun formatStringToComponents(input: String): List<Component> {
        val components = mutableListOf<Component>()

        val lines = input.split("\n")

        for (line in lines){
            components.add(Component.text(line))
        }

        return components
    }

}
