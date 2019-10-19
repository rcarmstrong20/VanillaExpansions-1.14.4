package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.util.Direction;

public class VeSlimeBlock extends SlimeBlock
{
	public VeSlimeBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public boolean isStickyBlock(BlockState state)
	{
		//BlockPos pos = new BlockPos.MutableBlockPos().up();
		Direction direction = Direction.NORTH;
		
		if(direction != null && state == this.getDefaultState())
		{
			return true;
		}
		return false;
	}
}
