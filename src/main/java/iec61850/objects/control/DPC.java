package iec61850.objects.control;

import iec61850.objects.control.Models.CtlModels;
import iec61850.objects.samples.Attribute;
import iec61850.objects.samples.Quality;
import iec61850.objects.samples.TimeStamp;

/**
 * Дублированное управление состоянием
 * Сигнал отключения и положения выключателя
 */
public class DPC {

    private Attribute<Boolean> stVal = new Attribute<>(false);

    private Quality q = new Quality();

    private TimeStamp t = new TimeStamp();

    private Attribute<CtlModels> ctlModel = new Attribute<>(CtlModels.DIRECT_WITH_NORMAL_SECURITY);

    public Attribute<Boolean> getStVal() {
        return stVal;
    }

    public void setStVal(Attribute<Boolean> stVal) {
        this.stVal = stVal;
    }

}
