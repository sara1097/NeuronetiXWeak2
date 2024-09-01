import java.util.Scanner;
import java.util.Comparator;
import java.util.ArrayList;

public class Main {

    private static ArrayList<String> descriptions = new ArrayList<>();
    private static ArrayList<Double> amounts = new ArrayList<>();
    private static ArrayList<String> categories = new ArrayList<>();
    public static void inputTransaction(Scanner scanner){

        System.out.println("Enter transaction description : ");
        scanner.nextLine();
        String transactionDescription= scanner.nextLine();

        System.out.println("Enter transaction amount (Positive for income, negative for expenses): ");
        double transactionAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category transaction (e.g., Food, Entertainment, Bills): ");
        String transactionCategory = scanner.nextLine();

        descriptions.add(transactionDescription);
        amounts.add(transactionAmount);
        categories.add(transactionCategory);

        System.out.println("Transaction added successfully.");
    }

    public static void viewTransaction(Scanner scanner){
        System.out.println("\nTransactions:");
        System.out.println("Description \t\t| Amount \t\t| Category\n");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < descriptions.size(); i++) {
            System.out.println(descriptions.get(i) + " \t\t| " + amounts.get(i) + " \t\t| " + categories.get(i));
        }

        System.out.print("\nDo you want to sort transactions by amount? (yes/no): ");
        scanner.nextLine();
        String sortChoice = scanner.nextLine();
        if(sortChoice.equalsIgnoreCase("yes"))
           sortTransactionsByAmount();
    }

    private static void sortTransactionsByAmount() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < amounts.size(); i++) {
            temp.add(i);
        }

        temp.sort(Comparator.comparingDouble(amounts::get));


        System.out.println("\nSorted Transactions:");
        System.out.println("Description \t\t| Amount \t\t| Category");

        for (int i : temp) {
            System.out.println(descriptions.get(i) + "\t\t | " + amounts.get(i) + " \t\t| " + categories.get(i));
        }
    }

    public static void viewSummary(){
        double totalIncome=0;
        double totalExpenses=0;
        double balance=0;
        for (Double amount : amounts) {
            if (amount >= 0) {
                totalIncome += amount;
            } else {
                totalExpenses += amount;
            }
        }
        balance= totalIncome - totalExpenses;

        System.out.println("\nsummary\nTotal income: " + totalIncome +
                "\n Total Expenses: " + totalExpenses +
                "\n Balance : " + balance+"\n"
                );

    }

    public static void getInsights(){
        double totalExpenses = 0;
        for (double amount : amounts) {
            if (amount < 0) {
                totalExpenses += amount;
            }
        }
        ArrayList<String> temp = new ArrayList<>();

        System.out.println("\nSpending Insights :" + totalExpenses*-1);
        for (String category : categories) {
            if(!temp.contains(category)){

                double categoryTotal = 0;
                for (int i = 0; i < amounts.size(); i++) {
                    if (categories.get(i).equalsIgnoreCase(category) && amounts.get(i) < 0) {
                        categoryTotal += amounts.get(i);
                    }
                }
                if(categoryTotal<0){
                    double percentage = (categoryTotal / totalExpenses) * 100;
                    System.out.println("Category: " + category + " | Total Spent: " + categoryTotal + " | Percentage: " + percentage + "%");
                }
                temp.add(category);
            }

        }
    }


    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("choose from the following: \n 1. input transaction.\n" +
                    "2. view transaction.\n" +
                    "3. view summary.\n" +
                    "4. get insights.\n" +
                    "5. exit. \n " +
                    "choose an option : ");
            int number = scanner.nextInt();
            switch(number){
                case 1:
                    inputTransaction(scanner);
                    break;

                case 2:
                    viewTransaction(scanner);
                    break;

                case 3:
                    viewSummary();
                    break;

                case 4:
                    getInsights();
                    break;

                case 5:
                    flag = false;
                    System.out.println("You entered 5 and exit the game");

                    break;


                default:
                    System.out.println("Invalid number. try again");
            }

        }
    }
}