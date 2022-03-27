package iec61850.nodes.control;

import iec61850.nodes.common.LN;
import iec61850.objects.control.DPC;
import iec61850.objects.control.INC;
import iec61850.objects.control.SPC;
import iec61850.objects.control.SPS;

/**
 * Класс комутационного оборудования, выключателя
 *
 * @see SPS - недублированное состояния, место управления удаленно или локально
 * @see INC - целочисленное состояние, счетчик переключений выключателя
 * @see DPC - дублированное состояние, сигнал проверяемый узлом выключателя и управления выключателем
 * @see SPC - недублированное состояние и управление, блокировка выключателя
 */
public class XCBR extends LN {

    private SPS loc = new SPS();
    private INC opCnt = new INC();
    private DPC pos = new DPC();
    private SPC BlkOpn = new SPC();
    private SPC BlkCls = new SPC();
    private DPC ctVal = new DPC();

    public int count = 30;
    public int i = 0;

    @Override
    public void process() {
        if (!BlkOpn.getStVal().getValue()) {
            if ((ctVal.getStVal().getValue() && !pos.getStVal().getValue())) {
                if (i < count) {
                    i++;
                } else {
                    pos.getStVal().setValue(true);
                    opCnt.increase();
                    i = 0;
                }
            }
        }
    }


    public INC getOpCnt() {
        return opCnt;
    }

    public SPC getBlkOpn() {
        return BlkOpn;
    }

    public DPC getPos() {
        return pos;
    }

    public void setPos(DPC pos) {
        this.pos = pos;
    }

    public DPC getCtVal() {
        return ctVal;
    }


    public void setCtVal(DPC ctVal) {
        this.ctVal = ctVal;
    }
}
