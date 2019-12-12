package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class TaigaCabinStructure extends ScatteredStructure<NoFeatureConfig>
{
	public static final ResourceLocation STRUCTURE_PATH = VanillaExpansions.location("cabin");
	
	public TaigaCabinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49888_1_)
	{
		super(p_i49888_1_);
	}
	
	@Override
	public String getStructureName()
	{
		return "Taiga_Cabin";
	}
	
	@Override
	public int getSize()
	{
		return 3;
	}
	
	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return TaigaCabinStructure.Start::new;
	}
	
	@Override
	protected int getSeedModifier()
	{
		return 14357617;
	}
	
	public static class Start extends StructureStart
	{
		public Start(Structure<?> structure, int p_i50768_2_, int p_i50768_3_, Biome biome, MutableBoundingBox mutableBoundingBox, int p_i50768_6_, long p_i50768_7_)
		{
			super(structure, p_i50768_2_, p_i50768_3_, biome, mutableBoundingBox, p_i50768_6_, p_i50768_7_);
		}
		
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn)
		{
			templateManagerIn.getTemplate(STRUCTURE_PATH);
			//DesertPyramidPiece desertpyramidpiece = new DesertPyramidPiece(this.rand, chunkX * 16, chunkZ * 16);
			//this.components.add(desertpyramidpiece);
			this.recalculateStructureSize();
		}
	}
}
