package rcarmstrong20.vanilla_expansions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.gen.feature.VeFeature;
import rcarmstrong20.vanilla_expansions.inventory.container.VeSawContainerType;
import rcarmstrong20.vanilla_expansions.proxy.ClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.CommonProxy;

@Mod("ve")
public class VanillaExpansions
{
	public static Object modInstance;
	public static final String MOD_ID = "ve";
	public static final String MINECRAFT_ID = "minecraft";
	public static final Logger LOGGER = LogManager.getLogger(VanillaExpansions.MOD_ID);
	public static final VeItemGroup VE_GROUP = new VeItemGroup(VanillaExpansions.MOD_ID);
	public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	public VanillaExpansions()
	{
		modInstance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		LOGGER.info("setup method registered");
		PROXY.onSetupCommon();
	}
	
	private void clientRegistries(final FMLCommonSetupEvent event)
	{
		LOGGER.info("client method registered");
		PROXY.onSetupClient();
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerContainerTypes(final RegistryEvent.Register<ContainerType<?>> event)
		{
			event.getRegistry().registerAll
			(
				VeSawContainerType.SAW
			);
		}
		
		@SubscribeEvent
		public static void registerFillBucketEvents(FillBucketEvent event)
		{
			DimensionType dimensionType = event.getWorld().getDimension().getType();
			
			if(dimensionType == DimensionType.THE_END && !event.getWorld().isRemote)
			{
				event.setFilledBucket(new ItemStack(VeItems.void_bucket));
				event.setResult(Result.ALLOW);
			}
			event.setResult(Result.DENY);
			
			LOGGER.info("Fill bucket events registered");
		}
		
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event)
		{
			registerBiomeFeatures(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, VeBlocks.airite_ore.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(2, 0, 0, 32)), Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.GRAVELLY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.ICE_SPIKES, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
			registerBiomeFeatures(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, VeBlocks.smoky_quartz_ore.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)), Biomes.NETHER);
			registerBiomeFeatures(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, VeBlocks.ruby_ore.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)), Biomes.NETHER);
			registerBiomeFeatures(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.BLUEBERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(12)), Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.FLOWER_FOREST);
			registerBiomeFeatures(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.CRANBERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)), Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.FLOWER_FOREST);
			registerBiomeFeatures(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(VeFeature.HUGE_PURPLE_MUSHROOM, new BigMushroomFeatureConfig(false), Placement.COUNT_HEIGHTMAP, new FrequencyConfig(4)), Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS);
			registerBiomeFeatures(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(VeBlocks.purple_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(10)), Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS);
			
			LOGGER.info("Biome Features registered.");
		}
		
		private static void registerBiomeFeatures(Decoration decoration, ConfiguredFeature<?> featureIn, Biome... biomes)
		{
			for(Biome biome : biomes)
			{
				if(biome != null)
				{
					biome.addFeature(decoration, featureIn);
				}
			}
		} 
		
		@SuppressWarnings("unused")
		private static void registerBiomeEntities(EntityType<?> entity, int weight, int maxCount, Biome... biomes)
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
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(VanillaExpansions.MOD_ID, name);
	}
	
	public static ResourceLocation vanillaLocation(String name)
	{
		return new ResourceLocation(VanillaExpansions.MINECRAFT_ID, name);
	}
}
