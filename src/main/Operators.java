package main;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.BlockVector;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Operators {
    YamlConfiguration configFile;
    public Operators(YamlConfiguration config){
        configFile = config;
    }

    public void save(int x, int y, int z, int x1, int y1, int z1, World welt, Server server, String name, @Nullable Player spieler) throws IOException {
        Location pos1 = new Location(welt, x, y, z);
        Location pos2 = new Location(welt, x1, y1, z1);
        StructureManager manager = server.getStructureManager();
        Structure structure = manager.createStructure();
        structure.fill(pos1, pos2, false);
        File file = new File(configFile.getString("path") + name);
        if(!file.exists()) {
            manager.saveStructure(file, structure);
        }
        else {
            assert spieler != null;
            spieler.sendMessage(Objects.requireNonNull(configFile.getString("name.already.exist")));
        }
    }
    public void place(int placeX, int placeY, int placeZ, World welt, Server server, StructureRotation rot, String name) throws IOException {
        StructureManager manager = server.getStructureManager();
        File file = new File(configFile.getString("path") + name);
        Structure structure = manager.loadStructure(file);
        BlockVector vector = structure.getSize();
        switch (rot) {
            case CLOCKWISE_90 -> {
                placeX = (int) (placeX + vector.getX());
            }
            case CLOCKWISE_180 -> {
                placeX = (int) (placeX + vector.getX());
                placeZ = (int) (placeZ + vector.getZ());
            }
            case COUNTERCLOCKWISE_90 -> {
                placeZ = (int) (placeZ + vector.getZ());
            }
            case NONE -> {
            }
        }
        Location pos = new Location(welt, placeX, placeY, placeZ);
        structure.place(pos, false, rot, Mirror.NONE,0, 1,new Random());
    }
}
