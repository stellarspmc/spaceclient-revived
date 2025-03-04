package fun.spmc.skins.accessor;

import fun.spmc.skins.render.CustomizableModelPart;

public interface PlayerSettings {

    public CustomizableModelPart getHeadLayers();

    public void setupHeadLayers(CustomizableModelPart box);

    public CustomizableModelPart[] getSkinLayers();

    public void setupSkinLayers(CustomizableModelPart[] box);

}