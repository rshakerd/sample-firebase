package sample.web.ui;


public interface MessageRepository {

	Iterable<Message> findAll();

	Message save(Message message);

	Iterable<Message> findMessages(String id); 

	void deleteMessage(Long id);

}
