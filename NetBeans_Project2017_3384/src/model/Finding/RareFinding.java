package model.Finding;


public abstract class RareFinding implements Finding{
    private int points;
    private boolean taken=false;
    
    /**  Constructor .
    *   Postcondition .Creates a new Rare Finding with 'points' points 
    * @param points
    */
    RareFinding(int points){
        this.points=points;
    }
    
    /**Accessor(selector):Returns the points of this finding
     * Postcondition:The points of this finding has been returned
     * @return points
     */
    @Override
    public int getPoints(){
        return points;
    }
    
    /**Transformer(Mutative) :set finding's points.
    *  Postcondition:The points of this finding has been set
    * @param points
    */
    @Override
    public void setPoints(int points) {
         this.points=points;
    }
    
   /** Observer:Return true if this item has taken from the other player, false otherwise
    * Postcondition: return true if this item has taken from the other player, false otherwise
    * @return true if this item has taken from the other player, false otherwise
    */
    public boolean hasTaken(){
        return taken;
    }

    /**Transformer(Mutative) :set that  this item is taken.
    *  Postcondition:The item has been taken
    */
    public void setTaken(){
        taken =true;
    }
    
    
    /** Returns the string representation of a finding
    * Postcondition: The string representation of a finding is returned.
    * @return The string representation of a finding 
    */
    @Override
    public abstract String toString();
}
