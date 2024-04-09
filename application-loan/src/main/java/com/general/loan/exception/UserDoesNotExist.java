package com.general.loan.exception;

public class UserDoesNotExist extends RuntimeException{

        private String message;

        public UserDoesNotExist() {}

        public UserDoesNotExist(String message)
        {
                super(message);
            this.message = message;

        }
}
