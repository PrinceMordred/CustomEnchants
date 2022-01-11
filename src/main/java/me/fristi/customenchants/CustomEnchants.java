package me.fristi.customenchants;

import me.fristi.customenchants.CEs.Grappling_Hook_Fishing_Rod;
import me.fristi.customenchants.CEs.Hemorrhage_Axe;
import me.fristi.customenchants.listeners.JoinListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;



public final class CustomEnchants extends JavaPlugin {


    static CustomEnchants plugin;
    public ArrayList<Enchantment> Enchants;
    public static Hemorrhage_Axe hemorrhage_axe;
    public static Grappling_Hook_Fishing_Rod grappling_hook_fishing_rod;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        hemorrhage_axe = new Hemorrhage_Axe("hemorrhage");
        grappling_hook_fishing_rod =new Grappling_Hook_Fishing_Rod("grappling_hook_fishing_rod");

        Enchants = new ArrayList<Enchantment>(){
            {
                //add enchants here
                add(hemorrhage_axe);
                add(grappling_hook_fishing_rod);
            }
        };
        for (Enchantment enchant : Enchants) {
            registerEnchantment(enchant);
        }
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        //Register listener(s) in the enchantments
        this.getServer().getPluginManager().registerEvents(hemorrhage_axe, this);
        this.getServer().getPluginManager().registerEvents(grappling_hook_fishing_rod, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for (Enchantment enchant : Enchants) {
                if (byKey.containsKey(enchant.getKey())) {
                    byKey.remove((enchant.getKey()));
                }
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for (Enchantment enchant : Enchants) {
                byName.remove((enchant.getName()));
            }
        } catch (Exception ignored) { }


    }
    //Load custom enchantments
    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

    public static CustomEnchants getPlugin(){
        return plugin;
    }
}
