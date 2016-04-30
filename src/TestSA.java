import java.io.IOException;

public class TestSA {
	public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
	
	public static void main(String[] args) throws IOException{
		PreferenceTable table = new PreferenceTable("tabfile.txt");
		CandidateSolution cs = new CandidateSolution(table);
		CandidateSolution bestSolution = new CandidateSolution(table);
        System.out.println("Initial solution: " + cs.getEnergy());
        double temp = 1000000;
        double coolingRate = 0.0003;
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
        bestSolution.printSolution(); 
        
        //Genetic Algoritms 

        System.out.println("Final solution Energy: " + bestEnergy);
        //cs.printSolution();
       // cs.printPreferences();
	}
}

