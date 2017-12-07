/**
 * 
 */
package edu.neu.coe.info6205.sudoku;

/**
 * @author Gaurang Davda
 *
 */
public class Sudoku {

	public static void main(String[] args) {

		int populationCount = 100;
		int[] baseGene = new int[] { 1, 0, 0, 0, 0, 4, 0, 0, 0, 0, 2, 0, 2, 0, 0, 3 };
		Population population = new Population(populationCount);
		for (int i = 0; i < populationCount; i++) {
			Chromosome chromosome = new Chromosome(baseGene);
			population.getChromosomeList().add(chromosome);
		}
		int count = 1;
		Chromosome c = population.bestChromosome();
		while (c.getFitness() != 0) {
			System.out.println("Current " + c + " count " + count);
			population.performMutation();
			c = population.bestChromosome();
			count++;
		}
		System.out.println("goal: " + c);
	}

}
