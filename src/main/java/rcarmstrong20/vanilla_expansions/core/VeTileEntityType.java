package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeTileEntityType
{
	@SuppressWarnings("rawtypes")
	public static final List<TileEntityType> TILE_ENTITY_TYPES = new ArrayList<>();
	
	public static TileEntityType<?> campfire = buildType(VanillaExpansions.vanillaLocation("campfire"), TileEntityType.Builder.create(CampfireTileEntity::new, Blocks.CAMPFIRE, VeBlocks.white_campfire, VeBlocks.magenta_campfire, VeBlocks.light_blue_campfire, VeBlocks.yellow_campfire, VeBlocks.lime_campfire, VeBlocks.pink_campfire, VeBlocks.gray_campfire, VeBlocks.light_gray_campfire, VeBlocks.cyan_campfire, VeBlocks.purple_campfire, VeBlocks.blue_campfire, VeBlocks.brown_campfire, VeBlocks.green_campfire, VeBlocks.red_campfire, VeBlocks.black_campfire));
	
	private static <T extends TileEntity> TileEntityType<T> buildType(ResourceLocation id, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        type.setRegistryName(id);
        TILE_ENTITY_TYPES.add(type);
        return type;
    }
}
