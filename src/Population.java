import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class Population {
	private ArrayList<CandidateSolution> solutions = new ArrayList();
	private ArrayList<CandidateSolution> parents =  new ArrayList();
	private int populationSize;
	private Random rand = new Random();
	
	private double mutationRate = 0.001;
	
	public Population(int size) throws IOException {
		populationSize = size;
		initiliazePopulation();
		selectParents();
	}
	
	private void initiliazePopulation() throws IOException {
		CandidateSolution s = new CandidateSolution(true);
		for(int i=0; i<populationSize; i++) {
			s = new CandidateSolution(true);
			solutions.add(s);
		}
	}
	
	private void sortSolutions() {
		solutions.sort( (cs1, cs2) -> { 
			return Integer.valueOf(cs1.getEnergy()).compareTo(Integer.valueOf(cs2.getEnergy()));}); 
	}
	
	public void selectParents() {
		sortSolutions();
		System.out.println(solutions.size());
		for(int i = 0; i < 100; i++){
		    parents.add(solutions.get(i));
		 }
		removeOthers();
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
	
	public void mutate() {
			CandidateSolution cs1 = getRandomSolution();
			cs1.GAChange();
	}
	
	public void crossover() throws IOException {
		CandidateSolution cs1 = getRandomParent();
		CandidateSolution cs2 = getRandomParent();
		CandidateSolution csNew = new CandidateSolution(false);
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
	   //System.out.println("CS1: " + cs1.getEnergy() + " CS2: " + cs2.getEnergy() + " New: "+ csNew.getEnergy() + " count: " + count + " random num: " + randomNum);
	   
	    solutions.add(csNew);
	}
}

