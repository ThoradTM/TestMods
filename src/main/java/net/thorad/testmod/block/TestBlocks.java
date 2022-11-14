package net.thorad.testmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thorad.testmod.TestMod;
import net.thorad.testmod.block.custom.bouncyDirtBlock;
import net.thorad.testmod.block.custom.naquadahOreBlock;
import net.thorad.testmod.item.ModCreativeModeTab;
import net.thorad.testmod.item.TestItems;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class TestBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> NAQUADAH_BLOCK = registerBlock("naquadah_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops().explosionResistance(10)),
            ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> JUMP_BLOCK = registerBlock("jump_block", () ->
                    new bouncyDirtBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops().explosionResistance(10)),
            ModCreativeModeTab.TEST_TAB);

    public static final RegistryObject<Block> NAQUAQDAH_ORE_BLOCK = registerBlock("naquadah_ore_block", () ->
                new naquadahOreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()
                        .explosionResistance(10).lightLevel(state -> state.getValue(naquadahOreBlock.LIT) ? 15 : 0)),
        ModCreativeModeTab.TEST_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        return TestItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
