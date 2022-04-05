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
        //int f=0;
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
}