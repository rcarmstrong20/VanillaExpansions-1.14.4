package rcarmstrong20.vanilla_expansions.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class VePointOfInterestTypes
{
	private static final List<PointOfInterestType> POINT_OF_INTEREST_TYPES = new ArrayList<>();
	
	public static final PointOfInterestType LUMBERJACK = register(VanillaExpansions.MOD_ID, "lumberjack", getAllStates(VeBlocks.woodcutter), 1, SoundEvents.ENTITY_VILLAGER_WORK_MASON, 1);
	
	private static Set<BlockState> getAllStates(Block block)
	{
		return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
	}
	
	private static PointOfInterestType register(String id, String name, Set<BlockState> blockState, int p_221051_2_, @Nullable SoundEvent soundEvent, int p_221051_4_)
	{
		return register(name, new PointOfInterestType(id + ":" + name, blockState, p_221051_2_, soundEvent, p_221051_4_));
	}
	
	private static PointOfInterestType register(String name, PointOfInterestType pointOfInterest)
	{
		pointOfInterest.setRegistryName(VanillaExpansions.location(name));
		POINT_OF_INTEREST_TYPES.add(pointOfInterest);
		return pointOfInterest;
	}
	
	@SubscribeEvent
	public static void registerPointOfInterestTypes(final RegistryEvent.Register<PointOfInterestType> event)
	{
		/*
		POINT_OF_INTEREST_TYPES.forEach(pointOfInterestTypes -> event.getRegistry().register(pointOfInterestTypes));
		POINT_OF_INTEREST_TYPES.clear();
		*/
		
		//A work around for forge's broken point of interest system.
		try
		{
			Method func_221052_a = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class);
			func_221052_a.invoke(null, cyclePointOfInterestType(POINT_OF_INTEREST_TYPES));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		VanillaExpansions.LOGGER.info("Point of Interests registered.");
	}
	
	/*
	 * Cycle through all the point of interest types in the given list.
	 */
	private static PointOfInterestType cyclePointOfInterestType(List<PointOfInterestType> pointOfInterestTypeList)
	{
		for(PointOfInterestType pointOfInterestType : pointOfInterestTypeList)
		{
			return pointOfInterestType;
		}
		return null;
	}
}
