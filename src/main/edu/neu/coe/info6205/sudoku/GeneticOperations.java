/**
 * 
 */
package main.edu.neu.coe.info6205.sudoku;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Gaurang Davda
 *
 */
public class GeneticOperations {

	Random rnd = new Random();

	public Chromosome bestChromosome(Chromosome[] chromosomeArr) {
		sort(chromosomeArr);
		return chromosomeArr[0];
	}

	public void performCrossover(Chromosome[] chromosomeArr, int populationCount) {

		int size = calculateActualArrayLength(chromosomeArr);
		for (int i = 0; i < size; i += 2) {
			if (rnd.nextBoolean()) {
				crossover(chromosomeArr[i], chromosomeArr[i + 1], chromosomeArr, size + i);
			} else {
				performMutation(chromosomeArr, i, size);
			}
		}
		sort(chromosomeArr);
		size = calculateActualArrayLength(chromosomeArr);
		for (int i = 0; i < populationCount - size; i += 2) {
			if (rnd.nextBoolean()) {
				crossover(chromosomeArr[i], chromosomeArr[i + 1], chromosomeArr, size + i);
			} else {
				performMutation(chromosomeArr, i, size);
			}

		}

	}

	private void crossover(Chromosome c1, Chromosome c2, Chromosome[] chromoArr, int index) {
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
		chromoArr[index] = new Chromosome(c1.getBaseGene(), newGene1);
		chromoArr[index + 1] = new Chromosome(c2.getBaseGene(), newGene2);
	}

	public void cullLessFitChromosomes(Chromosome[] chromosomeArr) {
		int size = calculateActualArrayLength(chromosomeArr);
		for (int i = size - 1; i >= size * 2 / 5; i--) {
			chromosomeArr[i] = null;
		}
	}

	public void sort(Chromosome[] a) {
		int size = calculateActualArrayLength(a);
		Chromosome[] aux = new Chromosome[size];
		sort(a, aux, 0, size - 1);

	}

	private static void merge(Chromosome[] a, Chromosome[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0)
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	private static void sort(Chromosome[] a, Chromosome[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	/**
	 * This method performs a mutation of the gene by assigning a random value
	 * to a random gene
	 */
	public void performMutation(Chromosome[] chromosomeArr, int j, int length) {

		for (int i = 0; i < 2; i++) {
			Chromosome chromo = chromosomeArr[j + i];
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
			chromosomeArr[length + j + i] = newChromo;
		}
	}

	private static int calculateActualArrayLength(Chromosome[] chromosomeArr) {

		int count = 0;
		for (int i = 0; i < chromosomeArr.length; i++) {
			if (chromosomeArr[i] != null) {
				count++;
			}
		}
		return count;
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
