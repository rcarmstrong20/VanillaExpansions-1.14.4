package rcarmstrong20.vanilla_expansions.item.crafting;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IVeRecipeType
{
	IRecipeType<CuttingRecipe> CUTTING = register("cutting");
	
	static <T extends IRecipe<?>> IRecipeType<T> register(final String key)
	{
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
			public String toString()
			{
				return key;
			}
		});
	}
}
