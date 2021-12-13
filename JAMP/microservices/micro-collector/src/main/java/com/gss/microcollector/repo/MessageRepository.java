package com.gss.microcollector.repo;

import com.gss.microcollector.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
