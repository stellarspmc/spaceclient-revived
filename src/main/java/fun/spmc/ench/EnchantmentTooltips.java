package fun.spmc.ench;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentTooltips {
    public static String getDescription(Enchantment enchantment) {
        final String key = getTranslationKey(enchantment);
        return I18n.format(key);
    }

    /**
     * Reads a List of enchantments from an ItemEnchantedBook stack.
     *
     * @param book  Instance of ItemEnchantedBook, as it uses non-static methods for some
     *              reason.
     * @param stack The stack to read the data from.
     * @return The list of enchantments stored on the stack.
     */
    public static List<Enchantment> getEnchantments(ItemEnchantedBook book, ItemStack stack) {

        final NBTTagList enchTags = book.getEnchantments(stack);
        final List<Enchantment> enchantments = new ArrayList<>();

        if (enchTags != null) {
            for (int index = 0; index < enchTags.tagCount(); ++index) {
                final int id = enchTags.getCompoundTagAt(index).getShort("id");
                final Enchantment enchant = Enchantment.getEnchantmentById(id);

                if (enchant != null) enchantments.add(enchant);
            }
        }

        return enchantments;
    }

    public static String getTranslationKey(Enchantment enchant) {
        if (enchant != null && enchant.getName() != null) return String.format("%s.desc", enchant.getName());
        return "NULL";
    }
}
