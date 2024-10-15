package me.richter.commandsAndGUI.module.playerTracker

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.items.ItemBuilder
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.CompassMeta
import org.bukkit.util.Vector
import java.util.*
import kotlin.math.atan2

class TrackManager {
	//BOSSBAR HANDLING
	fun updateBossbarTick() {
		for (uuid in Main.trackMap.keys) {
			val player = Bukkit.getPlayer(uuid) ?: continue
			val userMap = Main.trackMap[uuid] ?: continue

			for ((name, trackerMap) in userMap) {
				val locationOrPlayer = trackerMap[1]
				val location = when (locationOrPlayer) {
					is Location -> locationOrPlayer
					is Player -> locationOrPlayer.location
					else -> null
				}

				if (location == null) continue

				val bossbarOrCompass = trackerMap[0]
				if (bossbarOrCompass is BossBar) {
					val bossbar = bossbarOrCompass
					updateBossBarColor(bossbar, location)
					if (location.world != player.world) {
						bossbar.name(Component.text("Target is in ${getWorldEnvironmentName(location.world.environment)}"))
						continue
					}
					val angle = getRelativeYaw(player, location)
					val compassString = angleToCompassString(angle, 29)
					bossbar.name(Component.text(compassString))
					player.showBossBar(bossbar)
				}

				if (bossbarOrCompass is Item) {
					continue
				}
			}
		}
	}

	private fun updateBossBarColor(bossbar: BossBar, target: Location) {
		val color = when (target.world.environment) {
			World.Environment.NORMAL -> BossBar.Color.GREEN
			World.Environment.NETHER -> BossBar.Color.RED
			World.Environment.THE_END -> BossBar.Color.PURPLE
			World.Environment.CUSTOM -> BossBar.Color.BLUE
		}
		bossbar.color(color)
	}

	private fun getWorldEnvironmentName(environment: World.Environment): String {
		return when (environment) {
			World.Environment.NORMAL -> "the Overworld"
			World.Environment.NETHER -> "the Nether"
			World.Environment.THE_END -> "the End"
			World.Environment.CUSTOM -> "a custom World"
		}
	}

	fun startTrackingBossbar(player: Player, targetEntity: Entity, name: String) {
		// Erstelle eine neue Bossbar oder verwende eine vorhandene
		val bossBar = BossBar.bossBar(
			Component.text("Tracking ${targetEntity.name}"),
			1f,
			BossBar.Color.BLUE,
			BossBar.Overlay.PROGRESS
		)
		player.showBossBar(bossBar)

		// Füge den neuen Tracker zur Map hinzu oder update den vorhandenen

		val playerMap = Main.trackMap[player.uniqueId] ?: mutableMapOf(Pair(name, mutableListOf(bossBar, targetEntity)))
		Main.trackMap[player.uniqueId] = playerMap
		playerMap[name] = mutableListOf(bossBar, targetEntity)

	}

	fun startTrackingBossbar(player: Player, targetLocation: Location, name: String) {
		// Erstelle eine neue Bossbar oder verwende eine vorhandene
		val bossBar = BossBar.bossBar(
			Component.text("Tracking ${targetLocation.x} ${targetLocation.y} ${targetLocation.z}"),
			1f,
			BossBar.Color.BLUE,
			BossBar.Overlay.PROGRESS
		)
		player.showBossBar(bossBar)

		// Füge den neuen Tracker zur Map hinzu oder update den vorhandenen
		val playerMap = Main.trackMap[player.uniqueId] ?: mutableMapOf(Pair(name, mutableListOf(bossBar, targetLocation)))
		Main.trackMap[player.uniqueId] = playerMap
		playerMap[name] = mutableListOf(bossBar, targetLocation)

	}

	//COMPASS HANDLING
	fun updateCompassTick() {
		for (player in Bukkit.getOnlinePlayers()) {
			for (unsureItem in player.inventory.contents) {
				val item = unsureItem ?: continue
				val tag = ItemBuilder().getCustomTagValue(item, "CAG.item.trackingCompass.entity") ?: continue
				val entityUUID = UUID.fromString(tag)
				val targetEntity = Bukkit.getEntity(entityUUID) ?: continue
				val meta = item.itemMeta as CompassMeta
				meta.lodestone = targetEntity.location
				item.itemMeta = meta
			}
		}
	}

	fun giveLocationCompass(player: Player, location: Location, name: String) {
		val compass = ItemBuilder().itemBuilder(Material.COMPASS, "Tracking: $name", "", "CAG.item.trackingCompass.location", name)
		val meta = compass.itemMeta as CompassMeta
		meta.isLodestoneTracked = false
		meta.lodestone = location
		compass.itemMeta = meta
		println(compass.toString())
		player.inventory.addItem(compass)
	}

	fun giveEntityCompass(player: Player, entity: Entity, name: String) {
		val compass = ItemBuilder().itemBuilder(Material.COMPASS, "Tracking: $name", "", "CAG.item.trackingCompass.entity", entity.uniqueId.toString())
		val meta = compass.itemMeta as CompassMeta
		meta.isLodestoneTracked = false
		meta.lodestone = entity.location
		compass.itemMeta = meta
		println(compass.toString())
		player.inventory.addItem(compass)
	}


	//ALLGEMEINES HANDLING
	fun endTracking(player: Player, name: String) {
		val playerMap = Main.trackMap[player.uniqueId] ?: return
		val trackerList = playerMap[name] ?: return
		if (trackerList[0] is BossBar) {
			player.hideBossBar(trackerList[0] as BossBar)
		}

		playerMap.remove(name)
	}

	fun trackList(player: Player) : MutableSet<String> {
		val playerMap = Main.trackMap[player.uniqueId] ?: return mutableSetOf("None")

		return  playerMap.keys
	}

	private fun angleToCompassString(angle: Float, length: Int): String {
		val fov = 180
		val pos = (angle / (fov/2) * (length/2)).toInt()+(length/2)
		//-90 .. 0 .. 90
		val compassList: MutableList<Char> = mutableListOf()
		for (i in 0..<length) {
			compassList.add(i, '-')
		}

		if (pos in 0..<length) {
			compassList[pos] = '#'
			return compassList.joinToString("")
		}

		if (angle<-fov/2) {
			compassList[0] = '←'
			return compassList.joinToString("")
		}

		if (angle>fov/2) {
			compassList[length-1] = '→'
			return compassList.joinToString("")
		}

		return compassList.joinToString("")
	}

	private fun getRelativeYaw(player1: Player, trackedLocation: Location): Float {
		// Get the locations of both players
		val loc1 = player1.location
		val loc2 = trackedLocation

		// Calculate the difference in positions
		val direction: Vector = loc2.toVector().subtract(loc1.toVector())

		// Calculate the yaw needed to face player2 from player1
		val angleToPlayer2 = atan2(direction.z, direction.x)

		// Convert the angle to degrees (Minecraft uses degrees)
		var targetYaw = Math.toDegrees(angleToPlayer2).toFloat() - 90

		// Normalize the yaw (in case it's negative or greater than 360)
		if (targetYaw < 0) targetYaw += 360

		// Get player1's yaw
		val player1Yaw = loc1.yaw

		// Calculate the yaw relative to player1's current yaw
		var relativeYaw = targetYaw - player1Yaw

		// Normalize the relative yaw between -180 and 180 degrees
		if (relativeYaw > 180) relativeYaw -= 360
		if (relativeYaw < -180) relativeYaw += 360

		return relativeYaw
	}
}
