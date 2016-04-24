package sample.web.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryMessageRepository implements MessageRepository {

	private final ConcurrentMap<String, Set<Message>> messages = 
	    new ConcurrentHashMap<String, Set<Message>>();

	@Override
	public Iterable<Message> findAll() {
	
	  List<Message> allMsgs = new ArrayList<Message>();
	  this.messages.values().forEach(entry -> {
	    allMsgs.addAll(entry);
	  });
		return allMsgs;
	}

	
	@Override
  public Message save(Message message) {
	  String id = message.getId();
	  if(this.messages.containsKey(id))
	    this.messages.get(id).add(message);
	  else {
	    Set<Message> initMsgList = new HashSet<Message>();
	    initMsgList.add(message);
	    this.messages.put(id,initMsgList);
	  }
    return message;
  }

	@Override
	public Iterable<Message> findMessages(String id) {
		return this.messages.get(id);
	}

	@Override
	public void deleteMessage(Long id) {
		this.messages.remove(id);
	}

}
