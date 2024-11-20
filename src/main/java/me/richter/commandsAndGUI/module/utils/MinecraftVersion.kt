package me.richter.commandsAndGUI.module.utils

import org.bukkit.Bukkit

class MinecraftVersion {

    fun betweenAndIncluding(minVer: String, maxVer: String): Boolean {
        val server = Bukkit.getServer()

        val minVerNum = ("0." + minVer.replace(".", "")).toDouble()
        val maxVerNum = ("0." + maxVer.replace(".", "")).toDouble()

        val serverVerNum = ("0." + server.minecraftVersion.replace(".", "")).toDouble()

        return minVerNum <= serverVerNum && serverVerNum < maxVerNum
    }

    fun newerAndIncluding(minVer: String): Boolean {
        val server = Bukkit.getServer()

        val minVerNum = ("0." + minVer.replace(".", "")).toDouble()

        val serverVerNum = ("0." + server.minecraftVersion.replace(".", "")).toDouble()

        return minVerNum <= serverVerNum

    }

    fun olderAndIncluding(maxVer: String): Boolean {
        val server = Bukkit.getServer()

        val maxVerNum = ("0." + maxVer.replace(".", "")).toDouble()

        val serverVerNum = ("0." + server.minecraftVersion.replace(".", "")).toDouble()

        return serverVerNum <= maxVerNum
    }
}