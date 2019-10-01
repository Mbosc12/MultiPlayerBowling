package com.mycompany.projetbowlingmultiplayer;

import bowling.MultiPlayerGame;
 import bowling.SinglePlayerGame;
import java.util.HashMap;

/**
 *
 * @author pedago
 */
public class MultiPlayerBowling implements MultiPlayerGame {

    private boolean GameEnCours;
    
    public HashMap game = new HashMap<>();
    public SinglePlayerGame GameActuelle;

    
    public MultiPlayerBowling() {
        HashMap game = new HashMap<>();
    }
    
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if(playerName == null || playerName.length == 0 ) {
            throw new Exception("Il faut au minimum un joueur valide");
        }
        
        //On ajoute chaque joueur dans le tableau
        for( String p : playerName) {
            game.put(p, playerName);
        }
        
        //On lance une partie || -> true
        GameEnCours = true;
        
        //Joueur à itérer + changement de joueur
        

        
        return toString();
    }
    
    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (!GameEnCours){
            throw new Exception("La partie est terminée.");
        }
        
        GameActuelle.lancer(nombreDeQuillesAbattues);
        
        if(GameActuelle.isFinished() || GameActuelle.hasCompletedFrame()) {
            //Il faut passer au joueur suivant
            
        }
        return toString();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        SinglePlayerGame g = (SinglePlayerGame) game.get(playerName);
        return g.score();
    }
    
    public boolean JoueurSuivant() {
        //il faut passer tous les joueurs
        if(GameActuelle.isFinished()) {
            return false;
        } else {
            //On reinitialise l'iterateur
        }
        
        // iterateur = Joueur en cours 
        // GameEnCours = game.get(""//joueur itéré);
	return true;
        
    }
    
    @Override
    public String toString() {
        //Si la partie est terminée..
        if (!GameEnCours) {
            return "La game est terminée..";
        //sinon à chaque tour.
	} else {
            int nbtour = GameActuelle.getFrameNumber();
            int nbballe = GameActuelle.getNextBallNumber();
            return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d", "Joueur"//Joueur iterer
                    , nbtour, nbballe);
	}
    }
    
}
