package eu.midnightdust.motschen.polymer_rocks.mixin.block;

import eu.midnightdust.motschen.polymer_rocks.block.ItemDisplayStarfishModel;
import eu.midnightdust.motschen.rocks.block.Starfish;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Starfish.class, remap = false)
public class MixinStarfishBlock implements FactoryBlock, PolymerTexturedBlock {
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        if (state.get(WATERLOGGED)) return PolymerBlockResourceUtils.requestEmpty(BlockModelType.BOTTOM_SLAB_WATERLOGGED);
        else return PolymerBlockResourceUtils.requestEmpty(BlockModelType.TRIPWIRE_BLOCK_FLAT);
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, ServerPlayerEntity player) {
        return Blocks.SEA_PICKLE.getDefaultState();
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new ItemDisplayStarfishModel(initialBlockState, pos);
    }
}
