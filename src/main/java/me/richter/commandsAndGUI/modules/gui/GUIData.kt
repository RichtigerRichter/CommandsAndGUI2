package me.richter.commandsAndGUI.modules.gui

import org.bukkit.entity.Player

class GUIData(player: Player) {

    private var owner: Player = player
    private var selectedPlayer: Player = player
    private var playerSelectShowSelfFirst = true

    fun getOwner(): Player {
        return owner
    }

    fun getSelectedPlayer(): Player {
        return selectedPlayer
    }

    fun setSelectedPlayer(selectedPlayer: Player) {
        this.selectedPlayer = selectedPlayer
    }

    fun getPlayerSelectShowSelfFirst(): Boolean {
        return playerSelectShowSelfFirst
    }

    fun setPlayerSelectShowSelfFirst(playerSelectShowSelfFirst: Boolean) {
        this.playerSelectShowSelfFirst = playerSelectShowSelfFirst
    }

}