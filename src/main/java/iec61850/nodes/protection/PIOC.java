package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACT;
import iec61850.objects.protection.ASG;

/**
 * Класс МТЗ без выдержки времени
 *
 * @see ACT - сигнал срабатывания защиты
 * @see WYE - Класс общих данных трехфазной сети
 * @see ASG - уставка срабатывания
 */
public class PIOC extends LN {

    private WYE A = new WYE();
    private ACT op = new ACT();
    //    private ACD str = new ACD();
//    private ING OpDlTmms = new ING(50); //Выдержка времени
    private ASG StrVal = new ASG(0f); //Уставка срабатывания

    private int count = 0;

    /**
     * Проверка текущего значения тока и присвоение сигнала срабатывания
     * в случае превышения уставки
     */
    @Override
    public void process() {

        boolean phsA = A.getPhsA().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
        boolean phsB = A.getPhsB().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
        boolean phsC = A.getPhsC().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();

        boolean general = phsA || phsB || phsC;

        op.getGeneral().setValue(general);
        op.getPhsA().setValue(phsA);
        op.getPhsB().setValue(phsB);
        op.getPhsC().setValue(phsC);

    }

    public ASG getStrVal() {
        return StrVal;
    }

    public void setStrVal(Float strVal) {
        StrVal.getSetMag().getF().setValue(strVal);
    }

    public WYE getA() {
        return A;
    }

    public void setA(WYE a) {
        A = a;
    }

    public ACT getOp() {
        return op;
    }

    public void setOp(ACT op) {
        this.op = op;
    }

}
