package model.cards;

import model.PlaceType.PlaceType;


public class NumberCard implements Card {
    private  int number;     
    private  PlaceType cardtype;  
   
    /**  Constructor .
    *   Postcondition .Creates a new Number Card with 'cardtype' cardtype and 'number' number.
     * @param number
     * @param cardtype
    */
    public NumberCard(int number,PlaceType cardtype) {
        this.cardtype=cardtype;
        this.number=number;
    }
    
    /**Accessor(selector):Returns the number of the Card
     * Postcondition:The number of the card has been returned
     * @return PlaceType cardtype
     */
    
    public int getNumber(){
        return number;
    }
    
    /**Transformer(Mutative) :set Cards number.
    *  Postcondition:The card's number has been set 
    * @param number
    */
    public void setNumber(int number){
        this.number=number;
    }
    
    
    /**Accessor(selector):Returns the type of the Card
     * Postcondition:The type of the card has been returned
     * @return PlaceType cardtype
    */
    @Override
    public PlaceType getCardtype() {
        return cardtype;
    }

    /**Transformer(Mutative) :set Cards type.
    *  Postcondition:The card's type has been set 
    * @param cardtype
    */
    @Override
    public void setType(PlaceType cardtype) {
        this.cardtype=cardtype;
    }
    
    /** Returns the string representation of the card
    * Postcondition: The string representation of a card is returned.
    * @return The string representation of a card 
    */
    @Override
    public String toString(){
        return "Κάρτα αριθμού " + this.number + " και τύπου " + this.cardtype;
    }

    @Override
    public String getName() {
       return ""+number;

    }
    
}
