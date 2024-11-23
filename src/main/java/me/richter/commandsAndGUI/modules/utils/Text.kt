package me.richter.commandsAndGUI.modules.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object Text {
    fun miniMessage(text: String): Component {
        return MiniMessage.miniMessage().deserialize(LegacyColorCodesToMiniMassageTags.mach(text))
    }
}