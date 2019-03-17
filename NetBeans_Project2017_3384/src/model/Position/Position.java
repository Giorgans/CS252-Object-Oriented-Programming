
package model.Position;

import model.Pawn.Pawn;
import model.PlaceType.PlaceType;
import static model.PlaceType.PlaceType.FAISTOS;
import static model.PlaceType.PlaceType.KNOSOS;
import static model.PlaceType.PlaceType.MALIA;
import static model.PlaceType.PlaceType.ZAKROS;


public abstract class Position {
    private  PlaceType placetype;
    private int points;
    private Pawn Pawn1=null;
    private Pawn Pawn2=null;
    private int i;

    /**  Constructor .
    *   Postcondition .Creates a new Position with 'cardtype' cardtype 
    *             and sets the int points depending on the number of the step i.
     * @param placetype
     * @param i 
     */
    public Position(PlaceType placetype,int i){
         this.placetype=placetype;
         if(i==0){this.points=-20;}
         else if(i==1){this.points=-15;}
         else if(i==2){this.points=-10;}
         else if(i==3){this.points=5;}
         else if(i==4){this.points=10;}
         else if(i==5){this.points=15;}
         else if(i==6){this.points=30;}
         else if(i==7){this.points=35;}    
         else if(i==8){this.points=50;} 
         else {this.points=0;} 
         this.i=i;
    }
    
    /**Accessor(selector):Returns the type of this Position
    * Postcondition:The type of this Position has been returned
    * @return placeType 
    */
    public PlaceType getPlaceType(){
        return placetype;
    }
    /**Transformer(Mutative) :set Position's points.
    *  Postcondition:The posision's points has been set
    * @param points
    */
    public void setPoints(int points){
        this.points=points;
    }
    
    /**Accessor(selector):Returns the points of this Position
     * Postcondition:The points of this Position has been returned
     * @return points
     */
    public int getPoints(){
        return points;
    }
    
    /** Returns the string representation of a specific position depending on the place type,
    * and the step i.
    * Postcondition: The string representation of a position is returned.
     * @param i
    * @return The string representation of a card 
    */
    
    public Pawn getPawn(int i)  throws Exception{
        if (i==1)
            return Pawn1;
        else if(i==2)
            return Pawn2;
        else
            throw new Exception("Λάθος αριθμος παιχτη");
    }
    
    public void setPawn(Pawn pawn,int j) throws Exception{
        if(j==1)
            this.Pawn1=pawn;
        else if(j==2)
            this.Pawn2=pawn;
        else
            throw new Exception("Λάθος αριθμός παιχτη");
        System.out.print(" to " + placetype + " in "+ i + " possition\n");
    }
    
    public void removePawn(int j) throws Exception{
        if(j==1){
            this.Pawn1=null;
        }
        else if(j==2){
            this.Pawn2=null;
        }
        else
            throw new Exception("Λάθος αριθμός παιχτη");
        
        System.out.print("Pawn removed from " + placetype + " in "+ i + " possition\n");
    }
    
    @Override
    public String toString(){
        if(placetype==KNOSOS) return "Έφτασες στο Ανάκτορο της Κνωσού!!!;\nΤο μινωικό ανάκτορο "
                + "είναι ο κύριος επισκέψιμος χώρος της Κνωσού (ή Κνωσσού), σημαντικής πόλης"
                + " κατά την αρχαιότητα, με συνεχή ζωή από τα νεολιθικά χρόνια έως τον 5ο αι.\n Είναι "
                + "χτισμένο στο λόφο της Κεφάλας, με εύκολη πρόσβαση στη θάλασσα αλλά και στο εσωτερικό της "
                + "Κρήτης.\n Κατά την παράδοση, υπήρξε η έδρα του σοφού βασιλιά Μίνωα. Συναρπαστικοί μύθοι, του "
                + "Λαβύρινθου με το Μινώταυρο και του Δαίδαλου με τον Ίκαρο, συνδέονται με το ανάκτορο της Κνωσσού.";
        if(placetype==FAISTOS) return "Έφτασες στο Ανάκτορο της Φαιστού!!!;\n Το Μινωικό Ανάκτορο της Φαιστού  βρίσκεται "
                + "στην νότιο-κεντρική Κρήτη, στην πεδιάδα της Μεσαράς,\n 55 χιλιόμετρα νότια από το Ηράκλειο και σε μικρή "
                + "απόσταση από τον αρχαιολογικό χώρο στην Αγία Τριάδα, τον αρχαιολογικό χώρο στη Γόρτυνα και τα Μάταλα.\n "
                + "Το μινωικό ανάκτορο της Φαιστού αντιστοιχεί σε ακμαία πόλη που,\n όχι τυχαία, αναπτύχθηκε στην έφορη πεδιάδα "
                + "της Μεσαράς κατά τους προϊστορικούς χρόνους,\n δηλαδή από το 6.000 π.Χ. περίπου μέχρι και τον 1ο π.Χ. αιώνα, "
                + "όπως επιβεβαιώνουν τα αρχαιολογικά ευρήματα.";
        if(placetype==ZAKROS) return "Έφτασες στο Ανάκτορο της Ζάκρου!!!;\nΤο ανάκτορο της Ζάκρου είναι"
                + " το τέταρτο σε μέγεθος της Μινωικής Κρήτης.\n Βρισκόταν σε σημαντικό στρατηγικό σημείο,"
                + " σε ασφαλισμένο κολπίσκο,\n και ήταν κέντρο εμπορικών ανταλλαγών με τις χώρες της Ανατολής,\n"
                + " όπως φαίνεται από τα ευρήματα (χαυλιόδοντες ελέφαντα, φαγεντιανή, χαλκός κλπ).\n  "
                + "Το ανάκτορο αποτέλεσε το κέντρο διοίκησης, θρησκείας και εμπορίου. Το περιστοίχιζε η πόλη.\n "
                + "Στο χώρο δεν έγινε νέα οικοδόμηση, εκτός από κάποιες καλλιέργειες. ";
        if(placetype==MALIA) return "Έφτασες στο Ανάκτορο των Μαλίων!!!; "
                + "Ανατολικά από τα σημερινά Μάλια βρίσκεται το μινωικό ανάκτορο των Μαλίων.\n "
                + "Είναι το τρίτο σε μέγεθος ανάκτορο της μινωικής Κρήτης και είναι χτισμένο σε μια τοποθεσία προνομιακή,\n "
                + "κοντά στη θάλασσα και πάνω στο δρόμο που συνδέει την ανατολική με την κεντρική Κρήτη.\n Το ανάκτορο "
                + "των Μαλίων κατά τη μυθολογία χρησίμευε σαν κατοικία του Σαρπηδόνα, αδερφού του Μίνωα, και πρωτοχτίζεται"
                + " το 1900 π.Χ. \nΟ προϋπάρχων ισχυρός οικισμός, από τον οποίο σώζονται συνοικίες γύρω από το ανάκτορο, "
                + "μετατρέπεται έτσι σε ανακτορικό κέντρο-πόλη.";
        return "";
    }
}
