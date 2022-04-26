package iec61850.objects.measurements;

import iec61850.objects.samples.AnalogueValue;

/**
 * Содержит информацию о векторе
 */
public class Vector {

    private AnalogueValue mag = new AnalogueValue(); //Значение модуля комплексного значения
    private AnalogueValue ang = new AnalogueValue(); //Значение угла
    // Ортогональные состовляющие
    private AnalogueValue vectorX = new AnalogueValue();
    private AnalogueValue vectorY = new AnalogueValue();

    public void setByOrt(float X, float Y) {
        getVectorX().getF().setValue(X);
        getVectorY().getF().setValue(Y);
        mag.getF().setValue((float) Math.sqrt(X * X + Y * Y));
        ang.getF().setValue((float) Math.toDegrees(Math.atan2(Y, X)));
    }

    public AnalogueValue getVectorX() {
        return vectorX;
    }

    public void setVectorX(AnalogueValue vectorX) {
        this.vectorX = vectorX;
    }

    public AnalogueValue getVectorY() {
        return vectorY;
    }

    public void setVectorY(AnalogueValue vectorY) {
        this.vectorY = vectorY;
    }

    public AnalogueValue getMag() {
        return mag;
    }

    public void setMag(float magnitude) {
        mag.getF().setValue(magnitude);
        reOrt();
    }

    public AnalogueValue getAng() {
        return ang;
    }

    public void setAng(float ang) {
        this.ang.getF().setValue(ang);
        reOrt();
    }

    private void reOrt() {
        vectorX.getF().setValue((float) (mag.getF().getValue() * Math.cos(Math.toRadians(ang.getF().getValue()))));
        vectorY.getF().setValue((float) (mag.getF().getValue() * Math.sin(Math.toRadians(ang.getF().getValue()))));
    }

}
