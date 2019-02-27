/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Sep 30, 2015, 10:01:17 PM (GMT)]
 */
package vazkii.botania.common.block.decor.panes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.render.IModelRegister;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.lib.LibMisc;

import javax.annotation.Nonnull;

public class BlockModPane extends BlockPane {

	public BlockModPane(Properties props) {
		super(props);
	}

	@Override
	public boolean canBeConnectedTo(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing) {
		Block block = world.getBlockState(pos.offset(facing)).getBlock();
		return block == ModBlocks.elfGlass || block == ModBlocks.manaGlass || block == ModBlocks.bifrostPerm
				|| super.canBeConnectedTo(state, world, pos, facing);
	}

	@OnlyIn(Dist.CLIENT)
	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
