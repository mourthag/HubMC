package com.github.mourthag.MainHubPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.mourthag.CommandsHubPlugin.*;
import com.github.mourthag.ListenerHubPlugin.*;

public class Main extends JavaPlugin
{

	public void onEnable()
	{
		this.getLogger().info("HubMC Plugin v1.0.0 enabled");
		this.getLogger().info("Author: Mourthag");
		setCommands();
		this.reloadConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable()
	{
		this.getLogger().info("HubMC Plugin disabled");
		this.getLogger().info("Thanks for using");		
	}
	private void setCommands()
	{
		
	}
}
