package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

/**
 * Фильтр Фурье
 */
public class Fourier extends Filter {

    private int samples = 80;
    private int count = 0;
    private int harmonic = 0;
    private float k = (float) 2 / samples;
    private float[] BufferX = new float[samples];
    private float[] BufferY = new float[samples];
    private float X = 0;
    private float Y = 0;

    public Fourier(int harmonic, int samples) {
        this.samples = samples;
        this.harmonic = harmonic;
        this.BufferX = new float[samples];
        this.BufferY = new float[samples];
    }

    public Fourier() {
        this(1, 20);
    }


    @Override
    public void process(SAV sav, Vector vector) {

        float sin = (float) Math.sin(harmonic * 2 * Math.PI * ((float) count / samples));
        float cos = (float) Math.cos(harmonic * 2 * Math.PI * ((float) count / samples));

        X += k * (sav.getInstMag().getF().getValue() * sin - BufferX[count]);
        BufferX[count] = sav.getInstMag().getF().getValue() * sin;

        Y += k * (sav.getInstMag().getF().getValue() * cos - BufferY[count]);
        BufferY[count] = sav.getInstMag().getF().getValue() * cos;

        vector.setByOrt(X, Y);

        if (++count >= samples) count = 0;

    }
}
