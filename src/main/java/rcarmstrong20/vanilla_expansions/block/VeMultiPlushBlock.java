package rcarmstrong20.vanilla_expansions.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tags.FluidTags;
import rcarmstrong20.vanilla_expansions.VeBlockStateProperties;

public class VeMultiPlushBlock extends VePlushBlock
{
	public static final IntegerProperty PLUSH_STACK = VeBlockStateProperties.PLUSH_STACK_1_3;
	
	public VeMultiPlushBlock(Properties properties)
	{
		super(properties);
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		if (blockstate.getBlock() == this)
		{
			return blockstate.with(PLUSH_STACK, Integer.valueOf(Math.min(3, blockstate.get(PLUSH_STACK) + 1)));
		}
		else
		{
			IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
			return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(flag));
		}
	}
	
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
	{
		return useContext.getItem().getItem() == this.asItem() && state.get(PLUSH_STACK) < 3 ? true : false;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, WATERLOGGED, PLUSH_STACK);
	}
}
