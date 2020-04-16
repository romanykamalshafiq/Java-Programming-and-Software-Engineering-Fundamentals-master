

public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;
    
    public DepthFilter (double minDep, double maxDep) {
        this.minDep = minDep;
        this.maxDep = maxDep;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        boolean ans = (qe.getDepth() >= minDep) && (qe.getDepth() <= maxDep);
        return ans; 
    }
    
    public String getName() {
        return "Depth";
    }
}
