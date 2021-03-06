package iec61850.objects.measurements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс общих данных трехфазной сети
 */
public class WYE {

    private CMV phsA = new CMV();
    private CMV phsB = new CMV();
    private CMV phsC = new CMV();

    public CMV getPhsA() {
        return phsA;
    }

    public void setPhsA(CMV phsA) {
        this.phsA = phsA;
    }

    public CMV getPhsB() {
        return phsB;
    }

    public void setPhsB(CMV phsB) {
        this.phsB = phsB;
    }

    public CMV getPhsC() {
        return phsC;
    }

    public void setPhsC(CMV phsC) {
        this.phsC = phsC;
    }

    public List<CMV> phases() {
        return new ArrayList<>(Arrays.asList(phsA, phsB, phsC));
    }
}
