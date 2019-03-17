
package model.Position;
import model.PlaceType.PlaceType;
import java.util.Random; 
import model.Finding.Finding;
import model.Finding.Statue;


public class FindingPosition extends Position{
    int number;
    public FindingPosition(PlaceType placetype,int i) {
        super(placetype,i);
   
    }
   
    /**Accessor(selector):Returns a new finding
     * Postcondition:The new finding of this Position has been returned
     * @return points
     */
    public int FindNewFinding(){
        Random rn =new Random();
        return rn.nextInt(3) + 1;
    
    }
    

    
}
