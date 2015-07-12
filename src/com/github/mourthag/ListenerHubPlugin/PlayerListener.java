package com.github.mourthag.ListenerHubPlugin;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.mourthag.MainHubPlugin.*;

public class PlayerListener implements Listener
{
	static Main mainPlugin;
	
	public PlayerListener(Main main)
	{
		mainPlugin = main;
		mainPlugin.getServer().getPluginManager().registerEvents(this, mainPlugin);
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();				
		
		p.getInventory().addItem(mainPlugin.HubItems.getTeleporter());
		p.sendMessage(ChatColor.GREEN + "Willkommen");
	}
}
		
