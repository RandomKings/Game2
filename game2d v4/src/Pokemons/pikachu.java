package Pokemons;

import entity.Entity;
import game2d.GamePanel;

public class pikachu extends Entity {

    public pikachu(GamePanel gp) {
        super(gp);


        name = "pikachu";
        image = setUp("pokemon", "pikachu");
        collision = true;
        pHP = 150;
        pLevel = 1;
        description = (("name: " + name) + ("\n HP: " + pHP) + ("\n Level: " +pLevel));
    }
}

