package Pokemons;

import entity.Entity;
import game2d.GamePanel;



public class Charizard extends Entity {
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        // Perform additional actions specific to Charizard when taking damage, if needed
    }

    public Charizard(GamePanel gp) {
        super(gp);


        name = "Charizard";
        pHP = 150;
        pLevel = 1;
        image = setUp("pokemon","Charizard");
        collision = true;
        description = (("name: " + name) + ("\nHP: " + pHP) + ("\nLevel: " +pLevel));


    }
}


