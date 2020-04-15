package com.jwere.swingy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

import com.jwere.swingy.model.artifact.Armor;
import com.jwere.swingy.model.artifact.Helm;
import com.jwere.swingy.model.artifact.Weapon;
import com.jwere.swingy.model.character.Hero;
import com.jwere.swingy.model.character.HeroBuilder;

public class Database{

    private static final String DB_URL = "jdbc:sqlite::resource:heroes.db";
    private static Connection connection;

    //ref: https://www.javatpoint.com/java-sqlite

    //establish connection to database
    public static void connect(){
        Connection conn = null;

        try{
            //register JDBC driver
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Database.connection = conn;
    }

    public static Connection getConnection(){
        if (Database.connection == null)
            Database.connect();
        return Database.connection;
    }

    public static ArrayList<String> selectAll(){

        ArrayList<String> arrayList = new ArrayList<>();
        String query = "SELECT * FROM heroes";

        try(Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(query)){
            // inserting all names and heroclasses into ArrayList
            for (int i = 1; rs.next(); i++){
                arrayList.add(String.format("%d. %s [%s]", i, rs.getString("name"), rs.getString("class")));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    public static int insert(String name, String className, int level, int xp, int attack, int defense, int hp) {
        String sqlQuery = "INSERT INTO heroes(name, class, level, xp, attack, defense, hp) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setString(1, name);
            pstmt.setString(2, className);
            pstmt.setInt(3, level);
            pstmt.setInt(4, xp);
            pstmt.setInt(5, attack);
            pstmt.setInt(6, defense);
            pstmt.setInt(7, hp);
            pstmt.executeUpdate();

            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence WHERE name=\"heroes\"");
            if (rs.next())
                id = rs.getInt("seq");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //this will be the id of the newly inserted hero
        return id;
    }

    public static Hero selectHeroById(int id) {
        String sqlQuery = "SELECT * FROM heroes WHERE id = ?";
        Hero hero = null;

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                HeroBuilder builder = new HeroBuilder();
                builder.setId(rs.getInt("id"));
                builder.setName(rs.getString("name"));
                builder.setHeroClass(rs.getString("class"));
                builder.setLevel(rs.getInt("level"));
                builder.setExperience(rs.getInt("xp"));
                builder.setAttack(rs.getInt("attack"));
                builder.setDefense(rs.getInt("defense"));
                builder.setHitPoints(rs.getInt("hp"));

                if (rs.getString("weapon_name") != null)
                    builder.setWeapon(new Weapon(rs.getString("weapon_name"), rs.getInt("weapon_value")));
                if (rs.getString("helm_name") != null)
                    builder.setHelm(new Helm(rs.getString("helm_name"), rs.getInt("helm_value")));
                if (rs.getString("armor_name") != null)
                    builder.setArmor(new Armor(rs.getString("armor_name"), rs.getInt("armor_value")));

                hero = builder.getHero();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hero;
    }

    public static void updateHero(Hero hero) {
        String sqlQuery = "UPDATE heroes SET level = ?, xp = ?, attack = ?, defense = ?, hp = ? , " +
                "weapon_name = ?, weapon_value = ?, helm_name = ?, helm_value = ?, armor_name = ?, armor_value = ? " +
                "WHERE id = ?";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)) {
            pstmt.setInt(1, hero.getLevel());
            pstmt.setInt(2, hero.getExperience());
            pstmt.setInt(3, hero.getAttack());
            pstmt.setInt(4, hero.getDefense());
            pstmt.setInt(5, hero.getHitPoints());

            if (hero.getWeapon() != null) {
                pstmt.setString(6, hero.getWeapon().getName());
                pstmt.setInt(7, hero.getWeapon().getPoints());
            }
            if (hero.getHelm() != null) {
                pstmt.setString(8, hero.getHelm().getName());
                pstmt.setInt(9, hero.getHelm().getPoints());
            }
            if (hero.getArmor() != null) {
                pstmt.setString(10, hero.getArmor().getName());
                pstmt.setInt(11, hero.getArmor().getPoints());
            }

            pstmt.setInt(12, hero.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(){
        try{
            if (Database.connection != null){
                // close connection to the database
                Database.connection.close();
            }
            Database.connection = null;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}