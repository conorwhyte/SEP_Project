import java.io.IOException;

public class GeneticAlgorithm {
	private Population pop = null;
	private int popSize = 140;
	
	CandidateSolution bestSolution = new CandidateSolution(false);
	
	public GeneticAlgorithm() throws IOException {
		pop = new Population(popSize);
		bestSolution = runAlgrithm();
	}
	
	public CandidateSolution runAlgrithm() throws IOException {
		int t = 0;
		int currentEnergy, bestEnergy;
		currentEnergy = 1;
		bestEnergy = 100000;
		while((pop.getFittest().getEnergy() > 00) || t < 2 ) {
			for (int i=0; i < (popSize / 2 ) -2; i++) {
				pop.crossover();
			}
			/*if(count == 150) {
				pop.mutate();
			} */
			System.out.println("Generation: "+ t + " Energy: " + pop.getFittest().getEnergy());
			currentEnergy = pop.getFittest().getEnergy();
		
			pop.selectParents();
			t++;
		}
		System.out.println(pop.getFittest().getEnergy());
		return pop.getFittest();
	}
}
