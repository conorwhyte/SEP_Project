import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class Population {
	private ArrayList<CandidateSolution> solutions = new ArrayList<CandidateSolution>();
	private ArrayList<CandidateSolution> parents =  new ArrayList<CandidateSolution>();
	private int populationSize;
	private Random rand = new Random();
	private String fileName = "";
	//private double mutationRate = 0.001;
	
	public Population(int size, String filename) throws IOException {
		populationSize = size;
		fileName = filename;
		initiliazePopulation();
		selectParents();
	}
	
	private void initiliazePopulation() throws IOException {
		CandidateSolution s = new CandidateSolution(true,fileName);
		for(int i=0; i<populationSize; i++) {
			s = new CandidateSolution(true,fileName);
			solutions.add(s);
		}
	}
	
	public void selectParents() {
		sortSolutions();
		System.out.println(solutions.size());
		for(int i = 0; i < 100; i++){
		    parents.add(solutions.get(i));
		 }
		removeOthers();
	}

	public void crossover() throws IOException {
		CandidateSolution cs1 = getRandomParent();
		CandidateSolution cs2 = getRandomParent();
		CandidateSolution csNew = new CandidateSolution(false, fileName);
		int randomNum = rand.nextInt((51 - 0)) + 0;
		PreferenceTable table = new PreferenceTable("tabfile.txt");
		int count = 0;
		Hashtable<String, StudentEntry> studentEntries = table.getAllStudentEntries();
	    Enumeration<StudentEntry> e = studentEntries.elements();
	    StudentEntry entry; 
	    while(e.hasMoreElements()) {
	    	entry = e.nextElement();
			if(count < randomNum) {
				csNew.setAssignment(entry, cs1.getAssignmentForName(entry.getStudentName()));
			} else {
				csNew.setAssignment(entry, cs2.getAssignmentForName(entry.getStudentName()));
			}
			count++;
		}
	    solutions.add(csNew);
	}
	
	public void mutate() {
		CandidateSolution cs1 = getRandomSolution();
		cs1.GAChange();
	}
	
	private void removeOthers() {
		for(int i = solutions.size()-1; i > 100; i--){
		    solutions.remove(i);
		 }
	}
	
	public CandidateSolution getFittest() {
		sortSolutions();
		return  solutions.get(0);
	}
	
	private CandidateSolution getRandomParent() {
		Random rand = new Random();
		int randomNum = rand.nextInt((parents.size() - 1) + 1) + 0;
		return parents.get(randomNum);
	}
	
	private CandidateSolution getRandomSolution() {
		Random rand = new Random();
		int randomNum = rand.nextInt((solutions.size() - 1) + 1) + 0;
		return solutions.get(randomNum);
	}
	
	private void sortSolutions() {
		solutions.sort( (cs1, cs2) -> { 
			return Integer.valueOf(cs1.getEnergy()).compareTo(Integer.valueOf(cs2.getEnergy()));}); 
	}
	
}

