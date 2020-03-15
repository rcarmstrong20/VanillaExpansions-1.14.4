package rcarmstrong20.vanilla_expansions.client.renderer.tile_entity;

import java.util.List;
import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.IForgeBakedModel;
import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import rcarmstrong20.vanilla_expansions.block.VeCampfireBlockOld;
import rcarmstrong20.vanilla_expansions.tile_entity.VeCampfireTileEntity;

@OnlyIn(Dist.CLIENT)
public class VeCampfireTileEntityRenderer extends TileEntityRendererFast<VeCampfireTileEntity> implements IBakedModel
{
	@SuppressWarnings("deprecation")
	public void renderTileEntityFast(VeCampfireTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer)
	{
		Direction direction = tileEntityIn.getBlockState().get(VeCampfireBlockOld.FACING);
		NonNullList<ItemStack> nonnulllist = tileEntityIn.getInventory();
		
		for(int i = 0; i < nonnulllist.size(); ++i)
		{
			ItemStack itemstack = nonnulllist.get(i);
			if (itemstack != ItemStack.EMPTY)
			{
				GlStateManager.pushMatrix();
				GlStateManager.translatef((float)x + 0.5F, (float)y + 0.44921875F, (float)z + 0.5F);
				Direction direction1 = Direction.byHorizontalIndex((i + direction.getHorizontalIndex()) % 4);
				GlStateManager.rotatef(-direction1.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.translatef(-0.3125F, -0.3125F, 0.0F);
				GlStateManager.scalef(0.375F, 0.375F, 0.375F);
				Minecraft.getInstance().getItemRenderer().renderItem(itemstack, (IBakedModel) this.getBakedModel().handlePerspective(TransformType.FIXED));
				GlStateManager.popMatrix();
			}
		}
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAmbientOcclusion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGui3d() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemOverrideList getOverrides() {
		// TODO Auto-generated method stub
		return null;
	}
}
 
