package com.badlyac.newweapons.HandlePurchase;

import com.badlyac.newweapons.Weapons.KnockbackWeapon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondToKnockbackWeapon implements Listener {

    private final JavaPlugin plugin;
    private final KnockbackWeapon knockbackWeapon;
    private Listener currentListener;

    public DiamondToKnockbackWeapon(JavaPlugin plugin) {
        this.plugin = plugin;
        this.knockbackWeapon = new KnockbackWeapon();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.DIAMOND && item.getAmount() >= 32) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.sendMessage(ChatColor.YELLOW + "確定要購買“仙人跳”嗎? 請在聊天欄中輸入 'yes' 或 '是'");
                if (currentListener != null) {
                    HandlerList.unregisterAll(currentListener);
                }
                currentListener = new ChatListener(player);
                plugin.getServer().getPluginManager().registerEvents(currentListener, plugin);
            } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                player.sendMessage(ChatColor.RED + "購買已取消。");
                if (currentListener != null) {
                    HandlerList.unregisterAll(currentListener);
                    currentListener = null;
                }
            }
        }
    }

    private class ChatListener implements Listener {
        private final Player player;

        public ChatListener(Player player) {
            this.player = player;
        }

        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent event) {
            if (event.getPlayer().equals(player) && (event.getMessage().equalsIgnoreCase("yes") || event.getMessage().equalsIgnoreCase("是"))) {
                Bukkit.getScheduler().runTask(plugin, () -> {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType() == Material.DIAMOND && item.getAmount() >= 16) {
                        item.setAmount(item.getAmount() - 16);
                        knockbackWeapon.giveKnockbackWeapon(player);
                        player.sendMessage(ChatColor.GREEN + "你已經成功購買了“仙人跳”!");
                    } else {
                        player.sendMessage(ChatColor.RED + "購買失敗，請確認你有足夠的鑽石。");
                    }
                    HandlerList.unregisterAll(ChatListener.this);
                    currentListener = null;
                });
            } else {
                HandlerList.unregisterAll(ChatListener.this);
                currentListener = null;
            }
        }
    }
}
