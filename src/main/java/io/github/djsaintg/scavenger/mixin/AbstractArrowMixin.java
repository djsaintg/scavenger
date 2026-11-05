package io.github.djsaintg.scavenger.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Once an arrow has finished hitting a block (sound played, buttons and targets triggered),
// it turns into a dropped arrow item instead of staying stuck in the block.
@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
	@Shadow public AbstractArrow.Pickup pickup;

	@Shadow protected abstract ItemStack getPickupItem();

	@Inject(method = "onHitBlock", at = @At("TAIL"))
	private void scavenger$drop(BlockHitResult hit, CallbackInfo ci) {
		AbstractArrow self = (AbstractArrow) (Object) this;
		if (!(self.level() instanceof ServerLevel level) || !self.isAlive()) return;
		// Tridents keep their own rules (loyalty, riptide)
		if (!(self instanceof Arrow || self instanceof SpectralArrow)) return;
		// Infinity, multishot extras and creative players' arrows were never real items: drop nothing, like vanilla
		if (pickup == AbstractArrow.Pickup.CREATIVE_ONLY) return;

		// Keeps tipped arrow potions and any other data the arrow was fired with
		ItemStack stack = getPickupItem();
		if (stack.isEmpty()) return;
		ItemEntity item = new ItemEntity(level, self.getX(), self.getY(), self.getZ(), stack, 0.0, 0.0, 0.0);
		item.setDefaultPickUpDelay();
		level.addFreshEntity(item);
		self.discard();
	}
}
