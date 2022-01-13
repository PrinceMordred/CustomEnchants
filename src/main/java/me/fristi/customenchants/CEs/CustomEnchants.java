package me.fristi.customenchants.CEs;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {
    public static final Enchantment HEMORRHAGE = new EnchantmentWrapper("hemorrhage", "Hemorrhage", 1);

    public static void register(){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(HEMORRHAGE);
        if (!registered)
            registerEnchantment(HEMORRHAGE);
    }
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
        if (registered) System.out.println("Registered!");
        else System.out.println("Did not register...");
    }
}