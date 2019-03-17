
package model.cards;

import model.PlaceType.PlaceType;


public  class MinotaurCard extends SpecialCard {
  
    /**  Constructor .
    *   Postcondition .Creates a new Special Card with 'cardtype' cardtype and 'Mινωταυρος' name.
     * @param cardtype
    */    
    public MinotaurCard(PlaceType cardtype) {
        super(cardtype,"Mινώταυρος");
    }
    
     /** Returns the string representation of a card
     * Postcondition: The string representation of a card is returned.
     * @return The string representation of a card 
     */    @Override
    public String toString(){
        return "Κάρτα Μινωταύρου " + "τυπου " + getCardtype();
    }



    
}
