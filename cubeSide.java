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