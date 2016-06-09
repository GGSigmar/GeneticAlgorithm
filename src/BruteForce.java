public class BruteForce
{
    static double fitnessFunction(int x, int y)
    {
        return ((x+2*y-7)^2+(2*x+y-5)^2);
    }

    public static void main(String[] args)
    {
        long startTime = System.nanoTime();

        int optimalIndex1 = 0;
        int optimalIndex2 = 0;
        double highestFitness = 0;

        for(int i = -15; i < 16; i++)
        {
            for(int j = -15; j < 16; j++)
            {
                if(fitnessFunction(i,j)>highestFitness)
                {
                    highestFitness = fitnessFunction(i,j);
                    optimalIndex1 = i;
                    optimalIndex2 = j;
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Optimal indexes are "+optimalIndex1+" "+optimalIndex2+", and the maximum value of function is "+highestFitness);
        System.out.println("It took system units of time: "+duration);
    }


}
