
package model.cards;

import model.PlaceType.PlaceType;


public  class AriadneCard extends SpecialCard{
    /**  Constructor .
    *   Postcondition .Creates a new Special Card with 'cardtype' cardtype and 'Αριάδνη' name.
    * @param cardtype
    */    
    public AriadneCard(PlaceType cardtype) {
        super(cardtype,"Αριάδνη");
    }
    
    
    
    /** Returns the string representation of a card
     * Postcondition: The string representation of a card is returned.
     * @return The string representation of a card 
     */    @Override
    public String toString(){
        return "Κάρτα Αριάδνης " + "τυπου " + getCardtype();
    }


    
}
