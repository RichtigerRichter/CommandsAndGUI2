package me.richter.commandsAndGUI.module.utils

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*


class Head {
    fun getSkullWithCustomTexture(url: String): ItemStack {
        // Create a new ItemStack of PLAYER_HEAD
        val head = ItemStack(Material.PLAYER_HEAD)
        val meta = head.itemMeta as SkullMeta

        // Create a new GameProfile with a "random" UUID
        val profile = GameProfile(UUID.fromString("d5290e03-c476-4960-9685-9ab37286bca9"), "RichtigerRichter")

        // Encode the URL into Base64 and add it as a property to the GameProfile
        val encodedData =
            Base64.getEncoder().encodeToString("{\"textures\":{\"SKIN\":{\"url\":\"$url\"}}}".toByteArray())
        profile.properties.put("textures", Property("textures", encodedData))

        // Use reflection to set the profile field in the SkullMeta
        val profileField = meta.javaClass.getDeclaredField("profile")
        profileField.isAccessible = true
        profileField.set(meta, profile)

        //set name if no texture was set
        if (url == "http://textures.minecraft.net/texture/5c8817ee8e9c2c2bf767487737e0a60a5e09b725138e14daa57480a03f1766d8") {
            meta.itemName(Component.text("§kheheEasterEgg"))
        }

        // Set the modified meta back to the item
        head.itemMeta = meta

        return head
    }

    /*
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
     */

}