package me.richter.commandsAndGUI

import org.bukkit.Material
import org.bukkit.Registry
import org.bukkit.block.Biome

class Funs {
    fun isEven(number: Int): Boolean {
        println(number % 2 == 0)
        return number % 2 == 0
    }

    fun isOdd(number: Int): Boolean {
        println(number % 2 != 0)
        return number % 2 != 0
    }

    fun getAllBiomeNames(): MutableList<String> {
        val allBiomes: Array<Biome> = Biome.entries.toTypedArray()
        val biomeNames: MutableList<String> = mutableListOf()

        for (biome in allBiomes) {
            biomeNames.add(biome.name)
        }

        return biomeNames
    }

    fun getAllBlockNames(): MutableList<String> {
        val allBlocks: Collection<Material> = Registry.MATERIAL.toMutableList()
        val blockNames: MutableList<String> = mutableListOf()

        for (block in allBlocks) {
            if (!block.isBlock) return blockNames
            blockNames.add(block.toString())
        }

        return blockNames
    }
}