package me.richter.commandsAndGUI.module.scoreboard.newTest

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

class ScoreboardManager {
    fun newScoreboard(name: String, player: Player) {
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
            Component.text(name)
        )

        objective.displaySlot = DisplaySlot.SIDEBAR

        // Set a score on the scoreboard
        objective.getScore("siis").score = 1
        objective.getScore("siis2").score = 2


        // Assign the scoreboard to the player
        player.scoreboard = scoreboard
    }

    fun hideScorboard() {

    }

    fun updateScoreboard() {

    }

    fun setLine(objective: Objective, text: String, line: Int) {
        objective.getScore(text).score = line
    }

}