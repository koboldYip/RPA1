package iec61850.objects.protection;

import iec61850.objects.protection.dir.Direction;
import iec61850.objects.samples.Attribute;

/**
 * Сведения о направлении срабатывания защиты
 */
public class ACD {

    private Attribute<Direction> dirGeneral = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsA = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsB = new Attribute<>(Direction.UNKNOWN);
    private Attribute<Direction> dirPhsC = new Attribute<>(Direction.UNKNOWN);

}
