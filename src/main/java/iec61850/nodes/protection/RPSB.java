package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.control.SPS;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ASG;
import iec61850.objects.protection.ING;

import java.util.Deque;
import java.util.LinkedList;

public class RPSB extends LN {

    private ASG SwgRis = new ASG(0f);
    private ASG SwgReact = new ASG(0f);
    private SPS BlkZn = new SPS();
    private ING UnBlkTmms = new ING(0);

    private WYE Z = new WYE();

    private int count = 0;
    private float samples = 10;
    private Deque<Float> windowAX = new LinkedList<>();
    private Deque<Float> windowBX = new LinkedList<>();
    private Deque<Float> windowCX = new LinkedList<>();
    private Deque<Float> windowAY = new LinkedList<>();
    private Deque<Float> windowBY = new LinkedList<>();
    private Deque<Float> windowCY = new LinkedList<>();

    @Override
    public void process() {


        if (windowAX.size() <= samples) {
            windowAX.addLast(Z.getPhsA().getcVal().getVectorX().getF().getValue());
            windowBX.addLast(Z.getPhsB().getcVal().getVectorX().getF().getValue());
            windowCX.addLast(Z.getPhsC().getcVal().getVectorX().getF().getValue());
            windowAY.addLast(Z.getPhsA().getcVal().getVectorY().getF().getValue());
            windowBY.addLast(Z.getPhsB().getcVal().getVectorY().getF().getValue());
            windowCY.addLast(Z.getPhsC().getcVal().getVectorY().getF().getValue());
        } else {
            windowAX.addLast(Z.getPhsA().getcVal().getVectorX().getF().getValue());
            windowAX.removeFirst();
            windowBX.addLast(Z.getPhsB().getcVal().getVectorX().getF().getValue());
            windowBX.removeFirst();
            windowCX.addLast(Z.getPhsC().getcVal().getVectorX().getF().getValue());
            windowCX.removeFirst();
            windowAY.addLast(Z.getPhsA().getcVal().getVectorY().getF().getValue());
            windowAY.removeFirst();
            windowBY.addLast(Z.getPhsB().getcVal().getVectorY().getF().getValue());
            windowBY.removeFirst();
            windowCY.addLast(Z.getPhsC().getcVal().getVectorY().getF().getValue());
            windowCY.removeFirst();
        }
        if (BlkZn.getStVal().getValue()) {
            if ((Math.abs((windowAX.getLast() - windowAX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowBX.getLast() - windowBX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowCX.getLast() - windowCX.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowAY.getLast() - windowAY.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowBY.getLast() - windowBY.getFirst()) / 2500) > .0001f) ||
                    (Math.abs((windowCY.getLast() - windowCY.getFirst()) / 2500) > .0001f)) {
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

    public WYE getZ() {
        return Z;
    }

    public void setZ(WYE z) {
        Z = z;
    }
}
