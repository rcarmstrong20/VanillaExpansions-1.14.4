package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import rcarmstrong20.vanilla_expansions.init.VeBlocks;
import rcarmstrong20.vanilla_expansions.init.VeItems;

public class VeThreeStageCropBlock extends VeSevenStageCropBlock
{
	public static final IntegerProperty ONION_AGE = BlockStateProperties.AGE_0_3;
	
	public VeThreeStageCropBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public IntegerProperty getAgeProperty()
	{
		return ONION_AGE;
	}
	
	@Override
	public int getMaxAge()
	{
		return 3;
	}
	
	@Override
	protected IItemProvider getSeedsItem()
	{
		if(this == VeBlocks.green_onions)
		{
			return VeItems.green_onion;
		}
		else if(this == VeBlocks.garlic)
		{
			return VeItems.garlic;
		}
		else if(this == VeBlocks.ginger)
		{
			return VeItems.ginger_root;
		}
		return Items.WHEAT_SEEDS;
	}
}
