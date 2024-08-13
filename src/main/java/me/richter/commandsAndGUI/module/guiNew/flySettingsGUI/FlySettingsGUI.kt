package me.richter.commandsAndGUI.module.guiNew.flySettingsGUI

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.FlySettingsItems
import me.richter.commandsAndGUI.items.GeneralItems
import me.richter.commandsAndGUI.module.guiNew.GUIMiscFuns
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class FlySettingsGUI {
    fun open (player: Player) {
        val inventory = Bukkit.createInventory(player, 6*9, Component.text("Fly Settings GUI"))
        Main.guiFlySettingsMap[player.uniqueId] = inventory

        inventory.setItem(9*2+0, FlySettingsItems().skala0Logo())
        inventory.setItem(9*2+1, FlySettingsItems().skala25Logo())
        inventory.setItem(9*2+2, FlySettingsItems().skala50Logo())
        inventory.setItem(9*2+3, FlySettingsItems().skala75Logo())
        inventory.setItem(9*2+4, FlySettingsItems().skala100Logo())
        inventory.setItem(9*2+5, FlySettingsItems().skala250Logo())
        inventory.setItem(9*2+6, FlySettingsItems().skala500Logo())
        inventory.setItem(9*2+7, FlySettingsItems().skala750Logo())
        inventory.setItem(9*2+8, FlySettingsItems().skala1000Logo())

        this.setSkalaToOff(player)

        when (player.flySpeed) {
            0.000F -> { inventory.setItem(9*3+0, FlySettingsItems().skala0on()) }
            0.025F -> { inventory.setItem(9*3+1, FlySettingsItems().skala25on()) }
            0.050F -> { inventory.setItem(9*3+2, FlySettingsItems().skala50on()) }
            0.075F -> { inventory.setItem(9*3+3, FlySettingsItems().skala75on()) }
            0.100F -> { inventory.setItem(9*3+4, FlySettingsItems().skala100on()) }
            0.250F -> { inventory.setItem(9*3+5, FlySettingsItems().skala250on()) }
            0.500F -> { inventory.setItem(9*3+6, FlySettingsItems().skala500on()) }
            0.750F -> { inventory.setItem(9*3+7, FlySettingsItems().skala750on()) }
            1.000F -> { inventory.setItem(9*3+8, FlySettingsItems().skala1000on()) }
        }

        inventory.setItem(9*5+3, GeneralItems().itemGuiBack())
        inventory.setItem(9*5+4, GeneralItems().itemGuiClose())

        GUIMiscFuns().fillBG(inventory)

        player.openInventory(inventory)
    }

    fun setSkalaToOff (player: Player) {
        val inventory: Inventory = Main.guiFlySettingsMap[player.uniqueId] ?: return

        inventory.setItem(9*3+0, FlySettingsItems().skala0off())
        inventory.setItem(9*3+1, FlySettingsItems().skala25off())
        inventory.setItem(9*3+2, FlySettingsItems().skala50off())
        inventory.setItem(9*3+3, FlySettingsItems().skala75off())
        inventory.setItem(9*3+4, FlySettingsItems().skala100off())
        inventory.setItem(9*3+5, FlySettingsItems().skala250off())
        inventory.setItem(9*3+6, FlySettingsItems().skala500off())
        inventory.setItem(9*3+7, FlySettingsItems().skala750off())
        inventory.setItem(9*3+8, FlySettingsItems().skala1000off())
    }
}