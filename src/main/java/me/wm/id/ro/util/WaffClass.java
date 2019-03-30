package me.wm.id.ro.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.wm.id.ro.util.files.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WaffClass {

    public static void sendPlayer(Player p, String server){
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
            spl = Config.getInstance().getConfig().getYml().getString("ColorCharacter");
            colors = new ArrayList<>();
            colors.addAll(Config.getInstance().getConfig().getYml().getStringList("PluginColors"));
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
    }
}
