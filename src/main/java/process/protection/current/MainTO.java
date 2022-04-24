package process.protection.current;

import iec61850.nodes.control.CSWI;
import iec61850.nodes.control.XCBR;
import iec61850.nodes.custom.LSVC;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.other.NHMISignal;
import iec61850.nodes.measurements.MMXU;
import iec61850.nodes.protection.PTOC;

public class MainTO {


    public static void main(String[] args) {
        LSVC lsvc = new LSVC();
        NHMI nhmi = new NHMI();
        MMXU mmxu = new MMXU();
        PTOC PTOC1 = new PTOC();
        PTOC PTOC2 = new PTOC();
        PTOC PTOC3 = new PTOC();
        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();

        lsvc.readCSV("src/main/resources/1лаба/Опыты/Конец линии/PhABC20");

        mmxu.setiL1(lsvc.getSignals().get(0));
        mmxu.setiL2(lsvc.getSignals().get(1));
        mmxu.setiL3(lsvc.getSignals().get(2));

        PTOC1.setStrVal(3900f);
        PTOC2.setStrVal(3300f);
        PTOC3.setStrVal(533f);
        PTOC1.setA(mmxu.getA());
        PTOC2.setA(mmxu.getA());
        PTOC3.setA(mmxu.getA());

        cswi.setOpOpn1(PTOC1.getOp());
        cswi.setOpOpn2(PTOC2.getOp());
        cswi.setOpOpn3(PTOC3.getOp());
        cswi.setStVal(xcbr.getPos());

        xcbr.setCtVal(cswi.getCtVal());

        nhmi.addSignals(
                new NHMISignal("Ток l1", lsvc.getSignals().get(0).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l2", lsvc.getSignals().get(1).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l3", lsvc.getSignals().get(2).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l1 MSD", mmxu.getA().getPhsA().getcVal().getMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l2 MSD", mmxu.getA().getPhsB().getcVal().getMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l3 MSD", mmxu.getA().getPhsC().getcVal().getMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("PIOC1", PTOC1.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("PIOC2", PTOC2.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("PIOC3", PTOC3.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("CSWI", cswi.getCtVal().getStVal())
        );
        nhmi.addSignals(
                new NHMISignal("XCBR", xcbr.getPos().getStVal())
        );
        while (lsvc.hasNext()) {
            lsvc.process();
            nhmi.process();
            mmxu.process();
            PTOC1.process();
            PTOC2.process();
            PTOC3.process();
            cswi.process();
            xcbr.process();

            System.out.println(lsvc.getSignals().get(0).getInstMag().getF().getValue());
        }

    }
}
