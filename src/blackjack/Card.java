/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author ethan
 */
public class Card {
    boolean[][] cards = new boolean[4][13];
    Image card;
    int rank;
    int suite;
    
    public Card() throws FileNotFoundException
    {
       Random rand = new Random();
       suite = rand.nextInt(4) + 1;
       rank = rand.nextInt(13) + 1;
       
       if (suite == 1)
       {
           if (rank == 1)
           {
               card = new Image(new FileInputStream("src/cards/Clubs/A.png"));
           }
           else if (rank == 11)
           {
               card = new Image(new FileInputStream("src/cards/Clubs/J.png"));
           }
           else if (rank == 12)
           {
               card = new Image(new FileInputStream("src/cards/Clubs/Q.png"));
           }
           else if (rank == 13)
           {
               card = new Image(new FileInputStream("src/cards/Clubs/K.png"));
           }
           else
           {
               card = new Image(new FileInputStream("src/cards/Clubs/" + rank + ".png"));
           }
       }
       else if (suite == 2)
       {
           if (rank == 1)
           {
               card = new Image(new FileInputStream("src/cards/Diamonds/A.png"));
           }
           else if (rank == 11)
           {
               card = new Image(new FileInputStream("src/cards/Diamonds/J.png"));
           }
           else if (rank == 12)
           {
               card = new Image(new FileInputStream("src/cards/Diamonds/Q.png"));
           }
           else if (rank == 13)
           {
               card = new Image(new FileInputStream("src/cards/Diamonds/K.png"));
           }
           else
           {
               card = new Image(new FileInputStream("src/cards/Diamonds/" + rank  + ".png"));
           }
       }
       else if (suite == 3)
       {
           if (rank == 1)
           {
               card = new Image(new FileInputStream("src/cards/Hearts/A.png"));
           }
           else if (rank == 11)
           {
               card = new Image(new FileInputStream("src/cards/Hearts/J.png"));
           }
           else if (rank == 12)
           {
               card = new Image(new FileInputStream("src/cards/Hearts/Q.png"));
           }
           else if (rank == 13)
           {
               card = new Image(new FileInputStream("src/cards/Hearts/K.png"));
           }
           else
           {
               card = new Image(new FileInputStream("src/cards/Hearts/" + rank  + ".png"));
           }
       }
       else if (suite == 4)
       {
           if (rank == 1)
           {
               card = new Image(new FileInputStream("src/cards/Spades/A.png"));
           }
           else if (rank == 11)
           {
               card = new Image(new FileInputStream("src/cards/Spades/J.png"));
           }
           else if (rank == 12)
           {
               card = new Image(new FileInputStream("src/cards/Spades/Q.png"));
           }
           else if (rank == 13)
           {
               card = new Image(new FileInputStream("src/cards/Spades/K.png"));
           }
           else
           {
               card = new Image(new FileInputStream("src/cards/Spades/" + rank + ".png"));
           }
       }
       
    }
    
    public Image getCard()
    {
        return card;
    }
    
    public int getRank()
    {
        return rank;
    }
    
}
