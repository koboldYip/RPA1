package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.measurements.filter.Filter;
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

    private WYE A = new WYE();

    private Filter ia = new MSD();
    private Filter ib = new MSD();
    private Filter ic = new MSD();


    @Override
    public void process() {
        ia.process(iL1, A.getPhsA().getcVal());
        ib.process(iL2, A.getPhsB().getcVal());
        ic.process(iL3, A.getPhsC().getcVal());
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
