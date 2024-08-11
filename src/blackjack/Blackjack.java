package blackjack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class Blackjack extends Application implements MouseMotionListener {
  public static void main(String[] args) {
    launch(args);
  }
  
  Random rand = new Random();
  
  Button bet;
  
  Button confirmBet;
  
  Button hit;
  
  Button stay;
  
  Button next;
  
  Label money;
  
  ImageView dealer1;
  
  ImageView dealer2;
  
  ImageView player1;
  
  ImageView player2;
  
  ImageView card;
  
  Scene table;
  
  BorderPane pane = new BorderPane();
  
  VBox controls = new VBox();
  
  Timeline timeline = new Timeline();
  
  Stage stage;
  
  boolean hasBet = false;
  
  boolean win = false;
  
  boolean roundEnd = false;
  
  boolean playerBlackjack = false;
  
  boolean dealerBlackjack = false;
  
  int cardCount;
  
  int aces;
  
  int dealerAces;
  
  int total;
  
  int dealerTotal;
  
  double balance = 10000.0D;
  
  double numBet;
  
  double dealerX;
  
  double dealerY;
  
  double cardX;
  
  double cardY;
  
  double translateX;
  
  double translateY;
  
  Card[] deck = new Card[52];
  
  public void start(Stage primaryStage) throws FileNotFoundException {
    this.stage = primaryStage;
    this.hit = new Button();
    this.hit.setText("Hit");
    this.hit.setPrefSize(100.0D, 50.0D);
    this.hit.setOnAction(e -> {
          try {
            hit();
          } catch (FileNotFoundException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
        });
    this.stay = new Button();
    this.stay.setText("Stay");
    this.stay.setPrefSize(100.0D, 50.0D);
    this.stay.setOnAction(e -> {
          try {
            stand();
          } catch (FileNotFoundException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
        });
    this.bet = new Button("Bet");
    this.bet.setPrefSize(100.0D, 50.0D);
    this.bet.setOnAction(e -> {
          try {
            bet();
          } catch (FileNotFoundException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
        });
    next = new Button("Next Hand");
    this.next.setPrefSize(100.0D, 50.0D);
    this.next.setOnAction(e -> {
          try {
            next();
          } catch (FileNotFoundException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, (String)null, ex);
          } 
        });
    this.money = new Label();
    this.money.setText("Money: " + this.balance);
    this.table = new Scene(this.pane, 1000.0D, 600.0D);
    this.controls.setLayoutX(100.0D);
    this.controls.setLayoutY(100.0D);
    this.controls.setPadding(new Insets(50.0D, 50.0D, 100.0D, 100.0D));
    this.controls.setSpacing(10.0D);
    this.controls.getChildren().addAll(new Node[] { this.money, this.bet, this.hit, this.stay });
    startGame();
    this.stage.setTitle("Blackjack");
    this.stage.setScene(this.table);
    this.stage.show();
    this.timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000.0D), new javafx.animation.KeyValue[0]));
    this.timeline.setCycleCount(-1);
    this.timeline.setDelay(Duration.seconds(2.0D));
  }
  
  public void startGame() throws FileNotFoundException {
    for (int x = 0; x < 52; x++)
      this.deck[x] = new Card(); 
    this.win = false;
    this.roundEnd = false;
    this.cardCount = 0;
    this.aces = 0;
    this.dealerAces = 0;
    this.total = 0;
    this.dealerTotal = 0;
    this.dealerX = 400.0D;
    this.dealerY = 50.0D;
    this.cardX = 350.0D;
    this.cardY = 400.0D;
    this.dealer1 = new ImageView(new Image(new FileInputStream("src/cards/Pomegranate.png")));
    this.pane.setRight(this.controls);
    this.money.setText("Money: " + this.balance);
    this.stage.setTitle("Blackjack");
    this.stage.setScene(this.table);
    this.stage.show();
  }
  
  public void bet() throws FileNotFoundException {
    if (!this.roundEnd && !this.hasBet) {
      String q = JOptionPane.showInputDialog("Bet Amount:");
      if (Integer.parseInt(q) <= this.balance) {
        this.numBet = Integer.parseInt(q);
        this.balance -= this.numBet;
        this.money.setText("Money: " + this.balance);
        this.hasBet = true;
        this.dealer1.setFitWidth(100.0D);
        this.dealer1.setX(this.dealerX);
        this.dealer1.setY(this.dealerY);
        this.dealer1.setPreserveRatio(true);
        this.dealer1.setVisible(true);
        addDealer();
        this.dealer2 = new ImageView(this.deck[this.cardCount].getCard());
        this.dealer2.setFitWidth(100.0D);
        this.dealer2.setX(this.dealerX);
        this.dealer2.setY(this.dealerY);
        this.dealer2.setPreserveRatio(true);
        this.dealer2.setVisible(true);
        addDealer();
        this.player1 = new ImageView(this.deck[this.cardCount].getCard());
        this.player1.setFitWidth(100.0D);
        this.player1.setX(this.cardX);
        this.player1.setY(this.cardY);
        this.player1.setPreserveRatio(true);
        this.player1.setVisible(true);
        addPlayer();
        this.player2 = new ImageView(this.deck[this.cardCount].getCard());
        this.player2.setFitWidth(100.0D);
        this.player2.setX(this.cardX);
        this.player2.setY(this.cardY);
        this.player2.setPreserveRatio(true);
        this.player2.setVisible(true);
        addPlayer();
        if (this.total == 21) {
          System.out.println("Player: Blackjack");
          this.playerBlackjack = true;
          this.dealer1.setImage(this.deck[0].getCard());
          this.aces = 0;
          this.roundEnd = true;
          roundEnd();
        } 
        if (this.dealerTotal == 21) {
          this.dealer1.setImage(this.deck[0].getCard());
          System.out.println("Dealer: Blackjack");
          this.dealerAces = 0;
          this.roundEnd = true;
          roundEnd();
        } 
        this.pane.getChildren().addAll(new Node[] { this.dealer1, this.dealer2, this.player1, this.player2 });
      } else {
        System.out.println("You don't have that much to bet");
      } 
    } 
  }
  
  public void hit() throws FileNotFoundException {
    if (this.hasBet && !this.roundEnd) {
      if (this.hit.getText().equals("Hit")) {
        if (!this.roundEnd) {
          this.card = new ImageView(this.deck[this.cardCount].getCard());
          this.card.setFitWidth(100.0D);
          this.card.setX(this.cardX);
          this.card.setY(this.cardY);
          this.card.setPreserveRatio(true);
          this.card.setVisible(true);
          this.pane.getChildren().add(this.card);
          addPlayer();
        } 
      } else {
        this.hit.setText("Hit");
      } 
    } else {
      System.out.println("Please make a bet first");
    } 
  }
  
  public void stand() throws FileNotFoundException {
    if (this.hasBet && !this.roundEnd) {
      if (this.stay.getText().equals("Stay")) {
        dealerHit();
      } else {
        this.stay.setText("Stay");
      } 
    } else {
      System.out.println("Please make a bet first");
    } 
  }
  
  public void next() throws FileNotFoundException {
    if (this.next.getText().equals("Next Hand")) {
      this.pane.getChildren().clear();
      this.pane = new BorderPane();
      this.table = new Scene(this.pane, 1000.0D, 600.0D);
      startGame();
      this.hasBet = false;
      this.controls.getChildren().remove(this.next);
    } 
  }
  
  public void dealerHit() throws FileNotFoundException {
    this.dealer1.setImage(this.deck[0].getCard());
    while (this.dealerTotal <= 17) {
      ImageView q = new ImageView(this.deck[this.cardCount].getCard());
      q.setFitWidth(100.0D);
      q.setX(this.dealerX);
      q.setY(this.dealerY);
      q.setPreserveRatio(true);
      q.setVisible(true);
      this.pane.getChildren().add(q);
      addDealer();
    } 
    if (!this.roundEnd)
      roundEnd(); 
  }
  
  public void addPlayer() throws FileNotFoundException {
    if (this.deck[this.cardCount].getRank() == 1) {
      this.aces++;
      this.total += 11;
    } else {
      this.total += this.deck[this.cardCount].getRank();
    } 
    while (this.aces > 0 && this.total > 21) {
      this.total -= 10;
      this.aces--;
    } 
    if (this.total > 21) {
      System.out.println("Player busts");
      this.roundEnd = true;
      roundEnd();
    } 
    this.cardX += 75.0D;
    this.cardCount++;
  }
  
  public void addDealer() throws FileNotFoundException {
    if (this.deck[this.cardCount].getRank() == 1) {
      this.dealerTotal += 11;
      this.dealerAces++;
    } else {
      this.dealerTotal += this.deck[this.cardCount].getRank();
    } 
    while (this.dealerAces > 0 && this.dealerTotal > 21) {
      this.dealerTotal -= 10;
      this.dealerAces--;
    } 
    if (this.dealerTotal > 21) {
      System.out.println("Dealer busts");
      this.win = true;
      this.roundEnd = true;
      roundEnd();
    } 
    this.dealerX += 75.0D;
    this.cardCount++;
  }
  
  public void roundEnd() throws FileNotFoundException {
    if (this.total > this.dealerTotal && this.total <= 21)
      this.win = true; 
    if (this.playerBlackjack && !this.dealerBlackjack) {
      System.out.println("You gained " + (this.numBet * 1.5D) + " money");
      this.numBet *= 2.5D;
    } else if (this.win) {
      System.out.println("You gained " + this.numBet + " money");
      this.numBet *= 2.0D;
    } else if (this.dealerTotal == this.total) {
      System.out.println("Push: Your bet is returned");
    } else {
      System.out.println("You lost " + this.numBet + " money");
      this.numBet = 0.0D;
    }
    this.controls.getChildren().add(this.next);
    this.balance += this.numBet;
    this.numBet = 0.0D;
    this.money.setText("Money: " + this.balance);
    this.roundEnd = true;
    this.timeline = new Timeline(new KeyFrame[] { new KeyFrame(Duration.seconds(1.0D), e -> {
              try {
                this.pane.getChildren().clear();
                this.pane = new BorderPane();
                this.table = new Scene(this.pane, 1000.0D, 600.0D);
                startGame();
              } catch (FileNotFoundException fileNotFoundException) {}
            
            })});
            }

    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }
