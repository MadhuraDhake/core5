import java.util.Scanner;

class FIFO
{
    public static void main(String args[]) 
    {
        int n;
        int f;
        float rat;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of FRAMES :");
        f = sc.nextInt();
        int fifo[] = new int[f];

        System.out.println("Enter the number of INPUTS :");
        n = sc.nextInt();
        int inp[] = new int[n];

        System.out.println("Enter INPUT:");
        for(int i = 0; i < n; i++)
            inp[i] = sc.nextInt();

        System.out.println("----------------------");

        for(int i = 0; i < f; i++)
            fifo[i] = -1;

        int Hit = 0;
        int Fault = 0;
        int j = 0;
        boolean check;

        for(int i = 0; i < n; i++)
        {
            check = false;

            for(int k = 0; k < f; k++)
                if(fifo[k] == inp[i])
                {
                    check = true;
                    Hit = Hit + 1;
                }

            if(check == false)
            {
                fifo[j] = inp[i];
                j++;

                if(j >= f)
                    j = 0;

                Fault = Fault + 1;
            }
        }

        rat = (float)Hit / (float)n;
        System.out.println("HIT:" + Hit + "  FAULT:" + Fault + "   HIT RATIO:" + rat);
    }
}
