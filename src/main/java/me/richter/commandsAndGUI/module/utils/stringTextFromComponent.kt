package me.richter.commandsAndGUI.module.utils

import java.util.regex.Pattern

class stringTextFromComponent {
    fun stringTextFromComponent(input: String): String {

        val pattern = Pattern.compile("content=\"(.*?)\"")
        val matcher = pattern.matcher(input)

        if (matcher.find()) {
            val extractedText = matcher.group(1)
            return extractedText
        } else {
            return "Pattern not found"
        }
    }
}