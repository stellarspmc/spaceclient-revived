package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.Color;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    private GuiScreen M;

    public GuiMainMenu() {
    }

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
            case 1, 2 -> this.mc.displayGuiScreen(new GuiMultiplayer(this));
            case 5 -> this.mc.shutdown();
            case 3 -> this.mc.displayGuiScreen(new GuiSelectWorld(this));
            case 4 -> this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        //drawBetterRect(0, 0, width / 4, height, new Color(70, 70, 70, 35));
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/image/main.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/image/spmc.png"));
        drawModalRectWithCustomSizedTexture(width / 16, 10, 0, 0, width / 8, width / 8, (float) width / 8, (float) width / 8);
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
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
        if (this.M != null) {
            this.M.onGuiClosed();
        }
    }
}
