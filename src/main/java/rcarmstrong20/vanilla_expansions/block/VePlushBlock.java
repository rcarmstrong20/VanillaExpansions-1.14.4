package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.util.VeCollisionUtil;

public class VePlushBlock extends HorizontalBlock implements IWaterLoggable
{
	//Default block properties
	
	public static final VoxelShape NORMAL_CUBE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	//Blaze Bounding Boxes
	
	protected static final VoxelShape BLAZE_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 8.0D, 5.5D, 10.5D, 13.0D, 10.5D);
	protected static final VoxelShape BLAZE_NORTH_WEST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(4.5D, 1.0D, 4.5D, 5.5D, 7.0D, 5.5D);
	protected static final VoxelShape BLAZE_NORTH_EAST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(10.5D, 1.0D, 4.5D, 11.5D, 7.0D, 5.5D);
	protected static final VoxelShape BLAZE_SOUTH_WEST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(4.5D, 1.0D, 10.5D, 5.5D, 7.0D, 11.5D);
	protected static final VoxelShape BLAZE_SOUTH_EAST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(10.5D, 1.0D, 10.5D, 11.5D, 7.0D, 11.5D);
	protected static final VoxelShape BLAZE_SOUTH_TOP_LEG_SHAPE = Block.makeCuboidShape(7.5D, 7.0D, 11.5D, 8.5D, 13.0D, 12.5D);
	protected static final VoxelShape BLAZE_WEST_TOP_LEG_SHAPE = Block.makeCuboidShape(3.5D, 7.0D, 7.5D, 4.5D, 13.0D, 8.5D);
	protected static final VoxelShape BLAZE_EAST_TOP_LEG_SHAPE = Block.makeCuboidShape(11.5D, 7.0D, 7.5D, 12.5D, 13.0D, 8.5D);
	protected static final VoxelShape BLAZE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 11.0D, 5.0D, 7.5D, 12.0D, 5.5D);
	protected static final VoxelShape BLAZE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 11.0D, 5.0D, 10.5D, 12.0D, 5.5D);
	protected static final VoxelShape BLAZE_BOTTOM_LEGS_SHAPE = VoxelShapes.or(BLAZE_NORTH_WEST_BOTTOM_LEG_SHAPE, BLAZE_NORTH_EAST_BOTTOM_LEG_SHAPE, BLAZE_SOUTH_WEST_BOTTOM_LEG_SHAPE, BLAZE_SOUTH_EAST_BOTTOM_LEG_SHAPE);
	protected static final VoxelShape BLAZE_TOP_LEGS_SHAPE = VoxelShapes.or(BLAZE_SOUTH_TOP_LEG_SHAPE, BLAZE_WEST_TOP_LEG_SHAPE, BLAZE_EAST_TOP_LEG_SHAPE);
	protected static final VoxelShape BLAZE_NORTH_EYES_SHAPE = VoxelShapes.or(BLAZE_NORTH_RIGHT_EYE_SHAPE, BLAZE_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape BLAZE_NORTH_SHAPE = VoxelShapes.or(BLAZE_HEAD_SHAPE, BLAZE_BOTTOM_LEGS_SHAPE, BLAZE_TOP_LEGS_SHAPE, BLAZE_NORTH_EYES_SHAPE);
	
	protected static final VoxelShape BLAZE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, BLAZE_NORTH_SHAPE);
	protected static final VoxelShape BLAZE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, BLAZE_NORTH_SHAPE);
	protected static final VoxelShape BLAZE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, BLAZE_NORTH_SHAPE);
	
	//Creeper Bounding Boxes
	
	protected static final VoxelShape CREEPER_HEAD_SHAPE = Block.makeCuboidShape(4.5D, 9.0D, 4.5D, 11.5D, 16.0D, 11.5D);
	protected static final VoxelShape CREEPER_TORSO_SHAPE = Block.makeCuboidShape(5.5D, 3.0D, 5.5D, 10.5D, 9.0D, 10.5D);
	protected static final VoxelShape CREEPER_Z_FOOT1_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 2.5D, 10.5D, 3.0D, 5.5D);
	protected static final VoxelShape CREEPER_Z_FOOT2_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 10.5D, 10.5D, 3.0D, 13.5D);
	protected static final VoxelShape CREEPER_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 12.5D, 4.0D, 10.5D, 14.5D, 4.5D);
	protected static final VoxelShape CREEPER_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 12.5D, 4.0D, 7.5D, 14.5D, 4.5D);
	protected static final VoxelShape CREEPER_NORTH_MIDDLE_MOUTH_SHAPE = Block.makeCuboidShape(7.5D, 10.5D, 4.0D, 8.5D, 12.5D, 4.5D);
	protected static final VoxelShape CREEPER_NORTH_RIGHT_MOUTH_SHAPE = Block.makeCuboidShape(8.5D, 9.5D, 4.0D, 9.5D, 11.5D, 4.5D);
	protected static final VoxelShape CREEPER_NORTH_LEFT_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 9.5D, 4.0D, 7.5D, 11.5D, 4.5D);
	
	
	
	protected static final VoxelShape CREEPER_HEAD_AND_TORSO_SHAPE = VoxelShapes.or(CREEPER_HEAD_SHAPE, CREEPER_TORSO_SHAPE);
	protected static final VoxelShape CREEPER_Z_FEET_SHAPE = VoxelShapes.or(CREEPER_Z_FOOT1_SHAPE, CREEPER_Z_FOOT2_SHAPE);
	protected static final VoxelShape CREEPER_Z_BODY_SHAPE = VoxelShapes.or(CREEPER_HEAD_AND_TORSO_SHAPE, CREEPER_Z_FEET_SHAPE);
	protected static final VoxelShape CREEPER_NORTH_EYES_SHAPE = VoxelShapes.or(CREEPER_NORTH_RIGHT_EYE_SHAPE, CREEPER_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape CREEPER_NORTH_MIDDLE_AND_RIGHT_MOUTH_SHAPE = VoxelShapes.or(CREEPER_NORTH_MIDDLE_MOUTH_SHAPE, CREEPER_NORTH_RIGHT_MOUTH_SHAPE);
	protected static final VoxelShape CREEPER_NORTH_MOUTH_SHAPE = VoxelShapes.or(CREEPER_NORTH_MIDDLE_AND_RIGHT_MOUTH_SHAPE, CREEPER_NORTH_LEFT_MOUTH_SHAPE);
	protected static final VoxelShape CREEPER_NORTH_FACE_SHAPE = VoxelShapes.or(CREEPER_NORTH_MOUTH_SHAPE, CREEPER_NORTH_EYES_SHAPE);
	protected static final VoxelShape CREEPER_NORTH_SHAPE = VoxelShapes.or(CREEPER_NORTH_FACE_SHAPE, CREEPER_Z_BODY_SHAPE);
	
	protected static final VoxelShape CREEPER_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, CREEPER_NORTH_SHAPE);
	protected static final VoxelShape CREEPER_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, CREEPER_NORTH_SHAPE);
	protected static final VoxelShape CREEPER_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, CREEPER_NORTH_SHAPE);
	
	//Zombie Bounding Boxes
	
	protected static final VoxelShape ZOMBIE_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 11.0D, 7.5D, 11.0D, 16.0D, 12.0D);
	protected static final VoxelShape ZOMBIE_NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 8.5D, 10.5D, 11.0D, 11.5D);
	protected static final VoxelShape ZOMBIE_NORTH_RIGHT_ARM_SHAPE = Block.makeCuboidShape(10.5D, 9.0D, 4.0D, 12.5D, 11.0D, 11.0D);
	protected static final VoxelShape ZOMBIE_NORTH_LEFT_ARM_SHAPE = Block.makeCuboidShape(3.5D, 9.0D, 4.0D, 5.5D, 11.0D, 11.0D);
	protected static final VoxelShape ZOMBIE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 14.0D, 7.0D, 10.5D, 15.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 14.0D, 7.0D, 7.0D, 15.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_NORTH_MIDDLE_MOUTH_SHAPE = Block.makeCuboidShape(7.0D, 13.0D, 7.0D, 9.0D, 14.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_NORTH_RIGHT_MOUTH_SHAPE = Block.makeCuboidShape(9.0D, 12.0D, 7.0D, 10.0D, 13.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_NORTH_LEFT_MOUTH_SHAPE = Block.makeCuboidShape(6.0D, 11.0D, 7.0D, 7.0D, 13.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_NORTH_ARMS_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_RIGHT_ARM_SHAPE, ZOMBIE_NORTH_LEFT_ARM_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_EYES_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_RIGHT_EYE_SHAPE, ZOMBIE_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_MIDDLE_AND_RIGHT_MOUTH_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_MIDDLE_MOUTH_SHAPE, ZOMBIE_NORTH_RIGHT_MOUTH_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_MOUTH_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_MIDDLE_AND_RIGHT_MOUTH_SHAPE, ZOMBIE_NORTH_LEFT_MOUTH_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_FACE_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_EYES_SHAPE, ZOMBIE_NORTH_MOUTH_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_HEAD_AND_TORSO_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_HEAD_SHAPE, ZOMBIE_NORTH_TORSO_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_BODY_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_HEAD_AND_TORSO_SHAPE, ZOMBIE_NORTH_ARMS_SHAPE);
	protected static final VoxelShape ZOMBIE_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_BODY_SHAPE, ZOMBIE_NORTH_FACE_SHAPE);
	
	protected static final VoxelShape ZOMBIE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ZOMBIE_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ZOMBIE_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ZOMBIE_NORTH_SHAPE);
	
	//Zombie Demon Bounding Boxes
	
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN1_BOTTOM_SHAPE = Block.makeCuboidShape(4.0D, 14.5D, 9.0D, 5.0D, 16.5D, 10.0D);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN1_TOP_SHAPE = Block.makeCuboidShape(4.5D, 16.5D, 9.5D, 5.0D, 17.0D, 10.0D);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN2_BOTTOM_SHAPE = Block.makeCuboidShape(11.0D, 14.5D, 9.0D, 12.0D, 16.5D, 10.0D);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN2_TOP_SHAPE = Block.makeCuboidShape(11.0D, 16.5D, 9.5D, 11.5D, 17.0D, 10.0D);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN1_SHAPE = VoxelShapes.or(ZOMBIE_DEMON_NORTH_HORN1_BOTTOM_SHAPE, ZOMBIE_DEMON_NORTH_HORN1_TOP_SHAPE);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN2_SHAPE = VoxelShapes.or(ZOMBIE_DEMON_NORTH_HORN2_BOTTOM_SHAPE, ZOMBIE_DEMON_NORTH_HORN2_TOP_SHAPE);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORNS_SHAPE = VoxelShapes.or(ZOMBIE_DEMON_NORTH_HORN1_SHAPE, ZOMBIE_DEMON_NORTH_HORN2_SHAPE);
	protected static final VoxelShape ZOMBIE_DEMON_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_DEMON_NORTH_HORNS_SHAPE, ZOMBIE_NORTH_SHAPE);
	
	protected static final VoxelShape ZOMBIE_DEMON_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_DEMON_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_DEMON_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);
	
	//Zombie Pigman Bounding Boxes
	
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.0D, 14.0D, 7.0D, 7.0D, 15.0D, 7.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_FRONT_SHAPE = Block.makeCuboidShape(10.5D, 11.5D, 7.0D, 11.5D, 16.5D, 7.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_FRONT_SHAPE = Block.makeCuboidShape(9.0D, 12.5D, 7.0D, 10.5D, 16.5D, 7.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_FRONT_SHAPE = Block.makeCuboidShape(8.5D, 13.0D, 7.0D, 9.0D, 16.5D, 7.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE4_FRONT_SHAPE = Block.makeCuboidShape(8.5D, 14.0D, 7.0D, 8.0D, 16.5D, 7.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_SIDE_SHAPE = Block.makeCuboidShape(11.0D, 11.5D, 7.0D, 11.5D, 16.5D, 9.0D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_SIDE_SHAPE = Block.makeCuboidShape(11.0D, 12.0D, 9.0D, 11.5D, 16.5D, 9.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_SIDE_SHAPE = Block.makeCuboidShape(11.0D, 12.5D, 9.5D, 11.5D, 16.5D, 10.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_TOP_SHAPE = Block.makeCuboidShape(8.0D, 16.0D, 7.0D, 11.5D, 16.5D, 10.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_1_AND_2_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_FRONT_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_FRONT_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_3_AND_4_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_FRONT_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE4_FRONT_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_1_AND_2_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_3_AND_4_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_SIDE_PIECES_SIDE_1_AND_2_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_SIDE_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_SIDE_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_SIDE_PIECES_SIDE_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_SIDE_PIECES_SIDE_1_AND_2_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_SIDE_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_FRONT_AND_SIDE_HEAD_PIECES_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECES_FRONT_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_SIDE_PIECES_SIDE_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_LEFT_FRONT_AND_SIDE_HEAD_PIECES_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_TOP_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE1_SHAPE = Block.makeCuboidShape(7.5D, 8.5D, 8.0D, 10.5D, 10.5D, 8.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE2_SHAPE = Block.makeCuboidShape(10.5D, 8.0D, 8.0D, 9.0D, 8.5D, 8.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE3_SHAPE = Block.makeCuboidShape(7.5D, 8.0D, 8.0D, 8.5D, 8.5D, 8.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE4_SHAPE = Block.makeCuboidShape(6.5D, 9.5D, 8.0D, 7.5D, 10.5D, 8.5D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE_1_AND_2_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE1_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE2_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE_3_AND_4_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE3_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE4_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE_1_AND_2_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE_3_AND_4_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_TOP_STRAP_SHAPE = Block.makeCuboidShape(5.0D, 7.0D, 8.0D, 11.0D, 7.5D, 12.0D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_BOTTOM_STRAP_SHAPE = Block.makeCuboidShape(5.0D, 6.0D, 8.0D, 11.0D, 6.5D, 12.0D);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_STRAPS_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_TOP_STRAP_SHAPE, ZOMBIE_PIGMAN_NORTH_BOTTOM_STRAP_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_AND_LEFT_HEAD_PIECE_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_HEAD_PIECE_AND_EYE_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_AND_LEFT_HEAD_PIECE_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_HEAD_PIECE_EYE_AND_STRAPS_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIBCAGE_HEAD_PIECE_AND_EYE_SHAPE, ZOMBIE_PIGMAN_NORTH_STRAPS_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIBCAGE_HEAD_PIECE_EYE_AND_STRAPS_SHAPE, ZOMBIE_NORTH_BODY_SHAPE);
	
	protected static final VoxelShape ZOMBIE_PIGMAN_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ZOMBIE_PIGMAN_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ZOMBIE_PIGMAN_NORTH_SHAPE);
	protected static final VoxelShape ZOMBIE_PIGMAN_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ZOMBIE_PIGMAN_NORTH_SHAPE);
	
	//Endermite Bounding Boxes
	
	protected static final VoxelShape ENDERMITE_NORTH_FRONT_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 3.5D, 10.0D, 3.0D, 4.5D);
	protected static final VoxelShape ENDERMITE_NORTH_MIDDLE_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 4.5D, 11.0D, 5.0D, 10.5D);
	protected static final VoxelShape ENDERMITE_NORTH_BACK1_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.5D, 10.0D, 4.0D, 11.5D);
	protected static final VoxelShape ENDERMITE_NORTH_BACK2_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 11.5D, 9.0D, 2.0D, 12.5D);
	protected static final VoxelShape ENDERMITE_NORTH_FRONT_AND_MIDDLE_SHAPE = VoxelShapes.or(ENDERMITE_NORTH_FRONT_SHAPE, ENDERMITE_NORTH_MIDDLE_SHAPE);
	protected static final VoxelShape ENDERMITE_NORTH_BACK_SHAPE = VoxelShapes.or(ENDERMITE_NORTH_BACK1_SHAPE, ENDERMITE_NORTH_BACK2_SHAPE);
	protected static final VoxelShape ENDERMITE_NORTH_SHAPE = VoxelShapes.or(ENDERMITE_NORTH_FRONT_AND_MIDDLE_SHAPE, ENDERMITE_NORTH_BACK_SHAPE);
	
	protected static final VoxelShape ENDERMITE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ENDERMITE_NORTH_SHAPE);
	protected static final VoxelShape ENDERMITE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ENDERMITE_NORTH_SHAPE);
	protected static final VoxelShape ENDERMITE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ENDERMITE_NORTH_SHAPE);
	
	//Sheep Bounding Boxes
	
	protected static final VoxelShape SHEEP_Z_BACK_RIGHT_LEG_TUFF_SHAPE = Block.makeCuboidShape(8.5D, 2.0D, 3.5D, 11.0D, 4.0D, 6.0D);
	protected static final VoxelShape SHEEP_Z_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 4.0D, 10.5D, 2.0D, 5.5D);
	protected static final VoxelShape SHEEP_Z_BACK_LEFT_LEG_TUFF_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 3.5D, 7.5D, 4.0D, 6.0D);
	protected static final VoxelShape SHEEP_Z_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 4.0D, 7.0D, 2.0D, 5.5D);
	protected static final VoxelShape SHEEP_Z_FRONT_RIGHT_LEG_TUFF_SHAPE = Block.makeCuboidShape(8.5D, 2.0D, 10.0D, 11.0D, 4.0D, 12.5D);
	protected static final VoxelShape SHEEP_Z_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 10.5D, 10.5D, 2.0D, 12.0D);
	protected static final VoxelShape SHEEP_Z_FRONT_LEFT_LEG_TUFF_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 10.0D, 7.5D, 4.0D, 12.5D);
	protected static final VoxelShape SHEEP_Z_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 10.5D, 7.0D, 2.0D, 12.0D);
	protected static final VoxelShape SHEEP_Z_BACK_LEG_TUFFS_SHAPE = VoxelShapes.or(SHEEP_Z_BACK_RIGHT_LEG_TUFF_SHAPE, SHEEP_Z_BACK_LEFT_LEG_TUFF_SHAPE);
	protected static final VoxelShape SHEEP_Z_BACK_LEGS_SHAPE = VoxelShapes.or(SHEEP_Z_BACK_RIGHT_LEG_SHAPE, SHEEP_Z_BACK_LEFT_LEG_SHAPE);
	protected static final VoxelShape SHEEP_Z_FRONT_LEG_TUFFS_SHAPE = VoxelShapes.or(SHEEP_Z_FRONT_RIGHT_LEG_TUFF_SHAPE, SHEEP_Z_FRONT_LEFT_LEG_TUFF_SHAPE);
	protected static final VoxelShape SHEEP_Z_FRONT_LEGS_SHAPE = VoxelShapes.or(SHEEP_Z_FRONT_RIGHT_LEG_SHAPE, SHEEP_Z_FRONT_LEFT_LEG_SHAPE);
	protected static final VoxelShape SHEEP_Z_LEG_TUFFS_SHAPE = VoxelShapes.or(SHEEP_Z_BACK_LEG_TUFFS_SHAPE, SHEEP_Z_FRONT_LEG_TUFFS_SHAPE);
	protected static final VoxelShape SHEEP_Z_LEGS_SHAPE = VoxelShapes.or(SHEEP_Z_BACK_LEGS_SHAPE, SHEEP_Z_FRONT_LEGS_SHAPE);
	protected static final VoxelShape SHEEP_Z_LEG_AND_LEG_TUFFS_SHAPE = VoxelShapes.or(SHEEP_Z_LEGS_SHAPE, SHEEP_Z_LEG_TUFFS_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 1.0D, 10.5D, 14.0D, 6.0D);
	protected static final VoxelShape SHEEP_NORTH_TORSO_SHAPE = Block.makeCuboidShape(4.5D, 4.0D, 3.0D, 11.5D, 11.0D, 14.0D);
	protected static final VoxelShape SHEEP_NORTH_FOREHEAD_SHAPE = Block.makeCuboidShape(6.0D, 9.5D, 0.5D, 10.0D, 13.5D, 1.0D);
	protected static final VoxelShape SHEEP_NORTH_EARS_SHAPE = Block.makeCuboidShape(5.5D, 11.0D, 0.5D, 10.5D, 12.5D, 1.0D);
	protected static final VoxelShape SHEEP_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 12.0D, 0.5D, 10.0D, 11.0D, 0.0D);
	protected static final VoxelShape SHEEP_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 12.0D, 0.5D, 7.5D, 11.0D, 0.0D);
	protected static final VoxelShape SHEEP_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.5D, 9.5D, 0.5D, 8.5D, 10.5D, 0.0D);
	protected static final VoxelShape SHEEP_NORTH_BODY_SHAPE = VoxelShapes.or(SHEEP_NORTH_HEAD_SHAPE, SHEEP_NORTH_TORSO_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_EYES_SHAPE = VoxelShapes.or(SHEEP_NORTH_RIGHT_EYE_SHAPE, SHEEP_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_FOREHEAD_AND_EARS_SHAPE = VoxelShapes.or(SHEEP_NORTH_FOREHEAD_SHAPE, SHEEP_NORTH_EARS_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_EYES_AND_MOUTH_SHAPE = VoxelShapes.or(SHEEP_NORTH_EYES_SHAPE, SHEEP_NORTH_MOUTH_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_FACE_SHAPE = VoxelShapes.or(SHEEP_NORTH_FOREHEAD_AND_EARS_SHAPE, SHEEP_NORTH_EYES_AND_MOUTH_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_FACE_AND_BODY_SHAPE = VoxelShapes.or(SHEEP_NORTH_FACE_SHAPE, SHEEP_NORTH_BODY_SHAPE);
	protected static final VoxelShape SHEEP_NORTH_SHAPE = VoxelShapes.or(SHEEP_Z_LEG_AND_LEG_TUFFS_SHAPE, SHEEP_NORTH_FACE_AND_BODY_SHAPE);
	
	protected static final VoxelShape SHEEP_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, SHEEP_NORTH_SHAPE);
	protected static final VoxelShape SHEEP_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, SHEEP_NORTH_SHAPE);
	protected static final VoxelShape SHEEP_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, SHEEP_NORTH_SHAPE);
	
	//Chicken Bounding Boxes
	
	protected static final VoxelShape CHICKEN_Z_FEET_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 7.5D, 11.0D, 0.5D, 8.5D);
	protected static final VoxelShape CHICKEN_NORTH_TORSO_SHAPE = Block.makeCuboidShape(4.0D, 3.0D, 4.0D, 12.0D, 10.0D, 13.0D);
	protected static final VoxelShape CHICKEN_NORTH_WINGS_SHAPE = Block.makeCuboidShape(3.0D, 3.5D, 5.0D, 13.0D, 9.0D, 12.0D);
	protected static final VoxelShape CHICKEN_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 2.0D, 10.5D, 14.0D, 5.0D);
	protected static final VoxelShape CHICKEN_NORTH_WATTLE_SHAPE = Block.makeCuboidShape(6.5D, 8.5D, 1.0D, 9.5D, 11.0D, 3.0D);
	protected static final VoxelShape CHICKEN_NORTH_BEAK_SHAPE = Block.makeCuboidShape(6.0D, 11.0D, 0.0D, 10.0D, 12.0D, 3.0D);
	protected static final VoxelShape CHICKEN_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 12.0D, 1.5D, 7.0D, 13.0D, 2.0D);
	protected static final VoxelShape CHICKEN_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 12.0D, 1.5D, 10.0D, 13.0D, 2.0D);
	protected static final VoxelShape CHICKEN_NORTH_LEFT_LEG_BACK_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 9.5D, 6.5D, 3.0D, 10.5D);
	protected static final VoxelShape CHICKEN_NORTH_RIGHT_LEG_BACK_SHAPE = Block.makeCuboidShape(9.5D, 0.0D, 9.5D, 10.5D, 3.0D, 10.5D);
	protected static final VoxelShape CHICKEN_NORTH_LEFT_LEG_FRONT_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 8.5D, 6.5D, 0.5D, 9.5D);
	protected static final VoxelShape CHICKEN_NORTH_RIGHT_LEG_FRONT_SHAPE = Block.makeCuboidShape(9.5D, 0.0D, 8.5D, 10.5D, 0.5D, 9.5D);
	protected static final VoxelShape CHICKEN_NORTH_BODY_SHAPE = VoxelShapes.or(CHICKEN_NORTH_TORSO_SHAPE, CHICKEN_NORTH_HEAD_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_WATTLE_AND_BEAK_SHAPE = VoxelShapes.or(CHICKEN_NORTH_WATTLE_SHAPE, CHICKEN_NORTH_BEAK_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_EYES_SHAPE = VoxelShapes.or(CHICKEN_NORTH_RIGHT_EYE_SHAPE, CHICKEN_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_LEFT_LEG_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEFT_LEG_BACK_SHAPE, CHICKEN_NORTH_LEFT_LEG_FRONT_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_RIGHT_LEG_SHAPE = VoxelShapes.or(CHICKEN_NORTH_RIGHT_LEG_BACK_SHAPE, CHICKEN_NORTH_RIGHT_LEG_FRONT_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_LEGS_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEFT_LEG_SHAPE, CHICKEN_NORTH_RIGHT_LEG_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_FACE_SHAPE = VoxelShapes.or(CHICKEN_NORTH_WATTLE_AND_BEAK_SHAPE, CHICKEN_NORTH_EYES_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_WINGS_AND_BODY_SHAPE = VoxelShapes.or(CHICKEN_NORTH_BODY_SHAPE, CHICKEN_NORTH_WINGS_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_LEGS_AND_FEET_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEGS_SHAPE, CHICKEN_Z_FEET_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_LEGS_FEET_WINGS_AND_BODY_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEGS_AND_FEET_SHAPE, CHICKEN_NORTH_WINGS_AND_BODY_SHAPE);
	protected static final VoxelShape CHICKEN_NORTH_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEGS_FEET_WINGS_AND_BODY_SHAPE, CHICKEN_NORTH_FACE_SHAPE);
	
	protected static final VoxelShape CHICKEN_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, CHICKEN_NORTH_SHAPE);
	protected static final VoxelShape CHICKEN_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, CHICKEN_NORTH_SHAPE);
	protected static final VoxelShape CHICKEN_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, CHICKEN_NORTH_SHAPE);
	
	//Squid Bounding Boxes
	
	protected static final VoxelShape SQUID_BODY_SHAPE = Block.makeCuboidShape(4.0D, 5.0D, 4.0D, 12.0D, 15.0D, 12.0D);
	protected static final VoxelShape SQUID_MOUTH_SHAPE = Block.makeCuboidShape(6.0D, 4.5D, 6.0D, 10.0D, 5.0D, 10.0D);
	protected static final VoxelShape SQUID_LEG1_SHAPE = Block.makeCuboidShape(10.5D, 0.0D, 10.5D, 11.5D, 5.0D, 11.5D);
	protected static final VoxelShape SQUID_LEG2_SHAPE = Block.makeCuboidShape(10.5D, 0.0D, 8.5D, 11.5D, 5.0D, 9.5D);
	protected static final VoxelShape SQUID_LEG3_SHAPE = Block.makeCuboidShape(10.5D, 0.0D, 6.5D, 11.5D, 5.0D, 7.5D);
	protected static final VoxelShape SQUID_LEG4_SHAPE = Block.makeCuboidShape(10.5D, 0.0D, 4.5D, 11.5D, 5.0D, 5.5D);
	protected static final VoxelShape SQUID_LEG5_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 4.5D, 9.5D, 5.0D, 5.5D);
	protected static final VoxelShape SQUID_LEG6_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 4.5D, 7.5D, 5.0D, 5.5D);
	protected static final VoxelShape SQUID_LEG7_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 4.5D, 5.5D, 5.0D, 5.5D);
	protected static final VoxelShape SQUID_LEG8_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 6.5D, 5.5D, 5.0D, 7.5D);
	protected static final VoxelShape SQUID_LEG9_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 8.5D, 5.5D, 5.0D, 9.5D);
	protected static final VoxelShape SQUID_LEG10_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 10.5D, 5.5D, 5.0D, 11.5D);
	protected static final VoxelShape SQUID_LEG11_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 10.5D, 7.5D, 5.0D, 11.5D);
	protected static final VoxelShape SQUID_LEG12_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 10.5D, 9.5D, 5.0D, 11.5D);
	protected static final VoxelShape SQUID_TOOTH1_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 9.0D, 10.0D, 4.5D, 10.0D);
	protected static final VoxelShape SQUID_TOOTH2_SHAPE = Block.makeCuboidShape(7.5D, 4.0D, 9.0D, 8.5D, 4.5D, 10.0D);
	protected static final VoxelShape SQUID_TOOTH3_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 9.0D, 7.0D, 4.5D, 10.0D);
	protected static final VoxelShape SQUID_TOOTH4_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 7.5D, 7.0D, 4.5D, 8.5D);
	protected static final VoxelShape SQUID_TOOTH5_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 6.0D, 7.0D, 4.5D, 7.0D);
	protected static final VoxelShape SQUID_TOOTH6_SHAPE = Block.makeCuboidShape(7.5D, 4.0D, 6.0D, 8.5D, 4.5D, 7.0D);
	protected static final VoxelShape SQUID_TOOTH7_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 6.0D, 10.0D, 4.5D, 7.0D);
	protected static final VoxelShape SQUID_TOOTH8_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 7.5D, 10.0D, 4.5D, 8.5D);
	protected static final VoxelShape SQUID_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.5D, 10.0D, 3.5D, 11.5D, 12.0D, 4.0D);
	protected static final VoxelShape SQUID_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(4.5D, 10.0D, 3.5D, 6.5D, 12.0D, 4.0D);
	protected static final VoxelShape SQUID_LEGS_SHAPE = VoxelShapes.or(SQUID_LEG1_SHAPE, SQUID_LEG2_SHAPE, SQUID_LEG3_SHAPE, SQUID_LEG4_SHAPE, SQUID_LEG5_SHAPE, SQUID_LEG6_SHAPE, SQUID_LEG7_SHAPE, SQUID_LEG8_SHAPE, SQUID_LEG9_SHAPE, SQUID_LEG10_SHAPE, SQUID_LEG11_SHAPE, SQUID_LEG12_SHAPE);
	protected static final VoxelShape SQUID_TEETH_SHAPE = VoxelShapes.or(SQUID_TOOTH1_SHAPE, SQUID_TOOTH2_SHAPE, SQUID_TOOTH3_SHAPE, SQUID_TOOTH4_SHAPE, SQUID_TOOTH5_SHAPE, SQUID_TOOTH6_SHAPE, SQUID_TOOTH7_SHAPE, SQUID_TOOTH8_SHAPE);
	protected static final VoxelShape SQUID_NORTH_EYES_SHAPE = VoxelShapes.or(SQUID_NORTH_RIGHT_EYE_SHAPE, SQUID_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape SQUID_NORTH_SHAPE = VoxelShapes.or(SQUID_BODY_SHAPE, SQUID_MOUTH_SHAPE, SQUID_LEGS_SHAPE, SQUID_TEETH_SHAPE, SQUID_NORTH_EYES_SHAPE);
	
	protected static final VoxelShape SQUID_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, SQUID_NORTH_SHAPE);
	protected static final VoxelShape SQUID_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, SQUID_NORTH_SHAPE);
	protected static final VoxelShape SQUID_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, SQUID_NORTH_SHAPE);
	
	//Pig Bounding Boxes
	
	protected static final VoxelShape PIG_NORTH_BODY_SHAPE = Block.makeCuboidShape(4.5D, 4.0D, 4.0D, 11.5D, 9.0D, 15.0D);
	protected static final VoxelShape PIG_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 5.0D, 1.0D, 10.5D, 10.0D, 5.0D);
	protected static final VoxelShape PIG_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 6.0D, 11.5D, 4.0D, 8.0D);
	protected static final VoxelShape PIG_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 6.0D, 7.0D, 4.0D, 8.0D);
	protected static final VoxelShape PIG_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 13.5D, 11.5D, 4.0D, 15.5D);
	protected static final VoxelShape PIG_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 13.5D, 7.0D, 4.0D, 15.5D);
	protected static final VoxelShape PIG_NORTH_TAIL1_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 15.0D, 10.0D, 7.5D, 15.5D);
	protected static final VoxelShape PIG_NORTH_TAIL2_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 15.0D, 9.0D, 5.0D, 15.5D);
	protected static final VoxelShape PIG_NORTH_TAIL3_SHAPE = Block.makeCuboidShape(7.0D, 7.5D, 15.0D, 9.0D, 8.5D, 15.5D);
	protected static final VoxelShape PIG_NORTH_TAIL4_SHAPE = Block.makeCuboidShape(6.0D, 6.5D, 15.0D, 7.0D, 7.5D, 15.5D);
	protected static final VoxelShape PIG_NORTH_TAIL5_SHAPE = Block.makeCuboidShape(7.0D, 5.5D, 15.0D, 8.0D, 6.5D, 15.5D);
	protected static final VoxelShape PIG_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 7.5D, 0.5D, 10.0D, 8.5D, 1.0D);
	protected static final VoxelShape PIG_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 7.5D, 0.5D, 7.5D, 8.5D, 1.0D);
	protected static final VoxelShape PIG_NORTH_SNOUT_SHAPE = Block.makeCuboidShape(7.0D, 6.0D, 0.0D, 9.0D, 7.0D, 1.0D);
	protected static final VoxelShape PIG_NORTH_TAIL_1_AND_2_SHAPE = VoxelShapes.or(PIG_NORTH_TAIL1_SHAPE, PIG_NORTH_TAIL2_SHAPE);
	protected static final VoxelShape PIG_NORTH_TAIL_3_AND_4_SHAPE = VoxelShapes.or(PIG_NORTH_TAIL3_SHAPE, PIG_NORTH_TAIL4_SHAPE);
	protected static final VoxelShape PIG_NORTH_TAIL_1_2_3_AND_4_SHAPE = VoxelShapes.or(PIG_NORTH_TAIL_1_AND_2_SHAPE, PIG_NORTH_TAIL_3_AND_4_SHAPE);
	protected static final VoxelShape PIG_NORTH_TAIL_SHAPE = VoxelShapes.or(PIG_NORTH_TAIL_1_2_3_AND_4_SHAPE, PIG_NORTH_TAIL5_SHAPE);
	protected static final VoxelShape PIG_NORTH_BODY_AND_HEAD_SHAPE = VoxelShapes.or(PIG_NORTH_BODY_SHAPE, PIG_NORTH_HEAD_SHAPE);
	protected static final VoxelShape PIG_NORTH_FRONT_LEGS_SHAPE = VoxelShapes.or(PIG_NORTH_FRONT_RIGHT_LEG_SHAPE, PIG_NORTH_FRONT_LEFT_LEG_SHAPE);
	protected static final VoxelShape PIG_NORTH_BACK_LEGS_SHAPE = VoxelShapes.or(PIG_NORTH_BACK_RIGHT_LEG_SHAPE, PIG_NORTH_BACK_LEFT_LEG_SHAPE);
	protected static final VoxelShape PIG_NORTH_LEGS_SHAPE = VoxelShapes.or(PIG_NORTH_FRONT_LEGS_SHAPE, PIG_NORTH_BACK_LEGS_SHAPE);
	protected static final VoxelShape PIG_NORTH_LEGS_AND_TAIL_SHAPE = VoxelShapes.or(PIG_NORTH_LEGS_SHAPE, PIG_NORTH_TAIL_SHAPE);
	protected static final VoxelShape PIG_NORTH_EYES_SHAPE = VoxelShapes.or(PIG_NORTH_RIGHT_EYE_SHAPE, PIG_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape PIG_NORTH_EYES_AND_SNOUT_SHAPE = VoxelShapes.or(PIG_NORTH_EYES_SHAPE, PIG_NORTH_SNOUT_SHAPE);
	protected static final VoxelShape PIG_NORTH_EYES_SNOUT_LEGS_AND_TAIL_SHAPE = VoxelShapes.or(PIG_NORTH_LEGS_AND_TAIL_SHAPE, PIG_NORTH_EYES_AND_SNOUT_SHAPE);
	protected static final VoxelShape PIG_NORTH_SHAPE = VoxelShapes.or(PIG_NORTH_EYES_SNOUT_LEGS_AND_TAIL_SHAPE, PIG_NORTH_BODY_AND_HEAD_SHAPE);
	
	protected static final VoxelShape PIG_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, PIG_NORTH_SHAPE);
	protected static final VoxelShape PIG_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, PIG_NORTH_SHAPE);
	protected static final VoxelShape PIG_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, PIG_NORTH_SHAPE);
	
	//Cow Bounding Boxes
	
	protected static final VoxelShape COW_NORTH_WEST_LEFT_FRONT_LEG_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 7.0D, 4.0D, 7.0D);
	protected static final VoxelShape COW_NORTH_TORSO_SHAPE = Block.makeCuboidShape(4.5D, 4.0D, 4.0D, 11.5D, 10.0D, 14.5D);
	protected static final VoxelShape COW_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 6.0D, 1.0D, 10.5D, 11.0D, 4.0D);
	protected static final VoxelShape COW_NORTH_UTTERS_SHAPE = Block.makeCuboidShape(7.0D, 3.0D, 11.0D, 9.0D, 5.5D, 15.0D);
	protected static final VoxelShape COW_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 6.5D, 0.5D, 9.5D, 9.0D, 1.5D);
	protected static final VoxelShape COW_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 9.5D, 0.5D, 10.0D, 10.5D, 1.5D);
	protected static final VoxelShape COW_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 9.5D, 0.5D, 7.0D, 10.5D, 1.5D);
	protected static final VoxelShape COW_NORTH_RIGHT_HORN_SHAPE = Block.makeCuboidShape(10.5D, 10.0D, 1.5D, 11.0D, 11.5D, 2.5D);
	protected static final VoxelShape COW_NORTH_LEFT_HORN_SHAPE = Block.makeCuboidShape(5.0D, 10.0D, 1.5D, 5.5D, 11.5D, 2.5D);
	protected static final VoxelShape COW_NORTH_RIGHT_FRONT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 5.0D, 11.0D, 4.0D, 7.0D);
	protected static final VoxelShape COW_NORTH_RIGHT_BACK_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 12.0D, 11.0D, 4.0D, 14.0D);
	protected static final VoxelShape COW_NORTH_LEFT_BACK_LEG_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 12.0D, 7.0D, 4.0D, 14.0D);
	protected static final VoxelShape COW_NORTH_BODY_SHAPE = VoxelShapes.or(COW_NORTH_TORSO_SHAPE, COW_NORTH_HEAD_SHAPE);
	protected static final VoxelShape COW_NORTH_UTTERS_AND_MOUTH_SHAPE = VoxelShapes.or(COW_NORTH_UTTERS_SHAPE, COW_NORTH_MOUTH_SHAPE);
	protected static final VoxelShape COW_NORTH_EYES_SHAPE = VoxelShapes.or(COW_NORTH_RIGHT_EYE_SHAPE, COW_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape COW_NORTH_HORNS_SHAPE = VoxelShapes.or(COW_NORTH_RIGHT_HORN_SHAPE, COW_NORTH_LEFT_HORN_SHAPE);
	protected static final VoxelShape COW_NORTH_FRONT_LEGS_SHAPE = VoxelShapes.or(COW_NORTH_WEST_LEFT_FRONT_LEG_SHAPE, COW_NORTH_RIGHT_FRONT_LEG_SHAPE);
	protected static final VoxelShape COW_NORTH_BACK_LEGS_SHAPE = VoxelShapes.or(COW_NORTH_RIGHT_BACK_LEG_SHAPE, COW_NORTH_LEFT_BACK_LEG_SHAPE);
	protected static final VoxelShape COW_NORTH_LEGS_SHAPE = VoxelShapes.or(COW_NORTH_FRONT_LEGS_SHAPE, COW_NORTH_BACK_LEGS_SHAPE);
	protected static final VoxelShape COW_NORTH_BODY_UTTERS_AND_MOUTH_SHAPE = VoxelShapes.or(COW_NORTH_BODY_SHAPE, COW_NORTH_UTTERS_AND_MOUTH_SHAPE);
	protected static final VoxelShape COW_NORTH_EYES_AND_HORNS_SHAPE = VoxelShapes.or(COW_NORTH_EYES_SHAPE, COW_NORTH_HORNS_SHAPE);
	protected static final VoxelShape COW_NORTH_BODY_UTTERS_MOUTH_EYES_AND_HORNS_SHAPE = VoxelShapes.or(COW_NORTH_BODY_UTTERS_AND_MOUTH_SHAPE, COW_NORTH_EYES_AND_HORNS_SHAPE);
	protected static final VoxelShape COW_NORTH_SHAPE = VoxelShapes.or(COW_NORTH_BODY_UTTERS_MOUTH_EYES_AND_HORNS_SHAPE, COW_NORTH_LEGS_SHAPE);
	
	protected static final VoxelShape COW_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, COW_NORTH_SHAPE);
	protected static final VoxelShape COW_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, COW_NORTH_SHAPE);
	protected static final VoxelShape COW_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, COW_NORTH_SHAPE);
	
	//Baby Cow Bounding Boxes
	
	protected static final VoxelShape BABY_COW_NORTH_BODY_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 6.0D, 12.0D);
	protected static final VoxelShape BABY_COW_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 2.0D, 11.0D, 7.0D, 6.5D);
	protected static final VoxelShape BABY_COW_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 2.0D, 1.5D, 9.5D, 4.0D, 2.0D);
	protected static final VoxelShape BABY_COW_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 1.5D, 7.0D, 5.0D, 2.0D);
	protected static final VoxelShape BABY_COW_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 1.5D, 10.0D, 5.0D, 2.0D);
	protected static final VoxelShape BABY_COW_NORTH_RIGHT_HORN_SHAPE = Block.makeCuboidShape(4.0D, 5.5D, 2.5D, 5.0D, 7.5D, 3.5D);
	protected static final VoxelShape BABY_COW_NORTH_LEFT_HORN_SHAPE = Block.makeCuboidShape(11.0D, 5.5D, 2.5D, 12.0D, 7.5D, 3.5D);
	protected static final VoxelShape BABY_COW_NORTH_RIGHT_FRONT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.5D, 7.5D, 2.0D, 9.0D);
	protected static final VoxelShape BABY_COW_NORTH_LEFT_FRONT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 7.5D, 10.0D, 2.0D, 9.0D);
	protected static final VoxelShape BABY_COW_NORTH_RIGHT_BACK_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.0D, 7.5D, 2.0D, 11.5D);
	protected static final VoxelShape BABY_COW_NORTH_LEFT_BACK_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 10.0D, 10.0D, 2.0D, 11.5D);
	protected static final VoxelShape BABY_COW_NORTH_UTTER_SHAPE = Block.makeCuboidShape(7.5D, 1.5D, 10.5D, 8.5D, 2.5D, 12.5D);
	protected static final VoxelShape BABY_COW_NORTH_FACE_SHAPE = VoxelShapes.or(BABY_COW_NORTH_MOUTH_SHAPE, BABY_COW_NORTH_RIGHT_EYE_SHAPE, BABY_COW_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape BABY_COW_NORTH_HORNS_SHAPE = VoxelShapes.or(BABY_COW_NORTH_RIGHT_HORN_SHAPE, BABY_COW_NORTH_LEFT_HORN_SHAPE);
	protected static final VoxelShape BABY_COW_NORTH_LEGS_SHAPE = VoxelShapes.or(BABY_COW_NORTH_RIGHT_FRONT_LEG_SHAPE, BABY_COW_NORTH_LEFT_FRONT_LEG_SHAPE, BABY_COW_NORTH_RIGHT_BACK_LEG_SHAPE, BABY_COW_NORTH_LEFT_BACK_LEG_SHAPE);
	protected static final VoxelShape BABY_COW_NORTH_SHAPE = VoxelShapes.or(BABY_COW_NORTH_FACE_SHAPE, BABY_COW_NORTH_HORNS_SHAPE, BABY_COW_NORTH_LEGS_SHAPE, BABY_COW_NORTH_BODY_SHAPE, BABY_COW_NORTH_HEAD_SHAPE, BABY_COW_NORTH_UTTER_SHAPE);
	
	protected static final VoxelShape BABY_COW_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, BABY_COW_NORTH_SHAPE);
	protected static final VoxelShape BABY_COW_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, BABY_COW_NORTH_SHAPE);
	protected static final VoxelShape BABY_COW_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, BABY_COW_NORTH_SHAPE);
	
	//Mooshroom Bounding Boxes
	
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_STEM_SHAPE = Block.makeCuboidShape(7.5D, 11.0D, 2.0D, 8.5D, 12.0D, 3.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D, 12.0D, 1.0D, 9.5D, 13.0D, 4.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_SHAPE = Block.makeCuboidShape(7.0D, 13.0D, 1.5D, 9.0D, 13.5D, 3.5D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_STEM_SHAPE = Block.makeCuboidShape(7.5D, 10.0D, 7.0D, 8.5D, 11.0D, 8.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D, 11.0D, 6.0D, 9.5D, 12.0D, 9.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_SHAPE = Block.makeCuboidShape(7.0D, 12.0D, 6.5D, 9.0D, 12.5D, 8.5D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_STEM_SHAPE = Block.makeCuboidShape(7.5D, 10.0D, 11.0D, 8.5D, 11.0D, 12.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D, 11.0D, 10.0D, 9.5D, 12.0D, 13.0D);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_SHAPE = Block.makeCuboidShape(7.0D, 12.0D, 10.5D, 9.0D, 12.5D, 12.5D);
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_HALF_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE, MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_HALF_SHAPE, MOOSHROOM_NORTH_HEAD_MUSHROOM_STEM_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_HALF_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_BODY1_MUSHROOM_BOTTOM_SHAPE, MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_HALF_SHAPE, MOOSHROOM_NORTH_BODY1_MUSHROOM_STEM_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_HALF_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_BODY2_MUSHROOM_BOTTOM_SHAPE, MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_HALF_SHAPE, MOOSHROOM_NORTH_BODY2_MUSHROOM_STEM_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_HEAD_AND_BODY1_MUSHROOM_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_HEAD_MUSHROOM_SHAPE, MOOSHROOM_NORTH_BODY1_MUSHROOM_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_MUSHROOMS_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_HEAD_AND_BODY1_MUSHROOM_SHAPE, MOOSHROOM_NORTH_BODY2_MUSHROOM_SHAPE);
	protected static final VoxelShape MOOSHROOM_NORTH_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_MUSHROOMS_SHAPE, COW_NORTH_SHAPE);
	
	protected static final VoxelShape MOOSHROOM_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, MOOSHROOM_NORTH_SHAPE);
	protected static final VoxelShape MOOSHROOM_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, MOOSHROOM_NORTH_SHAPE);
	protected static final VoxelShape MOOSHROOM_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, MOOSHROOM_NORTH_SHAPE);
	
	//Baby Mooshroom Bounding Boxes
	
	protected static final VoxelShape BABY_MOOSHROOM_NORTH_MUSHROOM_STEM_SHAPE = Block.makeCuboidShape(7.5D, 6.0D, 9.0D, 8.5D, 7.0D, 10.0D);
	protected static final VoxelShape BABY_MOOSHROOM_NORTH_MUSHROOM_BOTTOM_SHAPE = Block.makeCuboidShape(6.0D, 7.0D, 7.5D, 10.0D, 8.0D, 11.5D);
	protected static final VoxelShape BABY_MOOSHROOM_NORTH_MUSHROOM_TOP_SHAPE = Block.makeCuboidShape(6.5D, 8.0D, 8.0D, 9.5D, 8.5D, 11.0D);
	protected static final VoxelShape BABY_MOOSHROOM_NORTH_MUSHROOM_SHAPE = VoxelShapes.or(BABY_MOOSHROOM_NORTH_MUSHROOM_STEM_SHAPE, BABY_MOOSHROOM_NORTH_MUSHROOM_BOTTOM_SHAPE, BABY_MOOSHROOM_NORTH_MUSHROOM_TOP_SHAPE);
	protected static final VoxelShape BABY_MOOSHROOM_NORTH_SHAPE = VoxelShapes.or(BABY_MOOSHROOM_NORTH_MUSHROOM_SHAPE, BABY_COW_NORTH_SHAPE);
	
	protected static final VoxelShape BABY_MOOSHROOM_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, BABY_MOOSHROOM_NORTH_SHAPE);
	protected static final VoxelShape BABY_MOOSHROOM_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, BABY_MOOSHROOM_NORTH_SHAPE);
	protected static final VoxelShape BABY_MOOSHROOM_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, BABY_MOOSHROOM_NORTH_SHAPE);
	
	//Ocelot Bounding Boxes
	
	protected static final VoxelShape OCELOT_Z_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 7.5D, 10.0D, 2.0D, 8.5D);
	protected static final VoxelShape OCELOT_Z_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.5D, 7.0D, 2.0D, 8.5D);
	protected static final VoxelShape OCELOT_Z_FRONT_LEGS_SHAPE = VoxelShapes.or(OCELOT_Z_FRONT_RIGHT_LEG_SHAPE, OCELOT_Z_FRONT_LEFT_LEG_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.5D, 2.0D, 6.0D, 10.5D, 6.0D, 11.5D);
	protected static final VoxelShape OCELOT_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 3.0D, 1.0D, 11.0D, 8.5D, 6.0D);
	protected static final VoxelShape OCELOT_NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(9.0D, 8.5D, 4.0D, 10.0D, 9.5D, 6.0D);
	protected static final VoxelShape OCELOT_NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(6.0D, 8.5D, 4.0D, 7.0D, 9.5D, 6.0D);
	protected static final VoxelShape OCELOT_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 6.0D, 0.5D, 10.5D, 7.0D, 1.0D);
	protected static final VoxelShape OCELOT_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 6.0D, 0.5D, 7.5D, 7.0D, 1.0D);
	protected static final VoxelShape OCELOT_NORTH_NOSE_SHAPE = Block.makeCuboidShape(6.5D, 3.5D, 0.0D, 9.5D, 5.5D, 1.0D);
	protected static final VoxelShape OCELOT_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 10.0D, 10.0D, 2.0D, 11.0D);
	protected static final VoxelShape OCELOT_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.0D, 7.0D, 2.0D, 11.0D);
	protected static final VoxelShape OCELOT_NORTH_PIECE_TAIL1_SHAPE = Block.makeCuboidShape(7.5D, 3.0D, 11.5D, 8.5D, 4.0D, 14.0D);
	protected static final VoxelShape OCELOT_NORTH_PIECE_TAIL2_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 13.0D, 8.5D, 3.0D, 16.0D);
	protected static final VoxelShape OCELOT_NORTH_TORSO_AND_HEAD_SHAPE = VoxelShapes.or(OCELOT_NORTH_TORSO_SHAPE, OCELOT_NORTH_HEAD_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_TAIL_SHAPE = VoxelShapes.or(OCELOT_NORTH_PIECE_TAIL1_SHAPE, OCELOT_NORTH_PIECE_TAIL2_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_EARS_SHAPE = VoxelShapes.or(OCELOT_NORTH_RIGHT_EAR_SHAPE, OCELOT_NORTH_LEFT_EAR_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_EYES_SHAPE = VoxelShapes.or(OCELOT_NORTH_RIGHT_EYE_SHAPE, OCELOT_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_BACK_LEGS_SHAPE = VoxelShapes.or(OCELOT_NORTH_BACK_RIGHT_LEG_SHAPE, OCELOT_NORTH_BACK_LEFT_LEG_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_LEGS_SHAPE = VoxelShapes.or(OCELOT_Z_FRONT_LEGS_SHAPE, OCELOT_NORTH_BACK_LEGS_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_LEGS_AND_TAIL_SHAPE = VoxelShapes.or(OCELOT_NORTH_LEGS_SHAPE, OCELOT_NORTH_TAIL_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_TORSO_HEAD_AND_EARS_SHAPE = VoxelShapes.or(OCELOT_NORTH_TORSO_AND_HEAD_SHAPE, OCELOT_NORTH_EARS_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_EYES_AND_NOSE_SHAPE = VoxelShapes.or(OCELOT_NORTH_EYES_SHAPE, OCELOT_NORTH_NOSE_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_BODY_SHAPE = VoxelShapes.or(OCELOT_NORTH_LEGS_AND_TAIL_SHAPE, OCELOT_NORTH_TORSO_HEAD_AND_EARS_SHAPE);
	protected static final VoxelShape OCELOT_NORTH_SHAPE = VoxelShapes.or(OCELOT_NORTH_BODY_SHAPE, OCELOT_NORTH_EYES_AND_NOSE_SHAPE);
	
	protected static final VoxelShape OCELOT_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, OCELOT_NORTH_SHAPE);
	protected static final VoxelShape OCELOT_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, OCELOT_NORTH_SHAPE);
	protected static final VoxelShape OCELOT_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, OCELOT_NORTH_SHAPE);
	
	//Persian Bounding Boxes
	
	protected static final VoxelShape PERSIAN_NORTH_NOSE_SHAPE = Block.makeCuboidShape(7.5D, 5.0D, 0.5D, 8.5D, 6.0D, 1.0D);
	protected static final VoxelShape PERSIAN_NORTH_EYES_AND_NOSE_SHAPE = VoxelShapes.or(OCELOT_NORTH_EYES_SHAPE, PERSIAN_NORTH_NOSE_SHAPE);
	protected static final VoxelShape PERSIAN_NORTH_SHAPE = VoxelShapes.or(OCELOT_NORTH_BODY_SHAPE, PERSIAN_NORTH_EYES_AND_NOSE_SHAPE);
	
	protected static final VoxelShape PERSIAN_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, PERSIAN_NORTH_SHAPE);
	protected static final VoxelShape PERSIAN_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, PERSIAN_NORTH_SHAPE);
	protected static final VoxelShape PERSIAN_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, PERSIAN_NORTH_SHAPE);
	
	//Wolf Bounding Boxes
	
	protected static final VoxelShape WOLF_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 4.0D, 11.0D, 8.5D, 8.0D);
	protected static final VoxelShape WOLF_NORTH_TORSO_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 8.0D, 10.0D, 7.0D, 13.0D);
	protected static final VoxelShape WOLF_NORTH_SNOUT_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 0.5D, 10.0D, 5.5D, 4.0D);
	protected static final VoxelShape WOLF_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 9.0D, 9.5D, 2.0D, 10.0D);
	protected static final VoxelShape WOLF_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 9.0D, 7.5D, 2.0D, 10.0D);
	protected static final VoxelShape WOLF_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 11.5D, 9.5D, 2.0D, 12.5D);
	protected static final VoxelShape WOLF_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 11.5D, 7.5D, 2.0D, 12.5D);
	protected static final VoxelShape WOLF_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 6.0D, 3.5D, 7.5D, 7.0D, 4.0D);
	protected static final VoxelShape WOLF_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 6.0D, 3.5D, 10.5D, 7.0D, 4.0D);
	protected static final VoxelShape WOLF_NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(9.0D, 8.5D, 6.0D, 10.5D, 10.5D, 7.0D);
	protected static final VoxelShape WOLF_NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(5.5D, 8.5D, 6.0D, 7.0D, 10.5D, 7.0D);
	protected static final VoxelShape WOLF_NORTH_TAIL1_SHAPE = Block.makeCuboidShape(7.0D, 3.0D, 13.0D, 9.0D, 6.0D, 14.0D);
	protected static final VoxelShape WOLF_NORTH_TAIL2_SHAPE = Block.makeCuboidShape(7.0D, 2.0D, 14.0D, 9.0D, 5.0D, 16.0D);
	protected static final VoxelShape WOLF_NORTH_HEAD_AND_TORSO_SHAPE = VoxelShapes.or(WOLF_NORTH_HEAD_SHAPE, WOLF_NORTH_TORSO_SHAPE);
	protected static final VoxelShape WOLF_NORTH_FRONT_LEGS_SHAPE = VoxelShapes.or(WOLF_NORTH_FRONT_RIGHT_LEG_SHAPE, WOLF_NORTH_FRONT_LEFT_LEG_SHAPE);
	protected static final VoxelShape WOLF_NORTH_BACK_LEGS_SHAPE = VoxelShapes.or(WOLF_NORTH_BACK_RIGHT_LEG_SHAPE, WOLF_NORTH_BACK_LEFT_LEG_SHAPE);
	protected static final VoxelShape WOLF_NORTH_LEGS_SHAPE = VoxelShapes.or(WOLF_NORTH_FRONT_LEGS_SHAPE, WOLF_NORTH_BACK_LEGS_SHAPE);
	protected static final VoxelShape WOLF_NORTH_EYES_SHAPE = VoxelShapes.or(WOLF_NORTH_RIGHT_EYE_SHAPE, WOLF_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape WOLF_NORTH_EARS_SHAPE = VoxelShapes.or(WOLF_NORTH_RIGHT_EAR_SHAPE, WOLF_NORTH_LEFT_EAR_SHAPE);
	protected static final VoxelShape WOLF_NORTH_TAIL_SHAPE = VoxelShapes.or(WOLF_NORTH_TAIL1_SHAPE, WOLF_NORTH_TAIL2_SHAPE);
	protected static final VoxelShape WOLF_NORTH_LEGS_AND_EARS_SHAPE = VoxelShapes.or(WOLF_NORTH_LEGS_SHAPE, WOLF_NORTH_EARS_SHAPE);
	protected static final VoxelShape WOLF_NORTH_LEGS_EARS_AND_TAIL_SHAPE = VoxelShapes.or(WOLF_NORTH_LEGS_AND_EARS_SHAPE, WOLF_NORTH_TAIL_SHAPE);
	protected static final VoxelShape WOLF_NORTH_BODY_SHAPE = VoxelShapes.or(WOLF_NORTH_HEAD_AND_TORSO_SHAPE, WOLF_NORTH_LEGS_EARS_AND_TAIL_SHAPE);
	protected static final VoxelShape WOLF_NORTH_FACE_SHAPE = VoxelShapes.or(WOLF_NORTH_SNOUT_SHAPE, WOLF_NORTH_EYES_SHAPE);
	protected static final VoxelShape WOLF_NORTH_SHAPE = VoxelShapes.or(WOLF_NORTH_BODY_SHAPE, WOLF_NORTH_FACE_SHAPE);
	
	protected static final VoxelShape WOLF_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, WOLF_NORTH_SHAPE);
	protected static final VoxelShape WOLF_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, WOLF_NORTH_SHAPE);
	protected static final VoxelShape WOLF_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, WOLF_NORTH_SHAPE);
	
	//Enderman Bounding Boxes
	
	protected static final VoxelShape ENDERMAN_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 12.0D, 7.0D, 10.5D, 16.0D, 12.0D);
	protected static final VoxelShape ENDERMAN_NORTH_TORSO_SHAPE = Block.makeCuboidShape(6.0D, 7.5D, 7.5D, 10.0D, 12.0D, 11.5D);
	protected static final VoxelShape ENDERMAN_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 14.5D, 6.5D, 10.0D, 15.0D, 7.0D);
	protected static final VoxelShape ENDERMAN_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 14.5D, 6.5D, 7.5D, 15.0D, 7.0D);
	protected static final VoxelShape ENDERMAN_NORTH_RIGHT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 9.0D, 9.5D, 7.5D, 10.0D);
	protected static final VoxelShape ENDERMAN_NORTH_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 9.0D, 7.5D, 7.5D, 10.0D);
	protected static final VoxelShape ENDERMAN_NORTH_RIGHT_ARM1_SHAPE = Block.makeCuboidShape(10.0D, 9.5D, 7.0D, 11.0D, 11.5D, 8.0D);
	protected static final VoxelShape ENDERMAN_NORTH_RIGHT_ARM2_SHAPE = Block.makeCuboidShape(9.0D, 7.5D, 7.0D, 10.0D, 10.5D, 8.0D);
	protected static final VoxelShape ENDERMAN_NORTH_MIDDLE_ARM_SHAPE = Block.makeCuboidShape(7.0D, 4.5D, 7.0D, 9.0D, 8.5D, 8.0D);
	protected static final VoxelShape ENDERMAN_NORTH_LEFT_ARM1_SHAPE = Block.makeCuboidShape(5.0D, 9.5D, 7.0D, 6.0D, 11.5D, 8.0D);
	protected static final VoxelShape ENDERMAN_NORTH_LEFT_ARM2_SHAPE = Block.makeCuboidShape(6.0D, 7.5D, 7.0D, 7.0D, 10.5D, 8.0D);
	protected static final VoxelShape ENDERMAN_NORTH_GRASS_BLOCK_SHAPE = Block.makeCuboidShape(6.5D, 5.0D, 4.0D, 9.5D, 8.0D, 7.0D);
	protected static final VoxelShape ENDERMAN_NORTH_BODY_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_HEAD_SHAPE, ENDERMAN_NORTH_TORSO_SHAPE);
	protected static final VoxelShape ENDERMAN_NORTH_EYES_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_EYE_SHAPE, ENDERMAN_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape ENDERMAN_NORTH_LEGS_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_LEG_SHAPE, ENDERMAN_NORTH_LEFT_LEG_SHAPE);
	protected static final VoxelShape ENDERMAN_NORTH_ARMS_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_ARM1_SHAPE, ENDERMAN_NORTH_RIGHT_ARM2_SHAPE, ENDERMAN_NORTH_MIDDLE_ARM_SHAPE, ENDERMAN_NORTH_LEFT_ARM1_SHAPE, ENDERMAN_NORTH_LEFT_ARM2_SHAPE);
	protected static final VoxelShape ENDERMAN_NORTH_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_BODY_SHAPE, ENDERMAN_NORTH_EYES_SHAPE, ENDERMAN_NORTH_LEGS_SHAPE, ENDERMAN_NORTH_ARMS_SHAPE, ENDERMAN_NORTH_GRASS_BLOCK_SHAPE);
	
	protected static final VoxelShape ENDERMAN_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ENDERMAN_NORTH_SHAPE);
	protected static final VoxelShape ENDERMAN_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ENDERMAN_NORTH_SHAPE);
	protected static final VoxelShape ENDERMAN_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ENDERMAN_NORTH_SHAPE);
	
	//Ghast Bounding Boxes
	
	protected static final VoxelShape GHAST_NORTH_BODY_SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 15.5D, 13.0D);
	protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(3.5D, 13.0D, 2.5D, 6.5D, 14.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 13.0D, 2.5D, 11.5D, 14.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 9.0D, 2.5D, 8.5D, 10.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_TOP_SHAPE = Block.makeCuboidShape(4.0D, 12.0D, 2.5D, 6.0D, 13.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_MIDDLE_SHAPE = Block.makeCuboidShape(4.0D, 11.0D, 2.5D, 5.0D, 12.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_BOTTOM_SHAPE = Block.makeCuboidShape(4.0D, 9.0D, 2.5D, 5.0D, 10.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_TOP_SHAPE = Block.makeCuboidShape(9.0D, 12.0D, 2.5D, 11.0D, 13.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_MIDDLE_SHAPE = Block.makeCuboidShape(10.0D, 10.0D, 2.5D, 11.0D, 12.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_BOTTOM_SHAPE = Block.makeCuboidShape(10.0D, 8.0D, 2.5D, 11.0D, 9.0D, 3.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_1_SHAPE = Block.makeCuboidShape(4.0D, 3.0D, 4.0D, 5.0D, 6.0D, 5.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_2_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 5.0D, 5.0D, 3.0D, 6.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_1_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 8.0D, 5.0D, 6.0D, 9.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_2_SHAPE = Block.makeCuboidShape(4.0D, 1.0D, 9.0D, 5.0D, 2.0D, 10.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_1_SHAPE = Block.makeCuboidShape(4.0D, 3.0D, 11.0D, 5.0D, 6.0D, 12.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_2_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 12.0D, 5.0D, 3.0D, 13.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_1_SHAPE = Block.makeCuboidShape(7.5D, 3.0D, 4.0D, 8.5D, 6.0D, 5.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_2_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 5.0D, 8.5D, 3.0D, 6.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_1_SHAPE = Block.makeCuboidShape(7.5D, 3.0D, 8.0D, 8.5D, 6.0D, 9.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_2_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 9.0D, 8.5D, 3.0D, 10.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_1_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 11.0D, 8.5D, 6.0D, 12.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_2_SHAPE = Block.makeCuboidShape(7.5D, 1.0D, 12.0D, 8.5D, 2.0D, 13.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_1_SHAPE = Block.makeCuboidShape(11.0D, 3.0D, 4.0D, 12.0D, 6.0D, 5.0D);
	protected static final VoxelShape GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_2_SHAPE = Block.makeCuboidShape(11.0D, 2.0D, 5.0D, 12.0D, 3.0D, 6.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_1_SHAPE = Block.makeCuboidShape(11.0D, 2.0D, 8.0D, 12.0D, 6.0D, 9.0D);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_2_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 9.0D, 12.0D, 2.0D, 10.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_1_SHAPE = Block.makeCuboidShape(11.0D, 3.0D, 11.0D, 12.0D, 6.0D, 12.0D);
	protected static final VoxelShape GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_2_SHAPE = Block.makeCuboidShape(11.0D, 2.0D, 12.0D, 12.0D, 3.0D, 13.0D);
	protected static final VoxelShape GHAST_NORTH_EYE_SHAPES = VoxelShapes.or(GHAST_NORTH_RIGHT_EYE_SHAPE, GHAST_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape GHAST_NORTH_RIGHT_TEAR_SHAPES = VoxelShapes.or(GHAST_NORTH_RIGHT_EYE_TEAR_TOP_SHAPE, GHAST_NORTH_RIGHT_EYE_TEAR_MIDDLE_SHAPE, GHAST_NORTH_RIGHT_EYE_TEAR_BOTTOM_SHAPE);
	protected static final VoxelShape GHAST_NORTH_LEFT_TEAR_SHAPES = VoxelShapes.or(GHAST_NORTH_LEFT_EYE_TEAR_TOP_SHAPE, GHAST_NORTH_LEFT_EYE_TEAR_MIDDLE_SHAPE, GHAST_NORTH_LEFT_EYE_TEAR_BOTTOM_SHAPE);
	protected static final VoxelShape GHAST_NORTH_RIGHT_TENTACLES_SHAPES = VoxelShapes.or(GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_2_SHAPE, GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_2_SHAPE, GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_2_SHAPE);
	protected static final VoxelShape GHAST_NORTH_MIDDLE_TENTACLES_SHAPES = VoxelShapes.or(GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_2_SHAPE, GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_2_SHAPE, GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_2_SHAPE);
	protected static final VoxelShape GHAST_NORTH_LEFT_TENTACLES_SHAPES = VoxelShapes.or(GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_2_SHAPE, GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_2_SHAPE, GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_2_SHAPE);
	protected static final VoxelShape GHAST_NORTH_SHAPE = VoxelShapes.or(GHAST_NORTH_EYE_SHAPES, GHAST_NORTH_RIGHT_TEAR_SHAPES, GHAST_NORTH_LEFT_TEAR_SHAPES, GHAST_NORTH_RIGHT_TENTACLES_SHAPES, GHAST_NORTH_MIDDLE_TENTACLES_SHAPES, GHAST_NORTH_LEFT_TENTACLES_SHAPES, GHAST_NORTH_BODY_SHAPE, GHAST_NORTH_MOUTH_SHAPE);
	
	protected static final VoxelShape GHAST_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, GHAST_NORTH_SHAPE);
	protected static final VoxelShape GHAST_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, GHAST_NORTH_SHAPE);
	protected static final VoxelShape GHAST_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, GHAST_NORTH_SHAPE);
	
	//Spider Bounding Boxes
	
	protected static final VoxelShape SPIDER_NORTH_BODY_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 5.5D, 14.0D);
	protected static final VoxelShape SPIDER_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 1.5D, 10.5D, 4.5D, 5.0D);
	protected static final VoxelShape SPIDER_NORTH_MIDDLE_EYE_SHAPE = Block.makeCuboidShape(7.0D, 1.5D, 1.0D, 9.0D, 2.5D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.5D, 2.0D, 1.0D, 10.5D, 3.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 2.0D, 1.0D, 6.5D, 3.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_BOTTOM_EYEBROW_SHAPE = Block.makeCuboidShape(7.0D, 3.0D, 1.0D, 7.5D, 3.5D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_TOP_EYEBROW_SHAPE = Block.makeCuboidShape(6.5D, 3.5D, 1.0D, 7.0D, 4.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_BOTTOM_EYEBROW_SHAPE = Block.makeCuboidShape(8.5D, 3.0D, 1.0D, 9.0D, 3.5D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_TOP_EYEBROW_SHAPE = Block.makeCuboidShape(9.0D, 3.5D, 1.0D, 9.5D, 4.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_FANG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 1.0D, 7.0D, 1.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_FANG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 1.0D, 9.5D, 1.0D, 1.5D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE = Block.makeCuboidShape(2.0D, 1.0D, 5.0D, 5.0D, 2.0D, 6.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 6.0D, 2.0D, 1.0D, 7.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE = Block.makeCuboidShape(2.0D, 1.0D, 7.0D, 5.0D, 2.0D, 8.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 8.0D, 2.0D, 1.0D, 9.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE = Block.makeCuboidShape(2.0D, 1.0D, 9.0D, 5.0D, 2.0D, 10.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 10.0D, 2.0D, 1.0D, 11.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE = Block.makeCuboidShape(3.0D, 1.0D, 11.0D, 5.0D, 2.0D, 12.0D);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 12.0D, 3.0D, 1.0D, 13.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 5.0D, 14.0D, 2.0D, 6.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE = Block.makeCuboidShape(14.0D, 0.0D, 6.0D, 15.0D, 1.0D, 7.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 7.0D, 14.0D, 2.0D, 8.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE = Block.makeCuboidShape(14.0D, 0.0D, 8.0D, 15.0D, 1.0D, 9.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 9.0D, 14.0D, 2.0D, 10.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE = Block.makeCuboidShape(14.0D, 0.0D, 10.0D, 15.0D, 1.0D, 11.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 11.0D, 13.0D, 2.0D, 12.0D);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE = Block.makeCuboidShape(13.0D, 0.0D, 12.0D, 14.0D, 1.0D, 13.0D);
	protected static final VoxelShape SPIDER_NORTH_EYES_SHAPE = VoxelShapes.or(SPIDER_NORTH_MIDDLE_EYE_SHAPE, SPIDER_NORTH_RIGHT_EYE_SHAPE, SPIDER_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape SPIDER_NORTH_EYEBROWS_SHAPE = VoxelShapes.or(SPIDER_NORTH_RIGHT_BOTTOM_EYEBROW_SHAPE, SPIDER_NORTH_RIGHT_TOP_EYEBROW_SHAPE, SPIDER_NORTH_LEFT_BOTTOM_EYEBROW_SHAPE, SPIDER_NORTH_LEFT_TOP_EYEBROW_SHAPE);
	protected static final VoxelShape SPIDER_NORTH_FANGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_RIGHT_FANG_SHAPE, SPIDER_NORTH_LEFT_FANG_SHAPE);
	protected static final VoxelShape SPIDER_NORTH_RIGHT_LEGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE, SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE, SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE, SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE, SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE);
	protected static final VoxelShape SPIDER_NORTH_LEFT_LEGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE, SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE, SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE, SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE);
	protected static final VoxelShape SPIDER_NORTH_SHAPE = VoxelShapes.or(SPIDER_NORTH_BODY_SHAPE, SPIDER_NORTH_HEAD_SHAPE, SPIDER_NORTH_EYES_SHAPE, SPIDER_NORTH_EYEBROWS_SHAPE, SPIDER_NORTH_FANGS_SHAPE, SPIDER_NORTH_RIGHT_LEGS_SHAPE, SPIDER_NORTH_LEFT_LEGS_SHAPE);
	
	protected static final VoxelShape SPIDER_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, SPIDER_NORTH_SHAPE);
	protected static final VoxelShape SPIDER_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, SPIDER_NORTH_SHAPE);
	protected static final VoxelShape SPIDER_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, SPIDER_NORTH_SHAPE);
	
	//Cave Spider Bounding Boxes
	
	
	
	//Horse Bounding Boxes
	
	protected static final VoxelShape HORSE_NORTH_BODY_SHAPE = Block.makeCuboidShape(4.5D, 4.5D, 5.0D, 11.5D, 10.5D, 13.0D);
	protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_LEG_PUFF_SHAPE = Block.makeCuboidShape(10.0D, 4.0D, 4.5D, 12.0D, 9.0D, 6.5D);
	protected static final VoxelShape HORSE_NORTH_BACK_LEFT_LEG_PUFF_SHAPE = Block.makeCuboidShape(10.0D, 4.0D, 11.5D, 12.0D, 9.0D, 13.5D);
	protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_LEG_PUFF_SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 4.5D, 6.0D, 9.0D, 6.5D);
	protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_LEG_PUFF_SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 11.5D, 6.0D, 9.0D, 13.5D);
	protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_LEG_MIDDLE_SHAPE = Block.makeCuboidShape(10.5D, 2.0D, 5.0D, 11.5D, 4.0D, 6.0D);
	protected static final VoxelShape HORSE_NORTH_BACK_LEFT_LEG_MIDDLE_SHAPE = Block.makeCuboidShape(10.5D, 2.0D, 12.0D, 11.5D, 4.0D, 13.0D);
	protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_LEG_MIDDLE_SHAPE = Block.makeCuboidShape(4.5D, 2.0D, 5.0D, 5.5D, 4.0D, 6.0D);
	protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_LEG_MIDDLE_SHAPE = Block.makeCuboidShape(4.5D, 2.0D, 12.0D, 5.5D, 4.0D, 13.0D);
	protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_HOOF_SHAPE = Block.makeCuboidShape(10.0D, 0.0D, 4.5D, 12.0D, 2.0D, 6.5D);
	protected static final VoxelShape HORSE_NORTH_BACK_LEFT_HOOF_SHAPE = Block.makeCuboidShape(10.0D, 0.0D, 11.5D, 12.0D, 2.0D, 13.5D);
	protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_HOOF_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.5D, 6.0D, 2.0D, 6.5D);
	protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_HOOF_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 11.5D, 6.0D, 2.0D, 13.5D);
	protected static final VoxelShape HORSE_NORTH_TAIL1_SHAPE = Block.makeCuboidShape(7.5D, 8.0D, 13.0D, 9.0D, 10.5D, 13.5D);
	protected static final VoxelShape HORSE_NORTH_TAIL2_SHAPE = Block.makeCuboidShape(7.0D, 5.0D, 12.5D, 9.5D, 9.0D, 15.0D);
	protected static final VoxelShape HORSE_NORTH_TAIL3_SHAPE = Block.makeCuboidShape(7.5D, 3.5D, 14.0D, 9.0D, 5.5D, 15.5D);
	protected static final VoxelShape HORSE_NORTH_TAIL4_SHAPE = Block.makeCuboidShape(7.5D, 2.5D, 14.0D, 8.0D, 3.5D, 14.5D);
	protected static final VoxelShape HORSE_NORTH_TAIL5_SHAPE = Block.makeCuboidShape(8.0D, 3.0D, 15.0D, 9.0D, 3.5D, 15.5D);
	protected static final VoxelShape HORSE_NORTH_TAIL6_SHAPE = Block.makeCuboidShape(8.0D, 2.5D, 15.0D, 8.5D, 3.0D, 15.5D);
	protected static final VoxelShape HORSE_NORTH_MANE1_SHAPE = Block.makeCuboidShape(6.5D, 10.5D, 7.0D, 9.0D, 11.5D, 9.5D);
	protected static final VoxelShape HORSE_NORTH_MANE2_SHAPE = Block.makeCuboidShape(6.5D, 11.5D, 6.0D, 9.0D, 12.5D, 8.5D);
	protected static final VoxelShape HORSE_NORTH_MANE3_SHAPE = Block.makeCuboidShape(6.5D, 12.5D, 5.0D, 9.0D, 13.5D, 7.5D);
	protected static final VoxelShape HORSE_NORTH_MANE4_SHAPE = Block.makeCuboidShape(6.5D, 13.5D, 4.0D, 9.0D, 14.5D, 6.5D);
	protected static final VoxelShape HORSE_NORTH_MANE5_SHAPE = Block.makeCuboidShape(6.5D, 14.5D, 3.0D, 9.0D, 15.5D, 5.5D);
	
	protected static final VoxelShape HORSE_NORTH_NECK1_SHAPE = Block.makeCuboidShape(6.0D, 10.0D, 4.0D, 9.5D, 12.5D, 7.0D);
	protected static final VoxelShape HORSE_NORTH_NECK2_SHAPE = Block.makeCuboidShape(6.0D, 10.0D, 7.0D, 9.5D, 11.5D, 8.0D);
	
	protected static final VoxelShape HORSE_NORTH_HEAD_RIGHT_SHAPE = Block.makeCuboidShape(6.5D, 10.0D, 2.0D, 10.0D, 13.0D, 4.0D);
	protected static final VoxelShape HORSE_NORTH_HEAD_MIDDLE_SHAPE = Block.makeCuboidShape(6.5D, 9.5D, 3.0D, 10.0D, 14.5D, 5.0D);
	protected static final VoxelShape HORSE_NORTH_HEAD_LEFT_SHAPE = Block.makeCuboidShape(6.5D, 11.0D, 4.0D, 10.0D, 13.0D, 7.0D);
	
	
	protected static final VoxelShape HORSE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(10.0D, 11.3D, 2.5D, 10.5D, 12.7D, 4.0D);
	protected static final VoxelShape HORSE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 11.3D, 2.5D, 6.5D, 12.7D, 4.0D);
	
	protected static final VoxelShape HORSE_NORTH_EYES_SHAPE = VoxelShapes.or(HORSE_NORTH_RIGHT_EYE_SHAPE, HORSE_NORTH_LEFT_EYE_SHAPE);
	protected static final VoxelShape HORSE_NORTH_LEG_PUFFS_SHAPE = VoxelShapes.or(HORSE_NORTH_FRONT_LEFT_LEG_PUFF_SHAPE, HORSE_NORTH_BACK_LEFT_LEG_PUFF_SHAPE, HORSE_NORTH_FRONT_RIGHT_LEG_PUFF_SHAPE, HORSE_NORTH_BACK_RIGHT_LEG_PUFF_SHAPE);
	protected static final VoxelShape HORSE_NORTH_LEG_MIDDLE_SHAPE = VoxelShapes.or(HORSE_NORTH_FRONT_LEFT_LEG_MIDDLE_SHAPE, HORSE_NORTH_BACK_LEFT_LEG_MIDDLE_SHAPE, HORSE_NORTH_FRONT_RIGHT_LEG_MIDDLE_SHAPE, HORSE_NORTH_BACK_RIGHT_LEG_MIDDLE_SHAPE);
	protected static final VoxelShape HORSE_NORTH_HOOVES_SHAPE = VoxelShapes.or(HORSE_NORTH_FRONT_LEFT_HOOF_SHAPE, HORSE_NORTH_BACK_LEFT_HOOF_SHAPE, HORSE_NORTH_FRONT_RIGHT_HOOF_SHAPE, HORSE_NORTH_BACK_RIGHT_HOOF_SHAPE);
	protected static final VoxelShape HORSE_NORTH_LEGS_SHAPE = VoxelShapes.or(HORSE_NORTH_LEG_PUFFS_SHAPE, HORSE_NORTH_LEG_MIDDLE_SHAPE, HORSE_NORTH_HOOVES_SHAPE);
	protected static final VoxelShape HORSE_NORTH_TAIL_SHAPE = VoxelShapes.or(HORSE_NORTH_TAIL1_SHAPE, HORSE_NORTH_TAIL2_SHAPE, HORSE_NORTH_TAIL3_SHAPE, HORSE_NORTH_TAIL4_SHAPE, HORSE_NORTH_TAIL5_SHAPE, HORSE_NORTH_TAIL6_SHAPE);
	protected static final VoxelShape HORSE_NORTH_NECK_SHAPE = VoxelShapes.or(HORSE_NORTH_NECK1_SHAPE, HORSE_NORTH_NECK2_SHAPE);
	protected static final VoxelShape HORSE_NORTH_HEAD_SHAPE = VoxelShapes.or(HORSE_NORTH_HEAD_LEFT_SHAPE, HORSE_NORTH_HEAD_MIDDLE_SHAPE, HORSE_NORTH_HEAD_RIGHT_SHAPE);
	protected static final VoxelShape HORSE_NORTH_MANE_SHAPE = VoxelShapes.or(HORSE_NORTH_MANE1_SHAPE, HORSE_NORTH_MANE2_SHAPE, HORSE_NORTH_MANE3_SHAPE, HORSE_NORTH_MANE4_SHAPE, HORSE_NORTH_MANE5_SHAPE);
	protected static final VoxelShape HORSE_NORTH_SHAPE = VoxelShapes.or(HORSE_NORTH_BODY_SHAPE, HORSE_NORTH_TAIL_SHAPE, HORSE_NORTH_NECK_SHAPE, HORSE_NORTH_MANE_SHAPE, HORSE_NORTH_HEAD_SHAPE, HORSE_NORTH_LEGS_SHAPE, HORSE_NORTH_EYES_SHAPE);
	
	protected static final VoxelShape HORSE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, HORSE_NORTH_SHAPE);
	protected static final VoxelShape HORSE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, HORSE_NORTH_SHAPE);
	protected static final VoxelShape HORSE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, HORSE_NORTH_SHAPE);
	
	//
	
	protected static final VoxelShape BAT_NORTH_SHAPE = Block.makeCuboidShape(0.1D, 0.0D, 6.0D, 15.0D, 12.0D, 11.0D);
	protected static final VoxelShape BAT_SOUTH_SHAPE = Block.makeCuboidShape(0.1D, 0.0D, 5.0D, 16.0D, 12.0D, 10.0D);
	protected static final VoxelShape BAT_EAST_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 0.1D, 10.0D, 12.0D, 15.0D);
	protected static final VoxelShape BAT_WEST_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 0.1D, 11.0D, 12.0D, 16.0D);
	
	protected static final VoxelShape RABBIT_NORTH_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 16.0D);
	protected static final VoxelShape RABBIT_SOUTH_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 0.0D, 14.0D, 16.0D, 14.0D);
	protected static final VoxelShape RABBIT_WEST_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 16.0D, 16.0D, 14.0D);
	protected static final VoxelShape RABBIT_EAST_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
    protected static final VoxelShape SKELETON_NORTH_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 3.0D, 14.0D, 16.0D, 15.0D);
    protected static final VoxelShape SKELETON_SOUTH_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 1.0D, 14.0D, 16.0D, 13.0D);
    protected static final VoxelShape SKELETON_EAST_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 2.0D, 13.0D, 16.0D, 14.0D);
    protected static final VoxelShape SKELETON_WEST_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 15.0D, 16.0D, 14.0D);
    
	protected static final VoxelShape HORSE_Z_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	protected static final VoxelShape HORSE_X_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
	
	protected static final VoxelShape COW_Z_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 14.0D, 16.0D);
	protected static final VoxelShape COW_X_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 14.0D, 12.0D);
	
	protected static final VoxelShape SILVERFISH_Z_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 0.0D, 14.0D, 8.0D, 16.0D);
	protected static final VoxelShape SILVERFISH_X_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 16.0D, 8.0D, 14.0D);
	
	protected static final VoxelShape OCELOT_Z_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 8.0D, 16.0D);
	protected static final VoxelShape OCELOT_X_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 8.0D, 11.0D);
	
	protected static final VoxelShape SPIDER_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
	protected static final VoxelShape CAVE_SPIDER_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
	protected static final VoxelShape VILLAGER_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	
	public VePlushBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		BlockPos blockpos = context.getPos();
		IFluidState ifluidstate = context.getWorld().getFluidState(blockpos);
		return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (stateIn.get(WATERLOGGED))
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return facing.getAxis().isHorizontal() ? stateIn.with(HORIZONTAL_FACING, stateIn.get(HORIZONTAL_FACING)) : stateIn;
	}
	
	@Override
	public IFluidState getFluidState(BlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		if(this == VeBlocks.blaze_plush)
		{
			return VePlushBlock.defineShapes(state, BLAZE_NORTH_SHAPE, BLAZE_SOUTH_SHAPE, BLAZE_WEST_SHAPE, BLAZE_EAST_SHAPE);
		}
		else if(this == VeBlocks.creeper_plush)
		{
			return VePlushBlock.defineShapes(state, CREEPER_NORTH_SHAPE, CREEPER_SOUTH_SHAPE, CREEPER_WEST_SHAPE, CREEPER_EAST_SHAPE);
		}
		else if(this == VeBlocks.zombie_plush)
		{
			return VePlushBlock.defineShapes(state, ZOMBIE_NORTH_SHAPE, ZOMBIE_SOUTH_SHAPE, ZOMBIE_WEST_SHAPE, ZOMBIE_EAST_SHAPE);
		}
		else if(this == VeBlocks.zombie_demon_plush)
		{
			return VePlushBlock.defineShapes(state, ZOMBIE_DEMON_NORTH_SHAPE, ZOMBIE_DEMON_SOUTH_SHAPE, ZOMBIE_DEMON_WEST_SHAPE, ZOMBIE_DEMON_EAST_SHAPE);
		}
		if(this == VeBlocks.zombie_pigman_plush)
		{
			return VePlushBlock.defineShapes(state, ZOMBIE_PIGMAN_NORTH_SHAPE, ZOMBIE_PIGMAN_SOUTH_SHAPE, ZOMBIE_PIGMAN_WEST_SHAPE, ZOMBIE_PIGMAN_EAST_SHAPE);
		}
		else if(this == VeBlocks.endermite_plush)
		{
			return VePlushBlock.defineShapes(state, ENDERMITE_NORTH_SHAPE, ENDERMITE_SOUTH_SHAPE, ENDERMITE_WEST_SHAPE, ENDERMITE_EAST_SHAPE);
		}
		else if(this == VeBlocks.white_sheep_plush || this == VeBlocks.orange_sheep_plush || this == VeBlocks.magenta_sheep_plush || this == VeBlocks.light_blue_sheep_plush || this == VeBlocks.yellow_sheep_plush || this == VeBlocks.lime_sheep_plush || this == VeBlocks.pink_sheep_plush || this == VeBlocks.gray_sheep_plush || this == VeBlocks.light_gray_sheep_plush || this == VeBlocks.cyan_sheep_plush || this == VeBlocks.purple_sheep_plush || this == VeBlocks.blue_sheep_plush || this == VeBlocks.brown_sheep_plush || this == VeBlocks.green_sheep_plush || this == VeBlocks.red_sheep_plush || this == VeBlocks.black_sheep_plush)
		{
			return VePlushBlock.defineShapes(state, SHEEP_NORTH_SHAPE, SHEEP_SOUTH_SHAPE, SHEEP_WEST_SHAPE, SHEEP_EAST_SHAPE);
		}
		else if(this == VeBlocks.chicken_plush)
		{
			return VePlushBlock.defineShapes(state, CHICKEN_NORTH_SHAPE, CHICKEN_SOUTH_SHAPE, CHICKEN_WEST_SHAPE, CHICKEN_EAST_SHAPE);
		}
		else if(this == VeBlocks.squid_plush)
		{
			return VePlushBlock.defineShapes(state, SQUID_NORTH_SHAPE, SQUID_SOUTH_SHAPE, SQUID_WEST_SHAPE, SQUID_EAST_SHAPE);
		}
		else if(this == VeBlocks.pig_plush)
		{
			return VePlushBlock.defineShapes(state, PIG_NORTH_SHAPE, PIG_SOUTH_SHAPE, PIG_WEST_SHAPE, PIG_EAST_SHAPE);
		}
		else if(this == VeBlocks.cow_plush)
		{
			return VePlushBlock.defineShapes(state, COW_NORTH_SHAPE, COW_SOUTH_SHAPE, COW_WEST_SHAPE, COW_EAST_SHAPE);
		}
		else if(this == VeBlocks.baby_cow_plush)
		{
			return VePlushBlock.defineShapes(state, BABY_COW_NORTH_SHAPE, BABY_COW_SOUTH_SHAPE, BABY_COW_WEST_SHAPE, BABY_COW_EAST_SHAPE);
		}
		else if(this == VeBlocks.red_mooshroom_plush || this == VeBlocks.brown_mooshroom_plush)
		{
			return VePlushBlock.defineShapes(state, MOOSHROOM_NORTH_SHAPE, MOOSHROOM_SOUTH_SHAPE, MOOSHROOM_WEST_SHAPE, MOOSHROOM_EAST_SHAPE);
		}
		else if(this == VeBlocks.baby_red_mooshroom_plush || this == VeBlocks.baby_brown_mooshroom_plush)
		{
			return VePlushBlock.defineShapes(state, BABY_MOOSHROOM_NORTH_SHAPE, BABY_MOOSHROOM_SOUTH_SHAPE, BABY_MOOSHROOM_WEST_SHAPE, BABY_MOOSHROOM_EAST_SHAPE);
		}
		else if(this == VeBlocks.ocelot_plush || this == VeBlocks.tabby_cat_plush || this == VeBlocks.tuxedo_cat_plush || this == VeBlocks.red_cat_plush || this == VeBlocks.siamese_cat_plush || this == VeBlocks.british_shorthair_cat_plush || this == VeBlocks.calico_cat_plush || this == VeBlocks.ragdoll_cat_plush || this == VeBlocks.white_cat_plush || this == VeBlocks.jellie_cat_plush || this == VeBlocks.black_cat_plush)
		{
			return VePlushBlock.defineShapes(state, OCELOT_NORTH_SHAPE, OCELOT_SOUTH_SHAPE, OCELOT_WEST_SHAPE, OCELOT_EAST_SHAPE);
		}
		else if(this == VeBlocks.persian_cat_plush)
		{
			return VePlushBlock.defineShapes(state, PERSIAN_NORTH_SHAPE, PERSIAN_SOUTH_SHAPE, PERSIAN_WEST_SHAPE, PERSIAN_EAST_SHAPE);
		}
		else if(this == VeBlocks.wolf_plush)
		{
			return VePlushBlock.defineShapes(state, WOLF_NORTH_SHAPE, WOLF_SOUTH_SHAPE, WOLF_WEST_SHAPE, WOLF_EAST_SHAPE);
		}
		else if(this == VeBlocks.enderman_plush)
		{
			return VePlushBlock.defineShapes(state, ENDERMAN_NORTH_SHAPE, ENDERMAN_SOUTH_SHAPE, ENDERMAN_WEST_SHAPE, ENDERMAN_EAST_SHAPE);
		}
		else if(this == VeBlocks.ghast_plush)
		{
			return VePlushBlock.defineShapes(state, GHAST_NORTH_SHAPE, GHAST_SOUTH_SHAPE, GHAST_WEST_SHAPE, GHAST_EAST_SHAPE);
		}
		else if(this == VeBlocks.spider_plush)
		{
			return VePlushBlock.defineShapes(state, SPIDER_NORTH_SHAPE, SPIDER_SOUTH_SHAPE, SPIDER_WEST_SHAPE, SPIDER_EAST_SHAPE);
		}
		
		else if(this == VeBlocks.white_horse_plush || this == VeBlocks.gray_horse_plush || this == VeBlocks.light_gray_horse_plush || this == VeBlocks.brown_horse_plush || this == VeBlocks.black_horse_plush || this == VeBlocks.purple_horse_plush)
		{
			return VePlushBlock.defineShapes(state, HORSE_NORTH_SHAPE, HORSE_SOUTH_SHAPE, HORSE_WEST_SHAPE, HORSE_EAST_SHAPE);
		}
		
		else if(this == VeBlocks.bat_plush)
		{
			return VePlushBlock.defineShapes(state, BAT_NORTH_SHAPE, BAT_SOUTH_SHAPE, BAT_WEST_SHAPE, BAT_EAST_SHAPE);
		}
		else if(this == VeBlocks.rabbit_plush)
		{
			return VePlushBlock.defineShapes(state, RABBIT_NORTH_SHAPE, RABBIT_SOUTH_SHAPE, RABBIT_WEST_SHAPE, RABBIT_EAST_SHAPE);
		}
		else if(this == VeBlocks.skeleton_plush)
		{
			 return VePlushBlock.defineShapes(state, SKELETON_NORTH_SHAPE, SKELETON_SOUTH_SHAPE, SKELETON_WEST_SHAPE, SKELETON_EAST_SHAPE);
		}
		else if(this == VeBlocks.guardian_plush)
		{
			return VePlushBlock.defineShapes(state, COW_Z_SHAPE, COW_Z_SHAPE, COW_X_SHAPE, COW_X_SHAPE);
		}
		else if(this == VeBlocks.silverfish_plush)
		{
			return VePlushBlock.defineShapes(state, SILVERFISH_Z_SHAPE, SILVERFISH_Z_SHAPE, SILVERFISH_X_SHAPE, SILVERFISH_X_SHAPE);
		}
		else if(this == VeBlocks.plains_villager_plush || this == VeBlocks.desert_villager_plush || this == VeBlocks.jungle_villager_plush || this == VeBlocks.taiga_villager_plush || this == VeBlocks.snow_villager_plush || this == VeBlocks.swamp_villager_plush  || this == VeBlocks.savanna_villager_plush|| this == VeBlocks.witch_plush)
		{
			return VILLAGER_SHAPE;
		}
		else if(this == VeBlocks.cave_spider_plush)
		{
			return CAVE_SPIDER_SHAPE;
		}
		return NORMAL_CUBE;
	}
	
	/**
	 * Decide what shape the block should have depending on its direction property
	 */
	private static VoxelShape defineShapes(BlockState state, VoxelShape northShape, VoxelShape southShape, VoxelShape westShape, VoxelShape eastShape)
	{
		switch((Direction)state.get(HORIZONTAL_FACING))
		{
			
			case NORTH:
				return northShape;
			case SOUTH:
				return southShape;
			case WEST:
				return westShape;
			default:
				return eastShape;
		}
	}
}
