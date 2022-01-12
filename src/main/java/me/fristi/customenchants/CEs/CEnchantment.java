package me.fristi.customenchants.CEs;

import me.fristi.customenchants.CustomEnchants;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.stream.IntStream;

public abstract class CEnchantment extends Enchantment implements Listener {
    private final String Name;
    private final int StartLevel;
    private final int MaxLevel;
    private final boolean IsTreasure;
    private final boolean IsCursed;
    private final EnchantmentTarget EnchantmentTarget;
    private final Enchantment[] MutualExclusives;
    private final ItemStack[] EnchantableItemStacks;

    public CEnchantment(String name, int startLevel, int maxLevel, boolean isTreasure, boolean isCursed, EnchantmentTarget enchantmentTarget, Enchantment[] mutualExclusives, ItemStack[] enchantableItemStacks){
        super(new NamespacedKey(CustomEnchants.getPlugin(), name));
        this.Name = name;
        this.StartLevel = startLevel;
        this.MaxLevel = maxLevel;
        this.IsTreasure = isTreasure;
        this.IsCursed = isCursed;
        this.EnchantmentTarget = enchantmentTarget;
        this.MutualExclusives = mutualExclusives;
        this.EnchantableItemStacks = enchantableItemStacks;
    }

    protected boolean HasEnchantment(ItemStack i){
        return i.getEnchantments().containsKey(Enchantment.getByKey((CustomEnchants.hemorrhage_axe.getKey())));
    }

    @Override    public String              getName() {
        return Name;
    }
    @Override    public int                 getMaxLevel() {
        return MaxLevel;
    }
    @Override    public int                 getStartLevel() {
        return StartLevel;
    }
    @Override    public EnchantmentTarget   getItemTarget() {
        return EnchantmentTarget;
    }
    @Override    public boolean             isTreasure() {
        return IsTreasure;
    }
    @Override    public boolean             isCursed() {
        return IsCursed;
    }
    @Override    public boolean             conflictsWith(Enchantment enchantment) {
        return IntStream.range(0, MutualExclusives.length).anyMatch(i -> MutualExclusives[i] == enchantment);
    }
    @Override    public boolean             canEnchantItem(ItemStack itemStack) {
        return IntStream.range(0, EnchantableItemStacks.length).anyMatch(i -> EnchantableItemStacks[i] == itemStack);
    }
}
