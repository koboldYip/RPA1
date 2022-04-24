package iec61850.objects.measurements;

import iec61850.objects.measurements.Seqenum.SeqT;

public class SEQ {

    private CMV c1 = new CMV();
    private CMV c2 = new CMV();
    private CMV c3 = new CMV();

    private SeqT seqT;

    public CMV getC1() {
        return c1;
    }

    public void setC1(CMV c1) {
        this.c1 = c1;
    }

    public CMV getC2() {
        return c2;
    }

    public void setC2(CMV c2) {
        this.c2 = c2;
    }

    public CMV getC3() {
        return c3;
    }

    public void setC3(CMV c3) {
        this.c3 = c3;
    }

    public SeqT getSeqT() {
        return seqT;
    }

    public void setSeqT(SeqT seqT) {
        this.seqT = seqT;
    }
}