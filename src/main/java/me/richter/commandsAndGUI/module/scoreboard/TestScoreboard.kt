package me.richter.commandsAndGUI.module.scoreboard

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.module.timer.countdown.Countdown
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TestScoreboard(player: Player?) : ScoreboardBuilder(player!!, "${ChatColor.DARK_PURPLE}${ChatColor.BOLD}  CommandsAndGUI  ") {
	private var taskEveryTick: BukkitRunnable? = null
	private var taskEverySec: BukkitRunnable? = null

	private var socialId = 0
	private val socialLinks = listOf(
		"${ChatColor.AQUA}twitter.com/DerBanko",
		"${ChatColor.DARK_PURPLE}twitch.tv/DerBanko",
		"${ChatColor.DARK_RED}youtube.com/DerBanko"
	)

	fun init() {
		runEverySec()
		runEveryTick()
	}

	override fun createScoreboard() {
		val nachricht = MiniMessage.miniMessage().deserialize("<rainbow>Hello world, isn't <underlined>MiniMessage</underlined> fun?</rainbow>")
		setScore(nachricht, 9)

		setScore("test", 8)
		setScore("", 7)
		setScore(MiniMessage.miniMessage().deserialize("<gray>Rank:"), 6)

		setScore(if (player.isOp) MiniMessage.miniMessage().deserialize("<red>Operator") else MiniMessage.miniMessage().deserialize("<green>Spieler"), 5)

		setScore("", 4)
		setScore("FlyTimePlaceholder", 3)
		setScore("", 2)
		setScore(MiniMessage.miniMessage().deserialize("<red>${player.address?.hostName ?: "Unbekannt"}"), 1)
		setScore("", 0)
	}

	override fun update() {
		// Dynamische Scoreboard Updates
	}

	// Optimierte runEverySec-Methode
	private fun runEverySec() {
		stopRunEverySec() // Sicherstellen, dass der vorherige Task gestoppt wird
		taskEverySec = object : BukkitRunnable() {
			override fun run() {
				setScore(socialLinks[socialId], 3)
				socialId = (socialId + 1) % socialLinks.size
				println("Scoreboard runEverySec() is running")
			}
		}.also {
			it.runTaskTimer(Main.instance, 20, 20)
		}
	}

	fun stopRunEverySec() {
		taskEverySec?.cancel()
	}

	// Optimierte runEveryTick-Methode
	private fun runEveryTick() {
		stopRunEveryTick() // Sicherstellen, dass der vorherige Task gestoppt wird
		taskEveryTick = object : BukkitRunnable() {
			override fun run() {
				val flyTime = Countdown().formatTime("flySoup.${player.uniqueId}")
				setScore(MiniMessage.miniMessage().deserialize("<white>Fly Time: <green>$flyTime"), 3)
			}
		}.also {
			it.runTaskTimer(Main.instance, 1, 1)
		}
	}

	fun stopRunEveryTick() {
		taskEveryTick?.cancel()
	}
}
