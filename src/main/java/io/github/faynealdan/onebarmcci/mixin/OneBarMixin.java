package io.github.faynealdan.onebarmcci.mixin;

import io.github.faynealdan.onebarmcci.OneBarMCCI;
import io.github.madis0.OneBarElements;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CancellationException;

@Mixin(value = OneBarElements.class)
public class OneBarMixin {
    @Inject(method = "renderOneBar", at = @At("HEAD"), cancellable = true, remap = false)
    private void injected(CallbackInfo ci) throws CancellationException {
        if (!OneBarMCCI.showBar())
            ci.cancel();
    }

}
