package me.richter.commandsAndGUI.module.workstations

import org.bukkit.entity.Player

class OpenWorkFun {
    fun openWorkbench(player: Player){
        player.openWorkbench(player.location, true) }

    fun openCartographyTable(player: Player){
        player.openCartographyTable(player.location, true) }

    fun openGrindstone(player: Player){
        player.openGrindstone(player.location, true) }

    fun openLoom(player: Player){
        player.openLoom(player.location, true) }

    fun openSmithingTable(player: Player){
        player.openSmithingTable(player.location, true) }

    fun openStonecutter(player: Player){
        player.openStonecutter(player.location, true) }


    //TODO [TODO] mach ma noch op dass anvil kein xp braucht oder kein too expensive
    fun openAnvil(
        player: Player,
        //op: Boolean
    ){
        player.openAnvil(player.location, true)
    }


    //TODO [TODO] mach ma auch noch op dass table lapis (lapis bekommt man ins inf) hat oder bookshelf power oder xp
    fun openEnchanting(
        player: Player,
        //op: Boolean
    ){
        player.openEnchanting(player.location, true)
    }
}