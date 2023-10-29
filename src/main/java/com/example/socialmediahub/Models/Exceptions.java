package com.example.socialmediahub.Models;

public class Exceptions {

    static class IDException extends Exception{
        /**
         * Exception for non unique post ID.
         *
         * @param errorMessage Error message to display from Exception super class
         */
        public IDException(String errorMessage){
            super(errorMessage);
        }
    }

    static class IllegalNumber extends Exception{
        /**
         * Exception for illegal negative numbers.
         *
         * @param errorMessage Error message to display from Exception super class
         */
        public IllegalNumber(String errorMessage){
            super(errorMessage);
        }
    }

    static class DoesNotExist extends Exception{
        /**
         * Exception for when post object does not exist.
         *
         * @param errorMessage Error message to display from Exception super class
         */
        public DoesNotExist(String errorMessage){
            super(errorMessage);
        }
    }
}