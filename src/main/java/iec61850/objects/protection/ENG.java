package iec61850.objects.protection;

import iec61850.objects.protection.dir.Direction;
import iec61850.objects.samples.Attribute;

public class ENG {

    private Attribute<Direction> dir = new Attribute<>(Direction.UNKNOWN);

    public Attribute<Direction> getDir() {
        return dir;
    }

    public void setDir(Attribute<Direction> dir) {
        this.dir = dir;
    }
}
