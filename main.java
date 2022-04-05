import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;

//A = Table
//B = Color
//C = Arm

//0=Up; 1=Front; 2=Left; 3=Right; 4=Back; 5=Down
//A Rotation ^ 270° ^ 90°
//B Positionen
//Idle      135
//Edge      195
//Corner    180
//Middle    235
//C Positionen 
//Hold      -115
//Drag      -235
class Main {
    static cube cube = new cube();
    public static void main (String[] args) {
        robot nxt = new robot();
        
        cube=nxt.scanCube();
        
        whiteCross();
        Motor.B.rotateTo(0);
        Button.waitForAnyPress();
    }
        
    public static void whiteCross() {
        int indexwhite=-1;
        for (int i=0;i<6;i++) {
            if (cube.sides[i].tiles[1][1]==cubecolor.white) {
                indexwhite=i;
            }
        }

        System.out.println(indexwhite);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            return;
        }
    } 
}

enum cubecolor {
    white,
    red,
    orange,
    blue,
    yellow,
    green;
    public char toChar() {
        switch (this) {
            case white: return 'w';
            case red: return 'r';
            case orange: return 'o';
            case blue: return 'b';
            case yellow: return 'y';
            case green: return 'g';
            default: return 'x';
        }
    }
}
enum side {
    UP,
    FRONT,
    LEFT,
    RIGHT,
    BACK,
    DOWN
}