package com.gss.microvisualizer.repo;


import com.gss.microvisualizer.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
