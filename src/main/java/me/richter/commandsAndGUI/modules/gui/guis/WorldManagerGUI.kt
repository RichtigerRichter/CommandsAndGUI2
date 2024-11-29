package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.utils.Text
import me.richter.commandsAndGUI.modules.worldManager.WorldManager
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class WorldManagerGUI(guiData : GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > World Manager"
    }

    override fun getSlots(): Int {
        return 9 * 6
    }

    override fun setGUIItems() {
        val worlds = WorldManager(Main.instance).getAllWorlds()
        for (worldID in worlds.indices) {
            val world = worlds[worldID]
            val worldName = world.name
            val worldDifficulty = world.difficulty.toString()
            val changedGameRules = WorldManager(Main.instance).getChangedGameRules(worldName)
            val seed = world.seed

            val worldItem = ItemStack(Material.GRASS_BLOCK, 1)
            val worldMeta = worldItem.itemMeta
            worldMeta.displayName(Text.miniMessage("<italic:false><white>$worldName"))
            val worldLore: MutableList<Component> = mutableListOf()
            worldLore.add(Text.miniMessage("<italic:false><yellow>seed: <dark_green>$seed"))
            worldLore.add(Text.miniMessage("<italic:false><yellow>difficulty: <dark_green>$worldDifficulty"))
            worldLore.addAll(changedGameRules)
            worldMeta.lore(worldLore)
            worldMeta.setCustomModelData(1)
            worldItem.setItemMeta(worldMeta)
            inventory.setItem(worldID, worldItem)
        }

        val goBackItem = ItemStack(Material.SPECTRAL_ARROW, 1)
        val goBackMeta = goBackItem.itemMeta
        goBackMeta.displayName(Text.miniMessage("<italic:false><white>Go Back"))
        goBackItem.setItemMeta(goBackMeta)
        inventory.setItem(getSlots() - 8, goBackItem)

    }

    override fun handleGUI(event: InventoryClickEvent) {

        when (event.slot) {
            getSlots() - 8 -> { guiData.lastGUIListGetLast().open() }
        }

    }
}