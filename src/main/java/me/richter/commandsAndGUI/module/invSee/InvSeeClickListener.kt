package me.richter.commandsAndGUI.module.invSee

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import me.richter.commandsAndGUI.items.guiItems.GeneralItems
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.InventoryView

class InvSeeClickListener(): Listener {
    @EventHandler
    fun invSeeGUIClickListener(event: InventoryClickEvent) {
        val player = event.whoClicked
        val inventories = Main.guiInvSeeMap.values
        if(!inventories.contains(event.clickedInventory)) { return }

        when (event.currentItem) {
            GeneralItems().itemGUIFillerGray() -> { event.isCancelled = true }
            GeneralItems().itemGUIFillerBlack() -> { event.isCancelled = true }
            GeneralItems().itemGUIFillerLightGray() -> { event.isCancelled = true }

            GeneralItems().itemGuiClose() -> { event.inventory.close(); event.isCancelled = true }
        }
        if (player.itemOnCursor.type == Material.AIR && ItemBuilder().getCustomTagValue(event.currentItem ?: return, "CAG.gui.item") == "placeholder") event.isCancelled = true
        if (event.isShiftClick && ItemBuilder().getCustomTagValue(event.currentItem ?: return, "CAG.gui.itme") == "placeholder") event.isCancelled = true
    }


    /*
    @EventHandler
    fun targetPlayerCursorItem(event: InventoryClickEvent) {
        var item = event.cursor
        if (event.currentItem == null) { item = ItemStack(Material.AIR) }

        val target = Bukkit.getPlayer(event.whoClicked.uniqueId) ?: return
        val viewerList = InvSeeGUI().getViewersForTarget(target)

        for (viewer in viewerList) {
            val targetPlayerInventory = Main.guiInvSeeMap[viewer.uniqueId] ?: break
            val inventory = targetPlayerInventory.inventory

            InvSeeGUI().updateInvSeeGUI(targetPlayerInventory)
            inventory.setItem(43, event.cursor)
        }

    }
    */

    /*
    @EventHandler
    fun targetPlayerCursorItem(event: InventoryClickEvent) {
        val target = event.whoClicked as? Player ?: return

        // Start a task asynchronously
        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, Runnable {
            // Hier kannst du Berechnungen durchführen, die keine Bukkit-API-Interaktion erfordern

            // Zurück zum Hauptthread wechseln, um Inventaränderungen sicher vorzunehmen
            Bukkit.getScheduler().runTask(Main.instance, Runnable {
                // Update des Cursors nach dem Event
                val updatedCursorItem = event.currentItem ?: ItemStack(Material.AIR)

                // Zuschauer des Zielspielers abrufen
                val viewerList = InvSeeGUI().getViewersForTarget(target)

                for (viewer in viewerList) {
                    val targetPlayerInventory = Main.guiInvSeeMap[viewer.uniqueId] ?: continue
                    val inventory = targetPlayerInventory.inventory

                    // Inventar aktualisieren
                    InvSeeGUI().updateInvSeeGUI(targetPlayerInventory)
                    inventory.setItem(43, updatedCursorItem) // Aktualisiere den Slot 43 mit dem aktuellen Cursor-Item
                }
            })
        })
    }

     */

    /*
    @EventHandler
    fun targetPlayerCursorItem(event: InventoryClickEvent) {
        val target = event.whoClicked as? Player ?: return
        val updatedCursorItem = event.currentItem ?: ItemStack(Material.AIR)
        val clickedInventory: Inventory = event.inventory

        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, Runnable {
            Bukkit.getScheduler().runTask(Main.instance, Runnable {
                target.openInventory.cursor
            })
        })

        val inventory = Main.guiInvSeeMap[target.uniqueId] ?: return

        inventory.setItem(7, target.openInventory.cursor)


    }

 */

    @EventHandler
    fun updateInvSeeGuiOnClick(event: InventoryClickEvent) {
        //if(event.currentItem == null) return
        val player = event.whoClicked as Player
        val inventory = event.clickedInventory ?: return
        val targetInventory = Main.guiInvSeeMap[player.uniqueId] ?: return
        if (!Main.guiInvSeeMap.containsKey(player.uniqueId)) return

        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, Runnable {
            Bukkit.getScheduler().runTask(Main.instance, Runnable {
                InvSeeGUI().updateInvSeeGUI(player, targetInventory)
            })
        })

    }

    @EventHandler
    fun updateTargetInvOnClick(event: InventoryClickEvent) {
        // Check if the clicked item or inventory is null
        val player = event.whoClicked as Player
        val view: InventoryView = event.whoClicked.openInventory
        val inventory = view.topInventory

        val target = Bukkit.getPlayer(InvSeeGUI().getTargetFromInv(inventory) ?: return) ?: return

        if (inventory == Main.guiInvSeeMap[target.uniqueId]) {
            // Run the update directly on the main thread
            Bukkit.getScheduler().runTask(Main.instance, Runnable {
                InvSeeGUI().updateTargetInv(target, inventory)
            })
        }
    }

    @EventHandler
    fun updateTargetInvOnDrag(event: InventoryDragEvent) {
        // Check if the clicked item or inventory is null
        val player = event.whoClicked as Player
        val view: InventoryView = event.whoClicked.openInventory
        val inventory = view.topInventory

        val target = Bukkit.getPlayer(InvSeeGUI().getTargetFromInv(inventory) ?: return) ?: return

        if (inventory == Main.guiInvSeeMap[target.uniqueId]) {
            // Run the update directly on the main thread
            Bukkit.getScheduler().runTask(Main.instance, Runnable {
                InvSeeGUI().updateTargetInv(target, inventory)
            })
        }
    }
}