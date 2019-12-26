package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ScatteredPlantFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.gen.feature.VeBigPurpleMushroomFeature;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.CabinPiece;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.CabinStructure;

/**
 * Author: rcarmstrong20
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFeature
{
	private static final List<Feature<?>> FEATURES = new ArrayList<>();
	private static final List<IStructurePieceType> STRUCTURE_PIECES = new ArrayList<>();
	private static final List<Structure<?>> STRUCTURES = new ArrayList<>();
	
	public static final IStructurePieceType CABIN_PIECE = register(VanillaExpansions.location("cabin"), CabinPiece::new);
	
	public static final Feature<BigMushroomFeatureConfig> HUGE_PURPLE_MUSHROOM = register(VanillaExpansions.location("huge_purple_mushroom"), new VeBigPurpleMushroomFeature(BigMushroomFeatureConfig::deserialize));
	public static final Feature<NoFeatureConfig> BLUEBERRY_BUSH = register(VanillaExpansions.location("blueberry_bush"), new ScatteredPlantFeature(NoFeatureConfig::deserialize, VeBlocks.blueberry_bush.getDefaultState().with(VeBerryBushBlock.AGE, Integer.valueOf(3))));
	public static final Feature<NoFeatureConfig> CRANBERRY_BUSH = register(VanillaExpansions.location("cranberry_bush"), new ScatteredPlantFeature(NoFeatureConfig::deserialize, VeBlocks.cranberry_bush.getDefaultState().with(VeBerryBushBlock.AGE, Integer.valueOf(3))));
	public static final Structure<NoFeatureConfig> CABIN = register(VanillaExpansions.location("cabin"), new CabinStructure(NoFeatureConfig::deserialize));
	
	/**
	 * Set the registry name for the features and add them to the registry list.
	 */
	private static <C extends IFeatureConfig> Feature<C> register(ResourceLocation name, Feature<C> feature)
	{
		feature.setRegistryName(name);
		FEATURES.add(feature);
		return feature;
	}
	
	/**
	 * Create registers for the structure pieces and add them to the registry list.
	 */
	private static IStructurePieceType register(ResourceLocation name, IStructurePieceType structurePieceType)
	{
		IStructurePieceType pieceRegistry = Registry.register(Registry.STRUCTURE_PIECE, name, structurePieceType);
		STRUCTURE_PIECES.add(pieceRegistry);
		return pieceRegistry;
	}
	
	/**
	 * Set the registry name for the structures and add them to the registry list.
	 */
	private static <C extends IFeatureConfig> Structure<C> register(ResourceLocation name, Structure<C> structure)
	{
		structure.setRegistryName(name);
		STRUCTURES.add(structure);
		return structure;
	}
	
	/**
	 * Register the Features to the game
	 */
	@SubscribeEvent
	public static void registerFeaturesAndStructures(final RegistryEvent.Register<Feature<?>> event)
	{
		FEATURES.forEach(feature -> event.getRegistry().register(feature));
		FEATURES.clear();
		
		STRUCTURE_PIECES.forEach(structurePiece -> STRUCTURE_PIECES.iterator());
		STRUCTURE_PIECES.clear();
		
		STRUCTURES.forEach(structure -> event.getRegistry().register(structure));
		STRUCTURES.clear();
		
		VanillaExpansions.LOGGER.info("Features registered.");
	}
}