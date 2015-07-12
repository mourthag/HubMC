package com.github.mourthag.ListenerHubPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.github.mourthag.MainHubPlugin.*;

public class ItemListener implements Listener
{
	static Main mainPlugin;
	
	public ItemListener(Main main)
	{
		mainPlugin = main;
		mainPlugin.getServer().getPluginManager().registerEvents(this, mainPlugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		
		try{
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Teleporter"))
			{
				Inventory teleInv = p.getServer().createInventory(null, 9, "§7Teleporter");
				p.openInventory(teleInv);
			}			
		}
		catch(NullPointerException NullEx)
		{
			
		}
	}

}
