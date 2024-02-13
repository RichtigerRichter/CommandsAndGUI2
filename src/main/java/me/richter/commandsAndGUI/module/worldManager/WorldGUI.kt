package me.richter.commandsAndGUI.module.worldManager

import me.richter.commandsAndGUI.items.GeneralItems
import me.richter.commandsAndGUI.module.guiNew.GUIMiscFuns
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class WorldGUI {
    fun open (player: Player) {
        val inventory = Bukkit.createInventory(player, 6*9, Component.text("World GUI"))





        inventory.setItem(49, GeneralItems().itemGuiClose())
        GUIMiscFuns().fillBG(inventory)
        player.openInventory(inventory)
    }
}