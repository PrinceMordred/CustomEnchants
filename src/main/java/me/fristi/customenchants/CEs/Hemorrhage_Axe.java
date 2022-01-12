package me.fristi.customenchants.CEs;

import me.fristi.customenchants.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Hemorrhage_Axe extends CEnchantment implements Listener {

    public Hemorrhage_Axe() {
        super("hemorrhage", 1, 5, false, false,
                null, null, new ItemStack[] { new ItemStack(Material.DIAMOND_AXE) });
    }

    public static boolean CanBeOn(ItemStack itemStack) { return itemStack.getType().equals(Material.DIAMOND_AXE); }

    @EventHandler
    public void OnPlayerHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player && HasEnchantment(player.getInventory().getItemInMainHand()))
            event.getEntity().setGlowing(true);
    }
}
