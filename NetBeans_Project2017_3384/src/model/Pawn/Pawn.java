
package model.Pawn;


public abstract  class Pawn {
       private final String player;
       private int pawnpoints=0;
       
    /**  Constructor .
     *   Postcondition .Creates a new Pawn for a 'player' player 
     * @param player
     */     
       public Pawn(String player){
           this.player=player;
       }
      
    /**Transformer(Mutative) :set Player to pawn.
    *  Postcondition:The Pawn's player has been set
    * @param player
    */
       public void setPlayer (String player){
          
       }
    
    /**Accessor(selector):Returns the owner of this Pawn
    * Postcondition:The player of this pawn has been returned
    * @return player 
    */
       public String getPawnOwner(){
           return player;
       }
       
   /** Observer:Return true if pawn has a player, false otherwise
    * Postcondition: return true if pawn has a player, false otherwise
    * @return true if pawn has a player, false otherwise
    */
       public boolean hasPlayer(){
           if(player==null){
               return false;
           }
           else{
               return true;
           }
       }
       
    /**Accessor(selector):Returns the points of this Pawn
    * Postcondition:The points of this pawn has been returned
    * @return points 
    */
       public int getPoints(){
           return pawnpoints;
       }
       
    /**Transformer(Mutative) :Set points to pawn.
    *  Postcondition:The Pawn's points has been set
    * @param pawnpoints
    */
       public void setPoints(int pawnpoints){
           this.pawnpoints=pawnpoints;
       }
    /** Returns the string representation of a pawn depending on the place type.
    * Postcondition: The string representation of a pawn is returned.
    * @return The string representation of a card 
    */
       @Override
       public abstract String toString();       
       
       
       
       
       
       
}
