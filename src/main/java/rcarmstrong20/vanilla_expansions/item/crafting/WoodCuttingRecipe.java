package rcarmstrong20.vanilla_expansions.item.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeRecipeSerializers;

public class WoodCuttingRecipe extends SingleItemRecipe
{
	public WoodCuttingRecipe(IRecipeType<?> type, IRecipeSerializer<?> serializer, ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
	{
		super(VeRecipeType.WOOD_CUTTING, VeRecipeSerializers.WOOD_CUTTING, id, group, ingredient, result);
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn)
	{
		return this.ingredient.test(inv.getStackInSlot(0));
	}
	
	@Override
	public ItemStack getIcon()
	{
		return new ItemStack(VeBlocks.saw);
	}
}
