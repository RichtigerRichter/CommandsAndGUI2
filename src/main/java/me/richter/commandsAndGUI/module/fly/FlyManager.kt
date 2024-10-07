package me.richter.commandsAndGUI.module.fly

import me.richter.commandsAndGUI.module.timer.countdown.CountdownFile
import me.richter.commandsAndGUI.module.utils.RegexStrings
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.*

class FlyManager {
	fun addTime(player: Player, time: Int) {
		CountdownFile().setTime("flySoup."+player.uniqueId, CountdownFile().getTime("flySoup."+player.uniqueId) + time)
	}

	fun getTime(player: Player): Int {
		return CountdownFile().getTime("flySoup."+player.uniqueId)
	}

	fun setTime(player: Player, time: Int) {
		CountdownFile().setTime("flySoup."+player.uniqueId, time)
	}

	private fun removePlayersWithNoTimeFromConfig() {
		val players = getAllFlyPlayersInCountdownConfigAsPlayers()

		for (player in players) {
			if(getTime(player) >= 0) continue
			CountdownFile().remove("flySoup." + player.uniqueId)
		}
		removeAllInvalidUuidsFromConfig()
	}

	private fun removeAllInvalidUuidsFromConfig() {
		val config = CountdownFile().getConfig()
		val flySoupSection = config.getConfigurationSection("flySoup")
		val uuids: MutableSet<String> = flySoupSection?.getKeys(false) ?: return

		for (uuid in uuids) {
			if (!Regex(RegexStrings.UUID).matches(uuid)) CountdownFile().remove("flySoup.$uuid")
		}
	}

	private fun getAllFlyPlayersInCountdownConfigAsOfflinePlayers(): MutableList<OfflinePlayer> {
		val config = CountdownFile().getConfig()
		val flySoupSection = config.getConfigurationSection("flySoup")

		val players: MutableList<OfflinePlayer> = mutableListOf()

		val uuids: MutableSet<String> = flySoupSection?.getKeys(false) ?: return players


		for (uuid in uuids) {
			if (!Regex(RegexStrings.UUID).matches(uuid)) {
				CountdownFile().remove("flySoup.$uuid")
				continue
			}
			val player = Bukkit.getOfflinePlayer(UUID.fromString(uuid))
			Bukkit.getPlayer(UUID.fromString(uuid))
			players.add(player)
		}

		return players
	}

	private fun getAllFlyPlayersInCountdownConfigAsPlayers(): MutableList<Player> {
		val config = CountdownFile().getConfig()

		val players: MutableList<Player> = mutableListOf()

		for (player in getAllFlyPlayersInCountdownConfigAsOfflinePlayers()) {
			if (player.isOnline) { players.add(player as Player) }
		}

		return players
	}

	fun countdownTick() {
		val players = getAllFlyPlayersInCountdownConfigAsPlayers()

		for (player in players) {
			if (!player.isOnline) return
			if (player.gameMode == GameMode.CREATIVE || player.gameMode == GameMode.SPECTATOR) return
			if (getTime(player) > 0) {
				player.allowFlight = true
			} else {
				player.allowFlight = false
				player.isFlying = false
			}

			setTime(player, getTime(player) - 1)
			removePlayersWithNoTimeFromConfig()
		}

	}
}