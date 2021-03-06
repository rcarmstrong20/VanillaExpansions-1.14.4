package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Suppliers;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SoupItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.enums.VeArmorMaterial;
import rcarmstrong20.vanilla_expansions.enums.VeItemTier;
import rcarmstrong20.vanilla_expansions.item.VeAxeItem;
import rcarmstrong20.vanilla_expansions.item.VeDrinkItem;
import rcarmstrong20.vanilla_expansions.item.VeGlassVialItem;
import rcarmstrong20.vanilla_expansions.item.VePickaxeItem;
import rcarmstrong20.vanilla_expansions.item.VeShovelItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeSwordItem;

/**
 * Author: rcarmstrong20
 */

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeItems
{
	//Item Property Presets
	
	public static final Item.Properties VE_ITEMS = new Item.Properties().group(VanillaExpansions.VE_GROUP);
	public static final Item.Properties VE_SINGLE_ITEMS = new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP);
	private static final List<Item> ITEMS = new ArrayList<>();
	
	//Vanilla Expansions Items
	
	public static Item ruby = register("ruby", new Item(VE_ITEMS));
	public static Item ruby_axe = register("ruby_axe", new VeAxeItem(VeItemTier.RUBY, 5.0F, -2.8F, new Item.Properties().addToolType(ToolType.AXE, 4).group(VanillaExpansions.VE_GROUP)));
	public static Item ruby_pickaxe = register("ruby_pickaxe", new VePickaxeItem(VeItemTier.RUBY, 1, new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(VanillaExpansions.VE_GROUP)));
	public static Item ruby_shovel = register("ruby_shovel", new VeShovelItem(VeItemTier.RUBY, 1.5F, new Item.Properties().addToolType(ToolType.SHOVEL, 4).group(VanillaExpansions.VE_GROUP)));
	public static Item ruby_sword = register("ruby_sword", new VeSwordItem(VeItemTier.RUBY, 3, VE_SINGLE_ITEMS));
	public static Item ruby_hoe = register("ruby_hoe", new HoeItem(VeItemTier.RUBY, 1, VE_SINGLE_ITEMS));
	public static Item ruby_helmet = register("ruby_helmet", new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item ruby_chestplate = register("ruby_chestplate", new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item ruby_leggings = register("ruby_leggings", new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item ruby_boots = register("ruby_boots", new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item emerald_axe = register("emerald_axe", new VeAxeItem(VeItemTier.EMERALD, 6.0F, -3.0F, new Item.Properties().addToolType(ToolType.AXE, 2).group(VanillaExpansions.VE_GROUP)));
	public static Item emerald_pickaxe = register("emerald_pickaxe", new VePickaxeItem(VeItemTier.EMERALD, 1, new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(VanillaExpansions.VE_GROUP)));
	public static Item emerald_shovel = register("emerald_shovel", new VeShovelItem(VeItemTier.EMERALD, 2, new Item.Properties().addToolType(ToolType.SHOVEL, 2).group(VanillaExpansions.VE_GROUP)));
	public static Item emerald_sword = register("emerald_sword", new VeSwordItem(VeItemTier.EMERALD, 4, VE_SINGLE_ITEMS));
	public static Item emerald_hoe = register("emerald_hoe", new HoeItem(VeItemTier.EMERALD, 0, VE_SINGLE_ITEMS));
	public static Item emerald_helmet = register("emerald_helmet", new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item emerald_chestplate = register("emerald_chestplate", new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item emerald_leggings = register("emerald_leggings", new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item emerald_boots = register("emerald_boots", new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item airite_ingot = register("airite_ingot", new Item(VE_ITEMS));
	public static Item air_diamond_helmet = register("air_diamond_helmet", new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item air_diamond_chestplate = register("air_diamond_chestplate", new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item air_diamond_leggings = register("air_diamond_leggings", new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item air_diamond_boots = register("air_diamond_boots", new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item air_iron_helmet = register("air_iron_helmet", new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item air_iron_chestplate = register("air_iron_chestplate", new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item air_iron_leggings = register("air_iron_leggings", new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item air_iron_boots = register("air_iron_boots", new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item air_gold_helmet = register("air_gold_helmet", new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item air_gold_chestplate = register("air_gold_chestplate", new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item air_gold_leggings = register("air_gold_leggings", new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item air_gold_boots = register("air_gold_boots", new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item air_ruby_helmet = register("air_ruby_helmet", new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item air_ruby_chestplate = register("air_ruby_chestplate", new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item air_ruby_leggings = register("air_ruby_leggings", new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item air_ruby_boots = register("air_ruby_boots", new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item air_emerald_helmet = register("air_emerald_helmet", new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
	public static Item air_emerald_chestplate = register("air_emerald_chestplate", new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
	public static Item air_emerald_leggings = register("air_emerald_leggings", new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
	public static Item air_emerald_boots = register("air_emerald_boots", new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
	public static Item bok_choy_seeds = register("bok_choy_seeds", new BlockNamedItem(VeBlocks.bok_choy, VE_ITEMS));
	public static Item bok_choy = register("bok_choy", new Item(VE_ITEMS));
	public static Item garlic = register("garlic", new BlockNamedItem(VeBlocks.garlic, VE_ITEMS));
	public static Item green_onion = register("green_onion", new BlockNamedItem(VeBlocks.green_onions, VE_ITEMS));
	public static Item quinoa = register("quinoa", new BlockNamedItem(VeBlocks.quinoa, VE_ITEMS));
	public static Item ginger_root = register("ginger_root", new BlockNamedItem(VeBlocks.ginger, new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.GINGER_ROOT)));
	public static Item blueberries = register("blueberries", new BlockNamedItem(VeBlocks.blueberry_bush, VE_ITEMS.food(VeFoods.BLUEBERRIES)));
	public static Item cranberries = register("cranberries", new BlockNamedItem(VeBlocks.cranberry_bush, new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRIES)));
	public static Item cranberry_sauce = register("cranberry_sauce", new VeDrinkItem(new Item.Properties().maxStackSize(16).group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRY_SAUCE)));
	public static Item noodles = register("noodles", new Item(VE_ITEMS));
	public static Item cooked_noodles = register("cooked_noodles", new Item(VE_ITEMS));
	public static Item noodle_soup = register("noodle_soup", new VeSoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.NOODLE_SOUP)));
	public static Item noodle_bowl = register("noodle_bowl", new Item(VE_ITEMS));
	public static Item chop_sticks = register("chop_sticks", new Item(VE_ITEMS));
	public static Item quinoa_cerceal = register("quinoa_cerceal", new SoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.QUINOA_CERCEAL)));
	public static Item smoky_quartz = register("smoky_quartz", new Item(VE_ITEMS));
	public static Item void_bucket = register("void_bucket", new BucketItem(Suppliers.ofInstance(VeFluids.VOID), new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.VOID_BUCKET)));
	public static Item frosting = register("frosting", new Item(VE_ITEMS));
	public static Item gingerbread = register("gingerbread", new Item(VE_ITEMS));
	public static Item orange_gumdrop = register("orange_gumdrop", new Item(VE_ITEMS));
	public static Item red_gumdrop = register("red_gumdrop", new Item(VE_ITEMS));
	public static Item caramel_apple = register("caramel_apple", new VeSoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.CARAMEL_APPLE)));
	public static Item caramel = register("caramel", new Item(VE_ITEMS));
	public static Item bread_crumbs = register("bread_crumbs", new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.BREAD_CRUMBS)));
	public static Item porkchop_bits = register("porkchop_bits", new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.PORKCHOP_BITS)));
	public static Item spruce_cone = register("spruce_cone", new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.SPRUCE_CONE)));
	public static Item forests_bounty = register("forests_bounty", new SoupItem(new Item.Properties().group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.FORESTS_BOUNTY)));
	public static Item witchs_cradle_branch = register("witchs_cradle_branch", new BlockNamedItem(VeBlocks.witchs_cradle, new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.WITCHS_CRADLE_BRANCH)));
	public static Item witchs_cradle_soup = register("witchs_cradle_soup", new VeSoupItem(new Item.Properties().group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.WITCHS_CRADLE_SOUP)));
	public static Item glass_vial = register("glass_vial", new VeGlassVialItem(VE_ITEMS));
	public static Item blood_vial = register("blood_vial", new VeDrinkItem(new Item.Properties().group(VanillaExpansions.VE_GROUP).maxStackSize(16).food(VeFoods.BLOOD_VIAL)));
	
	/*
	 * Set the registry name the items and add them to the registry list.
	 */
	private static Item register(String name, Item item)
	{
		item.setRegistryName(VanillaExpansions.MOD_ID, name);
		ITEMS.add(item);
		return item;
	}
	
	/*
	 * Register the Items to the game
	 */
	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();
        
        VanillaExpansions.LOGGER.info("Items registered.");
    }
}
