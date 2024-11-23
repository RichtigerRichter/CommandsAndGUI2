package me.richter.commandsAndGUI.module.gui.guis

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.module.gui.GUI
import me.richter.commandsAndGUI.module.gui.GUIData
import me.richter.commandsAndGUI.module.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*
import kotlin.math.ceil

class PlayerSelectGUI(guiData: GUIData) : GUI(guiData) {
    
    override fun getGUIName(): String {
        return "CAG > Players"
    }

    override fun getSlots(): Int {
        return 54
    }

    override fun setGUIItems() {
        val players = ArrayList(Bukkit.getServer().onlinePlayers)

        if (guiData.getPlayerSelectShowSelfFirst() && players.contains(guiData.getOwner())) {
            players.remove(guiData.getOwner())
            players.add(0, guiData.getOwner())
        }

        pageMax = ceil((players.size / maxItemsPerPage).toDouble()).toInt() - 1

        if (players.isNotEmpty()) {
            for (slot in 0 until maxItemsPerPage) {
                index = maxItemsPerPage * page + slot
                if (index >= players.size) break
                if (players[index] == null) break

                val playerItem = ItemStack(Material.PLAYER_HEAD, 1)
                val playerMeta = playerItem.itemMeta as SkullMeta

                playerMeta.setOwningPlayer(players[index])
                playerMeta.displayName(Text.miniMessage("<italic:false><yellow>${players[index]!!.name}"))
                val location = players[index]!!.location

                val playerLore: MutableList<Component> = mutableListOf()
                playerLore.add(Text.miniMessage("<italic:false><gray>Operator: <dark_gray>" + players[index].isOp))
                playerLore.add(Text.miniMessage("<italic:false><gray>Gamemode: <dark_gray>" + players[index].gameMode.name))
                playerLore.add(Text.miniMessage("<italic:false><gray>Health: <dark_gray>" + players[index].health + " / " + players[index].healthScale))
                playerLore.add(Text.miniMessage("<italic:false><gray>Food: <dark_gray>" + players[index].foodLevel))
                playerLore.add(Text.miniMessage("<italic:false><gray>Saturation: <dark_gray>" + players[index].saturation))
                playerLore.add(Text.miniMessage("<italic:false><gray>Remaining Air: <dark_gray>" + players[index].remainingAir))
                playerLore.add(Text.miniMessage("<italic:false><gray>Exp (Total Exp / Level): <dark_gray>" + players[index].totalExperience + " / " + players[index].level))
                playerLore.add(Text.miniMessage("<italic:false><gray>Location: <dark_gray>" + String.format("[X] %.3f", location.x) + String.format(" [Y] %.3f", location.y) + String.format(" [Z] %.3f", location.z)))
                playerLore.add(Text.miniMessage("<italic:false><gray>World: <dark_gray>" + players[index].world.name))
                playerLore.add(Text.miniMessage(""))
                playerLore.add(Text.miniMessage("<italic:false><gray>UUID: <dark_gray>" + players[index].uniqueId.toString()))
                playerLore.add(Text.miniMessage("<italic:false><gray>Entity ID: <dark_gray>" + players[index].entityId))
                playerLore.add(Text.miniMessage("<italic:false><gray>Address: <dark_gray>" + players[index].address.toString()))
                playerLore.add(Text.miniMessage("<italic:false><gray>Locale: <dark_gray>" + players[index].locale().displayName))
                playerLore.add(Text.miniMessage("<italic:false><gray>Item Pickup allowed: <dark_gray>" + players[index].canPickupItems))
                playerLore.add(Text.miniMessage("<italic:false><gray>Flight allowed: <dark_gray>" + players[index].allowFlight))
                playerLore.add(Text.miniMessage("<italic:false><gray>Fly Speed: <dark_gray>" + players[index].flySpeed))
                playerLore.add(Text.miniMessage("<italic:false><gray>Walk Speed: <dark_gray>" + players[index].walkSpeed))
                playerLore.add(Text.miniMessage("<italic:false><gray>Fire Ticks: <dark_gray>" + players[index].fireTicks))
                playerLore.add(Text.miniMessage("<italic:false><gray>Last Login: <dark_gray>" + players[index].lastLogin))
                playerLore.add(Text.miniMessage("<italic:false><gray>Last Seen: <dark_gray>" + players[index].lastSeen))
                playerLore.add(Text.miniMessage("<italic:false><gray>Time Alive (Ticks): <dark_gray>" + players[index].ticksLived))
                playerLore.add(Text.miniMessage(""))
                playerLore.add(Text.miniMessage("<italic:false><gray>Online: <dark_gray>" + players[index].isOnline))
                playerLore.add(Text.miniMessage("<italic:false><gray>Flying: <dark_gray>" + players[index].isFlying))
                playerLore.add(Text.miniMessage("<italic:false><gray>Sleeping: <dark_gray>" + players[index].isSleeping))
                playerLore.add(Text.miniMessage("<italic:false><gray>Sneaking: <dark_gray>" + players[index].isSneaking))
                playerLore.add(Text.miniMessage("<italic:false><gray>Sprinting: <dark_gray>" + players[index].isSprinting))
                playerLore.add(Text.miniMessage("<italic:false><gray>Can See You: <dark_gray>" + players[index].canSee(guiData.getOwner())))
                playerLore.add(Text.miniMessage("<italic:false><gray>Banned: <dark_gray>" + players[index].isBanned))
                playerLore.add(Text.miniMessage("<italic:false><gray>Blocking: <dark_gray>" + players[index].isBlocking))
                playerLore.add(Text.miniMessage("<italic:false><gray>Collidable: <dark_gray>" + players[index].isCollidable))
                playerLore.add(Text.miniMessage("<italic:false><gray>Conversing: <dark_gray>" + players[index].isConversing))
                playerLore.add(Text.miniMessage("<italic:false><gray>Dead: <dark_gray>" + players[index].isDead))
                playerLore.add(Text.miniMessage("<italic:false><gray>Gliding: <dark_gray>" + players[index].isGliding))
                playerLore.add(Text.miniMessage("<italic:false><gray>Glowing: <dark_gray>" + players[index].isGlowing))
                playerLore.add(Text.miniMessage("<italic:false><gray>Hand Raised: <dark_gray>" + players[index].isHandRaised))
                playerLore.add(Text.miniMessage("<italic:false><gray>Inside Vehicle: <dark_gray>" + players[index].isInsideVehicle))
                playerLore.add(Text.miniMessage("<italic:false><gray>Vehicle: <dark_gray>" + players[index].vehicle))
                playerLore.add(Text.miniMessage("<italic:false><gray>Invulnerable: <dark_gray>" + players[index].isInvulnerable))
                playerLore.add(Text.miniMessage("<italic:false><gray>Leashed: <dark_gray>" + players[index].isLeashed))
                playerLore.add(Text.miniMessage("<italic:false><gray>Silent: <dark_gray>" + players[index].isSilent))
                playerLore.add(Text.miniMessage("<italic:false><gray>Valid: <dark_gray>" + players[index].isValid))
                playerLore.add(Text.miniMessage("<italic:false><gray>Whitelisted: <dark_gray>" + players[index].isWhitelisted))
                playerLore.add(Text.miniMessage("<italic:false><gray>Fall Distance: <dark_gray>" + players[index].fallDistance))
                playerLore.add(Text.miniMessage("<italic:false><gray>Velocity: <dark_gray>" + players[index].velocity))
                playerMeta.lore(playerLore)

                playerMeta.persistentDataContainer.set(NamespacedKey(Main.instance, "me.richter.commandsandgui.gui.guis.playerselectgui.uuid"), PersistentDataType.STRING, players[index]!!.uniqueId.toString())
                playerItem.setItemMeta(playerMeta)

                inventory.addItem(playerItem)
            }
        }

        if (page > 0) {
            val lastPageItem = ItemStack(Material.ARROW, 1)
            val lastPageMeta = lastPageItem.itemMeta
            lastPageMeta.displayName(Text.miniMessage("<italic:false><white>Last Page"))
            lastPageItem.setItemMeta(lastPageMeta)
            inventory.setItem(getSlots() - 9, lastPageItem)
        }

        val goBackItem = ItemStack(Material.SPECTRAL_ARROW, 1)
        val goBackMeta = goBackItem.itemMeta
        goBackMeta.displayName(Text.miniMessage("<italic:false><white>Go Back"))
        goBackItem.setItemMeta(goBackMeta)
        inventory.setItem(getSlots() - 8, goBackItem)

        val filterItem = ItemStack(Material.HOPPER, 1)
        val filterMeta = filterItem.itemMeta
        filterMeta.displayName(Text.miniMessage("<italic:false><white>Filter"))
        val filterLore: MutableList<Component> = mutableListOf()
        filterLore.add(Text.miniMessage("<italic:false><gray>Filter the list by the properties of the players"))
        filterMeta.lore(filterLore)
        filterItem.setItemMeta(filterMeta)
        inventory.setItem(getSlots() - 7, filterItem)

        val infoItem = ItemStack(Material.PAPER, 1)
        val infoMeta = infoItem.itemMeta
        infoMeta.displayName(Text.miniMessage("<italic:false><white>Info"))
        val infoLore: MutableList<Component> = mutableListOf()
        infoLore.add(Text.miniMessage("<italic:false><gray>Change which properties you would like to see"))
        infoMeta.lore(infoLore)
        infoItem.setItemMeta(infoMeta)
        inventory.setItem(getSlots() - 6, infoItem)

        val sortItem = ItemStack(Material.PAPER, 1)
        val sortMeta = sortItem.itemMeta
        sortMeta.displayName(Text.miniMessage("<italic:false><white>Sorting"))
        val sortLore: MutableList<Component> = mutableListOf()
        sortLore.add(Text.miniMessage("<italic:false><gray>Set the property by which you would like to sort the players"))
        sortMeta.lore(sortLore)
        sortItem.setItemMeta(sortMeta)
        inventory.setItem(getSlots() - 5, sortItem)

        val selfItem = ItemStack(Material.PLAYER_HEAD, 1)
        val selfMeta = selfItem.itemMeta as SkullMeta
        selfMeta.setOwningPlayer(guiData.getOwner())
        selfMeta.displayName(Text.miniMessage("<italic:false><white>Show ${guiData.getOwner().name} first"))
        val selfLore: MutableList<Component> = mutableListOf()
        if (guiData.getPlayerSelectShowSelfFirst()) {
            selfLore.add(Text.miniMessage("<italic:false><blue>> Yes"))
            selfLore.add(Text.miniMessage("<italic:false><gray>  No"))
        } else {
            selfLore.add(Text.miniMessage("<italic:false><gray>  Yes"))
            selfLore.add(Text.miniMessage("<italic:false><blue>> No"))
        }
        selfMeta.lore(selfLore)
        selfItem.setItemMeta(selfMeta)
        inventory.setItem(getSlots() - 4, selfItem)

        if (page < pageMax) {
            val nextPageItem = ItemStack(Material.ARROW, 1)
            val nextPageMeta = nextPageItem.itemMeta
            nextPageMeta.displayName(Text.miniMessage("<italic:false><white>Next Page"))
            nextPageItem.setItemMeta(nextPageMeta)
            inventory.setItem(getSlots() - 1, nextPageItem)
        }

        for (i in getSlots() - 9 until getSlots()) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, getFillerGlass())
            }
        }
    }

    override fun handleGUI(event: InventoryClickEvent) {
        val uuid: UUID

        if (event.slot < getSlots() - 9) {
            val skullMeta = inventory.getItem(event.slot)!!.itemMeta as SkullMeta
            uuid = UUID.fromString(skullMeta.persistentDataContainer.get(NamespacedKey(Main.instance, "me.richter.commandsandgui.gui.guis.playerselectgui.uuid"), PersistentDataType.STRING))

            if (Bukkit.getPlayer(uuid) == null) {
                if (skullMeta.lore()!![0] == Text.miniMessage("<italic:false><red>This player could not be found and can therefore not be modified!")) return
                val offlinePlayerItem: ItemStack = inventory.getItem(event.slot)!!

                val offlinePlayerLore: MutableList<Component> = mutableListOf()
                offlinePlayerLore.add(Text.miniMessage("<italic:false><red>This player could not be found and can therefore not be modified!"))
                offlinePlayerLore.add(Text.miniMessage(""))
                offlinePlayerLore.addAll(skullMeta.lore()!!)
                skullMeta.lore(offlinePlayerLore)
                offlinePlayerItem.setItemMeta(skullMeta)
                inventory.setItem(event.slot, offlinePlayerItem)

            } else if (!Bukkit.getPlayer(uuid)!!.isOnline) {
                if (skullMeta.lore()!![0] == Text.miniMessage("<italic:false><red>This player could not be found and can therefore not be modified!")) return
                val offlinePlayerItem: ItemStack = inventory.getItem(event.slot)!!

                val offlinePlayerLore: MutableList<Component> = mutableListOf()
                offlinePlayerLore.add(Text.miniMessage("<italic:false><red>This player could not be found and can therefore not be modified!"))
                offlinePlayerLore.add(Text.miniMessage(""))
                offlinePlayerLore.addAll(skullMeta.lore()!!)
                skullMeta.lore(offlinePlayerLore)

                offlinePlayerItem.setItemMeta(skullMeta)
                inventory.setItem(event.slot, offlinePlayerItem)
            } else {
                guiData.setSelectedPlayer(Bukkit.getPlayer(uuid))
                OldMainGUI(guiData).open()
            }
        }

        if (event.slot == getSlots() - 9 && page > 0) {
            page -= 1
            super.open()
        }

        if (event.slot == getSlots() - 8) {
            MainGUI(guiData).open()
        }

        if (event.slot == getSlots() - 7) {
            event.whoClicked.sendMessage("todo")
        }

        if (event.slot == getSlots() - 4) {
            guiData.setPlayerSelectShowSelfFirst(!guiData.getPlayerSelectShowSelfFirst())
            super.open()
        }

        if (event.slot == getSlots() - 1 && pageMax > page) {
            page += 1
            super.open()
        }
    }
}