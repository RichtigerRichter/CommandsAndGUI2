package me.richter.commandsAndGUI.module.scoreboard

import io.papermc.paper.scoreboard.numbers.NumberFormat
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

abstract class ScoreboardBuilder(protected val player: Player, displayName: String?) {
    private val scoreboard: Scoreboard
    private val objective: Objective

    init {
        if (player.scoreboard == Bukkit.getScoreboardManager().mainScoreboard) {
            player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        }

        this.scoreboard = player.scoreboard

        if (scoreboard.getObjective("display") != null) {
            scoreboard.getObjective("display")!!.unregister()
        }

        this.objective = scoreboard.registerNewObjective(
            "display", "dummy",
            displayName!!
        )
        objective.displaySlot = DisplaySlot.SIDEBAR

        createScoreboard()
    }

    abstract fun createScoreboard()

    abstract fun update()

    fun setDisplayName(displayName: String?) {
        objective.displayName = displayName!!
    }

    fun setScore(content: String, score: Int) {
        val team = getTeamByScore(score) ?: return

        team.prefix(Component.text(content))
        showScore(score)
    }

    fun setScore(content: Component, score: Int) {
        val team = getTeamByScore(score) ?: return

        team.prefix(content)
        showScore(score)
    }

    fun removeScore(score: Int) {
        hideScore(score)
    }

    private fun getEntryNameByScore(score: Int): EntryName? {
        for (name in EntryName.entries) {
            if (score == name.entry) {
                return name
            }
        }

        return null
    }

    private fun getTeamByScore(score: Int): Team? {
        val name = getEntryNameByScore(score) ?: return null

        var team = scoreboard.getEntryTeam(name.entryName)

        if (team != null) {
            return team
        }

        team = scoreboard.registerNewTeam(name.name)
        team.addEntry(name.entryName)
        return team
    }

    private fun showScore(score: Int) {
        val name = getEntryNameByScore(score) ?: return

        if (objective.getScore(name.entryName).isScoreSet) {
            return
        }

        objective.getScore(name.entryName).score = score
        objective.getScore(name.entryName).numberFormat(NumberFormat.blank())
    }

    private fun hideScore(score: Int) {
        val name = getEntryNameByScore(score) ?: return

        if (!objective.getScore(name.entryName).isScoreSet) {
            return
        }

        scoreboard.resetScores(name.entryName)
    }
}