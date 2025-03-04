package fun.spmc.skins.accessor;

import fun.spmc.skins.layer.*;

/**
 * Used to expose the thinArms setting of the player model
 *
 */
public interface PlayerEntityModelAccessor {
    public boolean hasThinArms();
    public HeadLayerFeatureRenderer getHeadLayer();
    public BodyLayerFeatureRenderer getBodyLayer();
}