package iec61850.objects.samples;

/**
 * Общий класс данных выборочных значений
 * Используется для представления выборок мгновенных аналоговых значений
 * Аналоговое значение оборачаивается в AnalogueValue
 *
 * @see AnalogueValue
 */
public class SAV {

    /**
     * Поле представляемого значения
     */
    private AnalogueValue instMag = new AnalogueValue();

    private Quality q = new Quality();

    /**
     * Функция для получения аналогового значения
     *
     * @return AnalogueValue
     */
    public AnalogueValue getInstMag() {
        return instMag;
    }

    /**
     * Фнкция для определения аналогового значения
     *
     * @param instMag - аналоговое мгновенное значение
     */
    public void setInstMag(AnalogueValue instMag) {
        this.instMag = instMag;
    }

}
