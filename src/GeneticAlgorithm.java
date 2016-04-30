import java.io.IOException;

public class GeneticAlgorithm {
	private Population pop = null;
	private int popSize = 140;
	
	CandidateSolution bestSolution = new CandidateSolution(false);
	
	public GeneticAlgorithm() throws IOException {
		pop = new Population(popSize);
		System.out.println(pop.getFittest().getEnergy());
		bestSolution = runAlgrithm();
	}
	
	public CandidateSolution runAlgrithm() throws IOException {
		int t = 0;
		int currentEnergy, bestEnergy;
		currentEnergy = 1;
		bestEnergy = 100000;
		int count =0;
		while((pop.getFittest().getEnergy() > 200) || t < 2 ) {
			count++;
			for (int i=0; i < (popSize / 2) -2; i++) {
				pop.crossover();
			}
			if(count == 150) {
				pop.mutate();
			}
			
			System.out.println("Generation: "+ t + " Energy: " + pop.getFittest().getEnergy());
			currentEnergy = pop.getFittest().getEnergy();
			if(currentEnergy != bestEnergy) {
				count = 0;
				bestEnergy = currentEnergy;
			}
		
			pop.selectParents();
			t++;
		}
		System.out.println(pop.getFittest().getEnergy());
		return pop.getFittest();
	}
}
