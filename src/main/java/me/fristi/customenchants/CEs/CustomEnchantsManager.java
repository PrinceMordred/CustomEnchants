package me.fristi.customenchants.CEs;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CustomEnchantsManager {

    // Add Custom Enchantments both here and in the array:
    public static Enchantment HEMORRHAGE = new EnchantmentWrapper("hemorrhage", "Hemorrhage", 1);
    public static Enchantment GRAPPLING = new EnchantmentWrapper("grappling", "Grappling", 1);
    public static final Enchantment[] EnchantmentsArray = { HEMORRHAGE, GRAPPLING };

    public static void RegisterEnchantments(){
        for (Enchantment enchantment : EnchantmentsArray)
            if (!Arrays.stream(Enchantment.values()).toList().contains(enchantment))
                registerEnchantment(enchantment);
    }
    private static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
            System.out.println("Registered custom enchantments");
        } catch (Exception e) {
            System.out.println("Error: could not register CustomEnchants");
            e.printStackTrace();
        }
    }
}