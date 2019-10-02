package com.mycompany.projetbowlingmultiplayer;

import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.ArrayList;
import java.util.HashMap;


public class MultiPlayerBowling implements MultiPlayerGame {

    private boolean jeuencours;

    public HashMap<String, SinglePlayerGame> jeu;
    public SinglePlayerGame jeuactuel;

    private ArrayList<String> listejoueur;
    private String joueuractuel;

    public MultiPlayerBowling() {
        HashMap jeu = new HashMap<>();
    }

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName == null || playerName.length == 0) {
            throw new Exception("Il faut au minimum un joueur valide");
        }

        //On ajoute chaque joueur dans le tableau
        for (String p : playerName) {
            jeu.put(p, new SinglePlayerGame());
            listejoueur.add(p);
        }

        //On lance une partie || -> true
        jeuencours = true;

        //Joueur à itérer + changement de joueur
        return toString();
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (!jeuencours) {
            throw new Exception("La partie est terminée.");
        }

        jeuactuel.lancer(nombreDeQuillesAbattues);

        if (jeuactuel.isFinished() || jeuactuel.hasCompletedFrame()) {
            jeuencours = joueurSuivant();

        }
        return toString();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        SinglePlayerGame g = (SinglePlayerGame) jeu.get(playerName);
        return g.score();
    }

    public boolean joueurSuivant() {
        if (!listejoueur.iterator().hasNext()) {
            if (jeuactuel.isFinished()) {
                return false;
            } else {
                joueuractuel = listejoueur.get(0);
            }
        }
        joueuractuel = listejoueur.iterator().next();

        jeuactuel = jeu.get(joueuractuel);
        return true;

    }

    @Override
    public String toString() {
        //Si la partie est terminée..
        if (!jeuencours) {
            return "La game est terminée..";
            //sinon à chaque tour.
        } else {
            return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d", joueuractuel, jeuactuel.getFrameNumber(), jeuactuel.getNextBallNumber());
        }
    }

}
