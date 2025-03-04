package fun.spmc.skins.accessor;

import fun.spmc.skins.render.CustomizableModelPart;

public interface SkullSettings {

    public CustomizableModelPart getHeadLayers();

    public void setupHeadLayers(CustomizableModelPart box);

}