package ru.zagidev.levels;

import ru.zagidev.world.BlockType;

public class SavedWorldObject{
    public BlockType type;
    public int x;
    public int y;
    public boolean placeable;

    public SavedWorldObject(BlockType type, int x, int y, boolean placeable) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.placeable = placeable;
    }
}
