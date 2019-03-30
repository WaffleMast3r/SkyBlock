package me.wm.hybrid.ro.util.database;

import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public abstract class Database {

    private String databaseFile;

    public Database(String databaseFile) {
        this.databaseFile = databaseFile;
        defaultTables();
    }

    public abstract void defaultTables();

    public boolean exists(String sql){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        boolean b = false;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return b;
    }

    public String getString(String str, String sql) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        String s = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = rs.getString(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    public int getInt(String str, String sql) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        int s = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = rs.getInt(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    private Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void execute(String qry) {
        Connection con = null;
        PreparedStatement p = null;

        con = getConnection();
        try {
            p = con.prepareStatement(qry);
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Connection finalCon = con;
                new BukkitRunnable() {
                            @Override
                            public void run() {
                                try {
                                    finalCon.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                this.cancel();
                            }
                        }.runTaskTimer(Main.getInstance(), 20 * 1, 20);
                if (p != null) {
                    p.close();
                }
            } catch (Exception ignored) {

            }
        }

    }

}
