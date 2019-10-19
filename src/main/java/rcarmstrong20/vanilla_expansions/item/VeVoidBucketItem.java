package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import rcarmstrong20.vanilla_expansions.init.VeItems;

public class VeVoidBucketItem extends Item
{
	private static DimensionType dimType;
	
	public VeVoidBucketItem(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		super.onItemUseFinish(stack, worldIn, entityLiving);
		
		return new ItemStack(Items.BUCKET);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(dimType == DimensionType.THE_END && itemstack == new ItemStack(Items.BUCKET))
		{
			return new ActionResult<>(ActionResultType.SUCCESS, new ItemStack(VeItems.void_bucket));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
