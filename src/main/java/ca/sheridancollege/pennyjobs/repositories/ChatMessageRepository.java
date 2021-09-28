package ca.sheridancollege.pennyjobs.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.pennyjobs.beans.ChatMessage;
import ca.sheridancollege.pennyjobs.beans.MessageStatus;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
	List<ChatMessage> findByChatId(String chatId);
}
