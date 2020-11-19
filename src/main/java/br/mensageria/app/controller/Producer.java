package br.mensageria.app.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.mensageria.app.request.TransferenciaRequest;

@RestController
@RequestMapping("/producer")
public class Producer {
	
	

	@PostMapping
	public ResponseEntity inserir(@RequestBody TransferenciaRequest t) {
		try {
			inserir(t);
			return ResponseEntity.status(200).body("bem sucedido");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Erro do servidor!");

		}
	}

	private final static String QUEUE_NAME = "transferencia";

    public static void main(String[] argv) throws Exception {
    	TransferenciaRequest t = new TransferenciaRequest();
    	//criando conexão com o server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        //tentando a conexão
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
        	
        	//declaração da queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            
            //setando uma mensagem a ser publicada
            String message = "{hashOrigem: " + t.getHashOrigem() + ", hashDestino: " + t.getHashDestino() + ", tipo: " + t.getTipo() + ", valor: " + t.getValor() +" }";
            
            //aderindo a mensagem ao queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            
            //mostrando que a mensagem foi enviada
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}
