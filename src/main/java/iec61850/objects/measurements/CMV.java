package iec61850.objects.measurements;

import iec61850.objects.samples.Quality;
import iec61850.objects.samples.TimeStamp;

/**
 * комплексное измеренное значение
 *
 * @see Vector - вектор со значениями амплитуды и угла
 */
public class CMV {

    private Vector cVal = new Vector();

    private Quality q = new Quality();

    private TimeStamp t = new TimeStamp();

    public Vector getcVal() {
        return cVal;
    }

    public void setcVal(Vector cVal) {
        this.cVal = cVal;
    }

    public Float getOrtX() {
        return cVal.getVectorX().getF().getValue();
    }

    public Float getOrtY() {
        return cVal.getVectorY().getF().getValue();
    }

    public Float getMag() {
        return cVal.getMag().getF().getValue();
    }
}
