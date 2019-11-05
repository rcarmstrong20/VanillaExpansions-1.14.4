package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.VeBlockTags;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VeBerryBushBlock extends SweetBerryBushBlock
{
	public VeBerryBushBlock(Properties properties)
	{
		super(properties);
	}
	
	private IntegerProperty getAgeProperty()
	{
		return AGE;
	}
	
	private int getMaxAge()
	{
		return 3;
	}
	
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		if(this == VeBlocks.blueberry_bush)
		{
			return new ItemStack(VeItems.blueberries);
		}
		else if(this == VeBlocks.cranberry_bush)
		{
			return new ItemStack(VeItems.cranberries);
		}
		return new ItemStack(VeItems.sweet_berries);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		ItemStack itemstack = player.getHeldItem(handIn);
		if(!worldIn.isRemote && state.get(this.getAgeProperty()) == this.getMaxAge() || state.get(this.getAgeProperty()) == this.getMaxAge() - 1 && itemstack.getItem() != Items.BONE_MEAL)
		{
			spawnDrops(state, worldIn, pos);
			worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), 1), 2);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return VeBlockTags.CompareBlock(state.getBlock(), VeBlockTags.OVERWORLD_GROUNDS) || VeBlockTags.CompareBlock(state.getBlock(), VeBlockTags.OVERWORLD_SOILS);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		//Use the blocks from the isValidGround method.
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if(this == VeBlocks.sweet_berry_bush)
		{
			super.onEntityCollision(state, worldIn, pos, entityIn);
		}
	}
}
