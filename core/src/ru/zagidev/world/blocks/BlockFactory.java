package ru.zagidev.world.blocks;

import ru.zagidev.world.Cell;

public class BlockFactory {
    public static Placeable createPlaceable(String name, Cell cell) {
        switch (name) {
            case "brick": return new BrickWall(cell);
            case "wood": return new WoodWall(cell);
            default:
                return null;
        }

    }
}
