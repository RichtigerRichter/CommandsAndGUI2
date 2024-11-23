package me.richter.commandsAndGUI.modules.scoreboard

import me.richter.commandsAndGUI.Main
import me.richter.commandsAndGUI.files.AnimationsFile
import me.richter.commandsAndGUI.files.ScoreboardFile
import me.richter.commandsAndGUI.modules.placeholders.Placeholder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TestScoreboard(player: Player) :
    ScoreboardBuilder(player, ScoreboardFile().scoreboardDisplayNameMap["commandsAndGUI"]) {
    private var taskEveryTick: BukkitRunnable? = null
    private var taskEverySec: BukkitRunnable? = null

    private var socialId = 0
    private val socialLinks = listOf(
        "${ChatColor.AQUA}twitter.com/DerBanko",
        "${ChatColor.DARK_PURPLE}twitch.tv/DerBanko",
        "${ChatColor.DARK_RED}youtube.com/DerBanko"
    )

    fun init() {
        runEverySecTask()
        runEveryTickTask()
    }


    override fun createScoreboard() {
        /*
        val nachricht = MiniMessage.miniMessage().deserialize("<rainbow>Hello world, isn't <underlined>MiniMessage</underlined> fun?</rainbow>")
        setScore(nachricht, 9)

        setScore("test", 8)
        setScore("", 7)
        setScore(MiniMessage.miniMessage().deserialize("<gray>Rank:"), 6)

        setScore(if (player.isOp) MiniMessage.miniMessage().deserialize("<red>Operator") else MiniMessage.miniMessage().deserialize("<green>Spieler"), 5)

        setScore("", 4)
        setScore("FlyTimePlaceholder", 3)
        setScore("", 2)
        setScore(MiniMessage.miniMessage().deserialize("<red>${player.address?.hostName ?: "Unbekannt"}"), 1)
        setScore("", 0)

         */


        val scoreList = ScoreboardFile().scoreboardListMap["commandsAndGUI"] ?: return
        println(scoreList)

        for (score in 0..14) {

            if (score > scoreList.size - 1) return
            setScore(scoreList[score], score)
        }
    }

    fun runEverySec() {
        //setScore(socialLinks[socialId], 3)
        //socialId = (socialId + 1) % socialLinks.size
    }

    private var animation = 0
    private var aniFrame = 0

    val animationList = AnimationsFile().getAnimationList("test")


    fun runEveryTick() {
        updatePlaceholders()

    }


    fun updatePlaceholders() {
        val scoreList = ScoreboardFile().scoreboardListMap["commandsAndGUI"] ?: return

        for (score in 0..14) {
            if (score > scoreList.size - 1) return
            val line = Placeholder().replacePlaceholders(scoreList[score], player)

            setScore(line, score)
        }
    }


    override fun update() {
        // Dynamische Scoreboard Updates
    }

    private fun runEverySecTask() {
        stopRunEverySec() // Sicherstellen, dass der vorherige Task gestoppt wird
        taskEverySec = object : BukkitRunnable() {
            override fun run() {
                runEverySec()
            }
        }.also {
            it.runTaskTimer(Main.instance, 20, 20)
        }
    }

    fun stopRunEverySec() {
        taskEverySec?.cancel()
    }

    private fun runEveryTickTask() {
        stopRunEveryTick() // Sicherstellen, dass der vorherige Task gestoppt wird
        taskEveryTick = object : BukkitRunnable() {
            override fun run() {
                runEveryTick()
            }
        }.also {
            it.runTaskTimer(Main.instance, 1, 1)
        }
    }

    fun stopRunEveryTick() {
        taskEveryTick?.cancel()
    }
}
