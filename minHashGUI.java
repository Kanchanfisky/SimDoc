import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Integer;

public class minHashGUI extends JFrame{
    
    //FILE CHOOSER MIGHT NOT BE FINISHING
    
    private JPanel comboPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel bottomPanel;
    
    private JTextArea outputArea;
    private JTextArea inputArea1;
    private JTextArea inputArea2;
    
    private JFileChooser leftChooser;
    private JFileChooser rightChooser;
    
    private JTextField leftFileField;
    private JTextField rightFileField;
    
    private JTextField leftURLField;
    private JTextField rightURLField;
        
    private JButton compareButton;
    
    private JComboBox<String> leftBox;
    private JComboBox<String> rightBox;
    
    private String leftReturn;
    private String rightReturn;
    
    //private File f1;
    
    private Document doc1;
    private Document doc2;
    
    private int leftSelection = 0;
    private int rightSelection = 0;
    
    public minHashGUI(String title){
        super(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setPreferredSize(new Dimension(1050, 550));  
        
        //Add the 4 basic panels of the Border Layout
        SpringLayout sL = new SpringLayout();    //Spring Layout variable
        this.getContentPane().add(comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 370, 10)), BorderLayout.NORTH); 
        this.getContentPane().add(westPanel = new JPanel(new CardLayout()), BorderLayout.LINE_START);
        this.getContentPane().add(eastPanel = new JPanel(new CardLayout()), BorderLayout.LINE_END);
        this.getContentPane().add(bottomPanel = new JPanel(new FlowLayout()), BorderLayout.PAGE_END);
        
        //Setup combo button panel on top
        String[] options = new String[3];
        options[0] = "Enter your own text";
        options[1] = "Read from input file";
        options[2] = "Supply a URL for input";
        
        comboPanel.add(leftBox = new JComboBox<String>(options));
        comboPanel.add(rightBox = new JComboBox<String>(options));
        leftBox.setEditable(false);
        rightBox.setEditable(false);
        
        //Left side cards
        JPanel leftCard1 = new JPanel();
        leftCard1.add(new JScrollPane(inputArea1 = new JTextArea(20, 30)));
        
        JPanel leftCard2 = new JPanel();
        //Failed File Chooser Experiment below
        /*leftCard2.add(leftChooser = new JFileChooser());
        leftChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            
            }
            int returnVal = leftChooser.showOpenDialog(westPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION){
                f1 = leftChooser.getSelectedFile();
            }
        }*/
        leftCard2.add(new JLabel("Enter a local filename: "));
        leftCard2.add(leftFileField = new JTextField(30));        
            
        JPanel leftCard3 = new JPanel();
        JLabel leftURL = new JLabel("Insert a URL: ");
        //JButton leftReadURLButton = new JButton("Read file");
        leftCard3.add(leftURL);
        leftCard3.add(leftURLField = new JTextField(30));
        //leftCard3.add(leftReadURLButton);
        
        //Right side cards
        JPanel rightCard1 = new JPanel();
        rightCard1.add(new JScrollPane(inputArea2 = new JTextArea(20, 30)));
        
        JPanel rightCard2 = new JPanel();
        //rightCard2.add(rightChooser = new JFileChooser());
        rightCard2.add(new JLabel("Enter a local filename: "));
        rightCard2.add(rightFileField = new JTextField(30));
        
        
        JPanel rightCard3 = new JPanel();
        JLabel rightURL = new JLabel("Insert a URL: ");
        //JButton rightReadURLButton = new JButton("Read file");
        rightCard3.add(rightURL);
        rightCard3.add(rightURLField = new JTextField(30));
        //rightCard3.add(rightReadURLButton);
        
        //Left side card inserts
        westPanel.add(leftCard1);
        westPanel.add(leftCard2);
        westPanel.add(leftCard3);

        //Left side default setup
        leftCard1.setVisible(true);
        leftCard2.setVisible(false);
        leftCard3.setVisible(false);
        
        leftBox.addActionListener (new ActionListener () {
        public void actionPerformed(ActionEvent e) {
            String x = leftBox.getSelectedItem().toString();
            if (x.equals(options[0])){
                leftSelection = 0;
                leftCard1.setVisible(true);
                leftCard2.setVisible(false);
                leftCard3.setVisible(false);
            } else if (x.equals(options[1])){
                leftSelection = 1;
                leftCard1.setVisible(false);
                leftCard2.setVisible(true);
                leftCard3.setVisible(false);
            } else if (x.equals(options[2])) {
                leftSelection = 2;
                leftCard1.setVisible(false);
                leftCard2.setVisible(false);
                leftCard3.setVisible(true);
            }
        }
        });
  
        //Right side card insert
        eastPanel.add(rightCard1); 
        eastPanel.add(rightCard2); 
        eastPanel.add(rightCard3);
        
        //Right side default setup
        rightCard1.setVisible(true);
        rightCard2.setVisible(false);
        rightCard3.setVisible(false);
        
        rightBox.addActionListener (new ActionListener() {
           public void actionPerformed(ActionEvent e) {
            String y = rightBox.getSelectedItem().toString();
            if (y.equals(options[0])){
                rightSelection = 0;
                rightCard1.setVisible(true);
                rightCard2.setVisible(false);
                rightCard3.setVisible(false);
            } else if (y.equals(options[1])){
                rightSelection = 1;
                rightCard1.setVisible(false);
                rightCard2.setVisible(true);
                rightCard3.setVisible(false);
            } else if (y.equals(options[2])) {
                rightSelection = 2;
                rightCard1.setVisible(false);
                rightCard2.setVisible(false);
                rightCard3.setVisible(true);
            }
           }            
        });
        
        bottomPanel.add(compareButton = new JButton("Compare"));
        bottomPanel.add(new JScrollPane(outputArea = new JTextArea(10, 25)));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        compareButton.addActionListener(e -> compare());
    }

    private void compare(){
        switch (leftSelection) {
            case 0:
                leftReturn = inputArea1.getText();
                break;
            case 1:
                //leftReturn = f1.getName();
                leftReturn = leftFileField.getText();
                break;
            case 2:
                leftReturn = leftURLField.getText();
                break;
        }
        
        switch (rightSelection) {
            case 0:
                rightReturn = inputArea2.getText();
                break;
            case 1:
                //rightReturn = rightChooser.getSelectedFile().getName();
                rightReturn = rightFileField.getText();
                break;
            case 2:
                rightReturn = rightURLField.getText();
                break;
        }
        doc1 = new Document(leftReturn, leftSelection);
        doc2 = new Document(rightReturn, rightSelection);
        double result = doc1.jaccard(doc2);
        result *= 100;
        outputArea.append("Comparing \"" + leftReturn + "\" and \"" + rightReturn + "\". \n");
        outputArea.append("Percentage similarity: " + result +"%\n");
    }

    public static void main(String[] args){
        minHashGUI myGUI = new minHashGUI("MinHash GUI");
        myGUI.pack();   
        myGUI.setVisible(true);
        Document.makeRand();
        
    }
}