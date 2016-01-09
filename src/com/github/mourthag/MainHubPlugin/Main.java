package com.github.mourthag.MainHubPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.mourthag.CommandsHubPlugin.*;
import com.github.mourthag.ListenerHubPlugin.*;

public class Main extends JavaPlugin
{
	public List<Port> Ports = new ArrayList<Port>();
	File PortsFile;
	YamlConfiguration PortsYML; 
	
	public HubItems HubItems;
	
	public void onEnable()
	{
		PortsFile = new File(getDataFolder(), "Ports.yml");
		ConfigurationSerialization.registerClass(Port.class, "Port");
		
		HubItems = new HubItems();
		new PlayerListener(this);
		new ItemListener(this);
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getLogger().info("HubMC Plugin v1.0.1 enabled");
		this.getLogger().info("Author: Mourthag");
		
		setCommands();
		
		this.reloadConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		PortsYML = YamlConfiguration.loadConfiguration(PortsFile);
		loadPorts();
	}
	
	public void onDisable()
	{
		this.getLogger().info("HubMC Plugin disabled");
		this.getLogger().info("Thanks for using");		
	}
	
	public Port findPort(String search)
	{
		for(Port cur: Ports)
		{
			if(cur.Name.equals(search))
				return cur;
		}
		return null;
	}
	
	public boolean deletePort(Port port)
	{
		String path = "Ports."+port.Name;
		if(PortsYML.contains(path))
		{
			PortsYML.set(path, null);
			try {
				PortsYML.save(PortsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			loadPorts();
			
			return true;
		}
		return false;
	}
	
	public void addPort(Port port)
	{
		String path = "Ports."+port.Name;
		PortsYML.createSection(path);
		PortsYML.set(path, port);
		try {
			PortsYML.save(PortsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadPorts();
	}
	
	public void loadPorts()
	{
		Ports.clear();
		if(PortsYML.isConfigurationSection("Ports"))
		{			
			for(String curPort : PortsYML.getConfigurationSection("Ports").getKeys(true))
			{
				curPort = "Ports." + curPort;
				this.getLogger().info(curPort);
				Ports.add((Port)PortsYML.get(curPort));
			}
		}
	}
	

	private void setCommands()
	{
		this.getCommand("port").setExecutor(new PortCommands(this));
	}
}
