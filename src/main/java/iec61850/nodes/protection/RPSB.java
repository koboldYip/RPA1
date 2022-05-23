package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.control.SPS;
import iec61850.objects.measurements.DEL;
import iec61850.objects.protection.ASG;
import iec61850.objects.protection.ING;

import java.util.Deque;
import java.util.LinkedList;

public class RPSB extends LN {

    private ASG SwgRis = new ASG(0f);
    private ASG SwgReact = new ASG(0f);
    private SPS BlkZn = new SPS();
    private ING UnBlkTmms = new ING(0);

    private DEL Z = new DEL();

    private int count = 0;
    private float samples = 10;
    private Deque<Float> windowABX = new LinkedList<>();
    private Deque<Float> windowBCX = new LinkedList<>();
    private Deque<Float> windowCAX = new LinkedList<>();
    private Deque<Float> windowABY = new LinkedList<>();
    private Deque<Float> windowBCY = new LinkedList<>();
    private Deque<Float> windowCAY = new LinkedList<>();

    @Override
    public void process() {


        if (windowABX.size() <= samples) {
            windowABX.addLast(Z.getPhsAB().getcVal().getVectorX().getF().getValue());
            windowBCX.addLast(Z.getPhsBC().getcVal().getVectorX().getF().getValue());
            windowCAX.addLast(Z.getPhsCA().getcVal().getVectorX().getF().getValue());
            windowABY.addLast(Z.getPhsAB().getcVal().getVectorY().getF().getValue());
            windowBCY.addLast(Z.getPhsBC().getcVal().getVectorY().getF().getValue());
            windowCAY.addLast(Z.getPhsCA().getcVal().getVectorY().getF().getValue());
        } else {
            windowABX.addLast(Z.getPhsAB().getcVal().getVectorX().getF().getValue());
            windowABX.removeFirst();
            windowBCX.addLast(Z.getPhsBC().getcVal().getVectorX().getF().getValue());
            windowBCX.removeFirst();
            windowCAX.addLast(Z.getPhsCA().getcVal().getVectorX().getF().getValue());
            windowCAX.removeFirst();
            windowABY.addLast(Z.getPhsAB().getcVal().getVectorY().getF().getValue());
            windowABY.removeFirst();
            windowBCY.addLast(Z.getPhsBC().getcVal().getVectorY().getF().getValue());
            windowBCY.removeFirst();
            windowCAY.addLast(Z.getPhsCA().getcVal().getVectorY().getF().getValue());
            windowCAY.removeFirst();
        }
        if (BlkZn.getStVal().getValue()) {
            if ((Math.abs((windowABX.getLast() - windowABX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowBCX.getLast() - windowBCX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowCAX.getLast() - windowCAX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowABY.getLast() - windowABY.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowBCY.getLast() - windowBCY.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowCAY.getLast() - windowCAY.getFirst()) / 2500) > .0001f)) {
                BlkZn.getStVal().setValue(false);
            }
        }
        if (!BlkZn.getStVal().getValue() && ++count >= UnBlkTmms.getSetVal().getValue()) {
            BlkZn.getStVal().setValue(true);
            count = 0;
        }

    }

    public ASG getSwgRis() {
        return SwgRis;
    }

    public void setSwgRis(ASG swgRis) {
        SwgRis = swgRis;
    }

    public ASG getSwgReact() {
        return SwgReact;
    }

    public void setSwgReact(ASG swgReact) {
        SwgReact = swgReact;
    }

    public SPS getBlkZn() {
        return BlkZn;
    }

    public void setBlkZn(SPS blkZn) {
        BlkZn = blkZn;
    }

    public ING getUnBlkTmms() {
        return UnBlkTmms;
    }

    public void setUnBlkTmms(ING unBlkTmms) {
        UnBlkTmms = unBlkTmms;
    }

    public DEL getZ() {
        return Z;
    }

    public void setZ(DEL z) {
        Z = z;
    }
}
