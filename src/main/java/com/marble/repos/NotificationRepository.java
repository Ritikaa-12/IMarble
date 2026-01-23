package com.marble.repos;

import com.marble.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    /**
     * Finds all notifications intended for a specific target (userId).
     */
    List<Notification> findByTarget(Integer target);
}