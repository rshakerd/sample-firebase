package sample.web.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

@SpringBootApplication
public class BaseWebApplication {

	@Bean
	public MessageRepository messageRepository() {
		return new InMemoryMessageRepository();
	}

	@Bean
	public Converter<String, Iterable<Message>> messageConverter() {
		return new Converter<String, Iterable<Message>>() {

        @Override
        public Iterable<Message> convert(String id) {
          return messageRepository().findMessages(id);
        }
		};
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BaseWebApplication.class, args);
	}

}
