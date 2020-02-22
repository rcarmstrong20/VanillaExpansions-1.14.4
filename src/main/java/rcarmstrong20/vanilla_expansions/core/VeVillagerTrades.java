package rcarmstrong20.vanilla_expansions.core;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class VeVillagerTrades
{
	public static final Int2ObjectMap<ITrade[]> LUMBERJACK_TRADES = register(ImmutableMap.of(1, new VillagerTrades.ITrade[] {convertItemForEmeraldTrade(new ItemStack(Blocks.OAK_LOG, 4), new ItemStack(Blocks.OAK_WOOD), 2, 4), convertItemForEmeraldTrade(new ItemStack(Blocks.SPRUCE_LOG, 4), new ItemStack(Blocks.SPRUCE_WOOD), 2, 4), convertItemForEmeraldTrade(new ItemStack(Blocks.BIRCH_LOG, 4), new ItemStack(Blocks.BIRCH_WOOD), 2, 4)},
																							 2, new VillagerTrades.ITrade[] {new BasicTrade(2, new ItemStack(Items.GOLDEN_AXE), 6, 2, 0.02F), new BasicTrade(3, new ItemStack(Items.IRON_AXE), 3, 2, 0.02F)},
																							 3, new VillagerTrades.ITrade[] {itemForEmeraldTrade(new ItemStack(Blocks.OAK_LOG, 15), 16, 1), itemForEmeraldTrade(new ItemStack(Blocks.BIRCH_LOG, 15), 10, 1), itemForEmeraldTrade(new ItemStack(Blocks.JUNGLE_LOG, 25), 10, 1), itemForEmeraldTrade(new ItemStack(Blocks.ACACIA_LOG, 10), 10, 1), itemForEmeraldTrade(new ItemStack(Blocks.DARK_OAK_LOG, 20), 10, 1)},
																							 4, new VillagerTrades.ITrade[] {new BasicTrade(5, new ItemStack(Items.OAK_STAIRS), 12, 12, 30)},
																							 5, new VillagerTrades.ITrade[] {masterTrade(1, new ItemStack(Blocks.NOTE_BLOCK), 5, 0.05F), masterTrade(5, new ItemStack(Blocks.JUKEBOX), 10, 0.05F)}));
	
	private static Int2ObjectMap<VillagerTrades.ITrade[]> register(ImmutableMap<Integer, VillagerTrades.ITrade[]> trades_map)
	{
		return new Int2ObjectOpenHashMap<>(trades_map);
	}
	
	private static BasicTrade itemForEmeraldTrade(ItemStack price, int maxTrades, int xp)
	{
		return new BasicTrade(price, new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
	}
	
	private static BasicTrade convertItemForEmeraldTrade(ItemStack price, ItemStack forSale, int maxTrades, int xp)
	{
		return new BasicTrade(price, new ItemStack(Items.EMERALD), forSale, maxTrades, xp, 0.05F);
	}
	
	private static BasicTrade masterTrade(int emeralds, ItemStack forSale, int maxTrades, float priceMult)
	{
		return new BasicTrade(emeralds, forSale, maxTrades, 0, priceMult);
	}
}
