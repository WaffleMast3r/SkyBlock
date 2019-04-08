package me.wm.id.ro.Regions;

import me.wm.id.ro.Main;
import me.wm.id.ro.util.Language.LanguageManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class RegionInterogation implements Listener {

    private Player p;
    private Action action;
    private int stage;
    private Block pos1, pos2;

    public RegionInterogation(Player p, Action action) {
        this.p = p;
        this.action = action;
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
        stage = 0;

        LanguageManager.getInstance().sendMessage(p, "RegionChatFirstMessage");
    }

    @EventHandler
    public void onInteract(BlockBreakEvent e) {
        if (!e.getPlayer().equals(p)) return;
        e.setCancelled(true);
        if (stage == 0) {
            pos1 = e.getBlock();
            LanguageManager.getInstance().sendMessage(p, "RegionChatSecondMessage");
            stage++;
        } else if (stage == 1) {
            pos2 = e.getBlock();

            RegionInterogation inst = this;
            new RegionChatConfirm(p, new RegionChatConfirm.Action() {
                @Override
                public void confirm() {
                    action.run(pos1, pos2);
                    HandlerList.unregisterAll(inst);
                }

                @Override
                public void reset() {
                    stage = 0;
                }

                @Override
                public void deny() {
                    HandlerList.unregisterAll(inst);
                }
            });
            stage++;
        }
    }

    public interface Action {
        void run(Block pos1, Block pos2);
    }

}
