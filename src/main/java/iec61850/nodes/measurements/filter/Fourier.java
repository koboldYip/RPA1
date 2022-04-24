package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

/**
 * Фильтр Фурье
 */
public class Fourier extends Filter {

    private int samples = 80;
    private int count = 0;
    private float[] sin = new float[samples];
    private float[] cos = new float[samples];
    private float k = (float) 2 / samples;
    private float[] BufferX = new float[samples];
    private float[] BufferY = new float[samples];
    private float X = 0;
    private float Y = 0;

    public Fourier() {
        for (int v = 0; v < samples; v++) {
            sin[v] = (float) Math.sin(2 * Math.PI * ((float) v / samples));
            cos[v] = (float) Math.cos(2 * Math.PI * ((float) v / samples));
        }
    }

    @Override
    public void process(SAV sav, Vector vector) {

        X += k * (BufferX[count] - sav.getInstMag().getF().getValue() * sin[count]);
        BufferX[count] = sav.getInstMag().getF().getValue() * sin[count];

        Y += k * (BufferY[count] - sav.getInstMag().getF().getValue() * cos[count]);
        BufferY[count] = sav.getInstMag().getF().getValue() * cos[count];

        vector.setByOrt(X, Y);

        if (++count >= samples) count = 0;

    }
}
