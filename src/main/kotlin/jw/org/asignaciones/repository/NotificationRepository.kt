package jw.org.asignaciones.repository

import jw.org.asignaciones.model.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository: JpaRepository<Notification, Int> {
}