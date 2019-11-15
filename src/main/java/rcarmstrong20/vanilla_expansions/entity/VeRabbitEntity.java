package rcarmstrong20.vanilla_expansions.entity;

import java.util.UUID;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeRabbitEntity extends RabbitEntity
{
	/** Stores the UUID of the most recent lightning bolt to strike */
	private UUID lightningUUID;
	
	public VeRabbitEntity(EntityType<? extends RabbitEntity> p_i50247_1_, World p_i50247_2_)
	{
		super(p_i50247_1_, p_i50247_2_);
	}
	
	/**
	 * Called when a lightning bolt hits the entity.
	 */
	public void onStruckByLightning(LightningBoltEntity lightningBolt)
	{
		UUID uuid = lightningBolt.getUniqueID();
		if (!uuid.equals(this.lightningUUID))
		{
			if(this.getRabbitType() == 1 || this.getRabbitType() == 99)
			{
				this.setRabbitType(this.getRabbitType() == 1 ? 99 : 1);
				this.setCustomName(this.getCustomName() == new TranslationTextComponent(Util.makeTranslationKey("entity", VanillaExpansions.vanillaLocation("killer_bunny"))) ? null : this.getCustomName());
				
				this.lightningUUID = uuid;
				this.playSound(SoundEvents.ENTITY_MOOSHROOM_CONVERT, 2.0F, 1.0F);
				/*
				if(this.getCustomName() != null && this.getRabbitType() == 1)
				{
					this.setCustomName(null);
				}
				*/
			}
		}
	}
}
