package iec61850.objects.measurements;

import java.util.ArrayList;
import java.util.List;

public class HWYE {

    private List<CMV> phsAHar = new ArrayList<>();
    private List<CMV> phsBHar = new ArrayList<>();
    private List<CMV> phsCHar = new ArrayList<>();

    public List<CMV> getPhsAHar() {
        return phsAHar;
    }

    public void setPhsAHar(List<CMV> phsAHar) {
        this.phsAHar = phsAHar;
    }

    public List<CMV> getPhsBHar() {
        return phsBHar;
    }

    public void setPhsBHar(List<CMV> phsBHar) {
        this.phsBHar = phsBHar;
    }

    public List<CMV> getPhsCHar() {
        return phsCHar;
    }

    public void setPhsCHar(List<CMV> phsCHar) {
        this.phsCHar = phsCHar;
    }
}
