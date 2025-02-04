package com.mystic.atlantis.entities;

import com.mystic.atlantis.Atlantis;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

public class AtlantisEntities
{
    public static final EntityType<CrabEntity> CRAB =
            Registry.register(Registry.ENTITY_TYPE, Atlantis.id("atlantean_crab"), FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, CrabEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 0.3f))
                    .build());

    public static final EntityType<JellyfishEntity> JELLYFISH =
            Registry.register(Registry.ENTITY_TYPE, Atlantis.id("atlantean_jellyfish"), FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, JellyfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.8f))
                    .build());

    public static final EntityType<Jellyfish2Entity> JELLYFISH2 =
            Registry.register(Registry.ENTITY_TYPE, Atlantis.id("atlantean_jellyfish_2"), FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, Jellyfish2Entity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.8f))
                    .build());

    public static final EntityType<ShrimpEntity> SHRIMP =
            Registry.register(Registry.ENTITY_TYPE, Atlantis.id("atlantean_shrimp"), FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, ShrimpEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .build());

    public static final EntityType<SubmarineEntity> SUBMARINE =
            Registry.register(Registry.ENTITY_TYPE, Atlantis.id("atlantean_submarine"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, SubmarineEntity::new)
                    .dimensions(EntityDimensions.fixed(1.6F, 1.6F)).trackRangeBlocks(1).build());


    public static void initialize() {
        FabricDefaultAttributeRegistry.register(AtlantisEntities.CRAB, CrabEntity.createCrabAttributes());
        SpawnRestriction.register(AtlantisEntities.CRAB, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canSpawn);

        FabricDefaultAttributeRegistry.register(AtlantisEntities.JELLYFISH, JellyfishEntity.createJellyfishAttributes());
        FabricDefaultAttributeRegistry.register(AtlantisEntities.JELLYFISH2, Jellyfish2Entity.createJellyfishAttributes());
        FabricDefaultAttributeRegistry.register(AtlantisEntities.SHRIMP, ShrimpEntity.createShrimpAttributes());
    }
}
