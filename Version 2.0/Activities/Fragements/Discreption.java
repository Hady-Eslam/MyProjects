package my.projects.Activities.Fragements;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Discreption extends JFrame{
    
    private JPanel PTitle,PShow;
    private JLabel Title,Show;    
    private String title = "",Text = "";
    
    public Discreption( String title, String Text){
        
        this.title=title;
        this.Text=Text;
        SetComponents();
    }
    
    private void SetComponents() {
    
        this.setTitle( "My Projects" );
        this.setSize( 370 , 280 );
        this.setResizable(false);
        this.setLocation( 420 , 250 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        PTitle.setBorder(new EmptyBorder(5,5,5,5));
        
        PShow=new JPanel();
        PShow.setBorder(new EmptyBorder(5,5,5,5));
        
        Title=new JLabel();
        Title.setText(title);
        Title.setForeground(Color.red);
        
        Show=new JLabel();
        Show.setText( GetText( Text));
        
        PTitle.add(Title);
        
        PShow.add(Show);
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(PShow,BorderLayout.CENTER);
    }
    
    private String GetText( String Text) {
    
        String Get = "<html><body><p>";
        for (int i=0;i<Text.length();i++)
            if ( Text.charAt(i)=='_' )
                Get += "</p><p>";
            else
                Get += Text.charAt(i);
        return ( Get += "</p><body><html>");
    }
}