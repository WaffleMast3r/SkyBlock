package me.wm.hybrid.ro.util.gui;

import me.wm.hybrid.ro.util.Language.LanguageManager;
import me.wm.hybrid.ro.util.Logger;
import me.wm.hybrid.ro.util.Updater.UpdateEvent;
import me.wm.hybrid.ro.util.Updater.UpdateTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class EasyGUI implements Listener {

    private Inventory localInv;
    private GUIContents contents;
    private Map<Button, Integer> buttons;
    private Player p;
    private UpdateTime refresh;
    private boolean autoRefresh;
    private Button callBackButton;

    public EasyGUI(Player p, GUIContents contents) {
        this.p = p;
        this.contents = contents;
        this.buttons = new ConcurrentHashMap<>();
        this.refresh = UpdateTime.SECOND;
        this.callBackButton = null;
    }

    public void openGUI() {
        if (localInv == null) {
            Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
        }
        String title = LanguageManager.getInstance().getStyleFile(p).translateColor(contents.title());
        localInv = Bukkit.createInventory(p, contents.size(), title);

        for (Button b : buttons.keySet()) {
            this.localInv.setItem(buttons.get(b), b.getItem().build());
        }

        p.openInventory(localInv);
    }

    public void setCallBack(CallBack callBack) {
        CustomItem buttonItem = getItem("CallBack");
        if (buttonItem == null) {
            Logger.error("The CallBack is not defined in GUI: " + contents.title());
        } else {
            callBackButton = new Button(buttonItem, new Action() {
                @Override
                public void run(Player paramPlayer, ActionType paramActionType) {
                    callBack.run();
                }
            });
            setFinalButton(callBackButton, buttonItem.getSlot());
        }
    }

    public void loadFillItems() {
        if (contents.fillItems() == null) return;
        for (CustomFillItem item : contents.fillItems()) {
            if (item.getSlots().equalsIgnoreCase("all")) {
                for (int i = 0; i < contents.size(); i++) {
                    setFillButton(item, i);
                }
                break;
            } else {
                for (String slot : item.getSlots().split(",")) {
                    setFillButton(item, Integer.parseInt(slot));
                }
            }
        }
    }

    @EventHandler
    public void sync(UpdateEvent e) {
        if (!e.getUpdateTime().equals(refresh)) return;
        if (!autoRefresh) return;
        for (Button b : buttons.keySet()) {
            this.localInv.setItem(buttons.get(b), null);
            this.localInv.setItem(buttons.get(b), b.getItem().build());
        }
        this.p.updateInventory();
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getPlayer().equals(p) && p.getOpenInventory().getTopInventory().equals(this.localInv)) {
            unregisterListener();
            localInv = null;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player))
            return;

        Player player = (Player) e.getWhoClicked();

        if (player.equals(p) && player.getOpenInventory().getTopInventory().equals(this.localInv)) {
            Button b = getButton(e.getSlot());
            if (b == null) return;
            e.setCancelled(true);

            if (b.getAction() == null) {
                Logger.debug("Action for this button is null");
                return;
            }

            if (e.getAction().equals(InventoryAction.PICKUP_ALL)) {
                b.getAction().run(p, ActionType.LEFT_CLICK);
            } else if (e.getAction().equals(InventoryAction.PICKUP_HALF)) {
                b.getAction().run(p, ActionType.RIGHT_CLICK);
            } else if (e.getAction().equals(InventoryAction.CLONE_STACK)) {
                b.getAction().run(p, ActionType.MIDDLE_CLICK);
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (!e.getPlayer().equals(p)) return;
        if (e.getPlayer().getOpenInventory().getTopInventory().equals(this.localInv)) {
            unregisterListener();
            localInv = null;
        }
    }

    public void addButton(Button paramButton) {
        setFinalButton(paramButton, -1);
    }

    public void putButton(CustomItem item, Action action) {
        setButton(item, action, item.getSlot());
    }

    public void setButton(CustomItem item, Action action, int paramInt) {
        setFinalButton(new Button(item, action), paramInt);
    }

    public void setFillButton(CustomItem item, int slot) {
        Button button = new Button(item, null);
        if (button.getItem() == null) {
            System.out.println("Refused to add button to slot " + slot + " in " + contents.title());
            return;
        }
        if (!buttons.containsValue(slot))
            buttons.put(button, slot);
    }

    private void setFinalButton(Button button, int slot) {
        if ((button == null) || (button.getItem() == null)) {
            System.out.println("Refused to add button to slot " + slot + " in " + contents.title());
            return;
        }
        if (slot == -1) {
            slot = getNextFreeSlot();
        }

        if (buttons.containsValue(slot))
            buttons.remove(getButton(slot));

        buttons.put(button, slot);
    }

    private int getNextFreeSlot() {
        for (int i = 0; i < this.contents.size(); i++) {
            if (!this.buttons.containsValue(i)) {
                return i;
            }
        }
        return 0;
    }

    public void removeAllButtons() {
        buttons.clear();
        if (callBackButton != null) {
            buttons.put(callBackButton, callBackButton.getItem().getSlot());
        }
    }

    public void closeInventory() {
        p.closeInventory();
        unregisterListener();
    }

    public void unregisterListener() {
        HandlerList.unregisterAll(this);
    }

    public int getSize() {
        return contents.size();
    }

    public Player getPlayer() {
        return p;
    }

    public CustomItem getItem(String name) {
        for (CustomItem item : this.contents.items()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Button getButton(int slot) {
        for (Button b : buttons.keySet()) {
            if (buttons.get(b) == slot) {
                return b;
            }
        }
        return null;
    }

    public void enableAutoRefresh() {
        this.autoRefresh = true;
    }

}
