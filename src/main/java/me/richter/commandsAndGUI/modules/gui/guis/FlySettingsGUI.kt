package me.richter.commandsAndGUI.modules.gui.guis

import me.richter.commandsAndGUI.files.MessagesFile.Message
import me.richter.commandsAndGUI.modules.gui.GUI
import me.richter.commandsAndGUI.modules.gui.GUIData
import me.richter.commandsAndGUI.modules.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class FlySettingsGUI(guiData: GUIData) : GUI(guiData) {

    override fun getGUIName(): String {
        return "CAG > Fly Settings"
    }

    override fun getSlots(): Int {
        return 9 * 4
    }

    override fun setGUIItems() {
        val flySpeeds: MutableList<Float> = mutableListOf(0f, 25f, 50f, 75f, 100f, 250f, 500f, 750f, 1000f)
        var slot = 9 * 1

        for (speed in flySpeeds) {
            if ((speed / 1000f) < guiData.getSelectedPlayer().flySpeed) {
                val logoItem = ItemStack(Material.FEATHER, 1)
                val logoMeta = logoItem.itemMeta
                logoMeta.displayName(Text.miniMessage("<italic:false><white>${speed.toInt()}% Fly Speed"))
                logoMeta.setCustomModelData(100)
                logoItem.setItemMeta(logoMeta)
                inventory.setItem(slot, logoItem)
            } else if ((speed / 1000f) == guiData.getSelectedPlayer().flySpeed) {
                val logoItem = ItemStack(Material.ELYTRA, 1)
                val logoMeta = logoItem.itemMeta
                logoMeta.displayName(Text.miniMessage("<italic:false><white>${speed.toInt()}% Fly Speed"))
                logoMeta.setCustomModelData(100)
                logoItem.setItemMeta(logoMeta)
                inventory.setItem(slot, logoItem)
            } else if ((speed / 1000f) > guiData.getSelectedPlayer().flySpeed) {
                val logoItem = ItemStack(Material.FIREWORK_ROCKET, 1)
                val logoMeta = logoItem.itemMeta
                logoMeta.displayName(Text.miniMessage("<italic:false><white>${speed.toInt()}% Fly Speed"))
                logoMeta.setCustomModelData(100)
                logoItem.setItemMeta(logoMeta)
                inventory.setItem(slot, logoItem)
            }
            slot += 1
        }

        val goBackItem = ItemStack(Material.SPECTRAL_ARROW, 1)
        val goBackMeta = goBackItem.itemMeta
        goBackMeta.displayName(Text.miniMessage("<italic:false><white>Go Back"))
        goBackItem.setItemMeta(goBackMeta)
        inventory.setItem(getSlots() - 8, goBackItem)

        setFillerGlass()
    }

    override fun handleGUI(event: InventoryClickEvent) {

        when (event.slot) {
            9 * 1 + 0 -> { guiData.getSelectedPlayer().flySpeed = 0.000f }
            9 * 1 + 1 -> { guiData.getSelectedPlayer().flySpeed = 0.025f }
            9 * 1 + 2 -> { guiData.getSelectedPlayer().flySpeed = 0.050f }
            9 * 1 + 3 -> { guiData.getSelectedPlayer().flySpeed = 0.075f }
            9 * 1 + 4 -> { guiData.getSelectedPlayer().flySpeed = 0.100f }
            9 * 1 + 5 -> { guiData.getSelectedPlayer().flySpeed = 0.250f }
            9 * 1 + 6 -> { guiData.getSelectedPlayer().flySpeed = 0.500f }
            9 * 1 + 7 -> { guiData.getSelectedPlayer().flySpeed = 0.750f }
            9 * 1 + 8 -> { guiData.getSelectedPlayer().flySpeed = 1.000f }
            getSlots() - 8 -> { guiData.lastGUIListGetLast().open() }
        }

        if (event.slot in 9 * 1 + 0..9 * 1 + 8) {
            if (guiData.getSelectedPlayer() == guiData.getOwner()) {
                guiData.getOwner().sendMessage(Component.text(Message.getFlySpeed((guiData.getSelectedPlayer().flySpeed * 1000).toInt().toString())))
            } else {
                guiData.getOwner().sendMessage(Component.text(Message.getFlySpeedFor((guiData.getSelectedPlayer().flySpeed * 1000).toInt().toString(), guiData.getSelectedPlayer().name)))
            }
            super.update()
        }

    }

}