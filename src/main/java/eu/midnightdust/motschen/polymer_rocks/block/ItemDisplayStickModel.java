package eu.midnightdust.motschen.polymer_rocks.block;

import eu.midnightdust.motschen.rocks.RocksMain;
import eu.pb4.factorytools.api.resourcepack.BaseItemProvider;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.attachment.BlockAwareAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import org.joml.Vector3f;

public class ItemDisplayStickModel extends BlockModel {
    private final ItemDisplayElement main;
    public static ItemStack SMALL_OAK_STICK_MODEL;
    public static ItemStack MEDIUM_OAK_STICK_MODEL;
    public static ItemStack LARGE_OAK_STICK_MODEL;

    public static void initModels() {
        // TODO: Add models for the different wood types
        SMALL_OAK_STICK_MODEL = BaseItemProvider.requestModel(RocksMain.id("block/small_oak_stick"));
        MEDIUM_OAK_STICK_MODEL = BaseItemProvider.requestModel(RocksMain.id("block/medium_oak_stick"));
        LARGE_OAK_STICK_MODEL = BaseItemProvider.requestModel(RocksMain.id("block/large_oak_stick"));
    }

    public ItemDisplayStickModel(BlockState state) {
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

            this.tick();
        }
    }
    private ItemStack getModel(BlockState state) {
        return switch (state.get(RocksMain.STICK_VARIATION)) {
            case SMALL -> SMALL_OAK_STICK_MODEL;
            case MEDIUM -> MEDIUM_OAK_STICK_MODEL;
            case LARGE -> LARGE_OAK_STICK_MODEL;
        };
    }
}
