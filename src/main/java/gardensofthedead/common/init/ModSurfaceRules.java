package gardensofthedead.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
    private static final SurfaceRules.RuleSource CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource aboveOrAtLava = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        SurfaceRules.ConditionSource aboveLava = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        SurfaceRules.ConditionSource above30 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource below35 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource topBedrockLayer = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        SurfaceRules.ConditionSource hole = SurfaceRules.hole();
        SurfaceRules.ConditionSource patchNoise = SurfaceRules.noiseCondition(Noises.PATCH, -0.012D);
        SurfaceRules.ConditionSource netherrackNoise = SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.4D);
        SurfaceRules.RuleSource gravelBeach = SurfaceRules.ifTrue(patchNoise, SurfaceRules.ifTrue(above30, SurfaceRules.ifTrue(below35, GRAVEL)));
        SurfaceRules.ConditionSource soulBlightNetherrackNoise = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, -0.2D);
        SurfaceRules.ConditionSource soulBlightSoulSandNoise = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.3D);
        SurfaceRules.ConditionSource netherWartNoise = SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17D);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.verticalGradient(
                                "bedrock_floor",
                                VerticalAnchor.bottom(),
                                VerticalAnchor.aboveBottom(5)
                        ),
                        BEDROCK
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.not(
                                SurfaceRules.verticalGradient(
                                        "bedrock_roof",
                                        VerticalAnchor.belowTop(5),
                                        VerticalAnchor.top()
                                )
                        ),
                        BEDROCK
                ),

                SurfaceRules.ifTrue(topBedrockLayer, NETHERRACK),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SOULBLIGHT_FOREST),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(soulBlightSoulSandNoise, SOUL_SAND),
                                                SurfaceRules.ifTrue(soulBlightNetherrackNoise, SOUL_SOIL),
                                                NETHERRACK
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                gravelBeach,
                                                SurfaceRules.ifTrue(soulBlightSoulSandNoise, SOUL_SAND),
                                                SOUL_SOIL
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SOULBLIGHT_FOREST),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_CEILING,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(soulBlightSoulSandNoise, SOUL_SAND),
                                                SurfaceRules.ifTrue(soulBlightNetherrackNoise, SOUL_SOIL),
                                                NETHERRACK
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                gravelBeach,
                                                SurfaceRules.ifTrue(soulBlightSoulSandNoise, SOUL_SAND),
                                                SOUL_SOIL
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(aboveLava),
                                        SurfaceRules.ifTrue(hole, LAVA)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(ModBiomes.WHISTLING_WOODS),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrackNoise),
                                                SurfaceRules.ifTrue(aboveOrAtLava,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(netherWartNoise, NETHER_WART_BLOCK),
                                                                CRIMSON_NYLIUM
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                NETHERRACK
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
