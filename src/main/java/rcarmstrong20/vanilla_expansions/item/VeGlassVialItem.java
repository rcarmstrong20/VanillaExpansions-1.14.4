package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VeGlassVialItem extends GlassBottleItem
{
	public VeGlassVialItem(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
	{
		if(stack.getItem() == VeItems.glass_vial && target instanceof MobEntity & target.getType() != EntityType.ENDER_DRAGON && target.getType() != EntityType.WITHER)
		{
			//String entity_name = target.getType().getName().getString().toLowerCase();
			
			((MobEntity)target).playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0F, 1.0F);
			((MobEntity)target).attackEntityFrom(DamageSource.ON_FIRE, 1);
			playerIn.addItemStackToInventory(new ItemStack(VeItems.blood_vial));
			stack.shrink(1);
			/*
			if(target.getHealth() <= 0.0F)
			{
				target.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity." + entity_name + ".death")), 1.0F, 1.0F);
				target.onDeath(DamageSource.MAGIC);
			}
			*/
			return true;
		}
		return false;
	}
}
