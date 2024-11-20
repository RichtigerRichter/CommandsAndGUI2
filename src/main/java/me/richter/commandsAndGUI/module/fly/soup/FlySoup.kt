package me.richter.commandsAndGUI.module.fly.soup

import me.richter.commandsAndGUI.items.ItemBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class FlySoup {
    fun itemBuilder(
        material: Material,
        name: String?,
        lore: String?,
        maxStackSize: Int?,
        count: Int,
        addGlint: Boolean,
        tagKey: String?,
        tagValue: String?
    ): ItemStack {
        val item = ItemStack(material, count)
        val itemMeta: ItemMeta = item.itemMeta

        if (name != null) itemMeta.displayName(Component.text("§r§f$name"))
        if (lore != null) itemMeta.lore(ItemBuilder().formatStringToComponents(lore))
        if (maxStackSize != null) itemMeta.setMaxStackSize(maxStackSize)
        if (addGlint) itemMeta.setEnchantmentGlintOverride(true)
        if (tagKey != null && tagValue != null) ItemBuilder().setCustomTagValue(itemMeta, tagKey, tagValue)

        item.itemMeta = itemMeta
        return item
    }

    fun flySoupItem(count: Int): ItemStack {
        return itemBuilder(
            material = Material.SUSPICIOUS_STEW,
            name = "FlySoup",
            lore = "Eat to temporarily gain the ability to fly \nDuration: ?? min",
            maxStackSize = 64,
            count = count,
            addGlint = true,
            tagKey = "CAG.item",
            tagValue = "flySoup"
        )
    }

}