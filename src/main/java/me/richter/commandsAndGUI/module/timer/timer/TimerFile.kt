package me.richter.commandsAndGUI.module.timer.timer

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class TimerFile {
    private val file: File
    private var config: YamlConfiguration

    init {
        val dir = File("./plugins/CommandsAndGUI")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        file = File(dir, "timer.yml")
        if (!file.exists()) {
            file.createNewFile()

            config = YamlConfiguration.loadConfiguration(this.file)

            //sandart options
            config.set("isRunning", 0)
            config.set("time", 0)

            config.save(file)
        }


        config = YamlConfiguration.loadConfiguration(this.file)
    }

    fun setTime(time: Int) {
        config.set("time", time)
        saveTimerFile()
    }

    fun getTime(): Int {
        return config.getInt("time")
    }

    fun setRunning(isRunning: Boolean) {
        config.set("isRunning", isRunning)
        saveTimerFile()
    }

    fun getRunning(): Boolean {
        return config.getBoolean("isRunning")
    }

    fun saveTimerFile() {
        config.save(file)
    }
}