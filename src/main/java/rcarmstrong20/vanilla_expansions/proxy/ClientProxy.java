package rcarmstrong20.vanilla_expansions.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;
import rcarmstrong20.vanilla_expansions.client.renderer.screen.VeWoodcutterScreen;
import rcarmstrong20.vanilla_expansions.client.renderer.tile_entity.VeCampfireTileEntityRenderer;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VeVillagerProfessions;
import rcarmstrong20.vanilla_expansions.core.VeVillagerTrades;
import rcarmstrong20.vanilla_expansions.tile_entity.VeCampfireTileEntity;

public class ClientProxy extends CommonProxy
{
	@Override
	public void onSetupClient()
	{
		super.onSetupClient();
		VeBlockAndItemColors.registerColorHandlers();
		this.registerTileEntityRenders();
		this.registerScreenFactories();
		this.registerTrades();
	}
	
	/*
	 * Register the screen factories for the containers.
	 */
	private void registerScreenFactories()
	{
		ScreenManager.registerFactory(VeContainerTypes.WOODCUTTER, VeWoodcutterScreen::new);
	}
	
	/*
	 * Add the new trade lists to the vanilla ones along with the corresponding profession for registry.
	 */
	private void registerTrades()
	{
		VillagerTrades.field_221239_a.put(VeVillagerProfessions.LUMBERJACK, VeVillagerTrades.LUMBERJACK_TRADES);
	}
	
	private void registerTileEntityRenders()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(VeCampfireTileEntity.class, new VeCampfireTileEntityRenderer());
	}
}
