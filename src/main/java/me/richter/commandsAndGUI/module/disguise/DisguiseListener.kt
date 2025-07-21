package me.richter.commandsAndGUI.module.disguise
/*
import dev.iiahmed.disguise.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


class DisguiseListener(private val plugin: JavaPlugin) : Listener {
	private val provider = DisguiseManager.getProvider()

	init {
		DisguiseManager.setPlugin(plugin)
	}

	@EventHandler
	fun onJoin(event: PlayerJoinEvent) {
		val player: Player = event.player
		val disguise = Disguise.builder()
			// the boolean is whether this is a fake nickname or not
			.setName("BillBobbyBob", false)
			// you could as well use Disguise.Builder#setSkin(textures, signature)
			// or even Disguise.Builder#setSkin(uuid, skinAPI)
			// it's recommended to run this async since #setSkin from an online API will block the mainthread
			.setSkin("example-name", SkinAPI.MOJANG)
			// this will change the player into a zombie for others only
			.setEntityType(EntityType.ZOMBIE)
			.build()
		provider.disguise(player, disguise)
	}
}
 */