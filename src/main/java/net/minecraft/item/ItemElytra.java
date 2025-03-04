package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ItemElytra extends ItemArmor {
    public ItemElytra() {
        super(ArmorMaterial.LEATHER, 0, 1);
        this.maxStackSize = 1;
        this.setMaxDamage(432);
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getItemDamage() < stack.getMaxDamage() - 1;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.leather;
    }

    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        int i = EntityLiving.getArmorPosition(itemStackIn) - 1;
        ItemStack itemstack = playerIn.getCurrentArmor(i);

        if (itemstack == null) {
            playerIn.setCurrentItemOrArmor(i, itemStackIn.copy());
            itemStackIn.stackSize = 0;
        }

        return itemStackIn;
    }
}
