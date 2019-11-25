package rcarmstrong20.vanilla_expansions.item.crafting;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeRecipeType
{
	public static final IRecipeType<WoodCuttingRecipe> WOOD_CUTTING = register(VanillaExpansions.MOD_ID, "wood_cutting");
	
	static <T extends IRecipe<?>> IRecipeType<T> register(String id, final String key)
	{
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(id, key), new IRecipeType<T>()
		{
			public String toString()
			{
				return key;
			}
		});
	}
}
