package main;

import main.SubCommands.SubCommand;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class StructureCommand implements CommandExecutor, TabCompleter {
    YamlConfiguration configFile;
    Operators operators;
    Map<String, SubCommand> subcommands = new HashMap<String, SubCommand>();
    World welt = null;
    Server server = null;
    public StructureCommand(YamlConfiguration config) {
        configFile = config;
        operators = new Operators(config);
    }
    public void registerCommand(String command, SubCommand subCommand){
        subcommands.put(command, subCommand);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player spieler = null;
        server = sender.getServer();
        if (sender instanceof Player) {
            spieler = (Player) sender;
            welt = spieler.getWorld();
        }
        if (subcommands.containsKey(args[0].toLowerCase())) {
            subcommands.get(args[0]).onCommand(spieler, command, args, configFile, server, welt);
            return true;
        }
        else {
            assert spieler != null;
            spieler.sendMessage((String) Objects.requireNonNull(configFile.get("wrong.subcommand")));
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1) {
            List<String> list = new ArrayList<String>(subcommands.keySet());
            return list;
        }
        return null;
    }
}