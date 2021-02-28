package com.dropsfarmer;

import com.dropsfarmer.view.MainMenu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        MainMenu m = new MainMenu();
        m.setLocationRelativeTo(null);
        m.setTitle("Twitch !Drops farmer");
        m.setResizable(false);
        m.setVisible(true);
    }
}
