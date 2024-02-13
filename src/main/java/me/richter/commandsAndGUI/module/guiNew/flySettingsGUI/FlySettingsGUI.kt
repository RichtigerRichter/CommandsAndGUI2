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

        inventory.setItem(18, FlySettingsItems().skala0Logo())
        inventory.setItem(19, FlySettingsItems().skala25Logo())
        inventory.setItem(20, FlySettingsItems().skala50Logo())
        inventory.setItem(21, FlySettingsItems().skala75Logo())
        inventory.setItem(22, FlySettingsItems().skala100Logo())
        inventory.setItem(23, FlySettingsItems().skala125Logo())
        inventory.setItem(24, FlySettingsItems().skala150Logo())
        inventory.setItem(25, FlySettingsItems().skala175Logo())
        inventory.setItem(26, FlySettingsItems().skala200Logo())

        inventory.setItem(27, FlySettingsItems().skala0off())
        inventory.setItem(28, FlySettingsItems().skala25off())
        inventory.setItem(29, FlySettingsItems().skala50off())
        inventory.setItem(30, FlySettingsItems().skala75off())
        inventory.setItem(31, FlySettingsItems().skala100off())
        inventory.setItem(32, FlySettingsItems().skala125off())
        inventory.setItem(33, FlySettingsItems().skala150off())
        inventory.setItem(34, FlySettingsItems().skala175off())
        inventory.setItem(35, FlySettingsItems().skala200off())

        when (player.flySpeed) {
            0.000F -> { inventory.setItem(27, FlySettingsItems().skala0on()) }
            0.025F -> { inventory.setItem(28, FlySettingsItems().skala25on()) }
            0.050F -> { inventory.setItem(29, FlySettingsItems().skala50on()) }
            0.075F -> { inventory.setItem(30, FlySettingsItems().skala75Oon()) }
            0.100F -> { inventory.setItem(31, FlySettingsItems().skala100on()) }
            0.250F -> { inventory.setItem(32, FlySettingsItems().skala125On()) }
            0.500F -> { inventory.setItem(33, FlySettingsItems().skala150on()) }
            0.750F -> { inventory.setItem(34, FlySettingsItems().skala175on()) }
            1.000F -> { inventory.setItem(35, FlySettingsItems().skala200on()) }
        }

        inventory.setItem(48, GeneralItems().itemGuiBack())
        inventory.setItem(49, GeneralItems().itemGuiClose())

        GUIMiscFuns().fillBG(inventory)

        player.openInventory(inventory)
    }

    fun reset (player: Player) {
        val inventory: Inventory = Main.guiFlySettingsMap[player.uniqueId] ?: return

        inventory.setItem(27, FlySettingsItems().skala0off())
        inventory.setItem(28, FlySettingsItems().skala25off())
        inventory.setItem(29, FlySettingsItems().skala50off())
        inventory.setItem(30, FlySettingsItems().skala75off())
        inventory.setItem(31, FlySettingsItems().skala100off())
        inventory.setItem(32, FlySettingsItems().skala125off())
        inventory.setItem(33, FlySettingsItems().skala150off())
        inventory.setItem(34, FlySettingsItems().skala175off())
        inventory.setItem(35, FlySettingsItems().skala200off())
    }
}