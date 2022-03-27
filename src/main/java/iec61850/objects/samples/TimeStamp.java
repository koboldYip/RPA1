package iec61850.objects.samples;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс предоставляет информацию о времени измерения
 */
public class TimeStamp {

    private Attribute<Integer> secondSinceEpoch = new Attribute<>(0); //Время в секундах начиная с 1.1.1970

    private Attribute<Integer> fractionOfSecond = new Attribute<>(0); //Доля текущей секунды в которую было определено значени TimeStamp

    private List<Attribute<Boolean>> timeQuality = new ArrayList<>(); //Обеспечивает информацию о качестве

    private Attribute<Boolean> leapSecondsKnown = new Attribute<>(Boolean.FALSE); //Указывает об учете всех имеющих место коррециях секунды
    private Attribute<Boolean> clockFailure = new Attribute<>(Boolean.FALSE); //Источник времени передающего источника ненадежен
    private Attribute<Boolean> timeAccuracy = new Attribute<>(Boolean.FALSE); //Класс точности времени источника времени по отношению к UTC

    /**
     * Значения определяются по умолчанию так как не используются
     */
    public TimeStamp() {
        timeQuality.add(leapSecondsKnown);
        timeQuality.add(clockFailure);
        timeQuality.add(timeAccuracy);
    }
}
