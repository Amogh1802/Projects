import java.util.Scanner;


public class GradeCalculator{

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Number Of Subjects");
        int subjects = sc.nextInt();

        int[] marks = new int[subjects];
        int total= 0;

        for( int i=0;i< subjects; i++){
            System.out.println("Enter marks obtained in subject "+(i+1)+ "out of 100 ");
            marks[i] = sc.nextInt();
            total += marks[i];
        }
        float Percentage = (float) total/subjects;

        String Grade = calculateGrade(Percentage);
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + total + "/" + subjects * 100);
        System.out.println("Average Percentage: " + String.format("%.2f", Percentage) + "%");
        System.out.println("Grade: " + Grade);

        sc.close();
    }
    public static String calculateGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }


    }
}