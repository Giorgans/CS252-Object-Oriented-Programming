
package model.Board;
import java.util.ArrayList;
import model.cards.Card;
import java.util.Collections;
import model.Finding.FaistosDisk;
import model.Finding.MaliaJewel;
import model.Finding.MinoasRing;
import model.Finding.RareFinding;
import model.Finding.RytoZakrou;
import model.Finding.WallPictures;
import model.PlaceType.PlaceType;
import model.cards.NumberCard;
import model.Position.FindingPosition;
import model.Position.Position;
import model.Position.SimplePosition;
import model.cards.AriadneCard;
import model.cards.MinotaurCard;
import static model.PlaceType.PlaceType.KNOSOS;
import static model.PlaceType.PlaceType.FAISTOS;
import static model.PlaceType.PlaceType.MALIA;
import static model.PlaceType.PlaceType.ZAKROS;

public class Board {
    public ArrayList<Card> deck ;
    public Position[] PosK,PosF,PosM,PosZ;
    public ArrayList<WallPictures> wallpictures1 ,wallpictures2;
    private int Statues=10;
    private RareFinding DF,MR,MJ,RZ;

    
    /**  Constructor .
    *   Postcondition .Creates a new board with a new deck,
    * 4 different positions and the Finding items 
    */
    public Board(){
        this.deck= new ArrayList<>() ;
        this.wallpictures1=new ArrayList<>() ;
        this.wallpictures2=new ArrayList<>() ;
        this.PosF= new Position[9];
        this.PosK= new Position[9];
        this.PosZ= new Position[9];
        this.PosM= new Position[9];
        for (int i=0;i<9;i++){
            if (i==1 || i==3 || i==5 || i==7 || i==8 ){
               PosF[i]= new FindingPosition(FAISTOS,i);
               PosK[i]= new FindingPosition(KNOSOS,i);
               PosZ[i]= new FindingPosition(ZAKROS,i);
               PosM[i]= new FindingPosition(MALIA,i);
            }
            else{
               PosF[i]= new SimplePosition(FAISTOS,i);
               PosK[i]= new SimplePosition(KNOSOS,i);
               PosZ[i]= new SimplePosition(ZAKROS,i);
               PosM[i]= new SimplePosition(MALIA,i);
            }
        }
        this.DF= new FaistosDisk();
        this.MR= new MinoasRing();
        this.MJ= new MaliaJewel();
        this.RZ= new RytoZakrou();
        setAndShuffleDeck();
        setandshufflewallpapers();
    }
    
    /**Transformer(Mutative) :Set a new deck of 100 new cards 
     * and shuffle them.
    *  Postcondition:The deck has been set and shuffled
    */
    public void setAndShuffleDeck(){
         for (int j=0;j<2;j++){
             for (int i=1;i<=10;i++){
                deck.add(new NumberCard(i,KNOSOS));
                deck.add(new NumberCard(i,FAISTOS));
                deck.add(new NumberCard(i,MALIA));
                deck.add(new NumberCard(i,ZAKROS));
            }
        }
        
        for (int i=1;i<=3;i++){
                deck.add(new AriadneCard(KNOSOS));
                deck.add(new AriadneCard(FAISTOS));
                deck.add(new AriadneCard(MALIA));
                deck.add(new AriadneCard(ZAKROS));
        }
        for (int i=1;i<=2;i++){
                deck.add(new MinotaurCard(KNOSOS));
                deck.add(new MinotaurCard(FAISTOS));
                deck.add(new MinotaurCard(MALIA));
                deck.add(new MinotaurCard(ZAKROS));
        }
        Collections.shuffle(deck);
    }
    
    /**Accessor(selector):Returns a card from the top of the deck.
     * Postcondition:The top card has been returned
     * @return deck
     */
    public Card pickCard(){
        return deck.remove(deck.size()-1);
    } 
    
    /**Accessor(selector):Returns a random WallPicture from the remaining list.
     * Postcondition:The Wall Picture has been returned
     * @return wallpictures
     */
  // public WallPictures pickaWallPicture(){
  //      return wallpictures.remove(wallpictures.size()-1);
  //  } 
    
   /* /** Observer:Return true if there are no remaining WallPictures left, false otherwise.
    * Postcondition: Return true if there are no remaining WallPictures left, false otherwise.
    * @return  true if there are no remaining WallPictures left, false otherwise.
    /*
    public boolean isWallPicturesEmpty(){
        if (wallpictures1.isEmpty())
            return true;
        else
            return false;
    }*/
    
    /**Accessor(selector):Returns the remaining statues on the board.
     * Postcondition:The remaining statues has been returned
     * @return Statues
     */
    public int getRemainStatues(){
        return Statues;
    }

    /**Transformer(Mutative) :Set the remaining undiscovered statues.
    *  Postcondition:The remaining undiscovered statues has been set.
    */
    public void setRemainStatues(){
        this.Statues--;
    }
    
    /** Observer:Return true if the number of the remaining statues is 0, false otherwise.
    * Postcondition: Return true if the number of the remaining statues is 0, false otherwise.
    * @return  true if the number of the remaining statues is 0, false otherwise.
    */
    public boolean isStatuesEmpty(){
        if(Statues==0)
            return true;
        else
            return false;
    }
    
   /** Observer:Return true if the deck is empty, false otherwise.
    * Postcondition: return true if the deck is empty, false otherwise.
    * @return  true if the deck is empty, false otherwise.
    */
    public boolean isDeckEmpty(){
        if(deck.isEmpty())
            return true;
        else 
            return false;      
    }
    
   /** Observer:Return true if the item is taken, false otherwise.
    * Postcondition: return true if the item is taken, false otherwise.
    * @param placetype
    * @return  true if the item is taken, false otherwise.
    */
    public boolean hasRareitemTaken(PlaceType placetype) throws Exception{
        if(placetype==KNOSOS)
            return MR.hasTaken();
        else if(placetype==FAISTOS)
            return DF.hasTaken();
        else if(placetype==MALIA)
            return MJ.hasTaken();
        else if(placetype==ZAKROS)
            return RZ.hasTaken();
        else
            throw new Exception("Λάθος τοποθεσία");
            
    }
    
    
    /**Transformer(Mutative) :Set the RareItem taken
    *  Postcondition:The Item has been set taken
     * @param placetype
    */
    public void setRareItemTaken(PlaceType placetype)  throws Exception{
        if(placetype==KNOSOS)
            MR.setTaken();
        else if(placetype==FAISTOS)
            DF.setTaken();
        else if(placetype==MALIA)
            MJ.setTaken();
        else if(placetype==ZAKROS)
            RZ.setTaken();
        else
            throw new Exception("Λάθος τοποθεσία");
    }
    
        public RareFinding getRareItem(PlaceType placetype)  throws Exception{
        if(placetype==KNOSOS)
            return MR;
        else if(placetype==FAISTOS)
            return DF;
        else if(placetype==MALIA)
            return MJ;
        else if(placetype==ZAKROS)
            return RZ;
        else
            throw new Exception("Λάθος τοποθεσία");
    }

    public void setandshufflewallpapers(){
            wallpictures1.add(new WallPictures(1,20,"Φωτογράφισες την Τοιχογραφία: Οι γαλάζεις κυρίες!!!;\n"
                    + "Όμορφες Μινωίτισσες που κουβεντιάζουν. \n"
                    + "Έχουν ωραία φορέματα, σύμφωνα με τη μόδα της εποχής,\n"
                    + " όμορφα χτενισμένα μαλλιά και πολύτιμα κοσμήματα."));
            wallpictures1.add(new WallPictures(2,20,"Φωτογράφισες την Τοιχογραφία: Τα ταυροκαθάψια!!!;\n"
                    + "Τα ταυροκαθάψια ήταν ένα αγώνισμα που συνηθιζόταν πολύ στα μινωικά χρόνια.\n"
                    + " Περιλάμβανε το πιάσιμο του ταύρου από τα κέρατα, \n"
                    + "το επικίνδυνο άλμα στη ράχη του ζώου και τέλος το πήδημα στο έδαφος πίσω του."));
            wallpictures1.add(new WallPictures(3,15,"Φωτογράφισες την Τοιχογραφία: Τα δελφίνια!!!;\n"
                    + "Η τοιχογραφία αυτή προέρχεται από τo μέγαρο της βασίλισσας. \n"
                    + "Δελφίνια κολυμπούν ανάμεσα σε ψάρια, μέσα στα κύματα."));
            wallpictures1.add(new WallPictures(4,20,"Φωτογράφισες την Τοιχογραφία: Ο πρίγκιπας με τα κρίνα;\n"
                    + "Εικονίζεται επιβλητική ανδρική μορφή, που βαδίζει προς τα αριστερά σε απροσδιόριστο ερυθρό φόντο.\n"
                    + " Φοράει το τυπικό μινωικό περίζωμα με φαρδιά ζώνη, περιδέραιο στο λαιμό και πλούσιο κάλυμμα κεφαλής \n"
                    + "διακοσμημένο με κρίνα και φτερά παγωνιού. Η στάση των χεριών του δείχνει ότι ίσως έσερνε\n"
                    + " με το αριστερό του χέρι ένα ζώο ή κάποιο μυθικό τέρας, γρύπα ή σφίγγα.\n"
                    + " Ο νέος ονομάσθηκε από τους ερευνητές «πρίγκηπας», γιατί θεωρήθηκε ότι αποδίδει το βασιλιά-ιερέα,\n"
                    + " που ζούσε στο ανάκτορο της Κνωσού.!!!"));
           wallpictures1.add(new WallPictures(5,15,"Φωτογράφισες την Τοιχογραφία: Πομπή νέων!!!;\n"
                   + "Νέοι λαμβάνουν μέρος σε θρησκευτική πομπή και φέρουν αγγεία με δώρα για τη θεότητα ή για το βασιλιά.\n"
                   + " Η τοιχογραφία αυτή κοσμούσε τον λεγόμενο «διάδρομο της πομπής» του ανακτόρου της Κνωσού."));
           wallpictures1.add(new WallPictures(6,15,"Φωτογράφισες την Τοιχογραφία: Η παριζιάνα!!!;\n"
                   + "Εικονίζεται μια γυναίκα αριστοκρατικής καταγωγής σε θέση προφίλ. \n"
                   + " Ονομάστηκε «Παριζιάνα» από τον Άρθουρ Έβανς, γιατί το 1903 (έτος που ανακαλύφθηκε) τα μεγάλα μάτια,\n"
                   + " τα κατσαρά μαλλιά, τα έντονα κόκκινα χείλη και η ανασηκωμένη μύτη θεωρούνταν τα ιδεώδη της γυναικείας ομορφιάς,\n"
                   + " τα οποία μόνο μια όμορφη γυναίκα από … το Παρίσι μπορούσε να τα ενσαρκώσει." ));       
         
           wallpictures2.add(new WallPictures(1,20,"Φωτογράφισες την Τοιχογραφία: Οι γαλάζεις κυρίες!!!;\n"
                    + "Όμορφες Μινωίτισσες που κουβεντιάζουν. \n"
                    + "Έχουν ωραία φορέματα, σύμφωνα με τη μόδα της εποχής,\n"
                    + " όμορφα χτενισμένα μαλλιά και πολύτιμα κοσμήματα."));
            wallpictures2.add(new WallPictures(2,20,"Φωτογράφισες την Τοιχογραφία: Τα ταυροκαθάψια!!!;\n"
                    + "Τα ταυροκαθάψια ήταν ένα αγώνισμα που συνηθιζόταν πολύ στα μινωικά χρόνια.\n"
                    + " Περιλάμβανε το πιάσιμο του ταύρου από τα κέρατα, \n"
                    + "το επικίνδυνο άλμα στη ράχη του ζώου και τέλος το πήδημα στο έδαφος πίσω του."));
            wallpictures2.add(new WallPictures(3,15,"Φωτογράφισες την Τοιχογραφία: Τα δελφίνια!!!;\n"
                    + "Η τοιχογραφία αυτή προέρχεται από τo μέγαρο της βασίλισσας. \n"
                    + "Δελφίνια κολυμπούν ανάμεσα σε ψάρια, μέσα στα κύματα."));
            wallpictures2.add(new WallPictures(4,20,"Φωτογράφισες την Τοιχογραφία: Ο πρίγκιπας με τα κρίνα;\n"
                    + "Εικονίζεται επιβλητική ανδρική μορφή, που βαδίζει προς τα αριστερά σε απροσδιόριστο ερυθρό φόντο.\n"
                    + " Φοράει το τυπικό μινωικό περίζωμα με φαρδιά ζώνη, περιδέραιο στο λαιμό και πλούσιο κάλυμμα κεφαλής \n"
                    + "διακοσμημένο με κρίνα και φτερά παγωνιού. Η στάση των χεριών του δείχνει ότι ίσως έσερνε\n"
                    + " με το αριστερό του χέρι ένα ζώο ή κάποιο μυθικό τέρας, γρύπα ή σφίγγα.\n"
                    + " Ο νέος ονομάσθηκε από τους ερευνητές «πρίγκηπας», γιατί θεωρήθηκε ότι αποδίδει το βασιλιά-ιερέα,\n"
                    + " που ζούσε στο ανάκτορο της Κνωσού.!!!"));
           wallpictures2.add(new WallPictures(5,15,"Φωτογράφισες την Τοιχογραφία: Πομπή νέων!!!;\n"
                   + "Νέοι λαμβάνουν μέρος σε θρησκευτική πομπή και φέρουν αγγεία με δώρα για τη θεότητα ή για το βασιλιά.\n"
                   + " Η τοιχογραφία αυτή κοσμούσε τον λεγόμενο «διάδρομο της πομπής» του ανακτόρου της Κνωσού."));
           wallpictures2.add(new WallPictures(6,15,"Φωτογράφισες την Τοιχογραφία: Η παριζιάνα!!!;\n"
                   + "Εικονίζεται μια γυναίκα αριστοκρατικής καταγωγής σε θέση προφίλ. \n"
                   + " Ονομάστηκε «Παριζιάνα» από τον Άρθουρ Έβανς, γιατί το 1903 (έτος που ανακαλύφθηκε) τα μεγάλα μάτια,\n"
                   + " τα κατσαρά μαλλιά, τα έντονα κόκκινα χείλη και η ανασηκωμένη μύτη θεωρούνταν τα ιδεώδη της γυναικείας ομορφιάς,\n"
                   + " τα οποία μόνο μια όμορφη γυναίκα από … το Παρίσι μπορούσε να τα ενσαρκώσει." ));       
        Collections.shuffle(wallpictures1);
        Collections.shuffle(wallpictures2);
    }
}
