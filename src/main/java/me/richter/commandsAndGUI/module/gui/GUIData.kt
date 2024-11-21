package me.richter.commandsAndGUI.module.gui

import org.bukkit.entity.Player

class GUIData {

    private var owner: Player? = null
    private var selectedPlayer: Player? = null
    private var playerSelectShowSelfFirst = true

    constructor(player: Player?) {
        this.owner = player
    }

    fun getOwner(): Player? {
        return owner
    }

    fun getSelectedPlayer(): Player? {
        return selectedPlayer
    }

    fun setSelectedPlayer(selectedPlayer: Player?) {
        this.selectedPlayer = selectedPlayer
    }

    fun getPlayerSelectShowSelfFirst(): Boolean {
        return playerSelectShowSelfFirst
    }

    fun setPlayerSelectShowSelfFirst(playerSelectShowSelfFirst: Boolean) {
        this.playerSelectShowSelfFirst = playerSelectShowSelfFirst
    }

}