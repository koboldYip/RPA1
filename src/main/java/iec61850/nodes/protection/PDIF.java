package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.control.SPS;
import iec61850.objects.measurements.HWYE;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACT;
import iec61850.objects.protection.ASG;
import iec61850.objects.protection.ING;

import java.util.ArrayList;
import java.util.List;

public class PDIF extends LN {

    private ACT op = new ACT();
    private WYE DifAClc = new WYE();
    private WYE RstA = new WYE();

    private ING MinOpTmms = new ING(5);
    private ASG LoSet = new ASG(0f);
    private ING MaxOpTmms = new ING(5);
    private ASG HiSet = new ASG(0f);
    private ING RsDlTmms = new ING(30);

    private SPS Blk = new SPS();
    private List<HWYE> Harmonic = new ArrayList<>();

    private int count = 0;

    private int harmonicBlock = 0;
    private int Rst = 0;
    private int D0 = 0;
    private double k = 0;
    private double m = 0;


    @Override
    public void process() {
        boolean general = false;
        boolean phsA = false;
        boolean phsB = false;
        boolean phsC = false;

        Blk.getStVal().setValue(false);

        Blk.getStVal().setValue(Harmonic.stream().flatMap(hwye -> hwye.getHar().stream())
                .anyMatch(a -> a.get(harmonicBlock).getcVal().getMag().getF().getValue() /
                        a.get(0).getcVal().getMag().getF().getValue() > 0.1));


        if (!Blk.getStVal().getValue()) {
            if (RstA.getPhsA().getcVal().getMag().getF().getValue() > Rst ||
                    RstA.getPhsB().getcVal().getMag().getF().getValue() > Rst ||
                    RstA.getPhsC().getcVal().getMag().getF().getValue() > Rst) {
                phsA = DifAClc.getPhsA().getcVal().getMag().getF().getValue() > k * RstA.getPhsA().getcVal().getMag().getF().getValue() + m;
                phsB = DifAClc.getPhsB().getcVal().getMag().getF().getValue() > k * RstA.getPhsB().getcVal().getMag().getF().getValue() + m;
                phsC = DifAClc.getPhsC().getcVal().getMag().getF().getValue() > k * RstA.getPhsC().getcVal().getMag().getF().getValue() + m;
            } else {
                phsA = DifAClc.getPhsA().getcVal().getMag().getF().getValue() > D0;
                phsB = DifAClc.getPhsB().getcVal().getMag().getF().getValue() > D0;
                phsC = DifAClc.getPhsC().getcVal().getMag().getF().getValue() > D0;
            }
        }

        general = phsA || phsB || phsC;

        if (general && ++count >= MaxOpTmms.getSetVal().getValue()) {
            op.getGeneral().setValue(general);
            op.getPhsA().setValue(phsA);
            op.getPhsB().setValue(phsB);
            op.getPhsC().setValue(phsC);
            count = 0;
        }

        if (!general) count = 0;

    }

    public ACT getOp() {
        return op;
    }

    public void setOp(ACT op) {
        this.op = op;
    }

    public WYE getDifAClc() {
        return DifAClc;
    }

    public void setDifAClc(WYE difAClc) {
        DifAClc = difAClc;
    }

    public WYE getRstA() {
        return RstA;
    }

    public void setRstA(WYE rstA) {
        RstA = rstA;
    }

    public ING getMinOpTmms() {
        return MinOpTmms;
    }

    public void setMinOpTmms(ING minOpTmms) {
        MinOpTmms = minOpTmms;
    }

    public ASG getLoSet() {
        return LoSet;
    }

    public void setLoSet(ASG loSet) {
        LoSet = loSet;
    }

    public ING getMaxOpTmms() {
        return MaxOpTmms;
    }

    public void setMaxOpTmms(ING maxOpTmms) {
        MaxOpTmms = maxOpTmms;
    }

    public ASG getHiSet() {
        return HiSet;
    }

    public void setHiSet(ASG hiSet) {
        HiSet = hiSet;
    }

    public ING getRsDlTmms() {
        return RsDlTmms;
    }

    public void setRsDlTmms(ING rsDlTmms) {
        RsDlTmms = rsDlTmms;
    }

    public SPS getBlk() {
        return Blk;
    }

    public void setBlk(SPS blk) {
        Blk = blk;
    }

    public List<HWYE> getHarmonic() {
        return Harmonic;
    }

    public void setHarmonic(List<HWYE> harmonic) {
        Harmonic = harmonic;
    }

    public int getHarmonicBlock() {
        return harmonicBlock;
    }

    public void setHarmonicBlock(int harmonicBlock) {
        this.harmonicBlock = harmonicBlock;
    }

    public int getRst() {
        return Rst;
    }

    public void setRst(int rst) {
        Rst = rst;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public int getD0() {
        return D0;
    }

    public void setD0(int d0) {
        D0 = d0;
    }
}
