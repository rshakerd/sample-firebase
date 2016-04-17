package sample.web.ui;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.scheduling.annotation.Async;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class InMemoryMessageRepository implements MessageRepository {

	private static AtomicLong counter = new AtomicLong();
	
	private static String BASE_REPO_URL = "https://brilliant-fire-2824.firebaseio.com/";

	private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<Long, Message>();

	@Override
	public Iterable<Message> findAll() {
	
	  refreshData();
		return this.messages.values();
	}

	
	@Override
  public Message save(Message message) {
	  Long id = message.getId();
    if (id == null) {
      id = counter.incrementAndGet();
      message.setId(id);
    }
    this.messages.put(id, message);
    return message;
  }

	@Async
  public void refreshData() {
	  
	// Create a connection to your Firebase database
    Firebase ref = new Firebase(BASE_REPO_URL);

    // Listen for realtime changes
    ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snap) {
            Message newMsg = new Message();
            newMsg.setText((String)snap.getValue());
            save(newMsg);
        }
        @Override public void onCancelled(FirebaseError error) { }
    });
	}

	@Override
	public Message findMessage(Long id) {
		return this.messages.get(id);
	}

	@Override
	public void deleteMessage(Long id) {
		this.messages.remove(id);
	}

}
