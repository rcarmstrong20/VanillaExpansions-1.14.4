package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.util.BlockRenderLayer;

public class VeCutoutDirectionalBlock extends VeDirectionalBlock
{
	public VeCutoutDirectionalBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}