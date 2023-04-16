/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Minijuegos.Ordenamiento.Mini_Game;

import Minijuegos.Ordenamiento.Views.Init;
import java.io.IOException;

/**
 *
 * @author Alisser
 */
public class Mini_Game {
    public Mini_Game(String[] args) throws IOException {
        Init initWindow = new Init();
        initWindow.setVisible(true);
        initWindow.setLocationRelativeTo(null);
    }
}
