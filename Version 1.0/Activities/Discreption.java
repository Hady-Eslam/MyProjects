package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import my.projects.Consts;

public class Discreption extends JFrame{
    
    private JPanel PTitle,PDiscreption;
    private JLabel Title,Discreption;
    private Box box;
    
    private String title = "",discreption = "";
    private Consts Const=new Consts();
    
    public Discreption(String title,String discreption){
        
        this.title=title;
        this.discreption=discreption;
        
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }

    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 280 , 280 );
        this.setResizable(false);
        this.setLocation( 550 , 250 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        
        PDiscreption=new JPanel();
        
        box=Box.createVerticalBox();
        box.setBorder(new EmptyBorder(3,3,3,3));
    }
    private void SetComponents() {
    
        Title=new JLabel();
        Title.setText(title);
        Title.setFont(Const.TitleFont());
        Title.setForeground(Color.red);
        
        Discreption=new JLabel();
        Discreption.setText(discreption);
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Title);
        
        PDiscreption.add(Discreption);
        
        box.add(PTitle);
        box.add(Box.createVerticalStrut(3));
        box.add(PDiscreption);
        
        this.add(box,BorderLayout.CENTER);
    }
}
