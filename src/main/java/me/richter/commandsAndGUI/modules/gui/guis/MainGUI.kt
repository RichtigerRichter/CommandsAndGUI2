package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class MainGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Main GUI"
    }

    override fun getSlots(): Int {
        return 9 * 3
    }

    override fun setGUIItems() {
        guiData.setSelectedPlayer(guiData.getOwner())

        val playerItem = ItemStack(Material.PLAYER_HEAD, 1)
        val playerMeta = playerItem.itemMeta as SkullMeta
        playerMeta.displayName(Text.miniMessage("<italic:false><red>Players"))
        playerMeta.setOwningPlayer(guiData.getOwner())
        val playerLore: MutableList<Component> = mutableListOf()
        playerLore.add(Text.miniMessage("<italic:false><gray>Select a player to change their properties"))
        playerMeta.lore(playerLore)
        playerItem.setItemMeta(playerMeta)
        inventory.setItem(10, playerItem)

        val serverItem = ItemStack(Material.GRASS_BLOCK, 1)
        val serverMeta = serverItem.itemMeta
        serverMeta.displayName(Text.miniMessage("<italic:false><green>World Manager"))
        val serverLore: MutableList<Component> = mutableListOf()
        serverLore.add(Text.miniMessage("<italic:false><gray>World Manager"))
        serverMeta.lore(serverLore)
        serverItem.setItemMeta(serverMeta)
        inventory.setItem(12, serverItem)

        val worldItem = ItemStack(Material.CRAFTING_TABLE, 1)
        val worldMeta = worldItem.itemMeta
        worldMeta.displayName(Text.miniMessage("<italic:false><blue>Workstations"))
        val worldLore: MutableList<Component> = mutableListOf()
        worldLore.add(Text.miniMessage("<italic:false><gray>Select a workstation to use it"))
        worldMeta.lore(worldLore)
        worldItem.setItemMeta(worldMeta)
        inventory.setItem(14, worldItem)

        val pluginItem = ItemStack(Material.COMPARATOR, 1)
        val pluginMeta = pluginItem.itemMeta
        pluginMeta.displayName(Text.miniMessage("<italic:false><yellow>Plugin"))
        val pluginLore: MutableList<Component> = mutableListOf()
        pluginLore.add(Text.miniMessage("<italic:false><gray>Change properties of the CommandsAndGUI plugin"))
        pluginMeta.lore(pluginLore)
        pluginItem.setItemMeta(pluginMeta)
        inventory.setItem(16, pluginItem)

        setFillerGlass()
    }

    override fun handleGUI(event: InventoryClickEvent) {
        val guiOwner = event.whoClicked as Player

        if (event.slot == 10) {
            PlayerSelectGUI(guiData).open()
        }

        if (event.slot == 12) {
            WorldManagerGUI(guiData).open()
        }

        if (event.slot == 14) {
            WorkstationsGUI(guiData).open()
        }

        if (event.slot == 16) {
            guiOwner.sendMessage("me.richter.commandsAndGUI.module.gui.guis.MainGUI:78")
            //TODO editor for config files
        }

    }
}