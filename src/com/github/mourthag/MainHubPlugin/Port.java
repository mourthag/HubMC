package com.github.mourthag.MainHubPlugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SerializableAs("Port")
public class Port implements ConfigurationSerializable
{
	public String Name;
	public int itemID;
	public int slot;
	
	static Main mainPlugin;
	
	public Port(String newName, int newMat, int invSlot, Main main)
	{
		Name = newName;
		itemID = newMat;
		slot = invSlot;
		mainPlugin = main;
	}
	
	public ItemStack getItem()
	{
		Material mat = Material.getMaterial(itemID);
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Name);
		
		item.setItemMeta(meta);
		return item;
	}

	public void run(Player p)
	{
		p.sendMessage("You clicked an empty port");
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>(3);
		
		map.put("Name", Name);
		map.put("itemID", itemID);
		map.put("slot", slot);
		
		return map;
	}
	
    public static Port deserialize(Map<String, Object> map) {
        return new Port((String) map.get("Name"), (int) map.get("itemID"), (int) map.get("slot"), mainPlugin);
    }

}
