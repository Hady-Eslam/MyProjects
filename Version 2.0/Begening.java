package my.projects;

import java.awt.EventQueue;

public class Begening {

    public static void main(String[] args) {
    
        EventQueue.invokeLater( () -> {
            new Main();
        });
    }
}