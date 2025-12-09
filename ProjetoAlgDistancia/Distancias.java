import java.util.Scanner;

public class Distancias {

    //tamanho fixo e seguro para os arrays
    static final int MAX = 10000;

    //arrays para armazenar cada coluna do arquivo separadamente
    static String[] continentes = new String[MAX];
    static String[] paises = new String[MAX];
    static String[] cidades = new String[MAX];

    static double[] latitudes = new double[MAX];
    static double[] longitudes = new double[MAX];
    static int[] populacoes = new int[MAX];
    
    //Variavel para contar quantas cidades realmente guardamos
    static int totalCidades = 0;

    public static void main (String[] args) {

        //metodo para leitura
        carregarDados();

        System.out.println("Total de cidades carregadas: "+ totalCidades);

        if (totalCidades>0) {
            System.out.println("Primeira: "+ cidades[0]);
            System.out.println("Última: "+ cidades[totalCidades-1]);
            
        }
    }

    //Declaração do método para a leitura do arquivo
    public static void carregarDados() {
        Scanner sc = new Scanner(System.in);

        //enquanto houver linhas e não estourar o vetor
        while (sc.hasNextLine() && totalCidades<MAX) {
            String linha = sc.nextLine();

            String[] pedacos = linha.split(";");

            if (pedacos.length == 6) {
                continentes[totalCidades] = pedacos[0];
                paises[totalCidades] = pedacos[1];
                cidades[totalCidades] = pedacos[2];
            
                try {
                    //converte os textos para números
                    latitudes[totalCidades]  = Double.parseDouble(pedacos[3]);
                    longitudes[totalCidades]  = Double.parseDouble(pedacos[4]);
                    populacoes[totalCidades]  = Integer.parseInt(pedacos[5]);
                    
                    //se a conversão deu certo, incrementa o contador para a próxima posição
                    totalCidades++;
                } catch (NumberFormatException e) {
                    //se der erro ignora a linha evitando de quebrar o programa
                }

            }
        }
        sc.close();
    }
}
