package com.example.project.notification;

import com.example.project._core.utils.LocalDateTimeFormatter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j

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
            private String createdAgo; //10분전, 1시간전 이런식으로 출력하기위함. 클라이언트는 시간이 잘못셋팅되어있을수있기때문에 서버 시간을 기준으로함
            private Boolean checked; // 알림 확인 여부

            public NotificationDTO(Notification notification) {
                this.id = notification.getId();
                this.createdAt = LocalDateTimeFormatter.convert( notification.getCreatedAt() );
                this.createdAgo = LocalDateTimeFormatter.getDuration(notification.getCreatedAt());
                this.checked = notification.getChecked();
            }
        }
    }


}
