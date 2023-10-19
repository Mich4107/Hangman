package main;

public class Gallows {
    /**
     * Prints the gallows depending on the mistakes
     * @param mistakesLeft
     */
    public static void printGallows(int mistakesLeft){
        switch (mistakesLeft) {
            case 5 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |
                    |
                    |
                    |__________|""");
            case 4 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |      |
                    |      |
                    |
                    |__________|""");
            case 3 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |     /|
                    |      |
                    |
                    |__________|""");
            case 2 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |     /|\\
                    |      |
                    |   
                    |__________|""");
            case 1 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |     /|\\
                    |      |
                    |     /
                    |__________|""");
            case 0 -> System.out.println("""
                    --------
                    |/     |
                    |     x_x
                    |     /|\\
                    |      |
                    |     /|
                    |__________|""");
        }
    }
}
