/**
 * 
 */
package edu.neu.coe.info6205.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gaurang Davda
 *
 */
public class Population {

	public Population(int populationNumber) {
		count = populationNumber;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the chromosomeList
	 */
	public List<Chromosome> getChromosomeList() {
		if (chromosomeList == null) {
			chromosomeList = new ArrayList<>();
		}
		return chromosomeList;
	}

	/**
	 * This is the population count
	 */
	private int count;

	/**
	 * This is a gene pool
	 */
	private List<Chromosome> chromosomeList;
}
