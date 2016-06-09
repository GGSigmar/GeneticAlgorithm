import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class Chromosome
{
    static int generationNumber = 1;
    int xInputInteger;
    int yInputInteger;
    String xBinary;
    String yBinary;
    double fitnessValue;
    double matingFitnessValue;

    static int binaryToInteger(String wyraz)
    {
        int result = 0;

        if(wyraz.charAt(4)=='1')
        {
            result = result + 1;
        }
        if(wyraz.charAt(3)=='1')
        {
            result = result + 2;
        }
        if(wyraz.charAt(2)=='1')
        {
            result = result + 4;
        }
        if(wyraz.charAt(1)=='1')
        {
            result = result + 8;
        }
        if(wyraz.charAt(0)=='1')
        {
            result = result * (-1);
        }

        return result;
    }

    static String integerToBinary(int liczba)
    {
        switch(liczba)
        {
            case 0: return "00000";
            case 1: return "00001";
            case 2: return "00010";
            case 3: return "00011";
            case 4: return "00100";
            case 5: return "00101";
            case 6: return "00110";
            case 7: return "00111";
            case 8: return "01000";
            case 9: return "01001";
            case 10: return "01010";
            case 11: return "01011";
            case 12: return "01100";
            case 13: return "01101";
            case 14: return "01110";
            case 15: return "01111";
            case -1: return "10001";
            case -2: return "10010";
            case -3: return "10011";
            case -4: return "10100";
            case -5: return "10101";
            case -6: return "10110";
            case -7: return "10111";
            case -8: return "11000";
            case -9: return "11001";
            case -10: return "11010";
            case -11: return "11011";
            case -12: return "11100";
            case -13: return "11101";
            case -14: return "11110";
            case -15: return "11111";
            default: return "00000 bangladesz";
        }
    }

    /*Chromosome with random X and Y within range from -10 to 10*/
    Chromosome()
    {
        Random rand = new Random();
        xInputInteger = rand.nextInt((10 + 10) + 1) - 10;
        yInputInteger = rand.nextInt((10 + 10) + 1) - 10;
        xBinary = integerToBinary(xInputInteger);
        yBinary = integerToBinary(yInputInteger);
    }

    static Chromosome crossover(Chromosome A, Chromosome B)
    {
        Chromosome child = new Chromosome();
        int cutPoint = (int) (Math.random() * child.xBinary.length());
        child.xBinary = A.xBinary.substring(0, cutPoint).concat(B.xBinary.substring(cutPoint));
        child.yBinary = A.yBinary.substring(0, cutPoint).concat(B.yBinary.substring(cutPoint));
        child.xInputInteger = binaryToInteger(child.xBinary);
        child.yInputInteger = binaryToInteger(child.yBinary);

        return child;
    }

    static void mutate(Chromosome DNA, double mutationProbability)
    {
        for (int i = 0; i < DNA.xBinary.length(); i++)
        {
            if(Math.random() < mutationProbability)
            {
                StringBuilder budowniczyX = new StringBuilder(DNA.xBinary);

                if(DNA.xBinary.charAt(i)=='1')
                {
                    budowniczyX.setCharAt(i,'0');
                }
                else
                {
                    budowniczyX.setCharAt(i,'1');
                }

                DNA.xBinary = budowniczyX.toString();
                DNA.xInputInteger = binaryToInteger(DNA.xBinary);
            }

            if(Math.random() < mutationProbability)
            {
                StringBuilder budowniczyY = new StringBuilder(DNA.yBinary);

                if(DNA.yBinary.charAt(i)=='1')
                {
                    budowniczyY.setCharAt(i,'0');
                }
                else
                {
                    budowniczyY.setCharAt(i,'1');
                }

                DNA.yBinary = budowniczyY.toString();
                DNA.yInputInteger = binaryToInteger(DNA.yBinary);
            }
        }
    }
}
