package net.minecraft.client.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import javafx.scene.paint.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.CustomPanorama;
import net.optifine.CustomPanoramaProperties;
import net.optifine.reflect.Reflector;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    private static final Random RANDOM = new Random();
    private GuiScreen M;

    public GuiMainMenu() {}

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        this.buttonList.add(new GuiButton(1, width / 16, height / 4 + 40, width / 8, 20, "Play"));
        this.buttonList.add(new GuiButton(2, width / 16, height / 4 + 64, width / 8, 20, "Favourites"));
        this.buttonList.add(new GuiButton(3, width / 16, height / 4 + 88, width / 8, 20, "Cosmetics"));
        this.buttonList.add(new GuiButton(4, width / 16, height / 4 + 112, width / 8, 20, "Options"));
        this.buttonList.add(new GuiButton(5, width / 16, height / 4 + 136, width / 8, 20, "Quit"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                this.mc.shutdown();
                break;
            case 4:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 5:
                this.mc.shutdown();
                break;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/image/main.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/image/spmc.png"));
        drawModalRectWithCustomSizedTexture(width / 16, 10, 0, 0, width / 8, width / 8, width / 8, width / 8);
        drawBetterRect(0, 0, width / 4, height, Color.color(70/255, 70/255, 70/255, 0.65));
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.popMatrix();

        //String s = "SpaceClient 1.0";
        //this.drawString(this.fontRendererObj, s, 0, this.height - 10, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed() {
        if (this.M != null)
        {
            this.M.onGuiClosed();
        }
    }
}
