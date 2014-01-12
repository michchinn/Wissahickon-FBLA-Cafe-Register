 
/**
 * Wissahickon High School Cafe Register
 * Created by Mayank Makwana and Pratik Kunapuli
 */
 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.sound.midi.SysexMessage;
import javax.swing.BoxLayout;
import javax.swing.table.*;
import java.text.*;
 
public class CafeRegister extends JFrame
{
    private static JFrame mainFrame;
    private static JFrame registerFrame;
    private static JTextArea textarea ;
    private static JTextField textfield;
    private static JScrollPane scroll;
    private static ArrayList<String> registerText;
    private static ArrayList<String> registerPrice;
    private static StringTokenizer tokenizer;
    String passkey = new String();
     
     
    public static void main(String[] args) {
    	CafeRegister fullProgram =new CafeRegister();
        fullProgram.createAndShowGUI(); // launch program
    }
     
    public void createAndShowGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
         
         
        //Create and set up the window.
        mainFrame = new JFrame("Login");
        registerFrame = new JFrame ("Register");
         
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        mainFrame.setPreferredSize(screenSize);
        registerFrame.setPreferredSize(screenSize);
        registerFrame.setMaximumSize(screenSize);
         
        JPanel login = new JPanel();
        addLogin(login);
         
        JPanel food = new JPanel();
        addRegister(food);
         
         
        mainFrame.add(login);
        registerFrame.add(food);
        //Create and set up the content pane.
         
     //   windowListen listener = new windowListen();
         
      //  registerFrame.addWindowListener(listener);
         
         
        textarea = new JTextArea(10, 20);
      
        scroll = new JScrollPane(textarea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         
        registerText = new ArrayList <String>();
        registerPrice = new ArrayList<String>();
         
       
         
        registerFrame.add(scroll, BorderLayout.WEST);
         
         
        //Display the window.
        mainFrame.pack();
        registerFrame.pack();
         
        mainFrame.setVisible(true);
        registerFrame.setVisible(false);
        
         windowListen listener = new windowListen();
        registerFrame.addWindowListener(listener);
    }
     
    JLabel l_name,l_pass;
    JTextField t_name;
    JPasswordField t_pass;     //A special JTextField but hides input text
    JButton button;
    boolean checkLogin = false;
    boolean allowPass = false;
     
    //a inner class to handling ActionEvents
    handler handle;
     
    //a separate class for processing database connection and authentication
    database db;
     
    private JPanel userInterface = new JPanel (new CardLayout());
    private JPanel mainPane = new JPanel();
    private JPanel registerPane = new JPanel();
    private java.util.List<String> pages;
    private int currentPage;
     
     
    private void addLogin(JPanel pane)
    {
        JPanel pane2 = new JPanel();
         
        db=new database();
        handle =new handler();
        //swing components
        //l_name=new JLabel("Username");
        l_pass=new JLabel("Password");
        //t_name=new JTextField(10);
        t_pass=new JPasswordField(10);
        button=new JButton("Login");
         
        //adding actionlistener to the button
        button.addActionListener(handle);
         
        //add to contaienr
        // c.add(l_name);
        //c.add(t_name);
        pane2.setLayout(new GridBagLayout());
        pane2.add(l_pass);
        pane2.add(t_pass);
        pane2.add(button);
         
        pane.add(pane2);
         
    }
     
    class handler implements ActionListener        //This is triggered whenever the user clicks the login button
    {
        public void actionPerformed(ActionEvent ae) //checks if the button clicked
        {
            if(ae.getSource()==button)
            {
                char[] temp_pwd=t_pass.getPassword();
                String convert = t_pass.getText();
                 
                int passID = Integer.parseInt(convert);
                 
                //The entered username and password are sent via "checkLogin()" which return boolean
                if(db.checkLogin(passID))
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "You have logged in successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    checkLogin = true;
                    allowPass = true;
                    passkey = convert;
                    mainFrame.setVisible(false);
                    registerFrame.setVisible(true);
                    // call new metho to launch program
                    //   setCurrentPage(getCurrentPage() + 1);
                }
                else
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "Login failed!","Failed!!", JOptionPane.ERROR_MESSAGE);
                    checkLogin = false;
                }
            }//if
        }//method
         
    }//inner class
     
     
     
     
    private void addRegister(JPanel pane) {
         
        JPanel everythingPane = new JPanel();
        JPanel pluPane = new JPanel();
        double[] prices = new double[20];
         
        Dimension button = new Dimension(200,150);
         
        JTable pluTable = new JTable();
         
         
        JPanel buttonPane = new JPanel();
         
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
         
        //JPanel buttonPane = new JPanel (new FlowLayout (FlowLayout.LEFT));
         
        JPanel subPane1 = new JPanel();
        JPanel subPane2 = new JPanel();
        JPanel subPane3 = new JPanel();
        JPanel subPane4 = new JPanel();
        JPanel subPane5 = new JPanel();
 
         
        buttonListener listener = new buttonListener();
         
        button appleJ = new button("Apple Juice: \t $1.00");
        appleJ.thisButton.addActionListener(listener);
        appleJ.thisButton.setPreferredSize(button);
 
        button bagel = new button("S/E/C Bagel: \t\t $2.50");
        bagel.thisButton.addActionListener(listener);
        bagel.thisButton.setPreferredSize(button);
         
        button blueMuffin = new button("Blueberry Muffin: \t $1.50");
        blueMuffin.thisButton.addActionListener(listener);
        blueMuffin.thisButton.setPreferredSize(button);
         
        button chocolateMuffin = new button("Chocolate Muffin: \t $1.50");
        chocolateMuffin.thisButton.addActionListener(listener);
        chocolateMuffin.thisButton.setPreferredSize(button);
         
        button coffeeMuffin = new button("Coffee Cake Muffin: \t $1.50");
        coffeeMuffin.thisButton.addActionListener(listener);
        coffeeMuffin.thisButton.setPreferredSize(button);
         
        button cafeLatte = new button("Cafe Latte: \t\t $1.50");
        cafeLatte.thisButton.addActionListener(listener);
        cafeLatte.thisButton.setPreferredSize(button);
         
        button cafeMocha = new button("Cafe Mocha: \t\t $1.50");
        cafeMocha.thisButton.addActionListener(listener);
        cafeMocha.thisButton.setPreferredSize(button);
         
        button cappuccino = new button("Cappuccino: \t\t $1.50");
        cappuccino.thisButton.addActionListener(listener);
        cappuccino.thisButton.setPreferredSize(button);
         
        button coffee = new button("Coffee: \t\t $1.50");
        coffee.thisButton.addActionListener(listener);
        coffee.thisButton.setPreferredSize(button);
         
        button frenchCappuccino = new button("FV Cappuccino: \t $1.50");
        frenchCappuccino.thisButton.addActionListener(listener);
        frenchCappuccino.thisButton.setPreferredSize(button);
         
        button frenchLatte = new button("FV Latte: \t\t $1.50");
        frenchLatte.thisButton.addActionListener(listener);
        frenchLatte.thisButton.setPreferredSize(button);
         
        button frenchMocha = new button("FV Mocha: \t\t $1.50");
        frenchMocha.thisButton.addActionListener(listener);
        frenchMocha.thisButton.setPreferredSize(button);
         
        button hotChocolate = new button("Hot Chocolate: \t $1.50");
        hotChocolate.thisButton.addActionListener(listener);
        hotChocolate.thisButton.setPreferredSize(button);
         
        button hotTea = new button("Hot Tea: \t\t $1.00");
        hotTea.thisButton.addActionListener(listener);
        hotTea.thisButton.setPreferredSize(button);
         
        button pumpkinLatte = new button("Pumpkin Latte: \t $1.50");
        pumpkinLatte.thisButton.addActionListener(listener);
        pumpkinLatte.thisButton.setPreferredSize(button);
      
        button pumpkinMocha = new button("Pumpkin Mocha: \t $1.50");
        pumpkinMocha.thisButton.addActionListener(listener);
        pumpkinMocha.thisButton.setPreferredSize(button);
   
        button pumpkinSpice = new button("Pumpkin Spice: \t $1.50");
        pumpkinSpice.thisButton.addActionListener(listener);
        pumpkinSpice.thisButton.setPreferredSize(button);
         
        button water = new button("Water: \t\t $1.00");
        water.thisButton.addActionListener(listener);
        water.thisButton.setPreferredSize(button);
        
        button gatorade = new button("Gatorade: \t\t $1.50");
        gatorade.thisButton.addActionListener(listener);
        gatorade.thisButton.setPreferredSize(button);
        
        button poptart = new button("Poptart: \t\t $1.00");
        poptart.thisButton.addActionListener(listener);
        poptart.thisButton.setPreferredSize(button);
        
        button crystal = new button("Crystal Light: \t\t $0.50");
        crystal.thisButton.addActionListener(listener);
        crystal.thisButton.setPreferredSize(button);
        
        button gogurt = new button("Gogurt: \t\t $0.75");
        gogurt.thisButton.addActionListener(listener);
        gogurt.thisButton.setPreferredSize(button);
        
        button orangeJ = new button("Orange Juice: \t\t $1.00");
        orangeJ.thisButton.addActionListener(listener);
        orangeJ.thisButton.setPreferredSize(button);
        
        button creamBagel = new button("Cream Cheese Bagel: \t $1.25");
        creamBagel.thisButton.addActionListener(listener);
        creamBagel.thisButton.setPreferredSize(button);
        
        button gum = new button("Gum: \t $1.00");
        gum.thisButton.addActionListener(listener);
        gum.thisButton.setPreferredSize(button);
        
        subPane1.add(appleJ.thisButton);
        subPane1.add(bagel.thisButton);
        subPane1.add(blueMuffin.thisButton);
        subPane1.add(chocolateMuffin.thisButton);
        subPane1.add(gum.thisButton);
         
        subPane2.add(coffeeMuffin.thisButton);
        subPane2.add(orangeJ.thisButton);
        subPane2.add(creamBagel.thisButton);
        subPane2.add(cafeLatte.thisButton);
        subPane2.add(cafeMocha.thisButton);
        
        subPane3.add(cappuccino.thisButton);
        subPane3.add(coffee.thisButton);
        subPane3.add(frenchCappuccino.thisButton);
        subPane3.add(frenchLatte.thisButton);
        subPane3.add(frenchMocha.thisButton); 
        
        subPane4.add(hotChocolate.thisButton);
        subPane4.add(hotTea.thisButton);
        subPane4.add(pumpkinLatte.thisButton);
        subPane4.add(pumpkinMocha.thisButton);
        subPane4.add(pumpkinSpice.thisButton);
        
        subPane5.add(water.thisButton);
        subPane5.add(gatorade.thisButton);
        subPane5.add(poptart.thisButton);
        subPane5.add(crystal.thisButton);
        subPane5.add(gogurt.thisButton);
        
        buttonPane.add(subPane1);
        buttonPane.add(subPane2);
        buttonPane.add(subPane3);
        buttonPane.add(subPane4);
        buttonPane.add(subPane5);
        buttonPane.add(Box.createRigidArea(new Dimension(5,100)));
        
       JButton voidButton = new JButton("Void Last Item");
       
       voidButtonListener voidListener = new voidButtonListener();
       
       voidButton.addActionListener(voidListener);
             
        voidButton.setSize(250, 100);
        pluPane.add(voidButton);
         
        pluPane.add(pluTable);
         
         
         
        everythingPane.add(pluPane);
        everythingPane.add(buttonPane);
         
        pane.add(everythingPane);
         }
     
     
    public class buttonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
         //   String buttonText = ( (JButton) e.getSource() ).getText());
             
      //      System.out.println(e.getActionCommand());
            
            registerText.add(e.getActionCommand() + "\n");
            
            textarea.append(e.getActionCommand() + "\n");  // adds to register
            
            
            System.out.println("Register Text Size: " +registerText.size());
             
           // if( e.getActionCommand() == null )
                //throw new UnsupportedOperationException("Not supported yet.");
        }
         
    }
     
    public class button
    {
        private String name;
        JButton thisButton;
        public button (String inputName)
        {
            name = inputName;
            thisButton = new JButton (name);
        }
    }
     
    public class voidButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //System.out.println(textarea.getRows());
            textarea.setText(null);
            String lItem = new String();
            lItem = registerText.get(registerText.size() - 1);
            
            
     //       System.out.println("Last Item: " + lItem);

            registerText.remove(lItem);
            
            for (int i =0; i <registerText.size(); i++)
            {
               textarea.append(registerText.get(i));
            }
        }
    }
     
     
    public class windowListen implements WindowListener 
    { 
        
         
         
        private BufferedWriter out;
 
        public void windowClosing(WindowEvent e)
        {
            String price;
            double total = 0;
            //String path =  new String ("C:\\Users\\15pkunapuli\\Desktop\\PLU Reports\\"); // WINDOWS
            String path = new String ("/Users/mayankmmmx/Desktop/"); // MAC
                
            DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yy");
            DateFormat dateFormat2 = new SimpleDateFormat("hh");
            Date date = new Date();
            String timeOfDay; 
            int hour = Integer.parseInt(dateFormat2.format(date));
            
            if(hour < 12)
            	timeOfDay = "Morning";      
            else
            	timeOfDay = "Afternoon";
            
            for (int i=0; i < registerText.size(); i++)
            {
                tokenizer = new StringTokenizer(registerText.get(i), "$");
                tokenizer.nextToken();
                price = tokenizer.nextToken();
                registerPrice.add(price);
            }
              
            try
            { 
                    System.out.println("path: " + path + "");
                    String fullPath = path + dateFormat.format(date) + " " + timeOfDay + ".txt";
                     
                    out = new BufferedWriter(new FileWriter(fullPath)); 
                    out.flush();
                    
                /*    int numberToGo = registerText.size() - 1;
                    
                    for(int a=0; a < numberToGo ; a++)
                    {
                        
                        System.out.println(registerText.size());
                        out.write(registerText.get(a));
                        //out.newLine();
                        total += Double.parseDouble(registerPrice.get(a));
                        System.out.println("Output stuff: " + registerText.get(a) + "     a:   " + a);
                
                    }
                    */
                    
                    int position = 0; 
                    int runThroughValue = registerText.size();
                    
     
               
                    for(position = 0; position < registerPrice.size(); position++ )
                    {
                        //System.out.println(registerText.size());
                        out.write(registerText.get(position));
                        //System.out.println(registerText.get(position));
                        //out.newLine();
                        total += Double.parseDouble(registerPrice.get(position));
                                                
                        //System.out.println("Output stuff: " + registerText.get(position) + "     a:   " + position);
                        
                    }
                    
 
                    out.write("\n\nTotal: " +total);
                    out.write("\n\nID: " +passkey);
                    out.close();
                    
                    System.out.println("The data was successfully exported."); 
            
            } catch (IOException exception) {System.out.println();exception.printStackTrace();} 
        } 
        public void windowActivated(WindowEvent arg0) {} 
           
        public void windowClosed(WindowEvent arg0) 
        {
             
        } 
           
        public void windowDeactivated(WindowEvent arg0) {} 
           
        public void windowDeiconified(WindowEvent arg0) {} 
           
        public void windowIconified(WindowEvent arg0){} 
   
        public void windowOpened(WindowEvent arg0) {} 
           
    } 
}