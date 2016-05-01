import java.io.IOException;

public class GeneticAlgorithm {
	private Population pop = null;
	private int popSize = 140;
	String filename = "";
	CandidateSolution bestSolution = null;
	
	public GeneticAlgorithm(String fileName) throws IOException {
		pop = new Population(popSize,fileName);
		bestSolution = runAlgrithm();
		filename = fileName;
		bestSolution = new CandidateSolution(false, filename);
	}
	
	public CandidateSolution runAlgrithm() throws IOException {
		int t = 0;
		int currentEnergy, bestEnergy;
		currentEnergy = 1;
		bestEnergy = 100000;
		boolean flag = false;
		int counter = 0;
		int temp = 0;
		while(!flag || t < 2 ) {
			for (int i=0; i < (popSize / 2 ) -2; i++) {
				pop.crossover();
			}
			/*if(count == 150) {
				pop.mutate();
			} */
			System.out.println("Generation: "+ t + " Energy: " + pop.getFittest().getEnergy());
			currentEnergy = pop.getFittest().getEnergy();
			if(pop.getFittest().getEnergy() == temp){
				counter++;
			}
			else{
				counter = 0;
				temp = pop.getFittest().getEnergy();
			}
			if(counter > 250){
				flag = true;
			}
			pop.selectParents();
			t++;
		}
		System.out.println(pop.getFittest().getEnergy());
		return pop.getFittest();
	}
}
