package me.richter.commandsAndGUI.items

import me.richter.commandsAndGUI.Main
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin

class ItemBuilder {

    fun itemBuilder(
        material: Material,
        name: String,
        lore: String,
        customModelData: Int
    ): ItemStack {
        val item = ItemStack(material)
        val itemMeta = item.itemMeta
        itemMeta.displayName(Component.text("§r§f$name"))
        if (lore != "") {
            itemMeta.lore(formatStringToComponents(lore))
        }
        itemMeta.setCustomModelData(customModelData)
        item.itemMeta = itemMeta
        return item
    }


    fun itemBuilder(
        material: Material,
        name: String,
        lore: String,
        tagKey: String,
        tagValue: String
    ): ItemStack {
        val plugin: JavaPlugin = Main.instance
        val item = ItemStack(material)
        val itemMeta: ItemMeta = item.itemMeta!!
        val key = NamespacedKey(plugin, tagKey)
        itemMeta.persistentDataContainer.set(key, PersistentDataType.STRING, tagValue)
        itemMeta.displayName(Component.text("§r§f$name"))
        itemMeta.lore(formatStringToComponents(lore))
        item.itemMeta = itemMeta
        return item
    }

    fun itemBuilder(
        material: Material,
        displayName: Component,
        vararg lore: Component
    ): ItemStack {
        val item = ItemStack(material)
        val itemMeta: ItemMeta = item.itemMeta!!
        itemMeta.displayName(displayName)
        itemMeta.lore(lore.toList())
        item.itemMeta = itemMeta
        return item
    }

    fun itemBuilder(
        material: Material,
        displayName: Component,
        vararg lore: Component,
        customModelData: Int
    ): ItemStack {
        val item = ItemStack(material)
        val itemMeta: ItemMeta = item.itemMeta!!
        itemMeta.setCustomModelData(customModelData)
        itemMeta.displayName(displayName)
        itemMeta.lore(lore.toList())
        item.itemMeta = itemMeta
        return item
    }

    fun itemBuilder(
        material: Material,
        displayName: Component,
        vararg lore: Component,
        tagKey: String,
        tagValue: String
    ): ItemStack {
        val plugin: JavaPlugin = Main.instance
        val item = ItemStack(material)
        val itemMeta: ItemMeta = item.itemMeta!!
        val key = NamespacedKey(plugin, tagKey)
        itemMeta.persistentDataContainer.set(key, PersistentDataType.STRING, tagValue)
        itemMeta.displayName(displayName)
        itemMeta.lore(lore.toList())
        item.itemMeta = itemMeta
        return item
    }

    fun setCustomTagValue(itemMeta: ItemMeta, tagKey: String, tagValue: String) {
        val plugin: JavaPlugin = Main.instance
        val key = NamespacedKey(plugin, tagKey)
        itemMeta.persistentDataContainer.set(key, PersistentDataType.STRING, tagValue)


        println(itemMeta.persistentDataContainer.keys)
    }

    fun getCustomTagValue(item: ItemStack, tagKey: String): String? {
        val plugin: JavaPlugin = Main.instance
        val meta = item.itemMeta ?: return null

        // NamespacedKey für den benutzerdefinierten Tag erstellen
        val key = NamespacedKey(plugin, tagKey)

        // Wert aus dem PersistentDataContainer auslesen
        println(meta.persistentDataContainer.get(key, PersistentDataType.STRING))
        return meta.persistentDataContainer.get(key, PersistentDataType.STRING)
    }

    fun formatStringToComponents(input: String): List<Component> {
        val components = mutableListOf<Component>()

        val lines = input.split("\n")

        for (line in lines){
            components.add(Component.text(line))
        }

        return components
    }

    private fun formatStringArrayToComponents(input: Array<out String>): List<Component> {
        val components = mutableListOf<Component>()


        for (line in input){
            components.add(Component.text(line))
        }

        return components
    }

}
