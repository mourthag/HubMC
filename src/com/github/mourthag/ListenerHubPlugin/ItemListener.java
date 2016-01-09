package com.github.mourthag.ListenerHubPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
				Inventory teleInv = p.getServer().createInventory(null, 27, "§7Teleporter");
				
				for(Port cur: mainPlugin.Ports)
				{
					teleInv.setItem(cur.slot, cur.getItem());
				}
				
				p.openInventory(teleInv);
			}			
		}
		catch(NullPointerException NullEx)
		{
			
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		Item i = e.getItemDrop();
		ItemStack stack = i.getItemStack();
		try{
			if(stack.getItemMeta().getDisplayName().equalsIgnoreCase("§7Teleporter"))
			{
				e.setCancelled(true);
			}
		}
		catch(NullPointerException NullEx)
		{
			
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		Player p = (Player)e.getWhoClicked();
		
		try
		{
			if(e.getInventory().getName().equals("§7Teleporter"))
			{
				e.setCancelled(true);
				
				ItemStack i = e.getCurrentItem();
				ItemMeta meta = i.getItemMeta();
				String Name = meta.getDisplayName();
				if( mainPlugin.findPort(Name) != null)
				{
					Port port = mainPlugin.findPort(Name);
					port.run(p);
				}
			}
		}
		catch(NullPointerException NullEx)
		{
			
		}
	}

}
