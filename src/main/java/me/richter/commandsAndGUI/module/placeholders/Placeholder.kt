package me.richter.commandsAndGUI.module.placeholders

import me.clip.placeholderapi.PlaceholderAPI
import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.AnimationsFile
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import me.richter.commandsAndGUI.module.utils.Text
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

class Placeholder {
	fun getCurrentFlytime(player: Player): String {

		return Countdown().formatTime("flySoup.${player.uniqueId}")
	}

	fun getCurrentAnimation(name: String?) : String {
		val index = Main.animationsCount[name] ?: return "Main.animationsCount[$name] is null"
		return AnimationsFile().getAnimationList(name)[index]
	}

	fun animationUpdateTick() {
		if (!Main.animationsCount.containsKey("count")) { Main.animationsCount["count"] = 0 }
		if (Main.animationsCount["count"]!! >= 630720000) {
			println("\u001Bc")
			println("Herzlich Glückwunsch der Server läuft ununterbrochen seit einem Jahr")
			Main.instance.server.scheduler.runTaskLater(Main.instance, Runnable {
				println("\u001Bc")
				println("zeit das zu ändern :)")
			}, 100)
			Main.instance.server.shutdown()
		}

		for (animationName in AnimationsFile().aniStringMap.keys) {
			val changeInterval = AnimationsFile().aniIntervalMap[animationName]?.div(50) ?: return
			if (Main.animationsCount["count"]!!.mod(changeInterval) != 0) return

			val animationList =	AnimationsFile().getAnimationList(animationName)
			if (!Main.animationsCount.containsKey(animationName)) { Main.animationsCount[animationName] = 0 }
			if (Main.animationsCount[animationName]!! >= animationList.size-1) { Main.animationsCount[animationName] = 0 }


			Main.animationsCount[animationName] = Main.animationsCount[animationName]!! + 1
		}

		Main.animationsCount["count"]
	}



	fun replacePlaceholders(line: String, player: Player) : Component {

		val animationPlaceholder = Regex("(%Animation:[^%]+%)").find(line)?.value ?: "TestScoreboard.kt:91 null"
		val animationName = Regex("%Animation:([^%]+)%").find(line)?.groupValues?.get(1) ?: "TestScoreboard.kt:92 null"
		var text = line

		val papiString = PlaceholderAPI.setPlaceholders(player, text)


		return Text.miniMessage(papiString)
	}
}