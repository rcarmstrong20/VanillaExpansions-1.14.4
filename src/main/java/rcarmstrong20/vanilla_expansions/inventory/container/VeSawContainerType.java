package rcarmstrong20.vanilla_expansions.inventory.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeSawContainerType extends ContainerType<Container>
{
	public static final ContainerType<?> SAW = registerContainerType(VeSawContainer::new).setRegistryName(VanillaExpansions.location("saw"));
	
	@SuppressWarnings("unchecked")
	public VeSawContainerType(IFactory<?> factory)
	{
		super((IFactory<Container>) factory);
	}
	
	public static ContainerType<?> registerContainerType(ContainerType.IFactory<?> factory)
	{
		return new ContainerType<>(factory);
	}
}
