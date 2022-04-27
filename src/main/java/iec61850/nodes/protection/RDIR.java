package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.SEQ;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACD;
import iec61850.objects.samples.Attribute;

import static iec61850.objects.protection.dir.Direction.BACKWARD;
import static iec61850.objects.protection.dir.Direction.FORWARD;

public class RDIR extends LN {

    private ACD Dir = new ACD();

    private WYE W = new WYE();
    private SEQ A = new SEQ();
    private SEQ V = new SEQ();
    private Attribute<Float> W0 = new Attribute<>(0f);

    public RDIR() {
        Dir.getDirGeneral().setValue(FORWARD);
        Dir.getDirPhsA().setValue(FORWARD);
        Dir.getDirPhsB().setValue(FORWARD);
        Dir.getDirPhsC().setValue(FORWARD);
    }

    @Override
    public void process() {

        W0.setValue(A.getC3().getcVal().getMag().getF().getValue() *
                V.getC3().getcVal().getMag().getF().getValue());

        if (W0.getValue() < 0) {
            Dir.getDirPhsA().setValue(BACKWARD);
        }

        if (W0.getValue() < 0) {
            Dir.getDirPhsB().setValue(BACKWARD);
        }

        if (W0.getValue() < 0) {
            Dir.getDirPhsC().setValue(BACKWARD);
        }

        if (Dir.getDirPhsA().getValue().ordinal() != 1 || Dir.getDirPhsB().getValue().ordinal() != 1 ||
                Dir.getDirPhsC().getValue().ordinal() != 1) {
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

    public SEQ getA() {
        return A;
    }

    public void setA(SEQ a) {
        A = a;
    }

    public SEQ getV() {
        return V;
    }

    public void setV(SEQ v) {
        V = v;
    }

    public Attribute<Float> getW0() {
        return W0;
    }

    public void setW0(Attribute<Float> w0) {
        W0 = w0;
    }
}
