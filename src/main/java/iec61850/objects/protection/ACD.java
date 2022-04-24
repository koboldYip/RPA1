package iec61850.objects.protection;

import iec61850.objects.protection.dir.Direction;
import iec61850.objects.samples.Attribute;

/**
 * Сведения о направлении срабатывания защиты
 */
public class ACD {

    private Attribute<Direction> dirGeneral = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsA = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsB = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsC = new Attribute<>(Direction.UNKNOWN);

    public Attribute<Direction> getDirGeneral() {
        return dirGeneral;
    }

    public void setDirGeneral(Attribute<Direction> dirGeneral) {
        this.dirGeneral = dirGeneral;
    }

    public Attribute<Direction> getDirPhsA() {
        return dirPhsA;
    }

    public void setDirPhsA(Attribute<Direction> dirPhsA) {
        this.dirPhsA = dirPhsA;
    }

    public Attribute<Direction> getDirPhsB() {
        return dirPhsB;
    }

    public void setDirPhsB(Attribute<Direction> dirPhsB) {
        this.dirPhsB = dirPhsB;
    }

    public Attribute<Direction> getDirPhsC() {
        return dirPhsC;
    }

    public void setDirPhsC(Attribute<Direction> dirPhsC) {
        this.dirPhsC = dirPhsC;
    }
}
