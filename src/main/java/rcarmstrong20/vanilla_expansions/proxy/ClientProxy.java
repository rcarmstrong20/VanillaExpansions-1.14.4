package rcarmstrong20.vanilla_expansions.proxy;

import net.minecraft.client.Minecraft;
import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeUndervoidParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeVoidDripParticle;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;

public class ClientProxy extends CommonProxy
{
	@Override
	public void onSetupClient()
	{
		super.onSetupClient();
		VeBlockAndItemColors.registerColorHandlers();
		this.registerParticleFactories();
	}
	
	private void registerParticleFactories()
	{
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.DRIPPING_VOID, VeVoidDripParticle.DrippingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.FALLING_VOID, VeVoidDripParticle.FallingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.LANDING_VOID, VeVoidDripParticle.LandingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.UNDERVOID, VeUndervoidParticle.Factory::new);
	}
}
