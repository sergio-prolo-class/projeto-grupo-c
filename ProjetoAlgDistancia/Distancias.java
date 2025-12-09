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

    //definindo os filtros
    static char tipoFiltro = 'C';
    static String filtroTexto = "Europa";
    static int filtroNumero = 0;

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
                String cont = pedacos[0];
                String pais = pedacos[1];
                String cid = pedacos[2];
            
                //conversões do texto para número
                double lat = Double.parseDouble(pedacos[3]);
                double lon = Double.parseDouble(pedacos[4]);
                int pop = Integer.parseInt(pedacos[5]);

                //lógica do filtro
                boolean deveSalvar = false;

                if (tipoFiltro == 'N') {
                    deveSalvar = true;
                }
                else if (tipoFiltro == 'C') {
                    if (cont.equalsIgnoreCase(filtroTexto)) deveSalvar = true;
                }
                else if (tipoFiltro=='P') {
                    if(pais.equalsIgnoreCase(filtroTexto)) deveSalvar = true;
                }
                else if (tipoFiltro == '+') {
                    if (pop>= filtroNumero) deveSalvar = true;
                }
                else if (tipoFiltro == '-') {
                    if (pop <= filtroNumero) deveSalvar = true;
                }

                // se passou no filtro
                if (deveSalvar) {
                    continentes[totalCidades] = cont;
                    paises[totalCidades] = pais;
                    cidades[totalCidades] = cid;
                    latitudes[totalCidades] = lat;
                    longitudes[totalCidades] = lon;
                    populacoes[totalCidades] = pop;

                    totalCidades++;

                }

            }
        }
        sc.close();
    }
}
