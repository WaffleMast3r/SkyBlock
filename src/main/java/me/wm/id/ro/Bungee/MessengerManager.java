package me.wm.hybrid.ro.Bungee;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.*;
import java.net.InetSocketAddress;

public class MessengerManager implements Listener {

    public MessengerManager() {

    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        if (e.getTag().equalsIgnoreCase("BungeeCord")) {
            System.out.println("Event reached!");
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            try {
                String channel = in.readUTF(); // channel we delivered
                System.out.println("Channel: " + channel);
                if (channel.equalsIgnoreCase("AddServerToBungeeCord")) {
                    String server_name = in.readUTF();
                    String address = in.readUTF();
                    String motd = in.readUTF();
                    System.out.println("Server Name: " + server_name);
                    System.out.println("address: " + address);
                    System.out.println("motd: " + motd);

                    InetSocketAddress socketAddress = new InetSocketAddress(address.split(":")[0], Integer.parseInt(address.split(":")[1]));
                    ServerInfo server = Main.getInstance().getProxy().constructServerInfo(server_name, socketAddress, motd, false);
                    Main.getInstance().getProxy().getServers().put(server_name, server);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(stream);

                    out.writeUTF("BungeeCord");
                    out.writeUTF("return");
                    out.writeUTF("Server added successfully!");
                    if (server != null) {
                        System.out.println("Server is not null!");
                        Main.getInstance().getProxy().getServers().get("Hub").sendData("BungeeCord", stream.toByteArray(), false);
                    } else {
                        System.out.println("The server is null!");
                    }
                }
            } catch (IOException e1) {
                System.out.println("An error occurred trying to send a message back to the server! " + e1.getMessage());
            }
        }
    }

}
