package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

//@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeRecipeSerializers
{
    private static final List<IRecipeSerializer<?>> RECIPES = new ArrayList<>();

    //public static final IRecipeSerializer<WoodCuttingRecipe> WOOD_CUTTING = register(VanillaExpansions.location("wood_cutting"), new SingleItemRecipe.Serializer<>(WoodCuttingRecipe::new));
    
	@SuppressWarnings("unused")
	private static <T extends IRecipeSerializer<? extends IRecipe<?>>> T register(ResourceLocation name, T recipe)
    {
        recipe.setRegistryName(name);
        RECIPES.add(recipe);
        return recipe;
    }
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<IRecipeSerializer<?>> event)
    {
        RECIPES.forEach(item -> event.getRegistry().register(item));
        RECIPES.clear();
        
        VanillaExpansions.LOGGER.info("Recipe serializers registered.");
    }
}
