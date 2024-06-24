package com.example.project.notification;

import com.example.project._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class NotificationResponse {

    @Data
    public static class ListDTO{
        private List<NotificationDTO> notifications;

        public ListDTO(List<Notification> notificationList) {
            this.notifications = notificationList.stream().map(NotificationDTO::new).toList();
        }

        @Data
        class NotificationDTO{
            private Integer id;
            private String createdAt; // 생성 일자 !!주의: 타입이 문자열임에 주의!
            private Boolean checked; // 알림 확인 여부

            public NotificationDTO(Notification notification) {
                this.id = notification.getId();
                this.createdAt = LocalDateTimeFormatter.convert( notification.getCreatedAt() );
                this.checked = notification.getChecked();
            }
        }
    }


}
