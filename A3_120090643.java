import java.util.*;
public class A3_120090643 {
    public static PriorityQueue<board> board_queue = new PriorityQueue<>();
    public static class board implements Comparable<board>
    {
        // Constructor
        public board(board previous_blocks,int[][] input_blocks,int current_move)
        {
            previous = previous_blocks;
            current = input_blocks;
            Manhattan= find_Manhattan(input_blocks);
            steps = current_move;
        }
        public board previous;
        public int[][] current;//a matrix to represent the board
        public int Manhattan;//the distance of the current board
        public int steps; //current steps made
        public int find_Manhattan(int[][] blocks){
            int Manhattan = 0;
            for(int i =0;i<blocks.length;i++){
                for(int j=0;j<blocks[i].length;j++){
                    if(blocks[i][j]!=0){
                        if(blocks[i][j]!=3*i + j + 1){
                            int target_row = (blocks[i][j]-1)/3;
                            int target_column = blocks[i][j]-target_row*3-1;
                            Manhattan+=Math.abs(i-target_row)+Math.abs(j-target_column);
                        }
                    }
                    else Manhattan+=Math.abs(i-2)+Math.abs(j-2);}}
            return Manhattan;
        }
        //override the compareTo method for the priority queue
        @Override
        public int compareTo(board o) {
            return Integer.compare(this.Manhattan+this.steps, o.Manhattan+o.steps);
        }
    }

    public static ArrayList<int[][]> find_next_board(int[][] current){
        ArrayList<int[][]> next_board = new ArrayList<>();
        int zero_row=0;
        int zero_column=0;
        for(int i=0;i< current.length;i++){
            for(int j=0;j<current[i].length;j++){
                if(current[i][j]==0){
                    zero_row =i;
                    zero_column = j;}}
        }
        if(zero_row!=0) next_board.add(going_up(current,zero_row,zero_column));
        if(zero_row!=2) next_board.add(going_down(current,zero_row,zero_column));
        if(zero_column!=0) next_board.add(going_left(current,zero_row,zero_column));
        if(zero_column!=2) next_board.add(going_right(current,zero_row,zero_column));
        return next_board;
    }
    //define the function for move
    public static int[][] going_down(int [][] input_board,int blank_row, int blank_column){
        int[][] puzzle = new int[3][3];
        for(int i =0;i<input_board.length;i++){
            System.arraycopy(input_board[i], 0, puzzle[i], 0, input_board[i].length);
        }
        puzzle[blank_row][blank_column]= puzzle[blank_row+1][blank_column];
        puzzle[blank_row+1][blank_column]=0;
        return puzzle;}

    public static int[][] going_up(int [][] input_board,int blank_row, int blank_column){
        int[][] puzzle = new int[3][3];
        for(int i =0;i<input_board.length;i++){
            System.arraycopy(input_board[i], 0, puzzle[i], 0, input_board[i].length);
        }
        puzzle[blank_row][blank_column]= puzzle[blank_row-1][blank_column];
        puzzle[blank_row-1][blank_column]=0;
        return puzzle;}

    public static int[][] going_left(int [][] input_board,int blank_row, int blank_column){
        int[][] puzzle = new int[3][3];
        for(int i =0;i<input_board.length;i++){
            System.arraycopy(input_board[i], 0, puzzle[i], 0, input_board[i].length);
        }
        puzzle[blank_row][blank_column]= puzzle[blank_row][blank_column-1];
        puzzle[blank_row][blank_column-1]=0;
        return puzzle;}

    public static int[][] going_right(int [][] input_board,int blank_row, int blank_column){
        int[][] puzzle = new int[3][3];
        for(int i =0;i<input_board.length;i++){
            System.arraycopy(input_board[i], 0, puzzle[i], 0, input_board[i].length);
        }
        puzzle[blank_row][blank_column]= puzzle[blank_row][blank_column+1];
        puzzle[blank_row][blank_column+1]=0;
        return puzzle;}

    public static void print_board(int[][] board){
        for (int[] row : board) {
            for(int num:row){
                System.out.print(num);
                System.out.print("\t");
            }
            System.out.println("\n");
        }
    }

    public static int[][] read_board(){
        Scanner scan = new Scanner(System.in);
        int[][] blocks = new int[3][3];
        for(int i=0;i< blocks.length;i++){
            for(int j=0;j<3;j++ ){
                int num = scan.nextInt();
                blocks[i][j] = num;
            }
        }
        return blocks;
    }
    public static boolean find_target(board final_board){
        return final_board.find_Manhattan(final_board.current)==0;
    }
    
    public static void main(String[] args){
        board start = new board(null,read_board(),0);
        board_queue.add(start);
        board imm =board_queue.peek();
        while(imm!=null&& !find_target(imm)){
            imm = board_queue.remove();
            for(int[][] next:find_next_board(imm.current)){
                if(imm.previous==null||!Arrays.deepEquals(next, imm.previous.current)){
                    board_queue.add(new board(imm,next,imm.steps+1));}
            }
        }
        board temp_board = imm;
        Stack<board> temp= new Stack<>();
        while(!(temp_board ==null)){
            temp.push(temp_board);
            temp_board=temp_board.previous;
        }
        while(!temp.isEmpty()){
            board a = temp.pop();
            print_board(a.current);
        }

    }
}






