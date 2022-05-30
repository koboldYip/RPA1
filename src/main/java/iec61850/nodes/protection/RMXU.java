package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.CMV;
import iec61850.objects.measurements.WYE;

import java.util.ArrayList;
import java.util.List;

public class RMXU extends LN {

    private WYE ALoc = new WYE();
    private WYE RstA = new WYE();

    private List<WYE> a = new ArrayList<>();

    @Override
    public void process() {
        ALoc.getPhsA().getcVal().setByOrt(a.stream()
                        .map(WYE::getPhsA)
                        .map(CMV::getOrtX)
                        .reduce(0f, Float::sum),
                a.stream()
                        .map(WYE::getPhsA)
                        .map(CMV::getOrtY)
                        .reduce(0f, Float::sum));

        ALoc.getPhsB().getcVal().setByOrt(a.stream()
                        .map(WYE::getPhsB)
                        .map(CMV::getOrtX)
                        .reduce(0f, Float::sum),
                a.stream()
                        .map(WYE::getPhsB)
                        .map(CMV::getOrtY)
                        .reduce(0f, Float::sum));

        ALoc.getPhsC().getcVal().setByOrt(a.stream()
                        .map(WYE::getPhsC)
                        .map(CMV::getOrtX)
                        .reduce(0f, Float::sum),
                a.stream()
                        .map(WYE::getPhsC)
                        .map(CMV::getOrtY)
                        .reduce(0f, Float::sum));

        RstA.getPhsA().getcVal().setMag(a.stream()
                .map(WYE::getPhsA)
                .map(CMV::getMag)
                .reduce(0f, Float::sum));


        RstA.getPhsB().getcVal().setMag(a.stream()
                .map(WYE::getPhsB)
                .map(CMV::getMag)
                .reduce(0f, Float::sum));

        RstA.getPhsC().getcVal().setMag(a.stream()
                .map(WYE::getPhsC)
                .map(CMV::getMag)
                .reduce(0f, Float::sum));


    }

    public WYE getALoc() {
        return ALoc;
    }

    public void setALoc(WYE ALoc) {
        this.ALoc = ALoc;
    }

    public WYE getRstA() {
        return RstA;
    }

    public void setRstA(WYE rstA) {
        RstA = rstA;
    }

    public List<WYE> getA() {
        return a;
    }

    public void setA(List<WYE> a) {
        this.a = a;
    }
}
