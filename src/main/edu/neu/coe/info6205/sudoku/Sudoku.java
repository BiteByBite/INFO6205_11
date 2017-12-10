/**
 * 
 */
package main.edu.neu.coe.info6205.sudoku;

/**
 * @author Gaurang Davda
 *
 */
public class Sudoku {

	public Chromosome solveSudoku(int[] input, int populationCount) throws Exception {

		
		int generation = 1;
		int[] baseGene = input;
		GeneticOperations geneticOperations = new GeneticOperations();
		Chromosome c = null;
		try {
			Population population = new Population(populationCount);
			for (int i = 0; i < populationCount; i++) {
				Chromosome chromosome = new Chromosome(baseGene);
				population.getChromosomeArr()[i] = chromosome;
			}
			c = geneticOperations.bestChromosome(population.getChromosomeArr());
			while (c.getFitness() != 0) {
				geneticOperations.cullLessFitChromosomes(population.getChromosomeArr());
				geneticOperations.performCrossover(population.getChromosomeArr(), populationCount);
				c = geneticOperations.bestChromosome(population.getChromosomeArr());
				System.out.println(c);
				System.out.println(generation);
				generation++;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return c;
	}
}
