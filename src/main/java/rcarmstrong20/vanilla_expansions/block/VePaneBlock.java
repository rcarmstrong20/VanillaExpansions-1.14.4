package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.BlockRenderLayer;
import rcarmstrong20.vanilla_expansions.init.VeBlocks;

public class VePaneBlock extends PaneBlock
{
	public VePaneBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		if(this == VeBlocks.red_glass_pane)
		{
			return BlockRenderLayer.CUTOUT;
		}
		return BlockRenderLayer.TRANSLUCENT;
	}
}
