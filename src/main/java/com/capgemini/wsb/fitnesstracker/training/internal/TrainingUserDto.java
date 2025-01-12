package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object for Training User.
 */
@Getter
@AllArgsConstructor
class TrainingUserDto {
    
    /**
     * The ID of the user.
     */
    private final Long id;
    
    /**
     * The first name of the user.
     */
    private final String firstName;
    
    /**
     * The last name of the user.
     */
    private final String lastName;
    
    /**
     * The email of the user.
     */
    private final String email;
}