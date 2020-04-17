package ru.zagidev.world.blocks;

import ru.zagidev.world.BlockType;
import ru.zagidev.world.Cell;

public class BlockFactory {
    public static Placeable createPlaceable(BlockType name, Cell cell) {
        switch (name) {
            case BRICK: return new BrickWall(cell);
            case WOOD: return new WoodWall(cell);
            case WATER: return new Water(cell);
            default:
                return null;
        }

    }
}
