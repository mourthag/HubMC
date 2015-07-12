package com.github.mourthag.MainHubPlugin;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.github.mourthag.CommandsHubPlugin.*;
import com.github.mourthag.ListenerHubPlugin.*;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin
{

	public HubItems HubItems;
	public ArrayList<String> LobbyServerPlayer;
	
	public void onEnable()
	{
		HubItems = new HubItems();
		new PlayerListener(this);
		new ItemListener(this);
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener(this));
		this.getLogger().info("HubMC Plugin v1.0.1 enabled");
		this.getLogger().info("Author: Mourthag");
		
		
		setCommands();
		
		this.reloadConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void refreshLobbyPlayers()
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerList");
		out.writeUTF("Lobby");
		
		this.getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
	
	public void onDisable()
	{
		this.getLogger().info("HubMC Plugin disabled");
		this.getLogger().info("Thanks for using");		
	}

	private void setCommands()
	{
		getCommand("lobby").setExecutor(new TeleportCommands(this));
		getCommand("TKR").setExecutor(new TeleportCommands(this));
	}
}
