import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* System.out.println("Hello World!"); */
		System.out.println("\n**************************************\n");
		System.out.println("\tWelcome to TheDesk \n");
		System.out.println("**************************************");
		optionsSelection();

	}

	private static void optionsSelection() {
		String[] arr = { "1. I wish to review my expenditure", "2. I wish to add my expenditure",
				"3. I wish to delete my expenditure", "4. I wish to sort the expenditures",
				"5. I wish to search for a particular expenditure", "6. Close the application" };
		int[] arr1 = { 1, 2, 3, 4, 5, 6 };
		int slen = arr1.length;
		for (int i = 0; i < slen; i++) {
			System.out.println(arr[i]);
			// display the all the Strings mentioned in the String array
		}
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		ArrayList<Integer> expenses = new ArrayList<Integer>();
		expenses.add(1000);
		expenses.add(2300);
		expenses.add(45000);
		expenses.add(32000);
		expenses.add(110);
		expenses.addAll(arrlist);
		System.out.println("\nEnter your choice:\t");
		Scanner sc = new Scanner(System.in);
		int options = sc.nextInt();
		for (int j = 1; j <= slen; j++) {
			if (options == j) {
				switch (options) {
				case 1:
					System.out.println("Your saved expenses are listed below: \n");
					System.out.println(expenses + "\n");
					optionsSelection();
					break;
				case 2:
					System.out.println("Enter the value to add your Expense: \n");
					int value = sc.nextInt();
					expenses.add(value);
					System.out.println("Your value is updated\n");
					expenses.addAll(arrlist);
					System.out.println(expenses + "\n");
					optionsSelection();

					break;
				case 3:
					System.out.println(
							"You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
					int con_choice = sc.nextInt();
					if (con_choice == options) {
						expenses.clear();
						System.out.println(expenses + "\n");
						System.out.println("All your expenses are erased!\n");
					} else {
						System.out.println("Oops... try again!");
					}
					optionsSelection();
					break;
				case 4:
					sortExpenses(expenses, true);
					optionsSelection();
					break;
				case 5:
					searchExpenses(expenses);
					optionsSelection();
					break;
				case 6:
					closeApp();
					break;
				default:
					System.out.println("You have made an invalid choice!");
					break;
				}
			}
		}

	}

	private static void closeApp() {
		System.out.println("Closing your application... \nThank you!");
	}

	public static boolean binarySearch(Integer[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return true; // Element found
			}

			// If target is greater, ignore left half
			if (arr[mid] < target) {
				left = mid + 1;
			} else { // If target is smaller, ignore right half
				right = mid - 1;
			}
		}

		return false; // Element not found
	}

	private static void searchExpenses(ArrayList<Integer> arrayList) {
		int arrLength = arrayList.size();
		System.out.println("Enter the expense you need to search:\t");
		Scanner sc = new Scanner(System.in);
		int amountToFind = sc.nextInt();
		sortExpenses(arrayList, false);
//		System.out.println("The array is" + arrayList);

		Integer[] sortedArray = new Integer[arrLength];
		sortedArray = arrayList.toArray(sortedArray);

		boolean isElementFound = binarySearch(sortedArray, amountToFind);
		if (isElementFound) {
			System.out.println("Amount " + amountToFind + " found in the expenses.");
		} else {
			System.out.println("Amount " + amountToFind + " not found in the expenses.");
		}

	}

	private static void sortExpenses(ArrayList<Integer> arrayList, boolean toPrint) {

		// I'm using quick sort algorithm for sorting as it uses divide and conquer
		// method.
		int arrLength = arrayList.size();
		// Convert ArrayList to array for sorting using Quick Sort
		Integer[] expensesArray = new Integer[arrLength];
		expensesArray = arrayList.toArray(expensesArray);

		// Apply Quick Sort
		quickSort(expensesArray, 0, arrLength - 1);
		arrayList.clear();
		for (Integer expense : expensesArray) {
			arrayList.add(expense);
		}

		if (toPrint) {
			System.out.println("Expenses sorted in ascending order: " + arrayList);
			optionsSelection();
		}
	}

	// Quick Sort algorithm
	private static void quickSort(Integer[] arr, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(arr, low, high);
			quickSort(arr, low, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, high);
		}
	}

	private static int partition(Integer[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				// Swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// Swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}
}
