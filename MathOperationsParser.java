import java.util.List;
import java.util.Scanner;

public class Main {

    static String str;

    public static void main(String[] args) {
        for (; ; ) {
            System.out.print ("Enter: ");
            //example: (5+10*3.4)/10
            str = enter();
           
            str = str.replaceAll(" ", ""); //deleting " "
            //searching for errors
            if (isError(str)) {
                System.out.println("Errors in string \"" + str + "\"\n");
                continue;
            }
            //make array
            String[] res = str.split("((?<=[\\+|\\*|\\/\\(\\)])|(?=[\\+|\\*|\\/\\(\\)]))");
            


            String[] result = calculate(res);
            for(int i = 0; i < result.length; i++) System.out.println("The final result is " + result[i]);
            enter();


        }
    }
    //calculating result
    static String[] calculate(String[] res) {
	//operation with braces
        if (hasBrace(res) > 0 || res[0].equals("(")) {
            for(;;) {
                int index = hasBrace(res);
                int lastIndex = index;

                List<String> list = new ArrayList<String>();
                for (int i = index + 1; !res[i].equals(")"); i++) {
                    list.add(res[i]);
                    lastIndex = i;
                }
                lastIndex = lastIndex + 1;

                String[] tempArr = new String[list.size()];
                list.toArray(tempArr);


                String[] result = calculate(tempArr); //recursive
                res[index] = result[0];

                list = new ArrayList<String>(Arrays.asList(res));

                for (int i = index + 1; i <= lastIndex; i++) list.remove(index + 1);
                res = new String[list.size()];
                list.toArray(res);
                if (hasBrace(res) > 0 || res[0].equals("(")) continue;
                else break;
            }
        }

        //operations with operators
        for(;;) {
	        //  /
            if (hasOperator(res, "/") != -1) {
                int index = hasOperator(res, "/");
                float a = Float.parseFloat(res[index - 1]);
                float b = Float.parseFloat(res[index + 1]);
                String result = String.valueOf(a / b);
                res[index - 1] = result;
                List<String> list = new ArrayList<String>(Arrays.asList(res));
                list.remove(index + 1);
                list.remove(index);
                res = new String[list.size()];
                list.toArray(res);
                continue;
            }
	        // *
            if (hasOperator(res, "*") != -1) {
                int index = hasOperator(res, "*");
                float a = Float.parseFloat(res[index - 1]);
                float b = Float.parseFloat(res[index + 1]);
                String result = String.valueOf(a * b);
                res[index - 1] = result;
                List<String> list = new ArrayList<String>(Arrays.asList(res));
                list.remove(index + 1);
                list.remove(index);
                res = new String[list.size()];
                list.toArray(res);
                continue;
            }
	        // +
            if (hasOperator(res, "+") != -1) {
                int index = hasOperator(res, "+");
                float a = Float.parseFloat(res[index - 1]);
                float b = Float.parseFloat(res[index + 1]);
                String result = String.valueOf(a + b);
                res[index - 1] = result;
                List<String> list = new ArrayList<String>(Arrays.asList(res));
                list.remove(index + 1);
                list.remove(index);
                res = new String[list.size()];
                list.toArray(res);
                continue;
            }
 	        // -
            if (hasOperator(res, "-") != -1) {
                int index = hasOperator(res, "-");
                float a = Float.parseFloat(res[index - 1]);
                float b = Float.parseFloat(res[index + 1]);
                String result = String.valueOf(a - b);
                res[index - 1] = result;
                List<String> list = new ArrayList<String>(Arrays.asList(res));
                list.remove(index + 1);
                list.remove(index);
                res = new String[list.size()];
                list.toArray(res);
                continue;
            }

            break;
        }
        return res;
    }

    //searching errors in a string
    static boolean isError(String s) {
        int braceLeft = 0, braceRight = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isDigit(s.charAt(i)) || isOperator(s.charAt(i)) || s.charAt(i) == '.' || s.charAt(i) == '(' || s.charAt(i) == ')')) {
                return true;
            }
            if(s.charAt(i) == '(') braceLeft++;
            if(s.charAt(i) == ')') braceRight++;
        }
        if(braceLeft != braceRight) return true;
        return false;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static int hasOperator(String[] res, String op) {
        for (int i = 0; i < res.length; i++) {
            if (res[i].equals(op)) return i;
        }
        return -1;
    }

    //typing a string
    static String enter() {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        return str;
    }

    //return an index of last braces
    static int hasBrace(String[] res) {
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i].equals("(")) index = i;
        }
        return index;
    }
}