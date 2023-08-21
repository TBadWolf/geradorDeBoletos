package geradorDeBoletos;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class Main {
    public static void main(String[] args) {
        Datas datas = Datas.novasDatas().comDocumento(20, 8, 2023)
                .comProcessamento(20, 8, 2023).comVencimento(27, 8, 2023);
 
//DADOS DO "RECEBEDOR"
        Endereco enderecoBeneficiario = Endereco.novoEndereco()
        		.comLogradouro("Av. das Nações Unidas, n 3003")
        		.comCidade("Bonfim")
        		.comCep("06233-903")
        		.comUf("SP");
        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
        		.comNomeBeneficiario("MercadoPago.com Representações Ltda")
        		.comEndereco(enderecoBeneficiario)
        		.comDocumento("10.573.521/0001-91")
        		.comAgencia("2345")
        		.comDigitoAgencia("6")
        		.comCarteira("26")
        		.comNossoNumero("05930195194")
        		.comCodigoBeneficiario("3856128")
        		.comNumeroConvenio("1234567");   		

  
//DADOS DO PAGADOR
            Endereco enderecoPagador = Endereco.novoEndereco()
            	.comLogradouro("Av Araguaia 72")
            	.comCep("01234-111")
            	.comBairro("Bairro Santa Mena")
            	.comCidade("São Paulo")
            	.comUf("SP");
        
            Pagador pagador = Pagador.novoPagador()
            	.comNome("José dos Santos")
            	.comDocumento("111.222.333-12")
            	.comEndereco(enderecoPagador);  	

//Informando qual Banco     
        Banco banco = new Bradesco();

//Montando informações do boleto
        Boleto boleto = Boleto.novoBoleto()
            .comBanco(banco)
            .comDatas(datas)
            .comDescricoes("descricao 1", "descricao 2", "descricao 3",
                        "descricao 4", "descricao 5")
            .comBeneficiario(beneficiario)
            .comValorDescontos("50.00")
            .comPagador(pagador)
            .comValorBoleto("200.00")
            .comInstrucoes("Não receber pagamento em cheque", "Pagavel em qualquer agencia bancária",
                        "Boleto com vencimento no final de semana poderá ser pago no próximo dia útil", 
                        "Se houver algum problema com a compra, acesse https://www.mercadopago.com.br/ajuda")
            .comLocaisDePagamento("Em qualquer agência bancária")
            .comNumeroDoDocumento("5930195194");

//Gera boletos dentro da pasta do projeto
		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
		try {
			gerador.geraPDF("BoletoBradesco2.pdf");
			System.out.println("Boleto gerado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
}
