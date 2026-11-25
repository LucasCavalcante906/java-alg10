import java.util.Locale;
import java.util.Scanner;

public class alg10 {
    static Scanner sc = new Scanner(System.in);

    static int[][] estacionamento = new int[5][10];
    static int[][] cinema = new int[8][6];

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        int opcao;
        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Vendas da semana");
            System.out.println("2 - Estacionamento");
            System.out.println("3 - Estoque em depósitos");
            System.out.println("4 - Competição de salto");
            System.out.println("5 - Cinema");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    vendasSemana();
                    break;
                case 2:
                    menuEstacionamento();
                    break;
                case 3:
                    estoqueDepositos();
                    break;
                case 4:
                    competicaoSalto();
                    break;
                case 5:
                    menuCinema();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    static void vendasSemana() {
        String[] dias = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
        double[][] vendas = new double[7][3];
        double[] totalProduto = new double[3];
        double[] totalDia = new double[7];
        double totalSemana = 0;

        for (int i = 0; i < 7; i++) {
            System.out.println("--- " + dias[i] + " ---");
            for (int j = 0; j < 3; j++) {
                System.out.print("Valor vendido do Produto " + (j + 1) + ": R$ ");
                vendas[i][j] = sc.nextDouble();

                totalProduto[j] += vendas[i][j];
                totalDia[i] += vendas[i][j];
                totalSemana += vendas[i][j];
            }
        }

        System.out.println("\n=== Total por produto ===");
        for (int j = 0; j < 3; j++) {
            System.out.printf("Produto %d: R$ %.2f%n", j + 1, totalProduto[j]);
        }

        System.out.println("\n=== Total por dia ===");
        for (int i = 0; i < 7; i++) {
            System.out.printf("%s: R$ %.2f%n", dias[i], totalDia[i]);
        }

        int melhorProduto = 0;
        for (int j = 1; j < 3; j++) {
            if (totalProduto[j] > totalProduto[melhorProduto]) {
                melhorProduto = j;
            }
        }

        int melhorDia = 0;
        for (int i = 1; i < 7; i++) {
            if (totalDia[i] > totalDia[melhorDia]) {
                melhorDia = i;
            }
        }

        System.out.printf("%nProduto com maior faturamento: Produto %d (R$ %.2f)%n",
                melhorProduto + 1, totalProduto[melhorProduto]);
        System.out.printf("Dia com maior volume de vendas: %s (R$ %.2f)%n",
                dias[melhorDia], totalDia[melhorDia]);
        System.out.printf("Faturamento total da semana: R$ %.2f%n", totalSemana);
    }

    static void menuEstacionamento() {
        int opcao;
        do {
            System.out.println("\n===== ESTACIONAMENTO =====");
            System.out.println("1 - Exibir mapa de vagas");
            System.out.println("2 - Quantidade de vagas livres");
            System.out.println("3 - Quantidade de vagas ocupadas");
            System.out.println("4 - Fileira com mais veículos");
            System.out.println("5 - Alterar estado de uma vaga");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    exibirMapa(estacionamento, "Fileira", "Mapa de vagas (0 = livre, 1 = ocupada)");
                    break;
                case 2:
                    System.out.println("Vagas livres: " + contar(estacionamento, 0));
                    break;
                case 3:
                    System.out.println("Vagas ocupadas: " + contar(estacionamento, 1));
                    break;
                case 4:
                    fileiraMaisCheia();
                    break;
                case 5:
                    alterarVaga();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void fileiraMaisCheia() {
        int melhorFileira = 0;
        int maiorQtd = -1;
        for (int i = 0; i < estacionamento.length; i++) {
            int qtd = 0;
            for (int j = 0; j < estacionamento[i].length; j++) {
                qtd += estacionamento[i][j];
            }
            if (qtd > maiorQtd) {
                maiorQtd = qtd;
                melhorFileira = i;
            }
        }
        System.out.println("Fileira com mais veículos: " + (melhorFileira + 1)
                + " (" + maiorQtd + " veículos)");
    }

    static void alterarVaga() {
        System.out.print("Fileira (1 a " + estacionamento.length + "): ");
        int f = sc.nextInt() - 1;
        System.out.print("Vaga (1 a " + estacionamento[0].length + "): ");
        int v = sc.nextInt() - 1;

        if (f < 0 || f >= estacionamento.length || v < 0 || v >= estacionamento[0].length) {
            System.out.println("Posição inválida!");
            return;
        }

        if (estacionamento[f][v] == 0) {
            estacionamento[f][v] = 1;
            System.out.println("Vaga agora está OCUPADA.");
        } else {
            estacionamento[f][v] = 0;
            System.out.println("Vaga agora está LIVRE.");
        }
    }

    static void estoqueDepositos() {
        int produtos = 10;
        int depositos = 4;
        int[][] estoque = new int[produtos][depositos];
        int[] totalProduto = new int[produtos];
        int[] totalDeposito = new int[depositos];

        for (int i = 0; i < produtos; i++) {
            System.out.println("--- Produto " + (i + 1) + " ---");
            for (int j = 0; j < depositos; j++) {
                System.out.print("Quantidade no Depósito " + (j + 1) + ": ");
                estoque[i][j] = sc.nextInt();

                totalProduto[i] += estoque[i][j];
                totalDeposito[j] += estoque[i][j];
            }
        }

        System.out.println("\n=== Estoque total por produto ===");
        for (int i = 0; i < produtos; i++) {
            System.out.println("Produto " + (i + 1) + ": " + totalProduto[i] + " unidades");
        }

        System.out.println("\n=== Estoque total por depósito ===");
        for (int j = 0; j < depositos; j++) {
            System.out.println("Depósito " + (j + 1) + ": " + totalDeposito[j] + " unidades");
        }

        int maiorProduto = 0;
        for (int i = 1; i < produtos; i++) {
            if (totalProduto[i] > totalProduto[maiorProduto]) {
                maiorProduto = i;
            }
        }

        int maiorDeposito = 0;
        for (int j = 1; j < depositos; j++) {
            if (totalDeposito[j] > totalDeposito[maiorDeposito]) {
                maiorDeposito = j;
            }
        }

        System.out.println("\nProduto com maior quantidade armazenada: Produto "
                + (maiorProduto + 1) + " (" + totalProduto[maiorProduto] + " unidades)");
        System.out.println("Depósito com maior volume de estoque: Depósito "
                + (maiorDeposito + 1) + " (" + totalDeposito[maiorDeposito] + " unidades)");
    }

    static void competicaoSalto() {
        int atletas = 10;
        int tentativas = 3;
        double[][] saltos = new double[atletas][tentativas];
        double[] melhor = new double[atletas];
        double[] media = new double[atletas];

        for (int i = 0; i < atletas; i++) {
            System.out.println("--- Atleta " + (i + 1) + " ---");
            double soma = 0;
            melhor[i] = 0;
            for (int j = 0; j < tentativas; j++) {
                System.out.print("Distância da tentativa " + (j + 1) + " (m): ");
                saltos[i][j] = sc.nextDouble();

                soma += saltos[i][j];
                if (saltos[i][j] > melhor[i]) {
                    melhor[i] = saltos[i][j];
                }
            }
            media[i] = soma / tentativas;
        }

        System.out.println("\n=== Melhor salto e média por atleta ===");
        for (int i = 0; i < atletas; i++) {
            System.out.printf("Atleta %d: melhor = %.2f m | média = %.2f m%n",
                    i + 1, melhor[i], media[i]);
        }

        int campeao = 0;
        for (int i = 1; i < atletas; i++) {
            if (melhor[i] > melhor[campeao]) {
                campeao = i;
            }
        }
        System.out.printf("%nCampeão: Atleta %d com %.2f m%n", campeao + 1, melhor[campeao]);

        int[] ordem = new int[atletas];
        for (int i = 0; i < atletas; i++) {
            ordem[i] = i;
        }
        for (int i = 0; i < atletas - 1; i++) {
            for (int j = 0; j < atletas - 1 - i; j++) {
                if (melhor[ordem[j]] < melhor[ordem[j + 1]]) {
                    int aux = ordem[j];
                    ordem[j] = ordem[j + 1];
                    ordem[j + 1] = aux;
                }
            }
        }

        System.out.println("\n=== Ranking ===");
        for (int pos = 0; pos < atletas; pos++) {
            int a = ordem[pos];
            System.out.printf("%dº lugar: Atleta %d - %.2f m%n", pos + 1, a + 1, melhor[a]);
        }
    }

    static void menuCinema() {
        int total = cinema.length * cinema[0].length;

        int opcao;
        do {
            System.out.println("\n===== CINEMA =====");
            System.out.println("1 - Exibir mapa das cadeiras");
            System.out.println("2 - Reservar cadeira");
            System.out.println("3 - Cancelar reserva");
            System.out.println("4 - Taxa de ocupação");
            System.out.println("5 - Lugares disponíveis");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    exibirMapa(cinema, "Fileira", "Mapa de cadeiras (0 = disponível, 1 = ocupada)");
                    break;
                case 2:
                    reservarCadeira();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    int ocupadas = contar(cinema, 1);
                    double taxa = (double) ocupadas / total * 100;
                    System.out.printf("Taxa de ocupação: %.1f%% (%d de %d)%n", taxa, ocupadas, total);
                    break;
                case 5:
                    System.out.println("Lugares disponíveis: " + contar(cinema, 0));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void reservarCadeira() {
        System.out.print("Fileira (1 a " + cinema.length + "): ");
        int f = sc.nextInt() - 1;
        System.out.print("Cadeira (1 a " + cinema[0].length + "): ");
        int c = sc.nextInt() - 1;

        if (f < 0 || f >= cinema.length || c < 0 || c >= cinema[0].length) {
            System.out.println("Posição inválida!");
        } else if (cinema[f][c] == 1) {
            System.out.println("Essa cadeira já está ocupada!");
        } else {
            cinema[f][c] = 1;
            System.out.println("Reserva realizada com sucesso!");
        }
    }

    static void cancelarReserva() {
        System.out.print("Fileira (1 a " + cinema.length + "): ");
        int f = sc.nextInt() - 1;
        System.out.print("Cadeira (1 a " + cinema[0].length + "): ");
        int c = sc.nextInt() - 1;

        if (f < 0 || f >= cinema.length || c < 0 || c >= cinema[0].length) {
            System.out.println("Posição inválida!");
        } else if (cinema[f][c] == 0) {
            System.out.println("Essa cadeira não possui reserva!");
        } else {
            cinema[f][c] = 0;
            System.out.println("Reserva cancelada com sucesso!");
        }
    }

    static void exibirMapa(int[][] mapa, String rotulo, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.print("          ");
        for (int j = 0; j < mapa[0].length; j++) {
            System.out.printf("%3d", j + 1);
        }
        System.out.println();
        for (int i = 0; i < mapa.length; i++) {
            System.out.printf("%s %d ", rotulo, i + 1);
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.printf("%3d", mapa[i][j]);
            }
            System.out.println();
        }
    }

    static int contar(int[][] mapa, int estado) {
        int total = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == estado) {
                    total++;
                }
            }
        }
        return total;
    }
}