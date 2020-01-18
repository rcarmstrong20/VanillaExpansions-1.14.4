package rcarmstrong20.vanilla_expansions;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rcarmstrong20.vanilla_expansions.proxy.ClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.CommonProxy;

@Mod("ve")
public class VanillaExpansions
{
	public static Object modInstance;
	public static final String MOD_ID = "ve";
	public static final String MINECRAFT_ID = "minecraft";
	public static final Logger LOGGER = LogManager.getLogger(VanillaExpansions.MOD_ID);
	public static final VeItemGroup VE_GROUP = new VeItemGroup(VanillaExpansions.MOD_ID);
	public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	public VanillaExpansions()
	{
		modInstance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		LOGGER.info("setup method registered");
		PROXY.onSetupCommon();
	}
	
	private void clientRegistries(final FMLCommonSetupEvent event)
	{
		LOGGER.info("client method registered");
		PROXY.onSetupClient();
	}
	
	/**
	 * Get the resource location ve:name
	 */
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(VanillaExpansions.MOD_ID, name);
	}
	
	/**
	 * Get the resource location minecraft:name
	 */
	public static ResourceLocation vanillaLocation(String name)
	{
		return new ResourceLocation(VanillaExpansions.MINECRAFT_ID, name);
	}
	
	@SubscribeEvent
	public void onRightClickBlock(final RightClickBlock event)
	{
		BlockState worldState = event.getWorld().getBlockState(event.getPos());
		Item item = event.getItemStack().getItem();
		BlockPos pos = event.getPos();
		World world = event.getWorld();
		Random random = new Random();
		IntegerProperty cropsAge = CropsBlock.AGE;
		IntegerProperty netherWartAge = NetherWartBlock.AGE;
		IntegerProperty beetrootAge = BeetrootBlock.BEETROOT_AGE;
		
		if(!event.getWorld().isRemote)
		{
			if(worldState.getBlock() instanceof CropsBlock && item != Items.BONE_MEAL)
			{
				if(worldState.getBlock() instanceof BeetrootBlock)
				{
					if(worldState.get(beetrootAge) == 3)
					{
						world.setBlockState(pos, worldState.with(beetrootAge, 0), 2);
						playSoundAndSpawnDrops(worldState, world, pos, random);
						event.setResult(Result.ALLOW);
						event.setCanceled(true);
					}
				}
				else if(worldState.get(cropsAge) == 7)
				{
					world.setBlockState(pos, worldState.with(cropsAge, 0), 2);
					playSoundAndSpawnDrops(worldState, world, pos, random);
					event.setResult(Result.ALLOW);
					event.setCanceled(true);
				}
			}
			else if(worldState.getBlock() instanceof NetherWartBlock)
			{
				if(worldState.get(netherWartAge) == 3)
				{
					world.setBlockState(pos, worldState.with(netherWartAge, 0), 2);
					playSoundAndSpawnDrops(worldState, world, pos, random);
					event.setResult(Result.ALLOW);
					event.setCanceled(true);
				}
			}
			else
			{
				event.setResult(Result.DEFAULT);
			}
		}
	}
	
	private static void playSoundAndSpawnDrops(BlockState state, World world, BlockPos pos, Random random)
	{
		Block.spawnDrops(state, world, pos);
		world.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F);
	}
	
	/*
	@SubscribeEvent
	public void onEntityClick(final RightClickBlock event)
	{
		BlockPos pos = event.getPos();
		BlockState itemBlockState = Block.getBlockFromItem(event.getItemStack().getItem()).getDefaultState();
		BlockState worldBlockState = event.getWorld().getBlockState(pos);
		String itemBlockName = worldBlockState.getBlock().getRegistryName().toString().replaceFirst("slab", "");
		String worldBlockName = itemBlockState.getBlock().getRegistryName().toString().replaceFirst("slab", "");
		String worldBlockName2 = worldBlockName.substring(worldBlockName.indexOf(":") + 1);
		String itemBlockName2 = itemBlockName.substring(itemBlockName.indexOf(":") + 1);
		String topDoubleSlab = worldBlockName + itemBlockName2 + "double_slab";
		String bottomDoubleSlab = itemBlockName + worldBlockName2 + "double_slab";
		
		if(itemBlockState.getBlock() instanceof SlabBlock && itemBlockState != worldBlockState && worldBlockState.get(SlabBlock.TYPE) != SlabType.DOUBLE)
		{
			if(worldBlockState.getBlock() instanceof SlabBlock && worldBlockState.get(SlabBlock.TYPE) == SlabType.BOTTOM)
			{
				event.getWorld().setBlockState(pos, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(bottomDoubleSlab)).getDefaultState(), 1);
				LOGGER.info(bottomDoubleSlab);
				event.setCanceled(true);
			}
			else
			{
				event.getWorld().setBlockState(pos, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(topDoubleSlab)).getDefaultState(), 1);
				LOGGER.info(topDoubleSlab);
				event.setCanceled(true);
			}
		}
	}
	*/
}
