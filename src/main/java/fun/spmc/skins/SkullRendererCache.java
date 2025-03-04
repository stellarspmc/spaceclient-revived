package fun.spmc.skins;

import fun.spmc.skins.accessor.SkullSettings;
import fun.spmc.skins.render.CustomizableModelPart;
import net.minecraft.item.ItemStack;

import java.util.WeakHashMap;

public class SkullRendererCache {

    public static boolean renderNext = false;
    public static SkullSettings lastSkull = null;
    public static WeakHashMap<ItemStack, SkullSettings> itemCache = new WeakHashMap<>();

    public static class ItemSettings implements SkullSettings {

        private CustomizableModelPart hatModel = null;

        @Override
        public CustomizableModelPart getHeadLayers() {
            return hatModel;
        }

        @Override
        public void setupHeadLayers(CustomizableModelPart box) {
            this.hatModel = box;
        }

    }

}