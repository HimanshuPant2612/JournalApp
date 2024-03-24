package com.himanshu.journalApp.entity.repository;


import com.himanshu.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournaleEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
