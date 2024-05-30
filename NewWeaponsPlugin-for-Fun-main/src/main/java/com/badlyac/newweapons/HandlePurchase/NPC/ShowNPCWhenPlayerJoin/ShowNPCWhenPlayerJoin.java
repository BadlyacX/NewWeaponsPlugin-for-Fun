package com.badlyac.newweapons.HandlePurchase.NPC.ShowNPCWhenPlayerJoin;


import com.badlyac.newweapons.HandlePurchase.NPC.VillagerNPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ShowNPCWhenPlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location npcLocation = player.getLocation();
        VillagerNPC.spawnVillager(npcLocation);
    }
}
