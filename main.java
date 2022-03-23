
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

class cubeSide {
    public cubeSide () {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                tiles[i][j]=cubecolor.white;
            }
        }
    }
    public cubecolor[][] tiles=new cubecolor[3][3];
    public cubeSide turnClockwise() {
        cubecolor[][] tmp = 
        {
            {tiles[0][2],tiles[1][2],tiles[2][2]},
            {tiles[0][1],tiles[1][1],tiles[2][1]},
            {tiles[0][0],tiles[1][0],tiles[2][0]}
        };
        tiles = tmp;
        return this;
    }   
    public cubeSide turnCounterClockwise() {
        cubecolor[][] tmp =
        {
            {tiles[2][0],tiles[1][0],tiles[0][0]},
            {tiles[2][1],tiles[1][1],tiles[0][1]},
            {tiles[2][2],tiles[1][2],tiles[0][2]}
        };
        tiles = tmp;
        return  this;
    }
    public String toString() {
        String r="";
        r += "[" + tiles[2][0].toChar() + "]";
        r += "[" + tiles[2][1].toChar() + "]";
        r += "[" + tiles[2][2].toChar() + "]\r\n";
        r += "[" + tiles[1][0].toChar() + "]";
        r += "[" + tiles[1][1].toChar() + "]";
        r += "[" + tiles[1][2].toChar() + "]\r\n";
        r += "[" + tiles[0][0].toChar() + "]";
        r += "[" + tiles[0][1].toChar() + "]";
        r += "[" + tiles[0][2].toChar() + "]\r\n";
        return r;
    }
}

class cube {
    public cubeSide[] sides = new cubeSide[6];
    public cube () {
        for (int i=0; i<sides.length;i++) {
            sides[i]=new cubeSide();
        }
    }
    //0=Up; 1=Front; 2=Left; 3=Right; 4=Back; 5=Down
    public void turnClockwise() {
        cubeSide[] tmp = new cubeSide[6];
        for (int i=0;i<6;i++) {
            tmp[i] = new cubeSide();
        }
        tmp[side.UP.ordinal()]=sides[side.UP.ordinal()].turnClockwise();
        tmp[side.FRONT.ordinal()]=sides[side.RIGHT.ordinal()];
        tmp[side.LEFT.ordinal()]=sides[side.FRONT.ordinal()];
        tmp[side.RIGHT.ordinal()]=sides[side.BACK.ordinal()];
        tmp[side.BACK.ordinal()]=sides[side.LEFT.ordinal()];
        tmp[side.DOWN.ordinal()]=sides[side.DOWN.ordinal()].turnCounterClockwise();
        sides=tmp;
    }
    public void turnCounterClockwise() {
        cubeSide[] tmp = new cubeSide[6];
        tmp[side.UP.ordinal()]=sides[side.UP.ordinal()].turnCounterClockwise();
        tmp[side.FRONT.ordinal()]=sides[side.LEFT.ordinal()];
        tmp[side.LEFT.ordinal()]=sides[side.BACK.ordinal()];
        tmp[side.RIGHT.ordinal()]=sides[side.FRONT.ordinal()];
        tmp[side.BACK.ordinal()]=sides[side.RIGHT.ordinal()];
        tmp[side.DOWN.ordinal()]=sides[side.DOWN.ordinal()].turnClockwise();
        sides=tmp;
    }
    public void flipCube() {
        cubeSide[] tmp = new cubeSide[6];
        tmp[side.LEFT.ordinal()]=sides[side.LEFT.ordinal()].turnCounterClockwise();
        tmp[side.RIGHT.ordinal()]=sides[side.RIGHT.ordinal()].turnClockwise();
        tmp[side.BACK.ordinal()]=sides[side.UP.ordinal()].turnClockwise().turnClockwise();
        tmp[side.FRONT.ordinal()]=sides[side.DOWN.ordinal()].turnClockwise().turnClockwise();
        tmp[side.UP.ordinal()]=sides[side.FRONT.ordinal()];
        tmp[side.DOWN.ordinal()]=sides[side.BACK.ordinal()];
        sides=tmp;
    }
    public edge[] getEdges(cubecolor c) {
        edge[] tmp = new edge[4];
        int f=0;
        for (int s_i=0; s_i < 6; s_i++) {
            for (int ix=0; ix<3;ix++) {
                for (int iy=0; iy<3;iy++) {
                    if (sides[s_i].tiles[ix][iy]==c) {
                        
                    }
                }
            }
        }
        return tmp;
    } 
    private int getNeighbor(int i, int x, int y) {
        if (i==0) {
            
        }
        return 0;
    }
}

class robot {
    public robot() {}
    public void tableClockwise (int theta) {
        Motor.A.rotate(-3*theta);
    }
    public void tableCounterClockwise (int theta) {
        Motor.A.rotate(3*theta);
    }
    public void flipCube(int c) {
        Motor.C.rotateTo(-135);
        for (int i=0; i<c;i++) {
            Motor.C.rotateTo(-235);
            Motor.C.rotateTo(-135);
        }
        Motor.C.rotateTo(0);
    }
    public void holdCube() {
        Motor.C.rotateTo(-135);
        Motor.C.rotateTo(0);
    }

    public cube scanCube () {
        cube tmp = new cube();
        Motor.B.rotateTo(135);
        for (int i=0; i<4; i++) {
            tmp.sides[side.UP.ordinal()]=scanSide();
            flipCube(1);
            tmp.flipCube();
        }
        tableClockwise(90);
        tmp.turnClockwise();
        flipCube(1);
        tmp.flipCube();
        tmp.sides[side.UP.ordinal()]=scanSide();
        flipCube(2);
        tmp.flipCube();
        tmp.flipCube();
        tmp.sides[side.UP.ordinal()]=scanSide();
        return tmp;
    }

    public cubeSide scanSide () {   
        cubeSide tmp = new cubeSide();     
        for (int e = 0; e<4; e++) {
            Motor.B.rotateTo(195);
            tmp.tiles[0][1]=getColor();
            tableClockwise(45);
            Motor.B.rotateTo(180);
            tmp.tiles[0][2]=getColor();
            tableClockwise(45);
            tmp.turnClockwise();
        }
        Motor.B.rotateTo(235);
        tmp.tiles[1][1]=getColor();
        Motor.B.rotateTo(135);        
        return tmp;
    }

    public cubecolor getColor() {
        ColorSensor colorSensor = new ColorSensor(SensorPort.S3);
        Color cInput = colorSensor.getColor();
        int red = cInput.getRed();
        int green = cInput.getGreen();
        int blue = cInput.getBlue();
        if (Math.abs(red-blue)<10 && Math.abs(red-green)<10) return cubecolor.white;
        else if (red > 2*green && red > 2*blue) return cubecolor.red;
        else if (Math.abs(red-green) < 10 && red > 2*blue) return cubecolor.orange;
        else if (blue > red && blue > green) return cubecolor.blue;
        else if (red > green) return cubecolor.yellow;
        else return cubecolor.green;
    }

}

class edge {
    public edge() {

    }
    public edge(cubecolor main, cubecolor side, int index, int x, int y) {
        this.main = main;
        this.side = side;
    }
    cubecolor main;
    cubecolor side;
    int sideindex;
    int x,y;
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