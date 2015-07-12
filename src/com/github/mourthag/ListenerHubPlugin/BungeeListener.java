package com.github.mourthag.ListenerHubPlugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.github.mourthag.MainHubPlugin.Main;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class BungeeListener implements PluginMessageListener {

	Main mainPlugin;
	
	public BungeeListener(Main main)
	{
		mainPlugin = main;
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
		if(!channel.equals("BungeeCord"))
		{
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		
		if(subchannel.equals("PlayerList"))
		{
			String server= in.readUTF();
			if(server.equals("Lobby"))
			{
				mainPlugin.LobbyServerPlayer.clear();
				for(String p: in.readUTF().split(", "))
				{
					mainPlugin.LobbyServerPlayer.add(p);
				}
			}
		}
		
	}
}
