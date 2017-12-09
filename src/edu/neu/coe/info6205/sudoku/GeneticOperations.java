/**
 * 
 */
package edu.neu.coe.info6205.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author Gaurang Davda
 *
 */
public class GeneticOperations {

	Random rnd = new Random(0L);

	public Chromosome bestChromosome(List<Chromosome> chromosomeList) {
		sort(chromosomeList);
		return chromosomeList.get(0);
	}

	public void performCrossover(List<Chromosome> chromosomeList, int populationCount) {

		int size = chromosomeList.size();
		for (int i = 0; i < size; i += 2) {
			if (rnd.nextBoolean()) {
				crossover(chromosomeList.get(i), chromosomeList.get(i + 1), chromosomeList);
			} else {
				performMutation(chromosomeList, i);
			}
		}
		sort(chromosomeList);
		size = chromosomeList.size();
		for (int i = 0; i < populationCount - size; i += 2) {
			if (rnd.nextBoolean()) {
				crossover(chromosomeList.get(i), chromosomeList.get(i + 1), chromosomeList);
			} else {
				performMutation(chromosomeList, i);
			}

		}

	}

	private void crossover(Chromosome c1, Chromosome c2, List<Chromosome> chromoList) {
		int[] gene1 = c1.getGene();
		int[] gene2 = c2.getGene();
		int[] newGene1 = gene1.clone();
		int[] newGene2 = gene2.clone();
		int first = rnd.nextInt(gene1.length);
		int second = rnd.nextInt(gene1.length - first) + first;
		for (int i = 0; i < gene1.length; i++) {
			newGene1[i] = (i >= first && i <= second) ? gene2[i] : gene1[i];
			newGene2[i] = (i >= first && i <= second) ? gene1[i] : gene2[i];
		}
		chromoList.add(new Chromosome(c1.getBaseGene(), newGene1));
		chromoList.add(new Chromosome(c2.getBaseGene(), newGene2));
	}

	public void cullLessFitChromosomes(List<Chromosome> chromosomeList) {
		int size = chromosomeList.size();
		for (int i = size - 1; i >= size * 2 / 5; i--) {
			chromosomeList.remove(i);
		}
	}

	public void sort(List<Chromosome> chromosomeList) {

		Collections.sort(chromosomeList, new Comparator<Chromosome>() {
			@Override
			public int compare(Chromosome arg0, Chromosome arg1) {
				if (arg0.getFitness() < arg1.getFitness()) {
					return -1;
				} else if (arg0.getFitness() > arg1.getFitness()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

	}

	/**
	 * This method performs a mutation of the gene by assigning a random value
	 * to a random gene
	 */
	public void performMutation(List<Chromosome> chromosomeList, int j) {

		int size = chromosomeList.size();
		for (int i = 0; i < 2; i++) {
			Chromosome chromo = chromosomeList.get(j + i);
			int[] gene = chromo.getGene();
			int[] newGene = Arrays.copyOf(gene, gene.length);
			for (int k = 0; k < 3; k++) {
				int index = 0;
				do {
					index = rnd.nextInt(gene.length);
				} while (chromo.getBaseGene()[index] != 0);
				newGene[index] = rnd.nextInt((int) Math.sqrt(gene.length)) + 1;
			}
			Chromosome newChromo = new Chromosome(chromo.getBaseGene(), newGene);
			chromosomeList.add(newChromo);
		}
	}

	/**
	 * This method performs a mutation of the gene by assigning a random value
	 * to a random gene
	 */
	// public void performMutation(Chromosome chromosome) {
	// Random rnd = new Random();
	// int[] gene = chromosome.getGene();
	// int index = 0;
	// do {
	// index = rnd.nextInt(gene.length);
	// } while (chromosome.getBaseGene()[index] != 0);
	// gene[index] = rnd.nextInt((int) Math.sqrt(gene.length)) + 1;
	// Chromosome newChromo = new Chromosome(chromosome.getBaseGene(), gene);
	// chromosome = newChromo;
	//
	// }
}
