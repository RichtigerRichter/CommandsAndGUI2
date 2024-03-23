package me.richter.commandsAndGUI.module.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.profile.PlayerProfile
import org.bukkit.profile.PlayerTextures
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class Head {
	private fun getProfile(url: String): PlayerProfile {
		val randomUUID: UUID = UUID.fromString("d5290e03-c476-4960-9685-9ab37286bca9") // We reuse the same "random" UUID all the time

		val profile: PlayerProfile = Bukkit.createPlayerProfile(randomUUID) // Get a new player profile
		val textures: PlayerTextures = profile.textures
		val urlObject: URL
		try {
			urlObject = URL(url)
		} catch (exception: MalformedURLException) {
			throw RuntimeException("Invalid URL", exception)
		}
		textures.skin = urlObject // Set the skin of the player profile to the URL
		profile.setTextures(textures) // Set the textures back to the profile
		return profile
	}

	fun getHead(url: String): ItemStack {
		val profile: PlayerProfile = getProfile(url)
		val head = ItemStack(Material.PLAYER_HEAD)
		val meta = head.itemMeta as SkullMeta
		meta.ownerProfile = profile // Set the owning player of the head to the player profile
		head.setItemMeta(meta)
		return head
	}
}