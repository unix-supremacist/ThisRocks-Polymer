package eu.midnightdust.motschen.polymer_rocks.block;

import eu.midnightdust.motschen.rocks.RocksMain;
import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.pb4.factorytools.api.resourcepack.BaseItemProvider;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.attachment.BlockAwareAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class ItemDisplayNetherGeyserModel extends BlockModel {
    private final ItemDisplayElement main;;
    public static ItemStack NETHER_OFF;
    public static ItemStack NETHER_ON;

    public static void initModels() {
        NETHER_OFF = BaseItemProvider.requestModel(RocksMain.id("block/nether_geyser_off"));
        NETHER_ON = BaseItemProvider.requestModel(RocksMain.id("block/nether_geyser_on"));
    }

    public ItemDisplayNetherGeyserModel(BlockState state) {
        this.main = ItemDisplayElementUtil.createSimple(getModel(state));
        this.main.setDisplaySize(1, 1);
        this.main.setScale(new Vector3f(2));
        this.addElement(this.main);
    }

    @Override
    public void notifyUpdate(HolderAttachment.UpdateType updateType) {
        if (updateType == BlockAwareAttachment.BLOCK_STATE_UPDATE) {
            var state = this.blockState();
            this.main.setItem(getModel(state));
            if (state.contains(Properties.SNOWY) && state.get(Properties.SNOWY)) this.main.setOverridePos(new Vec3d(0d, 0.125d, 0d));
            else this.main.setOverridePos(new Vec3d(0, 0, 0));

            this.tick();
        }
    }
    private ItemStack getModel(BlockState state) {
        return state.get(NetherGeyser.ACTIVE) ? NETHER_ON : NETHER_OFF;
    }
}
