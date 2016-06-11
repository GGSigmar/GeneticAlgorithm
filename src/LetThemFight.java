public class LetThemFight
{
    public static void main(String[] args)
    {
        long startTime = System.nanoTime();

        Population populacja = new Population(200, 30, 0.01); // edit to fiddle with parameters

        while(populacja.generationNumber<=populacja.numberOfGenerations)
        {
            populacja.evolve();
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("It took system units of time: "+duration);
    }
}
