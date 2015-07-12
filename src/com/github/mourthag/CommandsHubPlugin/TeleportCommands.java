package com.github.mourthag.CommandsHubPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.MainHubPlugin.*;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class TeleportCommands implements CommandExecutor {

	public Main mainPlugin;
	
	public TeleportCommands(Main main)
	{
		mainPlugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p;
		if(sender instanceof Player)
		{
			p = (Player)sender;	
			if(cmd.getName().equalsIgnoreCase("lobby"))
			{
				if(mainPlugin.LobbyServerPlayer.contains(p.getName()))
				{
					p.sendMessage(ChatColor.GREEN + "You are already in the lobby");
				}
				else{
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF("Lobby");
					
					p.sendPluginMessage(mainPlugin, "BungeeCord", out.toByteArray());
					p.getInventory().addItem(mainPlugin.HubItems.getTeleporter());
					return true;
					
				}
			}
			if(cmd.getName().equalsIgnoreCase("TKR"))
			{
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF("TKR1");
				
				p.sendPluginMessage(mainPlugin, "BungeeCord", out.toByteArray());
				return true;
			}
			return false;
		}
		sender.sendMessage("You are no Player!");
		return false;
	}

}

