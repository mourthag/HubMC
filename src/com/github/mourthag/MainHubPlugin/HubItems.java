package com.github.mourthag.MainHubPlugin;

import org.bukkit.Material;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;

public class HubItems 
{
	public ItemStack Teleporter = new ItemStack(Material.COMPASS, 1);
	
	public ItemStack getTeleporter()
	{
		ItemStack teleporter = Teleporter;
		ItemMeta meta =  teleporter.getItemMeta();
		meta.setDisplayName("§7Teleporter");
		teleporter.setItemMeta(meta);
		
		return teleporter;
	}
	
}
