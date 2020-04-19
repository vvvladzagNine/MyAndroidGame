package ru.zagidev.save;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.FileHandler;

import ru.zagidev.levels.GameLevel;

public class Loader {
    ObjectMapper mapper;

    public Loader() {
        this.mapper = new ObjectMapper();

    }

    public SavedGameLevel load(){
        try {
            String locRoot = Gdx.files.getLocalStoragePath();
            FileHandle path = Gdx.files.internal("data/levels/levels628.json");
            InputStream is = path.read();
            SavedGameLevel s = mapper.readValue(is, SavedGameLevel.class);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (GdxRuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
