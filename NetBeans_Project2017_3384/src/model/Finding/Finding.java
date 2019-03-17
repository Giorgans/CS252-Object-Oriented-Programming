
package model.Finding;


public interface Finding {
    
    /**Accessor(selector):Returns the points of this finding
     * Postcondition:The points of this finding has been returned
     * @return points
     */
     public int getPoints();
     
    /**Transformer(Mutative) :set finding's points.
    *  Postcondition:The points of this finding has been set
    * @param points
    */
     public void setPoints(int points);  
     
    /** Returns the string representation of a finding
    * Postcondition: The string representation of a finding is returned.
    * @return The string representation of a finding 
    */
     @Override
     public String toString();
}
