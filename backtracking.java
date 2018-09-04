import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class wilsoncarvalho_201500031209_labirinto {

    static int n_entradas;
    static Labirinto labirinto[];
    static int x_inic;
    static int y_inic;
    static int counter = 2;
    static int counter_aux = 2;
    static int x;
    static int y;
    static int l = 0;
    static int saida_a = 0;
    static int saida_b = 1;
    static PrintWriter writer;

    public static void main(String[] args) throws IOException {

        long tempoInicio = System.currentTimeMillis();
        leitura(args[0]);
        writer = new PrintWriter(args[1], "UTF-8");

        for (int i = 0; i < n_entradas; i++) {
            achar_inicio(labirinto[l]);
            writer.println("L" + l + ":");
            writer.println("INICIO [" + y_inic + "," + x_inic + "]");
            procurar_caminho();
            writer.flush();
        }

        /*
        System.out.println(labirinto[0].largura + " " + labirinto[0].altura);
        for (int i = 0; i < labirinto[0].altura; i++){
            for (int j = 0; j < labirinto[0].largura; j++){
                System.out.print(labirinto[0].caminho[i][j] + " ");

            }
            System.out.println();

        }
        */
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));
    }
    public static boolean andar_direita(){
        if (labirinto[l].caminho[y][x + 1].equals("0")){//direita
            return true;
        }
        return false;
    }
    public static boolean andar_frente(){
        if (labirinto[l].caminho[y - 1][x].equals("0")){//frente
            return true;
        }
        return false;
    }
    public static boolean andar_esquerda(){
        if (labirinto[l].caminho[y][x - 1].equals("0")){ //esquerda
            return true;
        }
        return false;
    }
    public static boolean andar_tras(){
        if(labirinto[l].caminho[y + 1][x].equals("0")){ //tras
            return true;
        }
        return false;
    }
    public static void achar_inicio(Labirinto labirinto){
        int pare = 0;
        for (int i = 0; i < labirinto.altura; i++){
            for (int j = 0; j < labirinto.largura; j++){
                if(labirinto.caminho[i][j].equals("X")){
                    x_inic = j;
                    y_inic = i;
                    pare = 1;
                    break;


                }
            }
            if (pare == 1){
                break;
            }
        }
    }

    public static void procurar_caminho(){
        int i = 0;
        boolean saida = false;
        achar_inicio(labirinto[l]);
        x = x_inic;
        y = y_inic;
        labirinto[l].caminho[y_inic][x_inic] = Integer.toString(counter);


        int b = 0;

        do{
            saida_b = 1;
            saida_a = 0;
            if (labirinto[l].caminho[y][x + 1].equals("0")){ //direita
                writer.print("D [" + y + "," + x + "]");
                x++;
                counter++;
                counter_aux++;
                labirinto[l].caminho[y][x] = Integer.toString(counter);
                writer.println("->[" + y + "," + x + "]");
            }
            else {
                if (labirinto[l].caminho[y - 1][x].equals("0")){ //frente
                    writer.print("F [" + y + "," + x + "]");
                    y--;
                    counter++;
                    counter_aux++;
                    labirinto[l].caminho[y][x] = Integer.toString(counter);
                    writer.println("->[" + y + "," + x + "]");

                }
                else{
                    if (labirinto[l].caminho[y][x - 1].equals("0")){ //esquerda
                        writer.print("E [" + y + "," + x + "]");
                        x--;
                        counter++;
                        counter_aux++;
                        labirinto[l].caminho[y][x] = Integer.toString(counter);
                        writer.println("->[" + y + "," + x + "]");

                    }
                    else{
                        if(labirinto[l].caminho[y + 1][x].equals("0")){ //tras
                            writer.print("T [" + y + "," + x + "]");
                            y++;
                            counter++;
                            counter_aux++;
                            labirinto[l].caminho[y][x] = Integer.toString(counter);
                            writer.println("->[" + y + "," + x + "]");

                        }
                        else
                        {
                            saida_a = 1;
                            backtracking();
                        }
                    }
                }
            }

            if (x == 0 || x == labirinto[l].largura - 1 || y == 0 || y == labirinto[l].altura - 1){
                b = 1;
                writer.println("SAIDA [" + y + "," + x + "]");
                l++;
                saida = true;
            }
            if (saida_a == 1 && saida_b == 1 && saida == false){
                writer.println("SEM SAIDA");
                b = 1;
                l++;
            }
        } while(b == 0);
    }

    public static void backtracking(){
        //if (y == 35 && x == 79 && l == 99)
        //    writer.println("oi");

        int b = 0;
        int i = 0;
        do {

            if (labirinto[l].caminho[y][x + 1].equals(Integer.toString(counter - 1)) && counter > 2) { //direita
                writer.print("BT [" + y + "," + (x + 1) + "]");
                x++;
                counter--;
                writer.println("<-[" + y + "," + (x - 1) + "]");
                saida_b = 0;
                if (andar_direita()){
                    b = 1;
                }
                if (andar_frente()){
                    b = 1;
                }
                if (andar_esquerda()){
                    b = 1;
                }
                if (andar_tras()){
                    b = 1;
                }

            } else {
                if (labirinto[l].caminho[y - 1][x].equals(Integer.toString(counter - 1)) && counter > 2) { //frente
                    writer.print("BT [" + (y - 1) + "," + x + "]");
                    y--;
                    counter--;
                    writer.println("<-[" + (y + 1) + "," + x + "]");
                    saida_b = 0;
                    if (andar_direita()){
                        b = 1;
                    }
                    if (andar_frente()){
                        b = 1;
                    }
                    if (andar_esquerda()){
                        b = 1;
                    }
                    if (andar_tras()){
                        b = 1;
                    }
                } else {
                    if (labirinto[l].caminho[y][x - 1].equals(Integer.toString(counter - 1)) && counter > 2) { //esquerda
                        writer.print("BT [" + y + "," + (x - 1) + "]");
                        x--;
                        counter--;
                        writer.println("<-[" + y + "," + (x + 1) + "]");
                        saida_b = 0;
                        if (andar_direita()){
                            b = 1;
                        }
                        if (andar_frente()){
                            b = 1;
                        }
                        if (andar_esquerda()){
                            b = 1;
                        }
                        if (andar_tras()){
                            b = 1;
                        }
                    } else {
                        if (labirinto[l].caminho[y + 1][x].equals(Integer.toString(counter - 1)) && counter > 2) { //tras
                            writer.print("BT [" + (y + 1) + "," + x + "]");
                            y++;
                            counter--;
                            writer.println("<-[" + (y - 1) + "," + x + "]");
                            saida_b = 0;
                            if (andar_direita()){
                                b = 1;
                            }
                            if (andar_frente()){
                                b = 1;
                            }
                            if (andar_esquerda()){
                                b = 1;
                            }
                            if (andar_tras()){
                                b = 1;
                            }
                        } else {
                            b = 1;
                        }
                    }
                }
            }
        } while (b == 0);
    }
    public static void leitura(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        n_entradas = Integer.decode(br.readLine());
        labirinto = new Labirinto[n_entradas];

        String aux2[];

        for (int i = 0; i < n_entradas; i++){
            labirinto[i] = new Labirinto(br.readLine());
            aux2 = new String[labirinto[i].largura];
            for(int j = 0; j < labirinto[i].altura; j++){
                aux2 = br.readLine().split(" ");
                labirinto[i].caminho[j] = aux2;
            }
        }
    }
    public static class Labirinto{

        public int largura;
        public int altura;
        public String caminho[][];

        public Labirinto(String dados) {
            String aux[] = dados.split(" ");
            this.largura = Integer.decode(aux[0]);
            this.altura = Integer.decode(aux[1]);
            this.caminho = new String[altura][largura];
        }
    }
}
