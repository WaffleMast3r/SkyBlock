package me.wm.hybrid.ro.util.scoreboard;

import me.wm.hybrid.ro.util.Language.LanguageManager;
import me.wm.hybrid.ro.util.Language.LanguageUpdateEvent;
import me.wm.hybrid.ro.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************************************
 *
 * Copyright (c) 2018
 *
 * This class was crated on 5/12/18 6:45 PM in project The Pit by WaffleMast3r
 *
 * You cannot use this code without permission
 * ask on developer_waffle@gmail.com for permission
 *
 **************************************************************************************************/

@SuppressWarnings("deprecation")
public class ScoreBoard implements Listener {

    private Scoreboard board;
    private Objective obj;
    private Player p;
    private ScoreBoardContents contents;
    private String scoreboardName;

    private HashMap<String, Action> placeHolders;
    private ArrayList<String> used;
    private BukkitTask bukkitTask;

    public ScoreBoard(Player p, String scoreboardName) {
        this.p = p;
        this.scoreboardName = scoreboardName;

        placeHolders = new HashMap<>();
        used = new ArrayList<>();
    }

    public void startScoreboard() {
        this.contents = LanguageManager.getInstance().getStyleFile(p).getScoreBoard(scoreboardName);
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("WAFFLE", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(convert(contents.getTitle()));

        int lineNumber = contents.getLines().size();
        String teamName = "Team-" + lineNumber;
        HashMap<Team, String> teams = new HashMap<>();

        for (String line : contents.getLines()) {
            Team localTeam = board.registerNewTeam(teamName);
            String[] split = split(convert(line));
            String entry = fixDuplicates(split[1]);

            localTeam.addEntry(entry);
            localTeam.setPrefix(split[0]);
            localTeam.setSuffix(split[2]);

            obj.getScore(entry).setScore(lineNumber);
            teams.put(localTeam, line);

            lineNumber--;
            teamName = "Team-" + lineNumber;
        }

        p.setScoreboard(board);

        bukkitTask = new BukkitRunnable() {

            @Override
            public void run() {
                used.clear();
                for (Team localTeam : teams.keySet()) {
                    String line = teams.get(localTeam);
//                    Logger.debug("`" + line + "`");
                    String text = convert(line);
//                    Logger.debug("`" + text + "`");
                    String[] split = split(text);
                    String entry = fixDuplicates(split[1]);

                    for (String s : localTeam.getEntries()) {
                        localTeam.removeEntry(s);
                    }

                    localTeam.addEntry(entry);
                    localTeam.setPrefix(split[0]);
                    localTeam.setSuffix(split[2]);
                }
            }
        }.runTaskTimer(Main.getInstance(), 100, contents.getTickRefresh());

    }

    private String fixDuplicates(String str) {
        while (used.contains(str)) {
            str += "§r";
        }
        if (str.length() > 48) str = str.substring(0, 47);

        used.add(str);
        return str;
    }

    private String[] split(String s) {

        if (s.length() <= 16) return new String[]{s, "", ""};
        if (s.length() <= 36) return new String[]{s.substring(0, 16), s.substring(16, s.length()), ""};
        return new String[]{s.substring(0, 16), s.substring(16, 36), s.substring(36, s.length())};

    }

    private void stopScoreboard() {
        bukkitTask.cancel();
        HandlerList.unregisterAll(this);
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @EventHandler
    public void onLanguageChange(LanguageUpdateEvent e) {
        if (!e.getPlayer().equals(p)) return;
        stopScoreboard();
        startScoreboard();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (!e.getPlayer().equals(p)) return;
        if (!e.getPlayer().getScoreboard().equals(board)) return;
        stopScoreboard();
        HandlerList.unregisterAll(this);
    }

    private String convert(String str) {
        Logger.debug("String before: " + str);
        for (String ph : placeHolders.keySet()) {
            str = str.replace(ph, placeHolders.get(ph).getValue());
        }
        Logger.debug("String after: " + str);
        return LanguageManager.getInstance().getStyleFile(p).translateColor(str);
    }

    public void setPlaceHolders(HashMap<String, Action> placeHolders) {
        this.placeHolders = placeHolders;
    }
}

