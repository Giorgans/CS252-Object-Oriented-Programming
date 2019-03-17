
package controller;
import java.net.URL;
import java.util.Random;
import javax.swing.JOptionPane;
import model.Player.Player;
import model.Board.Board;
import model.Finding.Statue;
import model.Pawn.Archeologist;
import model.Pawn.Pawn;
import model.Pawn.Theseus;
import model.PlaceType.PlaceType;
import static model.PlaceType.PlaceType.FAISTOS;
import static model.PlaceType.PlaceType.KNOSOS;
import static model.PlaceType.PlaceType.MALIA;
import static model.PlaceType.PlaceType.ZAKROS;
import model.Position.FindingPosition;
import model.cards.AriadneCard;
import model.cards.Card;
import model.cards.MinotaurCard;
import model.cards.NumberCard;

public class Controller {
    public Board board;
    public Player P1,P2;
    private int turn;
    private int checkpoints;
    
    /** Constructor: Constructs a new Controller and sets the game as eligible to start.
    * Postcondition: constructs a new Controller,with 2 new players with their pawns 
    * and a new board with all its items.
    * So,is responsible for creating a new game and initializing it.
    */
    public Controller(){
        this.P1=new Player("Player 1");
        this.P2=new Player("Player 2");
        this.board=new Board();
        Random rn = new Random();
        turn = rn.nextInt(2) + 1; 
        setCardstoHand();
    }
   
    /**Accessor(selector):Returns the turn number
     * Postcondition:The turn number has been returned
     * @return turn
     */
    public int seeTurn(){
        return turn;
    }
    
    public int SeeNextTurn(int turn){
        if(turn==1)
            return turn++;
        else 
            return turn--;
    }

    
    
    /**Transformer(Mutative) :Set the next turn
    *  Postcondition:The next turn has been set
    * @param turn
    */
    public void SetNextTurn(int turn){
        if(seeTurn()==1)
            this.turn++;
        else 
            this.turn--;
    }
    
    /**Transformer(Mutative) :Set a pawn on a position
    *  Postcondition:The pawn has been set
     * @param placetype
    */
    public void PlacePawn(Card card) throws Exception{
                System.out.println("Pawn has been placed");

        if(seeTurn()==1){
            int size=P1.pawns.size()-1;
            Pawn topPawn=P1.pawns.remove(size);
                if (card.getCardtype()==KNOSOS){
                    board.PosK[0].setPawn(topPawn, 1);
                    P1.setPoints(board.PosK[0].getPoints());
                }
                else if(card.getCardtype()==FAISTOS){
                    board.PosF[0].setPawn(topPawn, 1);
                    P1.setPoints(board.PosF[0].getPoints());
                }
                else if(card.getCardtype()==MALIA){
                    board.PosM[0].setPawn(topPawn, 1);
                    P1.setPoints(board.PosM[0].getPoints());
                }
                else if(card.getCardtype()==ZAKROS){
                    board.PosZ[0].setPawn(topPawn, 1);
                    P1.setPoints(board.PosZ[0].getPoints());
                }
                else
                    throw new Exception("λαθος τοποθεσία");
                
            
        }
        else if(seeTurn()==2){
            int size=P2.pawns.size()-1;
            Pawn topPawn=P2.pawns.remove(size);
                if (card.getCardtype()==KNOSOS){
                    board.PosK[0].setPawn(topPawn, 2);
                    P2.setPoints(board.PosK[0].getPoints());
                }
                else if(card.getCardtype()==FAISTOS){
                    board.PosF[0].setPawn(topPawn, 2);
                    P2.setPoints(board.PosF[0].getPoints());
                }
                else if(card.getCardtype()==MALIA){
                    board.PosM[0].setPawn(topPawn, 2);
                    P2.setPoints(board.PosM[0].getPoints());
                }
                else if(card.getCardtype()==ZAKROS){
                    board.PosZ[0].setPawn(topPawn, 2);
                    P2.setPoints(board.PosZ[0].getPoints());
                }
                else
                    throw new Exception("λαθος τοποθεσία");
                
            }
        
            
    }
    
    /**Transformer(Mutative) :Move a pawn from a position to another
    *  Postcondition:The pawn has been moved
     * @param step
     * @param placetype
     * @throws java.lang.Exception
    */
    public void movePawn(int step,PlaceType placetype,int player) throws Exception{
                int tempstep=-100;
                Pawn tempPawn=null;
                
             if(step==-2){
                 int i=0;
                 while(i<9){
                    if(placetype==KNOSOS){
                        if (board.PosK[i].getPawn(SeeNextTurn(player))!=null && (board.PosK[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                                 JOptionPane.showMessageDialog(null,
                                 "Ο Θησέας απέκρουσε την επίθεση του Μινώταυρου...",
                                 "Ωπ...",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                        if (board.PosK[i].getPawn(SeeNextTurn(player))!=null && !(board.PosK[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                           tempstep=i;
                           if(step==-2 && i==1) step=-1;
                           if(step==-2 && i==0) step=0;
                           i=0;
                        }
                        if(i==step+tempstep && tempstep<6){
                              tempPawn=board.PosK[tempstep].getPawn(SeeNextTurn(player));
                              board.PosK[tempstep].removePawn(SeeNextTurn(player)) ;
                               
                               board.PosK[i].setPawn(tempPawn, SeeNextTurn(player));
                               if(board.PosK[i] instanceof FindingPosition){
                                   while(collectNewFinding(placetype,SeeNextTurn(player))){}
                               }
                              if(seeTurn()==1) 
                                   P2.setPoints(board.PosK[i].getPoints());
                              if(seeTurn()==2) 
                                   P1.setPoints(board.PosK[i].getPoints());
                        }
                    }
                    if(placetype==FAISTOS){
                        
                        if (board.PosF[i].getPawn(SeeNextTurn(player))!=null && (board.PosF[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                                 JOptionPane.showMessageDialog(null,
                                 "Ο Θησέας απέκρουσε την επίθεση του Μινώταυρου...",
                                 "Ωπ...",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                        if (board.PosF[i].getPawn(SeeNextTurn(player))!=null && !(board.PosF[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                           tempstep=i;
                           if(step==-2 && i==1) step=-1;
                           if(step==-2 && i==0) step=0;
                           i=0;
                        }
                           if(i==step+tempstep && tempstep<6){
                                   tempPawn=board.PosF[tempstep].getPawn(SeeNextTurn(player));
                                   board.PosF[tempstep].removePawn(SeeNextTurn(player)) ;
                               
                               board.PosF[i].setPawn(tempPawn, SeeNextTurn(player));
                              if(board.PosF[i] instanceof FindingPosition){
                                  while(!collectNewFinding(placetype,SeeNextTurn(player))){}
                              }
                               if(seeTurn()==1) 
                               P2.setPoints(board.PosF[i].getPoints());
                              else
                               P1.setPoints(board.PosF[i].getPoints());
                           }
                    }
                    if(placetype==MALIA){
                        if (board.PosM[i].getPawn(SeeNextTurn(player))!=null && (board.PosM[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                                 JOptionPane.showMessageDialog(null,
                                 "Ο Θησέας απέκρουσε την επίθεση του Μινώταυρου...",
                                 "Ωπ...",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                        if (board.PosM[i].getPawn(SeeNextTurn(player))!=null && !(board.PosM[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                           tempstep=i;
                           if(step==-2 && i==1) step=-1;
                           if(step==-2 && i==0) step=0;
                           i=0;
                        }
                           if(i==step+tempstep && tempstep<6){
                                 tempPawn=board.PosM[tempstep].getPawn(SeeNextTurn(player));
                                 board.PosM[tempstep].removePawn(SeeNextTurn(player)) ;
                               
                               board.PosM[i].setPawn(tempPawn, SeeNextTurn(player));
                              if(board.PosM[i] instanceof FindingPosition){
                                  while(!collectNewFinding(placetype,SeeNextTurn(player))){}
                              }
                              if(seeTurn()==1) 
                               P2.setPoints(board.PosM[i].getPoints());
                              else
                               P1.setPoints(board.PosM[i].getPoints());
                           }
                    }
                    if(placetype==ZAKROS){
                        if (board.PosZ[i].getPawn(SeeNextTurn(player))!=null && (board.PosZ[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                                 JOptionPane.showMessageDialog(null,
                                 "Ο Θησέας απέκρουσε την επίθεση του Μινώταυρου...",
                                 "Ωπ...",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                        if (board.PosZ[i].getPawn(SeeNextTurn(player))!=null && !(board.PosZ[i].getPawn(SeeNextTurn(player)) instanceof Theseus)) {
                           tempstep=i;
                           if(step==-2 && i==1) step=-1;
                           if(step==-2 && i==0) step=0;
                           i=0;
                        }
                           if(i==step+tempstep && tempstep<6){
                               tempPawn=board.PosZ[tempstep].getPawn(SeeNextTurn(player));
                               board.PosZ[tempstep].removePawn(SeeNextTurn(player)) ;
                               
                               board.PosZ[i].setPawn(tempPawn, SeeNextTurn(player));
                               if(board.PosZ[i] instanceof FindingPosition){
                                   while(!collectNewFinding(placetype,SeeNextTurn(player))){}
                               }
                               if(seeTurn()==1) 
                               P2.setPoints(board.PosZ[i].getPoints());
                              else
                               P1.setPoints(board.PosZ[i].getPoints());
                           }
                    }
                    i++;
                 }
             }
             else{
                int i=0;
                while(i<9){
                    if(placetype==KNOSOS){
                        if (board.PosK[i].getPawn(player)!=null){
                           tempstep=i;
                           if(step==2 && i==7) step=1;
                           if(step==2 && i==8) step=0;
                        }
                        if(i==step+tempstep){
                             tempPawn=board.PosK[tempstep].getPawn(player);
                             board.PosK[tempstep].removePawn(player) ;
                               
                               board.PosK[i].setPawn(tempPawn, player);
                              if(board.PosK[i] instanceof FindingPosition){
                                  while(!collectNewFinding(placetype,player)){}
                              }
                               if(seeTurn()==1) 
                                   P1.setPoints(board.PosK[i].getPoints());
                              if(seeTurn()==2) 
                                   P2.setPoints(board.PosK[i].getPoints());
                               if(i==6 || (i==7 && tempstep!=6)) checkpoints++;
                              if(i==8){
                                 JOptionPane.showMessageDialog(null,
                                 board.PosK[i].toString(),
                                 "Συγχαρητήρια!",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                        }
                    }
                    else if(placetype==FAISTOS){
                        if (board.PosF[i].getPawn(player)!=null){
                           tempstep=i;
                        }
                           if(i==step+tempstep){
                                   tempPawn=board.PosF[tempstep].getPawn(player);
                                   board.PosF[tempstep].removePawn(player) ;
                               
                               board.PosF[i].setPawn(tempPawn, player);
                              if(board.PosF[i] instanceof FindingPosition){
                                  while(!collectNewFinding(placetype,player)){}
                               }
                               if(seeTurn()==1) 
                               P1.setPoints(board.PosF[i].getPoints());
                              else
                               P2.setPoints(board.PosF[i].getPoints());
                               if(i==6 || (i==7 && tempstep!=6)) checkpoints++;
                              if(i==8){
                                 JOptionPane.showMessageDialog(null,
                                 board.PosF[i].toString(),
                                 "Συγχαρητήρια!",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                           }
                    }
                    else if(placetype==MALIA){
                        if (board.PosM[i].getPawn(player)!=null){
                           tempstep=i;
                        }
                           if(i==step+tempstep){
                                 tempPawn=board.PosM[tempstep].getPawn(player);
                                 board.PosM[tempstep].removePawn(player) ;
                               
                               board.PosM[i].setPawn(tempPawn, player);
                              if(board.PosM[i] instanceof FindingPosition){
                                  while(!collectNewFinding(placetype,player)){}
                              }
                              if(seeTurn()==1) 
                               P1.setPoints(board.PosM[i].getPoints());
                              else
                               P2.setPoints(board.PosM[i].getPoints());
                               if(i==6 || (i==7 && tempstep!=6)) checkpoints++;
                              if(i==8){
                                 JOptionPane.showMessageDialog(null,
                                 board.PosM[i].toString(),
                                 "Συγχαρητήρια!",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                           }
                    }
                    else if(placetype==ZAKROS){
                        if (board.PosZ[i].getPawn(player)!=null){
                           tempstep=i;
                        }
                           if(i==step+tempstep){
                               tempPawn=board.PosZ[tempstep].getPawn(player);
                               board.PosZ[tempstep].removePawn(player) ;
                               
                               board.PosZ[i].setPawn(tempPawn, player);
                               if(board.PosZ[i] instanceof FindingPosition){
                                   while(!collectNewFinding(placetype,player)){}
                               }
                               if(seeTurn()==1) 
                               P1.setPoints(board.PosZ[i].getPoints());
                              else
                               P2.setPoints(board.PosZ[i].getPoints());
                               if(i==6 || (i==7 && tempstep!=6)) checkpoints++;
                              if(i==8){
                                 JOptionPane.showMessageDialog(null,
                                 board.PosZ[i].toString(),
                                 "Συγχαρητήρια!",
                                  JOptionPane.PLAIN_MESSAGE);
                              }
                           }
                    }
                    else
                        throw new Exception("λαθος τοποθεσια");
                    
                    i++;
                }
            }
    }

    /**Transformer(Mutative) :Set 8 cards from the deck into players hands
    *  Postcondition:The cards has been set
    */
    public void setCardstoHand(){
        if (!P1.HasCards() && !P2.HasCards()){
            for (int i=0;i<8;i++){
                P1.DrawCard(board.pickCard());
                P2.DrawCard(board.pickCard());
            }
        }
    }

    /**Transformer(Mutative) :Play a card from players hand
    *  Postcondition:The card has been played
     * @param card
    */
    public boolean playCard(Card card,int j) throws Exception{
                            Object[] Mainoptions = {"Θέλω να παίξω την κάρτα.",
                           "Θέλω να απορίψω την κάρτα."};
                          int k = JOptionPane.showOptionDialog(null,
                              "Θέλεις να απορίψεις αυτή τη κάρτα;","Θέλεις να παίξεις ή να απορίψεις την κάρτα;",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     Mainoptions,   Mainoptions[0]); 
        if(k == JOptionPane.YES_OPTION){
           if (card instanceof NumberCard){
                  if(seeTurn()==1){
                      if(P1.getLastNumberCard(card.getCardtype())!=null){
                        if (((NumberCard) card).getNumber()>=P1.getLastNumberCard(card.getCardtype()).getNumber()){
                           P1.setLastNumberCard((NumberCard) P1.playersHand.remove(j));
                           movePawn(1,card.getCardtype(),1);
                           return true;
                        }
                        else{
                            Object[] options = {"Ναι θέλω!",
                           "Όχι!! Τη θέλω."};
                          int n = JOptionPane.showOptionDialog(null,
                              "Θέλεις να απορίψεις αυτή τη κάρτα; Δεν είναι χρήσιμη..","Απόριψη κάρτας",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     options,   options[0]); 
                             if (n == JOptionPane.YES_OPTION){ P1.playersHand.remove(j);
                                return true;
                             }
                             else return false;
                        }
                      }
                      else{
                           P1.setLastNumberCard((NumberCard) card);
                           PlacePawn(P1.playersHand.remove(j));
                           return true;
                      }
                  }
                  if(seeTurn()==2){
                      if(P2.getLastNumberCard(card.getCardtype())!=null){
                        if (((NumberCard) card).getNumber()>=P2.getLastNumberCard(card.getCardtype()).getNumber()){
                         P2.setLastNumberCard((NumberCard) card);
                         P2.playersHand.remove(j);
                         movePawn(1,card.getCardtype(),2);
                         return true;
                        }
                        else{
                            Object[] options = {"Ναι θέλω!",
                           "Όχι!! Την θέλω."};
                          int n = JOptionPane.showOptionDialog(null,
                              "Θέλεις να απορίψεις αυτή τη κάρτα; Δεν είναι χρήσιμη..","Απόριψη κάρτας",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     options,   options[0]);
                             if (n == JOptionPane.YES_OPTION){ P2.playersHand.remove(j);
                                return true;
                             }
                             else return false;
                        }
                      }
                      else{
                           P2.setLastNumberCard((NumberCard) card);
                           PlacePawn(P2.playersHand.remove(j));
                           return true;
                      }
                  }
           }
           if(card.getName() == "Αριάδνη"){
              if(seeTurn()==1 &&  P1.getLastNumberCard(card.getCardtype())!=null){
                 movePawn(2,card.getCardtype(),1);
                   P1.playersHand.remove(j);
               System.out.println("Card "+ card.getCardtype()+ " ariadne has been played by player 1");
               return true;
              }
              else if(seeTurn()==2 && P2.getLastNumberCard(card.getCardtype())!=null){
                 movePawn(2,card.getCardtype(),2);
                   P2.playersHand.remove(j);
               System.out.println("Card "+ card.getCardtype()+ " ariadne has been played by player 2");
               return true;
              }
              else{
                            Object[] options = {"Ναι θέλω!",
                           "Όχι!! Την θέλω."};
                          int n = JOptionPane.showOptionDialog(null,
                              "Θέλεις να απορίψεις αυτή τη κάρτα; Δεν είναι χρήσιμη..","Απόριψη κάρτας",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     options,   options[0]); 
                             if (n == JOptionPane.YES_OPTION){ 
                                 if(seeTurn()==1)P1.playersHand.remove(j);
                                 if(seeTurn()==2)P2.playersHand.remove(j);
                                return true;
                             }
                             else return false;
             }
           }
           if (card.getName() == "Mινώταυρος"){
              if(seeTurn()==1 &&  P2.getLastNumberCard(card.getCardtype())!=null){
                 movePawn(-2,card.getCardtype(),2);
                   P1.playersHand.remove(j);
               System.out.println("Card "+ card.getCardtype()+ " minotaur has been played by player 1");
               return true;
              }
              else if(seeTurn()==2 && P1.getLastNumberCard(card.getCardtype())!=null){
                 movePawn(-2,card.getCardtype(),1);
                   P2.playersHand.remove(j);
               System.out.println("Card "+ card.getCardtype()+ " minotaur has been played by player 2");
               return true;
              }
              else{
                            Object[] options = {"Ναι θέλω!",
                           "Όχι!! Την θέλω."};
                          int n = JOptionPane.showOptionDialog(null,
                              "Θέλεις να απορίψεις αυτή τη κάρτα; Δεν είναι χρήσιμη..","Απόριψη κάρτας",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     options,   options[0]); 
                             if (n == JOptionPane.YES_OPTION){ 
                                 if(seeTurn()==1)P1.playersHand.remove(j);
                                 if(seeTurn()==2)P2.playersHand.remove(j);
                                return true;
                             }
                             else return false;
             }
           }
        }
        else{
              if(seeTurn()==1)P1.playersHand.remove(j);
              if(seeTurn()==2)P2.playersHand.remove(j);
              return true;
        }
           return false;

    }
     /**Accessor(selector):Returns the number of a specific card on the hand to play
     * Postcondition:The choose number for a card has been returned
     */

    /**
     * Accessor(selector):Returns the number of a specific card on the hand to play
 Postcondition:The choose number for a card has been returned
     * @param i
     */
    public Card ChooseCardtoPlay(int i) throws Exception {
        if(seeTurn()==1)
                return P1.playersHand.get(i);
         
        else
                return P2.playersHand.get(i);
        

    }
    
    
    /**Transformer(Mutative) :Draw a new card
    *  Postcondition:The card has been drawn
    * @param placetype
    */
    public void drawCard(){
       
        if(seeTurn()==1){
           if(!P1.isHandFull()){
            P1.DrawCard(board.deck.remove(board.deck.size()-1));
            System.out.println("Player 1 draw a card");
           }
        }
        else if(seeTurn()==2){
           if(!P2.isHandFull()){
            P2.DrawCard(board.deck.remove(board.deck.size()-1));
            System.out.println("Player 2 draw a card");

           }
        }
        System.out.println("Deck Size:" +(board.deck.size()-100));
    }
            
    
    
    
    /**Transformer(Mutative) :Add a Rare Finding in a player's finding list,
    * depending on the place type and if the item is taken or not
    *  Postcondition:The Rare Finding has been added
    * @param placetype
    */
    public void addRareItemtoPlayer(PlaceType placetype) throws Exception{
        if(seeTurn()==1)
            P1.setRareItem(placetype,board.getRareItem(placetype));
        else if(seeTurn()==2)
            P2.setRareItem(placetype,board.getRareItem(placetype));
        else
            throw new Exception("Λάθος γύρος");
        
            board.setRareItemTaken(placetype);
    }
    
     /**Accessor(selector):Returns the total player's points
     * Postcondition:The player's points has been returned
     */
    public int collectPoints(){
        return 0;
    }
    
    
    public void checkIfCheckpoint(int i){
        if(i>=7)
            this.checkpoints++;
    }
    
    
    /**Transformer(Mutative) :Set the total checkpoints of both players
    *  Postcondition:The next turn has been set
    */
    public void setCheckPoints(){
        this.checkpoints=P1.getCheckpoints()+P2.getCheckpoints();
    }
    
    
   /** Observer:Return true if game is finished, false otherwise.
    * Postcondition: return true if game is finished, false otherwise.
    * @return  true if game is finished, false otherwise.
    */
    public boolean isGameFinished(){
        if (checkpoints>=4 || board.deck.isEmpty())
            return true;
        else
            return false;
    }
    
    /**Accessor(selector):Returns the winner (1 for player1,2 for player 2,0 for draw)
     * Postcondition:The winner number has been returned
     */
    public int seeWinner(){
        if(P1.getPoints()>P2.getPoints())
            return 1;
        else if(P1.getPoints()<P2.getPoints())
            return 2;
        else
            return 0;
    }
    
     /**Accessor(selector):Returns the total checkpoints
     * Postcondition:The check points has been returned
     */
    public int getCheckpoints(){
        return checkpoints;
    }
     /**Accessor(selector):Returns the card url
     * Postcondition:The card url has been returned
     */
    public URL cardurl(Card card,int i){
           ClassLoader cldr;
           cldr = this.getClass().getClassLoader();

            String place="";
            String cardtype="";
            if (card.getCardtype()==KNOSOS){
               place="knossos";
            }
            else if (card.getCardtype()==FAISTOS){
               place="phaistos";
            }
            else if (card.getCardtype()==ZAKROS){
               place="zakros";
            }
            else if  (card.getCardtype()==MALIA){
               place="malia";
            }
           if (card instanceof NumberCard){
               return numurl(card.getCardtype(),((NumberCard) card).getNumber());
            }
           if (card instanceof MinotaurCard){
                cardtype="Min"  ; 
            }
           if (card instanceof AriadneCard){
                cardtype="Ari"  ; 
           }

           return cldr.getResource("cards/"+place+cardtype+".jpg");
   }
     /**Accessor(selector):Returns the number card url
     * Postcondition:The number card url has been returned
     */
        public URL numurl(PlaceType pl,int i){
           ClassLoader cldr;
           cldr = this.getClass().getClassLoader();
            String place="";
            String cardtype="";
            if (pl==KNOSOS){
               place="knossos";
            }
            else if (pl==FAISTOS){
               place="phaistos";
            }
            else if (pl==ZAKROS){
               place="zakros";
            }
            else if  (pl==MALIA){
               place="malia";
            }
            cardtype=""+i;
            
            
           return cldr.getResource("cards/"+place+cardtype+".jpg");
        }
     /**Accessor(selector):Returns the pawn url
     * Postcondition:The pawn url has been returned
     */
        public URL PawnURL(Pawn pawn){
           ClassLoader cldr;
           cldr = this.getClass().getClassLoader();
           String pawnTYPE="";
           if(pawn instanceof Archeologist)
                  pawnTYPE="arch";
            if(pawn instanceof Theseus)
                  pawnTYPE="theseus";
            
            
           return cldr.getResource("pionia/"+pawnTYPE+".jpg");
        }

        
        
   /** Observer:Returns true if successfully collected a new finding, false otherwise.
    * Postcondition: Return true if successfully collected a new finding, false otherwise.
    * @return   true if successfully collected a new finding, false otherwise.    
    */
        public boolean collectNewFinding(PlaceType placetype,int player) throws Exception{
        int i;
        Random rn =new Random();
        i= rn.nextInt(3) + 1;
            if(player==1){
                if(i==1 && board.getRemainStatues()>0 && P1.statues.size()<10){
                    P1.statues.add(new Statue(P1.statues.size()-1));
                    board.setRemainStatues();
                      JOptionPane.showMessageDialog(null,
                     P1.statues.get(P1.statues.size()-1).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);

                    return true;
                }
                else if(i==2 && !board.wallpictures1.isEmpty()){
                    P1.wallpictures.add(board.wallpictures1.remove(board.wallpictures1.size()-1));
                      JOptionPane.showMessageDialog(null,
                     P1.wallpictures.get(P1.wallpictures.size()-1).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                else if(i==3 && !board.hasRareitemTaken(placetype)){
                    P1.setRareItem(placetype, board.getRareItem(placetype));
                    board.setRareItemTaken(placetype);
                      JOptionPane.showMessageDialog(null,
                     P1.getRareFinding(placetype).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                else if (board.getRemainStatues()==0 && board.hasRareitemTaken(placetype) &&board.wallpictures1.isEmpty()) 
                    return true;
                else 
                    return false;
            }
            if(player==2){
                if(i==1 && board.getRemainStatues()>0 && P2.statues.size()<10){
                    P2.statues.add(new Statue(P2.statues.size()-1));
                    board.setRemainStatues();
                      JOptionPane.showMessageDialog(null,
                     P2.statues.get(P2.statues.size()-1).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                else if(i==2 && !board.wallpictures2.isEmpty()){
                    P2.wallpictures.add(board.wallpictures2.remove(board.wallpictures2.size()-1));
                      JOptionPane.showMessageDialog(null,
                     P2.wallpictures.get(P2.wallpictures.size()-1).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                else if(i==3 && !board.hasRareitemTaken(placetype)){
                    P2.setRareItem(placetype, board.getRareItem(placetype));
                    board.setRareItemTaken(placetype);
                      JOptionPane.showMessageDialog(null,
                     P2.getRareFinding(placetype).toString(),
                     "Συγχαρητήρια!!",
                      JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                else if(board.getRemainStatues()==0 && board.hasRareitemTaken(placetype) && board.wallpictures2.isEmpty()) 
                    return true;
                else 
                    return false;
            }
            return false;
        }

}
