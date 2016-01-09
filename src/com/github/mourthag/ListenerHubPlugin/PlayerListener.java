package com.github.mourthag.ListenerHubPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

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
		
		p.getInventory().setItem(0, mainPlugin.HubItems.getTeleporter());
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		Player p = e.getPlayer();
		p.getInventory().setItem(0, mainPlugin.HubItems.getTeleporter());
	}
}
		
