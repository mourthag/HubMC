package com.github.mourthag.MainHubPlugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LocationPort extends Port {

	public Vector loc;
	public Vector dir;
	
	public LocationPort(String newName, int newMat, int invSlot, Main main,
			Vector newLocation, Vector newDir) {
		super(newName, newMat, invSlot, main);
		
		loc = newLocation;
		dir = newDir;
	}
	
	@Override
	public void run(Player p)
	{
		Location newloc = loc.toLocation(p.getWorld());
		newloc.setDirection(dir);
		p.teleport(newloc);
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>(3);
		
		map.put("Name", Name);
		map.put("itemID", itemID);
		map.put("slot", slot);
		map.put("Location", loc);
		map.put("Direction", dir);
		
		return map;
	}
	
    public static LocationPort deserialize(Map<String, Object> map) {
        return new LocationPort((String) map.get("Name"), (int) map.get("itemID"), (int) map.get("slot"), mainPlugin, (Vector) map.get("Location"), (Vector) map.get("Direction"));
    }

}
