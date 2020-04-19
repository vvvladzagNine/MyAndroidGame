package ru.zagidev.save;



import java.util.ArrayList;
import java.util.List;


public class SavedGameLevel {
    public int money;
    public int xSize;
    public int ySize;
    public List<JsonBlock> blocks;
    public List<JsonChar> chars;
    public List<GreenZone> greens;

    public SavedGameLevel() {
    }
}
