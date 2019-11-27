package rcarmstrong20.vanilla_expansions.fluid;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeFluids;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VoidFluid extends WaterFluid
{
	@Override
	public Fluid getFlowingFluid()
	{
		return VeFluids.FLOWING_VOID;
	}
	
	@Override
	public Fluid getStillFluid()
	{
		return VeFluids.VOID;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public Item getFilledBucket()
	{
		return VeItems.void_bucket;
	}
	
	@Override
	public boolean func_215665_a(IFluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction)
	{
		return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(World worldIn, BlockPos pos, IFluidState state, Random random)
	{
		if (!state.isSource() && !state.get(FALLING))
		{
			if (random.nextInt(64) == 0)
			{
				worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
			}
		}
		else if (random.nextInt(10) == 0)
		{
			worldIn.addParticle(ParticleTypes.UNDERWATER, (double)((float)pos.getX() + random.nextFloat()), (double)((float)pos.getY() + random.nextFloat()), (double)((float)pos.getZ() + random.nextFloat()), 0.0D, 0.0D, 0.0D);
		}	
	}
	
	@Nullable
	@OnlyIn(Dist.CLIENT)
	public IParticleData getDripParticleData()
	{
		return ParticleTypes.DRIPPING_WATER;
	}
	
	@Override
	public BlockState getBlockState(IFluidState state)
	{
		return VeBlocks.void_liquid.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}
	
	@Override
	public boolean isEquivalentTo(Fluid fluidIn)
	{
		return fluidIn == VeFluids.VOID || fluidIn == VeFluids.FLOWING_VOID;
	}
	
	@Override
	public int getTickRate(IWorldReader p_205569_1_)
	{
		return 10;
	}
	
	@Override
	public boolean isSource(IFluidState state)
	{
		return this.getStillFluid() != null ? true : false;
	}
	
	@Override
	public int getLevel(IFluidState state)
	{
		return this.getStillFluid() != null ? 8 : WaterFluid.getLevelFromState(state);
	}
	
	public static class Flowing extends VoidFluid
	{
		@Override
		protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder)
		{
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		
		@Override
		public int getLevel(IFluidState state)
		{
			return state.get(LEVEL_1_8);
		}
		
		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}
	}
	
	public static class Source extends VoidFluid
	{
		@Override
		public int getLevel(IFluidState p_207192_1_)
		{
			return 8;
		}
		
		@Override
		public boolean isSource(IFluidState state)
		{
			return true;
		}
	}
}