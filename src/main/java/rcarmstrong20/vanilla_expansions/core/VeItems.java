package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SoupItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.enums.VeArmorMaterial;
import rcarmstrong20.vanilla_expansions.enums.VeItemTier;
import rcarmstrong20.vanilla_expansions.item.VeAxeItem;
import rcarmstrong20.vanilla_expansions.item.VePickaxeItem;
import rcarmstrong20.vanilla_expansions.item.VeShovelItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeSwordItem;
import rcarmstrong20.vanilla_expansions.item.VeVoidBucketItem;

public class VeItems
{
	//Item Property Presets
	
	public static final Item.Properties VE_ITEMS = new Item.Properties().group(VanillaExpansions.VE_GROUP);
	public static final Item.Properties VE_SINGLE_ITEMS = new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP);
	private static final List<Item> ITEMS = new ArrayList<>();
	
	//Vanilla Expansions Items
	
	public static Item ruby = register(VanillaExpansions.location("ruby"), new Item(VE_ITEMS));
	public static Item ruby_axe = new VeAxeItem(VeItemTier.RUBY, 5.0F, -2.8F, new Item.Properties().addToolType(ToolType.AXE, 4).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("ruby_axe"));
	public static Item ruby_pickaxe = new VePickaxeItem(VeItemTier.RUBY, 1, new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("ruby_pickaxe"));
	public static Item ruby_shovel = new VeShovelItem(VeItemTier.RUBY, 1.5F, new Item.Properties().addToolType(ToolType.SHOVEL, 4).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("ruby_shovel"));
	public static Item ruby_sword = new VeSwordItem(VeItemTier.RUBY, 3, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_sword"));
	public static Item ruby_hoe = new HoeItem(VeItemTier.RUBY, 1, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_hoe"));
	public static Item ruby_helmet = new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_helmet"));
	public static Item ruby_chestplate = new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_chestplate"));
	public static Item ruby_leggings = new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_leggings"));
	public static Item ruby_boots = new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("ruby_boots"));
	public static Item emerald_axe = new VeAxeItem(VeItemTier.EMERALD, 6.0F, -3.0F, new Item.Properties().addToolType(ToolType.AXE, 2).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("emerald_axe"));
	public static Item emerald_pickaxe = new VePickaxeItem(VeItemTier.EMERALD, 1, new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("emerald_pickaxe"));
	public static Item emerald_shovel = new VeShovelItem(VeItemTier.EMERALD, 2, new Item.Properties().addToolType(ToolType.SHOVEL, 2).group(VanillaExpansions.VE_GROUP)).setRegistryName(VanillaExpansions.location("emerald_shovel"));
	public static Item emerald_sword = new VeSwordItem(VeItemTier.EMERALD, 4, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_sword"));
	public static Item emerald_hoe = new HoeItem(VeItemTier.EMERALD, 0, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_hoe"));
	public static Item emerald_helmet = new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_helmet"));
	public static Item emerald_chestplate = new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_chestplate"));
	public static Item emerald_leggings = new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_leggings"));
	public static Item emerald_boots = new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("emerald_boots"));
	public static Item airite_ingot = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("airite_ingot"));
	public static Item air_diamond_helmet = new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_diamond_helmet"));
	public static Item air_diamond_chestplate = new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_diamond_chestplate"));
	public static Item air_diamond_leggings = new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_diamond_leggings"));
	public static Item air_diamond_boots = new ArmorItem(VeArmorMaterial.AIR_DIAMOND, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_diamond_boots"));
	public static Item air_iron_helmet = new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_iron_helmet"));
	public static Item air_iron_chestplate = new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_iron_chestplate"));
	public static Item air_iron_leggings = new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_iron_leggings"));
	public static Item air_iron_boots = new ArmorItem(VeArmorMaterial.AIR_IRON, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_iron_boots"));
	public static Item air_gold_helmet = new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_gold_helmet"));
	public static Item air_gold_chestplate = new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_gold_chestplate"));
	public static Item air_gold_leggings = new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_gold_leggings"));
	public static Item air_gold_boots = new ArmorItem(VeArmorMaterial.AIR_GOLD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_gold_boots"));
	public static Item air_ruby_helmet = new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_ruby_helmet"));
	public static Item air_ruby_chestplate = new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_ruby_chestplate"));
	public static Item air_ruby_leggings = new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_ruby_leggings"));
	public static Item air_ruby_boots = new ArmorItem(VeArmorMaterial.AIR_RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_ruby_boots"));
	public static Item air_emerald_helmet = new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_emerald_helmet"));
	public static Item air_emerald_chestplate = new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_emerald_chestplate"));
	public static Item air_emerald_leggings = new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_emerald_leggings"));
	public static Item air_emerald_boots = new ArmorItem(VeArmorMaterial.AIR_EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS).setRegistryName(VanillaExpansions.location("air_emerald_boots"));
	public static Item bok_choy_seeds = new BlockNamedItem(VeBlocks.bok_choy, VE_ITEMS).setRegistryName(VanillaExpansions.location("bok_choy_seeds"));
	public static Item bok_choy = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("bok_choy"));
	public static Item garlic = new BlockNamedItem(VeBlocks.garlic, VE_ITEMS).setRegistryName(VanillaExpansions.location("garlic"));
	public static Item green_onion = new BlockNamedItem(VeBlocks.green_onions, VE_ITEMS).setRegistryName(VanillaExpansions.location("green_onion"));
	public static Item quinoa = new BlockNamedItem(VeBlocks.quinoa, VE_ITEMS).setRegistryName(VanillaExpansions.location("quinoa"));
	public static Item ginger_root = new BlockNamedItem(VeBlocks.ginger, new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.GINGER_ROOT)).setRegistryName(VanillaExpansions.location("ginger_root"));
	public static Item blueberries = new BlockNamedItem(VeBlocks.blueberry_bush, VE_ITEMS.food(VeFoods.BLUEBERRIES)).setRegistryName(VanillaExpansions.location("blueberries"));
	public static Item cranberries = new BlockNamedItem(VeBlocks.cranberry_bush, new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRIES)).setRegistryName(VanillaExpansions.location("cranberries"));
	public static Item cranberry_sauce = new VeSoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRY_SAUCE)).setRegistryName(VanillaExpansions.location("cranberry_sauce"));
	public static Item noodles = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("noodles"));
	public static Item cooked_noodles = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("cooked_noodles"));
	public static Item noodle_soup = new VeSoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.NOODLE_SOUP)).setRegistryName(VanillaExpansions.location("noodle_soup"));
	public static Item noodle_bowl = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("noodle_bowl"));
	public static Item chop_sticks = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("chop_sticks"));
	public static Item quinoa_cerceal = new SoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.QUINOA_CERCEAL)).setRegistryName(VanillaExpansions.location("quinoa_cerceal"));
	public static Item smoky_quartz = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("smoky_quartz"));
	public static Item void_bucket = new VeVoidBucketItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.VOID_WATER_BUCKET)).setRegistryName(VanillaExpansions.location("void_bucket"));
	public static Item frosting = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("frosting"));
	public static Item gingerbread = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("gingerbread"));
	public static Item orange_gumdrop = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("orange_gumdrop"));
	public static Item red_gumdrop = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("red_gumdrop"));
	public static Item caramel_apple = new VeSoupItem(new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.CARAMEL_APPLE)).setRegistryName(VanillaExpansions.location("caramel_apple"));
	public static Item caramel = new Item(VE_ITEMS).setRegistryName(VanillaExpansions.location("caramel"));
	public static Item bread_crumbs = new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.BREAD_CRUMBS)).setRegistryName(VanillaExpansions.location("bread_crumbs"));
	public static Item porkchop_bits = new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.PORKCHOP_BITS)).setRegistryName(VanillaExpansions.location("porkchop_bits"));
	public static Item spruce_cone = new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.SPRUCE_CONE)).setRegistryName(VanillaExpansions.location("spruce_cone"));
	public static Item forests_bounty = new SoupItem(new Item.Properties().group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.FORESTS_BOUNTY)).setRegistryName(VanillaExpansions.location("forests_bounty"));
	
	//Vanilla Replacement Items
	
	public static Item wheat_seeds = new BlockNamedItem(VeBlocks.wheat, new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("minecraft", "wheat_seeds");
	public static Item beetroot_seeds = new BlockNamedItem(VeBlocks.beetroots, new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("minecraft", "beetroot_seeds");
	public static Item nether_wart = new BlockNamedItem(VeBlocks.nether_wart, new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("minecraft", "nether_wart");
	public static Item carrot = new BlockNamedItem(VeBlocks.carrots, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName("minecraft", "carrot");
	public static Item potato = new BlockNamedItem(VeBlocks.potatoes, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName("minecraft", "potato");
	public static Item sweet_berries = new BlockNamedItem(VeBlocks.sweet_berry_bush, new Item.Properties().group(ItemGroup.FOOD)).setRegistryName("minecraft", "sweet_berries");
	
	private static Item register(ResourceLocation name, Item item)
	{
		
		item.setRegistryName(name);
		ITEMS.add(item);
		return item;
	}
	
	@SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();
    }
}
