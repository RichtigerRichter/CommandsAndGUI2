package me.richter.commandsAndGUI.modules.customCrafting.invisibleItemFrames

import me.richter.commandsAndGUI.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.hanging.HangingBreakEvent
import org.bukkit.event.hanging.HangingPlaceEvent

class EntityPlaceListener : Listener {
    @EventHandler
    fun itemFramePlaceEvent(event: HangingPlaceEvent) {
        if (event.entity.type != EntityType.ITEM_FRAME && event.entity.type != EntityType.GLOW_ITEM_FRAME) return
        val item = event.itemStack ?: return

        if (ItemBuilder().getCustomTagValue(item, "cag.item") == "invisibleItemFrame") {
            val entity = event.entity as ItemFrame
            entity.isVisible = false
        }


    }


    //TODO das macht basicly nix: fixen
    @EventHandler
    fun itemFrameRemoveEvent(event: HangingBreakEvent) {
        if (event.entity.type != EntityType.ITEM_FRAME || event.entity.type != EntityType.GLOW_ITEM_FRAME) return
        val entity = event.entity as ItemFrame

        if (!entity.isVisible) {
            (event.entity as ItemFrame).setItem(
                ItemBuilder().itemBuilder(
                    Material.ITEM_FRAME,
                    "Invisible ItemFrame",
                    "",
                    "cag.item",
                    "invisibleItemFrame"
                )
            )
        }


    }

}