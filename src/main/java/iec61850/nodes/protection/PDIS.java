package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.control.SPS;
import iec61850.objects.measurements.DEL;
import iec61850.objects.protection.ACD;
import iec61850.objects.protection.ACT;
import iec61850.objects.protection.ASG;
import iec61850.objects.protection.ING;

public class PDIS extends LN {

    private DEL Z = new DEL();
    private ACT op = new ACT();
    private ACD str = new ACD();

    private SPS Blk = new SPS();
    private ING OpDlTmms = new ING(10); //Выдержка времени
    private ASG PhStr = new ASG(0f);

    private double R0 = 0;
    private double X0 = 0;
    private int count = 0;

    @Override
    public void process() {

        if (!Blk.getStVal().getValue()) {
            boolean phsAB = Math.pow(Z.getPhsAB().getcVal().getVectorX().getF().getValue() - R0, 2) +
                    Math.pow(Z.getPhsAB().getcVal().getVectorY().getF().getValue() - X0, 2) <=
                    Math.pow(PhStr.getSetMag().getF().getValue(), 2);
            boolean phsBC = Math.pow(Z.getPhsBC().getcVal().getVectorX().getF().getValue() - R0, 2) +
                    Math.pow(Z.getPhsBC().getcVal().getVectorY().getF().getValue() - X0, 2) <=
                    Math.pow(PhStr.getSetMag().getF().getValue(), 2);
            boolean phsCA = Math.pow(Z.getPhsCA().getcVal().getVectorX().getF().getValue() - R0, 2) +
                    Math.pow(Z.getPhsCA().getcVal().getVectorY().getF().getValue() - X0, 2) <=
                    Math.pow(PhStr.getSetMag().getF().getValue(), 2);

            if (phsAB || phsBC || phsCA) {
                count++;
            } else {
                count = 0;
            }

            if (count >= OpDlTmms.getSetVal().getValue()) {
                op.getGeneral().setValue(true);
                op.getPhsA().setValue(phsAB);
                op.getPhsB().setValue(phsBC);
                op.getPhsC().setValue(phsCA);
            }
        }

    }

    public DEL getZ() {
        return Z;
    }

    public void setZ(DEL z) {
        Z = z;
    }

    public ACT getOp() {
        return op;
    }

    public void setOp(ACT op) {
        this.op = op;
    }

    public ACD getStr() {
        return str;
    }

    public void setStr(ACD str) {
        this.str = str;
    }

    public ING getOpDlTmms() {
        return OpDlTmms;
    }

    public void setOpDlTmms(ING opDlTmms) {
        OpDlTmms = opDlTmms;
    }

    public ASG getPhStr() {
        return PhStr;
    }

    public void setPhStr(ASG phStr) {
        PhStr = phStr;
    }

    public double getR0() {
        return R0;
    }

    public void setR0(double r0) {
        R0 = r0;
    }

    public double getX0() {
        return X0;
    }

    public void setX0(double x0) {
        X0 = x0;
    }

    public SPS getBlk() {
        return Blk;
    }

    public void setBlk(SPS blk) {
        Blk = blk;
    }
}
