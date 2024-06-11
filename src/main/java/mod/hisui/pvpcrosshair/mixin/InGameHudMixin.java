package mod.hisui.pvpcrosshair.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {


	@Shadow @Final private MinecraftClient client;
	@Unique
	Identifier resloc = Identifier.of("crosshairindicator","crosshair.png");
	@Unique
	int textureWidth = 9;
	@Unique
	int textureHeight = 9;


	@Inject(at = @At("RETURN"), method = "renderCrosshair")
	private void init(DrawContext ctx, float dt, CallbackInfo info) {
		if (this.client.targetedEntity instanceof PlayerEntity) {
			RenderSystem.setShaderTexture(0, resloc);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			ctx.drawTexture(resloc, (this.client.getWindow().getScaledWidth() - 9) / 2, (this.client.getWindow().getScaledHeight() - 9) / 2, 0, 0, this.textureWidth, this.textureHeight);
		}
	}
}