package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import me.richter.commandsAndGUI.items.ItemBuilder
import me.richter.commandsAndGUI.module.guiNew.GUIMiscFuns
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WorldGUI {
    fun open (player: Player, plugin: JavaPlugin) {
        val inventory = Bukkit.createInventory(player, 6*9, Component.text("World GUI"))
        val worlds = WorldManager(plugin).getAllWorlds()
        for (worldID in worlds.indices) {
            val world = worlds[worldID]
            val worldName = world.name
            val worldDifficulty = world.difficulty.toString()
            val changedGameRuled = WorldManager(plugin).getChangedGameRules(worldName)
            val seed = world.seed

            val item = ItemBuilder().itemBuilder(
                Material.GRASS_BLOCK,
                worldName,
                "§r§eseed: §2$seed\n§edifficulty: §2$worldDifficulty$changedGameRuled",
                1
                )



            inventory.setItem(worldID, item)
        }




        inventory.setItem(49, GeneralItems().itemGuiClose())
        GUIMiscFuns().fillBG(inventory)
        player.openInventory(inventory)
    }
}