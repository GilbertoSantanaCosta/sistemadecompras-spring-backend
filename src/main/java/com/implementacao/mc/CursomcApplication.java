package com.implementacao.mc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.implementacao.mc.domain.Categoria;
import com.implementacao.mc.domain.Cidade;
import com.implementacao.mc.domain.Cliente;
import com.implementacao.mc.domain.Endereco;
import com.implementacao.mc.domain.Estado;
import com.implementacao.mc.domain.ItemPedido;
import com.implementacao.mc.domain.Pagamento;
import com.implementacao.mc.domain.PagamentoComBoleto;
import com.implementacao.mc.domain.PagamentoComCartao;
import com.implementacao.mc.domain.Pedido;
import com.implementacao.mc.domain.Produto;
import com.implementacao.mc.domain.enums.EstadoPagamento;
import com.implementacao.mc.domain.enums.TipoCliente;
import com.implementacao.mc.repositories.CategoriaRepository;
import com.implementacao.mc.repositories.CidadeRepository;
import com.implementacao.mc.repositories.ClienteRepository;
import com.implementacao.mc.repositories.EnderecoRepository;
import com.implementacao.mc.repositories.EstadoRepository;
import com.implementacao.mc.repositories.ItemPedidoRepository;
import com.implementacao.mc.repositories.PagamentoRepository;
import com.implementacao.mc.repositories.PedidoRepository;
import com.implementacao.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtosRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteReposistory;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");
		Categoria cat3 = new Categoria(null,"cama");
		Categoria cat4 = new Categoria(null,"mesa");
		Categoria cat5 = new Categoria(null,"banho");
		Categoria cat6 = new Categoria(null,"cozinha");
		Categoria cat7 = new Categoria(null,"quarto");
		Categoria cat8 = new Categoria(null,"sala");
		
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCetegoria().addAll(Arrays.asList(cat1));
		p2.getCetegoria().addAll(Arrays.asList(cat1,cat2));
		p3.getCetegoria().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll( Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtosRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Gilberto", "g.s.d.c@hotmail.com", "38733323907", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "rua dos abreus", "231", "casa", "jardim das palmeiras", "06390077", cl1, c2);
		Endereco e2 = new Endereco(null, "Av. Barão de Itapura", "34", "casa", "Vila Angelino Rossi", "13073-300", cl1, c3);
		
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cl1.getTelefones().addAll(Arrays.asList("98647-7263","95363-8372"));
		
		
		clienteReposistory.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 13:35"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("24/07/2017 13:35"), cl1, e2);	
		
		Pagamento pagt1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);
		Pagamento pagt2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 14:45"), null);
		ped2.setPagamento(pagt2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagt1,pagt2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

				
	
		
	}
	
	

}
