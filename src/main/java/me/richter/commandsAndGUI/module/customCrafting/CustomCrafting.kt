package me.richter.commandsAndGUI.module.customCrafting

import me.richter.commandsAndGUI.module.customCrafting.invisibleItemFrames.InvisbleItemFrameRecipe

class CustomCrafting {
    //todo CustomCrafting
    fun registerCustomRecipes() {
        InvisbleItemFrameRecipe().invisibleItemframeRecipe()
        InvisbleItemFrameRecipe().invisibleGlowItemframeRecipe()
    }


}