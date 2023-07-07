import java.util.Objects;
import java.util.Scanner;
public class A4_120090643 {
    public static class sparse_matrix{
        public sparse_matrix(int read_row,int read_col){
            row = read_row;
            column = read_col;
            compact_matrix = new int[3][row*column];
            pointer = 0;//amount of non-zero elements
        }
        public int[][] compact_matrix;
        public int  row;
        public int column;
        public int pointer;
        public void add_element(int row,int col,int value){
            compact_matrix[0][pointer] = row;
            compact_matrix[1][pointer] = col;
            compact_matrix[2][pointer] = value;
            pointer++;}

    }

    public static  void print_matrix(sparse_matrix result) {
        String row = String.valueOf(result.row);
        String col = String.valueOf(result.column);
        System.out.println(row + "," + " " + col);
        int i =0;
        int j =0;
        while( i < result.row) {
            String str1 = String.valueOf(i+1);
            System.out.print(str1);
            StringBuilder str = new StringBuilder();
            while(result.compact_matrix[0][j]==i+1){
                String col_tem = String.valueOf(result.compact_matrix[1][j]);
                String val_tem = String.valueOf(result.compact_matrix[2][j]);
                str.append(" ").append(col_tem).append(":").append(val_tem);
                j++;
            }
            if(str.toString().equals("")){
                System.out.println(" "+":");
            }
            else{
                System.out.println(str);
            }
            i++;
        }
    }

    public static sparse_matrix add_matrix(sparse_matrix adder1, sparse_matrix adder2){
        sparse_matrix result = new sparse_matrix(adder1.row,adder1.column);
        int fir_pointer =0 ;
        int sec_pointer =0;
        while(fir_pointer<adder1.pointer||sec_pointer< adder2.pointer){
            if(adder1.compact_matrix[0][fir_pointer]==0){
                result.add_element(adder2.compact_matrix[0][sec_pointer],
                        adder2.compact_matrix[1][sec_pointer],adder2.compact_matrix[2][sec_pointer]);
                sec_pointer++;

            }
            else if(adder2.compact_matrix[0][sec_pointer]==0){
                result.add_element(adder1.compact_matrix[0][fir_pointer],
                        adder1.compact_matrix[1][fir_pointer],adder1.compact_matrix[2][fir_pointer]);
                fir_pointer++;
            }
            else if(adder1.compact_matrix[0][fir_pointer]<adder2.compact_matrix[0][sec_pointer]){
                result.add_element(adder1.compact_matrix[0][fir_pointer],
                        adder1.compact_matrix[1][fir_pointer],adder1.compact_matrix[2][fir_pointer]);
                fir_pointer++;
            }
            else if(adder1.compact_matrix[0][fir_pointer]>adder2.compact_matrix[0][sec_pointer]){
                result.add_element(adder2.compact_matrix[0][sec_pointer],
                        adder2.compact_matrix[1][sec_pointer],adder2.compact_matrix[2][sec_pointer]);
                sec_pointer++;
            }
            else{
                if(adder1.compact_matrix[1][fir_pointer]<adder2.compact_matrix[1][sec_pointer]){
                    result.add_element(adder1.compact_matrix[0][fir_pointer],
                            adder1.compact_matrix[1][fir_pointer],adder1.compact_matrix[2][fir_pointer]);
                    fir_pointer++;
                }
                else if(adder1.compact_matrix[1][fir_pointer]>adder2.compact_matrix[1][sec_pointer]){
                    result.add_element(adder2.compact_matrix[0][sec_pointer],
                            adder2.compact_matrix[1][sec_pointer],adder2.compact_matrix[2][sec_pointer]);
                    sec_pointer++;
                }
                else{
                    int add = adder1.compact_matrix[2][fir_pointer]+adder2.compact_matrix[2][sec_pointer];
                    if(add!=0)
                        result.add_element(adder2.compact_matrix[0][sec_pointer],
                                adder2.compact_matrix[1][sec_pointer],add);
                    sec_pointer++;
                    fir_pointer++;

                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String capacity = scan.nextLine().replace(" ", "");
        String[] row_col = capacity.split(",");
        int fir_row = Integer.parseInt(row_col[0].trim());
        int fir_col = Integer.parseInt(row_col[1].trim());
        sparse_matrix first = new sparse_matrix(fir_row, fir_col);
        String single_row = scan.nextLine();
        while (!single_row.contains(",")) {
            String[] row_list = single_row.split(" ");
            for (int i = 1; i < row_list.length; i++) {
                if ((!Objects.equals(row_list[1], ":"))) {
                    int row = Integer.parseInt(row_list[0]);
                    int comma = row_list[i].indexOf(":");
                    int col = Integer.parseInt(row_list[i].substring(0, comma));
                    int value = Integer.parseInt(row_list[i].substring(comma + 1));
                    first.add_element(row, col, value);
                }
            }
            single_row = scan.nextLine();
        }
        String[] row_col_sec = single_row.replace(" ", "").split(",");
        int sec_row = Integer.parseInt(row_col_sec[0].trim());
        int sec_col = Integer.parseInt(row_col_sec[1].trim());
        sparse_matrix second = new sparse_matrix(sec_row, sec_col);
        int j = 0;
        while(j<sec_row){
            String sec_single_row = scan.nextLine();
            String[] row_list = sec_single_row.split(" ");
            for (int i = 1; i < row_list.length; i++) {
                if (!Objects.equals(row_list[1].trim(), ":")) {
                    int row = Integer.parseInt(row_list[0]);
                    int comma = row_list[i].indexOf(":");
                    int col = Integer.parseInt(row_list[i].substring(0, comma));
                    int value = Integer.parseInt(row_list[i].substring(comma + 1));
                    second.add_element(row, col, value);
                }
            }

            j++;
        }
        sparse_matrix result = add_matrix(first,second);
        print_matrix(result);
    }


}
