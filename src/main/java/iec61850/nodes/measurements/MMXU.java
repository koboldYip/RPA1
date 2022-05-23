package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.measurements.filter.Filter;
import iec61850.nodes.measurements.filter.Fourier;
import iec61850.nodes.measurements.filter.MSD;
import iec61850.objects.measurements.WYE;
import iec61850.objects.samples.SAV;

/**
 * @see SAV - представление мгновенных аналоговых значений
 * @see MSD - фильтр средневыпрямленного значения
 * @see WYE - Класс общих данных трехфазной сети
 */
public class MMXU extends LN {

    private SAV iL1 = new SAV();
    private SAV iL2 = new SAV();
    private SAV iL3 = new SAV();
    private SAV vL1 = new SAV();
    private SAV vL2 = new SAV();
    private SAV vL3 = new SAV();

    private WYE A = new WYE();
    private WYE PhV = new WYE();
    private WYE W = new WYE();
    private WYE Z = new WYE();

    private Filter ia = new Fourier();
    private Filter ib = new Fourier();
    private Filter ic = new Fourier();
    private Filter va = new Fourier();
    private Filter vb = new Fourier();
    private Filter vc = new Fourier();


    @Override
    public void process() {
        ia.process(iL1, A.getPhsA().getcVal());
        ib.process(iL2, A.getPhsB().getcVal());
        ic.process(iL3, A.getPhsC().getcVal());
        va.process(vL1, PhV.getPhsA().getcVal());
        vb.process(vL2, PhV.getPhsB().getcVal());
        vc.process(vL3, PhV.getPhsC().getcVal());

        W.getPhsA().getcVal().getMag().getF().setValue(
                (float) (A.getPhsA().getcVal().getMag().getF().getValue() *
                        PhV.getPhsA().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(PhV.getPhsA().getcVal().getAng().getF().getValue() -
                                A.getPhsA().getcVal().getAng().getF().getValue())))));
        W.getPhsB().getcVal().getMag().getF().setValue(
                (float) (A.getPhsB().getcVal().getMag().getF().getValue() *
                        PhV.getPhsB().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(PhV.getPhsB().getcVal().getAng().getF().getValue() -
                                A.getPhsB().getcVal().getAng().getF().getValue())))));
        W.getPhsC().getcVal().getMag().getF().setValue(
                (float) (A.getPhsC().getcVal().getMag().getF().getValue() *
                        PhV.getPhsC().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(PhV.getPhsC().getcVal().getAng().getF().getValue() -
                                A.getPhsC().getcVal().getAng().getF().getValue())))));

        Z.getPhsA().getcVal().setByMagAndAngle(PhV.getPhsA().getcVal().getMag().getF().getValue() /
                        A.getPhsA().getcVal().getMag().getF().getValue(),
                PhV.getPhsA().getcVal().getAng().getF().getValue() -
                        A.getPhsA().getcVal().getAng().getF().getValue());

        Z.getPhsB().getcVal().setByMagAndAngle(PhV.getPhsB().getcVal().getMag().getF().getValue() /
                        A.getPhsB().getcVal().getMag().getF().getValue(),
                PhV.getPhsB().getcVal().getAng().getF().getValue() -
                        A.getPhsB().getcVal().getAng().getF().getValue());

        Z.getPhsC().getcVal().setByMagAndAngle(PhV.getPhsC().getcVal().getMag().getF().getValue() /
                        A.getPhsC().getcVal().getMag().getF().getValue(),
                PhV.getPhsC().getcVal().getAng().getF().getValue() -
                        A.getPhsC().getcVal().getAng().getF().getValue());

    }

    public WYE getZ() {
        return Z;
    }

    public void setZ(WYE z) {
        Z = z;
    }

    public WYE getW() {
        return W;
    }

    public void setW(WYE w) {
        W = w;
    }

    public WYE getPhV() {
        return PhV;
    }

    public void setPhV(WYE phV) {
        PhV = phV;
    }

    public SAV getvL1() {
        return vL1;
    }

    public void setvL1(SAV vL1) {
        this.vL1 = vL1;
    }

    public SAV getvL2() {
        return vL2;
    }

    public void setvL2(SAV vL2) {
        this.vL2 = vL2;
    }

    public SAV getvL3() {
        return vL3;
    }

    public void setvL3(SAV vL3) {
        this.vL3 = vL3;
    }

    public SAV getiL1() {
        return iL1;
    }

    public void setiL1(SAV iL1) {
        this.iL1 = iL1;
    }

    public SAV getiL2() {
        return iL2;
    }

    public void setiL2(SAV iL2) {
        this.iL2 = iL2;
    }

    public SAV getiL3() {
        return iL3;
    }

    public void setiL3(SAV iL3) {
        this.iL3 = iL3;
    }

    public WYE getA() {
        return A;
    }

    public void setA(WYE a) {
        A = a;
    }
}
