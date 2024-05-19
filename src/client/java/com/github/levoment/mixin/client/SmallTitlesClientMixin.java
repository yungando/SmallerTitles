package com.github.levoment.mixin.client;

import com.github.levoment.SmallerTitlesConfig;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class SmallTitlesClientMixin {
	@Redirect(method = "renderTitleAndSubtitle",
	at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I", ordinal = 0))
	private int SmallerTitlesMod$changeTitleLocation(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int color) {
		if (SmallerTitlesConfig.showTitles) {
			return instance.drawTextWithShadow(textRenderer, text, x - SmallerTitlesConfig.titleXOffset, y - SmallerTitlesConfig.titleYOffset, color);
		} else {
			// Don't draw anything
			return 0;
		}
	}

	@Redirect(method = "renderTitleAndSubtitle",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal = 0))
	private void SmallerTitlesMod$changeTitleSize(MatrixStack matrixStack, float x, float y, float z) {
		if (SmallerTitlesConfig.showTitles) matrixStack.scale(SmallerTitlesConfig.titleScaleInX, SmallerTitlesConfig.titleScaleInY, SmallerTitlesConfig.titleScaleInZ);
	}

	@Redirect(method = "renderTitleAndSubtitle",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I", ordinal = 1))
	private int SmallerTitleMod$changeSubtitleLocation(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int color) {
		if (SmallerTitlesConfig.showSubtitles) {
			return instance.drawTextWithShadow(textRenderer, text, x - SmallerTitlesConfig.subtitleXOffset, y - SmallerTitlesConfig.subtitleYOffset, color);
		} else {
			// Don't draw anything
			return 0;
		}
	}

	@Redirect(method = "renderTitleAndSubtitle",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal = 1))
	private void changeTextSize(MatrixStack matrixStack, float x, float y, float z) {
		if (SmallerTitlesConfig.showSubtitles) matrixStack.scale(SmallerTitlesConfig.subtitleScaleInX, SmallerTitlesConfig.subtitleScaleInY, SmallerTitlesConfig.subtitleScaleInZ);
	}
}