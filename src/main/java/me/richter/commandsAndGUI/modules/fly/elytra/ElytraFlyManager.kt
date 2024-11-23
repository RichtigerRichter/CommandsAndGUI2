package me.richter.commandsAndGUI.modules.fly.elytra

import me.richter.commandsAndGUI.files.PlayerDataFile
import org.bukkit.entity.Player

class ElytraFlyManager {
    fun startElytraFly(player: Player) {
        PlayerDataFile().setAllowElytralessElytraFlight(player, true)
        player.isGliding = true
    }

}