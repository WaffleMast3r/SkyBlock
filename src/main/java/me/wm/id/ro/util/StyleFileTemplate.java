package me.wm.id.ro.util;

import me.wm.id.ro.util.Language.CustomMessage.CustomMessage;
import me.wm.id.ro.util.Language.CustomMessage.CustomMessageContents;
import me.wm.id.ro.util.gui.CustomFillItem;
import me.wm.id.ro.util.gui.CustomItem;
import me.wm.id.ro.util.gui.GUIContents;
import me.wm.id.ro.util.scoreboard.ScoreBoardContents;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StyleFileTemplate {

    public HashMap<String, ScoreBoardContents> getScoreBoards() {
        HashMap<String, ScoreBoardContents> scoreBoards = new HashMap<>();

        return scoreBoards;
    }

    public HashMap<String, CustomMessageContents> getMessages() {
        HashMap<String, CustomMessageContents> messages = new HashMap<>();

        messages.put("in_progress_type", new CustomMessageContents() {
            @Override
            public String getText() {
                return "%prefix% $0Ne pare rau nu puteti folosi $1{Type} $0momentan$2! $0Este in lucru$2!";
            }

            @Override
            public ArrayList<CustomMessage.Replacement> getReplacements() {
                return null;
            }
        });

        return messages;
    }

    //ca sa scrii un lore faci asa

    public HashMap<String, GUIContents> getGUIS() {
        HashMap<String, GUIContents> guis = new HashMap<>();

        guis.put("Busola", new GUIContents() {
            @Override
            public String title() {
                return "$0Selecteaza o sectiune";
            }

            @Override
            public int size() {
                return 45;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.DIAMOND_SWORD, 0, 10, 1, "SkyPvP", "$0$5SkyPvP", Arrays.asList(
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------",
                                "",
                                "$1 * $2Pe aceasta sectiune trebuie sa te lupti",
                                "$1 * $2impotriva celorlalti jucatori. Explorand",
                                "$1 * $2insulele, vei gasi semne cu diferite iteme",
                                "$1 * $2pe care le poti lua si cu care trebuie sa",
                                "$1 * $2iti imbunatatesti echipamentul pentru a dovedi",
                                "$1 * $2ca tu esti regele insulelor. Ai grija sa nu cazi!",
                                "",
                                "$0$5 * $2Click pentru a te conecta",
                                "$0$5 * {Status}: $0{Online}",
                                "",
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------"), null),
                        new CustomItem(null, Material.LAVA_BUCKET, 0, 16, 1, "SkyBlock", "$0$5SkyBlock", Arrays.asList(
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------",
                                "",
                                "$1 * $2Pe aceasta sectiune trebuie sa supravietuiesti",
                                "$1 * $2pe o insula, singur, fara niciun ajutor.",
                                "$1 * $2Impreuna cu animalul tau de companie va trebui",
                                "$1 * $2sa treceti prin clipe grele, batalii, si multe altele",
                                "$1 * $2pentru a arata tuturor jucatoriilor de pe server",
                                "$1 * $2ca tu ai cea mai mare si frumoasa insula dintre toti!",
                                "",
                                "$0$5 * $2Click pentru a te conecta",
                                "$0$5 * {Status}: $0{Online}",
                                "",
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------"), null),
                        new CustomItem(null, Material.ANVIL, 0, 22, 1, "CreateServer", "$0$5Creeaza un server", Arrays.asList(
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------",
                                "",
                                "$1$5NOU IN ROMANIA!",
                                "$1 * $2Hybrid va prezinta primul server din Romania cu sistem",
                                "$1 * $2de creeare a serverelor pe baza de $0TOKEN-URI$2",
                                "$1 * $2pe care le puteti controla si pe care le puteti",
                                "$1 * $2customiza cum doriti voi! Aveti optiuni de la",
                                "$1 * $2de la numarul de jucatori care poate intra, pana",
                                "$1 * $2la ce plugin-uri doriti sa aveti. Toate",
                                "$1 * $2aceste servere sunt hostate la $0FASTVM.io$2.",
                                "",
                                "$1 * $2Broght to you by $0Hybrid.Minecraft-Romania.Ro",
                                "",
                                "$0$5 * $2Click stanga pentru a vedea lista serverelor",
                                "$0$5 * $2Click dreapta pentru a creea un server",
                                "",
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------"), null),
                        new CustomItem(null, Material.CHAINMAIL_CHESTPLATE, 0, 28, 1, "ThePit", "$0$5The Pit", Arrays.asList(
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------",
                                "",
                                "$1 * $2Pe aceasta sectiune trebuie sa te bati cu multi jucatori",
                                "$1 * $2pentru a debloca mai multe upgrade-uri si pentru a putea",
                                "$1 * $2sa iti infrunti adversarii fara nicio teama ca ei te vor bate.",
                                "$1 * $2Cu cat omori mai multi jucatori, cu atat cresti in nivel,",
                                "$1 * $2si cu atat ti se deblocheaza mai multe facilitati de care",
                                "$1 * $2doar jucatorii la nivelul tau mai beneficiaza!",
                                "$1 * $2Demonstreaza-le celorlalti ca tu esti cel mai bun!",
                                "",
                                "$0$5 * $2Click pentru a te conecta",
                                "$0$5 * {Status}: $0{Online}",
                                "",
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------"), null),
                        new CustomItem(null, Material.BEDROCK, 0, 34, 1, "Build", "$0$5Build", Arrays.asList(
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------",
                                "",
                                "$1 * $2Aceasta sectiune este dedicata doar builderilor",
                                "$1 * $2acestui server. Accesul jucatorilor este interzis.",
                                "",
                                "$0$5 * $2Click pentru a te conecta",
                                "$0$5 * {Status}: $0{Online}",
                                "",
                                "$3$6-------$2$5[ $0Hybrid$2-$4Network $2$5]$3$6-------"), null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[]{
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 2, "0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44", 1, "Fill1", "$0Hybrid", null, null),
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 7, "1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,38,39,41,43", 1, "Fill2", "$0Hybrid", null, null)
                };
            }
        });

        guis.put("CreateServer", new GUIContents() {
            @Override
            public String title() {
                return "$2• $0Alege tipul server-ului $2•";
            }

            @Override
            public int size() {
                return 27;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.ANVIL, 0, 10, 1, "Forge", "$1» $0Forge $1«", null, null),
                        new CustomItem(null, Material.GOLDEN_APPLE, 0, 13, 1, "UHC", "$1» $0UHC $1«", null, null),
                        new CustomItem(null, Material.GRASS, 0, 16, 1, "Spigot", "$1» $0Spigot $1«", null, null),
                        new CustomItem(null, Material.STAINED_GLASS_PANE, 1, 18, 1, "CallBack", "$0«", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[]{
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 2, "0,2,4,6,8,10,12,14,16,18,20,22,24,26", 1, "Fill1", "$0Hybrid", null, null),
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 7, "1,3,5,7,9,11,13,15,17,19,21,23,25", 1, "Fill2", "$0Hybrid", null, null)
                };
            }
        });

        guis.put("ChooseSpigotVersion", new GUIContents() {
            @Override
            public String title() {
                return "$2• $0Alege versiunea spigot-ului $2•";
            }

            @Override
            public int size() {
                return 45;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.LAVA_BUCKET, 0, -1, 1, "Spigot", "$1» $0{Name} $2- $0{Version} $1«", null, null),
                        new CustomItem(null, Material.ARROW, 0, 39, 1, "Previous", "Previous", null, null),
                        new CustomItem(null, Material.ARROW, 0, 41, 1, "Next", "Next", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[]{
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 2, "0,2,4,6,8,18,26,36,38,40,42,44", 1, "Fill1", "$0Hybrid", null, null),
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 7, "1,3,5,7,9,17,27,35,37,39,41,43", 1, "Fill2", "$0Hybrid", null, null)
                };
            }
        });

        guis.put("ChooseSpigotPlugins", new GUIContents() {
            @Override
            public String title() {
                return "$2• $0Alege plugin-urile server-ului $2•";
            }

            @Override
            public int size() {
                return 45;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.EMERALD_BLOCK, 0, 40, 1, "Confirm", "$1» $0Confirm $1«", Arrays.asList(
                                "$3● $0Pret Plugin-uri$3: $2{Plugins_Price}",
                                "$3● $0Pret Total$3: $2{Total_Price}",
                                "$3● $0Token-urile Tale$3: $2{Tokens}",
                                "$3● $0Plugin-uri selectate$3: $2{Selected}"
                        ), null),
                        new CustomItem(null, Material.DIRT, 0, -1, 1, "Plugin_Disabled", "$1» $0{Name} $2- $0{Version} $1«", Arrays.asList(
                                "$3● $0Nume$3: $2{Name}",
                                "$3● $0Versiune$3: $2{Version}",
                                "$3● $0Pret$3: $2{Price}",
                                "",
                                "$3● $0Status$3: $2{Enabled}"
                        ), null),
                        new CustomItem(null, Material.GRASS, 0, -1, 1, "Plugin_Enabled", "$1» $0{Name} $2- $0{Version} $1«", Arrays.asList(
                                "$3● $0Nume$3: $2{Name}",
                                "$3● $0Versiune$3: $2{Version}",
                                "$3● $0Pret$3: $2{Price}",
                                "",
                                "$3● $0Status$3: $2{Enabled}"
                        ), null),
                        new CustomItem(null, Material.ARROW, 0, 39, 1, "Previous", "Previous Page", null, null),
                        new CustomItem(null, Material.ARROW, 0, 41, 1, "Next", "Next Page", null, null)
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[]{
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 2, "0,2,4,6,8,18,26,36,38,40,42,44", 1, "Fill1", "$0Hybrid", null, null),
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 7, "1,3,5,7,9,17,27,35,37,39,41,43", 1, "Fill2", "$0Hybrid", null, null)
                };
            }
        });

        guis.put("ServerList", new GUIContents() {
            @Override
            public String title() {
                return "$2• $0Lista cu servere $2•";
            }

            @Override
            public int size() {
                return 45;
            }

            @Override
            public CustomItem[] items() {
                return new CustomItem[]{
                        new CustomItem(null, Material.GRASS, 0, -1, 1, "Spigot", "Spigot Server", null, null),
                        new CustomItem(null, Material.BOOK, 0, 40, 1, "ShowAll", "Click here to Show all servers", null, null),
                        new CustomItem(null, Material.WRITTEN_BOOK, 0, 40, 1, "ShowYours", "Click here to show your servers!", null, null),
                        new CustomItem(null, Material.ARROW, 0, 39, 1, "Previous", "Previous Page", null, null),
                        new CustomItem(null, Material.ARROW, 0, 41, 1, "Next", "Next Page", null, null),
                        new CustomItem(null, Material.STAINED_GLASS_PANE, 1, 36, 1, "CallBack", "$0«", null, null),
                };
            }

            @Override
            public CustomFillItem[] fillItems() {
                return new CustomFillItem[]{
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 2, "0,2,4,6,8,18,26,36,38,40,42,44", 1, "Fill1", "$0Hybrid", null, null),
                        new CustomFillItem(null, Material.STAINED_GLASS_PANE, 7, "1,3,5,7,9,17,27,35,37,39,41,43", 1, "Fill2", "$0Hybrid", null, null)
                };
            }
        });

        return guis;
    }

}
