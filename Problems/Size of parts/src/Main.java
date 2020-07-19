import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] parts = new int[n];

        for (int i = 0; i < n; i++) {
            parts[i] = scanner.nextInt();
        }

        int larger = 0;
        int smaller = 0;
        int perfect = 0;

        for (int part : parts) {
            switch (part) {
                case 1:
                    larger += 1;
                    break;
                case -1:
                    smaller += 1;
                    break;
                case 0:
                    perfect += 1;
            }
        }

        System.out.printf("%d %d %d", perfect, larger, smaller);
    }
}