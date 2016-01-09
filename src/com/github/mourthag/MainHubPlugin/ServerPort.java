package com.github.mourthag.MainHubPlugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ServerPort extends Port {
	
	public String Servername;
	
	public ServerPort(String newName, int newMat, int invSlot, Main main, String server) {
		super(newName, newMat, invSlot, main);
		
		Servername = server;
	}
	
	@Override
	public void run(Player p)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(Servername);
		
		p.sendPluginMessage(mainPlugin, "BungeeCord", out.toByteArray());
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>(3);
		
		map.put("Name", Name);
		map.put("itemID", itemID);
		map.put("slot", slot);
		map.put("Server", Servername);
		
		return map;
	}
	
    public static ServerPort deserialize(Map<String, Object> map) {
        return new ServerPort((String) map.get("Name"), (int) map.get("itemID"), (int) map.get("slot"), mainPlugin, (String) map.get("Server"));
    }

}
