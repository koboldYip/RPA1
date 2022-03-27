package iec61850.objects.control;

import iec61850.objects.control.Models.CtlModels;
import iec61850.objects.samples.Attribute;
import iec61850.objects.samples.Quality;
import iec61850.objects.samples.TimeStamp;

/**
 * Целочисленное состояние
 * Счетчик переключений выключателя
 */
public class INC {

    private Attribute<Integer> stVal = new Attribute<>(0);

    private Quality q = new Quality();

    private TimeStamp t = new TimeStamp();

    private Attribute<CtlModels> ctlModel = new Attribute<>(CtlModels.DIRECT_WITH_NORMAL_SECURITY);


    public Attribute<Integer> getStVal() {
        return stVal;
    }

    public void increase() {
        stVal.setValue(stVal.getValue() + 1);
    }

    public void setStVal(Attribute<Integer> stVal) {
        this.stVal = stVal;
    }
}
