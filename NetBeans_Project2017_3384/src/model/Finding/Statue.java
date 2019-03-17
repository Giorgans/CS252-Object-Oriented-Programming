
package model.Finding;


public class Statue implements Finding{
     private int points;
     
    /**  Constructor .
    *   Postcondition .Creates a new Statue with depending on how many statues the player has. 
    * @param i
    */
     public Statue(int i){
         if(i==1){this.points=-20;}
         else if(i==2){this.points=-15;}
         else if(i==3){this.points=5;}
         else if(i==4){this.points=15;}
         else if(i==5){this.points=30;}
         else if(i==6){this.points=45;}
         else if(i==7){this.points=55;}
         else if(i==8){this.points=70;}
         else if(i==9){this.points=90;}
         else if(i==10){this.points=100;}
         else {this.points=0;}
         
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
    @Override
    public String toString(){
        return "Βρήκες ένα άγαλμα της Θεάς των Φιδιών!!!;\nΩς η θεά με τα φίδια "
                + "ονομάζεται ο τύπος αγαλματίδιου που βρέθηκε σε ανασκαφές στους "
                + "Μινωικούς αρχαιολογικούς τόπους που παρουσιάζει γυναίκα να κρατάει φίδια.\n "
                + "Τα αγαλματίδα χρονολογούνται στον 16ο αιώνα π.Χ..\n Λίγες πληροφορίες έχουμε για την "
                + "ερμηνεία των αγαλματιδίων. \nΟ Έβανς συνδέει την θεά των όφεων με την Αιγυπτιακή θεά Ουατζέτ.\n "
                + "Είναι προπομπός της κρητικής Ρέας και παρουσιάζει μεγάλη ομοιότητα με την φρυγική Κυβέλη και την "
                + "εφεσία Αρτέμιδα.";
    }

}
