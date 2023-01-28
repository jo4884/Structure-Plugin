package main;

import main.SubCommands.PlaceCommand;
import main.SubCommands.SaveCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class StructurePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        File datei = new File(this.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(datei);
        this.saveResource("config.yml", false);

        CommandExecutor mazeCommand = new StructureCommand(config);
        Objects.requireNonNull(this.getCommand("structure")).setExecutor(mazeCommand);
        ((StructureCommand) mazeCommand).registerCommand("save",new SaveCommand());
        ((StructureCommand) mazeCommand).registerCommand("place",new PlaceCommand());
        super.onEnable();
    }
}
