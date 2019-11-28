package rcarmstrong20.vanilla_expansions.client.renderer.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.core.VeFluids;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;

public class VoidDripParticle extends SpriteTexturedParticle
{	
	private final Fluid fluid;
	
	private VoidDripParticle(World worldIn, double xIn, double yIn, double zIn, Fluid fluidIn)
	{
		super(worldIn, xIn, yIn, zIn);
		this.setSize(0.01F, 0.01F);
		this.particleGravity = 0.06F;
		this.fluid = fluidIn;
	}
	
	public IParticleRenderType getRenderType()
	{
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	
	public int getBrightnessForRender(float partialTick)
	{
		return this.fluid.isIn(FluidTags.LAVA) ? 20 : super.getBrightnessForRender(partialTick);
	}
	
	public void tick()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.func_217576_g();
		if (!this.isExpired) {
			this.motionY -= (double)this.particleGravity;
			this.move(this.motionX, this.motionY, this.motionZ);
			this.func_217577_h();
			if (!this.isExpired)
			{
				this.motionX *= (double)0.98F;
				this.motionY *= (double)0.98F;
				this.motionZ *= (double)0.98F;
				BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
				IFluidState ifluidstate = this.world.getFluidState(blockpos);
				if (ifluidstate.getFluid() == this.fluid && this.posY < (double)((float)blockpos.getY() + ifluidstate.func_215679_a(this.world, blockpos))) {
					this.setExpired();
				}
			}
		}
	}
	
	protected void func_217576_g()
	{
		if (this.maxAge-- <= 0)
		{
			this.setExpired();
		}
	}
	
	protected void func_217577_h() {}
	
	@OnlyIn(Dist.CLIENT)
	static class Dripping extends VoidDripParticle
	{
		private final IParticleData field_217579_C;
		
		private Dripping(World worldIn, double p_i50509_2_, double p_i50509_4_, double p_i50509_6_, Fluid p_i50509_8_, IParticleData p_i50509_9_) {
			super(worldIn, p_i50509_2_, p_i50509_4_, p_i50509_6_, p_i50509_8_);
			this.field_217579_C = p_i50509_9_;
			this.particleGravity *= 0.02F;
			this.maxAge = 40;
		}
		
		protected void func_217576_g() {
			if (this.maxAge-- <= 0) {
				this.setExpired();
				this.world.addParticle(this.field_217579_C, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
			}
		}
		
		protected void func_217577_h() {
			this.motionX *= 0.02D;
			this.motionY *= 0.02D;
			this.motionZ *= 0.02D;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	static class DrippingVoid extends VoidDripParticle.Dripping
	{
		private DrippingVoid(World worldIn, double p_i50513_2_, double p_i50513_4_, double p_i50513_6_, Fluid p_i50513_8_, IParticleData p_i50513_9_)
		{
			super(worldIn, p_i50513_2_, p_i50513_4_, p_i50513_6_, p_i50513_8_, p_i50513_9_);
		}
		
		protected void func_217576_g()
		{
			this.particleRed = 1.0F;
			this.particleGreen = 16.0F / (float)(40 - this.maxAge + 16);
			this.particleBlue = 4.0F / (float)(40 - this.maxAge + 8);
			super.func_217576_g();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class DrippingVoidFactory implements IParticleFactory<BasicParticleType>
	{
		protected final IAnimatedSprite spriteSet;
		
		public DrippingVoidFactory(IAnimatedSprite spriteSetIn)
		{
			this.spriteSet = spriteSetIn;
		}
		
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			VoidDripParticle.DrippingVoid dripparticle$drippingvoid = new VoidDripParticle.DrippingVoid(worldIn, x, y, z, VeFluids.VOID, VeParticleTypes.FALLING_VOID);
			dripparticle$drippingvoid.selectSpriteRandomly(this.spriteSet);
			return dripparticle$drippingvoid;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	static class Falling extends VoidDripParticle
	{
		private final IParticleData field_217578_C;
		
		private Falling(World p_i50511_1_, double p_i50511_2_, double p_i50511_4_, double p_i50511_6_, Fluid p_i50511_8_, IParticleData p_i50511_9_)
		{
			super(p_i50511_1_, p_i50511_2_, p_i50511_4_, p_i50511_6_, p_i50511_8_);
			this.field_217578_C = p_i50511_9_;
			this.maxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
		}
		
		protected void func_217577_h()
		{
			if (this.onGround)
			{
				this.setExpired();
				this.world.addParticle(this.field_217578_C, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class FallingVoidFactory implements IParticleFactory<BasicParticleType>
	{
		protected final IAnimatedSprite spriteSet;
		
		public FallingVoidFactory(IAnimatedSprite spriteIn)
		{
			this.spriteSet = spriteIn;
		}
		
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			VoidDripParticle dripparticle = new VoidDripParticle.Falling(worldIn, x, y, z, VeFluids.VOID, VeParticleTypes.LANDING_VOID);
			dripparticle.selectSpriteRandomly(this.spriteSet);
			return dripparticle;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	static class Landing extends VoidDripParticle
	{
		private Landing(World p_i50507_1_, double p_i50507_2_, double p_i50507_4_, double p_i50507_6_, Fluid p_i50507_8_)
		{
			super(p_i50507_1_, p_i50507_2_, p_i50507_4_, p_i50507_6_, p_i50507_8_);
			this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class LandingVoidFactory implements IParticleFactory<BasicParticleType>
	{
		protected final IAnimatedSprite spriteSet;
		
		public LandingVoidFactory(IAnimatedSprite sprite)
		{
			this.spriteSet = sprite;
		}
		
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			VoidDripParticle dripparticle = new VoidDripParticle.Landing(worldIn, x, y, z, VeFluids.VOID);
			dripparticle.selectSpriteRandomly(this.spriteSet);
			return dripparticle;
		}
	}
}
