package rcarmstrong20.vanilla_expansions.core;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class VeVillagerTrades
{
	public static final Int2ObjectMap<List<ITrade>> LUMBERJACK_TRADES = register(ImmutableMap.of(1, Arrays.asList(new BasicTrade(10, new ItemStack(Items.CLAY_BALL), 16, 2), new BasicTrade(1, new ItemStack(Items.BRICK), 10, 16, 1)), 2, Arrays.asList(new BasicTrade(20, new ItemStack(Blocks.STONE), 16, 10), new BasicTrade(1, new ItemStack(Blocks.CHISELED_STONE_BRICKS), 4, 16, 5)), 3, Arrays.asList(new BasicTrade(16, new ItemStack(Blocks.GRANITE), 16, 20), new BasicTrade(16, new ItemStack(Blocks.ANDESITE), 16, 20), new BasicTrade(16, new ItemStack(Blocks.DIORITE), 16, 20), new BasicTrade(1, new ItemStack(Blocks.POLISHED_ANDESITE), 4, 16, 10), new BasicTrade(1, new ItemStack(Blocks.POLISHED_DIORITE), 4, 16, 10), new BasicTrade(1, new ItemStack(Blocks.POLISHED_GRANITE), 4, 16, 10)), 4, Arrays.asList(new BasicTrade(12, new ItemStack(Items.QUARTZ), 12, 12, 30), new BasicTrade(1, new ItemStack(Blocks.ORANGE_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.WHITE_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BLUE_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIGHT_BLUE_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.GRAY_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIGHT_GRAY_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BLACK_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.RED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.PINK_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.MAGENTA_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIME_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.GREEN_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.CYAN_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.PURPLE_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.YELLOW_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BROWN_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.RED_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA), 1, 12, 15), new BasicTrade(1, new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA), 1, 12, 15)), 5, Arrays.asList(new BasicTrade(1, new ItemStack(Blocks.QUARTZ_PILLAR), 1, 12, 30), new BasicTrade(1, new ItemStack(Blocks.QUARTZ_BLOCK), 1, 12, 30))));
	
	private static Int2ObjectMap<List<ITrade>> register(ImmutableMap<Integer, List<ITrade>> trade)
	{
		return new Int2ObjectOpenHashMap<>(trade);
	}
	
	//@SubscribeEvent
	public static void registerVillagerTrades()
	{
		MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(LUMBERJACK_TRADES, VeVillagerProfessions.LUMBERJACK));
		VanillaExpansions.LOGGER.info("Villager trades registered.");
	}
}
