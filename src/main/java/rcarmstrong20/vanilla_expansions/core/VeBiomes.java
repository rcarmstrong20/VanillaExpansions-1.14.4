package rcarmstrong20.vanilla_expansions.core;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;

/*
 * Author: rcarmstrong20
 */
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class VeBiomes
{
	private static final List<Biome> COLD_BIOMES = Arrays.asList(Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.GRAVELLY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
	private static final List<Biome> NETHER_BIOMES = Arrays.asList(Biomes.NETHER);
	private static final List<Biome> FOREST_BIOMES = Arrays.asList(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.FLOWER_FOREST);
	private static final List<Biome> DARK_FOREST_BIOMES = Arrays.asList(Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS);
	private static final List<Biome> SWAMP_BIOMES = Arrays.asList(Biomes.SWAMP, Biomes.SWAMP_HILLS);
	private static final List<Biome> END_CITY_BIOMES = Arrays.asList(Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS);
	private static final List<Biome> TAIGA_BIOMES = Arrays.asList(Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS);
	
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event)
	{
		addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VeBlocks.airite_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)), COLD_BIOMES);
		addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, VeBlocks.smoky_quartz_ore.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)), NETHER_BIOMES);
		addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, VeBlocks.ruby_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)), NETHER_BIOMES);
		addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.BLUEBERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(12)), FOREST_BIOMES);
		addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.CRANBERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)), FOREST_BIOMES);
		addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.HUGE_PURPLE_MUSHROOM, new BigMushroomFeatureConfig(false), Placement.COUNT_HEIGHTMAP, new FrequencyConfig(4)), DARK_FOREST_BIOMES);
		addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(VeBlocks.purple_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)), DARK_FOREST_BIOMES);
		addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(VeBlocks.witchs_cradle.getDefaultState().with(VeBerryBushBlock.AGE, 3)), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)), SWAMP_BIOMES);
		addFeature(Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(VeBlocks.void_liquid.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)), END_CITY_BIOMES);
		addStructure(VeFeature.TAIGA_CABIN, IFeatureConfig.NO_FEATURE_CONFIG, TAIGA_BIOMES);
		addFeature(Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(VeFeature.TAIGA_CABIN, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG), TAIGA_BIOMES);
		VanillaExpansions.LOGGER.info("Biome Features registered.");
	}
	
	/**
	 * Add a new feature to be spawned into the world.
	 */
	private static void addFeature(Decoration decoration, ConfiguredFeature<?> featureIn, List<Biome> biomes)
	{
		for(Biome biome : biomes)
		{
			if(biome != null)
			{
				biome.addFeature(decoration, featureIn);
			}
		}
	}
	
	/**
	 * Add a new structure to be spawned into the world.
	 */
	private static <C extends IFeatureConfig> void addStructure(Structure<C> structureIn, C config, List<Biome> biomes)
	{
		for(Biome biome : biomes)
		{
			if(biome != null)
			{
				biome.addStructure(structureIn, config);
			}
		}
	}
	
	/**
	 * Add a new entity to be spawned into the world.
	 */
	@SuppressWarnings("unused")
	private static void registerEntitySpawn(EntityType<?> entity, int weight, int maxCount, List<Biome> biomes)
	{
		for(Biome biome : biomes)
		{
			if(biome != null)
			{
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, weight, 1, maxCount));
			}
		}
	}
}
