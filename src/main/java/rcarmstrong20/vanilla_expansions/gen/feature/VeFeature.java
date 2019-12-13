package rcarmstrong20.vanilla_expansions.gen.feature;

import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FossilsFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ScatteredPlantFeature;
import net.minecraft.world.gen.feature.structure.DesertPyramidStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.TaigaCabinFeature;

public class VeFeature
{
	public static final Feature<BigMushroomFeatureConfig> HUGE_PURPLE_MUSHROOM = new VeBigPurpleMushroomFeature(BigMushroomFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BLUEBERRY_BUSH = new ScatteredPlantFeature(NoFeatureConfig::deserialize, VeBlocks.blueberry_bush.getDefaultState().with(VeBerryBushBlock.AGE, Integer.valueOf(3)));
	public static final Feature<NoFeatureConfig> CRANBERRY_BUSH = new ScatteredPlantFeature(NoFeatureConfig::deserialize, VeBlocks.cranberry_bush.getDefaultState().with(VeBerryBushBlock.AGE, Integer.valueOf(3)));
	public static final Feature<NoFeatureConfig> TAIGA_CABIN = new TaigaCabinFeature(NoFeatureConfig::deserialize);
}