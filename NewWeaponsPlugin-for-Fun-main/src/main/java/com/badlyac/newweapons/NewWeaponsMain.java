package com.badlyac.newweapons;

import com.badlyac.newweapons.HandlePurchase.DiamondToKnockbackWeapon;
import com.badlyac.newweapons.HandlePurchase.NPC.ShowNPCWhenPlayerJoin.ShowNPCWhenPlayerJoin;
import com.badlyac.newweapons.HandlePurchase.NPC.VillagerNPC;
import com.badlyac.newweapons.Weapons.KnockbackWeapon;
import com.badlyac.newweapons.Weapons.MomIsGone;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public class NewWeaponsMain extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new KnockbackWeapon(), this);
        getServer().getPluginManager().registerEvents(new DiamondToKnockbackWeapon(this),this);
        getServer().getPluginManager().registerEvents(new MomIsGone(), this);
        getServer().getPluginManager().registerEvents(new VillagerNPC(),this);
        getServer().getPluginManager().registerEvents(new ShowNPCWhenPlayerJoin(),this);
    }
    @Override
    public void onDisable() {

    }
}
