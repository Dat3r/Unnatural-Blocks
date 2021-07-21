package dat3r.unnaturalblocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class UnnaturalBlocks implements ModInitializer {
    static ItemGroup UnnaturalBlocksGroup;
    static Block LaunchPad;
    static Block SpeedPad;
    static Block SmallJumpPad;
    static Block JumpPad;
    static SoundEvent Nothing = new SoundEvent(new Identifier("unnaturalblocks:nothing"));
    static BlockSoundGroup PadSound = new BlockSoundGroup(1.0f, 1.0f, SoundEvents.BLOCK_STONE_BREAK, Nothing, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, Nothing);


    @Override
    public void onInitialize() { // when the mod is loaded
        UnnaturalBlocksGroup = FabricItemGroupBuilder.create(new Identifier("unnaturalblocks:group"))
                .icon(() -> new ItemStack(SpeedPad))
                .build();

        Registry.register(Registry.SOUND_EVENT, Nothing.getId(), Nothing);

        FabricBlockSettings SpeedPadSettings = FabricBlockSettings.of(Material.STONE); // create the settings for your block
        SpeedPadSettings.strength(1.4F);
        SpeedPadSettings.breakByTool(FabricToolTags.PICKAXES, 1); // Break by stone pickaxe or higher
        SpeedPadSettings.sounds(PadSound);
        SpeedPad = new SpeedPad(SpeedPadSettings); // create the block using these settings
        Registry.register(Registry.BLOCK, "unnaturalblocks:speedpad", SpeedPad); // register the block to the game with the id "unnaturalblocks:speedpad"

        Item.Settings speedpaditemsettings = new Item.Settings(); // create the item settings
        speedpaditemsettings.group(UnnaturalBlocksGroup); // set the creative tab in the item settings
        Registry.register(Registry.ITEM, "unnaturalblocks:speedpad", new BlockItem(SpeedPad, speedpaditemsettings)); // register a new block item to the game with the id "unnaturalblocks:speedpad"
        // New Block

        FabricBlockSettings SmallJumpPadSettings = FabricBlockSettings.of(Material.STONE);
        SmallJumpPadSettings.strength(1.4F);
        SmallJumpPadSettings.breakByTool(FabricToolTags.PICKAXES, 1);
        SmallJumpPadSettings.sounds(PadSound);
        SmallJumpPad = new JumpPad(SmallJumpPadSettings, 7, 1);
        Registry.register(Registry.BLOCK, "unnaturalblocks:smalljumppad", SmallJumpPad);

        Item.Settings smalljumppaditemsettings = new Item.Settings();
        smalljumppaditemsettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:smalljumppad", new BlockItem(SmallJumpPad, smalljumppaditemsettings));

        //New Block

        FabricBlockSettings JumpPadSettings = FabricBlockSettings.of(Material.STONE);
        JumpPadSettings.strength(1.3f);
        JumpPadSettings.breakByTool(FabricToolTags.PICKAXES, 1);
        JumpPadSettings.sounds(PadSound);
        JumpPad = new JumpPad(JumpPadSettings, 16, 3);
        Registry.register(Registry.BLOCK, "unnaturalblocks:jumppad", JumpPad);

        Item.Settings JumpPadItemSettings = new Item.Settings();
        JumpPadItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:jumppad", new BlockItem(JumpPad, JumpPadItemSettings));
        // New Block

        FabricBlockSettings LaunchBlockSettings = FabricBlockSettings.of(Material.STONE);
        LaunchBlockSettings.strength(1.1f);
        LaunchBlockSettings.breakByTool(FabricToolTags.PICKAXES, 1);
        LaunchBlockSettings.sounds(PadSound);
        LaunchPad = new LaunchPad(LaunchBlockSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:launchpad", LaunchPad);

        Item.Settings LaunchPadItemSettings = new Item.Settings();
        LaunchPadItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:launchpad", new BlockItem(LaunchPad, LaunchPadItemSettings));
    }
}

