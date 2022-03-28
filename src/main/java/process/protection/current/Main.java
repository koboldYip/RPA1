package process.protection.current;

import iec61850.nodes.control.CSWI;
import iec61850.nodes.control.XCBR;
import iec61850.nodes.custom.LSVC;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.other.NHMISignal;
import iec61850.nodes.measurements.MMXU;
import iec61850.nodes.protection.PIOC;

public class Main {


    public static void main(String[] args) {
        LSVC lsvc = new LSVC();
        NHMI nhmi = new NHMI();
        MMXU mmxu = new MMXU();
        PIOC pioc1 = new PIOC();
        PIOC pioc2 = new PIOC();
        PIOC pioc3 = new PIOC();
        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();

        lsvc.readCSV("D:\\EAU_TEST\\Maga\\2sem\\alg\\RPA\\src\\main\\resources\\Опыты\\Конец линии\\PhABC20");

        mmxu.setiL1(lsvc.getSignals().get(0));
        mmxu.setiL2(lsvc.getSignals().get(1));
        mmxu.setiL3(lsvc.getSignals().get(2));

        pioc1.setStrVal(3900f);
        pioc2.setStrVal(3300f);
        pioc3.setStrVal(533f);
        pioc1.setA(mmxu.getA());
        pioc2.setA(mmxu.getA());
        pioc3.setA(mmxu.getA());

        cswi.setOpOpn1(pioc1.getOp());
        cswi.setOpOpn2(pioc2.getOp());
        cswi.setOpOpn3(pioc3.getOp());
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
                new NHMISignal("PIOC1", pioc1.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("PIOC2", pioc2.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("PIOC3", pioc3.getOp().getGeneral())
        );
        nhmi.addSignals(
                new NHMISignal("CSWI", cswi.getCtVal().getStVal())
        );
        nhmi.addSignals(
                new NHMISignal("XCBR", xcbr.getPos().getStVal())
        );
        int i = 0;
        while (lsvc.hasNext()) {
            lsvc.process();
            nhmi.process();
            mmxu.process();
            pioc1.process();
            pioc2.process();
            pioc3.process();
            cswi.process();
            xcbr.process();

            System.out.println(lsvc.getSignals().get(0).getInstMag().getF().getValue());
        }

    }
}
