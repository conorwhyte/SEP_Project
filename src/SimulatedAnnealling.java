import java.io.IOException;

public class SimulatedAnnealling {
	
	private CandidateSolution cs;
	private double temp = 1000000;
    private double coolingRate = 0.0001;
	private String[][] data = new String[100][100];
    
	public SimulatedAnnealling(String filename) throws IOException {
		cs = new CandidateSolution(true, filename);
		//bestSolution = new CandidateSolution(true);
	    System.out.println("Initial solution: " + cs.getEnergy());
	    runAlgorithm();
	}

	public void runAlgorithm() {
		int  bestEnergy = cs.getEnergy();
	    // Loop until system has cooled
	    while (temp > 1) {
	        // Create new neighbour 
	    	cs.makeChange();
	    	
	    	if(acceptanceProbability(bestEnergy, cs.getEnergy(), temp) > Math.random() ) {
	    		bestEnergy = cs.getEnergy();
	    		temp *= 1-coolingRate;
	    	} else {
	    		cs.undoChange();
	    	}
	    }

	    data = cs.printSolution(); 
	   

	    System.out.println("Final solution Energy: " + bestEnergy);

	}
	
	public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
	public String[][] getData(){
		return data;
	}
}
