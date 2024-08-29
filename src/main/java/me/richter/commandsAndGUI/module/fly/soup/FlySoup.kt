package me.richter.commandsAndGUI.module.fly.soup

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class FlySoup {
	fun itemBuilder(
		material: Material,
		name: String,
		lore: String,
		addGlint: Boolean,
		tagKey: String,
		tagValue: String
	): ItemStack {
		val plugin: JavaPlugin = Main.instance
		val item = ItemStack(material)
		val itemMeta: ItemMeta = item.itemMeta!!
		val key = NamespacedKey(plugin, tagKey)
		itemMeta.persistentDataContainer.set(key, PersistentDataType.STRING, tagValue)
		itemMeta.displayName(Component.text("§r§f$name"))
		itemMeta.lore(ItemBuilder().formatStringToComponents(lore))
		if (addGlint) {
			itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true)
			//itemMeta.itemFlags.add(ItemFlag.HIDE_ENCHANTS)
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
		}
		item.itemMeta = itemMeta
		return item
	}

	fun itemBuilder(
		material: Material,
		name: String,
		lore: String,
		count: Int,
		addGlint: Boolean,
		tagKey: String,
		tagValue: String
	): ItemStack {
		val plugin: JavaPlugin = Main.instance
		val item = ItemStack(material, count)
		val itemMeta: ItemMeta = item.itemMeta!!
		val key = NamespacedKey(plugin, tagKey)
		itemMeta.persistentDataContainer.set(key, PersistentDataType.STRING, tagValue)
		itemMeta.displayName(Component.text("§r§f$name"))
		itemMeta.lore(ItemBuilder().formatStringToComponents(lore))
		if (addGlint) {
			itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true)
			//itemMeta.itemFlags.add(ItemFlag.HIDE_ENCHANTS)
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
		}
		item.itemMeta = itemMeta
		return item
	}

	fun flySoupItem(): ItemStack {
		return itemBuilder(Material.FEATHER, name = "FlySoup", lore = "Eat temporarily to gain the ability to fly \nDuration: ?? min", addGlint =  true , tagKey = "CAG.item", tagValue = "flySoup")
	}
	fun flySoupItem(count: Int): ItemStack {
		return itemBuilder(Material.FEATHER, name = "FlySoup", lore = "Eat temporarily to gain the ability to fly \nDuration: ?? min", count, addGlint =  true , tagKey = "CAG.item", tagValue = "flySoup")
	}
	fun flySoup2Item(): ItemStack {
		return ItemBuilder().itemBuilder(Material.SUSPICIOUS_STEW, name = "FlySoup", lore = "Eat temporarily to gain the ability to fly \nDuration: ?? min", tagKey = "CAG.item", tagValue = "flySoup")
	}


}