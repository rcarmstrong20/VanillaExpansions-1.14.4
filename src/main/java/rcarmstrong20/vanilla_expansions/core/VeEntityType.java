package rcarmstrong20.vanilla_expansions.core;
/*
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * Author: rcarmstrong20
 */
/*
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeEntityType
{
	private static final List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();
	
	private static <T extends Entity> EntityType<T> buildType(String id, String name, EntityType.Builder<T> builder)
    {
        EntityType<T> type = builder.build(id + ":" + name);
        type.setRegistryName(new ResourceLocation(id, name));
        ENTITY_TYPES.add(type);
        return type;
    }
	
	@SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<EntityType<?>> event)
    {
        ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        ENTITY_TYPES.clear();
        
        VanillaExpansions.LOGGER.info("Entity types registered.");
    }
}
*/