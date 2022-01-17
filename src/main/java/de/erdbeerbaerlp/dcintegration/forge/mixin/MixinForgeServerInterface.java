package de.erdbeerbaerlp.dcintegration.forge.mixin;

import dcshadow.net.kyori.adventure.text.Component;
import dcshadow.net.kyori.adventure.text.TextReplacementConfig;
import dcshadow.net.kyori.adventure.text.format.Style;
import dcshadow.net.kyori.adventure.text.format.TextColor;
import de.erdbeerbaerlp.dcintegration.common.util.ComponentUtils;
import de.erdbeerbaerlp.dcintegration.forge.util.ForgeServerInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ForgeServerInterface.class)
public class MixinForgeServerInterface {
    @ModifyVariable(method = "sendReactionMCMessage",
            at = @At(value = "STORE"),
            remap = false)
    private Component injected(Component component) {
        final TextReplacementConfig prefixReplacer = ComponentUtils.replaceLiteral("%prefix%", Component.text("[Discord]").style(Style.style(TextColor.color(114, 137, 218))));
        return component.replaceText(prefixReplacer);
    }
}
