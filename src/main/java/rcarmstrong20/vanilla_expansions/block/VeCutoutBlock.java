package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.util.BlockRenderLayer;

public class VeCutoutBlock extends Block
{
	public VeCutoutBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}