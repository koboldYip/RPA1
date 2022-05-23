package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.measurements.filter.Filter;
import iec61850.nodes.measurements.filter.Fourier;
import iec61850.nodes.measurements.filter.MSD;
import iec61850.objects.measurements.DEL;
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
    private DEL Z = new DEL();

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

        float Ua_bx = PhV.getPhsA().getcVal().getVectorX().getF().getValue() - PhV.getPhsB().getcVal().getVectorX().getF().getValue();
        float Ub_cx = PhV.getPhsB().getcVal().getVectorX().getF().getValue() - PhV.getPhsC().getcVal().getVectorX().getF().getValue();
        float Uc_ax = PhV.getPhsC().getcVal().getVectorX().getF().getValue() - PhV.getPhsA().getcVal().getVectorX().getF().getValue();

        float Ua_by = PhV.getPhsA().getcVal().getVectorY().getF().getValue() - PhV.getPhsB().getcVal().getVectorY().getF().getValue();
        float Ub_cy = PhV.getPhsB().getcVal().getVectorY().getF().getValue() - PhV.getPhsC().getcVal().getVectorY().getF().getValue();
        float Uc_ay = PhV.getPhsC().getcVal().getVectorY().getF().getValue() - PhV.getPhsA().getcVal().getVectorY().getF().getValue();

        float Ia_bx = A.getPhsA().getcVal().getVectorX().getF().getValue() - A.getPhsB().getcVal().getVectorX().getF().getValue();
        float Ib_cx = A.getPhsB().getcVal().getVectorX().getF().getValue() - A.getPhsC().getcVal().getVectorX().getF().getValue();
        float Ic_ax = A.getPhsC().getcVal().getVectorX().getF().getValue() - A.getPhsA().getcVal().getVectorX().getF().getValue();

        float Ia_by = A.getPhsA().getcVal().getVectorY().getF().getValue() - A.getPhsB().getcVal().getVectorY().getF().getValue();
        float Ib_cy = A.getPhsB().getcVal().getVectorY().getF().getValue() - A.getPhsC().getcVal().getVectorY().getF().getValue();
        float Ic_ay = A.getPhsC().getcVal().getVectorY().getF().getValue() - A.getPhsA().getcVal().getVectorY().getF().getValue();

        float Ua_b = (float) Math.sqrt(Ua_bx * Ua_bx + Ua_by * Ua_by);
        float Ub_c = (float) Math.sqrt(Ub_cx * Ub_cx + Ub_cy * Ub_cy);
        float Uc_a = (float) Math.sqrt(Uc_ax * Uc_ax + Uc_ay * Uc_ay);

        float Ia_b = (float) Math.sqrt(Ia_bx * Ia_bx + Ia_by * Ia_by);
        float Ib_c = (float) Math.sqrt(Ib_cx * Ib_cx + Ib_cy * Ib_cy);
        float Ic_a = (float) Math.sqrt(Ic_ax * Ic_ax + Ic_ay * Ic_ay);

        float Ua_bAng = (float) Math.toDegrees(Math.atan2(Ua_by, Ua_bx));
        float Ub_cAng = (float) Math.toDegrees(Math.atan2(Ub_cy, Ub_cx));
        float Uc_aAng = (float) Math.toDegrees(Math.atan2(Uc_ay, Uc_ax));

        float Ia_bAng = (float) Math.toDegrees(Math.atan2(Ia_by, Ia_bx));
        float Ib_cAng = (float) Math.toDegrees(Math.atan2(Ib_cy, Ib_cx));
        float Ic_aAng = (float) Math.toDegrees(Math.atan2(Ic_ay, Ic_ax));

        float Za_b = Ua_b / Ia_b;
        float Zb_c = Ub_c / Ib_c;
        float Zc_a = Uc_a / Ic_a;

        float Za_bAng = Ua_bAng - Ia_bAng;
        float Zb_cAng = Ub_cAng - Ib_cAng;
        float Zc_aAng = Uc_aAng - Ic_aAng;

        Z.getPhsAB().getcVal().setByMagAndAngle(Za_b, Za_bAng);
        Z.getPhsBC().getcVal().setByMagAndAngle(Zb_c, Zb_cAng);
        Z.getPhsCA().getcVal().setByMagAndAngle(Zc_a, Zc_aAng);

    }

    public DEL getZ() {
        return Z;
    }

    public void setZ(DEL z) {
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
