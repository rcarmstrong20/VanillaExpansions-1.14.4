package rcarmstrong20.vanilla_expansions.block;

import java.util.function.Supplier;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

public class VeFlowingVoidBlock extends FlowingFluidBlock
{
	public VeFlowingVoidBlock(Supplier<? extends FlowingFluid> supplier, Properties builder)
	{
		super(supplier, builder);
	}
}
