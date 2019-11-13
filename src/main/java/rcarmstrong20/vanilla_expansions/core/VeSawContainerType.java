package rcarmstrong20.vanilla_expansions.inventory.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeSawContainerType
{
	public static final List<ContainerType<?>> CONTAINER_TYPES = new ArrayList<>();
	
	public static final ContainerType<?> SAW = buildType(VanillaExpansions.location("saw"), VeSawContainer::new);
	
	public static ContainerType<?> buildType(ResourceLocation name, ContainerType.IFactory<?> factory)
	{
		ContainerType<?> type = new ContainerType<>(factory);
		type.setRegistryName(name);
		CONTAINER_TYPES.add(type);
		return type;
	}
	
	@SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<ContainerType<?>> event)
    {
		CONTAINER_TYPES.forEach(type -> event.getRegistry().register(type));
		CONTAINER_TYPES.clear();
    }
}
