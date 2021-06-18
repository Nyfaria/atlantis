package com.mystic.atlantis.configfeature;

import com.mojang.serialization.Codec;
import com.mystic.atlantis.util.FastNoiseLite;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class AtlanteanIslands extends Feature<DefaultFeatureConfig> {

    FastNoiseLite perlin = null;


    public AtlanteanIslands(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        setSeed(context.getWorld().getSeed());

        double radius = 15; /*rand.nextInt(config.getMaxPossibleRadius()) + config.getMinRadius() - 5;*/

        if (context.getWorld().getHeight() < 60) {
            return false;
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (double x = -radius; x <= radius; x++) {
            for (double y = 1; y <= radius; y++) {
                for (double z = -radius; z <= radius; z++) {
                    mutable.set(context.getOrigin()).move((int) x, (int) (y - radius), (int) z);
                    double noise = FastNoiseLite.getSpongePerlinValue(perlin.GetNoise(mutable.getX(), mutable.getY(), mutable.getZ()));
                    double scaledNoise = (noise) * ((y * 3) / ((x * x) + (z * z)));
                    if (scaledNoise >= 0.5) {
                        if (y == radius) {
                            context.getWorld().setBlockState(mutable, Blocks.SAND.getDefaultState(), 2);
                        } else {
                            context.getWorld().setBlockState(mutable, Blocks.SANDSTONE.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }

    public void setSeed(long seed) {
        if (perlin == null) {
            perlin = FastNoiseLite.createSpongePerlin((int) seed);
            perlin.SetFrequency(0.2F);
        }
    }
}
