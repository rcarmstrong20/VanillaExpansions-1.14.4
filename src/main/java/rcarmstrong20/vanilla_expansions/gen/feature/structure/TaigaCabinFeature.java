package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class TaigaCabinFeature extends Feature<NoFeatureConfig>
{
	public static final ResourceLocation STRUCTURE_PATH = VanillaExpansions.location("taiga_cabin");
	
	public TaigaCabinFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49888_1_)
	{
		super(p_i49888_1_);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		Random random = worldIn.getRandom();
		Rotation[] arotation = Rotation.values();
		Rotation rotation = arotation[random.nextInt(arotation.length)];
		TemplateManager templatemanager = ((ServerWorld)worldIn.getWorld()).getSaveHandler().getStructureTemplateManager();
		Template template = templatemanager.getTemplateDefaulted(STRUCTURE_PATH);
		ChunkPos chunkpos = new ChunkPos(pos);
		MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(random).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
		BlockPos blockpos = template.transformedSize(rotation);
		int x = random.nextInt(16 - blockpos.getX());
		int y = generator.getGroundHeight();
		int z = random.nextInt(16 - blockpos.getZ());
		BlockPos blockpos1 = new BlockPos(x, y, z);
		
		BlockPos blockpos2 = template.getZeroPositionWithTransform(blockpos1, Mirror.NONE, rotation);
		System.out.print(blockpos1);
		template.addBlocksToWorld(worldIn, blockpos2, placementsettings, 4);
		return true;
	}
}
