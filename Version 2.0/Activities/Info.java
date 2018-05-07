package my.projects.Activities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import my.projects.Main;

public class Info extends JFrame implements ActionListener{
    
    private JPanel PTitle,PShow;
    private JButton Back;
    private JLabel Show,Title;
    
    public Info(){
        
        SetComponents();
    }
    
    private void SetComponents() {
    
        this.setTitle( "My Projects" );
        this.setSize( 300 , 300 );
        this.setResizable(false);
        this.setLocation( 500 , 250 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        PTitle.setLayout(new BorderLayout());
        PTitle.setBorder(new EmptyBorder(5,5,5,5));
        
        PShow=new JPanel();
        PShow.setBorder(new EmptyBorder(10,10,10,10));
        
        Back=new JButton();
        Back.setText("<<==");
        Back.addActionListener( Info.this );
        
        Title=new JLabel();
        Title.setText(" Information ");
        Title.setHorizontalAlignment(JLabel.CENTER);
        
        Show=new JLabel();
        Show.setText("<html><head><style type=\"text/css\">\n"+
                    "span{color: green;}</style></head><body>\n" +
                    "		<br><br><p style=\"margin-left: 41px\">Author &nbsp;: &nbsp;"+
                                                "<span>Hady Eslam</span></p>\n" +
                    "		<br><p style=\"margin-left: 12px\">Project Name &nbsp;: &nbsp;"+
                                                "<span>My Projects</span></p>\n" +
                    "		<br><p >Version Number &nbsp;: &nbsp;<span>2 . 0</span></p>\n" +
                    "		<br><p style=\"margin-left:5px\">Date Of Version : &nbsp;"+
                                                "<span>30 / 03 / 2018</span></p>\n" +
                    "</body></html>");
        
        PTitle.add(Back,BorderLayout.WEST);
        PTitle.add(Title,BorderLayout.CENTER);
        
        PShow.add(Show);
        
        this.add(PTitle,BorderLayout.NORTH);
        this.add(PShow,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new Main();
    }
}