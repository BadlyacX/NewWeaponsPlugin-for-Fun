package com.badlyac.newweapons.HandlePurchase.NPC;

import com.badlyac.newweapons.Weapons.MomIsGone;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.*;

public class VillagerNPC implements Listener {

    private static final List<Material> TRADE_ITEMS = Arrays.asList(
            Material.DIAMOND,
            Material.GOLD_INGOT,
            Material.EMERALD,
            Material.IRON_INGOT,
            Material.NETHERITE_INGOT,
            Material.LAPIS_LAZULI
    );

    public static void spawnVillager(Location location) {
        Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setCustomName("Trader");
        villager.setCustomNameVisible(true);
        villager.setProfession(Villager.Profession.TOOLSMITH);
        villager.setVillagerLevel(2);

        MerchantRecipe recipe = createRandomTrade();
        villager.setRecipes(Collections.singletonList(recipe));
    }

    private static MerchantRecipe createRandomTrade() {
        Random random = new Random();
        ItemStack item1 = new ItemStack(TRADE_ITEMS.get(random.nextInt(TRADE_ITEMS.size())), random.nextInt(5) + 1);
        ItemStack item2 = new ItemStack(TRADE_ITEMS.get(random.nextInt(TRADE_ITEMS.size())), random.nextInt(5) + 1);

        while (item1.getType() == item2.getType()) {
            item2 = new ItemStack(TRADE_ITEMS.get(random.nextInt(TRADE_ITEMS.size())), random.nextInt(5) + 1);
        }

        MerchantRecipe recipe = new MerchantRecipe(MomIsGone.createMomIsGoneSword(), Integer.MAX_VALUE);
        recipe.addIngredient(item1);
        recipe.addIngredient(item2);

        return recipe;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof VillagerNPC) {
            Villager villager = (Villager) event.getRightClicked();
            if ("Trader".equals(villager.getCustomName())) {
                event.setCancelled(true);
                event.getPlayer().openMerchant(villager, true);
            }
        }
    }
}
