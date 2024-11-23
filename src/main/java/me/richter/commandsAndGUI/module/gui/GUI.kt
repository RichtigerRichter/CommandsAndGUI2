package me.richter.commandsAndGUI.module.gui

import me.richter.commandsAndGUI.module.utils.Text
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class GUI(protected var guiData: GUIData) : InventoryHolder {

    private var inventory: Inventory? = null
    protected var page: Int = 0
    protected var pageMax: Int = 0
    protected var index: Int = 0
    protected var maxItemsPerPage: Int = 1 //getSlots() - 9;

    abstract fun getGUIName(): String
    abstract fun getSlots(): Int
    abstract fun setGUIItems()
    abstract fun handleGUI(event: InventoryClickEvent)

    fun open() {
        inventory = Bukkit.createInventory(this, getSlots(), Text.miniMessage(getGUIName()))
        this.setGUIItems()
        guiData.getOwner().openInventory(inventory as Inventory)
    }

    override fun getInventory(): Inventory {
        return inventory!!
    }

    fun setFillerGlass() {
        for (i in 0 until getSlots()) {
            if (inventory!!.getItem(i) == null) {
                val item = ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1)
                val itemMeta = item.itemMeta
                itemMeta.displayName(Text.miniMessage(" "))
                item.setItemMeta(itemMeta)
                inventory!!.setItem(i, item)
            }
        }
    }

    fun getFillerGlass(): ItemStack {
        val item = ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1)
        val itemMeta = item.itemMeta
        itemMeta.displayName(Text.miniMessage(" "))
        item.setItemMeta(itemMeta)
        return item
    }

}