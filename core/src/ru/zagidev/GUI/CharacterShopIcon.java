package ru.zagidev.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.sprites.characters.CharacterClass;
import ru.zagidev.world.Team;

public class CharacterShopIcon {
    public Sprite sp;
    public Sprite press;
    public Sprite release;
    public CharacterClass charClass;
    public Team team;
    public int price;

    public CharacterShopIcon(Texture texture1,Texture texture2,CharacterClass cl,Team t,int price) {
        charClass=cl;
        team=t;
        release = new Sprite(texture1,500,600);
        sp=release;
        press = new Sprite(texture2,500,600);
        this.price=price;
    }

    public void press(){
        Shop.state=GuiState.PLACING;
        Shop.currentTeam=team;
        Shop.currentClass=charClass;
        Shop.currentPrice=price;
    }
}
