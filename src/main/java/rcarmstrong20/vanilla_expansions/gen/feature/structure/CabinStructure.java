package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.List;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeBiomes;

public class CabinStructure extends ScatteredStructure<NoFeatureConfig>
{
	public CabinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}
	
	/**
	 * A modifier to the random seed for scattered structures, so that every structure does not spawn in the same place
	 *
	 * @return a number, vanilla likes to specifically use 8 digit numbers
	 */
	@Override
	protected int getSeedModifier()
	{
		return 12345678;
	}
	
	/**
	 * Get the start of the structure
	 * In vanilla the start is usually a static inner class of the structure class, follow this convention if you wish
	 *
	 * @return method reference to Start constructor
	 */
	@Override
	public Structure.IStartFactory getStartFactory()
	{
		return CabinStructure.Start::new;
	}
	
	/**
	 * An 'id' for the structure, distinct from registry id
	 * Used for the Locate command (by forge only, vanilla uses its own system)
	 * Should probably be in the format 'modid:name'
	 *
	 * @return name of structure
	 */
	
	@Override
	public String getStructureName()
	{
		return VanillaExpansions.MOD_ID + ":cabin";
	}
	
	/**
	 * Legacy code, only used to update from old structure format
	 *
	 * @return irrelevant
	 */
	@Override
	public int getSize()
	{
		return 3;
	}
	
	/**
	 * The structure start is responsible for creating the structure in memory, but not for placing the blocks themselves
	 */
	public static class Start extends StructureStart
	{
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox boundingBox, int references, long seed)
		{
			super(structure, chunkX, chunkZ, biome, boundingBox, references, seed);
		}
		
		/**
		 * For most structures this is the only method you will need to care about
		 * Not a lot needs to be done here, most of the work is done by structure pieces
		 * Examples of things vanilla does for different structures here include:
		 * - Getting configs from the chunk generator
		 * - Deciding the rotation of the structure
		 * - Getting height (rarely, most times height is determined in the piece)
		 */
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome)
		{
			ResourceLocation templateResource = null;
			if(isBiome(biome, VeBiomes.TAIGA_BIOMES))
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "taiga_cabin");
			}
			else if(biome == Biomes.FOREST)
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "forest_cabin");
			}
			else if(isBiome(biome, VeBiomes.BIRCH_FOREST_BIOMES))
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "birch_forest_cabin");
			}
			
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			CabinPiece piece = new CabinPiece(templateManager, templateResource, new BlockPos(chunkX * 16, 0, chunkZ * 16), rotation);
			this.components.add(piece);
			this.recalculateStructureSize();
		}
		
		/**
		 * Check to see if the current biome exists in the given list of biomes.
		 */
		private static boolean isBiome(Biome currentBiome, List<Biome> biomes)
		{
			for(Biome biome : biomes)
			{
				if(currentBiome == biome)
				{
					return true;
				}
			}
			return false;
		}
	}
}
