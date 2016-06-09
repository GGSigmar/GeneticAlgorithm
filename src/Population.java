import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Population
{
    LinkedList<Chromosome> chromosomePool = new LinkedList<>();
    LinkedList<Chromosome> matingPool = new LinkedList<>();

    int populationSize;
    int numberOfGenerations;
    double mutationProbability;

    int generationNumber;

    double lowestFitnessValue;
    double highestFitnessValue;
    double averageFitnessValue;
    double totalFitness;
    double totalMatingFitness;

    Population(int populationSize, int numberOfGenerations, double mutationProbability)
    {
        this.populationSize = populationSize;
        this.numberOfGenerations = numberOfGenerations;
        this.mutationProbability = mutationProbability;

        generationNumber = 1;

        populate();
    }

    double fitnessFunction(int x, int y)
    {
        return ((x+2*y-7)^2+(2*x+y-5)^2);
    }

    public void populate()
    {
        for (int i = 0; i < populationSize; i++)
        {
            Chromosome chromosome = new Chromosome();
            chromosomePool.add(chromosome);
        }
    }

    public void evolve()
    {
        totalFitness = 0;
        averageFitnessValue = 0;
        lowestFitnessValue = 0;
        highestFitnessValue = 0;
        totalMatingFitness = 0;

        for(Chromosome chromosome : chromosomePool)
        {
            chromosome.fitnessValue = fitnessFunction(chromosome.xInputInteger, chromosome.yInputInteger);
            getLowestFitness(chromosome.fitnessValue);
            getHighestFitness(chromosome.fitnessValue);
            totalFitness = totalFitness + chromosome.fitnessValue;
        }

        averageFitnessValue = totalFitness / populationSize;

        writeToFile();

        roulette();

        generationNumber++;
    }

    void roulette()
    {
        matingPool.clear();

        for(Chromosome chromosome : chromosomePool)
        {
            chromosome.matingFitnessValue = chromosome.fitnessValue+Math.abs(lowestFitnessValue)+1;
            totalMatingFitness = totalMatingFitness + chromosome.matingFitnessValue;
        }

        for(Chromosome chromosome : chromosomePool)
        {
            chromosome.matingFitnessValue = chromosome.matingFitnessValue / totalMatingFitness;

            int n = (int)(chromosome.matingFitnessValue*1000);

            for(int j = 0; j < n; j++)
            {
                matingPool.add(chromosome);
            }
        }

        for(Chromosome chromosome : chromosomePool)
        {
            int a = (int) (Math.random() * matingPool.size());
            int b = (int) (Math.random() * matingPool.size());
            Chromosome parentA = matingPool.get(a);
            Chromosome parentB = matingPool.get(b);
            Chromosome child = Chromosome.crossover(parentA, parentB);
            Chromosome.mutate(child, mutationProbability);
            chromosome.xBinary = child.xBinary;
            chromosome.yBinary = child.yBinary;
            chromosome.xInputInteger = child.xInputInteger;
            chromosome.yInputInteger = child.yInputInteger;
        }
    }

    public void getLowestFitness(double liczba)
    {
        if(liczba < lowestFitnessValue)
        {
            lowestFitnessValue = liczba;
        }
    }

    public void getHighestFitness(double liczba)
    {
        if(liczba > highestFitnessValue)
        {
            highestFitnessValue = liczba;
        }
    }

    void writeToFile()
    {
        try
        {
            PrintWriter writer = new PrintWriter("Generation"+generationNumber+".txt");
            writer.println("averageFitness: "+averageFitnessValue);
            writer.println("maximumFitness: "+highestFitnessValue);
            writer.println("minimumFitness: "+lowestFitnessValue);
            writer.println("effectiveness: "+(averageFitnessValue/highestFitnessValue));
            for(Chromosome chromosome : chromosomePool)
            {
                writer.println(chromosome.xInputInteger+","+chromosome.yInputInteger+","+chromosome.fitnessValue);
            }
            writer.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
