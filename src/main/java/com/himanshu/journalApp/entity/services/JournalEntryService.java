package com.himanshu.journalApp.entity.services;

import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.entity.repository.JournaleEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
 @Autowired
    private JournaleEntryRepository journaleEntryRepository;
 @Autowired
 private UserService userService;
 @Transactional
 public void saveEntry(JournalEntry journalEntry, String userName) {
     try {
         User user = userService.findByUsername(userName);
         journalEntry.setDate(LocalDateTime.now());
         JournalEntry saved = journaleEntryRepository.save(journalEntry);
         user.getJournalEntries().add(saved);

         userService.saveUser(user);
     }
     catch (Exception e) {
         System.out.println(e);
         throw new RuntimeException("an error has occured",e);
     }
 }
    public void saveEntry(JournalEntry journalEntry){
        journaleEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journaleEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journaleEntryRepository.findById(id);
    }
   @Transactional public boolean deleteById(ObjectId id,String userName)
    {   boolean removed = false;
        try {
        User user = userService.findByUsername(userName);
         removed =  user.getJournalEntries().removeIf(x ->  x.getId().equals(id));
        if(removed)
        {
            userService.saveUser(user);
            journaleEntryRepository.deleteById(id);
        }
    }
    catch (Exception e)
    {
        System.out.println(e);
        throw new RuntimeException("An error occured while deleting the entry", e);
    }
      return removed;
    }
    public List<JournalEntry> findByUserName(String userName)
    {
           return null;
    }


}
