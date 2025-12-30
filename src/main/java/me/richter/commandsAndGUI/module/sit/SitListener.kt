package me.richter.commandsAndGUI.module.sit

import me.richter.commandsAndGUI.Main
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.Pose
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPoseChangeEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class SitListener : Listener {
    @EventHandler
    fun unsit(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (!Main.sitMap.containsKey(player)) return
        val chair = Main.sitMap[player] as Entity
        val position = chair.location.add(0.0, 0.0, 0.0)
        //player.teleport(position)
        chair.remove()
        Main.sitMap.remove(player)
    }

    /*
    @EventHandler
    fun onPlayerMove(event: EntityPoseChangeEvent) {
        val player = event.entity as Player ?: return
        if (!Main.layMap.contains(player)) return

        //if (player.pose == Pose.SLEEPING && !(player.isSleeping || player.isDeeplySleeping)) {
        player.pose = Pose.SITTING
        //}
    }
     */
}