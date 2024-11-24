package me.richter.commandsAndGUI.module.timer.countdown

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player


class Countdown {

	fun sendActionBar(name: String) {
		for (player: Player in Bukkit.getOnlinePlayers()) {

			if (!CountdownFile().getRunning(name)) {
				//player.sendActionBar(Component.text("§c§lTimer ist pausiert"))
				continue
			}

			player.sendActionBar(Component.text("§l§g"+formatTime(name)))

		}
	}

	private fun timeList(name: String): Map<String, Int> {
		val time = CountdownFile().getTime(name)
		val h = time / 3600
		val m = (time % 3600) / 60
		val s = time % 60
		return mapOf(Pair("h", h), Pair("m", m), Pair("s", s))
	}

	fun formatTime(name: String): String {
		val seconds = timeList(name)["s"] ?: return "Error"
		val minutes = timeList(name)["m"] ?: return "Error"
		val hours = timeList(name)["h"] ?: return "Error"

		val sec = if (seconds < 10) "0$seconds" else "$seconds"
		val min = if (minutes < 10) "0$minutes" else "$minutes"
		val h = if (hours < 10) "0$hours" else "$hours"

		return  "$h:$min:$sec"
	}

	fun runningEverySec() {

		for (name in CountdownFile().getAllTopGroupsExceptFlySoup()) {
			if (!CountdownFile().getRunning(name)) { return }
			if (CountdownFile().getTime(name) <= 0) { return }

			CountdownFile().setTime(name, CountdownFile().getTime(name) - 1)
			//sendActionBar(name)
		}
	}

}