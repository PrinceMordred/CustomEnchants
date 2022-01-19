package me.fristi.customenchants;

import me.fristi.customenchants.CEs.CustomEnchantment;
import me.fristi.customenchants.CEs.Grappling;
import me.fristi.customenchants.CEs.Hemorrhage;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.*;


public class CustomEnchantsPlugin extends JavaPlugin implements Listener {

    public static final CustomEnchantment[] Enchantments = { new Hemorrhage(), new Grappling() };

    @Override
    public void onEnable() {
        registerEnchantments();
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    private void registerEnchantments(){
        for (Enchantment enchantment : Enchantments)
            if (!Arrays.stream(Enchantment.values()).toList().contains(enchantment)) //if not registered yet
                try {
                    Field f = Enchantment.class.getDeclaredField("acceptingNew");
                    f.setAccessible(true);
                    f.set(null, true);
                    Enchantment.registerEnchantment(enchantment);
                    System.out.println("CustomEnchantments: Registered " + enchantment.getName());
                } catch (Exception e) {
                    System.out.println("CustomEnchantments: Error: could not register " + enchantment.getName());
                    e.printStackTrace();
                }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ItemStack gift1 = new ItemStack(Material.WOODEN_AXE);
        gift1.addUnsafeEnchantment(Enchantments[0], 1);
        event.getPlayer().getInventory().addItem(gift1);
        ItemStack gift2 = new ItemStack(Material.FISHING_ROD);
        gift2.addUnsafeEnchantment(Enchantments[1], 1);
        event.getPlayer().getInventory().addItem(gift2);
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event)    { CallEventListeners(event); }
    @EventHandler
    public void onPrepEnchant(PrepareItemEnchantEvent event)    { CallEventListeners(event); }
    @EventHandler
    public void onEnchant(EnchantItemEvent event)               { CallEventListeners(event); }
    @EventHandler
    public void invClick(InventoryClickEvent event)             { CallEventListeners(event); }
    @EventHandler
    public void AnvilEvent(PrepareAnvilEvent event)             { CallEventListeners(event); }
    @EventHandler
    public void GrapplingEvent(PlayerFishEvent event)           { CallEventListeners(event); }

    private void CallEventListeners(Event event) {
        for (CustomEnchantment enchantment : Enchantments)
            enchantment.EventListener(event);
    }
}
