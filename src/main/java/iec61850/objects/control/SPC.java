package iec61850.objects.control;

import iec61850.objects.samples.Attribute;
import iec61850.objects.samples.Quality;
import iec61850.objects.samples.TimeStamp;

/**
 * Недублированное управление и состояние
 * Блокировка выключателя
 */
public class SPC {

    private Attribute<Boolean> stVal = new Attribute<>(false);

    private Quality q = new Quality();

    private TimeStamp t = new TimeStamp();

    public Attribute<Boolean> getStVal() {
        return stVal;
    }

    public void setStVal(Attribute<Boolean> stVal) {
        this.stVal = stVal;
    }
}
