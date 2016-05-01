import java.io.IOException;
import java.util.*;

public class CandidateSolution {
	
	private PreferenceTable table; 
	private Hashtable<StudentEntry, CandidateAssignment> assignments = new Hashtable<StudentEntry, CandidateAssignment>();
	static final int standardPenalty = 1000;
	private CandidateAssignment lastChanged;
	String filename = "";
	
	public CandidateSolution(boolean bool, String filename) throws IOException {
		table = new PreferenceTable(filename);
		if(bool) {		// Only initialise solution with assignments if boolean is set to true.
			createCandidateAssignments();
		}	
	}
	
	private void createCandidateAssignments() {
		Hashtable<String, StudentEntry> studentEntries = table.getAllStudentEntries();
	    Enumeration<StudentEntry> e = studentEntries.elements();
	    StudentEntry entry; 
	    while(e.hasMoreElements()) {
	    	entry = e.nextElement();
	    	CandidateAssignment assignment = new CandidateAssignment(entry);
	    	assignments.put(entry, assignment);
	    } 
	}

	public CandidateAssignment getAssignmentFor(StudentEntry student) {
		return assignments.get(student);
	}
	
	public CandidateAssignment getAssignmentForName(String name) {
		 for (StudentEntry key: assignments.keySet()) {
			   if(key.getStudentName().equals(name)) {
				   return assignments.get(key);
			   } 
		 }
		 return lastChanged;  // Should never reach here.
	}
	
	public CandidateAssignment getRandomAssignment() {
		return getAssignmentFor(table.getRandomStudent());
	}

	/*These Methods are used to calculate energy and fitness */
	
	public int getFitness() {
		return getEnergy() * -1;
	}
	
	public int getEnergy() {
		Enumeration<CandidateAssignment> e = assignments.elements();
		CandidateAssignment assignment;
		int totalEnergy = 0;
	    while(e.hasMoreElements()) {
	    	assignment = e.nextElement();
	    	totalEnergy += assignment.getEnergy();
	    } 
	   
	    int totalPenalties = getPenalties();
	    totalEnergy += totalPenalties;
		return totalEnergy;
	}
	
	public int getPenalties() {
		Hashtable<String, String> assignedProjects = new Hashtable<String, String>();
	    Enumeration<CandidateAssignment> e = assignments.elements();
	    int totalPenalties = 0;
	    while(e.hasMoreElements()) {
	    	CandidateAssignment current = e.nextElement();
	    	if(assignedProjects.contains(current.getAssignedProject())) {
	    		totalPenalties += standardPenalty;
	    	}
	    	assignedProjects.put(current.getAssignedProject(), current.getAssignedProject());
	    } 
		return totalPenalties;
	}
	
	/* Methods here are used to manipulate the candidate solution for GA and SA */
	
	public void setAssignment(StudentEntry se, CandidateAssignment ca) {
		assignments.put(se,ca);
	}
	
	public void GAChange() {
		 Object[] keys = assignments.keySet().toArray();
	     Object key = keys[new Random().nextInt(keys.length)];
	     CandidateAssignment ca;
	     ca = assignments.get(key);
	    // System.out.print("MUTATED");
	     ca.randomizeAssignment();
	}
	
	
	public void makeChange() {
		CandidateAssignment ca;
		ca = getRandomAssignment();
		lastChanged = ca;
		ca.randomizeAssignment();
	}
	
	public void undoChange() {
		lastChanged.undoChange();
	}
	
	/* Methods used to print */
	
	public void printPreferences() {
		int first, second,third, fourth, fifth, sixth, seventh, eighth, ninth, tenth;
	    first = second  = third = fourth = fifth = sixth = seventh = eighth = ninth = tenth =0;
		
		Enumeration<CandidateAssignment> e = assignments.elements();
		while(e.hasMoreElements()) {
			CandidateAssignment current = e.nextElement();
			switch(current.getAssignmentRank()){
			    case 0: first++; break;
			    case 1: second++; break;
			    case 2: third++; break;
			    case 3: fourth++; break;
			    case 4: fifth++; break;
			    case 5: sixth++; break;
			    case 6: seventh++; break;
			    case 7: eighth++; break;
			    case 8: ninth++; break;
			    case 9: tenth++; break;   
			}
		}
		System.out.println("first Preferences awarded: " + first + "\nSecond Preferences awarded: " + second + "\nThird Preferences awarded: " + third + "\nFourth Preferences awarded: " + fourth + "\nFifth Preferences awarded: " + fifth + "\nSixth Preferences awarded: " + sixth + "\nSeventh Preferences awarded: " + seventh + "\nEight Preferences awarded: " + eighth + "\nNinth Preferences awarded: " + ninth + "\nTenth Preferences awarded: " + tenth);
	}
	
	public String[][] printSolution() {
		Enumeration<StudentEntry> enumKey = assignments.keys();
		String[][] data = new String[100][100];
		System.out.println(assignments.size());
		int i = 0;
		while(enumKey.hasMoreElements()) {
		    StudentEntry key = enumKey.nextElement();
		    CandidateAssignment val = assignments.get(key);
		    data[i][0] = key.getStudentName();
		    data[i][1] = val.getAssignedProject();
		    System.out.println("Student: " + key.getStudentName());
		    System.out.println("Assignemt: " + val.getAssignedProject() + "\n");
		}
		return data;
	}
}