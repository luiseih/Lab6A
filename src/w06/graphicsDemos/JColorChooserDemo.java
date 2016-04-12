package w06.graphicsDemos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JColorChooserDemo extends JFrame implements ActionListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;

    private Color sampleColor = Color.LIGHT_GRAY; //initial sync with getContentPane( ).setBackground(sampleColor); below


    public static void main(String[] args){
        JColorChooserDemo gui = new JColorChooserDemo( );
        gui.setVisible(true);
    }

    public JColorChooserDemo( ){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane( ).setBackground(sampleColor); //initial sync with private Color sampleColor = Color.LIGHT_GRAY; above
        setLayout(new BorderLayout( ));
        setTitle("JColorChooser Demo");
        setSize(WIDTH, HEIGHT);

        JPanel buttonPanel = new JPanel( );
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout( ));

        JButton chooseButton = new JButton("Choose a Color");
        chooseButton.addActionListener(this);
        buttonPanel.add(chooseButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand( ).equals("Choose a Color")){
            //feed it current value of sample colour then reset with JColorChooser.showDialog's return value
            sampleColor = JColorChooser.showDialog(this, "JColorChooser", sampleColor);
            if (sampleColor != null)//If a color was chosen (more precisely OK clicked not CANCEL)
                getContentPane( ).setBackground(sampleColor);
        }
        else 
            System.out.println("Unanticipated Error");
    }
}
