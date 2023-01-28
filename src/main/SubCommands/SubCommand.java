package main.SubCommands;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public abstract class SubCommand{
    public void onCommand(Player player, Command command, String[] args, YamlConfiguration yamlConfiguration, Server server, World welt){}
}
