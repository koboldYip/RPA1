package process.protection.current;

import iec61850.nodes.common.LN;
import iec61850.nodes.control.CSWI;
import iec61850.nodes.control.XCBR;
import iec61850.nodes.custom.LSVC;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.NHMIP;
import iec61850.nodes.gui.other.NHMIPoint;
import iec61850.nodes.gui.other.NHMISignal;
import iec61850.nodes.measurements.MMXU;
import iec61850.nodes.protection.PDIS;
import iec61850.nodes.protection.RPSB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainDZ {


    public static void main(String[] args) {
        List<LN> Nodes = new LinkedList<>();

        LSVC lsvc = new LSVC();
        NHMIP nhmip = new NHMIP();
        NHMI nhmi = new NHMI();
        MMXU mmxu = new MMXU();
        RPSB rpsb = new RPSB();
        PDIS pdis1 = new PDIS();
        PDIS pdis2 = new PDIS();
        PDIS pdis3 = new PDIS();
        PDIS pdis4 = new PDIS();
        PDIS pdis5 = new PDIS();
        PDIS pdis6 = new PDIS();
        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();

        Nodes.add(lsvc);
        Nodes.add(nhmip);
        Nodes.add(nhmi);
        Nodes.add(mmxu);
        Nodes.add(rpsb);
        Nodes.add(pdis1);
        Nodes.add(pdis2);
        Nodes.add(pdis3);
        Nodes.add(pdis4);
        Nodes.add(pdis5);
        Nodes.add(pdis6);
        Nodes.add(cswi);
        Nodes.add(xcbr);

        lsvc.readComtrade("src/main/resources/2лаба/Опыты/KZ1");

        mmxu.setvL1(lsvc.getSignals().get(0));
        mmxu.setvL2(lsvc.getSignals().get(1));
        mmxu.setvL3(lsvc.getSignals().get(2));

        mmxu.setiL1(lsvc.getSignals().get(3));
        mmxu.setiL2(lsvc.getSignals().get(4));
        mmxu.setiL3(lsvc.getSignals().get(5));

        rpsb.setZ(mmxu.getZ());
        rpsb.getUnBlkTmms().getSetVal().setValue(50);
        pdis1.setBlk(rpsb.getBlkZn());
        pdis2.setBlk(rpsb.getBlkZn());
        pdis3.setBlk(rpsb.getBlkZn());
        pdis4.setBlk(rpsb.getBlkZn());
        pdis5.setBlk(rpsb.getBlkZn());
        pdis6.setBlk(rpsb.getBlkZn());

        pdis1.setZ(mmxu.getZ());
        pdis1.getOpDlTmms().getSetVal().setValue(15);
        pdis1.getPhStr().getSetMag().getF().setValue(35f);
        pdis1.setR0(50);
        pdis1.setX0(50);

        pdis2.setZ(mmxu.getZ());
        pdis2.getOpDlTmms().getSetVal().setValue(60);
        pdis2.getPhStr().getSetMag().getF().setValue(45f);
        pdis2.setR0(63);
        pdis2.setX0(63);

        pdis3.setZ(mmxu.getZ());
        pdis3.getOpDlTmms().getSetVal().setValue(110);
        pdis3.getPhStr().getSetMag().getF().setValue(60f);
        pdis3.setR0(83);
        pdis3.setX0(83);

        pdis4.setZ(mmxu.getZ());
        pdis4.getOpDlTmms().getSetVal().setValue(160);
        pdis4.getPhStr().getSetMag().getF().setValue(60f);
        pdis4.setR0(0);
        pdis4.setX0(0);

        pdis5.setZ(mmxu.getZ());
        pdis5.getOpDlTmms().getSetVal().setValue(200);
        pdis5.getPhStr().getSetMag().getF().setValue(145f);
        pdis5.setR0(0);
        pdis5.setX0(0);

        pdis6.setZ(mmxu.getZ());
        pdis6.getOpDlTmms().getSetVal().setValue(230);
        pdis6.getPhStr().getSetMag().getF().setValue(181f);
        pdis6.setR0(0);
        pdis6.setX0(0);

        cswi.setOpOpn1(pdis1.getOp());
        cswi.setOpOpn2(pdis2.getOp());
        cswi.setOpOpn3(pdis3.getOp());
        cswi.setOpOpn4(pdis4.getOp());
        cswi.setOpOpn5(pdis5.getOp());
        cswi.setOpOpn6(pdis6.getOp());
        cswi.setStVal(xcbr.getPos());

        xcbr.setCtVal(cswi.getCtVal());

        nhmip.addSignals(new NHMISignal("Za", mmxu.getZ().getPhsA().getcVal().getVectorX().getF(),
                        mmxu.getZ().getPhsA().getcVal().getVectorY().getF()),
                new NHMISignal("Zb", mmxu.getZ().getPhsB().getcVal().getVectorX().getF(),
                        mmxu.getZ().getPhsB().getcVal().getVectorY().getF()),
                new NHMISignal("Zc", mmxu.getZ().getPhsC().getcVal().getVectorX().getF(),
                        mmxu.getZ().getPhsC().getcVal().getVectorY().getF()));

        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(25, 25, 36));
        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(31, 31, 45));
        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(41, 41, 60));
        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(0, 0, 73));
        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(0, 0, 90));
        nhmip.drawCharacteristic("Characteristic", getNhmiPoints(0, 0, 30));

        nhmi.addSignals(
                new NHMISignal("Ток l1", lsvc.getSignals().get(3).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l2", lsvc.getSignals().get(4).getInstMag().getF())
        );
        nhmi.addSignals(
                new NHMISignal("Ток l3", lsvc.getSignals().get(5).getInstMag().getF())
        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Ra", mmxu.getZ().getPhsA().getcVal().getVectorX().getF())
//        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Xa", mmxu.getZ().getPhsA().getcVal().getVectorY().getF())
//        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Rb", mmxu.getZ().getPhsB().getcVal().getVectorX().getF())
//        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Xb", mmxu.getZ().getPhsB().getcVal().getVectorY().getF())
//        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Rc", mmxu.getZ().getPhsC().getcVal().getVectorX().getF())
//        );
//        nhmi.addSignals(
//                new NHMISignal("Сопротивление Xc", mmxu.getZ().getPhsC().getcVal().getVectorY().getF())
//        );
        nhmi.addSignals(
                new NHMISignal("CSWI", cswi.getCtVal().getStVal())
        );
        nhmi.addSignals(
                new NHMISignal("XCBR", xcbr.getPos().getStVal())
        );

        while (lsvc.hasNext()) {
            Nodes.forEach(LN::process);
        }
    }

    private static List<NHMIPoint<Double, Double>> getNhmiPoints(double x0, double y0, double r) {
        List<NHMIPoint<Double, Double>> pointsList = new ArrayList<>();

        for (double x = -r; x <= r; x += 0.1) {
            double a = Math.pow(r, 2) - Math.pow(x, 2);
            pointsList.add(new NHMIPoint<>(x0 + x, y0 + Math.sqrt(a)));
            pointsList.add(new NHMIPoint<>(x0 + x, y0 - Math.sqrt(a)));
        }
        return pointsList;
    }
}
