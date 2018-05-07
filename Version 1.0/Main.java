package my.projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import my.projects.Activities.NewProject;
import my.projects.Activities.NewVersion;
import my.projects.Activities.Show;

public class Main extends JFrame{
    
    private JPanel PTitle,PNewVersion,PNewProject,PShow;
    private JLabel Title;
    private JButton NewProject,NewVersion,Show;
    private Box Big_Box,Small_Box;
    
    private Consts Const=new Consts();
    
    public Main(){
        
        SetFrameAndPanels();
        SetComponents();
        AddComponentsToFrame();
    }

    private void SetFrameAndPanels() {
    
        this.setTitle( "My Projects" );
        this.setSize( 300 , 300 );
        this.setResizable(false);
        this.setLocation( 500 , 250 );
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        PTitle=new JPanel();
        
        PNewVersion=new JPanel();
        
        PNewProject=new JPanel();
        
        PShow=new JPanel();
        
        Small_Box=Box.createVerticalBox();
        Small_Box.setBorder(new EmptyBorder(5,5,5,5));
        
        Big_Box=Box.createVerticalBox();
        Big_Box.setBorder(new EmptyBorder(5,5,5,5));
    }
    private void SetComponents() {
    
        Title=new JLabel();
        Title.setText( Const.Greeting() );
        Title.setForeground(Color.red);
        
        NewVersion=new JButton();
        NewVersion.setText("New Version");
        NewVersion.addActionListener(new NewVersionAction());
        
        NewProject=new JButton();
        NewProject.setText("New Project");
        NewProject.addActionListener(new NewProjectAction());
        
        Show=new JButton();
        Show.setText("Show My Projects");
        Show.addActionListener(new ShowAction());
    }
    private void AddComponentsToFrame() {
    
        PTitle.add(Title);
        
        PNewProject.add(NewProject);
        PNewVersion.add(NewVersion);
        PShow.add(Show);
        
        Small_Box.add(PNewVersion);
        Small_Box.add(Box.createVerticalStrut(3));
        Small_Box.add(PNewProject);
        Small_Box.add(Box.createVerticalStrut(3));
        Small_Box.add(PShow);
        
        Big_Box.add(PTitle);
        Big_Box.add(Small_Box);
        
        this.add(Big_Box,BorderLayout.CENTER);
    }
    
    
    private class NewProjectAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            Main.this.dispose();
            new NewProject();
        }
    }
    private class NewVersionAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            Main.this.dispose();
            new NewVersion();
        }
    }
    private class ShowAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
            Main.this.dispose();
            new Show();
        }
    }
}