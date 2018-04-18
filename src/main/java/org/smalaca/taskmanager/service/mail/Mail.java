package org.smalaca.taskmanager.service.mail;

import org.smalaca.taskmanager.domain.EmailAddress;

public class Mail {
    private final EmailAddress from;
    private final EmailAddress to;
    private final String topic;
    private final String content;

    private Mail(Builder builder) {
        from = builder.from;
        to = builder.to;
        topic = builder.topic;
        content = builder.content;
    }

    public static class Builder {
        private EmailAddress from;
        private EmailAddress to;
        private String topic;
        private String content;

        public Builder from(EmailAddress emailAddress) {
            this.from = emailAddress;
            return this;
        }

        public Builder to(EmailAddress emailAddress) {
            to = emailAddress;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Mail build() {
            return new Mail(this);
        }
    }
}
