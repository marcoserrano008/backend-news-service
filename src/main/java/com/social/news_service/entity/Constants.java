package com.social.news_service.entity;

public final class Constants {

    public static final String SQL_RESTRICTION = "is_deleted = false";

    private Constants() {
        throw new RuntimeException("Cannot initialize a constant class");
    }

    public static class AttachmentTable {
        public static final String TABLE_NAME = "attachment";

        public static final class Id {
            public static final String NAME = "id";
        }

        public static final class AccountId {
            public static final String NAME = "account_id";
        }

        public static final class Field {
            public static final String NAME = "field";
        }

        public static final class Name {
            public static final String NAME = "name";
        }

        public static final class Size {
            public static final String NAME = "size";
        }

        public static final class MimeType {
            public static final String NAME = "mimetype";
        }

        public static final class CreatedDate {
            public static final String NAME = "created_date";
        }

        public static final class IsDeleted {
            public static final String NAME = "is_deleted";
        }
    }

    public static class BulletinTable {
        public static final String TABLE_NAME = "bulletin";

        public static final class Id {
            public static final String NAME = "id";
        }

        public static final class AccountId {
            public static final String NAME = "account_id";
        }

        public static final class SenderUserId {
            public static final String NAME = "sender_user_id";
        }

        public static final class Body {
            public static final String NAME = "body";

            public static final int LENGTH = 2000;
        }

        public static final class CreatedDate {
            public static final String NAME = "created_date";
        }

        public static final class IsDeleted {
            public static final String NAME = "is_deleted";
        }

        public static final class CommentsCounter {
            public static final String NAME = "comments_counter";
        }
    }

    public static class CommentTable {
        public static final String TABLE_NAME = "comment";

        public static final class Id {
            public static final String NAME = "id";
        }

        public static final class AccountId {
            public static final String NAME = "account_id";
        }

        public static final class SenderUserId {
            public static final String NAME = "sender_user_id";
        }

        public static final class Content {
            public static final String NAME = "content";

            public static final int LENGTH = 500;
        }

        public static final class RepliesCounter {
            public static final String NAME = "replies_counter";
        }

        public static final class CreatedDate {
            public static final String NAME = "created_date";
        }

        public static final class IsDeleted {
            public static final String NAME = "is_deleted";
        }
    }
}
