package me.richter.commandsAndGUI.modules.customCrafting

import me.richter.commandsAndGUI.modules.customCrafting.invisibleItemFrames.InvisbleItemFrameRecipe

class CustomCrafting {
    //todo CustomCrafting
    fun registerCustomRecipes() {
        InvisbleItemFrameRecipe().invisibleItemframeRecipe()
        InvisbleItemFrameRecipe().invisibleGlowItemframeRecipe()
    }


}