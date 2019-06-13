package data.model.user;

import java.util.Date;

public class Message {

    private String userName;
    private String text;
    private Date date;

    private Message(Builder builder) {
        this.userName = builder.userName;
        this.text = builder.text;
        if (builder.date == null) {
            this.date = new Date();
        } else {
            this.date = builder.date;
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public String getText() {
        return this.text;
    }

    public Date getDate() {
        return this.date;
    }

    public static class Builder {

        private String userName;
        private String text;
        private Date date;


        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
