package me.wm.id.ro.util.Comunications;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.wm.id.ro.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashMap;

public abstract class Controller implements PluginMessageListener {

    private String in_channel, out_channel, sub_channel;

    public Controller(String in_channel, String out_channel, String sub_channel) {
        this.in_channel = in_channel;
        this.out_channel = out_channel;
        this.sub_channel = sub_channel;
        Main.getInstance().getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), in_channel, this);
        Main.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance(), out_channel);
    }

    public abstract HashMap<String, Action> getResponses();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals(in_channel)) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        if (!in.readUTF().equalsIgnoreCase(sub_channel)) return;
        for (String r : getResponses().keySet()) {
            if (in.readUTF().equalsIgnoreCase(r)){
                getResponses().get(r).run();
            }
        }
    }

    public void sendMessage(String... args) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF(sub_channel);
        for (String arg : args) {
            out.writeUTF(arg);
        }

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null) {
            player.sendPluginMessage(Main.getInstance(), out_channel, out.toByteArray());
        }
    }

    public interface Action {
        void run();
    }
}
