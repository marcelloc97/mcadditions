package net.mcdev.mcadditions.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mcdev.mcadditions.item.MCAItems;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MCAScreen extends Screen {
    private Screen parentScreen = null;
    public ButtonWidget button1;
    public ButtonWidget button2;

    // ButtonWidgets declaration
    {
        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                assert this.client != null;

                this.client.getToastManager().add(
                    SystemToast.create(
                        this.client, SystemToast.Type.NARRATOR_TOGGLE,
                        Text.literal("Button 1 clicked!"), Text.literal("Oh my fucking god! A toast :O")
                    )
                );
            })
            .dimensions(width / 2 - 205, 20, 200, 20)
            .tooltip(Tooltip.of(Text.literal("Tooltip of Button 1")))
            .build();

        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                assert this.client != null;

                this.client.getToastManager().add(
                    SystemToast.create(
                        this.client, SystemToast.Type.NARRATOR_TOGGLE,
                        Text.literal("Button 2 clicked!"), Text.literal("Holy shit, it's an another toast!")
                    )
                );
            })
            .dimensions(width / 2 + 5, 20, 200, 20)
            .tooltip(Tooltip.of(Text.literal("Tooltip of Button 2")))
            .build();
    }

    public MCAScreen(Text title) {
        super(title);
    }

    public MCAScreen(Text title, Screen parentScreen) {
        super(title);
        this.parentScreen = parentScreen;
    }

    @Override
    protected void init() {
        button1.setPosition(width / 2 - 205, 20);
        button2.setPosition(width / 2 + 5, 20);

        addDrawableChild(button1);
        addDrawableChild(button2);
    }

    @Override
    public void close() {
        assert client != null;

        client.setScreen(this.parentScreen);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        final MultilineText multilineText = MultilineText.create(
            textRenderer,
            Text.literal("The text is pretty long ".repeat(20)),
            width - 20
        );

        context.drawCenteredTextWithShadow(
            textRenderer,
            Text.literal("You must see me"),
            width / 2, height / 2, 0xffffff
        );
        multilineText.drawCenterWithShadow(context, width / 2, height / 2 + 32);

        context.drawItem(new ItemStack(MCAItems.RUBY), 8, 8);

        super.render(context, mouseX, mouseY, delta);
    }
}
