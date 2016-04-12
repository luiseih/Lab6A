package w06.iconDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IconDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;
    public static final int TEXT_FIELD_SIZE = 30;

    private JTextField message;

    public static void main(String[] args){
        IconDemo iconGui = new IconDemo( );
        iconGui.setVisible(true);
    }


    public IconDemo( ){
        super("Icon Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout( ));

        JLabel dukeLabel = new JLabel("Mood check");
        //to get image reference right click image file in project explorer and select copy reference then paste into code
        ImageIcon dukeIcon = new ImageIcon("C:/Users/shuxford/Documents/Units/FIT2081/IntelliJ Projects/FIT2081Projects/out/production/FIT2081Projects/fit2081/w06/iconDemo/duke_waving.gif");
        dukeLabel.setIcon(dukeIcon);
        add(dukeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel( );
        buttonPanel.setLayout(new FlowLayout( ));
        JButton happyButton = new JButton("Happy");
        ImageIcon happyIcon = new ImageIcon("C:/Users/shuxford/Documents/Units/FIT2081/IntelliJ Projects/FIT2081Projects/out/production/FIT2081Projects/fit2081/w06/iconDemo/smiley.gif");
        happyButton.setIcon(happyIcon);
        happyButton.addActionListener(this);
        buttonPanel.add(happyButton);
        JButton sadButton = new JButton("Sad");
        ImageIcon sadIcon = new ImageIcon("C:/Users/shuxford/Documents/Units/FIT2081/IntelliJ Projects/FIT2081Projects/out/production/FIT2081Projects/fit2081/w06/iconDemo/sad.gif");
        sadButton.setIcon(sadIcon);
        sadButton.addActionListener(this);
        buttonPanel.add(sadButton);
        add(buttonPanel, BorderLayout.SOUTH);

        message = new JTextField(TEXT_FIELD_SIZE);
        add(message, BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e){
       String actionCommand = e.getActionCommand( );

       if (actionCommand.equals("Happy"))
           message.setText("Smile and the world smiles with you!");
       else if (actionCommand.equals("Sad"))
           message.setText("Cheer up. It can't be that bad.");
       else
            message.setText("Unexpected Error.");
    }
}
