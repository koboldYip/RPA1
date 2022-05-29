package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;

public class RMXU extends LN {

    private WYE ALoc = new WYE();
    private WYE RstA = new WYE();

    private WYE A1 = new WYE();
    private WYE A2 = new WYE();
    private WYE A3 = new WYE();
    private WYE A4 = new WYE();

    @Override
    public void process() {
        ALoc.getPhsA().getcVal().setByOrt(A1.getPhsA().getcVal().getVectorX().getF().getValue() +
                        A2.getPhsA().getcVal().getVectorX().getF().getValue() +
                        A3.getPhsA().getcVal().getVectorX().getF().getValue() +
                        A4.getPhsA().getcVal().getVectorX().getF().getValue(),
                A1.getPhsA().getcVal().getVectorY().getF().getValue() +
                        A2.getPhsA().getcVal().getVectorY().getF().getValue() +
                        A3.getPhsA().getcVal().getVectorY().getF().getValue() +
                        A4.getPhsA().getcVal().getVectorY().getF().getValue());

        ALoc.getPhsB().getcVal().setByOrt(A1.getPhsB().getcVal().getVectorX().getF().getValue() +
                        A2.getPhsB().getcVal().getVectorX().getF().getValue() +
                        A3.getPhsB().getcVal().getVectorX().getF().getValue() +
                        A4.getPhsB().getcVal().getVectorX().getF().getValue(),
                A1.getPhsB().getcVal().getVectorY().getF().getValue() +
                        A2.getPhsB().getcVal().getVectorY().getF().getValue() +
                        A3.getPhsB().getcVal().getVectorY().getF().getValue() +
                        A4.getPhsB().getcVal().getVectorY().getF().getValue());

        ALoc.getPhsC().getcVal().setByOrt(A1.getPhsC().getcVal().getVectorX().getF().getValue() +
                        A2.getPhsC().getcVal().getVectorX().getF().getValue() +
                        A3.getPhsC().getcVal().getVectorX().getF().getValue() +
                        A4.getPhsC().getcVal().getVectorX().getF().getValue(),
                A1.getPhsC().getcVal().getVectorY().getF().getValue() +
                        A2.getPhsC().getcVal().getVectorY().getF().getValue() +
                        A3.getPhsC().getcVal().getVectorY().getF().getValue() +
                        A4.getPhsC().getcVal().getVectorY().getF().getValue());

        RstA.getPhsA().getcVal().setMag((A1.getPhsA().getcVal().getMag().getF().getValue() +
                A2.getPhsA().getcVal().getMag().getF().getValue() +
                A3.getPhsA().getcVal().getMag().getF().getValue() +
                A4.getPhsA().getcVal().getMag().getF().getValue()) / 2);

        RstA.getPhsB().getcVal().setMag((A1.getPhsB().getcVal().getMag().getF().getValue() +
                A2.getPhsB().getcVal().getMag().getF().getValue() +
                A3.getPhsB().getcVal().getMag().getF().getValue() +
                A4.getPhsB().getcVal().getMag().getF().getValue()) / 2);

        RstA.getPhsC().getcVal().setMag((A1.getPhsC().getcVal().getMag().getF().getValue() +
                A2.getPhsC().getcVal().getMag().getF().getValue() +
                A3.getPhsC().getcVal().getMag().getF().getValue() +
                A4.getPhsC().getcVal().getMag().getF().getValue()) / 2);

    }

    public WYE getALoc() {
        return ALoc;
    }

    public void setALoc(WYE ALoc) {
        this.ALoc = ALoc;
    }

    public WYE getRstA() {
        return RstA;
    }

    public void setRstA(WYE rstA) {
        RstA = rstA;
    }

    public WYE getA1() {
        return A1;
    }

    public void setA1(WYE a1) {
        A1 = a1;
    }

    public WYE getA2() {
        return A2;
    }

    public void setA2(WYE a2) {
        A2 = a2;
    }

    public WYE getA3() {
        return A3;
    }

    public void setA3(WYE a3) {
        A3 = a3;
    }

    public WYE getA4() {
        return A4;
    }

    public void setA4(WYE a4) {
        A4 = a4;
    }

}
