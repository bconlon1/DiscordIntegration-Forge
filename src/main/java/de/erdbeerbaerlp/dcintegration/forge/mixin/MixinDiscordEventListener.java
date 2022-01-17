package de.erdbeerbaerlp.dcintegration.forge.mixin;

import dcshadow.net.kyori.adventure.text.format.Style;
import de.erdbeerbaerlp.dcintegration.common.DiscordEventListener;
import de.erdbeerbaerlp.dcintegration.common.util.ComponentUtils;
import net.dv8tion.jda.api.entities.Member;
import dcshadow.net.kyori.adventure.text.Component;
import dcshadow.net.kyori.adventure.text.format.TextColor;
import dcshadow.net.kyori.adventure.text.TextReplacementConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(DiscordEventListener.class)
public class MixinDiscordEventListener {
    @Redirect(
            method = "onEvent",
            at = @At(value = "INVOKE", target = "net/dv8tion/jda/api/entities/Member.getColorRaw ()I"),
            remap = false)
    private int getColor(Member instance) {
        return 11184810;
    }

    @ModifyArg(method = "onEvent",
            at = @At(value = "INVOKE",
                    target = "Lde/erdbeerbaerlp/dcintegration/common/util/ComponentUtils;append(Ldcshadow/net/kyori/adventure/text/Component;Ldcshadow/net/kyori/adventure/text/Component;)Ldcshadow/net/kyori/adventure/text/Component;",
                    ordinal = 12),
            index = 0,
            remap = false)
    private Component setArg(Component component) {
        final TextReplacementConfig prefixReplacer = ComponentUtils.replaceLiteral("%prefix%", Component.text("[Discord]").style(Style.style(TextColor.color(114, 137, 218))));
        return component.replaceText(prefixReplacer);
    }
}
