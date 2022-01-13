package me.fristi.customenchants;

import me.fristi.customenchants.CEs.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;


public class CustomEnchantsPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        CustomEnchants.register();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() { }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        axe.addUnsafeEnchantment(CustomEnchants.HEMORRHAGE, 1);
        event.getPlayer().getInventory().setItemInMainHand(axe);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            if(((Player)(event.getDamager())).getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.HEMORRHAGE)){
                event.getEntity().setGlowing(true);
            }
        }
    }
    @EventHandler
    public void onPrepEnchant(PrepareItemEnchantEvent event){
        event.getEnchanter().sendMessage("Enchanting");
        if(event.getItem().getType().equals(Material.DIAMOND_AXE)){
            if (Random(0, 100) < 100){ //50% kans
                event.getOffers()[Random(0, 2)] = new EnchantmentOffer(Enchantment.BINDING_CURSE, 1, 69);
                event.getEnchanter().sendMessage("Set EnchantmentOffer");
            }
        }
    }
    @EventHandler
    public void onEnchant(EnchantItemEvent event){
        // Set enchantment
        Map<Enchantment, Integer> oldEnchantMap = event.getEnchantsToAdd();
        oldEnchantMap.put(CustomEnchants.HEMORRHAGE, 1);

        // Get current lore
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        // Update lore
        lore.add("Hemmorrhage 18+");
        meta.setLore(lore);
        event.getItem().setItemMeta(meta);
    }

    private int Random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
