package iec61850.objects.samples;

import iec61850.objects.samples.Source.Source;
import iec61850.objects.samples.validity.Validity;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержащий информацию о качестве информации с сервера
 *
 * @see Attribute - обертка для значений
 * @see Validity - определитель ликвидности информации
 * @see Source - источник информации, полученный из процесса или может быть замещенным значением
 */
public class Quality {

    private Attribute<Validity> validity = new Attribute<>(Validity.GOOD);

    /**
     * Основание для признания атрибута дествительным или сомнительным
     * По идентификаторам
     */
    private List<Attribute<Boolean>> detailQual = new ArrayList<>();

    /**
     * Используется с информацией об измеряемых величинах
     * указывает что значение атрибута находиться за пределами выбранного типа данных
     */
    private Attribute<Boolean> overflow = new Attribute<>(false);
    private Attribute<Boolean> outOfRange = new Attribute<>(false); //Указывает что атрибут находить за пределами предопределенного диапозона значений
    private Attribute<Boolean> badReference = new Attribute<>(false); //Значение может быть неправильным из-за неоткалиброванного источника информации
    private Attribute<Boolean> oscillatory = new Attribute<>(false); //Для предотвращения избыточной нагрузки на канал информации
    private Attribute<Boolean> failure = new Attribute<>(false); //Показывает что контрольная функция выявила внутреннее или внешнее повреждение
    private Attribute<Boolean> oldData = new Attribute<>(false); //Ошибка при долгом необновленнии
    private Attribute<Boolean> inconsistent = new Attribute<>(false); //Показывает что функция оценки выявила несогласовонность

    private Attribute<Boolean> inaccurate = new Attribute<>(false); //Показывает что значение не удовлетворяет установленной точности источника

    private Source source = Source.PROCESS;

    private Attribute<Boolean> test = new Attribute<>(false); //Используется для указания тестового значения, неиспользуемого в операционных целях

    private Attribute<Boolean> operatorBlocked = new Attribute<>(false); //Устанавливается в случае если оператор блокирует дальнейшее обновление значения

    /**
     * Значение по умолчанию для всех параметров устанавливается в дефолтное значение
     * если функционал не поддерживается
     */
    public Quality() {
        detailQual.add(overflow);
        detailQual.add(outOfRange);
        detailQual.add(badReference);
        detailQual.add(oscillatory);
        detailQual.add(failure);
        detailQual.add(oldData);
        detailQual.add(inconsistent);
        detailQual.add(inaccurate);
    }

}
