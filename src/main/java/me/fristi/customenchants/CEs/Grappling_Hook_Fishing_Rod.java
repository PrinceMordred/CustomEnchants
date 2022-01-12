package me.fristi.customenchants.CEs;

import me.fristi.customenchants.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Grappling_Hook_Fishing_Rod extends CEnchantment implements Listener {
    private ArrayList<Enchantment> enchantableItemStacks = new ArrayList<>();

    public Grappling_Hook_Fishing_Rod() {
        super("grappling_hook_fishing_rod", 1, 4, false, false,
                null, null, new ItemStack[] { new ItemStack(Material.FISHING_ROD) });
        enchantableItemStacks.add(this);
    }

    public static boolean CanBeOn(ItemStack itemStack) { return itemStack.getType().equals(Material.FISHING_ROD); }

    @EventHandler
    public void OnPlayerHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player player && HasEnchantment(player.getInventory().getItemInMainHand()))
            e.getEntity().setGravity(false);
    }
}