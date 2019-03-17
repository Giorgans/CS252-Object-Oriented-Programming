
package view;

import javax.swing.*;
import java.awt.*;
import controller.Controller;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlaceType.PlaceType;

public class GraphicUI extends JFrame implements ActionListener{
        private Controller game;
        private JMenuBar menuBar;
        private JFrame wallpictures;
        private JButton pictures[]=new JButton[6];
        private JButton viewwallpic1,viewwallpic2,deckCard;
        private JButton Player1Cards[]= new JButton[8];
        private JButton Player2Cards[]= new JButton[8];
        private JButton RareFindingsP1[]=new JButton[4];
        private JButton RareFindingsP2[]=new JButton[4];
        private JLabel lastcards1[]=new JLabel[4];
        private JLabel lastcards2[]=new JLabel[4];
        private JLabel l11,l12,l13,l14,l21,l22,l23,l24;
        private JMenuItem newGame,Exit,Save;
        private JLayeredPane player1_field;
        private JLayeredPane player2_field;
        private JLayeredPaneImage board;
        private JLabel PathK[]=new JLabel[9];
        private JLabel PathF[]=new JLabel[9];
        private JLabel PathZ[]=new JLabel[9];
        private JLabel PathM[]=new JLabel[9];
        private JLabel PathPOINTS[]=new JLabel[9];
        private JLabel player1INFO,player2INFO,boardINFO,statues1,statues2,checkpoint;
        private JLabel scor1,scor2;
        private JLabel pawn1[][]=new JLabel[4][9];
        private JLabel pawn2[][]=new JLabel[4][9];
        private JDesktopPane basic_panel;
        private Image image;
         private URL imageURL;
        private ClassLoader cldr;
        private JButton[] buttons = new JButton[16];
        private JPanel jPanel1;
        private boolean clicked=false;
        /**
	 * constructor: Creates a new Window and initializes some buttons and labels 
	 * postconditions: Creates a new Window and initializes some buttons and labels
	 * starting a new game.
	 */
        public  GraphicUI() throws Exception{
             cldr = this.getClass().getClassLoader();
             basic_panel =  new JDesktopPane();
             player1_field=new JLayeredPane();
             player2_field =new JLayeredPane();
             deckCard=new JButton();
             wallpictures=new JFrame();
             int tempposs=10;
             int tempossx=660;
             int tempossy=10;
             for (int i=0;i<8;i++){
                Player1Cards[i]=new JButton();
                Player2Cards[i]=new JButton();
                Player1Cards[i].setBounds(tempposs, 10, 70, 110);
                Player2Cards[i].setBounds(tempposs, 10, 70, 110);
                Player1Cards[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
                Player2Cards[i].setBorder(BorderFactory.createLineBorder(Color.black,1));
                Player1Cards[i].addActionListener(this) ;   
                Player2Cards[i].addActionListener(this) ;   
                player1_field.add(Player1Cards[i]);
                player2_field.add(Player2Cards[i]);
                tempposs+=80;
                if(i<6){
                    pictures[i]=new JButton();
                    wallpictures.add(pictures[i]);
                }
                if(i<4){
                  RareFindingsP1[i]=new JButton();
                  RareFindingsP2[i]=new JButton();
                  RareFindingsP1[i].setBounds(tempossx, tempossy, 50, 50);
                  RareFindingsP2[i].setBounds(tempossx, tempossy, 50, 50);
                  lastcards1[i]=new JLabel();
                  lastcards2[i]=new JLabel();
                  player1_field.add(lastcards1[i]);
                  player2_field.add(lastcards2[i]);
                  tempossx+=60;
                  if(i==2 ){
                     RareFindingsP1[i].setBounds(660, 80, 50, 50);
                     RareFindingsP2[i].setBounds(660, 80, 50, 50);
                  }
                  if(i==3 ){
                     RareFindingsP1[i].setBounds(720, 80, 50, 50);
                     RareFindingsP2[i].setBounds(720, 80, 50, 50);
                  }
                  
                }

             }
            for(int j=0;j<9;j++){
                for(int i=0;i<4;i++){
                 pawn1[i][j]=new JLabel();
                 pawn1[i][j].setBounds(10, 15, 25, 50);
                 pawn2[i][j]=new JLabel();
                 pawn2[i][j].setBounds(40, 15, 25, 50);
                }
            }
             viewwallpic2=new JButton("Οι Τοιχογραφίες μου");
             setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
             this.getContentPane().setBackground(new Color(0,0,0));
             this.setResizable(false);
             this.setTitle("Αναζητώντας τα Χαμένα Μινωικά Ανάκτορα");
             game=new Controller();
             this.setPreferredSize(new Dimension(1315, 865));
             PlayGame();
        }
     
          /**
	 * transformer(mutative):Plays the game and refresh some parts that need change
	 * Postcondition: play the game and refresh some parts that need change
	 *
        */
        private void PlayGame() throws Exception {
           initComponents();
           initPawns() ;
           initLastcards() ;
           initButtons();
            while(!game.isGameFinished()){
                deckCard.setEnabled(false);
                boardINFO.setText("<html>Available Cards:" + (game.board.deck.size())+
                "<br>Check Points: "+ game.getCheckpoints() +"<br>Turn: Player "+ game.seeTurn()+"</html>");
                scor1.setText("To Σκορ μου: "+ game.P1.getPoints()+" Πόντοι");
                scor2.setText("To Σκορ μου: "+ game.P2.getPoints()+" Πόντοι");
                player1INFO.setText("Παίχτης 1 - Διαθέσιμα πιόνια: "+ game.P1.pawnsINFO());
                player2INFO.setText("Παίχτης 2 - Διαθέσιμα πιόνια: "+ game.P2.pawnsINFO());
                statues1.setText("Αγαλματάκια: "+game.P1.statues.size());
                statues2.setText("Αγαλματάκια: "+game.P2.statues.size());
                  System.out.println("Αρχή γύρου\n"+game.seeTurn());
                  for (int i=0;i<8;i++) {
                     if(game.seeTurn()==1){
                      Player1Cards[i].setEnabled(true);
                      Player2Cards[i].setEnabled(false);
                     }
                     if(game.seeTurn()==2){
                      Player1Cards[i].setEnabled(false);
                      Player2Cards[i].setEnabled(true);
                     }
                  }
                  
                  System.out.println("Play a card " + game.P1.playersHand.size()+ " "+ game.P2.playersHand.size());
               while(!clicked){try {Thread.sleep(200);} catch(InterruptedException e) {break;}}
                clicked=false;
                 System.out.println("Kartes paixton " + game.P1.playersHand.size()+" " +game.P2.playersHand.size());
                initLastcards() ;
                  for(int i=0;i<8;i++){
                     if(game.seeTurn()==1)
                      Player1Cards[i].setEnabled(false);
                     else
                      Player2Cards[i].setEnabled(false);
                     }
               deckCard.setEnabled(true);

               initPawns() ; 
               initRareFindings() ;
               while(!clicked){try {Thread.sleep(200);} catch(InterruptedException e) {break;}}
               clicked=false;

                  for (int i=0;i<8;i++) {
                     if(game.seeTurn()==1){
                      Player1Cards[i].setEnabled(false);
                      Player2Cards[i].setEnabled(true);
                     }
                     if(game.seeTurn()==2){
                      Player1Cards[i].setEnabled(true);
                      Player2Cards[i].setEnabled(false);

                     }
                  }
             initButtons();
            System.out.println("Επομενος γύρος");
           game.SetNextTurn(game.seeTurn());
          }
          Object[] Mainoptions = {"gg",
           "Ok"};
            int k = JOptionPane.showOptionDialog(null,
              "Το παιχνίδι τελείωσε! Νικητής ειναι ο παίχτης: "+game.seeWinner()+" !!","Τέλος παιχνιδιού!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,     Mainoptions,   Mainoptions[0]); 
            if(k==JOptionPane.YES_OPTION){System.exit(0);}
            else{System.exit(0);}
        
        }
        
    /**
	 * transformer(mutative):initializes some buttons and labels 
	 * Postcondition:initializes some buttons and labels 
	 *
     */
     private void initComponents() throws Exception {
           this.add(basic_panel);

            basic_panel.setBounds(0, 0, 1315, 900);
          
            /**Creating a menu bar
             */   
            menuBar = new JMenuBar();
            menuBar.setBounds(0, 0, 1315, 20);    
            
            newGame=new JMenuItem("New Game");
            newGame.setBorder(BorderFactory.createLineBorder(Color.black));
            newGame.setBounds(0,0,30,20);
            newGame.addActionListener((ActionEvent event) -> {
                
                
            });
            menuBar.add(newGame);    
            
            Exit=new JMenuItem("Exit");
            Exit.setBorder(BorderFactory.createLineBorder(Color.black));
            Exit.setBounds(30,0,30,20);
            Exit.setLayout(new GridLayout(4,4));
            Exit.addActionListener((ActionEvent event) -> {System.exit(0);});
            menuBar.add(Exit);
            this.setJMenuBar(menuBar);
            ///////////********/////////////////////////////////////////////////
            /**Creating player's 1 field
            */   
            player1_field.setBounds(0, 0, 1315, 140);
            player1_field.setOpaque(true);
            player1_field.setBackground(Color.WHITE);
            player1_field.setBorder(BorderFactory.createLineBorder(Color.red,3));
            player1INFO=new JLabel("Παίχτης 1 - Διαθέσιμα πιόνια: " + game.P1.pawnsINFO());
            player1INFO.setBounds(10, 105, 600, 50);
            player1_field.add(player1INFO);
            viewwallpic1=new JButton("Οι Τοιχογραφίες μου");
            viewwallpic1.setBounds(1150, 50, 150, 40);
            viewwallpic1.setHorizontalTextPosition(SwingConstants.LEFT); 
            viewwallpic1.addActionListener((ActionEvent e) -> {
                    createWallPicturesFrame(1);
            });
            player1_field.add(viewwallpic1);



            imageURL=cldr.getResource("findings/snakes.jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(30, 30,image.SCALE_SMOOTH);
            statues1=new JLabel("Αγαλματάκια: "+game.P1.statues.size());
            statues1.setBounds(1155, 80, 150, 60);
            statues1.setIcon(new ImageIcon(image));
            player1_field.add(statues1);
            
            scor1=new JLabel("To Σκορ μου: "+ game.P1.getPoints()+" Πόντοι");
            scor1.setBounds(1155, 20, 150, 20);
            player1_field.add(scor1);       
            
            basic_panel.add(player1_field);
            ///////////********/////////////////////////////////////////////////
            /**Creating player's 2 field
            */   
            player2_field.setBounds(0, 682, 1315, 140);
            player2_field.setOpaque(true);
            player2_field.setBackground(Color.WHITE);
            player2_field.setBorder(BorderFactory.createLineBorder(Color.green,3));
            player2INFO=new JLabel("Παίχτης 2 - Διαθέσιμα πιόνια: "+ game.P2.pawnsINFO());
            player2INFO.setBounds(10, 105, 600, 50);
            player2_field.add(player2INFO);
            viewwallpic2.setBounds(1150, 50, 150, 40);
            viewwallpic2.setHorizontalTextPosition(SwingConstants.LEFT);   
            viewwallpic2.addActionListener((ActionEvent e) -> {
                    createWallPicturesFrame(2);
            });
            player2_field.add(viewwallpic2);
           
            
            imageURL=cldr.getResource("findings/snakes.jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(30, 30,image.SCALE_SMOOTH);
            statues2=new JLabel("Αγαλματάκια: "+game.P2.statues.size());
            statues2.setBounds(1155, 80, 150, 60);
            statues2.setIcon(new ImageIcon(image));
            player2_field.add(statues2);
            
            scor2=new JLabel("To Σκορ μου: "+ game.P1.getPoints()+" Πόντοι");
            scor2.setBounds(1155, 20, 150, 20);
            player2_field.add(scor2);  
            
               l11=new JLabel("Κνωσός");
               l11.setBounds(805, 10, 70, 110);
               l21=new JLabel("Κνωσός");
               l21.setBounds(805, 10, 70, 110);
               l12=new JLabel("Μάλια");
               l12.setBounds(885, 10, 70, 110);
               l22=new JLabel("Μάλια");
               l22.setBounds(885, 10, 70, 110);
               l13=new JLabel("Φαιστός");
               l13.setBounds(965, 10, 70, 110);
               l23=new JLabel("Φαιστός");
               l23.setBounds(965, 10, 70, 110);
               l14=new JLabel("Ζάκρος");
               l14.setBounds(1045, 10, 70, 110);
               l24=new JLabel("Ζάκρος");
               l24.setBounds(1045, 10, 70, 110);
               player1_field.add(l11);
               player2_field.add(l21);
               player1_field.add(l12);
               player2_field.add(l22);
               player1_field.add(l13);
               player2_field.add(l23);
               player1_field.add(l14);
               player2_field.add(l24);

            basic_panel.add(player2_field);
            ///////////********/////////////////////////////////////////////////

            /**Creating everything on BOARD
             */   
            imageURL=cldr.getResource("background.jpg");
            image = new ImageIcon(imageURL).getImage();
            board=new JLayeredPaneImage(image);
            board.setBounds(0, 140, 1315, 582);

            int tempposs=220;
           
            for(int i=0;i<9;i++){
                URL urlK,urlF,urlZ,urlM;
                Image imageK,imageF,imageZ,imageM;
                PathK[i]=new JLabel();
                PathF[i]=new JLabel();
                PathZ[i]=new JLabel();
                PathM[i]=new JLabel();
                PathPOINTS[i]=new JLabel(game.board.PosK[i].getPoints() + " points");
                if(i==8){
                   PathK[i].setBounds(1140, 40, 150, 115);
                   PathF[i].setBounds(1140, 290, 150, 115);
                   PathM[i].setBounds(1140, 165, 150, 115);
                   PathZ[i].setBounds(1140, 410, 150, 115);
                   PathPOINTS[i].setBounds(1140, 5, 100, 50);
                }
                else{
                   PathK[i].setBounds(tempposs, 50, 100, 100);
                   PathF[i].setBounds(tempposs, 300, 100, 100);
                   PathM[i].setBounds(tempposs, 175, 100, 100);
                   PathZ[i].setBounds(tempposs, 420, 100, 100);
                   PathPOINTS[i].setBounds(tempposs, 5, 100, 50);

                }
                tempposs+=115;
                if(i==1 || i==3 || i==5 || i==7){
                    urlK=cldr.getResource("paths/knossos2.jpg");
                    urlF=cldr.getResource("paths/phaistos2.jpg");
                    urlZ=cldr.getResource("paths/zakros2.jpg");
                    urlM=cldr.getResource("paths/malia2.jpg");
                }
                else if (i==8){
                    urlK=cldr.getResource("paths/knossosPalace.jpg");
                    urlF=cldr.getResource("paths/phaistosPalace.jpg");
                    urlZ=cldr.getResource("paths/zakrosPalace.jpg");
                    urlM=cldr.getResource("paths/maliaPalace.jpg");
                }
                else{
                    urlK=cldr.getResource("paths/knossos.jpg");
                    urlF=cldr.getResource("paths/phaistos.jpg");
                    urlZ=cldr.getResource("paths/zakros.jpg");
                    urlM=cldr.getResource("paths/malia.jpg");
                }

                imageK = new ImageIcon(urlK).getImage();
                imageK = imageK.getScaledInstance(PathK[i].getWidth(), PathK[i].getHeight(),imageK.SCALE_SMOOTH);
                imageF = new ImageIcon(urlF).getImage();
                imageF = imageF.getScaledInstance(PathF[i].getWidth(), PathF[i].getHeight(),imageF.SCALE_SMOOTH);
                imageZ = new ImageIcon(urlZ).getImage();
                imageZ = imageZ.getScaledInstance(PathZ[i].getWidth(), PathZ[i].getHeight(),imageZ.SCALE_SMOOTH);
                imageM = new ImageIcon(urlM).getImage();
                imageM = imageM.getScaledInstance(PathM[i].getWidth(), PathM[i].getHeight(),imageM.SCALE_SMOOTH);
                PathK[i].setIcon(new ImageIcon(imageK));
                PathF[i].setIcon(new ImageIcon(imageF));
                PathZ[i].setIcon(new ImageIcon(imageZ));
                PathM[i].setIcon(new ImageIcon(imageM));
                
                PathK[i].add(pawn1[0][i]);
                PathM[i].add(pawn1[1][i]);
                PathF[i].add(pawn1[2][i]);
                PathZ[i].add(pawn1[3][i]);
                
                PathK[i].add(pawn2[0][i]);
                PathM[i].add(pawn2[1][i]);
                PathF[i].add(pawn2[2][i]);
                PathZ[i].add(pawn2[3][i]);
                
                board.add(PathK[i], JLabel.TOP);
                board.add(PathF[i], JLabel.TOP);
                board.add(PathZ[i], JLabel.TOP);
                board.add(PathM[i], JLabel.TOP);
                board.add(PathPOINTS[i], JLabel.TOP);
            }
            
            boardINFO=new JLabel();           
            boardINFO.setBounds(20, 450, 150, 60);
            boardINFO.setOpaque(true);
            boardINFO.setBackground(Color.WHITE);
            boardINFO.setBorder(BorderFactory.createLineBorder(Color.black));
            boardINFO.setText("<html>Available Cards:" + (game.board.deck.size())+
           "<br>Check Points: "+ game.getCheckpoints() +"<br>Turn: Player "+ game.seeTurn()+"</html>");
            board.add(boardINFO, JLabel.TOP);

             checkpoint=new JLabel("Check Point!");
             checkpoint.setBounds(910, 38, 100, 10);
             board.add(checkpoint);
            deckCard.setBounds(50, 280, 100, 150);
            imageURL=cldr.getResource("cards/backCard.jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(deckCard.getWidth(),deckCard.getHeight(),image.SCALE_SMOOTH);
            deckCard.setIcon(new ImageIcon(image));
            deckCard.addActionListener((ActionEvent e) -> {
                game.drawCard();
                clicked=true;
            });
            URL url=null;
            Image img1,img2;
            for (int i=0;i<4;i++){
              if(i==0) url=cldr.getResource("findings/ring.jpg");
              if(i==1) url=cldr.getResource("findings/kosmima.jpg");
              if(i==2) url=cldr.getResource("findings/diskos.jpg");
              if(i==3) url=cldr.getResource("findings/ruto.jpg");
               img1 = new ImageIcon(url).getImage();
               img1 = img1.getScaledInstance(RareFindingsP1[i].getWidth(),RareFindingsP1[i].getHeight(),img1.SCALE_SMOOTH);
               img2 = new ImageIcon(url).getImage();
               img2 = img2.getScaledInstance(RareFindingsP2[i].getWidth(),RareFindingsP2[i].getHeight(),img2.SCALE_SMOOTH);
               RareFindingsP1[i].setIcon(new ImageIcon(img1));
               RareFindingsP2[i].setIcon(new ImageIcon(img2));
               player1_field.add(RareFindingsP1[i]);
               player2_field.add(RareFindingsP2[i]);
               RareFindingsP1[i].setEnabled(false);
               RareFindingsP2[i].setEnabled(false);
            }

            board.add(deckCard, JLabel.TOP);

            basic_panel.add(board , JLayeredPaneImage.DEFAULT_LAYER);
            ///////
            
            pack();
            
        
            
            
         basic_panel.repaint();  
         setVisible(true);
        }    
  
    /**
	 * transformer(mutative):gives a refresh into the card button images
	 * Postcondition: gives a refresh into the card button images 
	 *
     */
     private void initButtons(){
         Image img1=null;
         Image img2=null;
            int tempposs=10;
            for (int i=0;i<8;i++){
                image = new ImageIcon(game.cardurl(game.P1.playersHand.get(i), i+1)).getImage();
                image = image.getScaledInstance(Player1Cards[i].getWidth(), Player1Cards[i].getHeight(),image.SCALE_SMOOTH);
                Player1Cards[i].setIcon(new ImageIcon(image));
                tempposs+=80;
            }
            tempposs=10;
            for (int i=0;i<8;i++){
                image = new ImageIcon(game.cardurl(game.P2.playersHand.get(i), i+1)).getImage();
                image = image.getScaledInstance(Player2Cards[i].getWidth(), Player2Cards[i].getHeight(),image.SCALE_SMOOTH);
                Player2Cards[i].setIcon(new ImageIcon(image));
                tempposs+=80;
            }

            
            
            pack();
            basic_panel.repaint();
     }    
        
    /**
	 * transformer(mutative):gives a refresh into the pawn images
	 * Postcondition: gives a refresh into the pawn images 
	 *
     */
     private void initPawns() throws Exception {
         URL url1[][]=new URL[4][9];
         URL url2[][]=new URL[4][9];
         Image img1[][]=new Image[4][9];
         Image img2[][]=new Image[4][9];
         for(int i=0;i<4;i++){
             for (int j=0;j<9;j++){
                  url1[i][j]=null;
                  url2[i][j]=null;
                  img1[i][j]=null;
                  img2[i][j]=null;
             }
         }
         for(int i=0;i<9;i++){
            
                    if(game.board.PosK[i].getPawn(1)!=null){
                            url1[0][i]=game.PawnURL(game.board.PosK[i].getPawn(1));}
                    //if(game.board.PosK[i].getPawn(1)==null) k=0;
                                     
                                     
                    if(game.board.PosK[i].getPawn(2)!=null){
                            url2[0][i]=game.PawnURL(game.board.PosK[i].getPawn(2));}
                    //if(game.board.PosK[i].getPawn(2)==null)  l=0;

                    
                    if(game.board.PosF[i].getPawn(1)!=null){
                            url1[2][i]=game.PawnURL(game.board.PosF[i].getPawn(1));}
                     //if(game.board.PosF[i].getPawn(1)==null) k=2;
                        
                        
                    if(game.board.PosF[i].getPawn(2)!=null){
                            url2[2][i]=game.PawnURL(game.board.PosF[i].getPawn(2));}
                    //if(game.board.PosF[i].getPawn(2)==null)  l=2;
                    
                            
                    if(game.board.PosZ[i].getPawn(1)!=null){
                            url1[3][i]=game.PawnURL(game.board.PosZ[i].getPawn(1));}
                    //if(game.board.PosZ[i].getPawn(1)==null) k=3;

                            
                    if(game.board.PosZ[i].getPawn(2)!=null){
                            url2[3][i]=game.PawnURL(game.board.PosZ[i].getPawn(2));}
                    //if(game.board.PosZ[i].getPawn(2)==null) l=3;

                    if(game.board.PosM[i].getPawn(1)!=null){
                            url1[1][i]=game.PawnURL(game.board.PosM[i].getPawn(1));}
                            
                    //if(game.board.PosM[i].getPawn(1)==null) k=1;

                    if(game.board.PosM[i].getPawn(2)!=null){
                            url2[1][i]=game.PawnURL(game.board.PosM[i].getPawn(2));}
                            
                    //if(game.board.PosM[i].getPawn(2)==null)  l=1;
                    
         }  
         for (int i=0;i<4;i++){
           for (int j=0;j<9;j++){
              if(url1[i][j]!=null){
                img1[i][j] = new ImageIcon(url1[i][j]).getImage();
                img1[i][j] = img1[i][j].getScaledInstance(pawn1[i][j].getWidth(),pawn1[i][j].getHeight(),img1[i][j].SCALE_SMOOTH);
                pawn1[i][j].setBorder(BorderFactory.createLineBorder(Color.red, 3));
                pawn1[i][j].setIcon(new ImageIcon(img1[i][j]));
              }
              if(url1[i][j]==null){
                pawn1[i][j].setIcon(null);
                pawn1[i][j].setBorder(null);
              }
              
              if(url2[i][j]!=null){
                img2[i][j] = new ImageIcon(url2[i][j]).getImage();
                img2[i][j] = img2[i][j].getScaledInstance(pawn2[i][j].getWidth(),pawn2[i][j].getHeight(),img2[i][j].SCALE_SMOOTH);
                pawn2[i][j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                pawn2[i][j].setIcon(new ImageIcon(img2[i][j]));
              }
              if(url2[i][j]==null){
                pawn2[i][j].setIcon(null);
                pawn2[i][j].setBorder(null);

              }
          }
         }
                pack();
                basic_panel.repaint();
        
         
   }
    /**
	 * transformer(mutative):gives a refresh into the last card played images
	 * Postcondition: gives a refresh into the last card played images 
	 *
     */
     private void initLastcards() throws Exception {
            int tempposs=800;
            for(int i=0;i<4;i++){
               URL url1=null;
               URL url2=null;
               Image img1,img2;
               if(i==0){
                   lastcards1[i].setBorder(BorderFactory.createLineBorder(Color.red ,3));
                   lastcards2[i].setBorder(BorderFactory.createLineBorder(Color.red ,3));
                   if(game.P1.getLastNumberCard(PlaceType.KNOSOS)!=null)
                      url1=game.numurl(PlaceType.KNOSOS, game.P1.getLastNumberCard(PlaceType.KNOSOS).getNumber());
                   if(game.P2.getLastNumberCard(PlaceType.KNOSOS)!=null)
                      url2=game.numurl(PlaceType.KNOSOS, game.P2.getLastNumberCard(PlaceType.KNOSOS).getNumber());
               }
               if(i==1){
                   lastcards1[i].setBorder(BorderFactory.createLineBorder(Color.orange,3));
                   lastcards2[i].setBorder(BorderFactory.createLineBorder(Color.orange,3));
                   if(game.P1.getLastNumberCard(PlaceType.MALIA)!=null)
                      url1=game.numurl(PlaceType.MALIA, game.P1.getLastNumberCard(PlaceType.MALIA).getNumber());
                   if(game.P2.getLastNumberCard(PlaceType.MALIA)!=null)
                      url2=game.numurl(PlaceType.MALIA, game.P2.getLastNumberCard(PlaceType.MALIA).getNumber());
               }
               if(i==2){
                   lastcards1[i].setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
                   lastcards2[i].setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
                   if(game.P1.getLastNumberCard(PlaceType.FAISTOS)!=null)
                      url1=game.numurl(PlaceType.FAISTOS, game.P1.getLastNumberCard(PlaceType.FAISTOS).getNumber());
                   if(game.P2.getLastNumberCard(PlaceType.FAISTOS)!=null)
                      url2=game.numurl(PlaceType.FAISTOS, game.P2.getLastNumberCard(PlaceType.FAISTOS).getNumber());
               }
               if(i==3){
                   lastcards1[i].setBorder(BorderFactory.createLineBorder(Color.blue,3));
                   lastcards2[i].setBorder(BorderFactory.createLineBorder(Color.blue,3));
                   if(game.P1.getLastNumberCard(PlaceType.ZAKROS)!=null)
                      url1=game.numurl(PlaceType.ZAKROS, game.P1.getLastNumberCard(PlaceType.ZAKROS).getNumber());
                   if(game.P2.getLastNumberCard(PlaceType.ZAKROS)!=null)
                      url2=game.numurl(PlaceType.ZAKROS, game.P2.getLastNumberCard(PlaceType.ZAKROS).getNumber());
               }
               lastcards1[i].setBounds(tempposs, 10, 70, 110);
               lastcards2[i].setBounds(tempposs, 10, 70, 110);
               if(url1!=null){
                    img1 = new ImageIcon(url1).getImage();
                    img1 = img1.getScaledInstance(lastcards1[i].getWidth(), lastcards1[i].getHeight(),img1.SCALE_SMOOTH);
                    lastcards1[i].setIcon(new ImageIcon(img1));
               }
               if(url2!=null){
                    img2 = new ImageIcon(url2).getImage();
                    img2 = img2.getScaledInstance(lastcards2[i].getWidth(), lastcards2[i].getHeight(),img2.SCALE_SMOOTH);
                    lastcards2[i].setIcon(new ImageIcon(img2));
               }
               tempposs+=80;
            }
            pack();
            basic_panel.repaint();
          }
    /**
	 * transformer(mutative):enables the rare finding button when player gets one
	 * Postcondition: enables the rare finding button when player gets one 
	 *
     */
     private void initRareFindings(){
            if(game.P1.MR!=null) RareFindingsP1[0].setEnabled(true);
            if(game.P2.MR!=null) RareFindingsP2[0].setEnabled(true);
            
            if(game.P1.MJ!=null) RareFindingsP1[1].setEnabled(true);
            if(game.P2.MJ!=null) RareFindingsP2[1].setEnabled(true);
            
            if(game.P1.DF!=null) RareFindingsP1[2].setEnabled(true);
            if(game.P2.DF!=null) RareFindingsP2[2].setEnabled(true);
            
            if(game.P1.RZ!=null) RareFindingsP1[3].setEnabled(true);
            if(game.P2.RZ!=null) RareFindingsP2[3].setEnabled(true);
     }
     
    /**
	 * transformer(mutative):creates the wall pictures one a new frame as buttons and 
         * enables them when player get them.
	 * Postcondition: creates the wall pictures one a new frame as buttons and 
         * enables them when player get them.
     */
     public  void createWallPicturesFrame(int player){
              URL url=null;
              Image img=null;
              if (player==1) wallpictures.setTitle("Τοιχογραφίες Παίχτης 1");
              if (player==2) wallpictures.setTitle("Τοιχογραφίες Παίχτης 2");
              wallpictures.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
               wallpictures.setBounds(400,180, 600, 600);
                 wallpictures.setVisible(true);
            String path=null;
            int tempnumX=0;
            int tempnymY=0;
            int tempWidth=400;
            for(int i=0;i<6;i++){
                if(i==3){ 
                    tempWidth=200;
                     tempnumX=400;
                     tempnymY=0;
                }
                if(i==5)  pictures[i].setBounds(400, 400, 200, 200);
                else pictures[i].setBounds(tempnumX, tempnymY, tempWidth, 200);

                tempnymY+=200;
                path="findings/fresco"+(i+1)+".jpg";
                url=cldr.getResource("findings/fresco"+(i+1)+".jpg");
                img = new ImageIcon(url).getImage();
                if (i==5) img = img.getScaledInstance(600, 600,img.SCALE_SMOOTH);
                else img = img.getScaledInstance(pictures[i].getWidth(), pictures[i].getHeight(),img.SCALE_SMOOTH);
                pictures[i].setIcon(new ImageIcon(img));
                pictures[i].setEnabled(false);
            }
     
              if (player==1) {
                  for(int i=0;i<(game.P1.wallpictures.size());i++)
                      pictures[game.P1.wallpictures.get(i).getInt()-1].setEnabled(true);
              }
              if (player==2) {
                  for(int i=0;i<(game.P2.wallpictures.size());i++)
                      pictures[game.P2.wallpictures.get(i).getInt()-1].setEnabled(true);
              }
     
     }
    /**
	 * transformer(mutative):implement method from action listener
	 * Postcondition: implement method from action listener.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
           for (int i=0;i<8;i++){
                 if(e.getSource() == Player2Cards[i] ||  e.getSource() == Player1Cards[i]){
                     try {
                         System.out.println("Player "+game.seeTurn()+" play:");
                        clicked= game.playCard(game.ChooseCardtoPlay(i), i);
                     } catch (Exception ex) {
                         Logger.getLogger(GraphicUI.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }   
            }
            basic_panel.repaint();
    }
   

}
