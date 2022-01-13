package me.fristi.customenchants;

import me.fristi.customenchants.CEs.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomEnchantsPlugin extends JavaPlugin implements Listener {


    static CustomEnchantsPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        CustomEnchants.register();
        this.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player player){
            if(player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.HEMORRHAGE)){
                event.getEntity().setGlowing(true);
            }
        }
    }
    @EventHandler
    public void onEnchant(PrepareItemEnchantEvent event){
        if(event.getEnchanter().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_AXE)){
            event.getEnchanter().sendMessage("nice axe bro");
            event.getOffers()[1] = new EnchantmentOffer(CustomEnchants.HEMORRHAGE, 1, 69);
        }
    }
    public static CustomEnchantsPlugin getPlugin(){
        return plugin;
    }
}
