
package model.Player;

import java.util.ArrayList;
import java.util.Collections;
import model.Finding.Finding;
import model.Finding.RareFinding;
import model.Finding.Statue;
import model.Finding.WallPictures;
import model.Pawn.Pawn;
import model.cards.Card;
import model.Pawn.Archeologist;
import model.Pawn.Theseus;
import model.PlaceType.PlaceType;
import static model.PlaceType.PlaceType.FAISTOS;
import static model.PlaceType.PlaceType.KNOSOS;
import static model.PlaceType.PlaceType.MALIA;
import static model.PlaceType.PlaceType.ZAKROS;
import model.cards.NumberCard;

public class Player {
    public ArrayList<Card> playersHand ;
    public ArrayList<Statue> statues ;
    public ArrayList<WallPictures> wallpictures ;
    public RareFinding DF,MR,MJ,RZ;
    public ArrayList<Pawn> pawns ;
    private int points;
    private int checkpoints;
    private  String name=null;
    private NumberCard lastnumcardK,lastnumcardF,lastnumcardZ,lastnumcardM;

    
    /**  Constructor .
    *   Postcondition .Creates a new player with his hand of cards. 
    */

    public Player(String name){
        this.wallpictures=new ArrayList<>();
        this.playersHand = new ArrayList<>();
        this.statues=new ArrayList<>();
        this.pawns=new ArrayList<>();
        this.points = 0;
        this.checkpoints = 0;
        this.name=name;
        this.DF=null;
        this.MJ=null;
        this.MR=null;
        this.RZ=null;
        this.lastnumcardK=null;
        this.lastnumcardF=null;
        this.lastnumcardZ=null;
        this.lastnumcardM=null;
        setAndshufflePawns();
    }
    
    /**Transformer(Mutative) :set Players points.
    *  Postcondition:The players's points has been set
    * @param points
    */
    public void setPoints(int points){
        this.points+=points;
    }
    
    /**Accessor(selector):Returns the points of the player
     * Postcondition:The points of the player has been returned
     * @return points
     */
    public int getPoints(){
        int temppoints=points;
        for (int i=0;i<statues.size();i++) temppoints+=statues.get(i).getPoints();
        if(MR!=null)  temppoints+=MR.getPoints();
        if(DF!=null)  temppoints+=DF.getPoints();
        if(MJ!=null)  temppoints+=MJ.getPoints();
        if(RZ!=null)  temppoints+=RZ.getPoints();
        for (int i=0;i<wallpictures.size();i++) temppoints+=wallpictures.get(i).getPoints();
        return temppoints;
    }
    
    /**Accessor(selector):Returns the name of the player
     * Postcondition:The name of the player has been returned
     * @return points
     */
    public String getName(){
        return name;
    }
    
   /** Observer:Return true if the Player's hand is empty, false otherwise.
    * Postcondition: return  true if the Player's hand is empty, false otherwise.
    * @return  true if the Player's hand is empty, false otherwise.
    */
    public boolean HasCards(){
        if (playersHand.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
    
    /**Transformer(Mutative) :Add a card in player's hand
    *  Postcondition:The card in the hand has been added
    * @param card
    */
    public void DrawCard(Card card){
       if(!isHandFull())
        playersHand.add(card);
    }
    
    /**Accessor(selector):Returns a selected card
     * Postcondition:The card has been returned
     * @param i
     * @return 
     */
    public Card getCardinHand(int i){
        return playersHand.remove(i);
    }
    
    
    
    /** Observer:Return true if the Player's holds 8 cards, false otherwise.
    * Postcondition: return  true if the Player's holds 8 cards, false otherwise.
    * @return true if the Player's holds 8 cards, false otherwise.
    */
    public boolean isHandFull(){
           if (playersHand.size()<8) {
               return false;
           }
           else {
               return true;
           }
    }
    
    /**Transformer(Mutative) :Add a checkpoint on this player
    *  Postcondition:The checkpoint has been added    
    */
    public void addCheckpoint(){
        checkpoints++;
    }
    
    /**Accessor(selector):Return the chekpoints of the player
     * Postcondition:The checkpoints of the player has been returned
     * @return checkpoints
     */
    public int getCheckpoints(){
        return checkpoints;
    }
    
    
    public void setRareItem(PlaceType placetype,RareFinding rareitem) throws Exception{
        if(placetype==KNOSOS)
            this.MR=rareitem;
        else if(placetype==FAISTOS)
            this.DF=rareitem;
        else if(placetype==MALIA)
            this.MJ=rareitem;
        else if(placetype==ZAKROS)
            this.RZ=rareitem;
        else 
            throw new Exception("Λάθος τοποθεσία");
    }
    
    public NumberCard getLastNumberCard(PlaceType placetype) throws Exception{
        if(placetype==KNOSOS)
            return (NumberCard) lastnumcardK;
        else if(placetype==FAISTOS)
            return (NumberCard) lastnumcardF;
        else if(placetype==MALIA)
            return (NumberCard) lastnumcardM;
        else if(placetype==ZAKROS)
            return (NumberCard) lastnumcardZ;
        else 
            throw new Exception("Λάθος τοποθεσία");
    }
    public void setLastNumberCard(NumberCard card) throws Exception{
        if(card.getCardtype()==KNOSOS)
             lastnumcardK=card;
        else if(card.getCardtype()==FAISTOS)
             lastnumcardF=card;
        else if(card.getCardtype()==MALIA)
             lastnumcardM=card;
        else if(card.getCardtype()==ZAKROS)
             lastnumcardZ=card;
        else 
            throw new Exception("Λάθος τοποθεσία");
    }
    
    public void setAndshufflePawns(){
        this.pawns.add(new Archeologist(name));
        this.pawns.add(new Archeologist(name));
        this.pawns.add(new Archeologist(name));
        this.pawns.add(new Theseus(name));
        Collections.shuffle(pawns);
    }
    
    public String pawnsINFO(){
        int archaiologists=0;
        int theseus=0;
        for(int i=0;i<pawns.size();i++){
            if(pawns.get(i) instanceof Archeologist)
                archaiologists++;
            else
                theseus++;
            
           
        }  
            if(archaiologists==0 && theseus!=0)
                return theseus + " Θησέας";
            else if(archaiologists!=0 && theseus==0)
                return archaiologists + " Αρχαιολόγοι";
            else if(archaiologists!=0 && theseus!=0)
                return archaiologists + " Αρχαιολόγοι, " + theseus + " Θησέας";
            else
                return "";

    }
    
    public RareFinding getRareFinding(PlaceType placetype) throws Exception{
        if(placetype==KNOSOS)
            return MR;
        else if(placetype==FAISTOS)
            return DF;
        else if(placetype==MALIA)
            return MJ;
        else if(placetype==ZAKROS)
            return RZ;
        else 
            throw new Exception("Λάθος τοποθεσία");
    }
}
