package me.fristi.customenchants.CEs;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantmentWrapper extends Enchantment {

    private final String name;
    private final int maxLvl;
    private final boolean isTreasure;
    private final boolean isCursed;

    public CustomEnchantmentWrapper(String namespace, String name, int maxLvl, boolean isTreasure, boolean isCursed) {
        super(NamespacedKey.minecraft(namespace));
        this.name = name;
        this.maxLvl = maxLvl;
        this.isTreasure = isTreasure;
        this.isCursed = isCursed;
    }

    @Override
    public String getName() { //TODO is dit nodig?
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLvl;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        throw new NotImplementedException(); //TODO niet netjes of wel
    }

    @Override
    public boolean isTreasure() {
        return isTreasure;
    }

    @Override
    public boolean isCursed() {
        return isCursed;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
