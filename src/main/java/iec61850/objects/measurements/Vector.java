package iec61850.objects.measurements;

import iec61850.objects.samples.AnalogueValue;

/**
 * Содержит информацию о векторе
 */
public class Vector {

    private AnalogueValue mag = new AnalogueValue(); //Значение модуля комплексного значения
    private AnalogueValue ang = new AnalogueValue(); //Значение угла

    public AnalogueValue getMag() {
        return mag;
    }

    public void setMag(AnalogueValue mag) {
        this.mag = mag;
    }

    public AnalogueValue getAng() {
        return ang;
    }

    public void setAng(AnalogueValue ang) {
        this.ang = ang;
    }
}
