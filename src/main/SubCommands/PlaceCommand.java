package main.SubCommands;

import main.Operators;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class PlaceCommand extends SubCommand {
    YamlConfiguration config;
    @Override
    public void onCommand(Player player, Command command, String[] args, YamlConfiguration yamlConfiguration, Server server, World welt) {
        config = yamlConfiguration;
        if (args.length == 6) {
            StructureRotation rotation = StructureRotation.NONE;
            int placeX = Integer.parseInt(args[1]);
            int placeY = Integer.parseInt(args[2]);
            int placeZ = Integer.parseInt(args[3]);
            switch (Integer.parseInt(args[4])) {
                case 90 -> {
                    rotation = StructureRotation.CLOCKWISE_90;
                }
                case 180 -> {
                    rotation = StructureRotation.CLOCKWISE_180;
                }
                case 270 -> {
                    rotation = StructureRotation.COUNTERCLOCKWISE_90;
                }
                case 0 -> {
                }
            }
            try {
                Operators operators = new Operators(yamlConfiguration);
                operators.place(placeX, placeY, placeZ, welt, server, rotation, args[5]);
                player.sendMessage((String) Objects.requireNonNull(yamlConfiguration.get("place.success")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            assert player != null;
            player.sendMessage(Objects.requireNonNull(yamlConfiguration.getString("place.wrong.usage")));
        }
    }
}
