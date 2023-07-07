import java.util.*;
public class A1_120090643 {
    public static String delete_blank(String polynomial){//delete the blank
        String no_blank = "";
        for(int i = 0;i<polynomial.length();i++){
            if(polynomial.charAt(i) !=' ')
                no_blank+=polynomial.charAt(i);
        }
        return no_blank;
    }
    public static String[] parse(String polynomial){
        String temp = delete_blank(polynomial).replace("-","+");
        String second_temp;
        if(temp.startsWith("+")) {
            second_temp = temp.substring(1);
        }
        else second_temp = temp;
        return second_temp.split("\\+");
    }
    public static ArrayList<String> calculate(String[] parsed){
        ArrayList<String> result = new ArrayList<String>();
        for(String item:parsed){
            String new_item;
            if (item.contains("*")){
                if(item.contains("^")) new_item = item;
                else new_item = item+"^1";
            }
            else{
                if(item.contains("^")) new_item = "1*"+item;
                else new_item = "1*"+item+"^1";
            }
//            System.out.println(new_item);
            int coefficient;
            int power;
            String variable;
            String result_item;
            int parseInt = Integer.parseInt(new_item.substring(new_item.indexOf("^") + 1));
            coefficient = Integer.parseInt(new_item.substring(0, new_item.indexOf("*")))*
                    parseInt;
            power = parseInt-1;
            variable = new_item.substring(new_item.indexOf("*")+1,new_item.indexOf("^"));
            if(Character.isDigit(variable.charAt(0))){
                result_item ="0";
            }
            else if(coefficient==1){
                if (power==0)result_item="1";
                else if (power==1) result_item = variable;
                else result_item = variable+"^"+power;
            }
            else{
                if (power==1)result_item = coefficient+"*"+variable;
                else if (power==0)result_item = String.valueOf(coefficient);
                else result_item =  coefficient +"*"+variable+"^"+power;
            }

            result.add(result_item);


        }
        return result;
    }


    public static ArrayList<String> get_operator( String polynomial){
        ArrayList<String> operator = new ArrayList<String>();
        if (polynomial.charAt(0)!='-')operator.add("+");
        for(int i =0;i<polynomial.length();i++){
            if(polynomial.charAt(i)=='+')operator.add("+");
            if(polynomial.charAt(i)=='-')operator.add("-");

        }
        return operator;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String polynomial = scan.nextLine();
        ArrayList<String> operator_list= get_operator(polynomial);
        ArrayList<String> item_list = calculate(parse(polynomial));
        String result = "";
        for(int i =0;i<operator_list.toArray().length;i++){
            result+=operator_list.get(i)+item_list.get(i);
        }
        String temp;
        if(result.charAt(0)=='+'){
            temp = result.substring(1);
        }
        else temp =result;
        String fin =temp.replace("+0","").replace("-0","");
        System.out.println(fin);

    }
}


