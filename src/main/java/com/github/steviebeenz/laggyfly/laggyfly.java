package com.github.steviebeenz.laggyfly;
import com.github.steviebeenz.laggyfly.FlightAlertEvent;

import java.util.List;
import java.util.ArrayList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.entity.EntityType; 
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.github.steviebeenz.getgrounddist.GetGroundDist;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import me.godead.lilliputian.Dependency;
import me.godead.lilliputian.Lilliputian;
import me.godead.lilliputian.Repository;

public class laggyfly extends Thread implements Listener {

    public void Setup(JavaPlugin instance) {
        final Lilliputian lilliputian = new Lilliputian(instance);
        lilliputian.getDependencyBuilder()
        .addDependency(new Dependency(
                        Repository.JITPACK,
                        "com.github.steviebeenz",
                        "getgrounddist",
                        "main-SNAPSHOT"))
        .addDependency(new Dependency(
                        "https://papermc.io/repo/repository/maven-public/",
                        "io.papermc",
                        "paperlib",
                        "1.0.5"))
        .loadDependencies();
        Bukkit.getServer().getPluginManager().registerEvents(this, instance);
        PaperLib.suggestPaper(instance);
    }
    

    @EventHandler   
    public void isPLayerFlying(PlayerMoveEvent event, JavaPlugin instance) {
        Player player = event.getPlayer();
        // REDUCE LAG BY RUNNING ONLY SOMETIMES
        int rand12 = (Math.random() <= 0.5) ? 1 : 4;
        if (rand12 == 4) {
            if (!player.isOnGround() && !player.getAllowFlight()) {
            if (GetGroundDist.getDistance(player) > 10) {
                // on ground
                    Material m = player.getLocation().getBlock().getType();
                    if (m == Material.STATIONARY_WATER || m == Material.WATER) {
                        // player is in water
                    } else {
                        long oldPos = GetGroundDist.getDistance(player);
                        double oldY = player.getLocation().getY();
                        new BukkitRunnable() {
        
            @Override
            public void run() {
                // What you want to schedule goes here
                
        if (!player.isOnGround()) {
            if (GetGroundDist.getDistance(player) > 2) {
            } else {
                long oldPosToCheck = oldPos-5;
                double oldYToCheck = oldY - 2;
            if (player.getLocation().getY() > oldY || GetGroundDist.getDistance(player) == oldPosToCheck || GetGroundDist.getDistance(player) > oldPosToCheck) {
                // on ground
                    Material m = player.getLocation().getBlock().getType();
                    if (m == Material.STATIONARY_WATER || m == Material.WATER) {
                        // player is in water
                    } else {
                        GetGroundDist getGroundDist = new GetGroundDist();
                        getGroundDist.goToGround(player);
                        FlightAlertEvent flightAlertEvent = new FlightAlertEvent(player);
                        Bukkit.getServer().getPluginManager().callEvent(flightAlertEvent);
                    }
                
            } /*else {
                
            }*/
        }
    
    }
            }
            
        }.runTaskLater(instance,20);
                        //confLowerOnFly(player);
                    }
                
            } /*else {
                
            }*/
        }
    }
    }
}