package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.fluid.VoidFluid;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFluids
{
	private static final List<Fluid> FLUIDS = new ArrayList<>();
	
	public static final FlowingFluid FLOWING_VOID = register(VanillaExpansions.location("flowing_void"), new VoidFluid.Flowing());
	public static final FlowingFluid VOID = register(VanillaExpansions.location("void"), new VoidFluid.Source());
	
	private static FlowingFluid register(ResourceLocation location, FlowingFluid fluid)
	{
		fluid.setRegistryName(location);
		FLUIDS.add(fluid);
		return fluid;
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Fluid> event)
	{
		FLUIDS.forEach(fluid -> event.getRegistry().register(fluid));
		FLUIDS.clear();
		
		VanillaExpansions.LOGGER.info("Fluids registered.");
	}
	
	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event)
	{
		VanillaExpansions.LOGGER.info("Fluid textures registered.");
	}
}
