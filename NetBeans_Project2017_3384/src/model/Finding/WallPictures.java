
package model.Finding;


public class WallPictures implements Finding{
    private int points;
    private String info; 
    private int i;
    /**  Constructor .
    *   Postcondition .Creates a new Wall Picture with 'points' points 
    * @param points
    */
    public WallPictures(int i,int points,String info){
        this.points=points;
        this.info=info;
        this.i=i;
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
    
    /** Returns the string representation of a finding
    * Postcondition: The string representation of a finding is returned.
    * @return The string representation of a finding 
    */
    public void setText(String text){
        info=text;
    }
    
    public int getInt(){
        return i;
    }
    
    
    @Override
    public String toString(){
        return info;
    }

    
    
    
}
