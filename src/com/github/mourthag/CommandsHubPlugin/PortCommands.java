package com.github.mourthag.CommandsHubPlugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.MainHubPlugin.LocationPort;
import com.github.mourthag.MainHubPlugin.Main;
import com.github.mourthag.MainHubPlugin.Port;
import com.github.mourthag.MainHubPlugin.ServerPort;

public class PortCommands implements CommandExecutor
{
	Main mainPlugin;
	
	public PortCommands(Main main)
	{
		mainPlugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p;
		if(sender instanceof Player)
		{
			p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("port"))
			{
				if(args.length >= 1)
				{
					if(args[0].equalsIgnoreCase("list"))
					{
						mainPlugin.loadPorts();
						p.sendMessage(ChatColor.GREEN + "There are " + mainPlugin.Ports.size() + " Ports available");
						for(Port cur: mainPlugin.Ports)
						{
							p.sendMessage(ChatColor.GREEN + cur.Name);
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("set"))
					{
						if(args.length == 4)
						{
							if(!args[1].equalsIgnoreCase("server") && !args[1].equalsIgnoreCase("location"))
							{
								
								Location loc= p.getLocation();
								int itemID = Integer.parseInt(args[2]);
								int slot = Integer.parseInt(args[3]) - 1;
								
								for(Port cur: mainPlugin.Ports)
								{
									if(cur.slot == slot)
									{
										p.sendMessage(ChatColor.GREEN +"Slot already occupied");
										return false;
									}
								}
								mainPlugin.addPort(new LocationPort(args[1], itemID, slot, mainPlugin, loc.toVector(), loc.getDirection()));
								if(slot > 26)
								{
									p.sendMessage(ChatColor.GREEN + "Not enough slots, please inform the Developer");
									return false;
								}
								else{
									
									p.sendMessage(ChatColor.GREEN + "Port '" + args[1] + "' set to your location with the item " + Material.getMaterial(itemID).name() + " in Slot " + args[3]);
									return true;
								}
							}
							p.sendMessage(ChatColor.GREEN + "Invalid arguments");
							return false;
						}
						else if(args.length >= 5)
						{
							if(args[1].equalsIgnoreCase("location") && args.length == 5)
							{
								Location loc= p.getLocation();
								int itemID = Integer.parseInt(args[3]);
								int slot = Integer.parseInt(args[4]) - 1;
								
								for(Port cur: mainPlugin.Ports)
								{
									if(cur.slot == slot)
									{
										p.sendMessage(ChatColor.GREEN +"Slot already occupied");
										return false;
									}
								}
								mainPlugin.addPort(new LocationPort(args[2], itemID, slot, mainPlugin, loc.toVector(), loc.getDirection()));
								if(slot > 26)
								{
									p.sendMessage(ChatColor.GREEN + "Not enough slots, please inform the Developer");
									return false;
								}
								else{
									
									p.sendMessage(ChatColor.GREEN + "Port '" + args[2] + "' set to your location with the item " + Material.getMaterial(itemID).name() + " in Slot " + args[4]);
									return true;
								}
							}
							else if(args[1].equalsIgnoreCase("server")&& args.length == 6)
							{
								int itemID = Integer.parseInt(args[3]);
								int slot = Integer.parseInt(args[4]) - 1;
								
								for(Port cur: mainPlugin.Ports)
								{
									if(cur.slot == slot)
									{
										p.sendMessage(ChatColor.GREEN +"Slot already occupied");
										return false;
									}
								}
								mainPlugin.addPort(new ServerPort(args[2], itemID, slot, mainPlugin, args[5]));
								
								if(slot > 26)
								{
									p.sendMessage(ChatColor.GREEN + "Not enough slots, please inform the Developer");
									return false;
								}
								else
								{
									
									p.sendMessage(ChatColor.GREEN + "Port '" + args[2] + "' set to server '" + args[5] + "' with the item " + Material.getMaterial(itemID).name() + " in Slot " + args[4]);
									return true;
								}
							}
							else
							{
								p.sendMessage(ChatColor.GREEN + "Invalid arguments");
								return false;								
							}
						}
						else
						{
							p.sendMessage(ChatColor.GREEN + "Invalid arguments");
							return false;
						}
							
					}
					else if(args[0].equalsIgnoreCase("delete"))
					{
						if(args.length == 2)
						{
							if(mainPlugin.findPort(args[1]) != null)
							{
								mainPlugin.deletePort(mainPlugin.findPort(args[1]));
								p.sendMessage(ChatColor.GREEN + "Port '" + args[1] + "' was deleted!");
								return true;
							}
							else
							{
								p.sendMessage(ChatColor.GREEN + "Can not find Port '" + args[1] + "'");
								return false;
							}
							
						}
						else
						{
							p.sendMessage(ChatColor.GREEN + "Invalid arguments");
							return false;
						}
					}
					else
					{
						p.sendMessage(ChatColor.GREEN + "Unknown Command");
						return false;
					}
				}
				else
				{
					p.sendMessage(ChatColor.GREEN + "Not enough arguments");
					return false;
				}
					
				
			}
		
		}
		sender.sendMessage(ChatColor.RED + "You are no Player!");
		return false;
	}

}
