package ru.zagidev.world;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

import ru.zagidev.sprites.characters.AbstractCharacter;

public class Team {
    private ArrayList<AbstractCharacter> members;

    public Color color;

    public Team() {
        this.members = new ArrayList<>();
        float r = (float)Math.random();
        float g = (float)Math.random();
        float b = (float)Math.random();
        color=new Color(r,g,b,1);
    }

    public Team(Color c) {
        this.members = new ArrayList<>();
        color=c;
    }

    public ArrayList<AbstractCharacter> getMembers() {
        return members;
    }

    public void addMembers(AbstractCharacter member) {
        members.add(member);
    }

    public void removerMember(AbstractCharacter member) {
        members.remove(member);
    }
}
