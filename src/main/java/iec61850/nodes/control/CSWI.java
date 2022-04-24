package iec61850.nodes.control;

import iec61850.nodes.common.LN;
import iec61850.objects.control.DPC;
import iec61850.objects.protection.ACT;

/**
 * Узел управления выключателем
 *
 * @see DPC - дублированное состояние, сигнал проверяемый узлом выключателя и управления выключателем
 * @see ACT - сигнал срабатывания защиты
 */
public class CSWI extends LN {

    private DPC ctVal = new DPC();
    private DPC stVal = new DPC();
    private ACT opOpn1 = new ACT();
    private ACT opOpn2 = new ACT();
    private ACT opOpn3 = new ACT();
    private ACT opOpn4 = new ACT();
    private ACT opOpn5 = new ACT();
    private ACT opOpn6 = new ACT();

    @Override
    public void process() {

        if (opOpn1.getGeneral().getValue() ||
                opOpn2.getGeneral().getValue() ||
                opOpn3.getGeneral().getValue() ||
                opOpn4.getGeneral().getValue() ||
                opOpn5.getGeneral().getValue() ||
                opOpn6.getGeneral().getValue()) {
            if (!stVal.getStVal().getValue()) {
                ctVal.getStVal().setValue(true);
            } else {
                ctVal.getStVal().setValue(false);
            }
        }


    }

    public DPC getCtVal() {
        return ctVal;
    }

    public void setCtVal(DPC ctVal) {
        this.ctVal = ctVal;
    }

    public DPC getStVal() {
        return stVal;
    }

    public void setStVal(DPC stVal) {
        this.stVal = stVal;
    }

    public ACT getOpOpn1() {
        return opOpn1;
    }

    public void setOpOpn1(ACT opOpn1) {
        this.opOpn1 = opOpn1;
    }

    public ACT getOpOpn2() {
        return opOpn2;
    }

    public void setOpOpn2(ACT opOpn2) {
        this.opOpn2 = opOpn2;
    }

    public ACT getOpOpn3() {
        return opOpn3;
    }

    public void setOpOpn3(ACT opOpn3) {
        this.opOpn3 = opOpn3;
    }

    public ACT getOpOpn4() {
        return opOpn4;
    }

    public void setOpOpn4(ACT opOpn4) {
        this.opOpn4 = opOpn4;
    }

    public ACT getOpOpn5() {
        return opOpn5;
    }

    public void setOpOpn5(ACT opOpn5) {
        this.opOpn5 = opOpn5;
    }

    public ACT getOpOpn6() {
        return opOpn6;
    }

    public void setOpOpn6(ACT opOpn6) {
        this.opOpn6 = opOpn6;
    }
}
