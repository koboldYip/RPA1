package iec61850.objects.protection;

import iec61850.objects.samples.Attribute;

/**
 * Класс установки целочисленного состояния
 * определяет общие INT32 значения
 * используется для выдержки времени
 */
public class ING {

    private Attribute<Integer> setVal = new Attribute<>(0); //Настройка параметра состояния

    public ING(Integer setVal) {
        this.setVal.setValue(setVal);
    } //Первичное определение параметра состояния

    public Attribute<Integer> getSetVal() {
        return setVal;
    } //Получение значения состояния

    public void setSetVal(Attribute<Integer> setVal) {
        this.setVal = setVal;
    } //Определение
}
