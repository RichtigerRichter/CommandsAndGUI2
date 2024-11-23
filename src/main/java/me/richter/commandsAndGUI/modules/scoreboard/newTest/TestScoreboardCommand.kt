package me.richter.commandsAndGUI.modules.scoreboard.newTest

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

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
        objective.getScore("siis1").score = 1
        objective.getScore("siis2").score = 2
        objective.getScore("siis3").score = 3
        objective.getScore("siis4").score = 4
        objective.getScore("siis5").score = 5
        objective.getScore("siis6").score = 6
        objective.getScore("siis7").score = 7
        objective.getScore("siis8").score = 8
        objective.getScore("siis9").score = 9
        objective.getScore("siis10").score = 10
        objective.getScore("siis11").score = 11
        objective.getScore("siis12").score = 12
        objective.getScore("siis13").score = 13
        objective.getScore("siis14").score = 14
        objective.getScore("siis15").score = 15

        // Assign the scoreboard to the player
        player.scoreboard = scoreboard

        return true
    }
}