package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.*;
import iec61850.objects.samples.Attribute;

/**
 * Класс МТЗ без выдержки времени
 *
 * @see ACT - сигнал срабатывания защиты
 * @see WYE - Класс общих данных трехфазной сети
 * @see ASG - уставка срабатывания
 */
public class PTOC extends LN {

    private WYE A = new WYE();
    private ACT op = new ACT();
    private ACD str = new ACD();
    private ING OpDlTmms = new ING(10); //Выдержка времени
    private ASG StrVal = new ASG(0f); //Уставка срабатывания

    private ENG DirMod = new ENG();
    private Attribute<Boolean> Pusk = new Attribute<>(false);
    private Attribute<Boolean> acceleration = new Attribute<>(false);

    private int count = 0;


    /**
     * Проверка текущего значения тока и присвоение сигнала срабатывания
     * в случае превышения уставки
     */
    @Override
    public void process() {

        if (acceleration.getValue()) {
            getOpDlTmms().getSetVal().setValue(10);
        }

        if ((DirMod.getDir().getValue() != str.getDirPhsA().getValue() ||
                DirMod.getDir().getValue() != str.getDirPhsB().getValue() ||
                DirMod.getDir().getValue() != str.getDirPhsC().getValue()) &&
                DirMod.getDir().getValue().ordinal() != 3) {
            count = 0;
        }

        boolean phsA = false;
        boolean phsB = false;
        boolean phsC = false;

        if (DirMod.getDir().getValue().ordinal() == 1) {
            phsA = A.getPhsA().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    DirMod.getDir().getValue() == str.getDirPhsA().getValue();
            phsB = A.getPhsB().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    DirMod.getDir().getValue() == str.getDirPhsB().getValue();
            phsC = A.getPhsC().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    DirMod.getDir().getValue() == str.getDirPhsC().getValue();
        } else {
            phsA = A.getPhsA().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
            phsB = A.getPhsB().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
            phsC = A.getPhsC().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
        }

        if (phsA || phsB || phsC) {
            count++;
            getPusk().setValue(true);
        } else {
            count = 0;
            getPusk().setValue(false);
        }

        if (count >= OpDlTmms.getSetVal().getValue()) {
            op.getGeneral().setValue(true);
            op.getPhsA().setValue(phsA);
            op.getPhsB().setValue(phsB);
            op.getPhsC().setValue(phsC);

        }

    }

    public Attribute<Boolean> getPusk() {
        return Pusk;
    }

    public void setPusk(Attribute<Boolean> pusk) {
        Pusk = pusk;
    }

    public ING getOpDlTmms() {
        return OpDlTmms;
    }

    public void setOpDlTmms(ING opDlTmms) {
        OpDlTmms = opDlTmms;
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

    public ACD getStr() {
        return str;
    }

    public void setStr(ACD str) {
        this.str = str;
    }

    public ENG getDirMod() {
        return DirMod;
    }

    public void setDirMod(ENG dirMod) {
        DirMod = dirMod;
    }
}
