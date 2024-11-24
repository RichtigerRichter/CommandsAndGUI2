package me.richter.commandsAndGUI.module.scoreboard

import me.clip.placeholderapi.PlaceholderAPI
import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.AnimationsFile
import me.richter.commandsAndGUI.files.ScoreboardFile
import me.richter.commandsAndGUI.module.placeholders.Placeholder
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import me.richter.commandsAndGUI.module.utils.LegacyColorCodesToMiniMassageTags
import me.richter.commandsAndGUI.module.utils.Text
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TestScoreboard(player: Player) : ScoreboardBuilder(player, ScoreboardFile().scoreboardDisplayNameMap["commandsAndGUI"]) {
	private var taskEveryTick: BukkitRunnable? = null
	private var taskEverySec: BukkitRunnable? = null

	fun init() {
		runEverySecTask()
		runEveryTickTask()
	}


	override fun createScoreboard() {

		val scoreList = ScoreboardFile().scoreboardListMap["commandsAndGUI"] ?: return
		println(scoreList)

		for (score in 0..14) {

			if (score > scoreList.size-1) return
			setScore(scoreList[score], score)
		}
	}

	fun runEverySec() {

	}


	fun runEveryTick() {
		updatePlaceholders()

	}


	fun updatePlaceholders() {
		val scoreList = ScoreboardFile().scoreboardListMap["commandsAndGUI"] ?: return

		for (score in 0..14) {
			if (score > scoreList.size-1) return
			val line = Text.miniPapi(player, scoreList[score])

			setScore(line, score)
		}
	}


	override fun update() {
		// Dynamische Scoreboard Updates
	}

	private fun runEverySecTask() {
		stopRunEverySec() // Sicherstellen, dass der vorherige Task gestoppt wird
		taskEverySec = object : BukkitRunnable() {
			override fun run() {
				runEverySec()
			}
		}.also {
			it.runTaskTimer(Main.instance, 20, 20)
		}
	}

	fun stopRunEverySec() {
		taskEverySec?.cancel()
	}

	private fun runEveryTickTask() {
		stopRunEveryTick() // Sicherstellen, dass der vorherige Task gestoppt wird
		taskEveryTick = object : BukkitRunnable() {
			override fun run() {
				runEveryTick()
			}
		}.also {
			it.runTaskTimer(Main.instance, 1, 1)
		}
	}

	fun stopRunEveryTick() {
		taskEveryTick?.cancel()
	}
}
