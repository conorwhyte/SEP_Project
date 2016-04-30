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
        double temp = 10000;
        double coolingRate = 0.0003;
        int  bestEnergy = cs.getEnergy();
       
        // Loop until system has cooled
       /* while (temp > 1) {
            // Creates new neighbour 
        	cs.makeChange();
        	
        	if(acceptanceProbability(bestEnergy, cs.getEnergy(), temp) > Math.random() ) {
        		bestEnergy = cs.getEnergy();
        		temp *= 1-coolingRate;
        	} else {
        		cs.undoChange();
        	}
        }*/
        //bestSolution.printSolution(); 
        
        //Genetic Algoritms 
        int i = 0 ; 
        while (i < 100000){
        	CandidateSolution newCs = new CandidateSolution(table);
        
        	//CandidateSolution child = cs ; 
        	//child.mergeSolutions(child, newCs); 	//creates the childs
      
        	
        		cs.mergeSolutions(cs, newCs);
        		bestEnergy = cs.getEnergy();
        		cs.makeChange();
        		
        		if (cs.getEnergy() > bestEnergy){
        			cs.undoChange();
        		}
        	
        	
        	i++ ; 
        }
    	
        System.out.println("Final solution Energy: " + bestEnergy);
        //cs.printSolution();
       // cs.printPreferences();
	}
}

