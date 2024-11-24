package me.richter.commandsAndGUI.module.placeholders

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import org.bukkit.OfflinePlayer

class PAPI: PlaceholderExpansion() {
	val plugin = Main.instance
	override fun getIdentifier(): String {
		return "cag"
	}

	override fun getAuthor(): String {
		return "RichtigerRichter"
	}

	override fun getVersion(): String {
		return plugin.description.version
	}

	override fun persist(): Boolean {
		return true
	}

	override fun onRequest(player: OfflinePlayer?, params: String): String? {
		//https://wiki.placeholderapi.com/developers/creating-a-placeholderexpansion/#making-an-internal-expansion
		//animation placeholder %animation:name%
		val animationPlaceholder = Regex("(animation:[^%]+)").find(params)?.value
		val animationName = Regex("animation:([^%]+)").find(params)?.groupValues?.get(1)
		if (animationPlaceholder != null) {
			return Placeholder().getCurrentAnimation(animationName)
		}

		//fly time placeholder %fly_time%
		if (params == "fly_time") {
			if (player != null) {
				return Countdown().formatTime("flySoup.${player.uniqueId}")
			}
		}


		return null
	}
}