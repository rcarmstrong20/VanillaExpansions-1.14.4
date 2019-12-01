package rcarmstrong20.vanilla_expansions.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;
import rcarmstrong20.vanilla_expansions.client.renderer.VeCampfireTileEntityRenderer;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeUndervoidParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeVoidDripParticle;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.tile_entity.VeCampfireTileEntity;

public class ClientProxy extends CommonProxy
{
	@Override
	public void onSetupClient()
	{
		super.onSetupClient();
		VeBlockAndItemColors.registerColorHandlers();
		this.registerParticleFactories();
		this.registerTileEntityRenders();
	}
	
	private void registerParticleFactories()
	{
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.DRIPPING_VOID, VeVoidDripParticle.DrippingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.FALLING_VOID, VeVoidDripParticle.FallingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.LANDING_VOID, VeVoidDripParticle.LandingVoidFactory::new);
		Minecraft.getInstance().particles.registerFactory(VeParticleTypes.UNDERVOID, VeUndervoidParticle.Factory::new);
	}
	
	private void registerTileEntityRenders()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(VeCampfireTileEntity.class, new VeCampfireTileEntityRenderer());
	}
}
