//Todo permission for untrackable player auch für andere sachen mehr sowas

package me.richter.commandsAndGUI.module.playerTracker

import me.richter.commandsAndGUI.Main
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.UUID

class TrackCommand : CommandExecutor, TabCompleter {
	override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
		if (sender !is Player) return true

		if (args.isEmpty()) return false // Check if any arguments are provided

		val player = sender

		when (args[1]) {
			"track" -> {
				if (args.size < 4) return false // Ensure we have enough arguments

				val name = args[2]

				if (args[3] != "location") {
					val target = Bukkit.getPlayer(args[3]) ?: return false
					TrackManager().endTracking(player, name)
					TrackManager().startTracking(player, target, name)
				} else {
					// Parse location with support for relative positions
					val x = if (args[4].contains('~')) player.location.x + args[4].replace("~", "").toDouble() else args[4].toDouble()
					val y = if (args[5].contains('~')) player.location.y + args[5].replace("~", "").toDouble() else args[5].toDouble()
					val z = if (args[6].contains('~')) player.location.z + args[6].replace("~", "").toDouble() else args[6].toDouble()
					val targetLocation = Location(player.world, x, y, z)

					TrackManager().endTracking(player, name)
					TrackManager().startTracking(player, targetLocation, name)
				}
			}

			"delete" -> {
				val name = args.getOrNull(2) ?: return false
				TrackManager().endTracking(player, name)
			}

			"list" -> {
				sender.sendMessage(TrackManager().trackList(player).toString())
			}

			else -> return false // Invalid subcommand
		}

		return true
	}

	override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
		if (sender !is Player) return mutableListOf()

		val completions = mutableListOf<String>()

		when (args.size) {
			1 -> {
				completions.add("[TrackingPlayer]")
				completions.addAll(Bukkit.getOnlinePlayers().map { it.name })
			}
			2 -> {
				completions.addAll(listOf("delete", "track", "list"))
			}
			3 -> {
				if (args[1] == "track") {
					completions.add("[NameOfTracker]")
				}
				if (args[1] == "delete") {
					val player = Bukkit.getPlayer(args[0]) ?: sender
					completions.addAll(Main.trackMap[player.uniqueId]?.keys ?: emptySet())
				}
			}
			4 -> {
				if (args[1] == "track") {
					completions.add("location")
					completions.addAll(Bukkit.getOnlinePlayers().map { it.name })
				}
			}
			5 -> {
				if (args[3] == "location" && args[1] == "track") {
					completions.add("[X]")
					val hitBlockX = sender.rayTraceBlocks(3.0)?.hitBlock?.x?.toString() ?: sender.location.blockX.toString()
					completions.add(hitBlockX)
				}
			}
			6 -> {
				if (args[3] == "location" && args[1] == "track") {
					completions.add("[Y]")
					val hitBlockY = sender.rayTraceBlocks(3.0)?.hitBlock?.y?.toString() ?: sender.location.blockY.toString()
					completions.add(hitBlockY)
				}
			}
			7 -> {
				if (args[3] == "location" && args[1] == "track") {
					completions.add("[Z]")
					val hitBlockZ = sender.rayTraceBlocks(3.0)?.hitBlock?.z?.toString() ?: sender.location.blockZ.toString()
					completions.add(hitBlockZ)
				}
			}
		}

		return completions.filter { it.startsWith(args.last(), ignoreCase = true) }.toMutableList()
	}
}
