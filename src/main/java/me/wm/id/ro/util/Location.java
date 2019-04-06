package me.wm.id.ro.util;

import org.bukkit.World;

public class Location extends org.bukkit.Location {

    org.bukkit.Location loc = this;

    public Location(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    public Location(org.bukkit.Location loc){
        super(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    @Override
    public String toString(){
        return "Location{world=" + this.getWorld().getName() + ",x=§3" + this.getX() + ",§ry=§3" + this.getY() + "§r,z=§3" + this.getZ() + "§r,pitch=§3" + this.getPitch() + "§r,yaw=§3" + this.getYaw() + "§r}";
    }
}
