import java.io.IOException;

public class GeneticAlgorithm {
	private Population pop = null;
	private int popSize = 1000;
	
	CandidateSolution bestSolution = new CandidateSolution(false);
	
	public GeneticAlgorithm() throws IOException {
		pop = new Population(popSize);
		System.out.println(pop.getFittest().getEnergy());
		bestSolution = runAlgrithm();
	}
	
	public CandidateSolution runAlgrithm() throws IOException {
		int t = 0;
		int idealScore = 100;
		
		while((pop.getFittest().getEnergy() > 200) || t < 2 ) {
			for (int i=0; i < (popSize / 2) -2; i++) {
				pop.crossover();
			}
			//pop.mutate();
			System.out.println(pop.getFittest().getEnergy());
			pop.selectParents();
			t++;
		}
		System.out.println(pop.getFittest().getEnergy());
		return pop.getFittest();
	}
}
