package me.richter.commandsAndGUI.module.disguise
/*
import dev.iiahmed.disguise.Disguise
import dev.iiahmed.disguise.DisguiseManager
import dev.iiahmed.disguise.DisguiseProvider
import dev.iiahmed.disguise.SkinAPI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class DisguiseCommand(private  val plugin: JavaPlugin): CommandExecutor {
	private val provider = DisguiseManager.getProvider()

	init {
		DisguiseManager.setPlugin(plugin)
	}


	override fun onCommand(sender: CommandSender, command: Command, alias: String, args: Array<out String>): Boolean {
		//if (!IsModuleEnabled.template) { sender.sendMessage(Message.moduleNotEnabled); return true }
		if (sender !is Player) return true
		val player: Player = sender
		val disguise = Disguise.builder()
			// the boolean is whether this is a fake nickname or not
			.setName("BillBobbyBob", false)
			// you could as well use Disguise.Builder#setSkin(textures, signature)
			// or even Disguise.Builder#setSkin(uuid, skinAPI)
			// it's recommended to run this async since #setSkin from an online API will block the mainthread
			.setSkin("example-name", SkinAPI.MOJANG)
			// this will change the player into a zombie for others only
			.setEntityType(EntityType.ZOMBIE)
			.build()
		provider.disguise(player, disguise)
		return true
	}
}

 */