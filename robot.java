import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
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