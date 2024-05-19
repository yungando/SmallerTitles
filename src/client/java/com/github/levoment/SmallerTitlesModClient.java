package com.github.levoment;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class SmallerTitlesModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Path configFilePath = FabricLoader.getInstance().getConfigDir().resolve("smaller_titles.properties");
		SmallerTitlesConfig.createOrReadConfig(configFilePath);

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(
					ClientCommandManager.literal("smaller_titles")
							.then(ClientCommandManager.literal("subtitle")
									.then(ClientCommandManager.argument("enabled", BoolArgumentType.bool())
											.executes(context -> {
												Boolean enabled = BoolArgumentType.getBool(context, "enabled");
												SmallerTitlesConfig.showSubtitles = enabled;
												SmallerTitlesConfig.saveConfig(configFilePath);
												return 0;
											})
											.then(ClientCommandManager.argument("yOffset", IntegerArgumentType.integer())
													.then(ClientCommandManager.argument("xOffset", IntegerArgumentType.integer())
															.then(ClientCommandManager.argument("xScale", FloatArgumentType.floatArg())
																	.then(ClientCommandManager.argument("yScale", FloatArgumentType.floatArg())
																			.then(ClientCommandManager.argument("zScale", FloatArgumentType.floatArg())
																			.executes(context -> {
																				Boolean enabled = BoolArgumentType.getBool(context, "enabled");
																				int yOffset = IntegerArgumentType.getInteger(context, "yOffset");
																				int xOffset = IntegerArgumentType.getInteger(context, "xOffset");
																				float xScale = FloatArgumentType.getFloat(context, "xScale");
																				float yScale = FloatArgumentType.getFloat(context, "yScale");
																				float zScale = FloatArgumentType.getFloat(context, "zScale");
																				SmallerTitlesConfig.showSubtitles = enabled;
																				SmallerTitlesConfig.subtitleYOffset = yOffset;
																				SmallerTitlesConfig.subtitleXOffset = xOffset;
																				SmallerTitlesConfig.subtitleScaleInX = xScale;
																				SmallerTitlesConfig.subtitleScaleInY = yScale;
																				SmallerTitlesConfig.subtitleScaleInZ = zScale;
																				SmallerTitlesConfig.saveConfig(configFilePath);
																				return 0;
																			}))))))))
							.then(ClientCommandManager.literal("title")
									.then(ClientCommandManager.argument("enabled", BoolArgumentType.bool())
											.executes(context -> {
												Boolean enabled = BoolArgumentType.getBool(context, "enabled");
												SmallerTitlesConfig.showTitles = enabled;
												SmallerTitlesConfig.saveConfig(configFilePath);
												return 0;
											})
											.then(ClientCommandManager.argument("yOffset", IntegerArgumentType.integer())
													.then(ClientCommandManager.argument("xOffset", IntegerArgumentType.integer())
															.then(ClientCommandManager.argument("xScale", FloatArgumentType.floatArg())
																	.then(ClientCommandManager.argument("yScale", FloatArgumentType.floatArg())
																			.then(ClientCommandManager.argument("zScale", FloatArgumentType.floatArg())
																			.executes(context -> {
																				Boolean enabled = BoolArgumentType.getBool(context, "enabled");
																				int yOffset = IntegerArgumentType.getInteger(context, "yOffset");
																				int xOffset = IntegerArgumentType.getInteger(context, "xOffset");
																				float xScale = FloatArgumentType.getFloat(context, "xScale");
																				float yScale = FloatArgumentType.getFloat(context, "yScale");
																				float zScale = FloatArgumentType.getFloat(context, "zScale");
																				SmallerTitlesConfig.showTitles = enabled;
																				SmallerTitlesConfig.titleYOffset = yOffset;
																				SmallerTitlesConfig.titleXOffset = xOffset;
																				SmallerTitlesConfig.titleScaleInX = xScale;
																				SmallerTitlesConfig.titleScaleInY = yScale;
																				SmallerTitlesConfig.titleScaleInZ = zScale;
																				SmallerTitlesConfig.saveConfig(configFilePath);
                                                                                return 0;
                                                                            }))
							)
			))))));
		});
	}
}