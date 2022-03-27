package iec61850.objects.protection;

import iec61850.objects.samples.Attribute;
import iec61850.objects.samples.Quality;
import iec61850.objects.samples.TimeStamp;

/**
 * Сведения о пуске защиты
 */
public class ACT {

    /**
     * Сведения об общем и пофазном срабатывании защиты
     */
    private Attribute<Boolean> general = new Attribute<>(false);
    private Attribute<Boolean> phsA = new Attribute<>(false);
    private Attribute<Boolean> phsB = new Attribute<>(false);
    private Attribute<Boolean> phsC = new Attribute<>(false);

    private Quality q = new Quality();

    private TimeStamp t = new TimeStamp();

    public Attribute<Boolean> getGeneral() {
        return general;
    } //

    public void setGeneral(Attribute<Boolean> general) {
        this.general = general;
    } //

    public Attribute<Boolean> getPhsA() {
        return phsA;
    } //

    public void setPhsA(Attribute<Boolean> phsA) {
        this.phsA = phsA;
    } //

    public Attribute<Boolean> getPhsB() {
        return phsB;
    } //

    public void setPhsB(Attribute<Boolean> phsB) {
        this.phsB = phsB;
    } //

    public Attribute<Boolean> getPhsC() {
        return phsC;
    } //

    public void setPhsC(Attribute<Boolean> phsC) {
        this.phsC = phsC;
    } //

}
