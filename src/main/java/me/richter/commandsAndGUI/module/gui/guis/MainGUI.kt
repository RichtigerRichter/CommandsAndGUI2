package me.richter.commandsAndGUI.module.gui.guis

import me.richter.commandsAndGUI.module.gui.GUI
import me.richter.commandsAndGUI.module.gui.GUIData
import me.richter.commandsAndGUI.module.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class MainGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Main GUI"
    }

    override fun getSlots(): Int {
        return 27
    }

    override fun handleGUI(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        player.sendMessage("siis")
    }

    override fun setGUIItems() {
        val playerItem = ItemStack(Material.PLAYER_HEAD, 1)
        val playerMeta = playerItem.itemMeta
        playerMeta.displayName(Text.miniMessage("<italic:false><red>Players"))
        val playerLore: MutableList<Component> = mutableListOf()
        playerLore.add(Text.miniMessage("<italic:false><gray>Select a player to change their properties"))
        playerMeta.lore(playerLore)
        playerItem.setItemMeta(playerMeta)
        inventory!!.setItem(10, playerItem)

        val worldItem = ItemStack(Material.MAP, 1)
        val worldMeta = worldItem.itemMeta
        worldMeta.displayName(Text.miniMessage("<italic:false><green>Worlds"))
        val worldLore: MutableList<Component> = mutableListOf()
        worldLore.add(Text.miniMessage("<italic:false><gray>Select a world to change its properties"))
        worldMeta.lore(worldLore)
        worldItem.setItemMeta(worldMeta)
        inventory!!.setItem(12, worldItem)

        val serverItem = ItemStack(Material.COMMAND_BLOCK, 1)
        val serverMeta = serverItem.itemMeta
        serverMeta.displayName(Text.miniMessage("<italic:false><blue>Server"))
        val serverLore: MutableList<Component> = mutableListOf()
        serverLore.add(Text.miniMessage("<italic:false><gray>Change properties of this server"))
        serverMeta.lore(serverLore)
        serverItem.setItemMeta(serverMeta)
        inventory!!.setItem(14, serverItem)

        val pluginItem = ItemStack(Material.COMPARATOR, 1)
        val pluginMeta = pluginItem.itemMeta
        pluginMeta.displayName(Text.miniMessage("<italic:false><yellow>Plugin"))
        val pluginLore: MutableList<Component> = mutableListOf()
        pluginLore.add(Text.miniMessage("<italic:false><gray>Change properties of the CommandsAndGUI plugin"))
        pluginMeta.lore(pluginLore)
        pluginItem.setItemMeta(pluginMeta)
        inventory!!.setItem(16, pluginItem)

        setFillerGlass()
    }
}