package me.richter.commandsAndGUI.module.timer.timer

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player


class Timer {

    fun sendActionBar() {
        for (player: Player in Bukkit.getOnlinePlayers()) {
            val seconds = timeList()["s"] ?: return
            val minutes = timeList()["m"] ?: return
            val hours = timeList()["h"] ?: return

            val sec = if (seconds < 10) "0$seconds" else "$seconds"
            val min = if (minutes < 10) "0$minutes" else "$minutes"
            val h = if (hours < 10) "0$hours" else "$hours"

            if (!TimerFile().getRunning()) {
                //player.sendActionBar(Component.text("§c§lTimer ist pausiert"))
                continue
            }

            //player.sendActionBar(Component.text("§l§g${TimerFile().getTime()}"))
            player.sendActionBar(Component.text("§l§g$h:$min:$sec"))


        }
    }

    fun timeList(): Map<String, Int> {
        val time = TimerFile().getTime()
        val h = time / 3600
        val m = (time % 3600) / 60
        val s = time % 60
        return mapOf(Pair("h", h), Pair("m", m), Pair("s", s))
    }

    fun runningEverySec() {
        sendActionBar()

        if (!TimerFile().getRunning()) {
            return
        }

        TimerFile().setTime(TimerFile().getTime() + 1)
    }

}
