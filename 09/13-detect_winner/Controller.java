/*
 Class Controller - Controls the Roving Robots game (CLI).

 @author      George F. Rice
 @copyright   Copyright 2017-2021
 @version     2.0

 This file is part of Roving Robots.

 Roving Robots is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Roving Robots is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Roving Robots.  If not, see <https://www.gnu.org/licenses/>.
*/ 

class Controller {
    public Controller() {
        grid = new Grid(30);
        view = new View(grid);
    }
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.cli();
    }
    public void cli() {
        char command = '5';
        
        grid.detectCollisions();
        while(command != '0' && grid.playerIsAlive() && grid.anyRobotsAlive()) {
            try {
                view.printGrid();                                 // Show the gameboard
                System.out.print("Command (1 to 9, 0 to exit)? ");
                command = (char)System.in.read();                 // Read the next character
                executeCommand(command);                          // Execute the requested command
                grid.animateRobots();                             // Move robots toward the player
                grid.detectCollisions();                          // Kill any robot that crashes
            } catch(Exception e) {
            }
        }
        view.printFinalGrid();                                    // Show final gameboard with results
    }
    private void executeCommand(char cmd) {
        if (cmd == '1') {grid.movePlayer(Direction.down_left ); }
        if (cmd == '2') {grid.movePlayer(Direction.down      ); }
        if (cmd == '3') {grid.movePlayer(Direction.down_right); }
        if (cmd == '4') {grid.movePlayer(Direction.left      ); }
        if (cmd == '5') {grid.movePlayer(Direction.stay      ); }
        if (cmd == '6') {grid.movePlayer(Direction.right     ); }
        if (cmd == '7') {grid.movePlayer(Direction.up_left   ); }
        if (cmd == '8') {grid.movePlayer(Direction.up        ); }
        if (cmd == '9') {grid.movePlayer(Direction.up_right  ); }
        if (cmd == '.') {grid.teleportPlayer();                 }
    }
    
    private Grid grid;
    private View view;
}
