package rcarmstrong20.vanilla_expansions.proxy;

import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;

public class ClientProxy extends CommonProxy
{
	@Override
	public void onSetupClient()
	{
		super.onSetupClient();
		VeBlockAndItemColors.registerColorHandlers();
	}
}
