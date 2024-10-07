package me.richter.commandsAndGUI.module.scoreboard

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Objective

class TestScoreboardCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        val player: Player = sender

        // If the player's scoreboard is the main one, assign a new scoreboard
        if (player.scoreboard == Bukkit.getScoreboardManager().mainScoreboard) {
            player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        }

        val scoreboard: Scoreboard = player.scoreboard
        var objective: Objective? = scoreboard.getObjective("display")

        // Remove old objective if it exists
        objective?.unregister()

        // Register a new objective using the Component API for display name
        objective = scoreboard.registerNewObjective(
            "display",
            Criteria.DUMMY,
            Component.text("DISPLAY NAME")  // Updated to use Component API
        )
        objective.displaySlot = DisplaySlot.SIDEBAR

        // Set a score on the scoreboard
        objective.getScore("siis").score = 1
        objective.getScore("siis2").score = 2


        // Assign the scoreboard to the player
        player.scoreboard = scoreboard

        return true
    }
}