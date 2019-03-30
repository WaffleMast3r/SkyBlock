package me.wm.id.ro.util.NMS.Versions;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.wm.id.ro.util.Logger;
import me.wm.id.ro.util.NMS.NMS;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class v1_12_R1 implements NMS {

    @Override
    public void registerCommand(String name, Command clasa) {
        ((CraftServer) Main.getInstance().getServer()).getCommandMap().register(name, clasa);
    }

    @Override
    public void openSign(Player p) {
        Location loc = p.getLocation();
        PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void openBook(Player p, ItemStack book) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte) 0);
        buf.writerIndex(1);

        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }

    @Override
    public void setNameTag(Player p, String name) {
        String pname = p.getName();
        EntityPlayer ep = ((CraftPlayer) p).getHandle();

        Logger.debug("%prefix% $1Nametag to: $2" + name);
        ep.setCustomName(name);
        p.setDisplayName(name);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (!pl.equals(p)) {
                ((CraftPlayer) pl).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(ep));
            }
        }

        ep.setCustomName(pname);
    }

    @Override
    public void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        if (title != null) {
            IChatBaseComponent bc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle tit = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, bc);
            PacketPlayOutTitle length = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(tit);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
        }
        if (subTitle != null) {
            IChatBaseComponent bc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
            PacketPlayOutTitle tit = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, bc);
            PacketPlayOutTitle length = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(tit);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
        }
    }

    @Override
    public void playAction(Player p, String text) {
        // TODO: 26/07/2018 Create Play Action!
    }

}
