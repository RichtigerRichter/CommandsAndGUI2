package me.richter.commandsAndGUI.module.utils

object RegexStrings {
	const val UUID = "([0-9a-f]{8})(?:-|)([0-9a-f]{4})(?:-|)(4[0-9a-f]{3})(?:-|)([89ab][0-9a-f]{3})(?:-|)([0-9a-f]{12})"
	const val TIME = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d\$"
}