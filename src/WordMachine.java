import java.util.Stack;


public class WordMachine {
    //Declare the maximum and minimum integer(0 to 2^20 -1) (1048576-1)
    private static final int Minimum = 0;
    private static final int Maximum = 1048575;
    private static final int Error = -1;

    private Stack<Integer> wordMachine = new Stack<>();

    public int Solution(String s) {
        if (s.length() < 0 || s.length() > 2000) {
            return Error;
        }
        String[] word = s.split(" ");//split the string by space
        for (String element : word) {
            try {
                switch (element) {
                    case "DUP":
                        dup();
                        break;
                    case "POP":
                        pop();
                        break;
                    case "+":
                        add();
                        break;
                    case "-":
                        subtract();
                        break;
                    default:
                        push(element);
                }
            } catch (Throwable ex) {
                return Error;
            }
        }
        return wordMachine.isEmpty() ? Error : wordMachine.pop();
    }

    private void push(String element) {
        int value = Integer.valueOf(element);
        wordMachine.push(value);
        if (value < Minimum || value > Maximum) {
            throw new IllegalArgumentException("Valid range is 0 to 2^20-1");
        }
    }

    private void dup() {
        int top = wordMachine.peek();
        wordMachine.push(top);
        if (wordMachine.isEmpty()) {
            throw new IllegalArgumentException("Too few number of elements to process");
        }

    }

    private void pop() {
        wordMachine.pop();
        if (wordMachine.isEmpty()) {
            throw new IllegalArgumentException("Too few number of elements to process");
        }
    }

    private void add() {
        int value1 = wordMachine.pop();
        int value2 = wordMachine.pop();
        int sum = value1 + value2;
        if (sum < Minimum || sum > Maximum) {
            throw new IllegalArgumentException("Valid range is 0 to 2^20-1");
        }
    }

    private void subtract() {
        int value1 = wordMachine.pop();
        int value2 = wordMachine.pop();
        int sum = value1 - value2;
        if (sum < Minimum || sum > Maximum) {
            throw new IllegalArgumentException("Valid range is 0 to 2^20-1");
        }

    }

    public static void main(String args[]) {
        WordMachine wordMachine = new WordMachine();
        System.out.println(wordMachine.Solution("1048575, 1048574, +"));
    }
}