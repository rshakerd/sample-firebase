package sample.web.ui;

import java.util.Calendar;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.firebase.client.DataSnapshot;

public class Message {

	private String id;

	private String text;

	private Calendar created = Calendar.getInstance();
	
	Message (DataSnapshot snapshot ){
	  this.key = snapshot.getKey();
	  this.id = (String) ((Map)snapshot.getValue()).get("name");
    this.text = (String) ((Map)snapshot.getValue()).get("text");
	}
	private String key;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getCreated() {
		return this.created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    
    Message compareWith = (Message) obj;
    return this.key.equals(compareWith.key);
  }

}
