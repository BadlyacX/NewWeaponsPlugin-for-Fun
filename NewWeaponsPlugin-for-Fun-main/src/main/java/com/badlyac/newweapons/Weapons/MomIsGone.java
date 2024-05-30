package com.badlyac.newweapons.Weapons;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MomIsGone implements Listener {

    public static ItemStack createMomIsGoneSword() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = sword.getItemMeta();
        assert meta != null;
        meta.setDisplayName("老媽不劍");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 10, true); // 添加 Sharpness 10 等附魔
        sword.setItemMeta(meta);

        return sword;
    }

    public static void giveMomIsGoneSword(Player player) {
        ItemStack sword = createMomIsGoneSword();
        player.getInventory().addItem(sword);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            if (player.getInventory().getItemInMainHand().isSimilar(createMomIsGoneSword())) {
                if (isPlayerWearingNoArmor(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 80, 0, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 4, true, false));
                }
            }
        }
    }

    private boolean isPlayerWearingNoArmor(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }
}
