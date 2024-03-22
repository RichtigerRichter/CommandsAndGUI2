package me.richter.commandsAndGUI.module.utils

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MinecraftVersion {

    fun between (minVer: String, maxVer: String): Boolean {
        val server = Bukkit.getServer()

        val minVerNum = minVer.substring(2).toDouble()
        val maxVerNum = maxVer.substring(2).toDouble()

        val serverVerNum = server.minecraftVersion.substring(2).toDouble()

        return minVerNum <= serverVerNum && serverVerNum < maxVerNum
    }
    fun from(ver: String): Boolean {
        val server = Bukkit.getServer()

        val minVerNum = ver.substring(2).toDouble()

        val serverVerNum = server.minecraftVersion.substring(2).toDouble()

        return minVerNum <= serverVerNum

    }
    fun until(ver: String): Boolean {
        val server = Bukkit.getServer()

        val maxVerNum = ver.substring(2).toDouble()

        val serverVerNum = server.minecraftVersion.substring(2).toDouble()

        return serverVerNum <= maxVerNum
    }
}