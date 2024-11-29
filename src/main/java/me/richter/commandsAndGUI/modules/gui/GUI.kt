package me.richter.commandsAndGUI.modules.gui

import me.richter.commandsAndGUI.modules.utils.Text
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class GUI(protected var guiData: GUIData) : InventoryHolder {

    private var inventory: Inventory? = null

    abstract fun getGUIName(): String
    abstract fun getSlots(): Int
    abstract fun setGUIItems()
    abstract fun handleGUI(event: InventoryClickEvent)

    // to use when going "forwards" to the next gui
    fun open(lastGUI: GUI) {
        inventory = Bukkit.createInventory(this, getSlots(), Text.miniMessage(getGUIName()))
        this.setGUIItems()
        guiData.lastGUIListAdd(lastGUI)
        guiData.getOwner().openInventory(inventory as Inventory)
    }

    fun update() {
        inventory = Bukkit.createInventory(this, getSlots(), Text.miniMessage(getGUIName()))
        this.setGUIItems()
        guiData.getOwner().openInventory(inventory as Inventory)
    }

    // to use when going "backwards" to the last gui
    fun open() {
        inventory = Bukkit.createInventory(this, getSlots(), Text.miniMessage(getGUIName()))
        this.setGUIItems()
        guiData.lastGUIListRemoveLast()
        guiData.getOwner().openInventory(inventory as Inventory)
    }

    override fun getInventory(): Inventory {
        return inventory!!
    }

    fun setFillerGlass() {
        for (i in 0 until getSlots()) {
            if (inventory!!.getItem(i) == null) {
                val item = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
                val itemMeta = item.itemMeta
                itemMeta.displayName(Text.miniMessage(" "))
                item.setItemMeta(itemMeta)
                inventory!!.setItem(i, item)
            }
        }
    }

    fun getFillerGlass(): ItemStack {
        val item = ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1)
        val itemMeta = item.itemMeta
        itemMeta.displayName(Text.miniMessage(" "))
        item.setItemMeta(itemMeta)
        return item
    }

}