package iec61850.objects.protection;

import iec61850.objects.samples.AnalogueValue;

/**
 * Класс задания значения аналогового сигнала
 * Используется для уставки токовой защиты
 */
public class ASG {

    private AnalogueValue setMag = new AnalogueValue(); //Значение параметра аналогово сигнала

    public ASG(Float setMag) {
        this.setMag.getF().setValue(setMag);
    } //Первичное определение значения

    public AnalogueValue getSetMag() {
        return setMag;
    } //Получение значения
}
