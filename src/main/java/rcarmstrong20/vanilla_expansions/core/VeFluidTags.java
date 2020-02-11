package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeFluidTags
{
	public static final Tag<Fluid> VOID = new FluidTags.Wrapper(VanillaExpansions.location("void"));
}
