package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class VePlantingPotBlock extends VeCutoutBlock
{
	private static final VoxelShape PILLAR_1_AABB = Block.makeCuboidShape(4.0, 1.0, 4.0, 6.0, 8.0, 6.0);
	private static final VoxelShape PILLAR_2_AABB = Block.makeCuboidShape(12.0, 1.0, 12.0, 10.0, 8.0, 10.0);
	private static final VoxelShape PILLAR_3_AABB = Block.makeCuboidShape(6.0, 1.0, 12.0, 4.0, 8.0, 10.0);
	private static final VoxelShape PILLAR_4_AABB = Block.makeCuboidShape(12.0, 1.0, 6.0, 10.0, 8.0, 4.0);
	private static final VoxelShape SIDE_1_AABB = Block.makeCuboidShape(2.0, 8.0, 3.0, 14.0, 18.0, 2.0);
	private static final VoxelShape SIDE_2_AABB = Block.makeCuboidShape(3.0, 8.0, 2.0, 2.0, 18.0, 14.0);
	private static final VoxelShape SIDE_3_AABB = Block.makeCuboidShape(13.0, 8.0, 3.0, 14.0, 18.0, 14.0);
	private static final VoxelShape SIDE_4_AABB = Block.makeCuboidShape(3.0, 8.0, 13.0, 14.0, 18.0, 14.0);
	private static final VoxelShape INSIDE_AABB = Block.makeCuboidShape(2.0, 8.0, 2.0, 14.0, 16.0, 14.0);
	private static final VoxelShape BASE_AABB = Block.makeCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
	private static final VoxelShape LIQUID_AABB = Block.makeCuboidShape(10.0, 1.0, 10.0, 6.0, 8.0, 6.0);
	
	private static final VoxelShape PILLAR_1_AND_2_AABB = VoxelShapes.or(PILLAR_1_AABB, PILLAR_2_AABB);
	private static final VoxelShape PILLAR_3_AND_4_AABB = VoxelShapes.or(PILLAR_3_AABB, PILLAR_4_AABB);
	private static final VoxelShape PILLARS_AABB = VoxelShapes.or(PILLAR_1_AND_2_AABB, PILLAR_3_AND_4_AABB);
	private static final VoxelShape SIDES_1_AND_2_AABB = VoxelShapes.or(SIDE_1_AABB, SIDE_2_AABB);
	private static final VoxelShape SIDES_3_AND_4_AABB = VoxelShapes.or(SIDE_3_AABB, SIDE_4_AABB);
	private static final VoxelShape SIDES_AABB = VoxelShapes.or(SIDES_1_AND_2_AABB, SIDES_3_AND_4_AABB);
	private static final VoxelShape TOP_AABB = VoxelShapes.or(INSIDE_AABB, SIDES_AABB);
	private static final VoxelShape TOP_AND_PILLARS_AABB = VoxelShapes.or(TOP_AABB, PILLARS_AABB);
	private static final VoxelShape TOP_PILLARS_AND_LIQUID_AABB = VoxelShapes.or(TOP_AND_PILLARS_AABB, LIQUID_AABB);
	private static final VoxelShape PLANTING_POT_AABB = VoxelShapes.or(TOP_PILLARS_AND_LIQUID_AABB, BASE_AABB);
	
	public VePlantingPotBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return PLANTING_POT_AABB;
	}
}
