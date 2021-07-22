package dat3r.unnaturalblocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class UnnaturalBlocks implements ModInitializer, ServerTickEvents.EndTick {
    public static ItemGroup UnnaturalBlocksGroup;
    public static Block GlowBlock;
    public static Block LaunchPad;
    public static Block SpeedPad;
    public static Block SmallJumpPad;
    public static Block ElytraPad;
    public static Block UltraElytraPad;
    public static Block Blindnesspad;
    public static Block PushPad;
    public static Block JumpPad;
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
        SpeedPadSettings.requiresTool();
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
        SmallJumpPadSettings.requiresTool();
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
        JumpPadSettings.requiresTool();
        JumpPadSettings.sounds(PadSound);
        JumpPad = new JumpPad(JumpPadSettings, 20, 4);
        Registry.register(Registry.BLOCK, "unnaturalblocks:jumppad", JumpPad);

        Item.Settings JumpPadItemSettings = new Item.Settings();
        JumpPadItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:jumppad", new BlockItem(JumpPad, JumpPadItemSettings));
        // New Block

        FabricBlockSettings LaunchBlockSettings = FabricBlockSettings.of(Material.STONE);
        LaunchBlockSettings.strength(1.1f);
        LaunchBlockSettings.breakByTool(FabricToolTags.PICKAXES, 1);
        LaunchBlockSettings.requiresTool();
        LaunchBlockSettings.sounds(PadSound);
        LaunchPad = new LaunchPad(LaunchBlockSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:launchpad", LaunchPad);

        Item.Settings LaunchPadItemSettings = new Item.Settings();
        LaunchPadItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:launchpad", new BlockItem(LaunchPad, LaunchPadItemSettings));
        //New Block

        FabricBlockSettings ElytrapadSettings = FabricBlockSettings.of(Material.STONE);
        ElytrapadSettings.strength(1.0f);
        ElytrapadSettings.breakByTool(FabricToolTags.PICKAXES, 2);
        ElytrapadSettings.requiresTool();
        ElytrapadSettings.sounds(PadSound);
        ElytraPad = new Block(ElytrapadSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:elytrapad", ElytraPad);

        Item.Settings ElytraPadItemSettings = new Item.Settings();
        ElytraPadItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:elytrapad", new BlockItem(ElytraPad, ElytraPadItemSettings));

        // New Block with same settings
        UltraElytraPad = new Block(ElytrapadSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:ultraelytrapad", UltraElytraPad);
        Registry.register(Registry.ITEM, "unnaturalblocks:ultraelytrapad", new BlockItem(UltraElytraPad, ElytraPadItemSettings));

        //New Block

        FabricBlockSettings GlowBlockSettings = FabricBlockSettings.of(Material.STONE);
        GlowBlockSettings.strength(1.7f);
        GlowBlockSettings.breakByTool(FabricToolTags.PICKAXES, 1);
        GlowBlockSettings.requiresTool();
        GlowBlockSettings.sounds(PadSound);
        GlowBlockSettings.luminance((state) -> 15);
        GlowBlock = new GlowBlock(GlowBlockSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:glowblock", GlowBlock);

        Item.Settings GlowBlockItemSettings = new Item.Settings();
        GlowBlockItemSettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:glowblock", new BlockItem(GlowBlock, GlowBlockItemSettings));

        FabricBlockSettings Blindnesspadsetting = FabricBlockSettings.of(Material.STONE);
        Blindnesspadsetting.strength(1.3f);
        Blindnesspadsetting.breakByTool(FabricToolTags.PICKAXES, 1);
        Blindnesspadsetting.requiresTool();
        Blindnesspadsetting.sounds(new BlockSoundGroup(1.0f, 1.0f, SoundEvents.BLOCK_SOUL_SAND_BREAK, Nothing, SoundEvents.BLOCK_SOUL_SAND_PLACE, SoundEvents.BLOCK_SOUL_SAND_HIT, Nothing));
        Blindnesspad = new BlindnessPad(Blindnesspadsetting);
        Registry.register(Registry.BLOCK, "unnaturalblocks:blindnesspad", Blindnesspad);

        Item.Settings Blindnesspaditemsettings = new Item.Settings();
        Blindnesspaditemsettings.group(UnnaturalBlocksGroup);
        Registry.register(Registry.ITEM, "unnaturalblocks:blindnesspad", new BlockItem(Blindnesspad, Blindnesspaditemsettings));

        FabricBlockSettings PushPadSettings = FabricBlockSettings.of(Material.STONE);
        PushPadSettings.strength(1.1f);
        PushPadSettings.breakByTool(FabricToolTags.PICKAXES, 2);
        PushPadSettings.requiresTool();
        PushPadSettings.sounds(new BlockSoundGroup(1.0f, 1.0f, SoundEvents.BLOCK_WOOL_BREAK, Nothing, SoundEvents.BLOCK_WOOL_PLACE, SoundEvents.BLOCK_WOOL_HIT, Nothing));
        PushPadSettings.dropsNothing();
        PushPad = new PushPad(PushPadSettings);
        Registry.register(Registry.BLOCK, "unnaturalblocks:pushpad", PushPad);

        Item.Settings PushPadItemSettings = new Item.Settings();
        Registry.register(Registry.ITEM, "unnaturalblocks:pushpad", new BlockItem(PushPad, PushPadItemSettings));


        // Registering our class so that it runs on the tick event
        ServerTickEvents.END_SERVER_TICK.register(this);
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        List<ServerPlayerEntity> PlayerList = server.getPlayerManager().getPlayerList(); // get the list of all players on the server

        for (ServerPlayerEntity Player: PlayerList) { // for each player in this list
            ItemStack equippedStack = Player.getEquippedStack(EquipmentSlot.CHEST); // get the stack they are wearing

            if (equippedStack.isEmpty()) { // check if they are wearing nothing on their chest
                World playerworld = Player.world; // get the world the player is in
                BlockPos playerpos = Player.getBlockPos().up(2); // the block pos 2 spaces above where the players feet are

                for (int i = 0; i < 10; i++) { // we're running this 10 times
                    BlockState state = playerworld.getBlockState(playerpos);

                    if (state.isOf(ElytraPad)) { // if the block at the current position we are checking is elytra pad
                        ItemStack Elytra = CreateElytra();

                        Player.equipStack(EquipmentSlot.CHEST, Elytra); // equip the elytra on the chest
                        break; // if we've already found it we break from the loop
                    } else if (!state.isAir()) {
                        break; // if it's not air it breaks the loop - there is a block in the way
                    }

                    playerpos = playerpos.up(1); // move the block position one up, so that we are checking the next block above
                }

                for (int i = 0; i < 12; i++) {
                    BlockState state = playerworld.getBlockState(playerpos);

                    if (state.isOf(UltraElytraPad)) {
                        ItemStack Elytra = CreateElytra();

                        Player.equipStack(EquipmentSlot.CHEST, Elytra);
                        break;
                    }
                    // this time we do not check for air. The ultra elytra pad works through blocks!

                    playerpos = playerpos.up(1);
                }
            }
        }
    }
    static ItemStack CreateElytra() {
        ItemStack Elytra = new ItemStack(Items.ELYTRA); // create a stack of 1 elytra
        Elytra.addEnchantment(Enchantments.BINDING_CURSE, 1); // give it curse of binding
        Elytra.addEnchantment(Enchantments.UNBREAKING, 1000);
        Elytra.getOrCreateNbt().putBoolean("TemporaryElytra", true); // marker to show it's our elytra
        Elytra.addHideFlag(ItemStack.TooltipSection.ENCHANTMENTS); // hide enchantment tooltips
        return Elytra;
    }
}

