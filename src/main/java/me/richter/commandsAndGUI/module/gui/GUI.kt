package me.richter.commandsAndGUI.module.gui

import me.richter.commandsAndGUI.module.utils.Text
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class GUI(guiData: GUIData) : InventoryHolder {

    protected var guiData: GUIData? = guiData
    private var inventory: Inventory? = null
    protected var page: Int = 0
    protected var pageMax: Int = 0
    protected var index: Int = 0
    protected var maxItemsPerPage: Int = 1 //getSlots() - 9;

    abstract fun getGUIName(): String
    abstract fun getSlots(): Int
    abstract fun handleGUI(event: InventoryClickEvent)
    abstract fun setGUIItems()

    fun open() {
        inventory = Bukkit.createInventory(this, getSlots(), Text.miniMessage(getGUIName()))
        this.setGUIItems()
        guiData!!.getOwner()!!.openInventory(inventory as Inventory)
    }

    override fun getInventory(): Inventory {
        return inventory!!
    }

    fun setFillerGlass() {
        for (i in 0 until getSlots()) {
            if (inventory!!.getItem(i) == null) {
                inventory!!.setItem(i, getFillerGlass())
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

    fun getBasicItem(material: Material?, displayName: String?): ItemStack {
        val itemStack = ItemStack(material!!, 1)
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName(Text.miniMessage(" "))
        itemStack.setItemMeta(itemMeta)
        return itemStack
    }

}