package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.measurements.filter.Fourier;
import iec61850.objects.measurements.CMV;
import iec61850.objects.measurements.HWYE;
import iec61850.objects.samples.SAV;

import java.util.ArrayList;
import java.util.List;

public class MHAI extends LN {

    private SAV iL1 = new SAV();
    private SAV iL2 = new SAV();
    private SAV iL3 = new SAV();

    private HWYE HA = new HWYE();
    private int numHar;

    private List<Fourier> fouriersA = new ArrayList<>();
    private List<Fourier> fouriersB = new ArrayList<>();
    private List<Fourier> fouriersC = new ArrayList<>();

    public MHAI(int numHar) {
        this.numHar = numHar;
        for (int i = 1; i < numHar + 1; i++) {
            HA.getPhsAHar().add(new CMV());
            HA.getPhsBHar().add(new CMV());
            HA.getPhsCHar().add(new CMV());
            fouriersA.add(new Fourier(i, 20));
            fouriersB.add(new Fourier(i, 20));
            fouriersC.add(new Fourier(i, 20));
        }
    }

    @Override
    public void process() {
        for (int i = 0; i < fouriersA.size(); i++) {
            fouriersA.get(i).process(iL1, HA.getPhsAHar().get(i).getcVal());
            fouriersB.get(i).process(iL2, HA.getPhsAHar().get(i).getcVal());
            fouriersC.get(i).process(iL3, HA.getPhsAHar().get(i).getcVal());
        }
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

    public HWYE getHA() {
        return HA;
    }

    public void setHA(HWYE HA) {
        this.HA = HA;
    }

    public int getNumHar() {
        return numHar;
    }

    public void setNumHar(int numHar) {
        this.numHar = numHar;
    }

}
