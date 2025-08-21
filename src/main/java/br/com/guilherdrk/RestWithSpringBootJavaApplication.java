package br.com.guilherdrk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestWithSpringBootJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringBootJavaApplication.class, args);
	}

}

/*
* O que são Logs?
* Imagine que sua aplicação está rodando e em segundo plano, ela está
* anotando tudo o que acontece;
*
* Funcionam como um diário detalhado do que está acontecendo nos
* bastidores da aplicação
*
* Os niveis de Log
* Trace (trace debug info warn Error)
* Degub ( Debug info Warn Error )
* Info ( Info Warn Error )
* Warn ( warn Error )
* Error ( Error
*
* qual a importancia dos logs?
* sem logs, descobrir o que está acontecendo é um verdadeiro pesadelo;
* Economizam um tempo precioso, tanto na detecção quanto na solução
*
* */