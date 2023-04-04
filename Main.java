
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        String tipo;

        ArrayList<Produto> produtosNaoPereciveis = new ArrayList<>();
        ArrayList<Perecivel> produtosPereciveis = new ArrayList<>();

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        Scanner ler = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-- Menu --");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - relatorio");
            System.out.println("3 - Vendas");
            System.out.println("0 - Sair");

            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    inclusao(produtosNaoPereciveis, produtosPereciveis);
                    break;
                case 2:
                    relatorio(produtosNaoPereciveis,produtosPereciveis);
                    break;
                case 3:
                    ler.nextLine();
                    System.out.println("Qual o id do produto a ser vendido? : ");
                    String idVenda = ler.nextLine();
                    System.out.println("id: " + idVenda);
                    System.out.println("Qual o tipo do produto a ser vendido? : ");
                    tipo = ler.nextLine();
                    venda(produtosNaoPereciveis,produtosPereciveis,idVenda,tipo);




                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        }
    }



    public static void inclusao(ArrayList<Produto> produtosNaoPereciveis, ArrayList<Perecivel> produtosPereciveis) {
        Scanner ler = new Scanner(System.in);
        String nome, id;
        double preco ;
        int quant ;
        int resp;

        System.out.println("Nome Produto");
        nome = ler.nextLine();

        System.out.println("Id Produto");
        id = ler.nextLine();

        do {
            System.out.println("Quantidade do Produto");
            quant = ler.nextInt();
        } while (quant <= 0);


        do {
            System.out.println("Preco Produto");
            preco = ler.nextDouble();
        } while (preco <= 0);


        do {
            System.out.println("Alimento Nao Perecivel-1");
            System.out.println("Alimento Perecivel-2");
            resp = ler.nextInt();
            ler.nextLine();
        } while (resp != 1 && resp != 2);


        switch (resp) {
            case 1:
                System.out.println("Produto não perecível:");
                Produto produtoNaoPerecivel  = new Produto(nome, id, quant, preco);
                produtosNaoPereciveis.add(produtoNaoPerecivel );
                System.out.println("Inclusão confirmada!");
                break;
            case 2:
                String dataValidadeStr;
                LocalDate dataValidade = null;
                do {
                    System.out.println("Data de validade (dd/MM/yyyy): ");
                    dataValidadeStr = ler.nextLine();
                    dataValidade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }while(dataValidade == null) ;

                dataValidade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Perecivel produtoPerecivel = new Perecivel(nome, id, quant, preco, dataValidade);
                produtosPereciveis.add(produtoPerecivel);
                System.out.println("Inclusão confirmada.");

                break;

        }


    }

    public static void relatorio(ArrayList<Produto> produtosNaoPereciveis, ArrayList<Perecivel> produtosPereciveis) {
        System.out.println("=== RELATÓRIO DE PRODUTOS ===");

        System.out.println("=== Pereciveis ===");
        for (Produto produto : produtosNaoPereciveis)
            System.out.println(produto.toString());

        System.out.println("=== Nao Pereciveis ===");
        for (Perecivel perecivel : produtosPereciveis)
            System.out.println(perecivel.toString()); // falta arruamar


    }

    public static void venda(ArrayList<Produto> produtosNaoPereciveis, ArrayList<Perecivel> produtosPereciveis, String id , String tipo) {
        Scanner ler = new Scanner(System.in);
        Produto produtoEncontrado = null;
        boolean achou = false;
        int quantVendida;
        Perecivel produto = null;
        double porctDesc;
        double precoUnitario = 0;
        double precoTotal = 0;



        for (Perecivel perecivel : produtosPereciveis) {
            System.out.println("id = " + perecivel.getId() );

            if (perecivel.getId().equals(id)) {
                produtoEncontrado = perecivel;
                achou = true;
                System.out.println("achou" + produtoEncontrado);
                break;
            }
        }
        if (!achou) {
            for (Produto prod : produtosNaoPereciveis) {
                if (prod.getId().equals(id)) {
                    produtoEncontrado = prod;
                    achou = true;
                    System.out.println("achou 2" + produtoEncontrado);
                    break;
                }

            }
        }
        if (!achou) {
            System.out.println("Produto inexistente");
            return;
        }

        do{
            System.out.println("Quantidade Vendida");
            quantVendida = ler.nextInt();
        }while (quantVendida <= 0);

        if(quantVendida > produtoEncontrado.getQuant()){
            System.out.println("SEM ESTOQUE SUFICIENTE,VENDA CANCELADA");
        }else{
            if (tipo.equals("Perecivel")){
                produto = (Perecivel) produtoEncontrado;

                if(produto.diasParaVencer() < 1){
                    System.out.println("PRODUTO FORA DE VALIDADE, VENDA CANCELADA");
                    return;
                } else if (produto.diasParaVencer() == 1){
                    System.out.println("Desconto aplicado 1 dia para vencer");
                    porctDesc = ler.nextDouble();

                    while (porctDesc < 0 || porctDesc>100){
                        System.out.println("Insira percentual valido");
                        porctDesc = ler.nextDouble();
                    }

                    precoUnitario = produto.getPreco();
                    precoTotal = quantVendida * precoUnitario;
                    System.out.println("Preço unitario c/ Desconto: " + produto.aplicaDesconto(porctDesc));
                    System.out.println("Preço Total c/ Desconto: " + produto.aplicaDesconto(porctDesc));

                }

            }else{
                precoUnitario = produtoEncontrado.getPreco();
                precoTotal = quantVendida * precoUnitario;
            }


            System.out.println("Quantidade vendida: " + quantVendida);
            System.out.println("Preço unitário: " + precoUnitario);
            System.out.println("Preço total: " + precoTotal);
            System.out.println("Confirma venda: S(SIM) / N(NÃO)? ");
            ler.nextLine();
            String confirmacao = ler.nextLine().toUpperCase();
            if (confirmacao.equals("S")) {
                System.out.println("Venda realizada.");
                if(tipo.equals("Perecivel")) {
                    produto.setQuant(produto.getQuant() - quantVendida);
                }else{
                    produtoEncontrado.setQuant(produtoEncontrado.getQuant() - quantVendida);

                }
            } else {
                System.out.println("Venda cancelada.");
            }

        }

    }






}