package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

import java.util.Deque;
import java.util.LinkedList;

/**
 * MSD - фильтр средневыпрямленного значения
 * Сумма выборки 20 значений по модулю, деленная на длину выборки
 */
public class MSD extends Filter {

    private float samples = 20;
    private Deque<Float> window = new LinkedList<>();


    @Override
    public void process(SAV sav, Vector vector) {

        if (window.size() < samples) {
            window.addLast(sav.getInstMag().getF().getValue());
        } else {
            window.addLast(sav.getInstMag().getF().getValue());
            window.removeFirst();
        }

        vector.getMag().getF().setValue(window.stream()
                .map(Math::abs)
                .reduce(Float::sum)
                .map(i -> i / window.size())
                .orElse(0f));

    }
}
