package me.richter.commandsAndGUI.module.utils

import me.clip.placeholderapi.PlaceholderAPI
import me.richter.commandsAndGUI.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

object Text {
	fun miniMessage(text: String): Component {
		return MiniMessage.miniMessage().deserialize(LegacyColorCodesToMiniMassageTags.mach(text))
	}

	fun miniPapi(player: Player?, text: String): Component {
		if (Main.isPapiEnabled) {
			return MiniMessage.miniMessage().deserialize(LegacyColorCodesToMiniMassageTags.mach(PlaceholderAPI.setPlaceholders(player, text)))
		}
		return MiniMessage.miniMessage().deserialize(LegacyColorCodesToMiniMassageTags.mach(text))
	}
}