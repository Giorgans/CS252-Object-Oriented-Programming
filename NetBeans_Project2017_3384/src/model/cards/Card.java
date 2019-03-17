
package model.cards;

import model.PlaceType.PlaceType;



public abstract interface Card {
           public PlaceType getCardtype();
           public String getName();
           public void setType(PlaceType cardtype);
           @Override
           public abstract String toString();
}
