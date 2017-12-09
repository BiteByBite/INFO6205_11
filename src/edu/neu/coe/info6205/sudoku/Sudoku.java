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

		int populationCount = 100000;
		int[] baseGene = new int[] { 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 8, 9, 0, 0, 0, 5, 0, 4, 6, 2, 0, 0, 3, 0, 0, 8, 0,
				0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 7, 0, 0, 0, 1, 3, 0, 0, 5, 0, 0,
				0, 0, 6, 0, 0, 0, 7, 0, 4, 0, 2, 0, 0, 6, 0, 0, 3, 0 };
		GeneticOperations go = new GeneticOperations();
		Population population = new Population(populationCount);
		for (int i = 0; i < populationCount; i++) {
			Chromosome chromosome = new Chromosome(baseGene);
			population.getChromosomeList().add(chromosome);
		}
		int count = 1;
		Chromosome c = go.bestChromosome(population.getChromosomeList());
		while (true) {
			if (c.getFitness() != 0) {
				System.out.println("Current " + c + " count " + count);
				go.cullLessFitChromosomes(population.getChromosomeList());
				go.performCrossover(population.getChromosomeList(), populationCount);
				// go.performMutation(population.getChromosomeList());
				c = go.bestChromosome(population.getChromosomeList());
				count++;
			} else {
				System.out.println("hi " + c.getFitness());
				c.calculateFitness();
				System.out.println(" fit " + c.getFitness());
				break;
			}
		}
		System.out.println("goal: " + c);

	}

}
