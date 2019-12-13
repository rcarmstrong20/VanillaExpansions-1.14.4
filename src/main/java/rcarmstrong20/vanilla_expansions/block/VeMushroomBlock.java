package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeFeature;

public class VeMushroomBlock extends MushroomBlock
{
	public VeMushroomBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public boolean generateBigMushroom(IWorld worldIn, BlockPos pos, BlockState state, Random rand)
	{
		worldIn.removeBlock(pos, false);
		Feature<BigMushroomFeatureConfig> feature = null;
		if(this == VeBlocks.purple_mushroom)
		{
			feature = VeFeature.HUGE_PURPLE_MUSHROOM;
		}
		if (feature != null && feature.place(worldIn, worldIn.getChunkProvider().getChunkGenerator(), rand, pos, new BigMushroomFeatureConfig(true)))
		{
			return true;
		}
		else
		{
			worldIn.setBlockState(pos, state, 3);
			return false;
		}
	}
}
