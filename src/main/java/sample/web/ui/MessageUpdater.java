package sample.web.ui;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/*
 * Author : rahul@primaseller.com
 * Created on : 24-Apr-2016
 */
@Component
public class MessageUpdater {
  
  private final Firebase firebaseDataSource;
  
  private static String BASE_REPO_URL = "https://brilliant-fire-2824.firebaseio.com/";
  
  public MessageUpdater() {
    this.firebaseDataSource = new Firebase(BASE_REPO_URL);
    init();
  }

  @Autowired
  MessageRepository msgRepository;
  
  private void init(){
    this.firebaseDataSource.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snap) {
          if(snap.hasChildren()){
            snap.getChildren().forEach( data -> {
              Message newMsg = new Message(data);
              msgRepository.save(newMsg);
            });
          }
         
      }
      
      @Override public void onCancelled(FirebaseError error) {
        
      }
  });
  }
}
