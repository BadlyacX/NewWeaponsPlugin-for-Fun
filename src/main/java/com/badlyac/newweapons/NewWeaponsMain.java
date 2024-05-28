package com.badlyac.newweapons;

import com.badlyac.newweapons.ConditionOfPurchase.DiamondToKnockbackWeapon;
import com.badlyac.newweapons.Weapons.KnockbackWeapon;
import com.badlyac.newweapons.Weapons.MomIsGone;
import org.bukkit.plugin.java.JavaPlugin;

public class NewWeaponsMain extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new KnockbackWeapon(), this);
        getServer().getPluginManager().registerEvents(new DiamondToKnockbackWeapon(this),this);
        getServer().getPluginManager().registerEvents(new MomIsGone(), this);
    }
}
