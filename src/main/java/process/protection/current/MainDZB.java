package process.protection.current;

import iec61850.nodes.control.CSWI;
import iec61850.nodes.control.XCBR;
import iec61850.nodes.custom.LSVC;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.other.NHMISignal;
import iec61850.nodes.measurements.MHAI;
import iec61850.nodes.measurements.MMXU;
import iec61850.nodes.protection.PDIF;
import iec61850.nodes.protection.RMXU;

public class MainDZB {


    public static void main(String[] args) {
        LSVC lsvc = new LSVC();
        NHMI nhmi = new NHMI();
        MMXU mmxu1 = new MMXU();
        MMXU mmxu2 = new MMXU();
        MMXU mmxu3 = new MMXU();
        MMXU mmxu4 = new MMXU();
        MHAI mhai1 = new MHAI(10);
        MHAI mhai2 = new MHAI(10);
        MHAI mhai3 = new MHAI(10);
        MHAI mhai4 = new MHAI(10);
        RMXU rmxu = new RMXU();
        PDIF pdif = new PDIF();
        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();

        lsvc.readComtrade("src/main/resources/4лаба/Опыты/Опыты/DPB/4 sections/Vkl");

        mmxu1.setiL1(lsvc.getSignals().get(0));
        mmxu1.setiL2(lsvc.getSignals().get(1));
        mmxu1.setiL3(lsvc.getSignals().get(2));

        mmxu2.setiL1(lsvc.getSignals().get(3));
        mmxu2.setiL2(lsvc.getSignals().get(4));
        mmxu2.setvL3(lsvc.getSignals().get(5));

        mmxu3.setiL1(lsvc.getSignals().get(6));
        mmxu3.setiL2(lsvc.getSignals().get(7));
        mmxu3.setiL3(lsvc.getSignals().get(8));

        mmxu4.setiL1(lsvc.getSignals().get(9));
        mmxu4.setiL2(lsvc.getSignals().get(10));
        mmxu4.setiL3(lsvc.getSignals().get(11));

        mhai1.setiL1(lsvc.getSignals().get(0));
        mhai1.setiL2(lsvc.getSignals().get(1));
        mhai1.setiL3(lsvc.getSignals().get(2));

        mhai2.setiL1(lsvc.getSignals().get(3));
        mhai2.setiL2(lsvc.getSignals().get(4));
        mhai2.setiL3(lsvc.getSignals().get(5));

        mhai3.setiL1(lsvc.getSignals().get(6));
        mhai3.setiL2(lsvc.getSignals().get(7));
        mhai3.setiL3(lsvc.getSignals().get(8));

        mhai4.setiL1(lsvc.getSignals().get(9));
        mhai4.setiL2(lsvc.getSignals().get(10));
        mhai4.setiL3(lsvc.getSignals().get(11));

        rmxu.setA1(mmxu1.getA());
        rmxu.setA2(mmxu2.getA());
        rmxu.setA3(mmxu3.getA());
        rmxu.setA4(mmxu4.getA());

        pdif.setDifAClc(rmxu.getALoc());
        pdif.getHarmonic().add(mhai1.getHA());
        pdif.getHarmonic().add(mhai2.getHA());
        pdif.getHarmonic().add(mhai3.getHA());
        pdif.getHarmonic().add(mhai4.getHA());
        pdif.setRstA(rmxu.getRstA());
        pdif.setRst(300);
        pdif.setD0(660);
        pdif.setK(0.4);
        pdif.setM(1000);
        pdif.setHarmonicBlock(4);

        cswi.setOpOpn1(pdif.getOp());
        cswi.setStVal(xcbr.getPos());

        xcbr.setCtVal(cswi.getCtVal());

        nhmi.addSignals(
                new NHMISignal("ТокA 1", lsvc.getSignals().get(0).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("ТокA 2", lsvc.getSignals().get(3).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("ТокA 3", lsvc.getSignals().get(6).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("ТокA 4", lsvc.getSignals().get(9).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Диф A", rmxu.getALoc().getPhsA().getcVal().getMag().getF()),
                new NHMISignal("B", rmxu.getALoc().getPhsB().getcVal().getMag().getF()),
                new NHMISignal("C", rmxu.getALoc().getPhsC().getcVal().getMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("ДЗШ", pdif.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("Blk", pdif.getBlk().getStVal())
        );
        nhmi.addSignals(
                new NHMISignal("CSWI", cswi.getStVal().getStVal())
        );
        nhmi.addSignals(
                new NHMISignal("XCBR", xcbr.getPos().getStVal())
        );

        while (lsvc.hasNext()) {
            lsvc.process();
            nhmi.process();
            mmxu1.process();
            mmxu2.process();
            mmxu3.process();
            mmxu4.process();
            mhai1.process();
            mhai2.process();
            mhai3.process();
            mhai4.process();
            rmxu.process();
            pdif.process();
            cswi.process();
            xcbr.process();
        }

    }
}
