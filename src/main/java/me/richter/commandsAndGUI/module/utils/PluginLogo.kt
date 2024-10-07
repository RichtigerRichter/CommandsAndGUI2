package me.richter.commandsAndGUI.module.utils

class PluginLogo {
	//font.txt

	private val r = "\u001B[0m" //reset
	private val s = "\u001B[90m" //schatten => grau
	private val b = "\u001B[34m" //border => blau
	private val f = "\u001B[31m" //font => rot
	private val t = "\u001B[97m" //text => weiß


	fun printPluginInfo() {
		printColored("####################################################################################################")
		printColored("#                                                                                                  #")
		printColored("#  ██████╗ ██╗ ██████╗██╗  ██╗████████╗███████╗██████╗ ██╗███████╗    ███████╗██╗  ██╗██╗████████╗ #")
		printColored("#  ██╔══██╗██║██╔════╝██║  ██║╚══██╔══╝██╔════╝██╔══██╗██║██╔════╝    ██╔════╝██║  ██║██║╚══██╔══╝ #")
		printColored("#  ██████╔╝██║██║     ███████║   ██║   █████╗  ██████╔╝╚═╝███████╗    ███████╗███████║██║   ██║    #")
		printColored("#  ██╔══██╗██║██║     ██╔══██║   ██║   ██╔══╝  ██╔══██╗   ╚════██║    ╚════██║██╔══██║██║   ██║    #")
		printColored("#  ██║  ██║██║╚██████╗██║  ██║   ██║   ███████╗██║  ██║   ███████║    ███████║██║  ██║██║   ██║    #")
		printColored("#  ╚═╝  ╚═╝╚═╝ ╚═════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝   ╚═╝    #")
		printColored("####################################################################################################")
		printColored("#      Plugin: CommandsAndGUI      #       Version: 1.0       #      Release: 16.11.2023 20:00     #")
		printColored("####################################################################################################")
		printColored("# Download: https://github.com/RichtigerRichter/CommandsAndGUI/releases                            #")
		printColored("####################################################################################################")
	}

	fun printColored(text: String) {
		val formatedText = text
			.replace("█", "$s█$r")
			.replace("#", "$b#$r")
			.replace("╗", "$f╗$r")
			.replace("║", "$f║$r")
			.replace("╝", "$f╝$r")
			.replace("═", "$f═$r")
			.replace("╔", "$f╔$r")
			.replace("╚", "$f╚$r")
		println(formatedText)
	}

}