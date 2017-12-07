/**
 * 
 */
package edu.neu.coe.info6205.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Gaurang Davda
 *
 */
public class Population {

	public Population(int populationNumber) {
		count = populationNumber;
	}

	public Chromosome bestChromosome() {
		Chromosome minimumFitness = chromosomeList.get(0);
		for (Chromosome chromosome : chromosomeList) {
			if (chromosome.getFitness() < minimumFitness.getFitness()) {
				minimumFitness = chromosome;
			}
		}
		return minimumFitness;
	}

	/**
	 * This method performs a mutation of the gene by assigning a random value
	 * to a random gene
	 */
	public void performMutation() {
		Random rnd = new Random();
		List<Chromosome> newChromoList = new ArrayList<>();
		for (Chromosome chromo : chromosomeList) {
			int[] gene = chromo.getGene();
			int index = 0;
			do {
				index = rnd.nextInt(gene.length);
			} while (chromo.getBaseGene()[index] != 0);
			gene[index] = rnd.nextInt((int) Math.sqrt(gene.length)) + 1;
			Chromosome newChromo = new Chromosome(chromo.getBaseGene(), gene);
			newChromoList.add(newChromo);
		}
		chromosomeList.clear();
		chromosomeList.addAll(newChromoList);
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
