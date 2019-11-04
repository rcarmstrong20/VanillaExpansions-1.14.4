package rcarmstrong20.vanilla_expansions.init;

import net.minecraft.util.SoundEvent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeSoundEvents
{
	public static final SoundEvent BLOCK_MUSHROOM_BOUNCE = registerSoundEvent("block.mushroom_bounce");
	
	private static SoundEvent registerSoundEvent(String soundName)
	{
		return new SoundEvent(VanillaExpansions.location(soundName)).setRegistryName(VanillaExpansions.location(soundName));
	}
}
