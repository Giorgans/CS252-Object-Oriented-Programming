
package model.cards;

import model.PlaceType.PlaceType;


public abstract class SpecialCard implements Card {
    private  String name;
    private PlaceType cardtype;
    
    /** Constructor .
    *   Postcondition .Creates a new Special Card with 'cardtype' cardtype and 'name' name.
     * @param cardtype
     * @param name
    */
    public SpecialCard(PlaceType cardtype,String name) {
        this.cardtype= cardtype;
        this.name=name;
    }
    
    
    /**Transformer(Mutative) :set Cards type.
    *  Postcondition:The card's type has been set 
    * @param cardtype
    */
    @Override
    public void setType(PlaceType cardtype){
         this.cardtype=cardtype;
    } 
    
    /**Accessor(selector):Returns the type of the Card.
     * Postcondition:The type of the card has been returned
     * @return PlaceType cardtype
     */
    @Override
    public PlaceType getCardtype(){
        return cardtype;
    }
    
     /**Accessor(selector):Returns the name of the Card.
     * Postcondition:The name of the card has been returned
     * @return  
     */
    public String getName(){
        return name;
    }
    
    
    /**Transformer(Mutative) :set Cards name.
    *  Postcondition:The card's number has been set 
    * @param name
    */
    public void setName(String name){
        this.name=name;
    }

    
    
     /**
     * Returns the string representation of the Card.
     * Postcondition: The string representation of a card is returned.
     * @return The string representation of a card 
     */
    @Override
    public abstract String toString();
    
    
}
