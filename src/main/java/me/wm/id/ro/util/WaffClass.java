package me.wm.id.ro.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import javafx.util.Pair;
import me.wm.id.ro.Main;
import me.wm.id.ro.util.files.Config;
import org.bukkit.Location;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class WaffClass {

    public static void sendPlayer(Player p, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;

        public static Direction get(Player p) {
            float yaw = (p.getLocation().getYaw() - 90) % 360;
            if (yaw < 0) {
                yaw += 360.0;
            }
            if (yaw > 45 && yaw < 135) {
                return Direction.NORTH;
            } else if (yaw > 135 && yaw < 225) {
                return Direction.EAST;
            } else if (yaw > 225 && yaw < 315) {
                return Direction.SOUTH;
            } else if ((yaw > 315 && yaw < 360) || (yaw > 0 && yaw < 45)) {
                return Direction.WEST;
            }
            return null;
        }

        public static float toFloat(Direction dir) {
            switch (dir) {
                case NORTH:
                    return 180;
                case EAST:
                    return -90;
                case SOUTH:
                    return 0;
                case WEST:
                    return 90;
                default:
                    Bukkit.broadcastMessage("Null Direction: " + dir);
                    return -1;
            }
        }
    }

    public static class Color {

        public static Color instance = new Color();
        private ArrayList<String> colors;
        private String spl;

        private Color() {
            spl = Config.getConfig().getYml().getString("ColorCharacter");
            colors = new ArrayList<>();
            colors.addAll(Config.getConfig().getYml().getStringList("PluginColors"));
        }

        public static Color getInstance() {
            return instance;
        }

        public String translateColor(String str) {
            for (int i = 0; i < colors.size(); i++) {
                String from = spl + "" + i;
                String to = "ƒ" + colors.get(i);
                str = str.replace(from, to);
            }
            str = str.replaceAll("@err", "ƒc");
            return ChatColor.translateAlternateColorCodes('ƒ', str);
        }
    }

    public static class Loc {
        private static Loc instance = new Loc();

        public static Loc getInstance() {
            return instance;
        }

        public String serialize(Location loc) {
            return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
        }

        public Location deserialize(String loc) {
            String[] str = loc.split(":");
            Location location = new Location(Main.getInstance().getServer().getWorld(str[0]), Double.valueOf(str[1]), Double.valueOf(str[2]), Double.valueOf(str[3]), Float.valueOf(str[4]), Float.valueOf(str[5]));
            return location;
        }

        public Pair<me.wm.id.ro.util.Location, me.wm.id.ro.util.Location> getMinAndMaxLocations(Location loc1, Location loc2) {

            World world1 = loc1.getWorld();
            double x1 = Math.min(loc1.getX(), loc2.getX());
            double y1 = Math.min(loc1.getY(), loc2.getY());
            double z1 = Math.min(loc1.getZ(), loc2.getZ());
            double yaw1 = Math.min(loc1.getYaw(), loc2.getYaw());
            double pitch1 = Math.min(loc1.getPitch(), loc2.getPitch());

            World world2 = loc2.getWorld();
            double x2 = Math.max(loc1.getX(), loc2.getX());
            double y2 = Math.max(loc1.getY(), loc2.getY());
            double z2 = Math.max(loc1.getZ(), loc2.getZ());
            double yaw2 = Math.max(loc1.getYaw(), loc2.getYaw());
            double pitch2 = Math.max(loc1.getPitch(), loc2.getPitch());

            return world1 == world2 ? new Pair<>(new me.wm.id.ro.util.Location(world1, x1, y1, z1, Float.parseFloat(yaw1 + ""), Float.parseFloat(pitch1 + "")), new me.wm.id.ro.util.Location(world2, x2, y2, z2, Float.parseFloat(yaw2 + ""), Float.parseFloat(pitch2 + ""))) : null;
        }
    }

    public static class Particles {
        public static void displayRedstoneParticle(Location loc, int r, int g, int b) {

            try {
                Constructor<?> dustOptionsConstructor = Particle.REDSTONE.getDataType().getConstructor(org.bukkit.Color.class, float.class);
                Object dustOptions = dustOptionsConstructor.newInstance(org.bukkit.Color.fromRGB(r, g, b), 5);
                loc.getWorld().getPlayers().forEach(player -> player.spawnParticle(Particle.REDSTONE, loc.add(0.5, 0, 0.5), 1, 0, 0, 0, 0, dustOptions));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }
}
