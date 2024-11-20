package me.richter.commandsAndGUI.module.fly.elytra

import me.richter.commandsAndGUI.files.PlayerDataFile
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityToggleGlideEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class ElytraFlyListener : Listener {
    @EventHandler
    fun elytraFlyEvent(event: EntityToggleGlideEvent) {
        val player = event.entity
        if (player !is Player) return

        if (!PlayerDataFile().getAllowElytralessElytraFlight(player)) return //wenn nicht elytra fliegen darf

        if (event.isGliding) return //wenn normal zum Gleiten wechseln
        val chestplate = player.inventory.chestplate
        if (chestplate != null) {
            if (chestplate.type == Material.ELYTRA) {
                PlayerDataFile().setAllowElytralessElytraFlight(player, false)
                return
            } //wenn elytra anhat
        }

        if (player.isOnGround) {
            PlayerDataFile().setAllowElytralessElytraFlight(player, false)
            return
        } //wenn auf dem boden

        event.isCancelled = true

    }

    @EventHandler
    fun playerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player
        if (event.action != Action.RIGHT_CLICK_AIR) return
        if (event.item == null) return
        if (event.item!!.type != Material.FEATHER) return

        ElytraFlyManager().startElytraFly(player)
        if (event.item!!.itemMeta.displayName == "fireworkBoost") {
            player.fireworkBoost(ItemStack(Material.FIREWORK_ROCKET))
            println("fireworkBoost")

        }

        event.isCancelled = true
    }

}