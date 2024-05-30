package com.badlyac.newweapons.Weapons;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class KnockbackWeapon implements Listener {

    private static final String ITEM_NAME = "仙人跳";
    private static final int BASE_KNOCKBACK = 1;
    private static final int MAX_KNOCKBACK = 256;
    private static final int MAX_STACK_SIZE = 64;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isKnockbackWeapon(item)) {

        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            ItemStack weapon = damager.getInventory().getItemInMainHand().getType().equals(Material.SLIME_BLOCK) ? damager.getInventory().getItemInMainHand() : null;

            if (isKnockbackWeapon(weapon)) {
                Entity victim = event.getEntity();
                int amount = weapon.getAmount();
                double knockbackHeight = (double) amount / MAX_STACK_SIZE * MAX_KNOCKBACK;
                knockbackHeight = Math.max(knockbackHeight, BASE_KNOCKBACK);

                Vector knockbackVector = damager.getLocation().getDirection().normalize().multiply(0.5).setY(knockbackHeight);
                victim.setVelocity(knockbackVector);
            }
        }
    }
    @EventHandler
    public void onPlayerPlace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (isKnockbackWeapon(item)) {
            event.setCancelled(true);
        }
    }

    private boolean isKnockbackWeapon(ItemStack item) {
        if (item == null || item.getType() != Material.SLIME_BLOCK) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasEnchant(Enchantment.MENDING)) {
            return false;
        }

        return ITEM_NAME.equals(meta.getDisplayName());
    }

    public ItemStack createKnockbackWeapon() {
        ItemStack item = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ITEM_NAME);
            meta.addEnchant(Enchantment.MENDING, 1, true);
            item.setItemMeta(meta);
        }

        return item;
    }
    public void giveKnockbackWeapon(Player player) {
        ItemStack item = createKnockbackWeapon();
        player.getInventory().addItem(item);
    }

}
