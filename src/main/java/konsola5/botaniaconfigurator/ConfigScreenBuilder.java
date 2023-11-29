package konsola5.botaniaconfigurator;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class ConfigScreenBuilder {

    public static Screen build(Screen parent) {

        var handler = ConfigFile.HANDLER;

        return YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Used for narration. Could be used to render a title in the future."))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Name of the category"))
                        .tooltip(Component.literal("This text will appear as a tooltip when you hover or focus the button with Tab. There is no need to add \n to wrap as YACL will do it for you."))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Name of the group"))
                                .description(OptionDescription.of(Component.literal("This text will appear when you hover over the name or focus on the collapse button with Tab.")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Boolean Option"))
                                        .description(OptionDescription.of(Component.literal("This text will appear as a tooltip when you hover over the option.")))
                                        .binding(true, () -> handler.instance().getCommon().myCoolBoolean, newVal -> handler.instance().getCommon().myCoolBoolean = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Component.literal("Integer Option"))
                                        .description(OptionDescription.of(Component.literal("This text will appear as a tooltip when you hover over the option.")))
                                        .binding(5, () -> handler.instance().getCommon().myCoolInteger, newVal -> handler.instance().getCommon().myCoolInteger = newVal)
                                        .controller(opt -> IntegerFieldControllerBuilder.create(opt).min(0).max(10000).formatValue(val -> Component.literal(val + " E")))
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Component.literal("String Option"))
                                        .description(OptionDescription.of(Component.literal("This text will appear as a tooltip when you hover over the option.")))
                                        .binding("How amazing!", () -> handler.instance().getCommon().myCoolString, newVal -> handler.instance().getCommon().myCoolString = newVal)
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .build()
                                )
                        .build())
                .save(handler::save)
                .build().generateScreen(parent);
    }
}
