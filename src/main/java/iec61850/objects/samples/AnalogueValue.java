package iec61850.objects.samples;

/**
 * Обертка для аналоговых значений типа float
 * Значение хранится в обертке Attribute
 *
 * @see Attribute
 */
public class AnalogueValue {

    /**
     * Поле оборачиваемого значения
     */
    private Attribute<Float> f = new Attribute<>(0f);

    /**
     * Функция получения отрибута
     *
     * @return Attribute<Float>
     */
    public Attribute<Float> getF() {
        return f;
    }

    /**
     * Функция определения атрибута
     *
     * @param f - аналоговое значение
     */
    public void setF(Attribute<Float> f) {
        this.f = f;
    }
}
