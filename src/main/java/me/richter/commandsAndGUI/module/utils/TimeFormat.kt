package me.richter.commandsAndGUI.module.utils

class TimeFormat {
	fun timeToString(timeInS: Int): String {
		val h = timeInS / 3600
		val m = (timeInS % 3600) / 60
		val s = timeInS % 60


		val formatedSec = if (s < 10) "0$s" else "$s"
		val formatedMin = if (m < 10) "0$m" else "$m"
		val formatedHor = if (h < 10) "0$h" else "$h"
		return "$formatedHor:$formatedMin:$formatedSec"
	}
	
	fun timeToTimeMap(timeInS: Int): Map<String, Int> {
		val h = timeInS / 3600
		val m = (timeInS % 3600) / 60
		val s = timeInS % 60
		return mapOf(Pair("h", h), Pair("m", m), Pair("s", s))
	}
}
