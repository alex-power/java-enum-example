package com.example.demo;

import java.util.function.BiFunction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		
		Operation.ADD.apply(2, 3);

	}

	enum Operation {
		ADD((x, y) -> x + y),
		SUBTRACT((x, y) -> x - y),
		MULTIPLY((x, y) -> x * y);

		Operation(BiFunction<Integer, Integer, Integer> operation) {
			this.operation = operation;
		}

		private final BiFunction<Integer, Integer, Integer> operation;

		public int apply(int x, int y) {
			return operation.apply(x, y);
		}
	}
	class Adder implements BiFunction<Integer, Integer, Integer> {
		@Override
		public Integer apply(Integer x, Integer y) {
			return x + y;
		}
	}
}