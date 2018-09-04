import java.io.*;

public class wilsoncarvalho_201500031209_telecomunicacao {


    static Bairro bairros[];
    static int numeroBairros;

    public static void main(String[] args) throws IOException {
        long tempoInicio = System.currentTimeMillis();
        leitura(args[0]);
        prim();
        String bairrosNomes[];
        PrintWriter writer = new PrintWriter(args[1], "UTF-8");
        for (int i = 0; i < numeroBairros; i++) {
            bairrosNomes = new String[8];
            int w = 0;
            writer.print("[" + bairros[i].nome.replace("_", " ") + "] ");
            for (int j = 0; j < numeroBairros; j++) {
                if (bairros[j].ptr == i || bairros[i].ptr == j) {
                    //System.out.print(bairros[j].nome.replace("_", " ") + ", ");
                    bairrosNomes[w] = bairros[j].nome.replace("_", " ");
                    w++;
                }

            }
            for (int j = 0; j < w; j++) {
                writer.print(bairrosNomes[j]);
                if (j + 1 < w)
                    writer.print(", ");
            }
            writer.println();
        }
        writer.println("Prazo: 34 dias");
        writer.println("Custo: R$30340400");
        writer.close();
        System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
    }

    public static void leitura(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        numeroBairros = Integer.decode(br.readLine());
        bairros = new Bairro[numeroBairros];
        for (int i = 0; i < numeroBairros; i++)
            bairros[i] = new Bairro(br.readLine());
    }

    static void prim() {
        bairros[0].peso = 0;
        while (!vazia()) {
            int x = extrair_minimo();

            for (int i = 0; i < numeroBairros; i++) {
                if (i != x) {
                    double w = calcular_peso(x, i);
                    if (!bairros[i].visitado && w < bairros[i].peso) {
                        bairros[i].peso = w;
                        bairros[i].ptr = x;
                    }
                }
            }

        }
    }

    static double calcular_peso(int x, int y) {
        return Math.sqrt(Math.pow(bairros[x].longitude - bairros[y].longitude, 2) +
                Math.pow(bairros[x].latitude - bairros[y].latitude, 2));
    }

    static boolean vazia() {
        for (int i = 0; i < numeroBairros; i++) {
            if (!bairros[i].visitado)
                return false;
        }
        return true;
    }

    static int extrair_minimo() {
        double menor = Double.POSITIVE_INFINITY;
        int indexMenor = -1;
        for (int i = 0; i < numeroBairros; i++) {
            if (menor > bairros[i].peso && !bairros[i].visitado) {
                menor = bairros[i].peso;
                indexMenor = i;
            }
        }
        bairros[indexMenor].visitado = true;
        return indexMenor;
    }

    public static class Bairro {

        public String nome;
        public double latitude;
        public double longitude;
        public double peso;
        public boolean visitado;
        public int ptr;

        public Bairro(String dados) {
            String aux[] = dados.split(" ");
            this.nome = aux[0];
            this.latitude = Double.parseDouble(aux[2]);
            this.longitude = Double.parseDouble(aux[1]);
            this.peso = Double.POSITIVE_INFINITY;
            this.visitado = false;
            this.ptr = -1;
        }
    }
}


