package main.SubCommands;

import main.Operators;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class SaveCommand extends SubCommand {
    YamlConfiguration config;
    public void onCommand(Player player, Command command, String args[], YamlConfiguration yamlConfiguration, Server server, World welt) {
        config = yamlConfiguration;
        if (args.length == 8) {
            try {
                Operators operators = new Operators(yamlConfiguration);
                operators.save(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), welt, server, args[7], player);
                player.sendMessage((String) Objects.requireNonNull(yamlConfiguration.get("save.success")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            assert player != null;
            player.sendMessage(Objects.requireNonNull(yamlConfiguration.getString("save.wrong.usage")));
        }

    }
}
