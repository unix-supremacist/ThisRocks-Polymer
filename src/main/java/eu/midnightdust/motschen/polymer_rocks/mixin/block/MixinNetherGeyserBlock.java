package eu.midnightdust.motschen.polymer_rocks.mixin.block;

import eu.midnightdust.motschen.polymer_rocks.block.ItemDisplayNetherGeyserModel;
import eu.midnightdust.motschen.polymer_rocks.block.ItemDisplayOverworldGeyserModel;
import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = NetherGeyser.class, remap = false)
public class MixinNetherGeyserBlock implements FactoryBlock, PolymerTexturedBlock {

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return PolymerBlockResourceUtils.requestEmpty(BlockModelType.TRIPWIRE_BLOCK_FLAT);
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, ServerPlayerEntity player) {
        return Blocks.NETHERRACK.getDefaultState();
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new ItemDisplayNetherGeyserModel(initialBlockState, pos);
    }
}
