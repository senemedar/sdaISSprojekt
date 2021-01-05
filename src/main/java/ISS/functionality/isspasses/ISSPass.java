package ISS.functionality.isspasses;

public class ISSPass {

    private long risetime;
    private int duration;

    public ISSPass() {
    }

    public ISSPass(long risetime, int duration) {
        this.risetime = risetime;
        this.duration = duration;
    }

    public long getRisetime() {
        return risetime;
    }

    public void setRisetime(long risetime) {
        this.risetime = risetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Pass{" +
                "risetime=" + risetime +
                ", duration=" + duration +
                '}';
    }
}
