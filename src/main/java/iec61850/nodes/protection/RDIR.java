package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACD;

import static iec61850.objects.protection.dir.Direction.BACKWARD;
import static iec61850.objects.protection.dir.Direction.FORWARD;

public class RDIR extends LN {

    private ACD Dir = new ACD();

    private WYE W = new WYE();

    public RDIR() {
        Dir.getDirGeneral().setValue(FORWARD);
        Dir.getDirPhsA().setValue(FORWARD);
        Dir.getDirPhsB().setValue(FORWARD);
        Dir.getDirPhsC().setValue(FORWARD);
    }

    @Override
    public void process() {

        if (W.getPhsA().getcVal().getMag().getF().getValue() < 0) {
            Dir.getDirPhsA().setValue(BACKWARD);
        }

        if (W.getPhsB().getcVal().getMag().getF().getValue() < 0) {
            Dir.getDirPhsB().setValue(BACKWARD);
        }

        if (W.getPhsC().getcVal().getMag().getF().getValue() < 0) {
            Dir.getDirPhsC().setValue(BACKWARD);
        }

        if (Dir.getDirPhsA().getValue().ordinal() != 1 || Dir.getDirPhsB().getValue().ordinal() != 1 || Dir.getDirPhsC().getValue().ordinal() != 1) {
            Dir.getDirGeneral().setValue(BACKWARD);
        }

    }

    public ACD getDir() {
        return Dir;
    }

    public void setDir(ACD dir) {
        Dir = dir;
    }

    public WYE getW() {
        return W;
    }

    public void setW(WYE w) {
        W = w;
    }
}
